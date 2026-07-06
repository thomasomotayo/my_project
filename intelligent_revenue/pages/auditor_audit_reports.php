<?php
$start_date = $_GET['start_date'] ?? date('Y-m-01');
$end_date   = $_GET['end_date']   ?? date('Y-m-d');

// Auditor sees all transactions, plus fraud alerts summary
$where = "WHERE DATE(p.payment_date) BETWEEN :start AND :end";
$stmt = $pdo->prepare("SELECT COUNT(*) as count, SUM(p.amount) as total 
                       FROM payments p $where");
$stmt->execute(['start' => $start_date, 'end' => $end_date]);
$summary = $stmt->fetch();

// Fraud alerts count
$fraudStmt = $pdo->prepare("SELECT COUNT(*) FROM fraud_alerts fa JOIN payments p ON fa.payment_id = p.id $where");
$fraudStmt->execute(['start' => $start_date, 'end' => $end_date]);
$fraudCount = $fraudStmt->fetchColumn();

$stmt = $pdo->prepare("SELECT p.*, u.full_name FROM payments p JOIN users u ON p.user_id = u.id $where ORDER BY p.payment_date DESC");
$stmt->execute(['start' => $start_date, 'end' => $end_date]);
$payments = $stmt->fetchAll();

// Chart
$chartStmt = $pdo->prepare("SELECT DATE(p.payment_date) as day, SUM(p.amount) as total FROM payments p $where GROUP BY day ORDER BY day");
$chartStmt->execute(['start' => $start_date, 'end' => $end_date]);
$chartData = $chartStmt->fetchAll();
$days = array_column($chartData, 'day');
$totals = array_column($chartData, 'total');
?>
<div class="report-print-area">
    <h3>Audit Report</h3>

    <form method="get" class="filter-form" style="margin-bottom:20px; display:flex; gap:15px; align-items:center;">
        <input type="hidden" name="page" value="auditor_audit_reports">
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
            <i class="fa fa-exchange-alt"></i>
            <span>Total Transactions</span>
            <strong><?= $summary['count'] ?></strong>
        </div>
        <div class="stat-card">
            <i class="fa fa-money-bill-wave"></i>
            <span>Total Amount (₦)</span>
            <strong><?= number_format($summary['total'] ?? 0, 2) ?></strong>
        </div>
        <div class="stat-card">
            <i class="fa fa-exclamation-triangle"></i>
            <span>Fraud Alerts</span>
            <strong><?= $fraudCount ?></strong>
        </div>
    </div>

    <div style="width:100%; margin-bottom:30px;">
        <canvas id="auditorRevenueChart"></canvas>
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
                <th>Status</th>
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
                <td><span class="badge <?= $p['status']=='verified'?'success':($p['status']=='flagged'?'danger':'warning')?>"><?= $p['status'] ?></span></td>
            </tr>
        <?php endforeach; ?>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
const ctx = document.getElementById('auditorRevenueChart').getContext('2d');
new Chart(ctx, {
    type: 'line',
    data: {
        labels: <?= json_encode($days) ?>,
        datasets: [{
            label: 'Daily Revenue (₦)',
            data: <?= json_encode($totals) ?>,
            borderColor: '#e74c3c',
            backgroundColor: 'rgba(231,76,60,0.1)',
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