<?php
	//conectare la baza de date
    $servername = "localhost";
	$username = "costinso_ip";
	$password = "QC7?(rG5;rZ{";
	$dbname = "costinso_ip";

	try {
			$conn = new PDO("mysql:host=$servername;dbname=$dbname;charset=utf8", $username, $password);
			$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		}
	catch(PDOException $e)
		{
			die("OOPs something went wrong");
		}
	//post variablkes
	//$numeTeatru = $_POST['postTeatru'];
    //$numeSpectacol = $_POST['postSpectacol'];
	//$dataSpectacol = $_POST['postData'];
	
	$numeTeatru = "Excelsior";
    $numeSpectacol = "iHamlet";
	$dataSpectacol = "2017-04-08 14:28:40";
	
	//query
	$sql = 	"SELECT PozeSpectacol.locatiepoza PathPoza, Spectacol.descriere DescriereSpectacol, Spectacol.trailer Trailer
			FROM `Spectacol` 	JOIN SeTine ON Spectacol.id = SeTine.id_spectacol
											JOIN Teatru ON SeTine.id_teatru = Teatru.id
											JOIN PozeSpectacol ON Spectacol.id = PozeSpectacol.id_spectacol
			WHERE Teatru.nume = :teatru AND Spectacol.nume = :spectacol AND SeTine.data = :data;
			";

			
		$stmt = $conn->prepare($sql);
        $stmt->bindParam(':teatru', $numeTeatru, PDO::PARAM_STR);
        $stmt->bindParam(':spectacol', $numeSpectacol, PDO::PARAM_STR);
		$stmt->bindParam(':data', $dataSpectacol, PDO::PARAM_STR);
        $stmt->execute();
	
	//rulare query
    //$result = $conn->query($sql);
	
	$date =array();
	if ($stmt->rowCount()) {
		//echo "AM INTRAAT";
		$result = $stmt->fetchAll();
		//print_r($result);
		echo json_encode($result);
	} 
	else {
		echo json_encode('0');
	}
	$conn->close();
	
?>