-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 24-11-2016 a las 02:16:27
-- Versión del servidor: 5.6.24
-- Versión de PHP: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `marajabar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE IF NOT EXISTS `auditoria` (
  `idAuditoria` int(11) NOT NULL,
  `descripcion` varchar(100) CHARACTER SET latin1 NOT NULL,
  `fechahora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fkUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

CREATE TABLE IF NOT EXISTS `cajas` (
  `idCaja` int(11) NOT NULL,
  `fecha` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `fkEstado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE IF NOT EXISTS `compras` (
  `idCompras` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecompras`
--

CREATE TABLE IF NOT EXISTS `detallecompras` (
  `fkMerca` int(11) NOT NULL,
  `fkCompras` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventas`
--

CREATE TABLE IF NOT EXISTS `detalleventas` (
  `fkVenta` int(11) NOT NULL,
  `fkProducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadomesa`
--

CREATE TABLE IF NOT EXISTS `estadomesa` (
  `idEstado` int(11) NOT NULL,
  `descripcion` varchar(15) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadoscaja`
--

CREATE TABLE IF NOT EXISTS `estadoscaja` (
  `idEstadoVenta` int(11) NOT NULL,
  `descripcion` varchar(10) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mercaderia`
--

CREATE TABLE IF NOT EXISTS `mercaderia` (
  `idMerca` int(11) NOT NULL,
  `Descripcion` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fkProveedor` int(11) NOT NULL,
  `costo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesa`
--

CREATE TABLE IF NOT EXISTS `mesa` (
  `idMesa` int(11) NOT NULL,
  `Descripcion` varchar(150) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fkSector` int(11) NOT NULL,
  `fkEstadoMesa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE IF NOT EXISTS `permisos` (
  `idPermiso` int(11) NOT NULL,
  `nombrePermiso` varchar(35) COLLATE utf8_spanish2_ci NOT NULL,
  `Descripcion` varchar(180) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`idPermiso`, `nombrePermiso`, `Descripcion`) VALUES
(1, 'Usuarios', 'MenuItem Usuarios dentro de Opciones.'),
(2, 'UsuarioListTab', 'Pestaña para ver todos los usuarios.'),
(3, 'UsariosModTab', 'Pestaña para modificar usuario.'),
(4, 'UsuarioNuevoTab', 'Pestaña para crear un nuevo usuario.'),
(5, 'UsuarioElim', 'Permiso para eliminar usuario del sistema.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `idProducto` int(11) NOT NULL,
  `nombreProducto` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Descripcion` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `precio` int(11) NOT NULL,
  `fkTipo` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE IF NOT EXISTS `proveedores` (
  `idProveedor` int(11) NOT NULL,
  `nombreProveedor` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `cuit` varchar(15) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `telefono` varchar(25) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `email` varchar(35) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`idProveedor`, `nombreProveedor`, `cuit`, `telefono`, `email`) VALUES
(1, 'Merco Sur', '99-000988767-3', '01154654322', 'elmerco@tetrae.todo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `idRol` int(11) NOT NULL,
  `Descripcion` varchar(20) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idRol`, `Descripcion`) VALUES
(1, 'administrador'),
(2, 'supervisor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rolespermisos`
--

CREATE TABLE IF NOT EXISTS `rolespermisos` (
  `fkRol` int(11) NOT NULL,
  `fkPermiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `rolespermisos`
--

INSERT INTO `rolespermisos` (`fkRol`, `fkPermiso`) VALUES
(1, 1),
(2, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sectores`
--

CREATE TABLE IF NOT EXISTS `sectores` (
  `idSector` int(11) NOT NULL,
  `nombreSector` varchar(15) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sectores`
--

INSERT INTO `sectores` (`idSector`, `nombreSector`) VALUES
(2, 'Salon'),
(3, 'Arriba'),
(4, 'Abajo'),
(5, 'Afuera');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoproducto`
--

CREATE TABLE IF NOT EXISTS `tipoproducto` (
  `idTipoProducto` int(11) NOT NULL,
  `descripcion` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `nombreUsuario` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `clave` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `fkRol` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `nombreUsuario`, `clave`, `fkRol`) VALUES
(1, 'mbenditti', 'matias', 1),
(2, 'jimhoff', 'juan', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE IF NOT EXISTS `ventas` (
  `idVenta` int(11) NOT NULL,
  `fkMesa` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `total` int(11) NOT NULL,
  `fkEstado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`idAuditoria`),
  ADD KEY `fkUsuario` (`fkUsuario`);

--
-- Indices de la tabla `cajas`
--
ALTER TABLE `cajas`
  ADD PRIMARY KEY (`idCaja`),
  ADD KEY `fkEstado` (`fkEstado`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`idCompras`);

--
-- Indices de la tabla `detallecompras`
--
ALTER TABLE `detallecompras`
  ADD PRIMARY KEY (`fkMerca`,`fkCompras`),
  ADD KEY `fkCompras` (`fkCompras`);

--
-- Indices de la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  ADD PRIMARY KEY (`fkVenta`,`fkProducto`),
  ADD KEY `fkProducto` (`fkProducto`);

--
-- Indices de la tabla `estadomesa`
--
ALTER TABLE `estadomesa`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indices de la tabla `estadoscaja`
--
ALTER TABLE `estadoscaja`
  ADD PRIMARY KEY (`idEstadoVenta`);

--
-- Indices de la tabla `mercaderia`
--
ALTER TABLE `mercaderia`
  ADD PRIMARY KEY (`idMerca`),
  ADD KEY `fkProveedor` (`fkProveedor`);

--
-- Indices de la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`idMesa`),
  ADD KEY `fkSalon` (`fkSector`),
  ADD KEY `fkEstadoMesa` (`fkEstadoMesa`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`idPermiso`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idProducto`),
  ADD KEY `fkTipo` (`fkTipo`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`idProveedor`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `rolespermisos`
--
ALTER TABLE `rolespermisos`
  ADD PRIMARY KEY (`fkRol`,`fkPermiso`),
  ADD KEY `fkPermiso` (`fkPermiso`);

--
-- Indices de la tabla `sectores`
--
ALTER TABLE `sectores`
  ADD PRIMARY KEY (`idSector`);

--
-- Indices de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  ADD PRIMARY KEY (`idTipoProducto`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `fkRol` (`fkRol`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idVenta`),
  ADD KEY `fkMesa` (`fkMesa`),
  ADD KEY `fkEstado` (`fkEstado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `idAuditoria` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `cajas`
--
ALTER TABLE `cajas`
  MODIFY `idCaja` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `idCompras` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `estadomesa`
--
ALTER TABLE `estadomesa`
  MODIFY `idEstado` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `estadoscaja`
--
ALTER TABLE `estadoscaja`
  MODIFY `idEstadoVenta` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `mercaderia`
--
ALTER TABLE `mercaderia`
  MODIFY `idMerca` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `mesa`
--
ALTER TABLE `mesa`
  MODIFY `idMesa` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `idPermiso` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `idProveedor` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `sectores`
--
ALTER TABLE `sectores`
  MODIFY `idSector` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  MODIFY `idTipoProducto` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idVenta` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cajas`
--
ALTER TABLE `cajas`
  ADD CONSTRAINT `cajas_ibfk_1` FOREIGN KEY (`fkEstado`) REFERENCES `estados_caja` (`idEstadoVenta`);

--
-- Filtros para la tabla `detallecompras`
--
ALTER TABLE `detallecompras`
  ADD CONSTRAINT `detallecompras_ibfk_1` FOREIGN KEY (`fkMerca`) REFERENCES `mercaderia` (`idMerca`),
  ADD CONSTRAINT `detallecompras_ibfk_2` FOREIGN KEY (`fkCompras`) REFERENCES `compras` (`idCompras`);

--
-- Filtros para la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  ADD CONSTRAINT `detalleventas_ibfk_1` FOREIGN KEY (`fkVenta`) REFERENCES `ventas` (`idVenta`),
  ADD CONSTRAINT `detalleventas_ibfk_2` FOREIGN KEY (`fkProducto`) REFERENCES `productos` (`idProducto`);

--
-- Filtros para la tabla `mercaderia`
--
ALTER TABLE `mercaderia`
  ADD CONSTRAINT `mercaderia_ibfk_1` FOREIGN KEY (`fkProveedor`) REFERENCES `proveedores` (`idProveedor`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD CONSTRAINT `mesa_ibfk_1` FOREIGN KEY (`fkSector`) REFERENCES `sectores` (`idSector`),
  ADD CONSTRAINT `mesa_ibfk_2` FOREIGN KEY (`fkEstadoMesa`) REFERENCES `estado_mesa` (`idEstado`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`fkTipo`) REFERENCES `tipo_producto` (`idTipoProducto`);

--
-- Filtros para la tabla `rolespermisos`
--
ALTER TABLE `rolespermisos`
  ADD CONSTRAINT `rolespermisos_ibfk_1` FOREIGN KEY (`fkRol`) REFERENCES `roles` (`idRol`),
  ADD CONSTRAINT `rolespermisos_ibfk_2` FOREIGN KEY (`fkPermiso`) REFERENCES `permisos` (`idPermiso`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`fkRol`) REFERENCES `roles` (`idRol`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `estado_venta` FOREIGN KEY (`fkEstado`) REFERENCES `estadoVenta` (`idEstadoVenta`),
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`fkMesa`) REFERENCES `mesa` (`idMesa`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
