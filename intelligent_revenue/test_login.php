<?php
require_once 'config.php';
$password = 'admin123';
$stmt = $pdo->prepare("SELECT * FROM users WHERE username = 'admin'");
$stmt->execute();
$user = $stmt->fetch();
if ($user && password_verify($password, $user['password'])) {
    echo 'Password is CORRECT!';
} else {
    echo 'FAILED.';
    echo 'Hash from DB: ' . $user['password'];
}
?>