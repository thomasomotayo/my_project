<?php
$user = getUser($pdo, $_SESSION['user_id']);
$successMsg = '';
$errorMsg = '';

// Check for success message from Paystack callback
if (isset($_GET['status']) && $_GET['status'] === 'success') {
    $successMsg = 'Payment successful! Your receipt is ready.';
}
if (isset($_GET['status']) && $_GET['status'] === 'failed') {
    $errorMsg = 'Payment failed or was cancelled. Please try again.';
}
?>
<h3>Make Tax Payment</h3>
<?php if ($successMsg): ?><div class="alert success"><?= htmlspecialchars($successMsg) ?></div><?php endif; ?>
<?php if ($errorMsg): ?><div class="alert error"><?= htmlspecialchars($errorMsg) ?></div><?php endif; ?>

<div class="form-card">
    <div class="form-group">
        <label>Tax Type</label>
        <select id="taxType" required>
            <option value="income">Income Tax</option>
            <option value="business">Business Tax</option>
        </select>
    </div>
    <div class="form-group">
        <label>Yearly Revenue / Income (₦)</label>
        <input type="number" id="yearlyRevenue" step="0.01" required>
    </div>
    <div class="form-group">
        <label>Calculated Tax</label>
        <input type="text" id="calculatedTax" readonly>
        <small>Tax Rate: <span id="taxRate">15%</span></small>
    </div>
    <button id="calculateBtn" class="btn primary">Calculate Tax</button>
    <div id="paystackSection" style="display:none; margin-top:15px;">
        <button id="paystackBtn" class="btn success">Proceed to Paystack</button>
    </div>
</div>

<!-- Paystack inline script -->
<script src="https://js.paystack.co/v1/inline.js"></script>
<script>
// Tax rates from database (injected by PHP)
const rates = {
    income: <?= getSetting($pdo,'tax_rate_income') ?: 15 ?>,
    business: <?= getSetting($pdo,'tax_rate_business') ?: 10 ?>
};

const taxTypeSelect = document.getElementById('taxType');
const revenueInput = document.getElementById('yearlyRevenue');
const calculatedField = document.getElementById('calculatedTax');
const rateSpan = document.getElementById('taxRate');
const calculateBtn = document.getElementById('calculateBtn');
const paystackSection = document.getElementById('paystackSection');
const paystackBtn = document.getElementById('paystackBtn');

let calculatedAmount = 0;

function updateTaxRate() {
    const type = taxTypeSelect.value;
    rateSpan.textContent = rates[type] + '%';
}

function calculateTax() {
    const revenue = parseFloat(revenueInput.value) || 0;
    const type = taxTypeSelect.value;
    const rate = rates[type];
    calculatedAmount = revenue * rate / 100;
    calculatedField.value = '₦ ' + calculatedAmount.toFixed(2);
    if (calculatedAmount > 0) {
        paystackSection.style.display = 'block';
    } else {
        paystackSection.style.display = 'none';
    }
}

taxTypeSelect.addEventListener('change', updateTaxRate);
calculateBtn.addEventListener('click', calculateTax);

// Paystack popup
paystackBtn.addEventListener('click', function() {
    const handler = PaystackPop.setup({
        key: '<?= getSetting($pdo, 'paystack_public_key') ?: 'pk_test_xxxxxxxxxxxxx' ?>', // Replace with your public key
        email: '<?= htmlspecialchars($user['email']) ?>',
        amount: Math.round(calculatedAmount * 100), // Paystack expects kobo
        currency: 'NGN',
        ref: 'TXN_' + Date.now() + '_' + Math.floor(Math.random() * 1000000), // unique reference
        metadata: {
            custom_fields: [
                {
                    display_name: "Tax Type",
                    variable_name: "tax_type",
                    value: taxTypeSelect.value
                },
                {
                    display_name: "Yearly Revenue",
                    variable_name: "yearly_revenue",
                    value: revenueInput.value
                },
                {
                    display_name: "User ID",
                    variable_name: "user_id",
                    value: '<?= $user['id'] ?>'
                }
            ]
        },
        callback: function(response) {
            // Redirect to verification page with reference
            window.location.href = 'paystack_callback.php?reference=' + response.reference;
        },
        onClose: function() {
            alert('Payment window closed.');
        }
    });
    handler.openIframe();
});
</script>