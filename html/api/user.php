<?php
require ($_SERVER['DOCUMENT_ROOT'].'/util/database.php');
define("SERVER_NAME", $database['name']);
define("USER_NAME", $database['user']);
define("PASSWORD", $database['pass']);
define("DATABASE", $database['database']);

class UserApi {

	public static function getDiscord($tolk) {
		$conn = new mysqli ( SERVER_NAME, USER_NAME, PASSWORD, DATABASE );
		
		$tolken = '"'.$tolk.'"';
		
		$sql = "SELECT * FROM tolkens_table WHERE tolken = '$tolken' LIMIT 1;";
		
		if ($conn->query ( $sql ) === TRUE) {
			$result = $conn->query($sql);
			
			if ($result->num_rows > 0) {
				// output data of each row
				$row = $result->fetch_assoc();
				
				$stmt->close();
				$conn->close();
				
				$array = array(
						"status"=>"sucess",
						"result"=>$row['id']
				);
				
				return $array;
			} else {
				$array = array(
						"status"=>"rows",
						"result"=>"no rows found"
				);
				return $array;
			}
		} else {
			$array = array(
					"status"=>"operation",
					"result"=>$conn->error
			);
			return $array;
		}
		
	}
}