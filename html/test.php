<?php
$to = 'theweeryan17@gmail.com';
$subject = 'Test';
$message = 'Testing stuffs.';
$header = 'From: Test <discordgroups@gmail.com>';

mail($to, $subject, $message, $header);
?>