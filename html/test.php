<?php
require ("api/mojang-api.class.php");

$user = MojangAPI::authenticate("", "");
if($user == null){
	echo ("Inavid user");
} else {
	?>
	<body>
	<img src="https://minotar.net/cube/<?php echo ($user['name'])?>.png" alt="<?php echo ($user['name'])?>" />
	<br>
	<?php
	echo("Name: " . $user['name']);
	?>
	<br>
	<?php echo ("ID: " . $user['id'])?>
	</body>
	<?php
}
?>