<?php
define("SERVER_NAME", "");
define("USER_NAME", "");
define("PASSWORD", "");
define("DATABASE", "");

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