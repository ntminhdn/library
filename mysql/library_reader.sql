-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.10-MariaDB

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
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reader` (
  `RdID` varchar(10) NOT NULL,
  `RdName` varchar(50) CHARACTER SET utf8 NOT NULL,
  `IDCardNumber` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Sex` varchar(10) CHARACTER SET utf8 NOT NULL,
  `Birthday` date NOT NULL,
  `Address` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8 NOT NULL,
  `ActivationDate` date NOT NULL,
  `ExpiredDate` date NOT NULL,
  PRIMARY KEY (`RdID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES ('RD-0VK68WI','Nguyen Ngoc Ngan','2918266788','Male','1989-10-13','10 Le Duan','0905056667','nnn@gmail.com','2016-10-05','2017-10-05'),('RD-FQLY68B','Nguyen Ngoc Tung','2918266908','Male','1991-10-03','101 Le Duan','0905056699','nnt@gmail.com','2016-10-05','2017-10-05'),('RD-J906TFL','Nguyen Thi Ngoc Linh','2918261821','Female','1994-10-20','1 Le Duan','0905050505','abcde@gmail.com','2016-10-05','2017-10-05'),('RD-Z819R08','Nguyen Ngoc Linh','2456766908','Male','1995-10-13','101 Le Do','0905056600','nnl@gmail.com','2016-10-05','2017-10-05');
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-06 18:15:13
