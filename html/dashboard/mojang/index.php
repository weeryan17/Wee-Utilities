<?php
require_once ($_SERVER ['DOCUMENT_ROOT'] . "/api/mojang-api.class.php");
require_once ($_SERVER ['DOCUMENT_ROOT'] . "/api/user.php");
echo($_POST . "<br>");
$user = MojangAPI::authenticate ( $_POST ["email"], $_POST ["pass"] );
echo ("Email: " . htmlspecialchars ( $_POST ["email"] ) . "<br>");
echo ("Pass: " . htmlspecialchars ( $_POST ["pass"] ) . "<br>");
?>
<head>

</head>
<body>
<?php
if (! isset ( $_COOKIE ["discord_groups"] )) {
	?>
<h1>You need to be logged in to add mojang accout!</h1>
<?php
} else {
	$tolken = $_COOKIE ["discord_groups"];
	$userResult = UserApi::getDiscord ( $tolken );
	if ($userResult ['status'] != 'sucess') {
		echo ("invalid!");
	} else {
		$discordId = $userResult ['result'] ['id'];
		$mojangTolken = $user ['tolken'];
		$id = $user ['id'];
		$sql = "INSERT INTO mojang_table" . "(id, mojangTolken, mojangId)" . "VALUES ('$discordId', '$mojangTolken', '$id')";
		?>
	<img src="https://minotar.net/cube/<?php echo ($user['name'])?>.png"
		alt="<?php echo ($user['name'])?>" />
	<br>
	<?php
		echo ("Name: " . $user ['name']);
		?>
	<br>
	<?php echo ("ID: " . $user['id'])?>
	
<?php
	}
}
?>
</body>