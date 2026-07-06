<?php
include("../config/db.php");

$result = $conn->query("SELECT * FROM individuals");
?>
<link rel="stylesheet" href="table.css">
<h2>Individuals</h2>

<div class="table-container">
<table border="1">
<tr>
    <th>ID</th>
    <th>Full Name</th>
    <th>NIN</th>
    <th>Action</th>
</tr>

<?php while($row = $result->fetch_assoc()) { ?>
<tr>
    <td><?= $row['id'] ?></td>
    <td><?= $row['fullname'] ?></td>
    <td><?= $row['nin'] ?></td>
    <td>
        <a href="edit_user.php?id=<?= $row['id'] ?>&type=individual">Edit</a>
        <a href="delete_user.php?id=<?= $row['id'] ?>&type=individual">Delete</a>
    </td>
</tr>
<?php } ?>

</table>