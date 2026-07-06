<?php
include("../config/db.php");

$id = $_GET['id'];
$type = $_GET['type'];

if ($type == "individual") {
    $table = "individuals";
} else {
    $table = "companies";
}

$result = $conn->query("SELECT * FROM $table WHERE id=$id");
$user = $result->fetch_assoc();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    if ($type == "individual") {
        $fullname = $_POST['fullname'];
        $conn->query("UPDATE individuals SET fullname='$fullname' WHERE id=$id");
    } else {
        $company_name = $_POST['company_name'];
        $conn->query("UPDATE companies SET company_name='$company_name' WHERE id=$id");
    }

    header("Location: dashboard.php");
}
?>

<form method="POST">
<?php if ($type == "individual") { ?>
    <input type="text" name="fullname" value="<?= $user['fullname'] ?>">
<?php } else { ?>
    <input type="text" name="company_name" value="<?= $user['company_name'] ?>">
<?php } ?>

<button type="submit">Update</button>
</form>