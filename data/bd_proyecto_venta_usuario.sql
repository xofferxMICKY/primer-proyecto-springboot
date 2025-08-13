-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_proyecto_venta
-- ------------------------------------------------------
-- Server version	8.4.4

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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `id_rol` int DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo` (`correo`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Admin Principal','admin@correo.com','$2a$12$0qbp1sdrSjTz229XuStkVOF2lI0Rg9mftlUjVPZ5pWt5n12/6AYSW',1),(2,'Juan Pérez','juan@correo.com','$2a$12$poBiBFq5am8zH4gQmbzheO7Rzqfb5u554I/NRDyyRfU7.P7aRx5sm',2),(3,'María García','maria@correo.com','$2a$10$WyR0czqPbclcXzXYBtuCOeRuCMoAA6RAUJ0b5OKVGuwNMEvjFO5yS',2),(4,'Pedro Torres','pedro@correo.com','$2a$10$WyR0czqPbclcXzXYBtuCOeRuCMoAA6RAUJ0b5OKVGuwNMEvjFO5yS',2),(5,'Lucía Gómez','lucia@correo.com','$2a$12$poBiBFq5am8zH4gQmbzheO7Rzqfb5u554I/NRDyyRfU7.P7aRx5sm',2),(7,'Offer Esteban','esteban@correo.com','$2a$10$YAtfgAz6bzX0NnBSjjOHL.sEp2w2Zg/OrGN79DdK4ioF4ID6ggjTK',2),(9,'Cesar Salas','salas@correo.com','$2a$10$HKQirEh0ImAdqMB7/450SOBGYaqR9mPYK2PkX0GZNEMJzK7BLmAje',2),(10,'xd xd','xd@correo.com','$2a$10$pPyQbM162Rpfu/JxVRrbSuvB4CSaSqI/iNXQyCEKpj8tev3O0sWJu',2),(11,'esclavo','esclavo@correo.com','$2a$10$C1nt2zGFjzYDbkmAXRzSPOgj2pz3GoZgfQ5O1vrmkoyktwFr29J/a',2),(12,'patron','patron@correo.com','$2a$10$XTp33DudIsriTmLwz9te3emgS8LfWyM.yXIKhbYZ5vV9USqkdFevi',2),(15,'xd','xd123@correo.com','$2a$10$4zfzDzJoTque5rapdgXurO2UztUw6Y6jxTOW8kWeWu1wW8XKWokki',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-13 17:57:54
