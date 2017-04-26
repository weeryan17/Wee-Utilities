<?php
require_once ($_SERVER['DOCUMENT_ROOT'].'/api/java-api.php');
$arr = array(
		'test' => 'test1',
		'test2' => 'test3'
);
echo JavaApi::postToJava(json_encode($arr));
?>