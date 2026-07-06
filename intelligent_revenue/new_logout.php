<?php

// Logout – fully destroy session & cookie
if ($page === 'logout') {
    // Try to log audit, but don’t let failure stop logout
    if (isset($_SESSION['user'])) {
        try {
            $stmt = $db->prepare("INSERT INTO audit_logs (user_id, action, details, ip_address) VALUES (?,?,?,?)");
            $stmt->execute([$_SESSION['user']['id'], 'logout', 'User logged out', $_SERVER['REMOTE_ADDR'] ?? '127.0.0.1']);
        } catch (Exception $e) {
            // Database error – ignore, we still need to logout
        }
    }
    
    // Destroy session completely
    $_SESSION = [];
    if (ini_get("session.use_cookies")) {
        $params = session_get_cookie_params();
        setcookie(session_name(), '', time() - 42000,
            $params["path"], $params["domain"],
            $params["secure"], $params["httponly"]
        );
    }
    session_destroy();
    
    header('Location: ?page=login');
    exit;
}

?>