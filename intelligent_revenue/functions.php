<?php
// Get logged-in user data
function getUser($pdo, $id) {
    $stmt = $pdo->prepare("SELECT * FROM users WHERE id = ?");
    $stmt->execute([$id]);
    return $stmt->fetch();
}

// Check authentication and role
function requireLogin($pdo) {
    if (!isset($_SESSION['user_id'])) {
        header('Location: index.php');
        exit;
    }
    return getUser($pdo, $_SESSION['user_id']);
}

// Get setting value
function getSetting($pdo, $key) {
    $stmt = $pdo->prepare("SELECT setting_value FROM settings WHERE setting_key = ?");
    $stmt->execute([$key]);
    $row = $stmt->fetch();
    return $row ? $row['setting_value'] : null;
}

// Fraud detection after payment insertion
function checkFraud($pdo, $payment_id, $user_id, $amount) {
    $alerts = [];

    // 1. Frequency check: more than X payments in the last hour
    $freqLimit = getSetting($pdo, 'fraud_frequency_limit') ?: 3;
    $stmt = $pdo->prepare("SELECT COUNT(*) as cnt FROM payments WHERE user_id = ? AND created_at > DATE_SUB(NOW(), INTERVAL 1 HOUR)");
    $stmt->execute([$user_id]);
    $row = $stmt->fetch();
    if ($row['cnt'] > $freqLimit) {
        $alerts[] = [
            'payment_id' => $payment_id,
            'user_id' => $user_id,
            'alert_type' => 'high_frequency',
            'description' => "User made more than {$freqLimit} payments in the last hour (total: {$row['cnt']})."
        ];
    }

    // 2. Amount deviation: compare to average of last 5 payments
    $stmt2 = $pdo->prepare("SELECT AVG(amount) as avg_amt FROM (SELECT amount FROM payments WHERE user_id = ? ORDER BY created_at DESC LIMIT 5) as t");
    $stmt2->execute([$user_id]);
    $avg = $stmt2->fetchColumn();
    if ($avg && $avg > 0) {
        $deviationPercent = getSetting($pdo, 'fraud_amount_deviation') ?: 50;
        $deviation = abs($amount - $avg) / $avg * 100;
        if ($deviation > $deviationPercent) {
            $alerts[] = [
                'payment_id' => $payment_id,
                'user_id' => $user_id,
                'alert_type' => 'amount_deviation',
                'description' => "Payment amount ($amount) deviates by " . round($deviation,2) . "% from user's average ($avg)."
            ];
        }
    }

    if (!empty($alerts)) {
        // Update payment status to flagged
        $stmt = $pdo->prepare("UPDATE payments SET status = 'flagged' WHERE id = ?");
        $stmt->execute([$payment_id]);

        // Insert fraud alerts
        $stmtInsert = $pdo->prepare("INSERT INTO fraud_alerts (payment_id, user_id, alert_type, description) VALUES (?, ?, ?, ?)");
        foreach ($alerts as $alert) {
            $stmtInsert->execute([$alert['payment_id'], $alert['user_id'], $alert['alert_type'], $alert['description']]);
        }
    }
}


// ... (existing functions: getUser, requireLogin, checkFraud, getSetting, etc.)

/**
 * Mappings of valid identifiers to official full names.
 * Simulates a live NIMC / CAC API response.
 */
$VALID_NIN_NAMES = [
    '12345678901' => 'Thomas Omotayo',
    '23456789012' => 'Jane Smith',
    '34567890123' => 'Michael Johnson',
    '45678901234' => 'Emily Brown',
    '56789012345' => 'David Wilson'
];

$VALID_CAC_NAMES = [
    'RC-1234567' => 'Ethon Digital Enterprises',
    'RC-7654321' => 'Tech Solutions Inc',
    'BN-1111111' => 'Local Business Name',
    'BN-2222222' => 'Alpha Enterprises',
    'RC-9999999' => 'Prime Holdings'
];

/**
 * Verify NIN and return true if valid.
 */
function verifyNIN($nin) {
    global $VALID_NIN_NAMES;
    return array_key_exists($nin, $VALID_NIN_NAMES);
}

/**
 * Verify CAC business number and return true if valid.
 */
function verifyCAC($business_number) {
    global $VALID_CAC_NAMES;
    return array_key_exists(strtoupper($business_number), $VALID_CAC_NAMES);
}

/**
 * Get the official full name for a given NIN.
 * Returns null if the NIN is invalid.
 */
function getNINName($nin) {
    global $VALID_NIN_NAMES;
    return $VALID_NIN_NAMES[$nin] ?? null;
}

/**
 * Get the official full name for a given CAC number.
 * Returns null if the number is invalid.
 */
function getCACName($business_number) {
    global $VALID_CAC_NAMES;
    return $VALID_CAC_NAMES[strtoupper($business_number)] ?? null;
}

// ... rest of functions
?>
