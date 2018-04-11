CREATE DATABASE  IF NOT EXISTS `AMSS_BDD` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `AMSS_BDD`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: AMSS_BDD
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `Domicilio`
--

LOCK TABLES `Domicilio` WRITE;
/*!40000 ALTER TABLE `Domicilio` DISABLE KEYS */;
INSERT INTO `Domicilio` VALUES (1,'Mexico','CDMX','Tlalpan','Ejidos de Huipulco','Transmisiones',222,0,'22222',''),(3,'A','C','C','C','E',1292,2,'ABCD','Dom');
/*!40000 ALTER TABLE `Domicilio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `FitbitUserData`
--

LOCK TABLES `FitbitUserData` WRITE;
/*!40000 ALTER TABLE `FitbitUserData` DISABLE KEYS */;
INSERT INTO `FitbitUserData` VALUES (6,'www.fitbit.com','api.fitbit.com','22CWB4','db0f341198f4914dcb22ce837056292c','http://localhost/fitbit','604800'),(7,'null','null','null','null','null','null'),(8,'null','null','null','null','null','null'),(9,'null','null','null','null','null','null');
/*!40000 ALTER TABLE `FitbitUserData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Paciente`
--

LOCK TABLES `Paciente` WRITE;
/*!40000 ALTER TABLE `Paciente` DISABLE KEYS */;
INSERT INTO `Paciente` VALUES (9,'A','B','Dom','2015-06-01 00:00:00',0,'C','A','A',0,'A+','IMSS','A/B',0);
/*!40000 ALTER TABLE `Paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'Luna','Cullens','2012-12-12 00:00:00','luna@me.com','luna','password','2012-12-30 00:00:00',0),(12,'Maya','Cullens','2033-09-03 00:00:00','maya@test.com','maya','maya','2006-12-06 00:00:00',3),(14,'Donaji','Mancera','2016-04-01 00:00:00','donaji@test.com','donaji','donaji','2016-04-01 00:00:00',1);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ValoracionGerontologica`
--

LOCK TABLES `ValoracionGerontologica` WRITE;
/*!40000 ALTER TABLE `ValoracionGerontologica` DISABLE KEYS */;
/*!40000 ALTER TABLE `ValoracionGerontologica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evaluacionFragilidad`
--

LOCK TABLES `evaluacionFragilidad` WRITE;
/*!40000 ALTER TABLE `evaluacionFragilidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluacionFragilidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `formatoGeriatria`
--

LOCK TABLES `formatoGeriatria` WRITE;
/*!40000 ALTER TABLE `formatoGeriatria` DISABLE KEYS */;
INSERT INTO `formatoGeriatria` VALUES (1,'6/6','6','6','6','6','6','6','6','6','6','6','6','6','6','6','6','2012-12-12 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `formatoGeriatria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `valoracionFitbit`
--

LOCK TABLES `valoracionFitbit` WRITE;
/*!40000 ALTER TABLE `valoracionFitbit` DISABLE KEYS */;
/*!40000 ALTER TABLE `valoracionFitbit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-11 18:36:43
