<?php
include("../config/db.php");

// Individual payments
$ind = $conn->query("SELECT fullname AS name, amount, reference FROM individual_payments");

// Company payments
$comp = $conn->query("SELECT company_name AS name, amount, reference FROM company_payments");
?>

<link rel="stylesheet" href="table.css">

<h2>All Payments</h2>
<div class="table-container">
<table border="1">
<tr>
    <th>Name</th>
    <th>Amount</th>
    <th>Reference</th>
</tr>

<?php while($row = $ind->fetch_assoc()) { ?>
<tr>
    <td><?= $row['name'] ?></td>
    <td><?= $row['amount'] ?></td>
    <td><?= $row['reference'] ?></td>
</tr>
<?php } ?>

<?php while($row = $comp->fetch_assoc()) { ?>
<tr>
    <td><?= $row['name'] ?></td>
    <td><?= $row['amount'] ?></td>
    <td><?= $row['reference'] ?></td>
</tr>
<?php } ?>

</table>