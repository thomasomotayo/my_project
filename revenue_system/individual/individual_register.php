<?php
include("../config/db.php");
include("../includes/nin_verify.php");

if (isset($_POST['register'])) {

    $nin = $_POST['nin'];
    $verify = verifyNIN($nin);


    if (!$verify['status']) {
        echo "<script>alert('Invalid NIN');</script>";
        exit();
    }

    $fullname = $_POST['fullname'];
    $email = $_POST['email'];
    $password = password_hash($_POST['password'], PASSWORD_DEFAULT);

    $stmt = $conn->prepare("INSERT INTO individuals (fullname, nin, email, password, is_verified) VALUES (?, ?, ?, ?, 1)");
    $stmt->bind_param("ssss", $fullname, $nin, $email, $password);

    $stmt->execute();

    echo "<script>alert('NIN Verified Successfully');</script>";
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Individual Registration</title>
    <link rel="stylesheet" href="../assets/css/auth.css">
</head>
<body>

<div class="auth-container">
    <form method="POST" class="auth-box">
        <h2>Individual Registration</h2>

        <input type="text" name="fullname" placeholder="Full Name" required>
        <input type="text" name="nin" placeholder="NIN" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>

        <button type="submit" name="register">Register</button>

        <p>Already registered? <a href="individual_login.php">Login</a></p>
    </form>
</div>

</body>
</html>