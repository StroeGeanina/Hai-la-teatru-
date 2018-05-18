pentru homepage-viewSpectacole:


	//----
	echo "<br><br> DECODARE:";
	echo "<br> impartire in array: ";
	$arraynew = json_decode($rezultatFinal, true);
	echo $arraynew[1]."  ".$arraynew[2]."  ".$arraynew[3];
	
	echo "<br> impartire array in info: ";
	$datele = json_decode($arraynew[1], true);
	echo $datele[2];