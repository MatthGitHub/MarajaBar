<?php
include('inc/config.php');
include('inc/validar.php');

// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_set_charset($link,'utf8');
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
$query = mysqli_query($link,"SELECT v.idVenta,m.idMesa,v.fecha,ev.descripcion,u.nombreUsuario,v.total
                              FROM ventas v JOIN mesa m ON v.fkMesa = m.idMesa
                              JOIN estadosventa ev ON ev.idEstadoVenta = v.fkEstado
                              JOIN usuarios u ON v.fkUsuario = u.idUsuario") or die(mysql_error());
?>

<!DOCTYPE html>
<html lang="en">
  <head >
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="refresh" content="60" />
	<link rel="icon" type="image/png" href="images/icons/ticket.png" sizes="16x16">
    <title>Ventas</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
		<link href="css/jquery.dataTables.min.css" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script language='javascript' src="js/jquery-1.12.3.js"></script>
    <script language='javascript' src="js/jquery.dataTables.min.js"></script>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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

<body>

<div class="container">
	<br>
	<!-- Static navbar -->
	<?php include('inc/menu.php'); ?>
	<!-- Main component for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h4 class="text-center bg-info">Ventas</h4>
			<div class="row">
			  <table id="example" class="display" cellspacing="0" width="100%">
				<thead>
				  <tr>
						<th> Numero venta </th>
						<th> Numero mesa </th>
						<th> Fecha </th>
						<th> Estado </th>
						<th> Usuario </th>
						<th> Total </th>
            <th> Historial venta </th>
				  </tr>
				</thead>
					<tbody>
						<?php while($ventas = mysqli_fetch_array($query)){ ?>
						<tr>
							<td> <?php echo $ventas['idVenta']; ?> </td>
							<td> <?php echo $ventas['idMesa']; ?> </td>
							<td> <?php echo $ventas['fecha']; ?> </td>
							<td> <?php echo $ventas['descripcion']; ?> </td>
							<td> <?php echo $ventas['nombreUsuario'];?></td>
              <td> <?php echo $ventas['total'];?></td>
              <td> <button type="submit" id="idVenta" name="idVenta" class="btn btn-sm btn-primary"  onclick="location.href='detalle_venta.php?idVenta=<?php echo $ventas['idVenta']; ?>';"><i class="fa fa-address-book-o fa-fw"></i></button> </td>
						</tr>
						<?php } ?>
					</tbody>
				</table>

			</div>


	</div><!-- /jumbotron -->
	<div class="panel-footer">
			<p class="text-center">Direccion de Sistemas - Municipalidad de Bariloche</p>
	</div>
</div> <!-- /container -->

</body>
</html>
