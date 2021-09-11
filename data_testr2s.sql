-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: testr2s
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `idaccounts` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fullname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date NOT NULL,
  `image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY (`idaccounts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('hihi','999999999','cuoihoai','2002-06-02','user1.jpg','Tan Thanh'),('meo','meo123','Trinh Thi Meo','2002-04-22','user.png','Can Tho'),('suu','suu123','Le Van Suu','2003-11-21','user2.jpg','Tien Giang'),('teo','teo123','Nguyen Van Teo','2001-10-25','user4.png','Hau Giang'),('ty','ty123','Ngo Van Ty','2001-11-21','user5.jpg','Long Xuyen');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `idauthorities` int NOT NULL AUTO_INCREMENT,
  `account_idaccounts` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_idroles` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idauthorities`),
  KEY `account_id_idx` (`account_idaccounts`),
  KEY `role_id_idx` (`role_idroles`),
  CONSTRAINT `account_id` FOREIGN KEY (`account_idaccounts`) REFERENCES `accounts` (`idaccounts`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_idroles`) REFERENCES `roles` (`idrole`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (5,'meo','USER'),(13,'teo','ADMIN'),(18,'meo','PM'),(19,'teo','PM'),(20,'suu','USER'),(22,'ty','USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `idcategories` char(4) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idcategories`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES ('1000','Dong ho'),('1001','May tinh'),('1002','Dien thoai'),('1003','May anh');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `idorderdetails` bigint NOT NULL AUTO_INCREMENT,
  `order_idorder` bigint NOT NULL,
  `product_idproduct` int NOT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`idorderdetails`),
  KEY `order_idorder_idx` (`order_idorder`),
  KEY `productid_idx` (`product_idproduct`),
  CONSTRAINT `order_idorder` FOREIGN KEY (`order_idorder`) REFERENCES `orders` (`idorders`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productid` FOREIGN KEY (`product_idproduct`) REFERENCES `products` (`idproducts`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (1,8,3,18890000,4),(2,8,4,19990000,9),(3,8,2,18990000,5),(4,8,8,552000,6),(5,9,3,18890000,5),(6,9,6,5490000,4),(7,10,7,9592000,4),(8,10,8,552000,4),(9,10,9,2290000,4),(10,11,3,18890000,4),(11,11,15,2790000,1),(12,11,4,19990000,3),(13,12,3,18890000,2),(14,12,3,18890000,1),(15,13,3,18890000,4),(16,13,4,19990000,4);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `idorders` bigint NOT NULL AUTO_INCREMENT,
  `account_idaccount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createdate` datetime NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idorders`),
  KEY `account_id_idx` (`account_idaccount`),
  CONSTRAINT `account` FOREIGN KEY (`account_idaccount`) REFERENCES `accounts` (`idaccounts`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'teo','2021-09-09 00:00:00','caibe - tien giang'),(2,'teo','2021-09-09 00:00:00','cai be - tien giang'),(3,'teo','2021-09-09 00:00:00','cai be'),(4,'teo','2021-09-09 00:00:00','aaaaa'),(5,'teo','2021-09-09 00:00:00','sasasas'),(6,'teo','2021-09-09 00:00:00','sgssg'),(7,'teo','2021-09-09 00:00:00','hggghhhg'),(8,'teo','2021-09-09 00:00:00','jjjjjj'),(9,'teo','2021-09-09 00:00:00','hihihihhiihi'),(10,'teo','2021-09-09 00:00:00','jhkhhkhkh'),(11,'teo','2021-09-10 00:00:00','tinh tien'),(12,'teo','2021-09-10 00:00:00','Vung Tau'),(13,'teo','2021-09-10 00:00:00','');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `idproducts` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` float NOT NULL,
  `createdate` date NOT NULL,
  `category_idcategory` char(4) NOT NULL,
  PRIMARY KEY (`idproducts`),
  KEY `category_id_idx` (`category_idcategory`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_idcategory`) REFERENCES `categories` (`idcategories`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'HP 200 PRO','1003.jpg',1000,'2021-04-04','1001'),(2,'Mac Mini M1','1004.jpg',2000,'2021-04-05','1001'),(3,'HP ALO 24','1006.jpg',3000,'2021-04-05','1001'),(4,'Apple mac Mini','1008.jpg',500,'2021-04-05','1001'),(5,'Samsung Galaxy Watch 4','1001.jpg',2000,'2021-05-05','1000'),(6,'Samsung Galaxy Watch 4','1002.jpg',3200,'2021-05-05','1000'),(7,'Apple Watch S6','1034.jpg',2000,'2021-05-05','1000'),(8,'BeU B2','1035.jpg',2000,'2021-07-05','1000'),(9,'Realme Watch 2 Pro','1038.jpg',2200,'2021-07-05','1000'),(10,'iPhone 12','1011.jpg',1505,'2021-07-05','1002'),(11,'Xiaomi Redmi 9T','1012.jpg',1000,'2021-07-05','1002'),(12,'Samsung Galaxy J7 Pro','1059.jpg',5000,'2021-07-05','1002'),(13,'OPPO Reno6 Z','1060.jpg',600,'2021-07-20','1002'),(14,'Vivo Y53s','1069.jpg',900,'2021-07-20','1002'),(15,'Vsmart Star 5','1071.jpg',400,'2021-07-20','1002'),(16,'Canon Powershot','1016.jpg',2000,'2021-08-10','1003'),(17,'Sonu Alpha 6000','1019.jpg',3000,'2021-08-10','1003'),(18,'Canon EOS M200','1020.jpg',2000,'2021-08-10','1003'),(19,'Sony DSC E32 W810','1021.jpg',2000,'2021-08-10','1003');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `idrole` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ADMIN','Quan tri vien'),('PM','Nhan vien'),('USER','Nguoi dung');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-11  8:58:06
