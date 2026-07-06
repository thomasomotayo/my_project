<?php
include("../config/db.php");

// Validate input
if (!isset($_GET['id']) || !isset($_GET['type'])) {
    die("Invalid request");
}

$id   = $_GET['id'];
$type = $_GET['type'];

// Fetch based on type
if ($type === 'individual') {

    $stmt = $conn->prepare("SELECT * FROM individual_payments WHERE id = ?");
    
} elseif ($type === 'company') {

    $stmt = $conn->prepare("SELECT * FROM company_payments WHERE id = ?");
    
} else {
    die("Invalid payment type");
}

$stmt->bind_param("i", $id);
$stmt->execute();
$result = $stmt->get_result();
$payment = $result->fetch_assoc();

if (!$payment) {
    die("Invoice not found");
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Invoice</title>
    <style>
        body { font-family: Arial; padding: 20px; }
        .invoice-box { max-width: 600px; margin: auto; border: 1px solid #ddd; padding: 20px; }
        h2 { text-align: center; }
    </style>
</head>

<body>

<div class="invoice-box">

    <h2>Payment Invoice</h2>

    <p><strong>Reference:</strong> <?php echo $payment['reference']; ?></p>
    <p><strong>Amount Paid:</strong> ₦<?php echo number_format($payment['amount']); ?></p>

    <?php if ($type === 'individual') { ?>

        <p><strong>Name:</strong> <?php echo $payment['fullname']; ?></p>
        <p><strong>NIN:</strong> <?php echo $payment['nin']; ?></p>

    <?php } else { ?>

        <p><strong>Company Name:</strong> <?php echo $payment['company_name']; ?></p>
        <p><strong>CAC Number:</strong> <?php echo $payment['cac_number']; ?></p>

    <?php } ?>

    <p><strong>Status:</strong> Success</p>

    <br>
    <button onclick="window.print()">Print Invoice</button>

</div>

</body>
</html>