<?php
require_once ($_SERVER['DOCUMENT_ROOT'].'/util/database.php');
define("SERVER_NAME", $database['name']);
define("USER_NAME", $database['user']);
define("PASSWORD", $database['pass']);
define("DATABASE", $database['database']);

class UserApi {

	public static function getDiscord($tolken) {
		$conn = new mysqli ( SERVER_NAME, USER_NAME, PASSWORD, DATABASE );
		
		$array;
		
		if ($conn->connect_error) {
			$array = array(
					"status"=>"conection",
					"result"=>"connection failed"
			);
		}
		
		$sql = "SELECT * FROM tolkens_table WHERE tolken = '$tolken' LIMIT 1;";
		
		$result = $conn->query($sql);
			
		if ($result->num_rows > 0) {
			$output = $result->fetch_assoc();
			
			$array = array(
					"status"=>"sucess",
					"result"=>array(
							"id"=>$output['id'],
							"avatar"=>$output['avatar']
					)
			);
			
		} else {
			$array = array(
					"status"=>"rows",
					"result"=>"no rows found"
			);
		}
		$conn->close();
		return($array);
	}
		
}