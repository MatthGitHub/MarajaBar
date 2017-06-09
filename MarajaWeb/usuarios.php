<?php
include('inc/config.php');
include('inc/validar.php');

// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
$query = mysqli_query($link,"SELECT id_usuario,nombre_usuario, activo, fk_rol, CONCAT( nombre,  ' ', apellido ) AS empleado FROM usuarios u JOIN empleados e ON u.fk_empleado = e.id_empleado") or die(mysql_error());


?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="images/icons/ticket.png" sizes="16x16">
    <title>Listado Usuarios</title>


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
            "columnDefs": [{ type: 'date-uk', targets: 0 }],
            "order":[[0,"desc"],[1,"desc"]]
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
			<h4 class="text-center bg-info">Listado Usuarios</h4>
		<div class="row">
              <table id ="example" class="display" cellspacing="0" width="100%">
                	<thead>
                    	<th> Nombre Usuario </th>
                      <th> Empleado </th>
                      <th> Activo </th>
                      <th> Permisos </th>
                      <?php if($_SESSION["permiso"] == 1){?> <th> Eliminar </th> <?php }?>
                    </thead>
                    <tbody>
                    	<?php while($usuario = mysqli_fetch_array($query)){ ?>
							<?php if($usuario['fk_rol'] == 1){ ?>
							<tr class="success">
								<td> <?php echo $usuario['nombre_usuario']; ?> </td>
								<td> <?php echo $usuario['empleado']; ?> </td>
								<td> <?php echo $usuario['activo']; ?> </td>
								<td> <?php if($usuario['fk_rol'] == 1){ echo "Administrador";}else{ echo "Normal";} ?> </td>
								 <?php if($_SESSION["permiso"] == 1){?>
								<td>  <a href="eliminar.php?id=<?php echo $usuario['id_usuario'];?>&tipo=usuario " role="button"  class="btn btn-primary btn-danger"> Eliminar </a></td>
								<?php }?>
							</tr>
							<?php }else{ ?>
							<tr class="info">
							  <td> <?php echo $usuario['nombre_usuario']; ?> </td>
							  <td> <?php echo $usuario['empleado']; ?> </td>
								<td> <?php echo $usuario['activo']; ?> </td>
							  <td> <?php if($usuario['fk_rol'] == 1){ echo "Administrador";}else{ echo "Normal";} ?> </td>
							   <?php if($_SESSION["permiso"] == 1){?>
							  <td>  <a href="eliminar.php?id=<?php echo $usuario['id_usuario'];?>&tipo=usuario " role="button"  class="btn btn-primary btn-danger"> Eliminar </a></td>
								<?php }?>
							</tr>
							<?php } ?>
                        <?php } ?>
                    </tbody>
				</table>
		</div>
  </div><!-- /jumbotron -->
      <div class="panel-footer">
			<p class="text-center">Direccion de Sistemas - Municipalidad de Bariloche</p>
		</div>
    </div> <!-- /container -->
</html>
