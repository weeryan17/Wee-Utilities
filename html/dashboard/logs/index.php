<?php
require_once ($_SERVER ['DOCUMENT_ROOT'] . '/api/user.php');
?>
<head>
<link rel="stylesheet" href="logs.css">
<link href="calender.css" type="text/css" rel="stylesheet" />
<link href="clock.css" type="text/css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script type="text/javascript" src="clock.js"></script>
</head>
<body>
<?php
$hasdir = isset ( $_GET ['dir'] );
$hasfile = isset ( $_GET ['file'] );
$logFolder="/media/Bots/Discord-Groups/Logs/";
if (!isset($_COOKIE["discord_groups"])) { ?>
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
	<?php 
	include 'calender.php';
	
	$calender = new Calendar();
	echo($calender->show());
	
	if ($hasdir) {
		$dir = $_GET ['dir'];
		$stringDir = "'".$dir."'";
		?>
	<h1>TIME</h1>
	<div class="center">
		<div class="clock">
		<input id="up" type="image" name="up" alt="up" src="up.png" onClick="up(<?php echo($stringDir); ?>);"/>
      	<div class="hours"></div>
      	<input id="down" type="image" name="down" alt="down" src="down.png" onClick="down(<?php echo($stringDir); ?>);"/>
		</div>
      	<div class="go"></div>
    </div>
	<div class="scrollbar" id="style-1"
		style="overflow-y: auto; height: 100px; padding: 2px;">
		<h2>current time stamps:</h2>
		<?php
		$directorys = scandir ( $logFolder . $dir );
		foreach ( $directorys as $directory ) {
			if ($directory != ".." && $directory != ".") {
				echo ($directory . "<br>");
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
		$file = $_GET ['file'];
		
			$file = fopen ( $logFolder . $dir . "/" . $file, "r" );
			while ( ! feof ( $file ) ) {
				echo (fgets ( $file ) . "<br>");
			}
			?>
	</div>
	<?php
		}
	}
	?>
	<h1>Mycs stuffs!</h1>
	<?php 
	$bytestotal = 0;
	$path = realpath($logFolder);
	if($path!==false && $path!='' && file_exists($path)){
		foreach(new RecursiveIteratorIterator(new RecursiveDirectoryIterator($path, FilesystemIterator::SKIP_DOTS)) as $object){
			$bytestotal += $object->getSize();
		}
	}
	
	echo("Current log directory size: " . $bytestotal . " bytes");
	
	?>
	<?php
		} else { ?>
			<h1>You are not a developer!</h1>
		<?php }
	}
}
?>
</body>