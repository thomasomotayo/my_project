<?php
require_once 'config.php';
$error = '';
$success = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = trim($_POST['email']);
    if (empty($email)) {
        $error = 'Please enter your email address.';
    } else {
        // Check if email exists
        $stmt = $pdo->prepare("SELECT id, full_name FROM users WHERE email = ?");
        $stmt->execute([$email]);
        $user = $stmt->fetch();
        if ($user) {
            // Generate token and expiry (1 hour)
            $token = bin2hex(random_bytes(32));
            $expires = date('Y-m-d H:i:s', strtotime('+1 hour'));

            // Save token
            $stmt = $pdo->prepare("INSERT INTO password_resets (email, token, expires_at) VALUES (?, ?, ?)");
            $stmt->execute([$email, $token, $expires]);

            // In production, send email. For testing, display the link.
            $resetLink = "http://{$_SERVER['HTTP_HOST']}/intelligent_revenue/reset_password.php?token=$token";
            $success = "A password reset link has been generated.<br>
                        <a href='$resetLink'>$resetLink</a><br>
                        (In production, this would be emailed to you.)";
        } else {
            $error = 'No account found with that email address.';
        }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forgot Password - RevenueGuard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body class="login-body">
    <div class="login-container">
        <div class="login-card">
            <h2>Reset Your Password</h2>
            <p>Enter your registered email address to receive a reset link.</p>
            <?php if ($error): ?><div class="alert error"><?= $error ?></div><?php endif; ?>
            <?php if ($success): ?><div class="alert success"><?= $success ?></div><?php endif; ?>
            <form method="post">
                <div class="input-group">
                    <i class="fa fa-envelope"></i>
                    <input type="email" name="email" placeholder="Email Address" required>
                </div>
                <button type="submit" class="btn primary">Send Reset Link</button>
            </form>
            <p style="margin-top:15px;"><a href="login.php">Back to Login</a></p>
        </div>
    </div>
</body>
</html>