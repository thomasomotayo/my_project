<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['resolve'])) {
    $aid = $_POST['alert_id'];
    $notes = $_POST['notes'];
    $stmt = $pdo->prepare("UPDATE fraud_alerts SET is_resolved = 1, auditor_notes = ? WHERE id = ?");
    $stmt->execute([$notes, $aid]);
}
$alerts = $pdo->query("SELECT fa.*, p.receipt_number, u.full_name FROM fraud_alerts fa JOIN payments p ON fa.payment_id = p.id JOIN users u ON fa.user_id = u.id WHERE fa.is_resolved = 0 ORDER BY fa.created_at DESC")->fetchAll();
?>
<h3>Open Fraud Alerts</h3>
<table class="data-table">
    <thead><tr><th>Alert ID</th><th>User</th><th>Receipt No</th><th>Type</th><th>Description</th><th>Action</th></tr></thead>
    <tbody>
    <?php foreach ($alerts as $a): ?>
    <tr>
        <td><?= $a['id'] ?></td>
        <td><?= htmlspecialchars($a['full_name']) ?></td>
        <td><?= $a['receipt_number'] ?></td>
        <td><?= $a['alert_type'] ?></td>
        <td><?= htmlspecialchars($a['description']) ?></td>
        <td>
            <form method="post" style="display:flex; gap:5px;">
                <input type="hidden" name="alert_id" value="<?= $a['id'] ?>">
                <input type="text" name="notes" placeholder="Investigation notes" required>
                <button type="submit" name="resolve" class="btn small success">Resolve</button>
            </form>
        </td>
    </tr>
    <?php endforeach; ?>
    </tbody>
</table>