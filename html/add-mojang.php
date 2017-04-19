<?php
require("api/mojang-api.class.php");
$user = MojangAPI::authenticate($_POST ["email"], $_POST ["pass"]);
echo ("Email: " . htmlspecialchars ( $_POST ["email"] ) . "<br>");
echo ("Pass: " . htmlspecialchars ( $_POST ["pass"] ) . "<br>");
?>
<head>

</head>
<body>
<?php
if(!isset($_COOKIE["discord_groups"])){
?>
<h1>You need to be logged in to add mojang accout!</h1>
<?php 
} else {
	$mojangTolken = $user['tolken'];
	$id = $user['id'];
	$tolken = $_COOKIE["discord_groups"];
	$sql = "INSERT INTO mojang_table". "(tolken, mojangTolken, mojangId)". "VALUES ('$tolken', '$mojangTolken', '$id')";
?>
	<img src="https://minotar.net/cube/<?php echo ($user['name'])?>.png"
		alt="<?php echo ($user['name'])?>" />
	<br>
	<?php
	echo ("Name: " . $user ['name']);
	?>
	<br>
	<?php echo ("ID: " . $user['id'])?>
	
<?php }?>
</body>