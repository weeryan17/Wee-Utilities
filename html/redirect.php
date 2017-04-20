<?php
header ( 'Content-Type: text/html' );
require_once ($_SERVER['DOCUMENT_ROOT'].'/util/client.php');
require_once ($_SERVER['DOCUMENT_ROOT'].'/util/database.php');
//Man the flarebot devs are awsome
$servername = $database['server'];
$username = $database['user'];
$password = $database['pass'];
$dbname = $database['database'];

if (! class_exists ( "DiscordAuth" )) {
	define ( "DISCORDAUTH_CLIENTID", $client['id'] );
	define ( "DISCORDAUTH_CLIENTSECRET", $client['secret'] );
	define ( "DISCORDAUTH_OAUTH2_REDIRECTURI", $client['redirect'] );
	class DiscordAuth {
		
		/**
		 * Returns the whole array retrieved from Discord API (refresh token, TTL, etc.)
		 *
		 * @param Code $code        	
		 * @throws UnauthorizedException
		 */
		public static function getAuthorization($code) {
			$fields = "client_id=" . DISCORDAUTH_CLIENTID . "&client_secret=" . DISCORDAUTH_CLIENTSECRET . "&grant_type=authorization_code&redirect_uri=" . DISCORDAUTH_OAUTH2_REDIRECTURI . "&code=" . urlencode ( $code );
			$ch = curl_init ();
			curl_setopt ( $ch, CURLOPT_URL, "https://discordapp.com/api/oauth2/token" );
			curl_setopt ( $ch, CURLOPT_POST, 5 );
			curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, true );
			curl_setopt ( $ch, CURLOPT_POSTFIELDS, $fields );
			$result = curl_exec ( $ch );
			curl_close ( $ch );
			$json = json_decode ( $result, true );
			if (! empty ( $json ['code'] )) {
				throw new UnauthorizedException ( $json ['message'] );
			}
			return $json;
		}
		
		/**
		 * Executes getAuthorization($code) but only returns the Bearer token needed to for the login.
		 *
		 * @param Code $code        	
		 * @throws UnauthorizedException
		 */
		public static function getBearerToken($code) {
			return DiscordAuth::getAuthorization ( $code ) ['access_token'];
		}
		
		/**
		 * Uses the bearer token to grab the user's data.
		 * Implementations of [connections, guilds and guilds.join] can be created by modifying the URL below.
		 *
		 * @param BearerToken $bearer        	
		 */
		public static function getUserDataByBearer($bearer) {
			$ch = curl_init ();
			curl_setopt ( $ch, CURLOPT_URL, "https://discordapp.com/api/users/@me" );
			curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, true );
			curl_setopt ( $ch, CURLOPT_HTTPHEADER, array (
					"Authorization: Bearer " . $bearer 
			) );
			$result = curl_exec ( $ch );
			curl_close ( $ch );
			return json_decode ( $result, true );
		}
		
		/**
		 * Uses the bearer token to grab the user's guilds.
		 *
		 * @param BearerToken $bearer        	
		 */
		public static function getGuildDataByBearer($bearer) {
			$ch = curl_init ();
			curl_setopt ( $ch, CURLOPT_URL, "https://discordapp.com/api/users/@me/guilds" );
			curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, true );
			curl_setopt ( $ch, CURLOPT_HTTPHEADER, array (
					"Authorization: Bearer " . $bearer 
			) );
			$result = curl_exec ( $ch );
			curl_close ( $ch );
			return json_decode ( $result, true );
		}
		
		/**
		 * Uses the auth code to grab the user's data joining together getUserDataByBearer($bearer) and getBearerToken($code)
		 *
		 * @param Code $code        	
		 * @throws UnauthorizedException
		 */
		public static function getUserDataByCode($code) {
			return DiscordAuth::getUserDataByBearer ( DiscordAuth::getBearerToken ( $code ) );
		}
	}
	class UnauthorizedException extends Exception {
	}
}

$conn = new mysqli ( $servername, $username, $password, $dbname );

$bearer = DiscordAuth::getBearerToken ( $_GET ['code'] );

$data = DiscordAuth::getUserDataByBearer ( $bearer );
$guildData = DiscordAuth::getGuildDataByBearer ( $bearer );
$guildDataString = json_encode ( $guildData );
$dataString = json_encode($data);

$guildJson = json_decode($guildDataString);
$dataJson = json_decode($dataString);

echo("Tolken: " . $bearer . "<br>");

$id = "";
$username = "";
foreach ($guildJson as $feild => $value){
	echo("Feild: " . print_r($feild, true) . "<br>");
	echo("Value: " . print_r($value, true) . "<br>");
}

foreach ($dataJson as $feild => $value){
	echo("Feild: " . print_r($feild, true) . "<br>");
	echo("Value: " . print_r($value, true) . "<br>");
	if(strcmp(print_r($feild, true), "id") == 0){
		$id = print_r($value, true);
	} elseif (strcmp(print_r($feild, true), "username") == 0){
		$username = print_r($value, true);
	} elseif (strcmp(print_r($feild, true), "avatar") == 0){
		$avatar = print_r($value, true);
	}
}

echo("User name: " . print_r($username, true) . "<br>");
echo("User id: " . print_r($id, true) . "<br>");

setcookie("discord_groups", $bearer, time() + 31556926, "/");

?>
<body>
<img src="https://cdn.discordapp.com/avatars/<?php echo (print_r($id, true))?>/<?php echo (print_r($avatar, true))?>.png">
</body>
<?php 

//echo(print_r($dataJson, true));

//echo(print_r($guildJson, true));



$sql = "INSERT INTO tolkens_table". "(tolken, id, avatar) VALUES (?, ?, ?)";

$stmt = $conn->prepare($sql);
$stmt->bind_param("sss", $bearer, $id, $avatar);

$stmt->execute();

$stmt->close();
$conn->close ();
?>
<head>
<meta http-equiv="refresh" content="0; url=dashboard" />
</head>