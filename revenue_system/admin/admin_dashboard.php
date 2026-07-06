<?php
session_start();
include("../config/db.php");

// Get Companies count
$companiesResult = $conn->query("SELECT COUNT(*) AS total FROM companies");
$companies = $companiesResult->fetch_assoc();

// Get Individuals count
$individualsResult = $conn->query("SELECT COUNT(*) AS total FROM individuals");
$individuals = $individualsResult->fetch_assoc();

// Get Total Payments (combined)
$paymentsResult = $conn->query("
    SELECT COUNT(*) AS total FROM (
        SELECT id FROM individual_payments
        UNION ALL
        SELECT id FROM company_payments
    ) AS all_payments
");
$payments = $paymentsResult->fetch_assoc();

// Get Total Revenue
$totalRevenueResult = $conn->query("
    SELECT SUM(amount) AS total FROM (
        SELECT amount FROM individual_payments
        UNION ALL
        SELECT amount FROM company_payments
    ) AS all_revenue
");
$totalRevenue = $totalRevenueResult->fetch_assoc();
?>

<link rel="stylesheet" href="admin.css">

<div class="container">

    <div class="sidebar">
        <h2>Admin Panel</h2>
        <a href="admin_dashboard.php">Dashboard</a>
        <a href="manage_individuals.php">Individuals</a>
        <a href="manage_companies.php">Companies</a>
        <a href="payments.php">Payments</a>
        <a href="pdf_report.php">Reports</a>
        <a href="feedback.php">Feedback</a>
    </div>

    <div class="main">

        <div class="topbar">
            <h2>Welcome Admin</h2>
        </div>

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
                <p>₦<?php echo number_format($totalRevenue['total'] ?? 0, 2); ?></p>
            </div>

        </div>

        <!-- RECENT PAYMENTS TABLE -->
        <div class="table-container">

            <h3 class="table-title">Recent Payments</h3>

            <table>
                <tr>
                    <th>ID</th>
                    <th>User</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Reference</th>
                </tr>

                <?php
                // Get recent individual payments
                $ind = $conn->query("SELECT id, fullname AS user, amount, reference, 'Individual' AS type FROM individual_payments");

                // Get recent company payments
                $comp = $conn->query("SELECT id, company_name AS user, amount, reference, 'Company' AS type FROM company_payments");

                // Combine manually
                $paymentsData = [];

                while ($row = $ind->fetch_assoc()) {
                    $paymentsData[] = $row;
                }

                while ($row = $comp->fetch_assoc()) {
                    $paymentsData[] = $row;
                }

                // Sort by ID DESC
                usort($paymentsData, function ($a, $b) {
                    return $b['id'] - $a['id'];
                });

                // Limit 5
                $paymentsData = array_slice($paymentsData, 0, 5);

                foreach ($paymentsData as $row) {
                    echo "<tr>
                        <td>{$row['id']}</td>
                        <td>{$row['user']}</td>
                        <td>{$row['type']}</td>
                        <td>₦" . number_format($row['amount']) . "</td>
                        <td>{$row['reference']}</td>
                    </tr>";
                }
                ?>

            </table>

        </div>

    </div>

</div>