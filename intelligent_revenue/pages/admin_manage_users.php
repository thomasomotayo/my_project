<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['action'])) {
    if ($_POST['action'] === 'toggle_status') {
        $uid = $_POST['user_id'];
        $newStatus = $_POST['status'] === 'active' ? 'inactive' : 'active';
        $stmt = $pdo->prepare("UPDATE users SET status = ? WHERE id = ? AND role != 'admin'");
        $stmt->execute([$newStatus, $uid]);
    } elseif ($_POST['action'] === 'add_user') {
        $username = $_POST['username'];
        $password = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $role = $_POST['role'];
        $full_name = $_POST['full_name'];
        $email = $_POST['email'];
        $stmt = $pdo->prepare("INSERT INTO users (username, password, role, full_name, email) VALUES (?,?,?,?,?)");
        $stmt->execute([$username, $password, $role, $full_name, $email]);
    }
}
$users = $pdo->query("SELECT id, username, full_name, email, role, status FROM users WHERE role != 'admin'")->fetchAll();
?>

<h3>System Users</h3>
<button onclick="document.getElementById('addUserForm').style.display='block'" class="btn primary">Add New User</button>
<div id="addUserForm" style="display:none; margin-top:20px;">
    <form method="post" class="form-card">
        <input type="hidden" name="action" value="add_user">
        <div class="form-group"><input name="full_name" placeholder="Full Name" required></div>
        <div class="form-group"><input name="email" placeholder="Email" required></div>
        <div class="form-group"><input name="username" placeholder="Username" required></div>
        <div class="form-group"><input type="password" name="password" placeholder="Password" required></div>
        <div class="form-group"><select name="role"><option value="officer">Revenue Officer</option><option value="auditor">Auditor</option><option value="taxpayer">Taxpayer</option></select></div>
        <button type="submit" class="btn success">Save</button>
        <button type="button" onclick="document.getElementById('addUserForm').style.display='none'" class="btn">Cancel</button>
    </form>
</div>

<table class="data-table">
    <thead><tr><th>Name</th><th>Email</th><th>Username</th><th>Role</th><th>Status</th><th>Actions</th></tr></thead>
    <tbody>
    <?php foreach ($users as $u): ?>
    <tr>
        <td><?= htmlspecialchars($u['full_name']) ?></td>
        <td><?= htmlspecialchars($u['email']) ?></td>
        <td><?= htmlspecialchars($u['username']) ?></td>
        <td><?= ucfirst($u['role']) ?></td>
        <td><span class="badge <?= $u['status']=='active'?'success':'danger'?>"><?= $u['status'] ?></span></td>
        <td>
            <form method="post" style="display:inline">
                <input type="hidden" name="action" value="toggle_status">
                <input type="hidden" name="user_id" value="<?= $u['id'] ?>">
                <input type="hidden" name="status" value="<?= $u['status'] ?>">
                <button type="submit" class="btn small"><?= $u['status']=='active'?'Deactivate':'Activate' ?></button>
            </form>
        </td>
    </tr>
    <?php endforeach; ?>
    </tbody>
</table>