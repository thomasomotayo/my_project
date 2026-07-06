<?php
if (!isset($_GET['id'])) header('Location: dashboard.php?page=taxpayer_payment_history');
$stmt = $pdo->prepare("SELECT * FROM payments WHERE id = ? AND user_id = ?");
$stmt->execute([$_GET['id'], $_SESSION['user_id']]);
$payment = $stmt->fetch();
if (!$payment) die("Receipt not found.");

// Fetch user info for the receipt
$user = getUser($pdo, $_SESSION['user_id']);
?>
<!DOCTYPE html>
<html>
<head>
    <title>Receipt <?= $payment['receipt_number'] ?></title>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; margin: 40px; }
        .receipt { border: 2px solid #000; padding: 30px; max-width: 600px; margin: auto; }
        .header { text-align: center; margin-bottom: 20px; }
        .header h2 { color: #1a5276; }
        .details { margin-bottom: 20px; }
        .details p { margin: 6px 0; }
        .footer { margin-top: 30px; text-align: center; font-size: 14px; }
        hr { border-top: 1px dashed #ccc; }
    </style>
</head>
<body>
<div class="receipt">
    <div class="header">
        <h2>Nigeria Revenue Service</h2>
        <h3>Payment Receipt</h3>
    </div>
    <div class="details">
        <p><strong>Receipt No:</strong> <?= $payment['receipt_number'] ?></p>
        <p><strong>Transaction ID:</strong> <?= $payment['transaction_id'] ?></p>
        <p><strong>Date:</strong> <?= $payment['payment_date'] ?></p>
        <p><strong>Tax Type:</strong> <?= ucfirst($payment['tax_type']) ?></p>
        <p><strong>Amount Paid:</strong> ₦<?= number_format($payment['amount'], 2) ?></p>
        <p><strong>Status:</strong> <?= $payment['status'] ?></p>
        <hr>
        <p><strong>Taxpayer:</strong> <?= htmlspecialchars($user['full_name']) ?></p>
        <?php if ($user['business_number']): ?>
            <p><strong>CAC Number:</strong> <?= htmlspecialchars($user['business_number']) ?></p>
        <?php elseif ($user['nin']): ?>
            <p><strong>NIN:</strong> <?= htmlspecialchars($user['nin']) ?></p>
        <?php endif; ?>
    </div>
    <div class="footer">
        <p>Thank you for your payment.</p>
    </div>
</div>
<script>window.print();</script>
</body>
</html>