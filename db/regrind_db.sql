-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2020 at 01:51 PM
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
-- Database: `regrind_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tab_material`
--

CREATE TABLE `tab_material` (
  `mm` int(10) NOT NULL,
  `item` varchar(50) NOT NULL,
  `warna` varchar(25) NOT NULL,
  `quantity` int(10) NOT NULL,
  `uom` varchar(5) NOT NULL,
  `lokasi` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tab_material`
--

INSERT INTO `tab_material` (`mm`, `item`, `warna`, `quantity`, `uom`, `lokasi`) VALUES
(1000002, 'GIL ABS JSR', 'BLACK', 225, 'Kg', 'insert se'),
(10000001, 'GIL HDPE CRATE', 'RED', 200, 'KG', 'FJ-01-01-A');

-- --------------------------------------------------------

--
-- Table structure for table `tab_material_in`
--

CREATE TABLE `tab_material_in` (
  `id` int(10) NOT NULL,
  `tanggal` date NOT NULL,
  `shift` varchar(10) NOT NULL,
  `user` varchar(50) NOT NULL,
  `mm` int(10) DEFAULT NULL,
  `item` varchar(50) NOT NULL,
  `warna` varchar(25) NOT NULL,
  `qty` int(10) NOT NULL,
  `uom` varchar(5) NOT NULL,
  `lokasi` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tab_material_in`
--

INSERT INTO `tab_material_in` (`id`, `tanggal`, `shift`, `user`, `mm`, `item`, `warna`, `qty`, `uom`, `lokasi`) VALUES
(1, '2020-11-20', 'dua', 'MENDOL', 1000001, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
(2, '2020-11-20', 'SATU', 'MENDOL', 1000001, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
(3, '2020-11-20', 'DUA', 'ANTON', 1000001, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
(5, '0000-00-00', '2', 'rdvdvr', 1000002, 'GIL ABS JSR', 'BLACK', 25, 'Kg', 'insert se');

-- --------------------------------------------------------

--
-- Table structure for table `tab_material_out`
--

CREATE TABLE `tab_material_out` (
  `id` int(10) NOT NULL,
  `tanggal` date NOT NULL,
  `shift` varchar(10) NOT NULL,
  `user` varchar(50) NOT NULL,
  `mm` int(10) DEFAULT NULL,
  `item` varchar(50) NOT NULL,
  `warna` varchar(25) NOT NULL,
  `qty` int(10) NOT NULL,
  `uom` varchar(5) NOT NULL,
  `lokasi` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tab_material_out`
--

INSERT INTO `tab_material_out` (`id`, `tanggal`, `shift`, `user`, `mm`, `item`, `warna`, `qty`, `uom`, `lokasi`) VALUES
(1, '2020-11-23', 'satu', 'asep', 0, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
(3, '2020-11-20', 'DUA', 'ANTON', 1000001, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
(10, '2020-11-28', 'dua', 'kubil', 10001101, 'gil gear oil', '', 100, 'kg', 'fj-01-01-a'),
(11, '2020-11-28', 'dua', 'kubil', 10001101, 'gil gear oil', 'BLACK', 50, 'kg', 'fj-03-03-a'),
(12, '0000-00-00', '1', 'rdn', 1000002, 'GIL ABS JSR', '', 50, 'Kg', 'fe-11');

-- --------------------------------------------------------

--
-- Table structure for table `tab_user`
--

CREATE TABLE `tab_user` (
  `id` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(10) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tab_user`
--

INSERT INTO `tab_user` (`id`, `username`, `password`, `nama_lengkap`, `phone`, `email`) VALUES
(1, 'Ridwan', '123456', 'Muhamad Ridwan Ramdani', '081356810153', 'muhammadreadone96@gmail.com'),
(5, 'dani', '123456', 'ramdani', '0890909009', 'dani@gmail.com'),
(7, 'asep', '123456', 'asep sunandar sunarya', '081190902', 'asep@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tab_material`
--
ALTER TABLE `tab_material`
  ADD PRIMARY KEY (`mm`);

--
-- Indexes for table `tab_material_in`
--
ALTER TABLE `tab_material_in`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tab_material_out`
--
ALTER TABLE `tab_material_out`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mm` (`mm`);

--
-- Indexes for table `tab_user`
--
ALTER TABLE `tab_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tab_material`
--
ALTER TABLE `tab_material`
  MODIFY `mm` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10000003;

--
-- AUTO_INCREMENT for table `tab_material_in`
--
ALTER TABLE `tab_material_in`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tab_material_out`
--
ALTER TABLE `tab_material_out`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tab_user`
--
ALTER TABLE `tab_user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
