-- phpMyAdmin SQL Dump
-- version 4.0.10.18
-- https://www.phpmyadmin.net
--
-- Gazda: localhost:3306
-- Timp de generare: 05 Apr 2017 la 00:09
-- Versiune server: 5.6.35-log
-- Versiune PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Bază de date: `costinso_ip`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Actori`
--

CREATE TABLE IF NOT EXISTS `Actori` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nume` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenume` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `poza` varchar(140) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Comentarii`
--

CREATE TABLE IF NOT EXISTS `Comentarii` (
  `id` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `text` text NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_spectacol` (`id_spectacol`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Critici`
--

CREATE TABLE IF NOT EXISTS `Critici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_spectacol` int(11) NOT NULL,
  `nume_critic` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_spectacol` (`id_spectacol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Joaca`
--

CREATE TABLE IF NOT EXISTS `Joaca` (
  `id_spectacol` int(11) NOT NULL,
  `id_actor` int(11) NOT NULL,
  KEY `id_spectacol` (`id_spectacol`),
  KEY `id_actor` (`id_actor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `PozeSpectacol`
--

CREATE TABLE IF NOT EXISTS `PozeSpectacol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_spectacol` int(11) NOT NULL,
  `locatiepoza` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_spectacol` (`id_spectacol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Review`
--

CREATE TABLE IF NOT EXISTS `Review` (
  `id_user` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `nota` int(1) NOT NULL,
  KEY `id_user` (`id_user`),
  KEY `id_spectacol` (`id_spectacol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `SeTine`
--

CREATE TABLE IF NOT EXISTS `SeTine` (
  `id_teatru` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `id_teatru` (`id_teatru`),
  KEY `id_spectacol` (`id_spectacol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Spectacol`
--

CREATE TABLE IF NOT EXISTS `Spectacol` (
  `id` int(11) NOT NULL,
  `nume` varchar(64) NOT NULL,
  `descriere` text NOT NULL,
  `trailer` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Teatru`
--

CREATE TABLE IF NOT EXISTS `Teatru` (
  `id` int(11) NOT NULL,
  `nume` varchar(64) NOT NULL,
  `descriere` text NOT NULL,
  `locatie` varchar(128) NOT NULL,
  `gps_x` int(64) NOT NULL,
  `gps_y` int(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Useri`
--

CREATE TABLE IF NOT EXISTS `Useri` (
  `id` int(11) NOT NULL,
  `nume` varchar(64) NOT NULL,
  `prenume` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `parola` varchar(128) NOT NULL,
  `userRole` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Restrictii pentru tabele sterse
--

--
-- Restrictii pentru tabele `Comentarii`
--
ALTER TABLE `Comentarii`
  ADD CONSTRAINT `fk_idSpectacol_Comentarii` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idUser_Comentarii` FOREIGN KEY (`id_user`) REFERENCES `Useri` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `Critici`
--
ALTER TABLE `Critici`
  ADD CONSTRAINT `Critici_ibfk_1` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `Joaca`
--
ALTER TABLE `Joaca`
  ADD CONSTRAINT `Joaca_ibfk_1` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Joaca_ibfk_2` FOREIGN KEY (`id_actor`) REFERENCES `Actori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `PozeSpectacol`
--
ALTER TABLE `PozeSpectacol`
  ADD CONSTRAINT `PozeSpectacol_ibfk_1` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `Review`
--
ALTER TABLE `Review`
  ADD CONSTRAINT `fk_idSpectacol` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idUser` FOREIGN KEY (`id_user`) REFERENCES `Useri` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `SeTine`
--
ALTER TABLE `SeTine`
  ADD CONSTRAINT `fk_idSpectacol_SeTine` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idTeatru_SeTine` FOREIGN KEY (`id_teatru`) REFERENCES `Teatru` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
