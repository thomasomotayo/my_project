<?php
session_start();
include("../config/db.php");

if (isset($_POST['login'])) {
    $nin = $_POST['nin'];
    $password = $_POST['password'];

    $stmt = $conn->prepare("SELECT * FROM individuals WHERE nin=?");
    $stmt->bind_param("s", $nin);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($user = $result->fetch_assoc()) {
        if (password_verify($password, $user['password'])) {
            $_SESSION['user_id'] = $user['id'];
            $_SESSION['fullname'] = $user['fullname'];
            $_SESSION['type'] = 'individual';
       
            header("Location: ../user/dashboard.php");
        } else {
            $error = "Invalid password";
        }
    } else {
        $error = "NIN not found";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Individual Login</title>
    <link rel="stylesheet" href="../assets/css/auth.css">
</head>
<body>

<div class="auth-container">
    <form method="POST" class="auth-box">
        <h2>Individual Login</h2>

        <input type="text" name="nin" placeholder="NIN" required>
        <input type="password" name="password" placeholder="Password" required>

        <button type="submit" name="login">Login</button>

        <p>Don't have an account? <a href="individual_register.php">Register</a></p>

        <?php if(isset($error)) echo "<p class='error'>$error</p>"; ?>
    </form>
</div>

</body>
</html>