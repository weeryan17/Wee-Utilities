<?php
require ($_SERVER['DOCUMENT_ROOT'].'/util/database.php');
define("SERVER_NAME", $database['name']);
define("USER_NAME", $database['user']);
define("PASSWORD", $database['pass']);
define("DATABASE", $database['database']);

class UserApi {

	public static function getDiscord($tolken) {
		$conn = new mysqli ( SERVER_NAME, USER_NAME, PASSWORD, DATABASE );
		
		$sql = "SELECT * FROM auth_tokens WHERE token = '$tolken' LIMIT 1";
		
		$result = $conn->querrty($sql);
		if ($result->num_rows > 0) {
			// output data of each row
			$row = $result->fetch_assoc();
			return $row["id"];
		} else {
			return false;
		}
	}
}