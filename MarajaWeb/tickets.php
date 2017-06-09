<?php
include('inc/config.php');
include('inc/validar.php');

$id_usuario =  $_SESSION["id_usuario"];
// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_set_charset($link,'utf8');
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
$query = mysqli_query($link," SELECT id_ticket,ht.fecha,SUBSTRING(CAST(hora AS CHAR),11,24) AS hora,nombre,observacion, resolucion
                              FROM tickets t
                              JOIN historial_tickets ht ON ht.fk_ticket = t.id_ticket
                              JOIN estados e ON ht.fk_estado = e.id_estado
                              WHERE t.creador = $id_usuario
                              AND ht.fk_estado IN (5)
                              AND ht.id_historial IN (SELECT MAX(id_historial) FROM historial_tickets WHERE fk_ticket = t.id_ticket)
                              ORDER by id_ticket DESC") or die(mysql_error());


?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="refresh" content="60" />
	<link rel="icon" type="image/png" href="images/icons/ticket.png" sizes="16x16">
    <title>Tickets Resueltos</title>

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
	  		<h4 class="text-center bg-info">Listado Tickets Resueltos</h4>
        <div class="row">
              <table id="example" class="display" cellspacing="0" width="100%">
                	<thead>
                    	<th width="5%"> Numero de ticket </th>
                      <th width="10%"> Fecha </th>
            					<th width="10%"> Hora </th>
                      <th width="70%"> Observacion </th>
                  </thead>
                    <tbody>
                    	<?php while($tickets = mysqli_fetch_array($query)){ ?>
                        <tr class="success">
                            <td> <?php echo $tickets['id_ticket']; ?> </td>
                            <td> <?php echo $tickets['fecha']; ?> </td>
                            <td> <?php echo $tickets['hora']; ?> </td>
                            <td> <?php echo $tickets['observacion']; ?> </td>
                        </tr>
                        <?php } ?>
                    </tbody>
				</table>

</div>
      </div>
      <div class="panel-footer">
			<p class="text-center">Direccion de Sistemas - Municipalidad de Bariloche</p>
		</div>
    </div> <!-- /container -->
  </body>
</html>
