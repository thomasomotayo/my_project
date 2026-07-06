<?php
$user_id = $_SESSION['user_id'];
$payments = $pdo->prepare("SELECT * FROM payments WHERE user_id = ? ORDER BY created_at DESC");
$payments->execute([$user_id]);
$payments = $payments->fetchAll();
?>
<h3>My Payment History</h3>
<table class="data-table">
    <thead><tr><th>Receipt No</th><th>Tax Type</th><th>Amount</th><th>Date</th><th>Status</th><th>Action</th></tr></thead>
    <tbody>
    <?php foreach ($payments as $p): ?>
    <tr>
        <td><?= $p['receipt_number'] ?></td>
        <td><?= $p['tax_type'] ?></td>
        <td><?= number_format($p['amount'],2) ?></td>
        <td><?= $p['payment_date'] ?></td>
        <td><span class="badge <?= $p['status']=='verified'?'success':($p['status']=='flagged'?'danger':'warning')?>"><?= $p['status'] ?></span></td>
        <td><a href="?page=taxpayer_download_receipt&id=<?= $p['id'] ?>" class="btn small">Receipt</a></td>
    </tr>
    <?php endforeach; ?>
    </tbody>
</table>