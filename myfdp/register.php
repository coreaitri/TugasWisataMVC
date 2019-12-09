<?php
    
    $server		= "localhost"; 
	$user		= "root"; 
	$password	= ""; 
	$database	= "panorama";
	
	$con = mysqli_connect($server, $user, $password, $database);
	if (mysqli_connect_errno()) {
		echo "Gagal terhubung MySQL: " . mysqli_connect_error();
	}

	class usr{}

    $username = $_POST["username"];
    $email = $_POST["email"];
	$password = $_POST["password"];
	

    if ((empty($username))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom username tidak boleh kosong";
		die(json_encode($response));
	}else if ((empty($email))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom email tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($password))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom password tidak boleh kosong";
		die(json_encode($response));
	
	} else {
		if (!empty($username) && !empty($email) && !empty($password)){
			$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM users WHERE email='".$email."'"));

			if ($num_rows == 0){
				$query = mysqli_query($con, "INSERT INTO users (id, username, email, password) VALUES(0,'".$username."','".$email."','".$password."')");

				if ($query){
					$response = new usr();
					$response->success = 1;
					$response->message = "Register berhasil, silahkan Sign Up!.";
					die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
					$response->message = "email sudah ada";
					die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "email sudah ada";
				die(json_encode($response));
			}
		}
	}

	mysqli_close($con);

?>	