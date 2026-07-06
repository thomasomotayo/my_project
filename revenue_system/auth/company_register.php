<?php
include("../config/db.php");
include("../includes/cac_verify.php");

if (isset($_POST['register'])) {

    $cac = $_POST['cac'];
    $verify = verifyCAC($cac);

    if (!$verify['status']) {
        echo "<script>alert('Invalid CAC Number');</script>";
        exit();
    }

    $name = $verify['company_name'];
    $email = $_POST['email'];
    $password = password_hash($_POST['password'], PASSWORD_DEFAULT);

    $stmt = $conn->prepare("INSERT INTO companies (company_name, cac_number, email, password, is_verified) VALUES (?, ?, ?, ?, 1)");
    $stmt->bind_param("ssss", $name, $cac, $email, $password);

    $stmt->execute();

    echo "<script>alert('CAC Verified Successfully');</script>";
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Company Registration</title>
    <link rel="stylesheet" href="../assets/css/auth.css">
</head>
<body>

<div class="auth-container">
    <form method="POST" class="auth-box">
        <h2>Company Registration</h2>

        <input type="text" name="company_name" placeholder="Company Name" required>
        <input type="text" name="cac" placeholder="CAC Number" required>
        <input type="email" name="email" placeholder="Company Email" required>
        <input type="password" name="password" placeholder="Password" required>

        <button type="submit" name="register">Register</button>

        <p>Already have an account? <a href="company_login.php">Login</a></p>
    </form>
</div>

</body>
</html>