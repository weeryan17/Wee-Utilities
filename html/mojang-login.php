<html>
<head>

</head>
<body>
<?php
require ("api/user.php");
if (! isset ( $_COOKIE ["Discord_Groups"] )) {
	?>

<h1>You need to be logged in to add mojang accout!</h1>

<?php
} else {
	$tolken = $_COOKIE ["Discord_Groups"];
	if (UserApi::getDiscord ( $tolken ) == false) {
		?>
<h1>Invalid login!</h1>
<?php
	} else {
		?>

<h1>Mojang login</h1>
	<form action="/add-mojang.php" method="post">
		Email:<br> <input type="text" name="email" value=""><br> Password:<br>
		<input type="password" name="pass" value=""><br> <input type="submit"
			value="Login">
	</form>
	<?php
	}
}
?>
</body>
</html>