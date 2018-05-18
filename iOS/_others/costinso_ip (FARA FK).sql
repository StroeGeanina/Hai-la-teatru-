-- phpMyAdmin SQL Dump
-- version 4.0.10.18
-- https://www.phpmyadmin.net
--
-- Gazda: localhost:3306
-- Timp de generare: 05 Apr 2017 la 00:00
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
-- Structura de tabel pentru tabelul `Comentarii`
--

CREATE TABLE IF NOT EXISTS `Comentarii` (
  `id` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `text` text NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Review`
--

CREATE TABLE IF NOT EXISTS `Review` (
  `id_user` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `nota` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `SeTine`
--

CREATE TABLE IF NOT EXISTS `SeTine` (
  `id_teatru` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
-- Structura de tabel pentru tabelul `useri`
--

CREATE TABLE IF NOT EXISTS `useri` (
  `id` int(11) NOT NULL,
  `nume` varchar(64) NOT NULL,
  `prenume` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `parola` varchar(128) NOT NULL,
  `userRole` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
