<?php
class JavaApi {
	public static function postToJava($json){
		$url = 'localhost:4000/java';
		
		$options = array(
				'http' => array(
						'header'  => "Content-type: application/json\r\n",
						'method'  => 'POST',
						'content' => $json
				)
		);
		
		$context = stream_context_create($options);
		$result = file_get_contents($url, false, $context);
		if ($result === FALSE) { return; }
		
		var_dump($result);
	}
}
?>