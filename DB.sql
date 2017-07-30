CREATE DATABASE  IF NOT EXISTS `wpmaps` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wpmaps`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wpmaps
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `cable`
--

DROP TABLE IF EXISTS `cable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cable` (
  `ID` int(11) NOT NULL,
  `CAPACITY` varchar(255) DEFAULT NULL,
  `INPLACE` varchar(255) DEFAULT NULL,
  `LENGTHCABLE` int(11) DEFAULT NULL,
  `MARKING` varchar(255) DEFAULT NULL,
  `MODULECOUNT` int(11) DEFAULT NULL,
  `OUTPLACE` varchar(255) DEFAULT NULL,
  `PRODUCTION` varchar(255) DEFAULT NULL,
  `FIBER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CABLE_FIBER_ID` (`FIBER_ID`),
  CONSTRAINT `FK_CABLE_FIBER_ID` FOREIGN KEY (`FIBER_ID`) REFERENCES `fiber` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cable`
--

LOCK TABLES `cable` WRITE;
/*!40000 ALTER TABLE `cable` DISABLE KEYS */;
INSERT INTO `cable` VALUES (1,'48','Китобоев 7',255,NULL,8,'Китобоев 35','Одесс- кабель',1);
/*!40000 ALTER TABLE `cable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clutch`
--

DROP TABLE IF EXISTS `clutch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clutch` (
  `ID` int(11) NOT NULL,
  `INPUTS` int(11) DEFAULT NULL,
  `CONDITIONS` longblob,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `COUNTCASSETS` int(11) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clutch`
--

LOCK TABLES `clutch` WRITE;
/*!40000 ALTER TABLE `clutch` DISABLE KEYS */;
INSERT INTO `clutch` VALUES (1,4,NULL,'Даля 17',2,'Короткие кабеля, сложный подъезд');
/*!40000 ALTER TABLE `clutch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clutch_has_cable`
--

DROP TABLE IF EXISTS `clutch_has_cable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clutch_has_cable` (
  `clutch_ID` int(11) NOT NULL,
  `cable_ID` int(11) NOT NULL,
  PRIMARY KEY (`clutch_ID`,`cable_ID`),
  KEY `fk_clutch_has_cabel_cable_idx` (`cable_ID`),
  CONSTRAINT `fk_clutch_has_cabel_cable` FOREIGN KEY (`cable_ID`) REFERENCES `cable` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_clutch_has_cabel_clutch` FOREIGN KEY (`clutch_ID`) REFERENCES `clutch` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clutch_has_cable`
--

LOCK TABLES `clutch_has_cable` WRITE;
/*!40000 ALTER TABLE `clutch_has_cable` DISABLE KEYS */;
/*!40000 ALTER TABLE `clutch_has_cable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cord`
--

DROP TABLE IF EXISTS `cord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cord` (
  `ID` bigint(20) NOT NULL,
  `CABLEID` bigint(20) DEFAULT NULL,
  `DATEOFLASTMEASUREMENT` date DEFAULT NULL,
  `LASTMEASUREMENT` double DEFAULT NULL,
  `NUMBER` int(11) DEFAULT NULL,
  `USED` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cord`
--

LOCK TABLES `cord` WRITE;
/*!40000 ALTER TABLE `cord` DISABLE KEYS */;
/*!40000 ALTER TABLE `cord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiber`
--

DROP TABLE IF EXISTS `fiber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fiber` (
  `ID` int(11) NOT NULL,
  `ADDRESSOWNER` varchar(255) DEFAULT NULL,
  `INDICATIONREFLECTOR` varchar(255) DEFAULT NULL,
  `NUMBER` int(11) DEFAULT NULL,
  `TYPEFIBER` int(11) DEFAULT NULL,
  `SLEEVE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SLEEVE_ID_idx` (`SLEEVE_ID`),
  CONSTRAINT `FK_SLEEVE_ID` FOREIGN KEY (`SLEEVE_ID`) REFERENCES `sleeve` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiber`
--

LOCK TABLES `fiber` WRITE;
/*!40000 ALTER TABLE `fiber` DISABLE KEYS */;
INSERT INTO `fiber` VALUES (1,'Пограничная 69/A','',22,1,1);
/*!40000 ALTER TABLE `fiber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiber_sleeve`
--

DROP TABLE IF EXISTS `fiber_sleeve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fiber_sleeve` (
  `Fiber_ID` int(11) NOT NULL,
  `sleeve_ID` int(11) NOT NULL,
  PRIMARY KEY (`Fiber_ID`,`sleeve_ID`),
  KEY `FK_FIBER_SLEEVE_sleeve_ID` (`sleeve_ID`),
  CONSTRAINT `FK_FIBER_SLEEVE_Fiber_ID` FOREIGN KEY (`Fiber_ID`) REFERENCES `fiber` (`ID`),
  CONSTRAINT `FK_FIBER_SLEEVE_sleeve_ID` FOREIGN KEY (`sleeve_ID`) REFERENCES `sleeve` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiber_sleeve`
--

LOCK TABLES `fiber_sleeve` WRITE;
/*!40000 ALTER TABLE `fiber_sleeve` DISABLE KEYS */;
/*!40000 ALTER TABLE `fiber_sleeve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `ID` bigint(20) NOT NULL,
  `ADDED` datetime DEFAULT NULL,
  `ERRORTYPE` longblob,
  `LOGGERLEVEL` int(11) DEFAULT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (3,'2017-07-04 13:22:59',NULL,3,'zekart авторизовался в системе. IP:0:0:0:0:0:0:0:1'),(51,'2017-07-04 14:19:48',NULL,3,'zekart авторизовался в системе. IP:0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pillar`
--

DROP TABLE IF EXISTS `pillar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pillar` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LAT` double DEFAULT NULL,
  `LNG` double DEFAULT NULL,
  `MATHERIALLPILLAR` int(11) DEFAULT NULL,
  `NUMBERTRANSPOTRSTATION` int(11) DEFAULT NULL,
  `OWNER` int(11) DEFAULT NULL,
  `TRANSPORTSTATION` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `CLUTCH_ID` int(11) DEFAULT NULL,
  `CABEL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CLUTCH_ID_idx` (`CLUTCH_ID`),
  KEY `FK_CABLE_ID_idx` (`CABEL_ID`),
  CONSTRAINT `FK_CABLE_ID` FOREIGN KEY (`CABEL_ID`) REFERENCES `cable` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLUTCH_ID` FOREIGN KEY (`CLUTCH_ID`) REFERENCES `clutch` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pillar`
--

LOCK TABLES `pillar` WRITE;
/*!40000 ALTER TABLE `pillar` DISABLE KEYS */;
INSERT INTO `pillar` VALUES (1,46.96788786373975,31.978025436401367,0,230,0,'WPN',0,1,1),(2,46.96804893529075,31.97519302368164,2,230,2,'WPN22',2,NULL,NULL),(4,46.96799036387377,31.976802349090576,0,230,3,'WPN226',0,NULL,NULL);
/*!40000 ALTER TABLE `pillar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `port`
--

DROP TABLE IF EXISTS `port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `port` (
  `ID` bigint(20) NOT NULL,
  `ACTIVE` tinyint(1) DEFAULT '0',
  `CORDID` bigint(20) DEFAULT NULL,
  `NUM` int(11) DEFAULT NULL,
  `PORTTYPE` int(11) DEFAULT NULL,
  `SPEED` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `port`
--

LOCK TABLES `port` WRITE;
/*!40000 ALTER TABLE `port` DISABLE KEYS */;
/*!40000 ALTER TABLE `port` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',100);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serverdevices`
--

DROP TABLE IF EXISTS `serverdevices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serverdevices` (
  `ID` bigint(20) NOT NULL,
  `MANUFACTURER` varchar(255) DEFAULT NULL,
  `MODELNAME` varchar(255) DEFAULT NULL,
  `PORTCOUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serverdevices`
--

LOCK TABLES `serverdevices` WRITE;
/*!40000 ALTER TABLE `serverdevices` DISABLE KEYS */;
/*!40000 ALTER TABLE `serverdevices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serverdevices_port`
--

DROP TABLE IF EXISTS `serverdevices_port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serverdevices_port` (
  `ServerDevices_ID` bigint(20) NOT NULL,
  `ports_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ServerDevices_ID`,`ports_ID`),
  KEY `FK_SERVERDEVICES_PORT_ports_ID` (`ports_ID`),
  CONSTRAINT `FK_SERVERDEVICES_PORT_ServerDevices_ID` FOREIGN KEY (`ServerDevices_ID`) REFERENCES `serverdevices` (`ID`),
  CONSTRAINT `FK_SERVERDEVICES_PORT_ports_ID` FOREIGN KEY (`ports_ID`) REFERENCES `port` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serverdevices_port`
--

LOCK TABLES `serverdevices_port` WRITE;
/*!40000 ALTER TABLE `serverdevices_port` DISABLE KEYS */;
/*!40000 ALTER TABLE `serverdevices_port` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sleeve`
--

DROP TABLE IF EXISTS `sleeve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sleeve` (
  `ID` int(11) NOT NULL,
  `INDICATION` double DEFAULT NULL,
  `TYPESLEEVE` double DEFAULT NULL,
  `FIBER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SLEEVE_FIBER_ID` (`FIBER_ID`),
  CONSTRAINT `FK_SLEEVE_FIBER_ID` FOREIGN KEY (`FIBER_ID`) REFERENCES `fiber` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sleeve`
--

LOCK TABLES `sleeve` WRITE;
/*!40000 ALTER TABLE `sleeve` DISABLE KEYS */;
INSERT INTO `sleeve` VALUES (1,-0.01,60,NULL);
/*!40000 ALTER TABLE `sleeve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `ABOUT` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `MESSAGES` longblob,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERROLE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'I`m first user. I`m admin','informer@mksat.net','panker','�\�\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0t\0Created automaticly by systemx','b762f226d34829794a1aec5e40727bce',1),(2,'I`m first user. I`m admin','zekart22@gmail.com','zekart','�\�\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0t\0Created automaticly by systemx','00a1f187721c63501356bf791e69382c',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-04 17:14:21
