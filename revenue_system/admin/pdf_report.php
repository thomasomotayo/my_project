<?php
require __DIR__ . '/../vendor/autoload.php';
use Dompdf\Dompdf;

include("../config/db.php");

$dompdf = new Dompdf();

$html = "<h2>Payment Report</h2>";

$result = $conn->query("SELECT * FROM individual_payments");

while($row = $result->fetch_assoc()){
    $html .= "<p>{$row['fullname']} - ₦{$row['amount']}</p>";
}

$dompdf->loadHtml($html);
$dompdf->render();
$dompdf->stream("report.pdf");
?>