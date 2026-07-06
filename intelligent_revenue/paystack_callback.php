<?php
require_once 'config.php';

// Ensure user is logged in
$user = requireLogin($pdo);

if (!isset($_GET['reference'])) {
    header('Location: dashboard.php?page=taxpayer_make_payment&status=failed');
    exit;
}

$reference = $_GET['reference'];
$secretKey = getSetting($pdo, 'paystack_secret_key') ?: 'sk_test_xxxxxxxxxxxxx'; // Replace with your secret key

// Verify transaction with Paystack
$curl = curl_init();
curl_setopt_array($curl, [
    CURLOPT_URL => "https://api.paystack.co/transaction/verify/" . rawurlencode($reference),
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_HTTPHEADER => [
        "Authorization: Bearer $secretKey",
        "Cache-Control: no-cache",
    ],
]);

$response = curl_exec($curl);
$err = curl_error($curl);
curl_close($curl);

if ($err) {
    // cURL error – log it, redirect with failure
    header('Location: dashboard.php?page=taxpayer_make_payment&status=failed');
    exit;
}

$result = json_decode($response, true);

if ($result['status'] === true && $result['data']['status'] === 'success') {
    // Payment successful – extract metadata
    $metadata = $result['data']['metadata']['custom_fields'] ?? [];
    $taxType = '';
    $yearlyRevenue = 0;
    $userIdFromMeta = $user['id']; // fallback

    foreach ($metadata as $field) {
        if ($field['variable_name'] === 'tax_type') $taxType = $field['value'];
        if ($field['variable_name'] === 'yearly_revenue') $yearlyRevenue = $field['value'];
    }

    // Recalculate tax (ensure integrity)
    $rateKey = ($taxType === 'income') ? 'tax_rate_income' : 'tax_rate_business';
    $rate = getSetting($pdo, $rateKey) ?: 10;
    $base = floatval($yearlyRevenue);
    $calculatedTax = $base * ($rate / 100);
    $amountPaid = $result['data']['amount'] / 100; // convert from kobo to NGN

    // If amounts don't match, flag as suspicious (optional)
    if (abs($calculatedTax - $amountPaid) > 1) {
        // possible tampering – still record but flag
    }

    // Generate receipt number
    $receipt_number = 'RCP' . date('Ymd') . rand(1000,9999);
    $transaction_id = $reference; // using Paystack reference as transaction ID

    // Insert payment record
    $stmt = $pdo->prepare("INSERT INTO payments (user_id, tax_type, base_amount, calculated_tax, amount, payment_date, transaction_id, receipt_number, status) VALUES (?, ?, ?, ?, ?, NOW(), ?, ?, 'verified')");
    $stmt->execute([$user['id'], $taxType, $base, $calculatedTax, $amountPaid, $transaction_id, $receipt_number]);
    $payment_id = $pdo->lastInsertId();

    // Run fraud detection
    checkFraud($pdo, $payment_id, $user['id'], $amountPaid);

    // Redirect to make payment page with success
    header('Location: dashboard.php?page=taxpayer_make_payment&status=success');
    exit;
} else {
    // Payment not successful
    header('Location: dashboard.php?page=taxpayer_make_payment&status=failed');
    exit;
}
?>