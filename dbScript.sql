-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: kalistore
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `addressId` int(11) NOT NULL AUTO_INCREMENT,
  `cityId` int(11) DEFAULT NULL,
  `zipCode` varchar(10) DEFAULT NULL,
  `addressLine` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `mainAddress` int(11) DEFAULT '0',
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,1,'1000','ул. Пършевица 5',5,1),(2,2,'4000','ул. Ала Бала 5',6,1),(3,2,'4000','ул. Някоя си 72',5,0),(4,2,NULL,'First Avenue',20,1),(5,1,'0','ул. Пършевица 5',23,1);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Картички'),(2,'Торти'),(3,'Аксесоари');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `cityId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'София'),(2,'Пловдив'),(3,'Варна'),(4,'Бургас'),(5,'Плевен'),(6,'Стара Загора'),(7,'Шумен');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `clientId` int(11) NOT NULL AUTO_INCREMENT,
  `fullName` varchar(100) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`clientId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Иван Пешев','0988855',5),(16,'Иван Иванов','09888335',5),(20,'Иван Пешев','0988855',23);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliveries`
--

DROP TABLE IF EXISTS `deliveries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliveries` (
  `deliveryId` int(11) NOT NULL AUTO_INCREMENT,
  `deliveryAddressId` int(11) DEFAULT NULL,
  `clientId` int(11) DEFAULT NULL,
  `isToOffice` int(11) NOT NULL DEFAULT '0',
  `differentAddress` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`deliveryId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliveries`
--

LOCK TABLES `deliveries` WRITE;
/*!40000 ALTER TABLE `deliveries` DISABLE KEYS */;
INSERT INTO `deliveries` VALUES (1,3,1,0,0),(10,3,16,0,1),(11,3,16,0,1),(12,1,16,0,0),(13,3,1,0,1),(14,3,1,0,1),(15,3,1,0,1),(16,3,1,0,1),(17,1,1,0,0),(18,1,1,0,0),(19,0,1,1,0),(20,1,1,1,0),(21,1,1,0,0),(22,5,20,0,0);
/*!40000 ALTER TABLE `deliveries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materials` (
  `materialId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`materialId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'Картон'),(2,'Брокат'),(3,'Стикери'),(4,'Панделка'),(5,'Мъниста'),(6,'Въже'),(7,'Висулка');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offices`
--

DROP TABLE IF EXISTS `offices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offices` (
  `officeId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(145) DEFAULT NULL,
  `cityId` int(11) DEFAULT NULL,
  PRIMARY KEY (`officeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offices`
--

LOCK TABLES `offices` WRITE;
/*!40000 ALTER TABLE `offices` DISABLE KEYS */;
INSERT INTO `offices` VALUES (1,'Econt офис 1',1),(2,'Econt офис 2',2),(3,'Econt офис 3',1),(4,'Speedy офис 1',1),(5,'Speedy офис 2',2);
/*!40000 ALTER TABLE `offices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderentries`
--

DROP TABLE IF EXISTS `orderentries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderentries` (
  `orderId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`,`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderentries`
--

LOCK TABLES `orderentries` WRITE;
/*!40000 ALTER TABLE `orderentries` DISABLE KEYS */;
INSERT INTO `orderentries` VALUES (1,1,2),(1,3,1),(2,5,1),(11,1,2),(11,3,1),(12,1,2),(12,3,1),(13,1,2),(13,3,1),(14,1,2),(14,3,1),(15,1,2),(15,3,1),(16,1,2),(16,3,1),(17,1,2),(17,3,1),(18,1,2),(18,3,1),(19,1,2),(19,3,1),(20,1,2),(20,3,1),(21,1,2),(21,3,1),(22,1,2),(22,3,1),(23,1,2),(23,3,1);
/*!40000 ALTER TABLE `orderentries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `deliveryId` int(11) NOT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  `userId` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,33.70,5,4),(2,1,11.70,5,3),(11,10,0.00,5,1),(12,11,33.70,5,1),(13,12,33.70,5,1),(14,13,33.70,5,1),(15,14,33.70,5,1),(16,15,33.70,5,1),(17,16,33.70,5,1),(18,17,33.70,5,1),(19,18,33.70,5,1),(20,19,33.70,5,1),(21,20,33.70,5,1),(22,21,33.70,5,1),(23,22,33.70,23,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `rating` decimal(10,1) DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  `image` varchar(45) DEFAULT NULL,
  `daysToMake` int(11) DEFAULT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Пролетна картичка','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',10.00,4.5,'20см x 10см','kartichka1.jpg',2),(2,'Картичка с рози','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',10.00,4.0,'20см x 10см','kartichka2.jpg',2),(3,'Коледна картичка с елен','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',10.00,4.0,'20см x 10см','kartichka3.jpg',2),(4,'Детска торта от картон','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',30.00,4.0,'40см x 40см x 20см','torta.jpg',5),(5,'Детска диадема','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',8.00,4.1,'стандартен','diadema.jpg',3),(6,'Ръчно изработена картонена торта','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',20.00,4.3,'40см x 40см x 20см','torta2.jpg',5),(7,'Огърлица със сърце','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',7.00,4.8,'стандартен','gerdan.jpg',2),(8,'Гривна със сърце','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',7.00,4.8,'стандартен','grivna.jpg',2),(9,'Обици','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',8.00,5.0,'стандартен','obici.jpg',3);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products2categories`
--

DROP TABLE IF EXISTS `products2categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products2categories` (
  `productId` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  PRIMARY KEY (`productId`,`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products2categories`
--

LOCK TABLES `products2categories` WRITE;
/*!40000 ALTER TABLE `products2categories` DISABLE KEYS */;
INSERT INTO `products2categories` VALUES (1,1),(2,1),(3,1),(4,2),(5,3),(6,2),(7,3),(8,3),(9,3);
/*!40000 ALTER TABLE `products2categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products2materials`
--

DROP TABLE IF EXISTS `products2materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products2materials` (
  `productId` int(11) NOT NULL,
  `materialId` int(11) NOT NULL,
  PRIMARY KEY (`productId`,`materialId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products2materials`
--

LOCK TABLES `products2materials` WRITE;
/*!40000 ALTER TABLE `products2materials` DISABLE KEYS */;
INSERT INTO `products2materials` VALUES (1,1),(2,1),(3,1),(3,2),(4,1),(4,3),(4,4),(5,4),(5,5),(6,1),(6,3),(6,4),(7,6),(7,7),(8,5),(8,7),(9,6),(9,7);
/*!40000 ALTER TABLE `products2materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `reviewId` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `text` varchar(250) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `creationDate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reviewId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,1,5,'Много красива',5,'02.05.2016 11:23'),(2,1,6,'Искам и аз! Страхотна е!',4,'12.06.2016 12:34'),(3,2,6,'Страхотно качество! Препоръчвам, заслужават си всяка стотинка.',4,'10.06.2016 08:04'),(4,3,5,'Бърза и качествена изработка. Препоръчвам!',4,'12.05.2016 01:02'),(5,4,5,'Много хъбав подарък. Заслужава си парите.',5,'03.01.2017 11:23'),(8,4,5,'Хубав продукт, но очаквах повече',3,'08.01.2017 11:23');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(70) NOT NULL,
  `fullName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `fbId` varchar(50) DEFAULT NULL,
  `fbToken` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'pesho','<™	¯ì%5MU®!Y²n8Õ?!s¸ÓÜ>îL~z±Áë‹…>;çºa;1»\\œ6!MÉñJBýz/Û„…kÊ\\DÂ','Pesho Peshev','pesho@test.com','2873278',NULL,NULL),(6,'loshiq','<™	¯ì%5MU®!Y²n8Õ?!s¸ÓÜ>îL~z±Áë‹…>;çºa;1»\\œ6!MÉñJBýz/Û„…kÊ\\DÂ','Losho Loshev','loshiq@test.com','5555555',NULL,NULL),(20,'test','<™	¯ì%5MU®!Y²n8Õ?!s¸ÓÜ>îL~z±Áë‹…>;çºa;1»\\œ6!MÉñJBýz/Û„…kÊ\\DÂ','Losho Loshev',NULL,'5555555',NULL,NULL),(23,'testMainAddress','<™	¯ì%5MU®!Y²n8Õ?!s¸ÓÜ>îL~z±Áë‹…>;çºa;1»\\œ6!MÉñJBýz/Û„…kÊ\\DÂ','No Main Address',NULL,'5555555',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-07 21:05:01
