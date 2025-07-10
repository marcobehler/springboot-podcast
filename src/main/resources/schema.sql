-- MySQL dump 10.13  Distrib 9.3.0, for macos15.4 (arm64)
--
-- Host: 127.0.0.1    Database: marcobehler
-- ------------------------------------------------------
-- Server version	8.0.41

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

SET foreign_key_checks = 0;


--
-- Table structure for table `EXCHANGE_RATES`
--

DROP TABLE IF EXISTS `EXCHANGE_RATES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EXCHANGE_RATES` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `from` char(3) COLLATE utf8mb4_unicode_ci NOT NULL,
                                  `to` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
                                  `rate` decimal(30,6) NOT NULL,
                                  `valid_at` datetime NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `rates` (`from`,`to`,`valid_at`)
) ENGINE=InnoDB AUTO_INCREMENT=16448461 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;


--
-- Table structure for table `BOOKS`
--

DROP TABLE IF EXISTS `BOOKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOKS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `publication_date` date DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_updated` date DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `toc` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int DEFAULT NULL,
  `discounted_amount` int DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pages` int DEFAULT NULL,
  `eap` bit(1) DEFAULT b'0',
  `vcs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `twitter_infos` json DEFAULT (json_object()),
  `sample_url` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `download_url` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `screencasts_download_url` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `currency` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'USD',
  `tax_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'ebook',
  `thumbnail` mediumtext COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GUIDES`
--

DROP TABLE IF EXISTS `GUIDES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GUIDES` (
  `id` int NOT NULL AUTO_INCREMENT,
  `slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int DEFAULT NULL,
  `discounted_amount` int DEFAULT NULL,
  `thumbnail` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `publication_date` date DEFAULT NULL,
  `last_updated` date DEFAULT NULL,
  `currency` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'USD',
  `tax_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'ebook',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PRODUCT_PACKAGES`
--

DROP TABLE IF EXISTS `PRODUCT_PACKAGES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCT_PACKAGES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `plus_guide_id` int DEFAULT NULL,
  `guide_id` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uk_product_packages_name` (`name`,`plus_guide_id`,`guide_id`,`book_id`),
  KEY `fk_product_packages_plus_guide` (`plus_guide_id`),
  KEY `fk_product_packages_guide` (`guide_id`),
  KEY `fk_product_packages_book` (`book_id`),
  CONSTRAINT `fk_product_packages_book` FOREIGN KEY (`book_id`) REFERENCES `BOOKS` (`ID`),
  CONSTRAINT `fk_product_packages_guide` FOREIGN KEY (`guide_id`) REFERENCES `GUIDES` (`id`),
  CONSTRAINT `fk_product_packages_plus_guide` FOREIGN KEY (`plus_guide_id`) REFERENCES `PLUS_GUIDES` (`ID`),
  CONSTRAINT `chk_product_packages_refs` CHECK ((((`plus_guide_id` is not null) and (`guide_id` is null) and (`book_id` is null)) or ((`plus_guide_id` is null) and (`guide_id` is not null) and (`book_id` is null)) or ((`plus_guide_id` is null) and (`guide_id` is null) and (`book_id` is not null))))
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PLUS_GUIDES`
--

DROP TABLE IF EXISTS `PLUS_GUIDES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PLUS_GUIDES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ascii_doc` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int DEFAULT NULL,
  `discounted_amount` int DEFAULT NULL,
  `total_eap_slots` smallint DEFAULT '20',
  `taken_eap_slots` smallint DEFAULT '0',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'EAP',
  `twitter_infos` json DEFAULT (json_object()),
  `screencasts_download_url` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `currency` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'USD',
  `tax_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'eservice',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PRODUCT_PRICING`
--

DROP TABLE IF EXISTS `PRODUCT_PRICING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCT_PRICING` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `plus_guide_id` int DEFAULT NULL,
  `guide_id` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `package_id` int NOT NULL,
  `team_size_id` int NOT NULL,
  `amount` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `discounted_amount` int DEFAULT NULL,
  `currency` varchar(4) DEFAULT 'USD',
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uk_product_pricing_product_package_team` (`plus_guide_id`,`guide_id`,`book_id`,`package_id`,`team_size_id`),
  KEY `fk_product_pricing_package` (`package_id`),
  KEY `fk_product_pricing_team_size` (`team_size_id`),
  KEY `fk_product_pricing_book` (`book_id`),
  CONSTRAINT `fk_product_pricing_book` FOREIGN KEY (`book_id`) REFERENCES `BOOKS` (`ID`),
  CONSTRAINT `fk_product_pricing_package` FOREIGN KEY (`package_id`) REFERENCES `PRODUCT_PACKAGES` (`ID`),
  CONSTRAINT `fk_product_pricing_plus_guide` FOREIGN KEY (`plus_guide_id`) REFERENCES `PLUS_GUIDES` (`ID`),
  CONSTRAINT `fk_product_pricing_team_size` FOREIGN KEY (`team_size_id`) REFERENCES `PRODUCT_TEAM_SIZES` (`ID`),
  CONSTRAINT `chk_product_pricing_refs` CHECK ((((`plus_guide_id` is not null) and (`guide_id` is null) and (`book_id` is null)) or ((`plus_guide_id` is null) and (`guide_id` is not null) and (`book_id` is null)) or ((`plus_guide_id` is null) and (`guide_id` is null) and (`book_id` is not null))))
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PRODUCT_TEAM_SIZES`
--

DROP TABLE IF EXISTS `PRODUCT_TEAM_SIZES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCT_TEAM_SIZES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `plus_guide_id` int DEFAULT NULL,
  `guide_id` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uk_product_team_sizes_size` (`name`,`plus_guide_id`,`guide_id`,`book_id`),
  KEY `fk_product_team_sizes_plus_guide` (`plus_guide_id`),
  KEY `fk_product_team_sizes_guide` (`guide_id`),
  KEY `fk_product_team_sizes_book` (`book_id`),
  CONSTRAINT `fk_product_team_sizes_book` FOREIGN KEY (`book_id`) REFERENCES `BOOKS` (`ID`),
  CONSTRAINT `fk_product_team_sizes_guide` FOREIGN KEY (`guide_id`) REFERENCES `GUIDES` (`id`),
  CONSTRAINT `fk_product_team_sizes_plus_guide` FOREIGN KEY (`plus_guide_id`) REFERENCES `PLUS_GUIDES` (`ID`),
  CONSTRAINT `chk_product_team_sizes_refs` CHECK ((((`plus_guide_id` is not null) and (`guide_id` is null) and (`book_id` is null)) or ((`plus_guide_id` is null) and (`guide_id` is not null) and (`book_id` is null)) or ((`plus_guide_id` is null) and (`guide_id` is null) and (`book_id` is not null))))
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TAX_RATES`
--

DROP TABLE IF EXISTS `TAX_RATES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TAX_RATES` (
  `id` int NOT NULL AUTO_INCREMENT,
  `country` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `vat_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NAME` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NOTES` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RATE` double DEFAULT NULL,
  `valid_until` date DEFAULT NULL,
  `tax_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `functional_index` (`tax_code`,`country`,(ifnull(`vat_number`,0)),(ifnull(`valid_until`,0))),
  UNIQUE KEY `functional_index_2` (`tax_code`,`country`,(ifnull(`vat_number`,0)),(ifnull(`valid_until`,0)))
) ENGINE=InnoDB AUTO_INCREMENT=14980 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `REGIONAL_DISCOUNTS`
--

DROP TABLE IF EXISTS `REGIONAL_DISCOUNTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REGIONAL_DISCOUNTS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `percent` smallint NOT NULL,
  `countries` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `continents` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `course_discount_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_discount_valid_until` datetime DEFAULT NULL,
  `max_redemptions` int DEFAULT NULL,
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `course_discount_code` (`course_discount_code`),
  KEY `discounts_fk_courses` (`course_id`),
  CONSTRAINT `discounts_fk_courses` FOREIGN KEY (`course_id`) REFERENCES `COURSES` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1186 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-30 14:19:03
SET foreign_key_checks = 1;