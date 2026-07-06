<?php
$records = $pdo->query("SELECT p.*, u.full_name FROM payments p JOIN users u ON p.user_id = u.id WHERE p.status = 'verified' ORDER BY p.payment_date DESC")->fetchAll();
?>
<h3>Verified Revenue Records</h3>
<table class="data-table">
    <thead><tr><th>Receipt No</th><th>Taxpayer</th><th>Type</th><th>Amount</th><th>Date</th></tr></thead>
    <tbody>
    <?php foreach ($records as $r): ?>
    <tr>
        <td><?= $r['receipt_number'] ?></td>
        <td><?= htmlspecialchars($r['full_name']) ?></td>
        <td><?= $r['tax_type'] ?></td>
        <td><?= number_format($r['amount'],2) ?></td>
        <td><?= $r['payment_date'] ?></td>
    </tr>
    <?php endforeach; ?>
    </tbody>
</table>