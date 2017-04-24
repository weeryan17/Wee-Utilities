<?php
require_once ($_SERVER ['DOCUMENT_ROOT'] . '/api/user.php');
?>
<head>
	<script src="/_include/pace/pace.min.js"></script>
	<link href="/_include/pace/themes/flash.css" rel="stylesheet" />
	<link rel="stylesheet" href="logs.css">
	<link href="calender.css" type="text/css" rel="stylesheet" />
	<link href="clock.css" type="text/css" rel="stylesheet" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script type="text/javascript" src="clock.js"></script>
	<script src="/_include/pace/pace.min.js"></script>
	<link href="/_include/pace/themes/flash.css" rel="stylesheet" />
	<title>Discord Groups | Logs</title>
</head>
<body>
<?php
$hasdir = isset ( $_GET ['dir'] );
$hasfile = isset ( $_GET ['file'] );
$logFolder="/media/Bots/Discord-Groups/Logs/";
if (!isset($_COOKIE["discord_groups"])) { ?>
<div id="login-page">
          <div class="container">
          
              <form class="form-login">
                <h2 class="form-login-heading">Not Loged In</h2>
                <div class="login-social-link centered">
                    <p>You are not logged in.</p>
                    <br>
                    <p>Please log in.</p>
                    <hr>
                    
                    <div class="login-social-link centered">
                      <p>Created by weeryan17 & CodeCo</p>
                      <hr>
                    </div>
        
                </div>        
              </form>          
          
          </div>
      </div>
<?php
} else {
	$tolken = $_COOKIE ["discord_groups"];
	$array = UserApi::getDiscord ( $tolken );
	if (strcmp ( $array ['status'], "rows" ) == 0) {
		?>
	<div id="login-page">
          <div class="container">
          
              <form class="form-login">
                <h2 class="form-login-heading">Invalid</h2>
                <div class="login-social-link centered">
                    <p>You have an invalid login.</p>
                    <br>
                    <p>Please re log in.</p>
                    <hr>
                    
                    <div class="login-social-link centered">
                      <p>Created by weeryan17 &amp; CodeCo</p>
                      <hr>
                    </div>
        
                </div>        
              </form>          
          
          </div>
      </div>
      			<?php
	} else {
		$devIds = array (
				"215644829969809421",
				"207629082257653760",
				"158310004187725824",
				"84745855399129088",
				"83632458699923456",
				"84766711735136256",
				"134127815531560960",
				"155954930191040513"
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
	if($bytestotal >= 1000000000000){
		$bytestotal = $bytestotal/1000000000000;
		echo("Current log directory size: " . round($bytestotal,3) . " TB"); //I doupt we'll ever get to this point but it's here just in case.
	} else if($bytestotal >= 1000000000){
		$bytestotal = $bytestotal/1000000000;
		echo("Current log directory size: " . round($bytestotal,3). " GB");
	} else if($bytestotal >= 1000000){
		$bytestotal = $bytestotal/1000000;
		echo("Current log directory size: " . round($bytestotal,3). " MB");
	} else if($bytestotal >= 1000){
		$bytestotal = $bytestotal/1000;
		echo("Current log directory size: " . round($bytestotal,3). " KB");
	} else {
		echo("Current log directory size: " . $bytestotal . " bytes");
	}
	
	?>
	<?php
		} else { ?>
			<div id="login-page">
          <div class="container">
          
              <form class="form-login">
                <h2 class="form-login-heading">Unauthorized</h2>
                <div class="login-social-link centered">
                    <p>You are nnot allowed to veiw this content.</p>
                    <br>
                    <p>Please leave this page</p>
                    <hr>
                    
                    <div class="login-social-link centered">
                      <p>Created by weeryan17 & CodeCo</p>
                      <hr>
                    </div>
        
                </div>        
              </form>          
          
          </div>
      </div>
		<?php }
	}
}
?>
</body>