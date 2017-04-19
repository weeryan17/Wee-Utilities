<?php
require_once ($_SERVER ['DOCUMENT_ROOT'] . '/api/user.php');
?>
<head>
<link rel="stylesheet" href="logs.css">
</head>
<body>
<?php
$hasdir = isset ( $_GET ['dir'] );
$hasfile = isset ( $_GET ['file'] );
?>
<?php if (!isset($_COOKIE["discord_groups"])) { ?>
<h1>Not Loged in!</h1>
<?php
} else {
	$tolken = $_COOKIE ["discord_groups"];
	$array = UserApi::getDiscord ( $tolken );
	if (strcmp ( $array ['status'], "rows" ) == 0) {
		?>
	<h1>Invalid Login!</h1>
      			<?php
	} else {
		$devIds = array (
				"215644829969809421",
				"207629082257653760" 
		);
		$id = $array ["result"] ["id"];
		if (in_array ( $id, $devIds )) {
			?>
	<h1>DATE</h1>
	<div class="scrollbar" id="style-1"
		style="overflow-y: auto; height: 100px; padding: 2px;">
		<?php
		$logFolder = "/media/Bots/Discord-Groups/Logs/";
		$directorys = scandir ( $logFolder );
		foreach ( $directorys as $directory ) {
			if ($directory != ".." && $directory != ".") {
				echo ("<a href='?dir=" . $directory . "' >" . $directory . "<a><br>");
			}
		}
		?>
	</div>
	
	<?php
	if ($hasdir) {
		?>
	
	<h1>TIME</h1>
	<div class="scrollbar" id="style-1"
		style="overflow-y: auto; height: 100px; padding: 2px;">
		<?php
		$dir = $_GET ['dir'];
		$directorys = scandir ( $logFolder . $dir );
		foreach ( $directorys as $directory ) {
			if ($directory != ".." && $directory != ".") {
				echo ("<a href='?dir=" . $dir . "&file=" . $directory . "' >" . $directory . "<a><br>");
			}
		}
		?>
	</div>
	
	<?php
		if ($hasfile) {
			?>
	
	<h1>LOG</h1>
	<div class="scrollbar" id="style-1" style="overflow-y: auto; height: 35%;">
		<?php
			$file = fopen ( $logFolder . $dir . "/" . $_GET ['file'], "r" );
			while ( ! feof ( $file ) ) {
				echo (fgets ( $file ) . "<br>");
			}
			?>
	</div>
	<?php
		}
	}
	?>
	<?php
		} else { ?>
			<h1>You are not a developer!</h1>
		<?php }
	}
}
?>
</body>