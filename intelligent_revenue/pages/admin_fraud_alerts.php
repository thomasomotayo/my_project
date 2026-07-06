<?php
$alerts = $pdo->query("SELECT fa.*, p.receipt_number, u.full_name FROM fraud_alerts fa JOIN payments p ON fa.payment_id = p.id JOIN users u ON fa.user_id = u.id ORDER BY fa.created_at DESC")->fetchAll();
?>
<h3>Fraud Alerts</h3>
<table class="data-table">
    <thead><tr><th>Alert ID</th><th>User</th><th>Receipt No</th><th>Type</th><th>Description</th><th>Status</th></tr></thead>
    <tbody>
    <?php foreach ($alerts as $a): ?>
    <tr>
        <td><?= $a['id'] ?></td>
        <td><?= htmlspecialchars($a['full_name']) ?></td>
        <td><?= $a['receipt_number'] ?></td>
        <td><?= $a['alert_type'] ?></td>
        <td><?= htmlspecialchars($a['description']) ?></td>
        <td><span class="badge <?= $a['is_resolved']?'success':'danger'?>"><?= $a['is_resolved']?'Resolved':'Open' ?></span></td>
    </tr>
    <?php endforeach; ?>
    </tbody>
</table>