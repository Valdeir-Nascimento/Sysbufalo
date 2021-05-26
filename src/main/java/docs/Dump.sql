-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: sysbufalo
-- ------------------------------------------------------
-- Server version	5.6.34-log

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
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brinco` varchar(45) NOT NULL,
  `fazenda` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_animal_fazenda_idx` (`fazenda`),
  CONSTRAINT `fk_animal_fazenda` FOREIGN KEY (`fazenda`) REFERENCES `fazenda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (2,'AA1',2),(3,'AB2',2),(4,'AC3',2),(11,'AA3',11),(12,'DDVB3',11),(13,'FFSR4',11);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fazenda`
--

DROP TABLE IF EXISTS `fazenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fazenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `cidade` varchar(20) DEFAULT NULL,
  `estado` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fazenda`
--

LOCK TABLES `fazenda` WRITE;
/*!40000 ALTER TABLE `fazenda` DISABLE KEYS */;
INSERT INTO `fazenda` VALUES (2,'FAZENDA TRES MARIAS','Belem','Acre'),(9,'FAZENDA CURRALZINHO','de teste','Acre'),(10,'FAZENDA DE TESTE THIAGO','Thiagolandia','Ceará'),(11,'TESTE FAZENDA NOVA','wfgsfgsdg','Amapá'),(13,'TESTE ASFGASFGSDGS','sdfgsdfg','Alagoas');
/*!40000 ALTER TABLE `fazenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenha`
--

DROP TABLE IF EXISTS `ordenha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordenha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `manual` tinyint(1) NOT NULL,
  `quantidade_produzida` float NOT NULL,
  `animal` int(11) NOT NULL,
  `ordenhador` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ordenha_animal1_idx` (`animal`),
  KEY `fk_ordenha_ordenhador1_idx` (`ordenhador`),
  CONSTRAINT `fk_ordenha_animal1` FOREIGN KEY (`animal`) REFERENCES `animal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordenha_ordenhador1` FOREIGN KEY (`ordenhador`) REFERENCES `ordenhador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenha`
--

LOCK TABLES `ordenha` WRITE;
/*!40000 ALTER TABLE `ordenha` DISABLE KEYS */;
INSERT INTO `ordenha` VALUES (1,'2019-01-21',1,100,2,1),(2,'2019-01-01',1,300,2,1),(3,'2018-12-19',1,150,2,1),(4,'2018-12-03',0,197,2,1),(6,'2019-01-23',1,400,2,1),(7,'2019-01-16',0,800,2,2);
/*!40000 ALTER TABLE `ordenha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenhador`
--

DROP TABLE IF EXISTS `ordenhador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordenhador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenhador`
--

LOCK TABLES `ordenhador` WRITE;
/*!40000 ALTER TABLE `ordenhador` DISABLE KEYS */;
INSERT INTO `ordenhador` VALUES (1,'Valdeir'),(2,'Thiago Lima');
/*!40000 ALTER TABLE `ordenhador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parto`
--

DROP TABLE IF EXISTS `parto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordem` int(11) NOT NULL,
  `data_parto` date NOT NULL,
  `animal` int(11) NOT NULL,
  `inicioProducao` date DEFAULT NULL,
  `fimProducao` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parto_animal1_idx` (`animal`),
  CONSTRAINT `fk_parto_animal1` FOREIGN KEY (`animal`) REFERENCES `animal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parto`
--

LOCK TABLES `parto` WRITE;
/*!40000 ALTER TABLE `parto` DISABLE KEYS */;
INSERT INTO `parto` VALUES (1,6,'2019-01-01',2,'2019-01-03','2019-01-16'),(3,1,'2019-01-01',3,'2019-01-07','2019-01-21');
/*!40000 ALTER TABLE `parto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peso_animal`
--

DROP TABLE IF EXISTS `peso_animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peso_animal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `peso` float DEFAULT NULL,
  `data` date DEFAULT NULL,
  `animal` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_peso_animal_animal` (`animal`),
  CONSTRAINT `FK_peso_animal_animal` FOREIGN KEY (`animal`) REFERENCES `animal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peso_animal`
--

LOCK TABLES `peso_animal` WRITE;
/*!40000 ALTER TABLE `peso_animal` DISABLE KEYS */;
INSERT INTO `peso_animal` VALUES (11,10,'2019-01-23',3),(21,200,'2019-01-28',2),(22,225,'2019-01-28',2),(28,45,'2019-01-28',3);
/*!40000 ALTER TABLE `peso_animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `senha` varchar(45) NOT NULL,
  `fazenda` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`email`),
  KEY `fk_usuario_fazenda1_idx` (`fazenda`),
  CONSTRAINT `fk_usuario_fazenda1` FOREIGN KEY (`fazenda`) REFERENCES `fazenda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (0000000002,'Valdeir Carneiro Nascimento','valdeircn11@gmail.com','235235','123',2),(0000000004,'Carlos Manoel da Silva','produtor@gmail.com','235235','123456',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_has_fazenda`
--

DROP TABLE IF EXISTS `usuario_has_fazenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_has_fazenda` (
  `usuario` int(10) unsigned zerofill NOT NULL,
  `fazenda` int(11) NOT NULL,
  `perfil` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`usuario`,`fazenda`),
  KEY `fk_usuario_has_fazenda_fazenda1_idx` (`fazenda`),
  KEY `fk_usuario_has_fazenda_usuario1_idx` (`usuario`),
  CONSTRAINT `fk_usuario_has_fazenda_fazenda1` FOREIGN KEY (`fazenda`) REFERENCES `fazenda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_fazenda_usuario1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_has_fazenda`
--

LOCK TABLES `usuario_has_fazenda` WRITE;
/*!40000 ALTER TABLE `usuario_has_fazenda` DISABLE KEYS */;
INSERT INTO `usuario_has_fazenda` VALUES (0000000002,11,'ADM'),(0000000004,11,'PROD');
/*!40000 ALTER TABLE `usuario_has_fazenda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-26 20:06:11
