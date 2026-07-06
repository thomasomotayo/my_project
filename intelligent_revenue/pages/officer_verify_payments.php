<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['verify'])) {
    $pid = $_POST['payment_id'];
    $status = $_POST['new_status'];
    if (in_array($status, ['verified','rejected'])) {
        $stmt = $pdo->prepare("UPDATE payments SET status = ?, verified_by = ?, verified_at = NOW() WHERE id = ?");
        $stmt->execute([$status, $_SESSION['user_id'], $pid]);
    }
}
$pending = $pdo->query("SELECT p.*, u.full_name FROM payments p JOIN users u ON p.user_id = u.id WHERE p.status IN ('pending','flagged') ORDER BY p.created_at DESC")->fetchAll();
?>
<h3>Pending & Flagged Payments</h3>
<table class="data-table">
    <thead><tr><th>ID</th><th>Taxpayer</th><th>Tax Type</th><th>Amount</th><th>Date</th><th>Status</th><th>Action</th></tr></thead>
    <tbody>
    <?php foreach ($pending as $p): ?>
    <tr>
        <td><?= $p['id'] ?></td>
        <td><?= htmlspecialchars($p['full_name']) ?></td>
        <td><?= $p['tax_type'] ?></td>
        <td><?= number_format($p['amount'],2) ?></td>
        <td><?= $p['payment_date'] ?></td>
        <td><span class="badge <?= $p['status']=='flagged'?'danger':'warning'?>"><?= $p['status'] ?></span></td>
        <td>
            <form method="post" style="display:flex; gap:5px;">
                <input type="hidden" name="payment_id" value="<?= $p['id'] ?>">
                <input type="hidden" name="new_status" value="verified">
                <button type="submit" name="verify" class="btn small success">Verify</button>
            </form>
            <form method="post" style="display:inline;">
                <input type="hidden" name="payment_id" value="<?= $p['id'] ?>">
                <input type="hidden" name="new_status" value="rejected">
                <button type="submit" name="verify" class="btn small danger">Reject</button>
            </form>
        </td>
    </tr>
    <?php endforeach; ?>
    </tbody>
</table>