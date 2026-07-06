<?php
$start_date = $_GET['start_date'] ?? date('Y-m-01');
$end_date   = $_GET['end_date']   ?? date('Y-m-d');

// Summary query (no join, so it's fine)
$where = "WHERE p.status = 'verified' AND DATE(p.payment_date) BETWEEN :start AND :end";
$stmt = $pdo->prepare("SELECT COUNT(*) as count, SUM(p.amount) as total FROM payments p $where");
$stmt->execute(['start' => $start_date, 'end' => $end_date]);
$summary = $stmt->fetch();

// Detailed payments with user names (join – fixed aliases)
$detailsWhere = "WHERE p.status = 'verified' AND DATE(p.payment_date) BETWEEN :start AND :end";
$stmt = $pdo->prepare("SELECT p.*, u.full_name FROM payments p JOIN users u ON p.user_id = u.id $detailsWhere ORDER BY p.payment_date DESC");
$stmt->execute(['start' => $start_date, 'end' => $end_date]);
$payments = $stmt->fetchAll();

// Chart data (only payments table – fine)
$chartWhere = "WHERE p.status = 'verified' AND DATE(p.payment_date) BETWEEN :start AND :end";
$chartStmt = $pdo->prepare("SELECT DATE(p.payment_date) as day, SUM(p.amount) as total FROM payments p $chartWhere GROUP BY day ORDER BY day");
$chartStmt->execute(['start' => $start_date, 'end' => $end_date]);
$chartData = $chartStmt->fetchAll();
$days = array_column($chartData, 'day');
$totals = array_column($chartData, 'total');
?>
<div class="report-print-area">
    <h3>Verified Revenue Report</h3>

    <form method="get" class="filter-form" style="margin-bottom:20px; display:flex; gap:15px; align-items:center;">
        <input type="hidden" name="page" value="officer_generate_reports">
        <div class="form-group" style="margin:0;">
            <label>Start Date</label>
            <input type="date" name="start_date" value="<?= $start_date ?>">
        </div>
        <div class="form-group" style="margin:0;">
            <label>End Date</label>
            <input type="date" name="end_date" value="<?= $end_date ?>">
        </div>
        <button type="submit" class="btn primary">Filter</button>
        <button type="button" class="btn success" onclick="window.print()"><i class="fa fa-print"></i> Print Report</button>
    </form>

    <div class="stats-row">
        <div class="stat-card">
            <i class="fa fa-check-circle"></i>
            <span>Verified Payments</span>
            <strong><?= $summary['count'] ?></strong>
        </div>
        <div class="stat-card">
            <i class="fa fa-money-bill-wave"></i>
            <span>Total Verified (₦)</span>
            <strong><?= number_format($summary['total'] ?? 0, 2) ?></strong>
        </div>
    </div>

    <div style="width:100%; margin-bottom:30px;">
        <canvas id="officerRevenueChart"></canvas>
    </div>

    <h4>Transaction Details</h4>
    <table class="data-table">
        <thead>
            <tr>
                <th>Receipt No</th>
                <th>Taxpayer</th>
                <th>Tax Type</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
        <?php foreach ($payments as $p): ?>
            <tr>
                <td><?= $p['receipt_number'] ?></td>
                <td><?= htmlspecialchars($p['full_name']) ?></td>
                <td><?= ucfirst($p['tax_type']) ?></td>
                <td>₦<?= number_format($p['amount'], 2) ?></td>
                <td><?= $p['payment_date'] ?></td>
            </tr>
        <?php endforeach; ?>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
const ctx = document.getElementById('officerRevenueChart').getContext('2d');
new Chart(ctx, {
    type: 'bar',
    data: {
        labels: <?= json_encode($days) ?>,
        datasets: [{
            label: 'Daily Revenue (₦)',
            data: <?= json_encode($totals) ?>,
            backgroundColor: '#27ae60'
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: { display: true }
        }
    }
});
</script>