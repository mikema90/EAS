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
INSERT INTO `admin` VALUES (1,'admin','admin','77804d2ba1922c33',0,0);
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
  `subject` varchar(15) DEFAULT NULL,
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
INSERT INTO `college` VALUES (1,'01000','经济与管理学院','文管艺术其它','77804d2ba1922c33',0),(2,'02000','建筑与城市规划学院','工学','77804d2ba1922c33',0),(3,'03000','土木工程学院','工学','77804d2ba1922c33',0),(4,'04000','机械与能源工程学院','工学','77804d2ba1922c33',0),(5,'05000','环境科学与工程学院','工学','77804d2ba1922c33',0),(6,'07900','人文学院','文管艺术其它','77804d2ba1922c33',0),(7,'08000','材料科学与工程学院','工学','77804d2ba1922c33',0),(8,'10000','电子与信息工程学院','工学','77804d2ba1922c33',0),(9,'11000','外国语学院','文管艺术其它','77804d2ba1922c33',0),(10,'12200','数学系','理学','77804d2ba1922c33',0),(11,'12300','化学系','理学','77804d2ba1922c33',0),(12,'58000','物理科学与工程学院','理学','77804d2ba1922c33',0),(13,'13000','国际文化交流学院','文管艺术其它','77804d2ba1922c33',0),(14,'14000','医学院','医学生命','77804d2ba1922c33',0),(15,'14200','口腔医学院','医学生命','77804d2ba1922c33',0),(16,'15000','交通运输工程学院','工学','77804d2ba1922c33',0),(17,'16000','女子学院','文管艺术其它','77804d2ba1922c33',0),(18,'17000','生命科学与技术学院','医学生命','77804d2ba1922c33',0),(19,'18000','传播与艺术学院','文管艺术其它','77804d2ba1922c33',0),(20,'19000','汽车学院','工学','77804d2ba1922c33',0),(21,'31000','海洋与地球科学学院','理学','77804d2ba1922c33',0),(22,'42000','软件学院','工学','77804d2ba1922c33',0),(23,'43000','铁道与城市轨道交通研究院','工学','77804d2ba1922c33',0),(25,'45000','航空航天与力学学院','工学','77804d2ba1922c33',0),(26,'49000','职业技术教育学院','文管艺术其它','77804d2ba1922c33',0),(27,'50000','网络学院','文管艺术其它','77804d2ba1922c33',0),(28,'51000','中德工程学院','工学','77804d2ba1922c33',0),(29,'52000','法学院','文管艺术其它','77804d2ba1922c33',0),(30,'53000','政治与国际关系学院','文管艺术其它','77804d2ba1922c33',0),(31,'55000','设计与艺术学院','文管艺术其它','77804d2ba1922c33',0),(32,'57000','测绘与地理信息学院','工学','77804d2ba1922c33',0);
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
  `subject` varchar(45) DEFAULT NULL,
  `language` varchar(15) DEFAULT NULL,
  `submitted` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expert`
--

LOCK TABLES `expert` WRITE;
/*!40000 ALTER TABLE `expert` DISABLE KEYS */;
INSERT INTO `expert` VALUES (1,'1234839','42000','马国来','77804d2ba1922c33','工学,理学,医学生命,文管艺术其他','中文,英文,德文,日文,其他',NULL),(2,'2222222','19000','何杰','77804d2ba1922c33','工学,理学','英文',NULL);
/*!40000 ALTER TABLE `expert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mapping`
--

DROP TABLE IF EXISTS `mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapping` (
  `issues` varchar(120) NOT NULL,
  `journal_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`issues`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mapping the relationship between issues and journal_type';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mapping`
--

LOCK TABLES `mapping` WRITE;
/*!40000 ALTER TABLE `mapping` DISABLE KEYS */;
INSERT INTO `mapping` VALUES ('ACADEMY OF MANAGEMENT JOURNAL','A类核心期刊'),('ACADEMY OF MANAGEMENT PERSPECTIVES','A类核心期刊'),('ACADEMY OF MANAGEMENT REVIEW','A类核心期刊'),('ACCOUNTING REVIEW','A类核心期刊'),('ACCOUNTING, ORGANIZATIONS AND SOCIETY','A类核心期刊'),('ADMINISTRATIVE SCIENCE QUARTERLY','A类核心期刊'),('AMERICAN ECONOMIC REVIEW, WITH JOURNAL OF ECONOMIC LITERATURE','A类核心期刊'),('CALIFORNIA MANAGEMENT REVIEW','A类核心期刊'),('CN-11-1010','A类核心期刊'),('CN-11-1047','A类核心期刊'),('CN-11-1081','A类核心期刊'),('CN-11-1389','A类核心期刊'),('CN-11-1517','A类核心期刊'),('CN-11-1774','A类核心期刊'),('CN-11-1931','A类核心期刊'),('CN-11-2064','A类核心期刊'),('CN-11-2068','A类核心期刊'),('CN-11-2120','A类核心期刊'),('CN-11-2127','A类核心期刊'),('CN-11-2595','A类核心期刊'),('CN-11-2833','A类核心期刊'),('CN-11-3151/TB','A类核心期刊'),('CN-11-4531/N','A类核心期刊'),('CN-11-5142/TU ','A类核心期刊'),('CN-12-1275/G3','A类核心期刊'),('CN-21-1373','A类核心期刊'),('CN-23-1157','A类核心期刊'),('CN-31-1164','教学类核心期刊'),('CN-31-1267','A类核心期刊'),('CN-31-1358','A类核心期刊'),('CN-31-1523/TU','A类核心期刊'),('CN-31-1656/TU','A类核心期刊'),('CN-31-1829','A类核心期刊'),('CN-31-1898/TS','A类核心期刊'),('CN-31-1938/TU','A类核心期刊'),('CN-32-1124','A类核心期刊'),('CN-32-1349','A类核心期刊'),('CN-42-1026','A类核心期刊'),('CN-42-1250','A类核心期刊'),('CN-44-1109','教学类核心期刊'),('CN-44-1361','教学类核心期刊'),('CN-51-1137','A类核心期刊'),('CN-51-1260','A类核心期刊'),('CN-61-1313','A类核心期刊'),('ECONOMETRICA','A类核心期刊'),('ENTREPRENEURSHIP THEORY AND PRACTICE','A类核心期刊'),('HARVARD BUSINESS REVIEW','A类核心期刊'),('HUMAN RESOURCE MANAGEMENT','A类核心期刊'),('INFORMATION SYSTEMS RESEARCH','A类核心期刊'),('INTERNATIONAL JOURNAL OF HUMAN RESOURCE MANAGEMENT','A类核心期刊'),('ISSN-0250-3301','A类核心期刊'),('ISSN-0252-3116','A类核心期刊'),('ISSN-0253-2352','A类核心期刊'),('ISSN-0253-2468','A类核心期刊'),('ISSN-0253-2670','A类核心期刊'),('ISSN-0253-2727','A类核心期刊'),('ISSN-0253-3006','A类核心期刊'),('ISSN-0253-374X','A类核心期刊'),('ISSN-0253-3758','A类核心期刊'),('ISSN-0253-3766','A类核心期刊'),('ISSN-0253-4339','A类核心期刊'),('ISSN-0253-9624','A类核心期刊'),('ISSN-0253-9780 ','A类核心期刊'),('ISSN-0254-0053','A类核心期刊'),('ISSN-0254-1416','A类核心期刊'),('ISSN-0254-1424','A类核心期刊'),('ISSN-0254-1432','A类核心期刊'),('ISSN-0254-1785','A类核心期刊'),('ISSN-0254-1793','A类核心期刊'),('ISSN-0254-5098 ','A类核心期刊'),('ISSN-0254-5101 ','A类核心期刊'),('ISSN-0254-6450','A类核心期刊'),('ISSN-0254-7805','A类核心期刊'),('ISSN-0254-9026','A类核心期刊'),('ISSN-0255-2930','A类核心期刊'),('ISSN-0255-7053','A类核心期刊'),('ISSN-0256-1891','A类核心期刊'),('ISSN-0257-0181','A类核心期刊'),('ISSN-0257-0254','A类核心期刊'),('ISSN-0257-2826','A类核心期刊'),('ISSN-0257-5876','A类核心期刊'),('ISSN-0257-5914','A类核心期刊'),('ISSN-0258-2724','A类核心期刊'),('ISSN-0366-6999','A类核心期刊'),('ISSN-0376-2491 ','A类核心期刊'),('ISSN-0412-3662','A类核心期刊'),('ISSN-0412-4030 ','A类核心期刊'),('ISSN-0412-4081','A类核心期刊'),('ISSN-0439-755X','A类核心期刊'),('ISSN-0448-9365','教学类核心期刊'),('ISSN-0452-8832','A类核心期刊'),('ISSN-0453-2902','A类核心期刊'),('ISSN-0457-6241','A类核心期刊'),('ISSN-0461-6855','A类核心期刊'),('ISSN-0495-5692','出版类核心刊物'),('ISSN-0511-4683','A类核心期刊'),('ISSN-0513-4870','A类核心期刊'),('ISSN-0529-1399','A类核心期刊'),('ISSN-0529-567X ','A类核心期刊'),('ISSN-0529-5807','A类核心期刊'),('ISSN-0529-5815','A类核心期刊'),('ISSN-0577-9154','A类核心期刊'),('ISSN-0578-1310','A类核心期刊'),('ISSN-0578-1426','A类核心期刊'),('ISSN-0578-1949','A类核心期刊'),('ISSN-1000-0054','A类核心期刊'),('ISSN-1000-0135','A类核心期刊'),('ISSN-1000-0216','A类核心期刊'),('ISSN-1000-0429','A类核心期刊'),('ISSN-1000-0879','A类核心期刊'),('ISSN-1000-0887','A类核心期刊'),('ISSN-1000-0992','A类核心期刊'),('ISSN-1000-1026','A类核心期刊'),('ISSN-1000-1031','A类核心期刊'),('ISSN-1000-1182','A类核心期刊'),('ISSN-1000-131X','A类核心期刊'),('ISSN-1000-2472','A类核心期刊'),('ISSN-1000-2995','A类核心期刊'),('ISSN-1000-3355','A类核心期刊'),('ISSN-1000-3363','A类核心期刊'),('ISSN-1000-3673','A类核心期刊'),('ISSN-1000-3770','A类核心期刊'),('ISSN-1000-3894','A类核心期刊'),('ISSN-1000-4203','教学类核心期刊'),('ISSN-1000-4289','A类核心期刊'),('ISSN-1000-436X','A类核心期刊'),('ISSN-1000-4394','教学类核心期刊'),('ISSN-1000-4416','A类核心期刊'),('ISSN-1000-4548','A类核心期刊'),('ISSN-1000-4602','A类核心期刊'),('ISSN-1000-4750','A类核心期刊'),('ISSN-1000-4777','A类核心期刊'),('ISSN-1000-5560','教学类核心期刊'),('ISSN-1000-6648','A类核心期刊'),('ISSN-1000-6664','A类核心期刊'),('ISSN-1000-6672 ','A类核心期刊'),('ISSN-1000-6680','A类核心期刊'),('ISSN-1000-6699','A类核心期刊'),('ISSN-1000-6702','A类核心期刊'),('ISSN-1000-677X','A类核心期刊'),('ISSN-1000-6869','A类核心期刊'),('ISSN-1000-6923','A类核心期刊'),('ISSN-1000-7881','A类核心期刊'),('ISSN-1000-8039','A类核心期刊'),('ISSN-1000-8152','A类核心期刊'),('ISSN-1000-8934 ','A类核心期刊'),('ISSN-1000-8942','A类核心期刊'),('ISSN-1000-8993','A类核心期刊'),('ISSN-1001-0505','A类核心期刊'),('ISSN-1001-0939','A类核心期刊'),('ISSN-1001-1528','A类核心期刊'),('ISSN-1001-1609','A类核心期刊'),('ISSN-1001-1978','A类核心期刊'),('ISSN-1001-201X','A类核心期刊'),('ISSN-1001-2036','A类核心期刊'),('ISSN-1001-2095','A类核心期刊'),('ISSN-1001-2346','A类核心期刊'),('ISSN-1001-3865','A类核心期刊'),('ISSN-1001-4233','教学类核心期刊'),('ISSN-1001-4314','出版类核心刊物'),('ISSN-1001-4497','A类核心期刊'),('ISSN-1001-4519','教学类核心期刊'),('ISSN-1001-4632','A类核心期刊'),('ISSN-1001-5302','A类核心期刊'),('ISSN-1001-5531','A类核心期刊'),('ISSN-1001-6368','A类核心期刊'),('ISSN-1001-6728','A类核心期刊'),('ISSN-1001-6740','A类核心期刊'),('ISSN-1001-6791','A类核心期刊'),('ISSN-1001-6929','A类核心期刊'),('ISSN-1001-7097','A类核心期刊'),('ISSN-1001-7143','出版类核心刊物'),('ISSN-1001-7372','A类核心期刊'),('ISSN-1001-8050','A类核心期刊'),('ISSN-1001-831','A类核心期刊'),('ISSN-1001-8867','A类核心期刊'),('ISSN-1001-9030','A类核心期刊'),('ISSN-1001-9391','A类核心期刊'),('ISSN-1001-960X','教学类核心期刊'),('ISSN-1002-0098','A类核心期刊'),('ISSN-1002-011X','A类核心期刊'),('ISSN-1002-0411','A类核心期刊'),('ISSN-1002-0446','A类核心期刊'),('ISSN-1002-0454','A类核心期刊'),('ISSN-1002-0845','教学类核心期刊'),('ISSN-1002-087X','A类核心期刊'),('ISSN-1002-1027','A类核心期刊'),('ISSN-1002-1329','A类核心期刊'),('ISSN-1002-1566','A类核心期刊'),('ISSN-1002-1620','A类核心期刊'),('ISSN-1002-4166','出版类核心刊物'),('ISSN-1002-4239','A类核心期刊'),('ISSN-1002-4247','A类核心期刊'),('ISSN-1002-4409','教学类核心期刊'),('ISSN-1002-4417','教学类核心期刊'),('ISSN-1002-4565','A类核心期刊'),('ISSN-1002-4808','教学类核心期刊'),('ISSN-1002-4921','A类核心期刊'),('ISSN-1002-4956','实验类核心期刊'),('ISSN-1002-4980','A类核心期刊'),('ISSN-1002-5111','教学类核心期刊'),('ISSN-1002-5502','A类核心期刊'),('ISSN-1002-5685','A类核心期刊'),('ISSN-1002-5707','A类核心期刊'),('ISSN-1002-5731','A类核心期刊'),('ISSN-1002-5766','A类核心期刊'),('ISSN-1002-5936','A类核心期刊'),('ISSN-1002-6045','A类核心期刊'),('ISSN-1002-6711','A类核心期刊'),('ISSN-1002-7246','A类核心期刊'),('ISSN-1002-7963','A类核心期刊'),('ISSN-1002-8064','教学类核心期刊'),('ISSN-1002-8102','A类核心期刊'),('ISSN-1002-8331','A类核心期刊'),('ISSN-1002-834X','A类核心期刊'),('ISSN-1002-8471','A类核心期刊'),('ISSN-1002-848X','A类核心期刊'),('ISSN-1002-8501','A类核心期刊'),('ISSN-1002-8528','A类核心期刊'),('ISSN-1002-8870','A类核心期刊'),('ISSN-1002-896X','A类核心期刊'),('ISSN-1002-9621','A类核心期刊'),('ISSN-1002-9753','A类核心期刊'),('ISSN-1003-0263','A类核心期刊'),('ISSN-1003-0344','A类核心期刊'),('ISSN-1003-160X','教学类核心期刊'),('ISSN-1003-1707','A类核心期刊'),('ISSN-1003-1952','A类核心期刊'),('ISSN-1003-2053','A类核心期刊'),('ISSN-1003-207X','A类核心期刊'),('ISSN-1003-2487','A类核心期刊'),('ISSN-1003-2886','A类核心期刊'),('ISSN-1003-3734','A类核心期刊'),('ISSN-1003-3947','A类核心期刊'),('ISSN-1003-4722','A类核心期刊'),('ISSN-1003-4870','教学类核心期刊'),('ISSN-1003-5370 ','A类核心期刊'),('ISSN-1003-6520','A类核心期刊'),('ISSN-1003-7519','A类核心期刊'),('ISSN-1003-7624','A类核心期刊'),('ISSN-1003-7667','教学类核心期刊'),('ISSN-1003-8493','A类核心期刊'),('ISSN-1003-8930','A类核心期刊'),('ISSN-1003-9279','A类核心期刊'),('ISSN-1003-9406','A类核心期刊'),('ISSN-1004-0013','A类核心期刊'),('ISSN-1004-0994','会计类核心期刊'),('ISSN-1004-3667','教学类核心期刊'),('ISSN-1004-4221','A类核心期刊'),('ISSN-1004-440X','A类核心期刊'),('ISSN-1004-4477','A类核心期刊'),('ISSN-1004-4523','A类核心期刊'),('ISSN-1004-4914','会计类核心期刊'),('ISSN-1004-5961','A类核心期刊'),('ISSN-1004-633X','教学类核心期刊'),('ISSN-1004-8308','A类核心期刊'),('ISSN-1004-9789','A类核心期刊'),('ISSN-1005-0159','A类核心期刊'),('ISSN-1005-0450','教学类核心期刊'),('ISSN-1005-054X','A类核心期刊'),('ISSN-1005-0566','A类核心期刊'),('ISSN-1005-0892','会计类核心期刊'),('ISSN-1005-1015','A类核心期刊'),('ISSN-1005-1201','A类核心期刊'),('ISSN-1005-2577','A类核心期刊'),('ISSN-1005-5827','会计类核心期刊'),('ISSN-1005-684X','A类核心期刊'),('ISSN-1005-829X','A类核心期刊'),('ISSN-1006-1924','A类核心期刊'),('ISSN-1006-480X','A类核心期刊'),('ISSN-1006-6101','A类核心期刊'),('ISSN-1006-7167','实验类核心期刊'),('ISSN-1006-7469','教学类核心期刊'),('ISSN-1006-7876','A类核心期刊'),('ISSN-1006-7884','A类核心期刊'),('ISSN-1006-9550','A类核心期刊'),('ISSN-1007-192X','A类核心期刊'),('ISSN-1007-2020','教学类核心期刊'),('ISSN-1007-3418','A类核心期刊'),('ISSN-1007-3884 ','出版类核心刊物'),('ISSN-1007-449X','A类核心期刊'),('ISSN-1007-4708','A类核心期刊'),('ISSN-1007-5232','A类核心期刊'),('ISSN-1007-5429','A类核心期刊'),('ISSN-1007-5968 ','A类核心期刊'),('ISSN-1007-6239 ','A类核心期刊'),('ISSN-1007-631X','A类核心期刊'),('ISSN-1007-6638','A类核心期刊'),('ISSN-1007-7480','A类核心期刊'),('ISSN-1007-8118','A类核心期刊'),('ISSN-1007-8274','A类核心期刊'),('ISSN-1007-9408 ','A类核心期刊'),('ISSN-1008-1275','A类核心期刊'),('ISSN-1008-1801','A类核心期刊'),('ISSN-1008-3448','A类核心期刊'),('ISSN-1008-5882','A类核心期刊'),('ISSN-1009-2528','A类核心期刊'),('ISSN-1009-2587 ','A类核心期刊'),('ISSN-1009-3060 ','A类核心期刊'),('ISSN-1009-4598','A类核心期刊'),('ISSN-1009-5829','A类核心期刊'),('ISSN-1009-6345','会计类核心期刊'),('ISSN-1009-6906','A类核心期刊'),('ISSN-1009-7716','A类核心期刊'),('ISSN-1009-8402','A类核心期刊'),('ISSN-1009-9158','A类核心期刊'),('ISSN-1104-1109','教学类核心期刊'),('ISSN-1671-0274','A类核心期刊'),('ISSN-1671-0282','A类核心期刊'),('ISSN-1671-0290','A类核心期刊'),('ISSN-1671-0622','会计类核心期刊'),('ISSN-1671-4318','A类核心期刊'),('ISSN-1671-4555','A类核心期刊'),('ISSN-1671-7368','A类核心期刊'),('ISSN-1671-7600','A类核心期刊'),('ISSN-1671-8615','A类核心期刊'),('ISSN-1671-8925','A类核心期刊'),('ISSN-1671-9042','教学类核心期刊'),('ISSN-1671-9220','出版类核心刊物'),('ISSN-1672-0334','A类核心期刊'),('ISSN-1672-4305','实验类核心期刊'),('ISSN-1673-0860','A类核心期刊'),('ISSN-1673-1530','A类核心期刊'),('ISSN-1673-3010','A类核心期刊'),('ISSN-1673-677X','A类核心期刊'),('ISSN-1674-0815','A类核心期刊'),('ISSN-1674-1927','A类核心期刊'),('ISSN-1674-1935','A类核心期刊'),('ISSN-1674-2397','A类核心期刊'),('ISSN-1674-2907','A类核心期刊'),('ISSN-1674-5337','A类核心期刊'),('ISSN-1674-5434','会计类核心期刊'),('ISSN-1674-5809','A类核心期刊'),('ISSN-1674-8417','A类核心期刊'),('JOURNAL OF ACCOUNTING AND ECONOMICS','A类核心期刊'),('JOURNAL OF ACCOUNTING RESEARCH','A类核心期刊'),('JOURNAL OF APPLIED PSYCHOLOGY','A类核心期刊'),('JOURNAL OF BUSINESS ETHICS','A类核心期刊'),('JOURNAL OF BUSINESS VENTURING','A类核心期刊'),('JOURNAL OF CONSUMER RESEARCH','A类核心期刊'),('JOURNAL OF ECONOMIC PERSPECTIVES','A类核心期刊'),('JOURNAL OF FINANCE','A类核心期刊'),('JOURNAL OF FINANCIAL AND QUANTITATIVE ANALYSIS','A类核心期刊'),('JOURNAL OF FINANCIAL ECONOMICS','A类核心期刊'),('JOURNAL OF INTERNATIONAL BUSINESS STUDIES','A类核心期刊'),('JOURNAL OF MARKETING','A类核心期刊'),('JOURNAL OF MARKETING RESEARCH','A类核心期刊'),('JOURNAL OF OPERATIONS MANAGEMENT','A类核心期刊'),('JOURNAL OF POLITICAL ECONOMY','A类核心期刊'),('JOURNAL OF THE AMERICAN STATISTICAL ASSOCIATION','A类核心期刊'),('MANAGEMENT INTERNATIONAL REVIEW','A类核心期刊'),('MANAGEMENT SCIENCE','A类核心期刊'),('MARKETING SCIENCE','A类核心期刊'),('MIS QUARTERLY','A类核心期刊'),('MIT SLOAN MANAGEMENT REVIEW','A类核心期刊'),('OPERATIONS RESEARCH','A类核心期刊'),('ORGANIZATION SCIENCE','A类核心期刊'),('ORGANIZATIONAL BEHAVIOR AND HUMAN DECISION PROCESSES','A类核心期刊'),('RAND JOURNAL OF ECONOMICS','A类核心期刊'),('REVIEW OF FINANCIAL STUDIES','A类核心期刊'),('STRATEGIC MANAGEMENT JOURNAL','A类核心期刊'),('上海建设工程咨询','A类核心期刊'),('中国社会科学','A类核心期刊'),('数量经济技术经济研究','A类核心期刊'),('管理世界','A类核心期刊'),('管理科学学报','A类核心期刊'),('系统工程理论与实践','A类核心期刊'),('经济研究','A类核心期刊'),('﻿ISSN-1002-5731','教学类核心期刊');
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
  `first_author_wid` varchar(10) DEFAULT NULL,
  `other_authors` varchar(120) DEFAULT NULL,
  `other_authors_wid` varchar(120) DEFAULT NULL,
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
INSERT INTO `paper` VALUES (1,'42000','软件学院','教育理论','马国来','1234839','尹长青','0000000','大数据概论','软件学报','ISSN-2222-2222','A','2014-01-01 00:00:00','中文','42000\\大数据概论.pdf',0),(2,'19000','汽车学院','教育理论','何杰','1234567','','','汽车原理','汽车周报','ISSN-1111-1111','A','2013-10-01 00:00:00','中文','19000\\汽车原理.pdf',0),(3,'42000','软件学院','教育理论','张林','1111111','梁爽','0000000','朴素贝叶斯算法优化','模式识别','ISSN-2222-2222','A类核心期刊','2014-01-01 00:00:00','中文','42000\\朴素贝叶斯算法优化.pdf',0),(4,'42000','软件学院','教育理论','xxx','123456','','','pr','软件学报','ISSN-1234-1344','B类核心期刊','2013-08-01 00:00:00','英文','42000\\pr.pdf',0),(5,'42000','软件学院','教育理论','sss','1234','','','xxx','xxx','ISSN-1234-1234','非核心期刊','2014-01-01 00:00:00','中文','42000\\xxx.pdf',0),(6,'42000','软件学院','教育理论','xxx','123','ccc,www',',456','123456','qdqdq','ISSN-2222-2222','非核心期刊','2014-01-01 00:00:00','中文','42000\\123456.pdf',0),(7,'42000','软件学院','教育理论','zhanglin','','马国来,ls','1234839,','Test','上海建设工程','ISSN-2222-2222','非核心期刊','2014-01-01 00:00:00','中文','42000\\Test.pdf',0),(8,'42000','软件学院','教育理论','vvv','1234','','','weq','JOURNAL OF ACCOUNTING AND ECONOM','ISSN-2222-2223','非核心期刊','2014-01-01 00:00:00','中文','42000\\weq.pdf',0),(9,'42000','软件学院','教育实践','mike','1234567','马国来','','network','IEEE','ISSN-2222-2222','非核心期刊','2014-01-01 00:00:00','日文','42000\\network.pdf',0),(10,'43000','铁道与城市轨道交通研究院','教育实践','xxx','xxx','','','ddddd','eeee','ISSN-3333-3333','非核心期刊','2014-01-01 00:00:00','德文','43000\\ddddd.pdf',0),(11,'43000','铁道与城市轨道交通研究院','教育实践','yyy','yyy','','','yyyy','yyyyy','ISSN-5555-5555','非核心期刊','2014-01-01 00:00:00','英文','43000\\yyyy.pdf',0);
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

--
-- Dumping routines for database 'eas'
--
/*!50003 DROP FUNCTION IF EXISTS `func_get_split_string` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `func_get_split_string`(  
f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8
BEGIN  
  declare result varchar(255) default '';  
  set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));  
  return result;  
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `func_get_split_string_total` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `func_get_split_string_total`(  
f_string varchar(1000),f_delimiter varchar(5)  
) RETURNS int(11)
BEGIN  
  declare returnInt int(11);  
  if length(f_delimiter)=2  then  
     return 1+(length(f_string) - length(replace(f_string,f_delimiter,'')))/2;  
  else      
     return 1+(length(f_string) - length(replace(f_string,f_delimiter,'')));  
  end if;  
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-06 13:02:21
