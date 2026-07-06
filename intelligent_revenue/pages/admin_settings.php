<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    foreach ($_POST as $key => $value) {
        $stmt = $pdo->prepare("UPDATE settings SET setting_value = ? WHERE setting_key = ?");
        $stmt->execute([$value, $key]);
    }
    echo '<div class="alert success">Settings updated.</div>';
}
$settings = $pdo->query("SELECT * FROM settings")->fetchAll();
$config = [];
foreach ($settings as $s) $config[$s['setting_key']] = $s['setting_value'];
?>
<h3>System Configuration</h3>
<form method="post" class="form-card">
    <div class="form-group">
        <label>Income Tax Rate (%)</label>
        <input name="tax_rate_income" value="<?= $config['tax_rate_income'] ?? 15 ?>" required>
    </div>
    <div class="form-group">
        <label>Business Tax Rate (%)</label>
        <input name="tax_rate_business" value="<?= $config['tax_rate_business'] ?? 10 ?>" required>
    </div>
    <div class="form-group">
        <label>Fraud Frequency Limit (payments/hour)</label>
        <input name="fraud_frequency_limit" value="<?= $config['fraud_frequency_limit'] ?? 3 ?>" required>
    </div>
    <div class="form-group">
        <label>Fraud Amount Deviation (%)</label>
        <input name="fraud_amount_deviation" value="<?= $config['fraud_amount_deviation'] ?? 50 ?>" required>
    </div>
    <button type="submit" class="btn primary">Save Settings</button>
</form>