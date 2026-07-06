<?php
session_start();
include("../config/db.php");
include("../tax/tax_calculator.php");

// Check session
if (!isset($_SESSION['user_id'])) {
    header("Location: ../auth/company_login.php");
    exit();
}

$user_id = $_SESSION['user_id'];
$type = $_SESSION['type'];

// ✅ FETCH USER FIRST (VERY IMPORTANT)
if ($type == "company") {
    $result = $conn->query("SELECT * FROM companies WHERE id=$user_id");
} else {
    $result = $conn->query("SELECT * FROM individuals WHERE id=$user_id");
}

// Convert to array
$user = $result->fetch_assoc();

// ✅ CHECK IF USER EXISTS
if (!$user) {
    echo "User not found";
    exit();
}

// Get income
$income = $user['annual_income'];

// ✅ VALIDATION
if (!$income || $income <= 0) {
    echo "<p>Please update your annual income first.</p>";
    exit();
}

// Calculate tax
if ($type == "company") {
    $tax = calculateCompanyTax($income);
} else {
    $tax = calculateIndividualTax($income);
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Pay Tax</title>
    <link rel="stylesheet" href="../assets/css/dashboard.css">
</head>

<body>

<div class="main">

    <h2>Annual Income: ₦<?php echo number_format($income); ?></h2>
    <h2>Tax Payable: ₦<?php echo number_format($tax); ?></h2>

    <script src="https://js.paystack.co/v1/inline.js"></script>

    <button onclick="payWithPaystack()">Pay Now</button>

</div>

<script>
function payWithPaystack(){
    var handler = PaystackPop.setup({
        key: 'pk_test_7d1d01424f889542e452b6e164b85c2328f908bd',
        email: '<?php echo $user['email']; ?>',
        amount: <?php echo $tax * 100; ?>,
        currency: "NGN",
        callback: function(response){
            window.location = "payment_success.php?ref=" + response.reference;
        }
    });
    handler.openIframe();
}
</script>

</body>
</html>