-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 08-06-2017 a las 01:26:33
-- Versión del servidor: 5.6.35
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `marajabar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE `auditoria` (
  `idAuditoria` int(11) NOT NULL,
  `descripcion` varchar(100) CHARACTER SET latin1 NOT NULL,
  `fechahora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fkUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

CREATE TABLE `cajas` (
  `idCaja` int(11) NOT NULL,
  `fecha` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `fkEstado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `idCompras` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecompras`
--

CREATE TABLE `detallecompras` (
  `fkMerca` int(11) NOT NULL,
  `fkCompras` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventas`
--

CREATE TABLE `detalleventas` (
  `fkVenta` int(11) NOT NULL,
  `fkProducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `detalleventas`
--

INSERT INTO `detalleventas` (`fkVenta`, `fkProducto`, `cantidad`) VALUES
(1, 4, 1),
(1, 9, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosventa`
--

CREATE TABLE `estadosventa` (
  `idEstadoVenta` int(11) NOT NULL,
  `descripcion` varchar(10) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `estadosventa`
--

INSERT INTO `estadosventa` (`idEstadoVenta`, `descripcion`) VALUES
(1, 'cerrada'),
(2, 'abierta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesa`
--

CREATE TABLE `mesa` (
  `idMesa` int(11) NOT NULL,
  `Descripcion` varchar(150) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fkSector` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `mesa`
--

INSERT INTO `mesa` (`idMesa`, `Descripcion`, `fkSector`) VALUES
(1, 'Esquina de la ventana', 3),
(2, 'Esta ahi', 2),
(100, '', 99);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `idPermiso` int(11) NOT NULL,
  `nombrePermiso` varchar(35) COLLATE utf8_spanish2_ci NOT NULL,
  `Descripcion` varchar(180) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`idPermiso`, `nombrePermiso`, `Descripcion`) VALUES
(6, 'Estadisticas', 'Boton estadisticas dentro de la barra de menu'),
(7, 'EstadisticasVentas', 'Boton ventas dentro del menu estadísticas'),
(8, 'Estadisticas compras', 'Boton compras dentro del menu estadisticas'),
(9, 'Administracion', 'Boton administración de la barra de menu'),
(10, 'Productos', 'Boton productos dentro del menu administracion'),
(11, 'ProductosListar', 'Boton listar dentro del menu productos'),
(12, 'ProductosNuevo', 'Boton nuevo dentro del menu productos'),
(13, 'ProductoEliminar', 'Boton eliminar dentro del view de productos'),
(14, 'Proveedores', 'Boton proevedores dentro del menu administracion'),
(15, 'PrveedorNuevo', 'Boton nuevo dentro del view de proveedores'),
(16, 'ProveedorEliminar', 'Boton eliminar dentro del view de proveedores'),
(17, 'Mesas', 'Boton mesas dentro del menu administracion'),
(18, 'MesaNueva', 'Boton nueva dentro del view de mesas'),
(19, 'MesaEliminar', 'Boton eliminar dentro del view de mesas'),
(20, 'Configuracion', 'Boton configuración dentro de la barra de menu'),
(21, 'Usuarios', 'Boton usuarios dentro del menu configuracion'),
(22, 'UsuarioNuevo', 'Boton nuevo dentro del view usuarios'),
(23, 'UsuarioEliminar', 'Boton eliminar dentro del view de usuarios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idProducto` int(11) NOT NULL,
  `nombreProducto` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Descripcion` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `precio` int(11) NOT NULL,
  `fkTipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `nombreProducto`, `Descripcion`, `precio`, `fkTipo`) VALUES
(1, 'Jarra cerveza', '1Lts cerveza rubia,roja o negra artesanal', 100, 1),
(2, 'Pizza Maraja', 'Muzzarella, Panceta y Huevo Frito', 200, 1),
(3, 'Pizza margarita', 'Muzzarella', 150, 1),
(4, 'Hamburgesa maraja', 'Queso, panceta, huevo frito + guarnicion', 170, 1),
(5, 'Jarra Fernet', '1Lts fernet', 120, 1),
(6, 'Jarra gancia', '1Lts de gancia', 100, 1),
(7, 'Milanesa napolitana', 'MIlanga de carne a la napolitana', 180, 1),
(8, 'SUper pancho maraja', 'Pancho con de todo', 150, 1),
(9, 'El panchito', 'Pancho ', 100, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `idProveedor` int(11) NOT NULL,
  `nombreProveedor` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `cuit` varchar(15) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `telefono` varchar(25) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `email` varchar(35) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`idProveedor`, `nombreProveedor`, `cuit`, `telefono`, `email`) VALUES
(3, 'Proveedor1', '30-158789645-1', '2944655052', 'proveedoruno@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `idRol` int(11) NOT NULL,
  `Descripcion` varchar(20) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idRol`, `Descripcion`) VALUES
(1, 'administrador'),
(2, 'supervisor'),
(3, 'cajero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rolespermisos`
--

CREATE TABLE `rolespermisos` (
  `fkRol` int(11) NOT NULL,
  `fkPermiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sectores`
--

CREATE TABLE `sectores` (
  `idSector` int(11) NOT NULL,
  `nombreSector` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sectores`
--

INSERT INTO `sectores` (`idSector`, `nombreSector`) VALUES
(2, 'Salon'),
(3, 'Arriba'),
(4, 'Abajo'),
(5, 'Afuera'),
(99, 'Movil');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoproducto`
--

CREATE TABLE `tipoproducto` (
  `idTipoProducto` int(11) NOT NULL,
  `descripcion` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipoproducto`
--

INSERT INTO `tipoproducto` (`idTipoProducto`, `descripcion`) VALUES
(1, 'Producto'),
(2, 'Mercaderia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `nombreUsuario` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `clave` varchar(32) COLLATE utf8_spanish2_ci NOT NULL,
  `fkRol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `nombreUsuario`, `clave`, `fkRol`) VALUES
(1, 'matias', '090c36e3bb39377468363197afb3e91b', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idVenta` int(11) NOT NULL,
  `fkMesa` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `total` int(11) NOT NULL,
  `fkEstado` int(11) NOT NULL,
  `fkUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `fkMesa`, `fecha`, `total`, `fkEstado`, `fkUsuario`) VALUES
(1, 1, '2017-06-07', 570, 1, 1);

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
-- Indices de la tabla `estadosventa`
--
ALTER TABLE `estadosventa`
  ADD PRIMARY KEY (`idEstadoVenta`);

--
-- Indices de la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`idMesa`),
  ADD KEY `fkSalon` (`fkSector`);

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
  ADD KEY `fkEstado` (`fkEstado`),
  ADD KEY `fkUsuario` (`fkUsuario`);

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
-- AUTO_INCREMENT de la tabla `estadosventa`
--
ALTER TABLE `estadosventa`
  MODIFY `idEstadoVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `mesa`
--
ALTER TABLE `mesa`
  MODIFY `idMesa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;
--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `idPermiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `idProveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `sectores`
--
ALTER TABLE `sectores`
  MODIFY `idSector` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
--
-- AUTO_INCREMENT de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  MODIFY `idTipoProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallecompras`
--
ALTER TABLE `detallecompras`
  ADD CONSTRAINT `rfkCompra` FOREIGN KEY (`fkCompras`) REFERENCES `compras` (`idCompras`),
  ADD CONSTRAINT `rfkMerca` FOREIGN KEY (`fkMerca`) REFERENCES `productos` (`idProducto`);

--
-- Filtros para la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  ADD CONSTRAINT `rfkProducto` FOREIGN KEY (`fkProducto`) REFERENCES `productos` (`idProducto`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `rfkVenta` FOREIGN KEY (`fkVenta`) REFERENCES `ventas` (`idVenta`);

--
-- Filtros para la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD CONSTRAINT `rfkSector` FOREIGN KEY (`fkSector`) REFERENCES `sectores` (`idSector`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `rfkTipoProducto` FOREIGN KEY (`fkTipo`) REFERENCES `tipoproducto` (`idTipoProducto`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `rolespermisos`
--
ALTER TABLE `rolespermisos`
  ADD CONSTRAINT `rfkPermiso` FOREIGN KEY (`fkPermiso`) REFERENCES `permisos` (`idPermiso`),
  ADD CONSTRAINT `rfkRol` FOREIGN KEY (`fkRol`) REFERENCES `roles` (`idRol`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `rfkUsuarioRol` FOREIGN KEY (`fkRol`) REFERENCES `roles` (`idRol`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `rfkEstadoVenta` FOREIGN KEY (`fkEstado`) REFERENCES `estadosventa` (`idEstadoVenta`),
  ADD CONSTRAINT `rfkMesa` FOREIGN KEY (`fkMesa`) REFERENCES `mesa` (`idMesa`),
  ADD CONSTRAINT `rfkUsuario` FOREIGN KEY (`fkUsuario`) REFERENCES `usuarios` (`idUsuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
