CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `library`;
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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `AdminID` varchar(10) NOT NULL,
  `AdminName` varchar(50) NOT NULL,
  `UserName` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Pass` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Sex` varchar(10) CHARACTER SET utf8 NOT NULL,
  `Role` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('1','Nguyen Thanh Minh','ntminh','minhanh','Male','Leader'),('2','Admin','admin','minhanh','Male','Manager');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `AuthorID` varchar(10) NOT NULL,
  `AuthorName` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`AuthorID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES ('AT-0A1Z6HJ','Grant Cardone'),('AT-0TYQOJM','William Shakespeare'),('AT-14NKQGU','Edgar Allan Poe'),('AT-434VGT5','Vu Trong Phung'),('AT-7RTQBF0','Franz Kafka'),('AT-8YCL7EN','Ernest Hemingway'),('AT-CSFRSDY','Ben Horowitz'),('AT-CVNMDEU','JD Salinger'),('AT-F93WQP1','Conan Doyle'),('AT-FHDKGOE','Harper Lee'),('AT-FW98FQ','J.K.Rowling'),('AT-GKGEW21','Dan Brown'),('AT-H137GUW','Timothee De Fombelle'),('AT-IUQWJ31','Haruki Murakami'),('AT-NXALZJ1','Jason Medelson'),('AT-QDFKSE5','Mark Twain'),('AT-RTQ3G8','Nguyen Minh Nhat'),('AT-VBA2145','Nam Cao'),('AT-YGH62UQ','Nguyen Du'),('AT-Z52NGWE','Rick Riordan'),('AT-Z7W97CI','Nguyen Nhat Anh'),('AT-ZX786JG','Tran Dang Khoa');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `book` VALUES ('BK-14IWS','Rung Na Uy','AT-IUQWJ31','SP-J6B73','CG-WOGNBZ7',125000,6,'TT3',4,2,NULL,'PL-F25792E'),('BK-28VNZ','So Do','AT-434VGT5','SP-J6B73','CG-NQGO34V',43000,5,'TT2',9,2,NULL,'PL-IOV68JE'),('BK-3RJU7','Phi Ly Tri','AT-0A1Z6HJ','SP-QKNR8','CG-NQGO34V',590000,5,'TT1',2,4,'','PL-4CCNET0'),('BK-513RT','Doi Gio Hu','AT-NXALZJ1','SP-X7IGN','CG-LNMSV13',89000,5,'TT9',8,1,NULL,'PL-RYGHN20'),('BK-78NCQ','Giet Con Chim Nhai','AT-FHDKGOE','SP-1TDYI','CG-JKFNV07',48000,10,'TT8',2,5,NULL,'PL-VBQ67JG'),('BK-9I9UI','Harry Potter Tap 3','AT-FW98FQ','SP-1TDYI','CG-OEWFJB5',780000,9,'TT1',2,5,'','PL-4CCNET0'),('BK-AVUE4','Vango','AT-H137GUW','SP-QKNR8','CG-OEWF21Z',92000,1,'TT8',2,4,NULL,'PL-KAKV8YW'),('BK-FMQIE','Nha Gia Kim','AT-YGH62UQ','SP-QKNR8','CG-DWXIF09',58000,8,'TT3',2,1,NULL,'PL-TYQ2346'),('BK-FYNV8','Co Gai Mat Tich','AT-0A1Z6HJ','SP-KXD69','CG-VWK4392',100000,4,'TT9',3,7,'','PL-26JGUQE'),('BK-IFZ77','Lich Su The Gioi','AT-CSFRSDY','SP-Z5YOP','CG-LNMSV13',40000,2,'TT8',9,5,NULL,'PL-FJV57BT'),('BK-IYRCC','Hoa Nguc','AT-GKGEW21','SP-RM2EC','CG-G790BWT',150000,7,'TT4',9,2,'','PL-6FQD7HF'),('BK-JFLQ2','Tat Den','AT-VBA2145','SP-Z5YOP','CG-G790BWT',40000,2,'TT4',6,1,NULL,'PL-56QZG6F'),('BK-JS670','Thien Than Va Ac Quy','AT-GKGEW21','SP-RM2EC','CG-G790BWT',125000,3,'TT6',9,3,NULL,'PL-13HFKQ8'),('BK-KJGO8','Ke Cap Tia Chop','AT-Z52NGWE','SP-KY0LY','CG-ADFQ291',67000,10,'TT7',2,8,NULL,'PL-780FHNV'),('Bk-NCT73','Hat Giong Tam Hon','AT-RTQ3G8','SP-1TDYI','CG-A95WFBY',60000,12,'TT4',6,2,NULL,'PL-689FHWE'),('BK-NQG88','Bach Khoa Toan Thu','AT-NXALZJ1','SP-KXD69','CG-VWK4392',100000,4,'TT7',7,7,NULL,'PL-780FHNV'),('BK-OFN89','Gia Tu Vu Khi','AT-8YCL7EN','SP-X7IGN','CG-KQGSV29',50000,4,'TT3',2,6,NULL,'PL-FSRD7TT'),('BK-OT790','Bat Tre Dong Xanh','AT-0A1Z6HJ','SP-KXD69','CG-JQWN252',75000,8,'TT4',3,7,NULL,'PL-GHQ245V'),('BK-P31D2','So Do Tap 2','AT-434VGT5','SP-J6B73','CG-NQGO34V',43000,5,'TT2',9,3,'','PL-IOV68JE'),('BK-QZOU3','Tu Dien Tieng Anh','AT-7RTQBF0','SP-X7IGN','CG-5VZV8NN',90000,9,'TT5',8,3,NULL,'PL-13HFKQ8'),('BK-SX4G0','Toeic 600','AT-Z7W97CI','SP-X7IGN','CG-5VZV8NN',900000,12,'TT3',1,8,'','PL-56QZG6F'),('BK-TP6O1','Kinh Te Hoc','AT-0A1Z6HJ','SP-KXD69','CG-5VZV8NN',60000,4,'F3',2,3,'12cachyeu.jpg','PL-6FQD7HF'),('BK-UFN23','Tap Chi Ngay Nay','AT-ZX786JG','SP-QKNR8','CG-JKFNV07',25000,12,'TT1',8,4,NULL,'PL-EJQFO0Q');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowingmanagement`
--

DROP TABLE IF EXISTS `borrowingmanagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrowingmanagement` (
  `BorrowID` varchar(10) NOT NULL,
  `RdID` varchar(10) DEFAULT NULL,
  `BookID` varchar(10) DEFAULT NULL,
  `BorrowDate` date NOT NULL,
  `ReturnDate` date NOT NULL,
  PRIMARY KEY (`BorrowID`),
  KEY `fk_Reader` (`RdID`),
  KEY `fk_Book` (`BookID`),
  CONSTRAINT `fk_Book` FOREIGN KEY (`BookID`) REFERENCES `book` (`BookID`),
  CONSTRAINT `fk_Reader` FOREIGN KEY (`RdID`) REFERENCES `reader` (`RdID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowingmanagement`
--

LOCK TABLES `borrowingmanagement` WRITE;
/*!40000 ALTER TABLE `borrowingmanagement` DISABLE KEYS */;
INSERT INTO `borrowingmanagement` VALUES ('BR-0TZJO','RD-J906TFL','BK-FYNV8','2016-04-11','2016-11-04'),('BR-1095N','RD-GJQ3249','BK-513RT','2016-10-20','2016-10-22'),('BR-123ER','RD-348GNZV','BK-14IWS','2016-05-05','2016-11-04'),('BR-123NZ','RD-28952GQ','BK-OFN89','2016-08-20','2016-09-10'),('BR-39NVZ','RD-89CVCUE','BK-OT790','2016-02-03','2016-02-23'),('BR-51NEF','RD-J906TFL','BK-JFLQ2','2016-04-20','2016-05-03'),('BR-89DHU','RD-GJWEGKE','BK-JS670','2016-04-16','2016-04-30'),('BR-8Y2UL','RD-J906TFL','BK-9I9UI','2016-10-21','2016-10-31'),('BR-AB762','RD-GJQ3249','BK-JFLQ2','2016-03-06','2016-11-07'),('BR-ADZ23','RD-28952GQ','BK-AVUE4','2016-04-14','2016-11-03'),('BR-FJQ39','RD-6123FQP','BK-SX4G0','2016-10-03','2016-10-17'),('BR-MV93L','RD-WGRKP','BK-KJGO8','2016-02-20','2016-02-25'),('BR-OBN37','RD-KQEFQ8','BK-28VNZ','2016-03-06','2016-03-20'),('BR-PO928','RD-78QENDZ','BK-UFN23','2016-03-05','2016-03-08'),('BR-TY8US','RD-FQLY68B','BK-FYNV8','2016-04-30','2016-05-14'),('BR-VY286','RD-BNIWEF6','BK-IFZ77','2016-10-01','2016-10-10'),('BR-VZI26','RD-KLQR92','BK-9I9UI','2016-07-07','2016-07-29'),('BR-YKSH1','RD-0VK68WI','BK-3RJU7','2016-10-28','2016-11-07'),('BR-Z356I','RD-78QENDZ','BK-FMQIE','2016-02-28','2016-03-15'),('BR-ZAYD4','RD-0VK68WI','BK-78NCQ','2016-10-28','2016-11-07');
/*!40000 ALTER TABLE `borrowingmanagement` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER trig_dcrbook after insert ON borrowingmanagement
    FOR EACH ROW
    update book
    set Quantity = Quantity-1
    where BookID = new.BookID */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER trig_icrbook after delete ON borrowingmanagement
    FOR EACH ROW
    update book
    set Quantity = Quantity+1
    where BookID = OLD.BookID */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `CategoryID` varchar(10) NOT NULL,
  `CategoryName` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('CG-146VYIU','Dia Ly'),('CG-5AZTWC9','Khoa Hoc'),('CG-5VZV8NN','Ngoai Ngu'),('CG-609NVMA','Khoa Hoc Gia Tuong'),('CG-A95WFBY','Ky Nang Song'),('CG-ADFQ291','Than Thoai'),('CG-DWXIF09','Triet Hoc'),('CG-FVNIEQ8','Trinh Tham'),('CG-G790BWT','Tieu Thuyet'),('CG-JKFNV07','Tap chi'),('CG-JQWN252','Truyen Ngan'),('CG-KJG678B','Truyen Tranh'),('CG-KQGSV29','Kinh Doanh'),('CG-LNMSV13','Lich Su'),('CG-MFBP3TI','Kinh Te'),('CG-NQGO34V','Binh Luan Van Hoc'),('CG-OEWF21Z','Sach Giao Khoa'),('CG-OEWFJB5','Truyen Thieu Nhi'),('CG-VWK4392','Truyen Kinh Di'),('CG-WOGNBZ7','Lang Man'),('CG-XWEQABL','Bao');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher` (
  `PublisherID` varchar(10) NOT NULL,
  `PublisherName` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`PublisherID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES ('PL-13HFKQ8','Hachette Book Group'),('PL-26JGUQE','HarperCollins'),('PL-4CCNET0','Alphabook'),('PL-56QZG6F','NXB Hoi Nha Van'),('PL-689FHWE','	Oxford University Press'),('PL-6FQD7HF','	Pearson'),('PL-780FHNV','	Groupe Madrigall'),('PL-EJQFO0Q','NXB Phu Nu'),('PL-F25792E','Informa'),('PL-FJV57BT','NXB Ha Noi'),('PL-FSRD7TT','NXB Tre'),('PL-GHQ245V','	Shueisha'),('PL-GXZ378D','Penguin Random House	'),('PL-IOV68JE','NXB Thoi Dai'),('PL-KAKV8YW','First News'),('PL-PWEM0CU','NXB Kim Dong'),('PL-RYGHN20','Macmillan Publishers'),('PL-TYQ2346','Holtzbrinck'),('PL-VBQ67JG','Simon and Schuster'),('PL-WF4H9C9','NXB Van Hoc');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `reader` VALUES ('RD-0VK68WI','Nguyen Thanh Minh','2918266788','Male','1989-10-13','10 Le Duan','0905056667','ntminhdn@gmail.com','2015-10-14','2017-10-25'),('RD-28952GQ','Pham Thi Quynh Anh','2149262609','Female','1993-12-12','110 Nui Thanh','09012375102','PTQAnh@gmail.com','2015-07-29','2017-10-25'),('RD-348GNZV','Le Tu Dung','3406384582','Male','1980-01-23','123 Bach Dang','09234901239','TuDung@gmail.com','2015-04-06','2017-10-25'),('RD-6123FQP','Nguyen Cao Cuong','2789456023','Male','1995-06-18','20 Cao Thang','01247260201','NcCuong@gmail.com','2014-06-10','2017-10-25'),('RD-78QENDZ','Tran Quoc Thang','3129312930','Male','1998-03-17','165 Quang Trung','09283712184','Mirokid@gmail.com','2015-12-14','2016-12-30'),('RD-89CVCUE','Nguyen Dang Khoa','2986419284','Male','1997-04-16','50 Le Dinh Ly','01689234892','KhoaKhonKheo@gmail.com','2014-12-25','2016-12-24'),('RD-BNIWEF6','Nguyen Tuan Anh','2348253937','Male','1993-09-12','54 Ba Dinh','01619271391','TuanAnh@gmail.com','2016-11-11','2017-10-25'),('RD-FQLY68B','Nguyen Ngoc Tung','2918266908','Male','1991-10-03','101 Le Duan','0905056699','minhthanhdn94@gmail.com','2016-10-05','2017-10-05'),('RD-GJQ3249','Tran Thi My Linh','2359262034','Female','1985-10-23','214 Ly Tu Trong','01623489133','MyLinh@gmail.com','2014-03-08','2015-04-09'),('RD-GJWEGKE','Nguyen Le Thuy Trang','3282301481','Female','2000-09-28','200 Hoang Dieu','01249375392','Gorse@gmail.com','2016-09-09','2017-10-09'),('RD-J906TFL','Nguyen Thi Ngoc Linh','2918261821','Female','1994-10-20','1 Le Duan','0905050505','naalinhh94000@gmail.com','2016-07-13','2017-10-19'),('RD-KLQR92','Huynh Cam Huong','2348925201','Female','1994-11-20','66 Tieu La','01675920902','CamHuong@gmail.com','2016-03-29','2017-04-07'),('RD-KQEFQ8','Ha Nguyen Minh Quan','2589246924','Male ','1995-07-07','01 An Thuong 23','0906445737','kamisamaGG@gmail.com','2015-01-20','2016-02-12'),('RD-WGRKP','Hoang Thuy Trang','2456766876','Female','1994-06-02','116 Le Do','0905050505','httrang@gmail.com','2016-10-19','2017-10-19'),('RD-Z819R08','Nguyen Ngoc Linh','2456766908','Male','1995-10-13','101 Le Do','0905056600','batch126aptech@gmail.com','2016-10-05','2017-10-19');
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `SupplierID` varchar(10) NOT NULL,
  `SupplierName` varchar(50) CHARACTER SET utf8 NOT NULL,
  `S_Address` varchar(100) CHARACTER SET utf8 NOT NULL,
  `S_Phone` varchar(20) NOT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('SP-1TDYI','NS Fahasa','108 Dien Bien Phu','09031295129'),('SP-J6B73','NS Phuong Nam','156 Phan Chau Trinh','09040500422'),('SP-KXD69','NS Da Nang','412 Bach Dang','01623489210'),('SP-KY0LY','NS Dong Tay','30 Nguyen Thai Hoc','01234567892'),('SP-QKNR8','NS Ho Guom','53 Dinh Tien Hoang','09053920414'),('SP-RM2EC','NS Tien Tho','828 Duong Lang','09352321913'),('SP-X7IGN','NS Kim Dong','52 Nam Cao','09012389519'),('SP-Z5YOP','NS Nha Nam','22 Pham Ngoc Thach','01234568882');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'library'
--

--
-- Dumping routines for database 'library'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-22 10:44:17
