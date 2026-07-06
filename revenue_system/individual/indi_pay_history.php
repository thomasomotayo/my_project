<?php
session_start();
include("../config/db.php");

$user_id = $_SESSION['user_id'];

// Get only individual payments
$result = $conn->query("
    SELECT * 
    FROM individual_payments 
    WHERE user_id = '$user_id'
    ORDER BY created_at DESC
");
?>

<!DOCTYPE html>
<html>
<head>
    <title>Individual Payment History</title>
    <link rel="stylesheet" href="../assets/css/dashboard.css">
</head>

<body>

<div class="container">
    <div class="main">
        <h1>Individual Payment History (NIN)</h1>

        <table class="table">
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>NIN</th>
                <th>Amount</th>
                <th>Reference</th>
                <th>Status</th>
                <th>Date</th>
                <th>Action</th>
            </tr>

            <?php while($row = $result->fetch_assoc()) { ?>
            <tr>
                <td><?php echo $row['id']; ?></td>
                <td><?php echo $row['fullname']; ?></td>
                <td><?php echo $row['nin']; ?></td>
                <td>₦<?php echo number_format($row['amount']); ?></td>
                <td><?php echo $row['reference']; ?></td>
                <td><?php echo $row['status']; ?></td>
                <td><?php echo $row['created_at']; ?></td>
                <td>
                    <a href="invoice.php?id=<?php echo $row['id']; ?>&type=individual" class="btn">
                        Print Invoice
                    </a>
                </td>
            </tr>
            <?php } ?>

        </table>
    </div>
</div>

</body>
</html>