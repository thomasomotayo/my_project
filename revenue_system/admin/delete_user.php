<?php
include("../config/db.php");

$id = $_GET['id'];
$type = $_GET['type'];

if ($type == "individual") {
    $conn->query("DELETE FROM individuals WHERE id=$id");
} else {
    $conn->query("DELETE FROM companies WHERE id=$id");
}

header("Location: dashboard.php");
?>