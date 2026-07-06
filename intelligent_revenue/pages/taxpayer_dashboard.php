<?php
$user = getUser($pdo, $_SESSION['user_id']);
$stats = $pdo->prepare("SELECT COUNT(*) as total_payments, SUM(amount) as total_paid FROM payments WHERE user_id = ? AND status = 'verified'");
$stats->execute([$user['id']]);
$stat = $stats->fetch();
$totalPayments = $stat['total_payments'] ?? 0;
$totalPaid = $stat['total_paid'] ?? 0;
?>
<div class="dashboard-welcome">
    <h3>Welcome, <?= htmlspecialchars($user['full_name']) ?></h3>
    <div class="stats-row">
        <div class="stat-card">
            <i class="fa fa-file-invoice"></i>
            <span>Total Payments</span>
            <strong><?= $totalPayments ?></strong>
        </div>
        <div class="stat-card">
            <i class="fa fa-money-bill-wave"></i>
            <span>Total Paid (₦)</span>
            <strong><?= number_format($totalPaid, 2) ?></strong>
        </div>
    </div>
    <div class="profile-card">
        <h4>Your Identification</h4>
        <?php if ($user['business_number']): ?>
            <p><strong>CAC Number:</strong> <?= htmlspecialchars($user['business_number']) ?></p>
        <?php elseif ($user['nin']): ?>
            <p><strong>NIN:</strong> <?= htmlspecialchars($user['nin']) ?></p>
        <?php else: ?>
            <p>No identification on file.</p>
        <?php endif; ?>
    </div>
</div>

<!-- Add some simple styling (can be placed in style.css, but here inline) -->
<style>
.dashboard-welcome h3 { margin-bottom:20px; }
.stats-row { display:flex; gap:20px; margin-bottom:30px; }
.stat-card {
    background:white; padding:20px; border-radius:8px; box-shadow:0 2px 10px rgba(0,0,0,0.05);
    flex:1; text-align:center;
}
.stat-card i { font-size:24px; color:var(--secondary); margin-bottom:8px; }
.stat-card span { display:block; font-size:14px; color:#666; }
.stat-card strong { font-size:20px; }
.profile-card {
    background:white; padding:20px; border-radius:8px; box-shadow:0 2px 10px rgba(0,0,0,0.05);
}
.profile-card h4 { margin-bottom:10px; }
</style>