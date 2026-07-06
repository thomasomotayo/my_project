<?php

require_once 'config.php';
$user = requireLogin($pdo);
$role = $user['role'];

// Allowed pages per role
$rolePages = [
    'admin' => ['admin_manage_users','admin_view_reports','admin_fraud_alerts','admin_settings'],
    'officer' => ['officer_verify_payments','officer_revenue_records','officer_generate_reports'],
    'auditor' => ['auditor_transaction_history','auditor_fraud_alerts','auditor_audit_reports'],
    'taxpayer' => ['taxpayer_dashboard','taxpayer_make_payment','taxpayer_payment_history','taxpayer_download_receipt']
];

$page = $_GET['page'] ?? ($rolePages[$role][0] ?? '');
if (!in_array($page, $rolePages[$role])) {
    $page = $rolePages[$role][0];
}
$pageFile = "pages/{$page}.php";
if (!file_exists($pageFile)) {
    die("Page not found.");
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - RevenueGuard</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="dashboard">
        <!-- Sidebar -->
        <aside class="sidebar">
            <div class="sidebar-brand">
                <div class="logo-section">
                    <img src="images/nigeria2.png" alt="Logo" style="width: 100px; height: auto; margin-bottom: 10px;">
                    <h2 style ="color: white;"> NRS | Nigeria Revenue Service</h2>
                    
                </div>
            </div>
            <ul class="nav">
                <?php foreach ($rolePages[$role] as $p):
                    $labels = [
                        'admin_manage_users' => 'Manage Users',
                        'admin_view_reports' => 'Reports',
                        'admin_fraud_alerts' => 'Fraud Alerts',
                        'admin_settings' => 'Settings',
                        'officer_verify_payments' => 'Verify Payments',
                        'officer_revenue_records' => 'Revenue Records',
                        'officer_generate_reports' => 'Generate Reports',
                        'auditor_transaction_history' => 'Transaction History',
                        'auditor_fraud_alerts' => 'Fraud Alerts',
                        'auditor_audit_reports' => 'Audit Reports',
                        'taxpayer_dashboard' => 'Dashboard',
                        'taxpayer_make_payment' => 'Make Payment',
                        'taxpayer_payment_history' => 'Payment History',
                        'taxpayer_download_receipt' => 'Download Receipt'
                    ];
                    $active = ($page === $p) ? 'active' : '';
                ?>
                <li><a href="?page=<?= $p ?>" class="<?= $active ?>"><i class="fa fa-<?= ($p == 'admin_manage_users'?'users': ($p == 'admin_view_reports'?'chart-bar':'cogs')) ?>"></i> <?= $labels[$p] ?></a></li>
                <?php endforeach; ?>
            </ul>
            <div class="sidebar-footer">
                <a href="logout.php" class="btn logout-btn"><i class="fa fa-sign-out-alt"></i> Logout</a>
            </div>
        </aside>

        <!-- Main Content -->
        <main class="main-content">
            <header class="topbar">
                <h2><?= $labels[$page] ?? ucfirst($role) ?></h2>
                <div class="user-info">
                    <i class="fa fa-user-circle"></i>
                    <span><?= htmlspecialchars($user['full_name']) ?> (<?= ucfirst($role) ?>)</span>
                </div>
            </header>
            <div class="content">
                <?php include $pageFile; ?>
            </div>
        </main>
    </div>
</body>
</html>