<?php
session_start();
include("../config/db.php");

$user_id = $_SESSION['user_id'];
$type = $_SESSION['type'];

if ($type == "company") {
    $user = $conn->query("SELECT * FROM companies WHERE id=$user_id")->fetch_assoc();
} else {
    $user = $conn->query("SELECT * FROM individuals WHERE id=$user_id")->fetch_assoc();
}

if (isset($_POST['save_income'])) {

    $income = $_POST['income'];
    $user_id = $_SESSION['user_id'];
    $type = $_SESSION['type'];

    if ($type == "company") {
        $conn->query("UPDATE companies SET annual_income='$income' WHERE id=$user_id");
    } else {
        $conn->query("UPDATE individuals SET annual_income='$income' WHERE id=$user_id");
    }

    echo "<script>alert('Income Updated Successfully');</script>";
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="../assets/css/dashboard.css">
</head>

<body>

<div class="container">

    <!-- Sidebar -->
    <div class="sidebar">
        <h2>Revenue System</h2>

        <a href="dashboard.php">Dashboard</a>
        <a href="profile.php">Profile</a>
        <?php
        if (isset($_SESSION['type']) && $_SESSION['type'] == 'company') {
            $pay_url = "company_payments.php";
        } else {
            $pay_url = "individual_payments.php";
        }
        ?>

        <a href="<?php echo $pay_url; ?>" class="btn btn-primary">
            Pay Tax
        </a>


        <!--a href="pay_tax.php">Pay Tax</a-->
        <a href="../auth/logout.php">Logout</a>
    </div>

    <!-- Main -->
    <div class="main">

       <h1>Welcome, <?php echo $_SESSION['fullname']; ?></h1>


    



        <form method="POST" class="profile-card">

    <h3>Update Annual Income</h3>

    <input type="number" name="income" placeholder="Enter Annual Income (₦)" required>

    <button type="submit" name="save_income">Save Income</button>

</form>
<p><strong>Annual Income:</strong> ₦<?php echo number_format($user['annual_income']); ?></p>

        <div class="cards">

            <div class="card">
                <h3>User Type</h3>
                <p><?php echo ucfirst($type); ?></p>
            </div>

            <div class="card">
                <h3>Status</h3>
                <p>
                    <?php
                    if (isset($user['is_verified']) && $user['is_verified'] == 1) {
                        echo "<span class='verified'>Verified</span>";
                    } else {
                        echo "<span class='pending'>Pending</span>";
                    }
                    ?>
                </p>
            </div>

            <div class="card">
                <h3>Tax Status</h3>
                <p>Ready for Payment</p>
            </div>

        </div>

    </div>

</div>





</body>
</html>