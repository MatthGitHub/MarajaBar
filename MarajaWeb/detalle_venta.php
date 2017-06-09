
<?php
//--------------------------------Inicio de sesion------------------------

include('inc/config.php');
include('inc/validar.php');


//--------------------------------Fin inicio de sesion------------------------

//Parametros - var - librerias

//Capturo dni encontrado
$idVenta = $_GET['idVenta'];

//#########################################BUSCO TICKET################################
	$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
	mysqli_set_charset($link,'utf8');
	mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');

	$sql = "SELECT p.nombreProducto,cantidad FROM detalleventas dv JOIN productos p ON dv.fkProducto = p.idProducto
					WHERE fkVenta = $idVenta";
	$stmt = mysqli_query($link,$sql);


?>

<!DOCTYPE html">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="../images/icons/logo_vet.png" sizes="16x16">
    <title>Detalle venta</title>

		<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
		<link href="css/jquery.dataTables.min.css" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script language='javascript' src="js/jquery-1.12.3.js"></script>
    <script language='javascript' src="js/jquery.dataTables.min.js"></script>

	<script type="text/javascript">
	$(document).ready(function() {
		$('#example').DataTable( {
		"language": {
					"lengthMenu": "Mostrar _MENU_ registros por pagina",
					"zeroRecords": "No se encontraron registros",
					"info": "Pagina _PAGE_ de _PAGES_",
					"infoEmpty": "No hay registros",
					"infoFiltered": "(filtrado de _MAX_ registros)",
					"sSearch":       	"Buscar",
					"oPaginate": {
						"sFirst":    	"Primero",
						"sPrevious": 	"Anterior",
						"sNext":     	"Siguiente",
						"sLast":     	"Ultimo"
					}
			},
			"scrollY":        "500px",
			"scrollCollapse": true,
			"order":[[0,"desc"]]
				} );
	} );
</script>

</head>


<body >

	<div class="container">
		<br>
			<?php include("inc/menu.php"); ?>

			<div class="jumbotron">
				<h4 class="text-center bg-info">Ventas</h4>
					<div class="row">
						<table id="example" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th> Producto </th>
								<th> Cantidad </th>
							</tr>
						</thead>
							<tbody>
								<?php while($ventas = mysqli_fetch_array($stmt)){ ?>
								<tr>
									<td> <?php echo $ventas['nombreProducto']; ?> </td>
									<td> <?php echo $ventas['cantidad']; ?> </td>
								</tr>
								<?php } ?>
							</tbody>
						</table>
						<form method="post" action="ventas.php" >
							<div class="row">
								<div class="col-md-8 col-md-offset-2">
									<div class="panel panel-default">
										<div class="panel-body">
											<input id="cerrar" name="cerrar" type="submit"  class="btn btn-sm btn-danger btn-block" value="Cerrar" />
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
			</div><!-- /jumbotron -->
	</div> <!-- Container -->
</body>
</html>
