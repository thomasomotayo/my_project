

<?php
session_start();
include("../config/db.php");

if (!isset($_GET['ref'])) {
    die("No reference");
}

$ref = $_GET['ref'];

$user_id = $_SESSION['user_id'];


$stmt = $conn->prepare("SELECT fullname, nin FROM individuals WHERE id = ?");
$stmt->bind_param("i", $user_id);
$stmt->execute();
$result = $stmt->get_result();
$user = $result->fetch_assoc();


// Verify Paystack
$curl = curl_init();

curl_setopt_array($curl, [
    CURLOPT_URL => "https://api.paystack.co/transaction/verify/" . rawurlencode($ref),
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_HTTPHEADER => [
        "Authorization: Bearer sk_test_f8e754c1ccdbd4116546bb0235bb428988578c2c"
    ],
]);

$response = curl_exec($curl);

if (curl_errno($curl)) {
    die("Curl error: " . curl_error($curl));
}

curl_close($curl);

$result = json_decode($response, true);

if (!isset($result['data'])) {
    echo "<pre>";
    print_r($result);
    echo "</pre>";
    exit;
}

if ($result['data']['status'] == 'success') {

    $amount = $result['data']['amount'] / 100;
   

    // ✅ Get session values
    $user_id   = $_SESSION['user_id'];
    $fullname = $_SESSION['fullname'];
    //$nin = $_SESSION['nin'];
    $nin = $user['nin'];



    $status = "success";

    // ✅ INSERT with user_type FIRST
    $stmt = $conn->prepare("
        INSERT INTO individual_payments (user_id, fullname, nin, amount, reference)
        VALUES (?, ?, ?, ?, ?)
    ");

    if (!$stmt) {
        die("Prepare failed: " . $conn->error);
    }

    // s = user_type, i = user_id, s = reference, d = amount, s = status
    $stmt->bind_param("issds",  $user_id, $fullname, $nin, $amount, $ref);

    if ($stmt->execute()) {
        echo "✅ Payment saved successfully!";
        header("Location: ../user/invoice.php?id=".$stmt->insert_id."&type=individual");
    } else {
        echo "Error: " . $stmt->error;
    }

    $stmt->close();

} else {
    echo "Payment failed";
}
?>