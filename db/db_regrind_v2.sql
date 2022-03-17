-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2021 at 09:08 AM
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
-- Database: `db_regrind_v2`
--

-- --------------------------------------------------------

--
-- Table structure for table `tab_material`
--

CREATE TABLE `tab_material` (
  `mm` int(8) NOT NULL,
  `item` varchar(100) NOT NULL,
  `color` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tab_material`
--

INSERT INTO `tab_material` (`mm`, `item`, `color`) VALUES
(10001314, 'sdfsdf', 'GREEN'),
(10002121, 'GIL HDPE CRATE BINTANG', 'RED');

-- --------------------------------------------------------

--
-- Table structure for table `tab_user`
--

CREATE TABLE `tab_user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `level` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tab_user`
--

INSERT INTO `tab_user` (`username`, `password`, `full_name`, `phone`, `email`, `level`) VALUES
('rdn', '123456', 'Muhamad Ridwan Ramdani', '81356810', 'muhammadreadone96@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_incoming_material`
--

CREATE TABLE `tb_incoming_material` (
  `id_in` int(11) NOT NULL,
  `date` date NOT NULL,
  `shift` varchar(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `code_location` varchar(20) NOT NULL,
  `mm` int(20) NOT NULL,
  `item` varchar(100) NOT NULL,
  `quantity` int(10) NOT NULL,
  `uom` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_incoming_material`
--

INSERT INTO `tb_incoming_material` (`id_in`, `date`, `shift`, `username`, `code_location`, `mm`, `item`, `quantity`, `uom`) VALUES
(1, '2021-06-20', '1', 'RDN', 'GDC-01-A', 10002121, 'sdfsdf', 1, 'kg'),
(2, '2021-05-10', '2', 'rdn', 'GDC-01-A', 10002121, 'GIL HDPE CRATE BINTANG', 100, 'KG'),
(3, '2021-05-09', '3', 'RDN', 'GDC-01-A', 10001314, 'GIL HDPE', 100, 'KG');

-- --------------------------------------------------------

--
-- Table structure for table `tb_location`
--

CREATE TABLE `tb_location` (
  `code_location` varchar(20) NOT NULL,
  `mm` int(10) NOT NULL,
  `item` varchar(50) NOT NULL,
  `color` varchar(25) NOT NULL,
  `quantity` int(10) NOT NULL,
  `uom` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_location`
--

INSERT INTO `tb_location` (`code_location`, `mm`, `item`, `color`, `quantity`, `uom`) VALUES
('GDC-01-A', 10002121, '', '', 100, ' '),
('GDC-02-A', 10001314, 'GIL HDPE ', '', 100, 'KG'),
('GDC-02-B', 10001314, 'GIL HDPE ', 'RED', 100, 'KG');

-- --------------------------------------------------------

--
-- Table structure for table `tb_used_material`
--

CREATE TABLE `tb_used_material` (
  `id_use` int(11) NOT NULL,
  `date` date NOT NULL,
  `shift` varchar(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `code_location` varchar(20) NOT NULL,
  `mm` int(20) NOT NULL,
  `item` varchar(100) NOT NULL,
  `quantity` int(10) NOT NULL,
  `uom` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_used_material`
--

INSERT INTO `tb_used_material` (`id_use`, `date`, `shift`, `username`, `code_location`, `mm`, `item`, `quantity`, `uom`) VALUES
(1, '2021-06-20', '1', 'RDN', 'GDC-01-A', 10002121, 'sdfsdf', 1, 'kg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tab_material`
--
ALTER TABLE `tab_material`
  ADD PRIMARY KEY (`mm`);

--
-- Indexes for table `tab_user`
--
ALTER TABLE `tab_user`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tb_incoming_material`
--
ALTER TABLE `tb_incoming_material`
  ADD PRIMARY KEY (`id_in`),
  ADD KEY `code_location` (`code_location`),
  ADD KEY `mm` (`mm`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `tb_location`
--
ALTER TABLE `tb_location`
  ADD PRIMARY KEY (`code_location`),
  ADD KEY `mm` (`mm`);

--
-- Indexes for table `tb_used_material`
--
ALTER TABLE `tb_used_material`
  ADD PRIMARY KEY (`id_use`),
  ADD KEY `code_location` (`code_location`),
  ADD KEY `mm` (`mm`),
  ADD KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tab_material`
--
ALTER TABLE `tab_material`
  MODIFY `mm` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10002122;

--
-- AUTO_INCREMENT for table `tb_incoming_material`
--
ALTER TABLE `tb_incoming_material`
  MODIFY `id_in` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_used_material`
--
ALTER TABLE `tb_used_material`
  MODIFY `id_use` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_incoming_material`
--
ALTER TABLE `tb_incoming_material`
  ADD CONSTRAINT `tb_incoming_material_ibfk_1` FOREIGN KEY (`mm`) REFERENCES `tab_material` (`mm`),
  ADD CONSTRAINT `tb_incoming_material_ibfk_2` FOREIGN KEY (`code_location`) REFERENCES `tb_location` (`code_location`),
  ADD CONSTRAINT `tb_incoming_material_ibfk_3` FOREIGN KEY (`username`) REFERENCES `tab_user` (`username`);

--
-- Constraints for table `tb_location`
--
ALTER TABLE `tb_location`
  ADD CONSTRAINT `tb_location_ibfk_1` FOREIGN KEY (`mm`) REFERENCES `tab_material` (`mm`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_used_material`
--
ALTER TABLE `tb_used_material`
  ADD CONSTRAINT `tb_used_material_ibfk_1` FOREIGN KEY (`mm`) REFERENCES `tab_material` (`mm`),
  ADD CONSTRAINT `tb_used_material_ibfk_2` FOREIGN KEY (`code_location`) REFERENCES `tb_location` (`code_location`),
  ADD CONSTRAINT `tb_used_material_ibfk_3` FOREIGN KEY (`username`) REFERENCES `tab_user` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
