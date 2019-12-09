<?php

define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'panorama');
	
	//connecting to database and getting the connection object
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	//Checking if any error occured while connecting
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	//creating a query
	$stmt = $conn->prepare("SELECT id, panorama, city, deskripsi,lokasi, image, latitude, longitude FROM galery;");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id, $panorama, $city, $deskripsi, $lokasi, $image, $latitude, $longitude);
	
	$galery = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id'] = $id; 
		$temp['panorama'] = $panorama; 
		$temp['city'] = $city; 
		$temp['deskripsi'] = $deskripsi; 
		$temp['lokasi'] = $lokasi; 
		$temp['image'] = $image; 
		$temp['latitude'] = $latitude; 
		$temp['longitude'] = $longitude; 

		array_push($galery, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($galery);