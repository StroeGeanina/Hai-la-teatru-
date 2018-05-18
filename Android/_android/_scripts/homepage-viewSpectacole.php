<?php
	//conectare la baza de date
	require('../databaseConnection.php');
	
	//query
	$sql = "SELECT Spectacol.id IdSpectacol, Spectacol.nume NumeSpectacol, SeTine.data DataSpectacol, Teatru.Nume NumeTeatru, PozeSpectacol.locatiepoza PathPoza, IFNULL (avg(Review.nota),0) NotaSpectacol
			FROM `Spectacol` 	JOIN SeTine ON Spectacol.id = SeTine.id_spectacol
								JOIN Teatru ON SeTine.id_teatru = Teatru.id
								JOIN PozeSpectacol ON Spectacol.id = PozeSpectacol.id_spectacol
								LEFT JOIN Review ON Spectacol.id = Review.id_spectacol
			GROUP BY Spectacol.nume, SeTine.data, Teatru.Nume, PozeSpectacol.locatiepoza
			ORDER BY DataSpectacol DESC";

	//rulare query
    $result = $conn->query($sql);
	
	$date =array();
	if ($result->num_rows > 0) {
		//output pentru fiecare rand returnat
		$i = 1;
		while($row = $result->fetch_assoc()) {
			$newLine = array	(
								'IdSpectacol' => $row["IdSpectacol"], 
								'NumeSpectacol' => $row["NumeSpectacol"], 
								'NumeTeatru' => $row["NumeTeatru"], 
								'DataSpectacol' =>  $row["DataSpectacol"], 
								'NotaSpectacol' => round($row["NotaSpectacol"],2),
								'PathPoza' => $row["PathPoza"] 
								);

			array_push($date, $newLine);
			++$i;
		}
	} 
	else {
		echo json_encode('0');
	}
	$conn->close();
	echo json_encode($date);
?>