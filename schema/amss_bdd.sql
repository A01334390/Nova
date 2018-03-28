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
-- Table structure for table `Domicilio`
--

DROP TABLE IF EXISTS `Domicilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Domicilio` (
  `idDomicilio` int(11) NOT NULL AUTO_INCREMENT,
  `pais` varchar(45) NOT NULL,
  `estado` varchar(40) NOT NULL,
  `ciudad` varchar(80) NOT NULL,
  `colonia` varchar(80) NOT NULL,
  `calle` varchar(80) NOT NULL,
  `numeroExterno` int(11) NOT NULL,
  `numeroInterno` int(11) DEFAULT NULL,
  `codigoPostal` varchar(10) NOT NULL,
  PRIMARY KEY (`idDomicilio`),
  UNIQUE KEY `idDomicilio_UNIQUE` (`idDomicilio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FitbitUserData`
--

DROP TABLE IF EXISTS `FitbitUserData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FitbitUserData` (
  `idFitbitUserData` int(11) NOT NULL,
  `FITAPP_CONSUMER_SECRET` varchar(80) DEFAULT NULL,
  `FITAPP_VERIFICATION_CODE` varchar(80) DEFAULT NULL,
  `FITAPP_SUBSCRIBE` varchar(80) DEFAULT NULL,
  `FITAPP_SUBSCRIBER_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idFitbitUserData`),
  UNIQUE KEY `idFitbitUserData_UNIQUE` (`idFitbitUserData`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Paciente`
--

DROP TABLE IF EXISTS `Paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Paciente` (
  `idPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `primerNombre` varchar(80) DEFAULT NULL,
  `segundoNombre` varchar(45) DEFAULT NULL,
  `fechaDeNacimiento` datetime DEFAULT NULL,
  `genero` tinyint(4) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `nacionalidad` varchar(45) DEFAULT NULL,
  `estadoNacimiento` varchar(45) DEFAULT NULL,
  `estadoCivil` tinyint(4) DEFAULT NULL,
  `tipoSangre` varchar(5) DEFAULT NULL,
  `afiliacionMedica` varchar(30) DEFAULT NULL,
  `amai` varchar(5) DEFAULT NULL,
  `cohabitacion` tinyint(4) DEFAULT NULL,
  `idDomicilio` int(11) DEFAULT NULL,
  `FITAPP_CONSUMER_KEY` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPaciente`),
  UNIQUE KEY `idPaciente_UNIQUE` (`idPaciente`),
  KEY `IdDomicilio_idx` (`idDomicilio`),
  KEY `FITAPP_CONSUMER_KEY_idx` (`FITAPP_CONSUMER_KEY`),
  CONSTRAINT `FITAPP_CONSUMER_KEY` FOREIGN KEY (`FITAPP_CONSUMER_KEY`) REFERENCES `FitbitUserData` (`idFitbitUserData`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `IdDomicilio` FOREIGN KEY (`idDomicilio`) REFERENCES `Domicilio` (`idDomicilio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TipoUsuario`
--

DROP TABLE IF EXISTS `TipoUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoUsuario` (
  `idTipoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTipoUsuario`),
  UNIQUE KEY `idTipoUsuario_UNIQUE` (`idTipoUsuario`),
  UNIQUE KEY `area_UNIQUE` (`area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `primerNombre` varchar(80) DEFAULT NULL,
  `segundoNombre` varchar(80) DEFAULT NULL,
  `fechaNacimiento` datetime DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `usuario` varchar(45) NOT NULL,
  `contrasenia` varchar(512) NOT NULL,
  `fechaValidez` datetime DEFAULT NULL,
  `idDomicilio` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`,`usuario`),
  UNIQUE KEY `idUsuario_UNIQUE` (`idUsuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  KEY `Domicilio_idx` (`idDomicilio`),
  CONSTRAINT `Domicilio` FOREIGN KEY (`idDomicilio`) REFERENCES `Domicilio` (`idDomicilio`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ValoracionGerontologica`
--

DROP TABLE IF EXISTS `ValoracionGerontologica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ValoracionGerontologica` (
  `idValoracionGerontologica` int(11) NOT NULL AUTO_INCREMENT,
  `dispositivosUso` varchar(255) DEFAULT NULL,
  `dispositivoMayorUso` varchar(255) DEFAULT NULL,
  `frecuenciaUso` varchar(255) DEFAULT NULL,
  `actividadesUso` varchar(255) DEFAULT NULL,
  `usosFavorecer` varchar(255) DEFAULT NULL,
  `apoyosocialPercibido` varchar(255) DEFAULT NULL,
  `actividadesComunitarias` varchar(255) DEFAULT NULL,
  `impresionDiagnostica` varchar(255) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `idPaciente` int(11) DEFAULT NULL,
  PRIMARY KEY (`idValoracionGerontologica`),
  UNIQUE KEY `idValoracionGerontologica_UNIQUE` (`idValoracionGerontologica`),
  KEY `idUsuario_idx` (`idUsuario`),
  KEY `idPac_idx` (`idPaciente`),
  CONSTRAINT `idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `pac` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `evaluacionFragilidad`
--

DROP TABLE IF EXISTS `evaluacionFragilidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluacionFragilidad` (
  `idevaluacionFragilidad` int(11) NOT NULL AUTO_INCREMENT,
  `perdidaPeso` tinyint(4) DEFAULT NULL,
  `perdidaPeso_interpretacion` varchar(45) DEFAULT NULL,
  `pobreResistencia` tinyint(4) DEFAULT NULL,
  `pobreResistencia_interpretacion` varchar(45) DEFAULT NULL,
  `velocidadMarcha` varchar(45) DEFAULT NULL,
  `velocidadMarcha_interpretacion` varchar(45) DEFAULT NULL,
  `fuerzaPresion` varchar(45) DEFAULT NULL,
  `fuerzaPresion_interpretacion` varchar(45) DEFAULT NULL,
  `actividadFisica` tinyint(4) DEFAULT NULL,
  `actividadFisica_interpretacion` varchar(512) DEFAULT NULL,
  `diagnostico` varchar(512) DEFAULT NULL,
  `evaluacionFuncional` varchar(512) DEFAULT NULL,
  `evaluacionCognitiva` varchar(512) DEFAULT NULL,
  `evaluacionNutricional` varchar(512) DEFAULT NULL,
  `evaluacionDeFragilidad` varchar(512) DEFAULT NULL,
  `IdPaciente` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idevaluacionFragilidad`),
  UNIQUE KEY `idevaluacionFragilidad_UNIQUE` (`idevaluacionFragilidad`),
  KEY `idPaciente_idx` (`IdPaciente`),
  KEY `idUs_idx` (`idUsuario`),
  CONSTRAINT `idPac` FOREIGN KEY (`IdPaciente`) REFERENCES `Paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUs` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formatoGeriatria`
--

DROP TABLE IF EXISTS `formatoGeriatria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formatoGeriatria` (
  `idformatoGeriatria` int(11) NOT NULL AUTO_INCREMENT,
  `katz` varchar(10) DEFAULT NULL,
  `katz_interpretacion` varchar(45) DEFAULT NULL,
  `barthel` varchar(10) DEFAULT NULL,
  `barthel_interpretacion` varchar(45) DEFAULT NULL,
  `lawtonBrody` varchar(10) DEFAULT NULL,
  `lawtonBrody_interpretacion` varchar(45) DEFAULT NULL,
  `estadoMental` varchar(10) DEFAULT NULL,
  `estadoMental_interpretacion` varchar(45) DEFAULT NULL,
  `escalaDepresion` varchar(10) DEFAULT NULL,
  `escalaDepresion_interpretacion` varchar(45) DEFAULT NULL,
  `cribadoNutricional` varchar(10) DEFAULT NULL,
  `cribadoNutricional_interpretacion` varchar(45) DEFAULT NULL,
  `pruebaDesempenio` varchar(10) DEFAULT NULL,
  `pruebaDesempenio_interpretacion` varchar(45) DEFAULT NULL,
  `levantateAnda` varchar(10) DEFAULT NULL,
  `levantateAnda_interpretacion` varchar(45) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `idPaciente` int(11) DEFAULT NULL,
  PRIMARY KEY (`idformatoGeriatria`),
  KEY `us_idx` (`idUsuario`),
  KEY `pa_idx` (`idPaciente`),
  CONSTRAINT `pa` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `us` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `valoracionFitbit`
--

DROP TABLE IF EXISTS `valoracionFitbit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valoracionFitbit` (
  `idvaloracionFitbit` int(11) NOT NULL AUTO_INCREMENT,
  `datosMovilidad` blob,
  PRIMARY KEY (`idvaloracionFitbit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-28 13:38:07
