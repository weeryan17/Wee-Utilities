<?php
require_once ($_SERVER ['DOCUMENT_ROOT'] . '/util/database.php');

$servername = $database ['server'];
$username = $database ['user'];
$password = $database ['pass'];
$dbname = $database ['database'];

$conn = new mysqli ( $servername, $username, $password, $dbname );

$tolken = $_COOKIE['discord_groups'];

echo("Cookie stuffs: " . print_r($_COOKIE, true) . "<br>");


$tolk = '"'. $tolken . '"';

$sql = "SELECT * FROM tolkens_table WHERE tolken = '$tolken' LIMIT 1";

echo("tolken before: " . $tolken . "<br>");

echo("tolken after: " . $tolk . "<br>");

if ($conn->connect_error) {
	echo ("connection failed: " . $conn->connect_error);
}

$result = $conn->query ( $sql );

if ($result->num_rows > 0) {
	$output = $result->fetch_assoc();
	
	echo("Result found: " . print_r($output, true). "<br>");
	
	echo ("id: " . $output['id'] . "<br>");
	
	$id = $output['id'];
	$avatar = $output['avatar'];
	
	echo("avatar: " . $avatar . "<br>");
	
	echo("<img src='https://cdn.discordapp.com/avatars/$id/$avatar.png'>");
	
} else {
	$array = array (
			"status" => "rows",
			"result" => "no rows found" 
	);
	echo ("no rows found<br>");
	echo("Result gotten: " . print_r($result, true) . "<br>");
	echo("stuff gotten: " . print_r($result->fetch_field_direct(1), true));
	
}

$conn->close ();
?>