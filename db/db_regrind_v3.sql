-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2021 at 07:43 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_regrind_v3`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_consumption`
--

CREATE TABLE `tb_consumption` (
  `mat_doc` int(10) NOT NULL,
  `id_stock` int(10) NOT NULL,
  `mm` int(10) NOT NULL,
  `item` varchar(100) NOT NULL,
  `quantity` decimal(10,0) NOT NULL,
  `uom` varchar(5) NOT NULL,
  `date` date NOT NULL,
  `nik` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_location`
--

CREATE TABLE `tb_location` (
  `bin` varchar(20) NOT NULL,
  `storage_location` int(11) NOT NULL,
  `plant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_material`
--

CREATE TABLE `tb_material` (
  `mm` int(10) NOT NULL,
  `item` varchar(100) NOT NULL,
  `color` varchar(25) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_migo`
--

CREATE TABLE `tb_migo` (
  `no_doc` int(10) NOT NULL,
  `id_stock` int(10) NOT NULL,
  `mm` int(10) NOT NULL,
  `item` varchar(100) NOT NULL,
  `quantity` decimal(10,0) NOT NULL,
  `uom` varchar(5) NOT NULL,
  `good_recipient` varchar(100) NOT NULL,
  `text` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_stock`
--

CREATE TABLE `tb_stock` (
  `id_stock` int(10) NOT NULL,
  `bin` varchar(20) NOT NULL,
  `mm` int(10) NOT NULL,
  `item` varchar(100) NOT NULL,
  `available_stock` decimal(10,0) NOT NULL,
  `uom` varchar(5) NOT NULL,
  `gr_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_users`
--

CREATE TABLE `tb_users` (
  `nik` int(10) NOT NULL,
  `password` varchar(25) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `phone` int(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `level` int(1) NOT NULL,
  `activated` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_consumption`
--
ALTER TABLE `tb_consumption`
  ADD PRIMARY KEY (`mat_doc`),
  ADD KEY `id_stock` (`id_stock`,`mm`,`nik`),
  ADD KEY `nik` (`nik`),
  ADD KEY `mm` (`mm`);

--
-- Indexes for table `tb_location`
--
ALTER TABLE `tb_location`
  ADD PRIMARY KEY (`bin`);

--
-- Indexes for table `tb_material`
--
ALTER TABLE `tb_material`
  ADD PRIMARY KEY (`mm`);

--
-- Indexes for table `tb_migo`
--
ALTER TABLE `tb_migo`
  ADD PRIMARY KEY (`no_doc`),
  ADD KEY `id_stock` (`id_stock`,`mm`),
  ADD KEY `mm` (`mm`);

--
-- Indexes for table `tb_stock`
--
ALTER TABLE `tb_stock`
  ADD PRIMARY KEY (`id_stock`),
  ADD KEY `mm` (`mm`),
  ADD KEY `bin` (`bin`);

--
-- Indexes for table `tb_users`
--
ALTER TABLE `tb_users`
  ADD PRIMARY KEY (`nik`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_consumption`
--
ALTER TABLE `tb_consumption`
  MODIFY `mat_doc` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_material`
--
ALTER TABLE `tb_material`
  MODIFY `mm` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_migo`
--
ALTER TABLE `tb_migo`
  MODIFY `no_doc` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_stock`
--
ALTER TABLE `tb_stock`
  MODIFY `id_stock` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_users`
--
ALTER TABLE `tb_users`
  MODIFY `nik` int(10) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_consumption`
--
ALTER TABLE `tb_consumption`
  ADD CONSTRAINT `tb_consumption_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `tb_users` (`nik`),
  ADD CONSTRAINT `tb_consumption_ibfk_2` FOREIGN KEY (`id_stock`) REFERENCES `tb_stock` (`id_stock`),
  ADD CONSTRAINT `tb_consumption_ibfk_3` FOREIGN KEY (`mm`) REFERENCES `tb_material` (`mm`);

--
-- Constraints for table `tb_migo`
--
ALTER TABLE `tb_migo`
  ADD CONSTRAINT `tb_migo_ibfk_1` FOREIGN KEY (`id_stock`) REFERENCES `tb_stock` (`id_stock`),
  ADD CONSTRAINT `tb_migo_ibfk_2` FOREIGN KEY (`mm`) REFERENCES `tb_material` (`mm`);

--
-- Constraints for table `tb_stock`
--
ALTER TABLE `tb_stock`
  ADD CONSTRAINT `tb_stock_ibfk_1` FOREIGN KEY (`mm`) REFERENCES `tb_material` (`mm`),
  ADD CONSTRAINT `tb_stock_ibfk_2` FOREIGN KEY (`bin`) REFERENCES `tb_location` (`bin`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
