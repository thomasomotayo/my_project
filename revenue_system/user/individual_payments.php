<?php
session_start();
include("../config/db.php");
include("../tax/tax_calculator.php");

// Check session
if (!isset($_SESSION['user_id']) || $_SESSION['type'] !== 'individual') {
    header("Location: ../auth/individual_login.php");
    exit();
}

$user_id = $_SESSION['user_id'];

// Fetch individual
$result = $conn->query("SELECT * FROM individuals WHERE id=$user_id");
$user = $result->fetch_assoc();

if (!$user) {
    echo "User not found";
    exit();
}

// Income
$income = $user['annual_income'];

if (!$income || $income <= 0) {
    echo "<p>Please update your annual income first.</p>";
    exit();
}

// Tax calculation
$tax = calculateIndividualTax($income);
?>

<!DOCTYPE html>
<html>
<head>
    <title>Individual Tax Payment</title>
</head>

<body>

<h2>Annual Income: ₦<?php echo number_format($income); ?></h2>
<h2>Tax Payable: ₦<?php echo number_format($tax); ?></h2>

<script src="https://js.paystack.co/v1/inline.js"></script>

<button onclick="payWithPaystack()">Pay Now</button>

<script>
function payWithPaystack(){
    var handler = PaystackPop.setup({
        key: 'pk_test_7d1d01424f889542e452b6e164b85c2328f908bd',
        email: '<?php echo $user['email']; ?>',
        amount: <?php echo $tax * 100; ?>,
        currency: "NGN",

        ref: 'IND_' + Math.floor(Math.random() * 1000000000),

        callback: function(response){
            window.location = "indi_payment_success.php?ref=" + response.reference;
        }
    });

    handler.openIframe();
}
</script>

</body>
</html>