<?php
require("api/mojang-api.class.php");
$user = MojangAPI::authenticate($_POST ["email"], $_POST ["pass"]);
echo ("Email: " . htmlspecialchars ( $_POST ["email"] ) . "<br>");
echo ("Pass: " . htmlspecialchars ( $_POST ["pass"] ) . "<br>");
?>

<body>
	<img src="https://minotar.net/cube/<?php echo ($user['name'])?>.png"
		alt="<?php echo ($user['name'])?>" />
	<br>
	<?php
	echo ("Name: " . $user ['name']);
	?>
	<br>
	<?php echo ("ID: " . $user['id'])?>
</body>