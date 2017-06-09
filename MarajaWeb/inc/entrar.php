<?php
// Configura los datos de tu cuenta
include('config.php');

if(isset($_POST['nombre_usuario']) && isset($_POST['contrasenia'])){
// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
$db = mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');


if ($_POST['nombre_usuario']) {
	//Comprobacion del envio del nombre de usuario y contrasenia
	$nombre_usuario=htmlentities($_POST['nombre_usuario']);
	$contrasenia=md5($_POST['contrasenia']);
	if ($contrasenia==NULL) {
		header ("Location: ../index.php?nopass");
		exit();
	}else{
		$query = mysqli_query($link,"SELECT nombreUsuario,clave FROM usuarios WHERE nombreUsuario = '$nombre_usuario'") or die(mysql_error());
		$data = mysqli_fetch_array($query);

		if($data['clave'] != $contrasenia) {

			//echo "No a introducido una contrasenia correcta";
			header ("Location: ../index.php?errorpass");
			exit();
		}else{
		$query = mysqli_query($link,"SELECT idUsuario,nombreUsuario,clave,fkRol FROM usuarios WHERE nombreUsuario = '$nombre_usuario'") or die(mysql_error());
		$row = mysqli_fetch_array($query);
		$nombre_usuario2 = $row['nombreUsuario'];
		$_SESSION["s_nombre_usuario"] = $row['nombreUsuario'];

		$_SESSION["logeado"] = "SI";
		$_SESSION["permiso"] = $row['fkRol'];
		$_SESSION["id_usuario"] = $row['idUsuario'];

			header ("Location: ../inicio.php");
			exit();
			}
	}
}
}else{
	header("Location: ../index.php?errorpass");
	exit();
}
?>
