<?php
header ( 'Content-Type: text/html' );
//Man the flarebot devs are awsome
$servername = "";
$username = "";
$password = "";
$dbname = "";

if (! class_exists ( "DiscordAuth" )) {
	define ( "DISCORDAUTH_CLIENTID", "" );
	define ( "DISCORDAUTH_CLIENTSECRET", "" );
	define ( "DISCORDAUTH_OAUTH2_REDIRECTURI", "" );
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

$sql = "INSERT INTO tolkens_table". "(tolken, userData, guildData, guildDataString)". "VALUES ('$test', '$data', '$guildData', '$guildDataString')";

if ($conn->query ( $sql ) === TRUE) {
	echo "Data inserted successfully";
} else {
	echo "Error creating table: " . $conn->error;
}

$conn->close ();
?>