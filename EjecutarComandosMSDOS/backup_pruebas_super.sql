-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: supermercado
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cif` char(9) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Pepe','Perez','12345678Q','1945-01-01'),(2,'Juan','Juanes','87654321A','2021-01-26'),(3,'Anita','Rodriguez Perez','X1234567Z',NULL),(4,'Javier','Lete','A1234567X','1970-01-12'),(6,'Laika','Gomez','12345678Q','2001-11-11'),(9,'Jaime AAA',NULL,'X1234567A','2002-12-01'),(10,'Administrador','Lopez','12345678Q',NULL),(11,'Harry','Potter','X1234567A',NULL),(12,'Potter','Harry','12345678Q',NULL),(13,'Yoo mismo',NULL,'A12345678',NULL),(14,'Prueba','Holaaaa','X1234567Q','2020-01-01'),(18,'Laika','Gomez','X1234567A',NULL),(19,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(20,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(21,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(22,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(23,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(24,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(25,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(26,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(27,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-25'),(28,'Nuevo','Nuevez Novisimez','13243545Z','2021-01-26'),(29,'JAIME','Rodriguez Perez','12345678Q','2020-01-01'),(30,'Laika','Gomez','A12345678','2021-03-08'),(31,'Laika','Gomez','A12345678','2021-03-08'),(32,'JAIME','Rodriguez Perez','12345678Q','2021-02-01'),(33,'Jaime','Lopez','X1234567A','2021-03-08'),(34,'administrador','Gomez','A12345678','2021-03-01'),(35,'administrador','Gomez','A12345678','2021-03-01'),(36,'administrador','Gomez','X1234567A','2021-03-01'),(37,'Laika999','bbb','X1234567A','2021-03-01'),(38,'JAIME','Potter','12345678Q','2021-03-01'),(39,'JAIME','Potter','X1234567A','2021-03-01'),(40,'JAIME','Potter','A12345678','2021-03-01'),(41,'administrador','Gomez','12345678Q','2021-03-01'),(42,'Laika','Gomez','12345678Q','2021-03-01');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `clientes_AFTER_INSERT` AFTER INSERT ON `clientes` FOR EACH ROW BEGIN
INSERT INTO historicos (tabla, mensaje) VALUES ('clientes', CONCAT('Nuevo Cliente: ', NEW.nombre, ' ', NEW.apellidos));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `clientes_BEFORE_DELETE` BEFORE DELETE ON `clientes` FOR EACH ROW BEGIN
INSERT INTO historicos (tabla, mensaje) VALUES ('clientes', CONCAT('Borrado Cliente: ', OLD.nombre, ' ', OLD.apellidos));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'Verduras',NULL),(2,'Lácteos',NULL),(3,'Congelados',NULL),(4,'Frutas',NULL),(5,'Secos',NULL),(6,'Electronica',NULL),(7,'Pepe','El Departamento de PEPE'),(9,'Prueba','Texto'),(13,'Dpto 12',NULL),(14,'Pepe','Descripción del Departamento PEPE'),(15,'Pepepepe','Descripción del Departamento PEPE'),(16,'Pepe',NULL);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo` char(8) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha` date NOT NULL,
  `clientes_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  KEY `fk_facturas_clientes1_idx` (`clientes_id`),
  CONSTRAINT `fk_facturas_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (1,'1','2020-12-16',1),(2,'2','2021-03-09',29),(6,'20210002','2021-03-10',33),(8,'20210003','2021-03-10',35),(9,'20210004','2021-03-10',36),(10,'20210005','2021-03-10',40),(11,'20210006','2021-03-10',41),(12,'20210007','2021-03-11',42);
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `facturas_completas`
--

DROP TABLE IF EXISTS `facturas_completas`;
/*!50001 DROP VIEW IF EXISTS `facturas_completas`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `facturas_completas` AS SELECT 
 1 AS `id_cliente`,
 1 AS `nombre_cliente`,
 1 AS `apellidos`,
 1 AS `cif`,
 1 AS `fecha_nacimiento`,
 1 AS `id_factura`,
 1 AS `codigo`,
 1 AS `fecha`,
 1 AS `cantidad`,
 1 AS `id_producto`,
 1 AS `nombre_producto`,
 1 AS `descripcion`,
 1 AS `url_imagen`,
 1 AS `precio`,
 1 AS `descuento`,
 1 AS `unidad_medida`,
 1 AS `precio_unidad_medida`,
 1 AS `stock`,
 1 AS `departamentos_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `facturas_has_productos`
--

DROP TABLE IF EXISTS `facturas_has_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas_has_productos` (
  `facturas_id` bigint NOT NULL,
  `productos_id` bigint NOT NULL,
  `cantidad` int DEFAULT '1',
  PRIMARY KEY (`facturas_id`,`productos_id`),
  KEY `fk_facturas_has_productos_productos1_idx` (`productos_id`),
  KEY `fk_facturas_has_productos_facturas1_idx` (`facturas_id`),
  CONSTRAINT `fk_facturas_has_productos_facturas1` FOREIGN KEY (`facturas_id`) REFERENCES `facturas` (`id`),
  CONSTRAINT `fk_facturas_has_productos_productos1` FOREIGN KEY (`productos_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas_has_productos`
--

LOCK TABLES `facturas_has_productos` WRITE;
/*!40000 ALTER TABLE `facturas_has_productos` DISABLE KEYS */;
INSERT INTO `facturas_has_productos` VALUES (1,1,1),(1,2,5),(2,1,1),(2,2,1),(2,3,1),(2,4,1),(6,1,2),(8,1,2),(8,2,1),(8,3,13),(9,1,2),(9,2,1),(9,3,14),(10,2,1),(11,1,1),(11,2,2),(11,3,2),(11,4,1),(12,4,1);
/*!40000 ALTER TABLE `facturas_has_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historicos`
--

DROP TABLE IF EXISTS `historicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historicos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tabla` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `mensaje` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historicos`
--

LOCK TABLES `historicos` WRITE;
/*!40000 ALTER TABLE `historicos` DISABLE KEYS */;
INSERT INTO `historicos` VALUES (1,'clientes','Nuevo ClienteHolaaaa Pruebaaaa','2021-01-13 10:04:11'),(2,'clientes','Nuevo Cliente: Holaaaa Pruebaaaa','2021-01-13 10:04:41'),(3,'clientes','Nuevo Cliente: Laika Gomez','2021-01-13 11:56:41'),(4,'clientes','Borrado Cliente: Holaaaa Pruebaaaa','2021-01-13 12:14:59'),(5,'clientes','Borrado Cliente: Holaaaa Pruebaaaa','2021-01-13 12:15:22'),(6,'clientes','Borrado Cliente: Holaaaa Pruebaaaa','2021-01-15 13:24:18'),(7,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 09:45:38'),(8,'clientes','Borrado Cliente: Los pilares de la tierra Gomez','2021-01-25 09:45:39'),(9,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 09:51:42'),(10,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 11:01:27'),(11,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 11:03:12'),(12,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 13:30:08'),(13,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 13:30:36'),(14,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 13:32:15'),(15,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 13:35:43'),(16,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-25 13:42:56'),(17,'clientes','Nuevo Cliente: Nuevo Nuevez Novisimez','2021-01-26 09:09:03'),(18,'clientes','Nuevo Cliente: JAIME Rodriguez Perez','2021-03-09 13:17:49'),(19,'clientes','Nuevo Cliente: Laika Gomez','2021-03-10 12:24:55'),(20,'clientes','Nuevo Cliente: Laika Gomez','2021-03-10 12:26:13'),(21,'clientes','Nuevo Cliente: JAIME Rodriguez Perez','2021-03-10 12:26:51'),(22,'clientes','Nuevo Cliente: Jaime Lopez','2021-03-10 12:35:26'),(23,'clientes','Nuevo Cliente: administrador Gomez','2021-03-10 13:35:17'),(24,'clientes','Nuevo Cliente: administrador Gomez','2021-03-10 13:38:31'),(25,'clientes','Nuevo Cliente: administrador Gomez','2021-03-10 13:39:24'),(26,'clientes','Nuevo Cliente: Laika999 bbb','2021-03-10 22:20:38'),(27,'clientes','Nuevo Cliente: JAIME Potter','2021-03-10 22:21:59'),(28,'clientes','Nuevo Cliente: JAIME Potter','2021-03-10 22:23:06'),(29,'clientes','Nuevo Cliente: JAIME Potter','2021-03-10 22:24:07'),(30,'clientes','Nuevo Cliente: administrador Gomez','2021-03-10 22:24:58'),(31,'clientes','Nuevo Cliente: Laika Gomez','2021-03-11 11:53:46');
/*!40000 ALTER TABLE `historicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `importe` decimal(12,2) NOT NULL,
  `clientes_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movimientos_clientes1_idx` (`clientes_id`),
  CONSTRAINT `fk_movimientos_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (1,10000.00,1),(2,5000.00,1),(3,4000.00,2),(4,200.00,2),(5,8000.00,3),(6,-100.00,4),(7,-100.00,3),(8,100.00,4),(9,-100.00,3);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_general_ci,
  `url_imagen` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precio` decimal(20,2) NOT NULL,
  `descuento` int DEFAULT NULL,
  `unidad_medida` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precio_unidad_medida` decimal(20,2) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `departamentos_id` int NOT NULL,
  `activo` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_productos_departamentos1_idx` (`departamentos_id`),
  CONSTRAINT `fk_productos_departamentos1` FOREIGN KEY (`departamentos_id`) REFERENCES `departamentos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Scottex','Papel Higiénico 24 rollos','1.jpg',12345.12,0,'Kilo',1.85,100,5,1),(2,'Activia','Activia Muesli pack 4 yogures','2.jpg',123.45,5,'Metro',0.00,9999,7,1),(3,'Dodot Pañales','Pañales extra absorción','3.jpg',1000.00,0,'Coche',1000.00,9999,6,1),(4,'Dixan Total','Detergente líquido 24 lavados','4.jpg',9.99,9,'Kg',0.99,999,7,1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `productos_departamentos`
--

DROP TABLE IF EXISTS `productos_departamentos`;
/*!50001 DROP VIEW IF EXISTS `productos_departamentos`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `productos_departamentos` AS SELECT 
 1 AS `idproducto`,
 1 AS `nombre_producto`,
 1 AS `descripcion_producto`,
 1 AS `url_imagen`,
 1 AS `precio`,
 1 AS `descuento`,
 1 AS `unidad_medida`,
 1 AS `cantidad`,
 1 AS `id_departamento`,
 1 AS `nombre_departamento`,
 1 AS `descripcion_departamento`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `clientes_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuarios_clientes_idx` (`clientes_id`),
  CONSTRAINT `fk_usuarios_clientes` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'jaime@quintana.com','password',NULL),(2,'pepe@perez.com','pepe',1),(3,'javier@lete','javier',NULL),(4,'nuevo3@nuevez.com','nuevo3',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `facturas_completas`
--

/*!50001 DROP VIEW IF EXISTS `facturas_completas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `facturas_completas` AS select `c`.`id` AS `id_cliente`,`c`.`nombre` AS `nombre_cliente`,`c`.`apellidos` AS `apellidos`,`c`.`cif` AS `cif`,`c`.`fecha_nacimiento` AS `fecha_nacimiento`,`f`.`id` AS `id_factura`,`f`.`codigo` AS `codigo`,`f`.`fecha` AS `fecha`,`fp`.`cantidad` AS `cantidad`,`p`.`id` AS `id_producto`,`p`.`nombre` AS `nombre_producto`,`p`.`descripcion` AS `descripcion`,`p`.`url_imagen` AS `url_imagen`,`p`.`precio` AS `precio`,`p`.`descuento` AS `descuento`,`p`.`unidad_medida` AS `unidad_medida`,`p`.`precio_unidad_medida` AS `precio_unidad_medida`,`p`.`cantidad` AS `stock`,`p`.`departamentos_id` AS `departamentos_id` from (((`clientes` `c` join `facturas` `f` on((`c`.`id` = `f`.`clientes_id`))) join `facturas_has_productos` `fp` on((`f`.`id` = `fp`.`facturas_id`))) join `productos` `p` on((`fp`.`productos_id` = `p`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `productos_departamentos`
--

/*!50001 DROP VIEW IF EXISTS `productos_departamentos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `productos_departamentos` AS select `p`.`id` AS `idproducto`,`p`.`nombre` AS `nombre_producto`,`p`.`descripcion` AS `descripcion_producto`,`p`.`url_imagen` AS `url_imagen`,`p`.`precio` AS `precio`,`p`.`descuento` AS `descuento`,`p`.`unidad_medida` AS `unidad_medida`,`p`.`cantidad` AS `cantidad`,`d`.`id` AS `id_departamento`,`d`.`nombre` AS `nombre_departamento`,`d`.`descripcion` AS `descripcion_departamento` from (`productos` `p` join `departamentos` `d` on((`p`.`departamentos_id` = `d`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-11 16:47:04
