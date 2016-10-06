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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `BookID` varchar(10) NOT NULL,
  `BookName` varchar(50) CHARACTER SET utf8 NOT NULL,
  `AuthorID` varchar(10) NOT NULL,
  `SupplierID` varchar(10) NOT NULL,
  `CategoryID` varchar(10) NOT NULL,
  `Price` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Shelf` varchar(50) CHARACTER SET utf8 NOT NULL,
  `RowNum` int(11) NOT NULL,
  `ColNum` int(11) NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `PublisherID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`BookID`),
  KEY `fk_Author` (`AuthorID`),
  KEY `fk_Supplier` (`SupplierID`),
  KEY `fk_Category` (`CategoryID`),
  CONSTRAINT `fk_Author` FOREIGN KEY (`AuthorID`) REFERENCES `author` (`AuthorID`),
  CONSTRAINT `fk_Category` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`),
  CONSTRAINT `fk_Supplier` FOREIGN KEY (`SupplierID`) REFERENCES `supplier` (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('BK-3RJU7','Harry Potter Tap 2','AT-0A1Z6HJ','SP-1TDYI','CG-5AZTWC9',500000,5,'TT1',2,4,'','PL-4CCNET0'),('BK-9I9UI','Harry Potter Tap 3','AT-0A1Z6HJ','SP-1TDYI','CG-5AZTWC9',500000,5,'TT1',2,5,'','PL-4CCNET0'),('BK-FYNV8','Harry Potter Tap 5','AT-0A1Z6HJ','SP-1TDYI','CG-5AZTWC9',500000,5,'TT1',2,7,'','PL-4CCNET0'),('BK-IYRCC','Harry Potter Tap 4','AT-0A1Z6HJ','SP-1TDYI','CG-5AZTWC9',500000,5,'TT1',2,6,'','PL-4CCNET0'),('BK-SX4G0','Harry Potter','AT-0A1Z6HJ','SP-1TDYI','CG-5AZTWC9',500000,5,'TT1',2,3,'','PL-4CCNET0');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
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
