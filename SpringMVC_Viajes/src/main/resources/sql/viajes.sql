-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-02-2018 a las 12:33:18
-- Versión del servidor: 10.1.29-MariaDB
-- Versión de PHP: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `viajes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `idcompra` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `preciototal` double DEFAULT NULL,
  `direccioncomprador` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `mailcomprador` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `nombrecomprador` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `telefonocomprador` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fechacompra` date DEFAULT NULL,
  `numcompra` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `nombre` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `pass` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `role` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`nombre`, `active`, `pass`, `role`) VALUES
('admin', b'1', '123456', 'MANAGER'),
('empleado', b'1', '123456', 'EMPLOYEE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos`
--

CREATE TABLE `destinos` (
  `iddestino` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `fecha` date DEFAULT NULL,
  `imagen` longblob,
  `nombre` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `descripcion` varchar(1500) COLLATE latin1_spanish_ci DEFAULT NULL,
  `descripcioncorta` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `numplazas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `destinos`
--

INSERT INTO `destinos` (`iddestino`, `fecha`, `imagen`, `nombre`, `precio`, `descripcion`, `descripcioncorta`, `numplazas`) VALUES
('DES001', '2018-02-24', NULL, 'Viaje al polo', 159.99, 'afsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsafafsdafsaf', 'Pingüinos mas Frio', 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `idreserva` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `preciototal` double DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `numplazas` int(11) DEFAULT NULL,
  `idcompra` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `iddestino` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`idcompra`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `destinos`
--
ALTER TABLE `destinos`
  ADD PRIMARY KEY (`iddestino`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`idreserva`),
  ADD KEY `idcompra` (`idcompra`,`iddestino`),
  ADD KEY `iddestino` (`iddestino`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`iddestino`) REFERENCES `destinos` (`iddestino`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`idcompra`) REFERENCES `compras` (`idcompra`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
