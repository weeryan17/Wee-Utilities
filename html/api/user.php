<?php
require ($_SERVER['DOCUMENT_ROOT'].'/util/database.php');
define("SERVER_NAME", $database['name']);
define("USER_NAME", $database['user']);
define("PASSWORD", $database['pass']);
define("DATABASE", $database['database']);

class UserApi {

	public static function getDiscord($tolk) {
		$conn = new mysqli ( SERVER_NAME, USER_NAME, PASSWORD, DATABASE );
		
		$sql = "SELECT * FROM tolkens_table WHERE token = ? LIMIT 1";
		
		$stmt = $conn->prepare($sql);
		$stmt->bind_param("s", $tolken);
		
		$tolken = $tolk;
		$stmt->execute();
		
		if ($result->num_rows > 0) {
			// output data of each row
			$row = $result->fetch_assoc();
			return $row['id'];
		} else {
			return false;
		}
	}
}