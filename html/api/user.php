<?php
require_once ($_SERVER['DOCUMENT_ROOT'].'/util/database.php');
define("SERVER_NAME", $database['server']);
define("USER_NAME", $database['user']);
define("PASSWORD", $database['pass']);
define("DATABASE", $database['database']);

class UserApi {
	/**
	 * Get's the saved discord data from MySQL
	 * 
	 * @param String $tolken The discord tolken gotten from the cookies.
	 * @return string[] an array containing the data.
	 */
	public static function getDiscord(String $tolken) {
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
	
	/**
	 * Get's mojang stuff from the persons discord id.
	 * 
	 * @param String $discordId Their discord id.
	 * @return string[] The result.
	 */
	public static function getMinecraft(String $discordId){
		$conn = new mysqli ( SERVER_NAME, USER_NAME, PASSWORD, DATABASE );
		
		$array;
		
		if ($conn->connect_error) {
			$array = array(
					"status"=>"conection",
					"result"=>"connection failed"
			);
		}
		
		$sql = "SELECT * FROM mojang_table WHERE id = '$discordId' LIMIT 1;";
		
		$result = $conn->query($sql);
		
		if ($result->num_rows > 0) {
			$output = $result->fetch_assoc();
			
			$array = array(
					"status"=>"sucess",
					"result"=>array(
							"tolken"=>$output['mojangTolken'],
							"uuid"=>$output['mojangId']
					)
			);
		} else {
			$array = array(
					"status"=>"rows",
					"result"=>"no rows found"
			);
		}
		
		return $array;
	}
}