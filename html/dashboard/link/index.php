<?php
require_once ($_SERVER ['DOCUMENT_ROOT'] . '/api/user.php');
?>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword" content="Discord Groups">
<script src="/_include/pace/pace.min.js"></script>
<link href="/_include/pace/themes/flash.css" rel="stylesheet" />

<title>Link - Dashboard - Discord Groups</title>

<!-- Bootstrap core CSS -->
<link href="../assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="../assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="../assets/css/style.css" rel="stylesheet">
<link href="../assets/css/style-responsive.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<?php
	
	if (! isset ( $_COOKIE ['discord_groups'] )) {
		header ( 'Location: https://discordapp.com/oauth2/authorize?client_id=280192562583699458&scope=guilds+identify&response_type=code' );
	} else {
		$tolken = $_COOKIE ['discord_groups'];
		$result = UserApi::getDiscord ( $tolken );
		if ($result ['status'] == "rows") {
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
		} else if ($result ['status'] == "sucess") {
			?>
      <!-- ***********************************************
      MAIN CONTENT
      **************************************************** -->
	
	<div id="login-page">
		<div class="container">

			<form class="form-login" action="../mojang" method="post">
				<h2 class="form-login-heading">Link Account</h2>

				<div class="login-social-link centered">
					<p>
						<strong>Discord Groups Dashboard</strong>
					</p>
					<a class="btn btn-theme" href="/dashboard/"><em
						class="fa fa-dashboard"></em> HOME</a> <a class="btn btn-theme04"><em
						class="fa fa-cogs"></em> LOG OUT</a>
				</div>
				<hr>

				<div class="login-social-link centered">
					<p>Link your Minecraft account with Discord!</p>
					<p>Your password is not stored.</p>
				</div>

				<div class="login-wrap">
						<input autocomplete="on" type="email" class="form-control"
							placeholder="Minecraft Email" name="email" value="" autofocus>
							<br> 
							<input
							type="password" class="form-control"
							placeholder="Minecraft Password" name="pass" value=""> 
							<br>
						<button class="btn btn-theme btn-block" type="submit">
							<i class="fa fa-lock"></i> SIGN IN
						</button>

					<div class="registration">
						<br> Forgot Minecraft Password? <br /> <a class=""
							href="https://account.mojang.com/password"> Reset Minecraft
							Password </a>
					</div>

				</div>

				<hr>

				<div class="login-social-link centered">
					<p>Created by weeryan17 & CodeCo</p>
					<hr>
				</div>
			</form>

		</div>
	</div>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="../assets/js/jquery.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>

	<!--BACKSTRETCH-->
	<!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
	<script type="text/javascript"
		src="../assets/js/jquery.backstretch.min.js"></script>
	<script>
        $.backstretch("../assets/img/login-bg.jpg", {speed: 500});
    </script>

<?php
		} else {
			?>
			<div id="login-page">
		<div class="container">

			<form class="form-login">
				<h2 class="form-login-heading">Error</h2>
				<div class="login-social-link centered">
					<p>There was an error signing you in.</p>
					<br>
					<p>Please try again later.</p>
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
		}
	}
	?>

  </body>
</html>
