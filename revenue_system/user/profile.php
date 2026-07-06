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
?>

<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="../assets/css/dashboard.css">
</head>

<body>

<div class="container">

    <div class="main">
        <h1>My Profile</h1>

        <div class="profile-card">
            <p><strong>Name:</strong> <?php echo $user['company_name'] ?? $user['fullname']; ?></p>
            <p><strong>Email:</strong> <?php echo $user['email']; ?></p>

            <?php if ($type == "company") { ?>
                <p><strong>CAC:</strong> <?php echo $user['cac_number']; ?></p>
            <?php } else { ?>
                <p><strong>NIN:</strong> <?php echo $user['nin']; ?></p>
            <?php } ?>

            <p><strong>Verification:</strong>
                <?php echo $user['is_verified'] ? "Verified" : "Pending"; ?>
            </p>

            <a href="pay_history.php" class="btn">View Payment History</a>
        </div>

    </div>

</div>

</body>
</html>