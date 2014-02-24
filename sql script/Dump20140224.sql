CREATE DATABASE  IF NOT EXISTS `eas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `eas`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: eas
-- ------------------------------------------------------
-- Server version	5.6.16-log

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
  `work_id` int(11) NOT NULL COMMENT 'username (job number)',
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
INSERT INTO `admin` VALUES (1,1111,'admin','8888',0,0);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college_id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `pwd` varchar(30) DEFAULT NULL,
  `submitted` int(1) DEFAULT '0' COMMENT '是否已经提交教务处',
  PRIMARY KEY (`id`,`college_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (1,8800,'软件学院','8888',0),(2,7700,'土木学院','8888',0);
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
  `work_id` int(11) NOT NULL,
  `college_id` int(11) DEFAULT NULL,
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
INSERT INTO `expert` VALUES (1,1234839,8800,'马国来','8888',1,1,0,0,1,0,0,1,0);
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
  `college_id` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (4,8800,'软件学院','教育理论','尹长青',1234839,'','','data mining','IEEE','ISSN-2222-2222','A','2014-03-01 00:00:00','英文','8800\\data mining.pdf',0),(5,8800,'软件学院','教育理论','马国来',1234839,'jack','0000','中英文化深入研究','文化周刊','ISSN-2222-2222','A','2013-12-01 00:00:00','英文','8800\\中英文化深入研究.pdf',0),(6,8800,'软件学院','教育理论','刘琴',111,'bbb,ccc','222,333','detch','paper','ISBN-qqqqqqqqqqqqa','A','2014-01-01 00:00:00','中文','8800\\detch.pdf',0),(7,8800,'软件学院','教育理论','尹长青',1234839,'','','data analysis','IEEE','ISSN-2222-2222','A','2014-03-01 00:00:00','英文','8800\\data analysis.pdf',0),(8,7700,'土木学院','教育理论','严峻',1234839,'jack','0000','土木研究','土木周刊','ISSN-2222-2222','A','2013-12-01 00:00:00','英文','8800\\土木研究.pdf',0),(10,7700,'土木学院','教育实践','土老师',22222222,'穆老师','33333333','土木学','土木周刊','CN-22-2222','A','2014-01-01 00:00:00','中文','7700\\土木学.pdf',0),(11,7700,'土木学院','教育理论','xxx',1234,'','','xxx','xxx','ISBN-wwwwwwwwwwwww','A','2014-01-01 00:00:00','中文','7700\\xxx.pdf',0);
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
  `expert_work_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '1-属于\n2-不属于\n3-无法认定',
  `comment` varchar(180) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewschedule`
--

LOCK TABLES `reviewschedule` WRITE;
/*!40000 ALTER TABLE `reviewschedule` DISABLE KEYS */;
INSERT INTO `reviewschedule` VALUES (1,1,1234839,2,'it is not good enough');
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

-- Dump completed on 2014-02-24 23:32:26
