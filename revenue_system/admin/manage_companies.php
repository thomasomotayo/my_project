<?php
include("../config/db.php");

$result = $conn->query("SELECT * FROM companies");
?>
<link rel="stylesheet" href="table.css">
<h2>Companies</h2>
<div class="table-container">
<table border="1">
<tr>
    <th>ID</th>
    <th>Company Name</th>
    <th>CAC</th>
    <th>Action</th>
</tr>

<?php while($row = $result->fetch_assoc()) { ?>
<tr>
    <td><?= $row['id'] ?></td>
    <td><?= $row['company_name'] ?></td>
    <td><?= $row['cac_number'] ?></td>
    <td>
        <a href="edit_user.php?id=<?= $row['id'] ?>&type=company">Edit</a>
        <a href="delete_user.php?id=<?= $row['id'] ?>&type=company">Delete</a>
    </td>
</tr>
<?php } ?>

</table>