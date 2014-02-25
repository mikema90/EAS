CREATE DATABASE  IF NOT EXISTS `eas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `eas`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: eas
-- ------------------------------------------------------
-- Server version	5.5.27

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `work_id` varchar(12) NOT NULL COMMENT 'username (job number)',
  `name` varchar(30) DEFAULT NULL,
  `pwd` varchar(30) DEFAULT NULL,
  `opendeclare` int(1) DEFAULT '0',
  `openreview` int(1) DEFAULT '0',
  PRIMARY KEY (`id`,`work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='table for admin';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin','888888',0,0);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college` (
  `id` int(11) NOT NULL,
  `college_id` varchar(12) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `pwd` varchar(30) DEFAULT NULL,
  `submitted` int(1) DEFAULT '0' COMMENT '是否已经提交教务处',
  PRIMARY KEY (`id`,`college_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (1,'01000','经济与管理学院','888888',0),(2,'02000','建筑与城市规划学院','888888',0),(3,'03000','土木工程学院','888888',0),(4,'04000','机械与能源工程学院','888888',0),(5,'05000','环境科学与工程学院','888888',0),(6,'07900','人文学院','888888',0),(7,'08000','材料科学与工程学院','888888',0),(8,'10000','电子与信息工程学院','888888',0),(9,'11000','外国语学院','888888',0),(10,'12200','数学系','888888',0),(11,'12300','化学系','888888',0),(12,'58000','物理科学与工程学院','888888',0),(13,'13000','国际文化交流学院','888888',0),(14,'14000','医学院','888888',0),(15,'14200','口腔医学院','888888',0),(16,'15000','交通运输工程学院','888888',0),(17,'16000','女子学院','888888',0),(18,'17000','生命科学与技术学院','888888',0),(19,'18000','传播与艺术学院','888888',0),(20,'19000','汽车学院','888888',0),(21,'31000','海洋与地球科学学院','888888',0),(22,'42000','软件学院','888888',0),(23,'43000','铁道与城市轨道交通研究院','888888',0),(24,'44000','电影学院','888888',0),(25,'45000','航空航天与力学学院','888888',0),(26,'49000','职业技术教育学院','888888',0),(27,'50000','网络学院','888888',0),(28,'51000','中德工程学院','888888',0),(29,'52000','法学院','888888',0),(30,'53000','政治与国际关系学院','888888',0),(31,'55000','设计与艺术学院','888888',0),(32,'57000','测绘与地理信息学院','888888',0);
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expert`
--

DROP TABLE IF EXISTS `expert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `work_id` varchar(12) NOT NULL,
  `college_id` varchar(12) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `pwd` varchar(30) DEFAULT NULL,
  `engineering` tinyint(1) DEFAULT '0',
  `science` tinyint(1) DEFAULT '0',
  `medicine` tinyint(1) DEFAULT '0',
  `arts` tinyint(1) DEFAULT '0',
  `english` tinyint(1) DEFAULT '0',
  `deutsch` tinyint(1) DEFAULT '0',
  `japanese` tinyint(1) DEFAULT '0',
  `other` tinyint(1) DEFAULT '0',
  `submitted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`,`work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expert`
--

LOCK TABLES `expert` WRITE;
/*!40000 ALTER TABLE `expert` DISABLE KEYS */;
INSERT INTO `expert` VALUES (1,'1234839','42000','马国来','888888',1,1,0,0,1,0,0,1,0);
/*!40000 ALTER TABLE `expert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mapping`
--

DROP TABLE IF EXISTS `mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapping` (
  `issues` varchar(30) NOT NULL,
  `journal_type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`issues`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mapping the relationship between issues and journal_type';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mapping`
--

LOCK TABLES `mapping` WRITE;
/*!40000 ALTER TABLE `mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college_id` varchar(12) DEFAULT NULL,
  `college_name` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `first_author` varchar(10) DEFAULT NULL,
  `first_author_wid` int(11) DEFAULT NULL,
  `other_authors` varchar(90) DEFAULT NULL,
  `other_authors_wid` varchar(90) DEFAULT NULL,
  `title` varchar(90) DEFAULT NULL,
  `journal` varchar(90) DEFAULT NULL,
  `issues` varchar(30) DEFAULT NULL COMMENT 'ISBN号',
  `journal_type` varchar(30) DEFAULT NULL COMMENT '核心期刊类型',
  `post_date` datetime DEFAULT NULL,
  `language` varchar(10) DEFAULT NULL,
  `pdf_url` varchar(120) DEFAULT NULL,
  `passed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (1,'42000','软件学院','教育理论','马国来',1234839,'尹长青','0000000','大数据概论','软件学报','ISSN-2222-2222','A','2014-01-01 00:00:00','中文','42000\\大数据概论.pdf',0),(2,'19000','汽车学院','教育理论','何杰',1234567,'','','汽车原理','汽车周报','ISSN-1111-1111','A','2013-10-01 00:00:00','中文','19000\\汽车原理.pdf',0);
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewschedule`
--

DROP TABLE IF EXISTS `reviewschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewschedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paper_id` int(11) DEFAULT NULL,
  `expert_work_id` varchar(12) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '1-属于\n2-不属于\n3-无法认定',
  `comment` varchar(180) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewschedule`
--

LOCK TABLES `reviewschedule` WRITE;
/*!40000 ALTER TABLE `reviewschedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviewschedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-25 16:10:49
