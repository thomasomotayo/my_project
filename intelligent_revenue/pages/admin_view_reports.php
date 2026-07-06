<?php
// Date filtering
$start_date = $_GET['start_date'] ?? date('Y-m-01');
$end_date   = $_GET['end_date']   ?? date('Y-m-d');

$where = "WHERE status IN ('verified','flagged') AND DATE(payment_date) BETWEEN :start AND :end";
$stmt = $pdo->prepare("SELECT COUNT(*) as count, SUM(amount) as total FROM payments $where");
$stmt->execute(['start' => $start_date, 'end' => $end_date]);
$summary = $stmt->fetch();

$stmt = $pdo->prepare("SELECT * FROM payments $where ORDER BY payment_date DESC");
$stmt->execute(['start' => $start_date, 'end' => $end_date]);
$payments = $stmt->fetchAll();

// Chart data: daily totals
$chartStmt = $pdo->prepare("SELECT DATE(payment_date) as day, SUM(amount) as total FROM payments $where GROUP BY day ORDER BY day");
$chartStmt->execute(['start' => $start_date, 'end' => $end_date]);
$chartData = $chartStmt->fetchAll();
$days = array_column($chartData, 'day');
$totals = array_column($chartData, 'total');
?>
<div class="report-print-area">
    <h3>Revenue Report</h3>

    <!-- Filter Form -->
    <form method="get" class="filter-form" style="margin-bottom:20px; display:flex; gap:15px; align-items:center;">
        <input type="hidden" name="page" value="admin_view_reports">
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

    <!-- Summary Cards -->
    <div class="stats-row">
        <div class="stat-card">
            <i class="fa fa-file-invoice"></i>
            <span>Total Transactions</span>
            <strong><?= $summary['count'] ?></strong>
        </div>
        <div class="stat-card">
            <i class="fa fa-money-bill-wave"></i>
            <span>Total Revenue (₦)</span>
            <strong><?= number_format($summary['total'] ?? 0, 2) ?></strong>
        </div>
    </div>

    <!-- Chart -->
    <div style="width:100%; margin-bottom:30px;">
        <canvas id="adminRevenueChart"></canvas>
    </div>

    <!-- Data Table -->
    <h4>Transaction Details</h4>
    <table class="data-table">
        <thead>
            <tr>
                <th>Receipt No</th>
                <th>Tax Type</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        <?php foreach ($payments as $p): ?>
            <tr>
                <td><?= $p['receipt_number'] ?></td>
                <td><?= ucfirst($p['tax_type']) ?></td>
                <td>₦<?= number_format($p['amount'], 2) ?></td>
                <td><?= $p['payment_date'] ?></td>
                <td><span class="badge <?= $p['status']=='verified'?'success':'warning'?>"><?= $p['status'] ?></span></td>
            </tr>
        <?php endforeach; ?>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
const ctx = document.getElementById('adminRevenueChart').getContext('2d');
new Chart(ctx, {
    type: 'line',
    data: {
        labels: <?= json_encode($days) ?>,
        datasets: [{
            label: 'Daily Revenue (₦)',
            data: <?= json_encode($totals) ?>,
            borderColor: '#2e86c1',
            backgroundColor: 'rgba(46,134,193,0.1)',
            tension: 0.2,
            fill: true
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