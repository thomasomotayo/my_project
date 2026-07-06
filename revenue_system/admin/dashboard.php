<?php
session_start();
include("../config/db.php");

// Fetch statistics
$companies = $conn->query("SELECT COUNT(*) AS total FROM companies")->fetch_assoc();
$individuals = $conn->query("SELECT COUNT(*) AS total FROM individuals")->fetch_assoc();
$payments = $conn->query("SELECT COUNT(*) AS total FROM tax_payments")->fetch_assoc();
$totalRevenue = $conn->query("SELECT SUM(amount) AS total FROM tax_payments WHERE status='success'")->fetch_assoc();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../assets/css/admin.css">
</head>

<body>

<div class="container">

    <!-- Sidebar -->
    <div class="sidebar">
        <h2>Revenue Admin</h2>
        <a href="dashboard.php">Dashboard</a>
        <a href="manage_users.php">Manage Users</a>
        <a href="reports.php">Reports</a>
        <a href="../auth/logout.php">Logout</a>
    </div>

    <!-- Main Content -->
    <div class="main">

        <h1>Admin Dashboard</h1>

        <div class="cards">

            <div class="card">
                <h3>Companies</h3>
                <p><?php echo $companies['total']; ?></p>
            </div>

            <div class="card">
                <h3>Individuals</h3>
                <p><?php echo $individuals['total']; ?></p>
            </div>

            <div class="card">
                <h3>Total Payments</h3>
                <p><?php echo $payments['total']; ?></p>
            </div>

            <div class="card highlight">
                <h3>Total Revenue</h3>
                <p>₦<?php echo number_format($totalRevenue['total'], 2); ?></p>
            </div>

        </div>

        <div class="table-container">
            <h2>Recent Payments</h2>

            <table>
                <tr>
                    <th>ID</th>
                    <th>User Type</th>
                    <th>Amount</th>
                    <th>Reference</th>
                    <th>Status</th>
                </tr>

                <?php
                $result = $conn->query("SELECT * FROM tax_payments ORDER BY id DESC LIMIT 5");

                while ($row = $result->fetch_assoc()) {
                    echo "<tr>
                        <td>{$row['id']}</td>
                        <td>{$row['user_type']}</td>
                        <td>₦{$row['amount']}</td>
                        <td>{$row['reference']}</td>
                        <td>{$row['status']}</td>
                    </tr>";
                }
                ?>
            </table>
        </div>

    </div>

</div>

</body>
</html>