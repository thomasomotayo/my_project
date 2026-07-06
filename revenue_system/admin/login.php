<?php
session_start();
include("../config/db.php");

if (isset($_POST['login'])) {

    $username = trim($_POST['username']);
    $password = trim($_POST['password']);

    $stmt = $conn->prepare("SELECT * FROM admin WHERE username=?");
    $stmt->bind_param("s", $username);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($admin = $result->fetch_assoc()) {

        if (password_verify($password, $admin['password'])) {

            // Create session
            $_SESSION['admin_id'] = $admin['id'];
            $_SESSION['admin_name'] = $admin['username'];

            header("Location: admin_dashboard.php");
            exit();

        } else {
            $error = "Invalid password";
        }

    } else {
        $error = "Admin not found";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" href="../assets/css/admin_auth.css">
</head>

<body>

<div class="login-container">

    <form method="POST" class="login-box">
        <h2>Admin Login</h2>

        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>

        <button type="submit" name="login">Login</button>

        <?php if(isset($error)) echo "<p class='error'>$error</p>"; ?>
    </form>

</div>

</body>
</html>