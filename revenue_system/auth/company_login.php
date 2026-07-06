<?php
session_start();
include("../config/db.php");

if (isset($_POST['login'])) {
    $cac = $_POST['cac'];
    $password = $_POST['password'];

    $stmt = $conn->prepare("SELECT * FROM companies WHERE cac_number=?");
    $stmt->bind_param("s", $cac);
    $stmt->execute();
    $result = $stmt->get_result(); 
  

    if ($user = $result->fetch_assoc()) {
        if (password_verify($password, $user['password'])) {
            $_SESSION['user_id'] = $user['id'];
           $_SESSION['type'] = 'company';
            $_SESSION['fullname'] = $user['fullname'];

            header("Location: ../user/dashboard.php");
        } else {
            $error = "Invalid password";
        }
    } else {
        $error = "CAC not found";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Company Login</title>
    <link rel="stylesheet" href="../assets/css/auth.css">
</head>
<body>

<div class="auth-container">
    <form method="POST" class="auth-box">
        <h2>Company Login</h2>

        <input type="text" name="cac" placeholder="CAC Number" required>
        <input type="password" name="password" placeholder="Password" required>

        <button type="submit" name="login">Login</button>

        <p>Don't have an account? <a href="company_register.php">Register</a></p>

        <?php if(isset($error)) echo "<p class='error'>$error</p>"; ?>
    </form>
</div>

</body>
</html>