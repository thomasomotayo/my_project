<?php
$transactions = $pdo->query("SELECT p.*, u.full_name FROM payments p JOIN users u ON p.user_id = u.id ORDER BY p.created_at DESC")->fetchAll();
?>
<h3>All Transactions</h3>
<table class="data-table">
    <thead><tr><th>ID</th><th>User</th><th>Amount</th><th>Date</th><th>Status</th></tr></thead>
    <tbody>
    <?php foreach ($transactions as $t): ?>
    <tr>
        <td><?= $t['id'] ?></td>
        <td><?= htmlspecialchars($t['full_name']) ?></td>
        <td><?= number_format($t['amount'],2) ?></td>
        <td><?= $t['payment_date'] ?></td>
        <td><?= $t['status'] ?></td>
    </tr>
    <?php endforeach; ?>
    </tbody>
</table>