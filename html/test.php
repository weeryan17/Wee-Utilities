<?php
require_once ($_SERVER['DOCUMENT_ROOT'].'/api/java-api.php');
$arr = array(
		'test' => 'test1',
		'trst2' => 'test3'
);
JavaApi::postToJava(json_encode($arr));
?>