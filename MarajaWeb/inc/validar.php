<?php
if($_SESSION["logeado"] != "SI"){
  header ("Location: index.php");
  exit();
}

?>
