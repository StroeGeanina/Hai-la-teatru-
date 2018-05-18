<?php
    $servername = "localhost";
	$username = "costinso_ip";
	$password = "QC7?(rG5;rZ{";
	$dbname = "costinso_ip";
	
	//conectare
	$conn = new mysqli($servername, $username, $password, $dbname);
	
	//verificare conectare
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error); //AICI O SA PUNEM UN JSON PENTRU TRATAREA ERORII IN ANDROID!!!
	} 
?>