CREATE DATABASE  IF NOT EXISTS `trabalho` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `trabalho`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: trabalho
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `tb_pokemon`
--

DROP TABLE IF EXISTS `tb_pokemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pokemon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pokemon` varchar(70) NOT NULL,
  `tipo` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pokemon`
--

LOCK TABLES `tb_pokemon` WRITE;
/*!40000 ALTER TABLE `tb_pokemon` DISABLE KEYS */;
INSERT INTO `tb_pokemon` VALUES (1,'Pikachu','Elétrico'),(2,'Miraidon','Elétrico'),(3,'Charmander','Fogo'),(4,'Fuecoco','Fogo'),(5,'Miraidon','Elétrico'),(6,'Pidgeotto','Voador'),(7,'Butterfree','Voador'),(8,'Butterfree','Voador'),(9,'Fuecoco','Fogo');
/*!40000 ALTER TABLE `tb_pokemon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pokemon_deletados`
--

DROP TABLE IF EXISTS `tb_pokemon_deletados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pokemon_deletados` (
  `id` int NOT NULL,
  `pokemon` varchar(70) DEFAULT NULL,
  `tipo` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `tb_pokemon_deletados_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_pokemon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pokemon_deletados`
--

LOCK TABLES `tb_pokemon_deletados` WRITE;
/*!40000 ALTER TABLE `tb_pokemon_deletados` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pokemon_deletados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pokemon_eletrico`
--

DROP TABLE IF EXISTS `tb_pokemon_eletrico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pokemon_eletrico` (
  `id` int NOT NULL,
  `pokemon` varchar(70) DEFAULT NULL,
  `tipo` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `tb_pokemon_eletrico_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_pokemon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pokemon_eletrico`
--

LOCK TABLES `tb_pokemon_eletrico` WRITE;
/*!40000 ALTER TABLE `tb_pokemon_eletrico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pokemon_eletrico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pokemon_fogo`
--

DROP TABLE IF EXISTS `tb_pokemon_fogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pokemon_fogo` (
  `id` int NOT NULL,
  `pokemon` varchar(70) DEFAULT NULL,
  `tipo` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `tb_pokemon_fogo_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_pokemon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pokemon_fogo`
--

LOCK TABLES `tb_pokemon_fogo` WRITE;
/*!40000 ALTER TABLE `tb_pokemon_fogo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pokemon_fogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pokemon_totalizador`
--

DROP TABLE IF EXISTS `tb_pokemon_totalizador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pokemon_totalizador` (
  `tipo` varchar(50) NOT NULL,
  `quantidade` int DEFAULT NULL,
  `quantidade_duplicados` int DEFAULT NULL,
  PRIMARY KEY (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pokemon_totalizador`
--

LOCK TABLES `tb_pokemon_totalizador` WRITE;
/*!40000 ALTER TABLE `tb_pokemon_totalizador` DISABLE KEYS */;
INSERT INTO `tb_pokemon_totalizador` VALUES ('Elétrico',3,1),('Fogo',3,1),('Voador',3,1);
/*!40000 ALTER TABLE `tb_pokemon_totalizador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pokemon_voador`
--

DROP TABLE IF EXISTS `tb_pokemon_voador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pokemon_voador` (
  `id` int NOT NULL,
  `pokemon` varchar(70) DEFAULT NULL,
  `tipo` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `tb_pokemon_voador_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_pokemon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pokemon_voador`
--

LOCK TABLES `tb_pokemon_voador` WRITE;
/*!40000 ALTER TABLE `tb_pokemon_voador` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pokemon_voador` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-26 14:21:58
