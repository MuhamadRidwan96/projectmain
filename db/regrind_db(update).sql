-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2020 at 11:46 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
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
  `id` char(36) NOT NULL,
  `kode` varchar(20) DEFAULT NULL,
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

INSERT INTO `tab_material` (`id`, `kode`, `mm`, `item`, `warna`, `quantity`, `uom`, `lokasi`) VALUES
('1d4bbg0e-4a5f-14eb-9fb7-50a29d8f2710', 'M-1', 1000002, 'GIL ABS JSR', 'BLACK', 225, 'Kg', 'insert se'),
('1d4cbg0e-4a5f-24eb-9eb7-50a25d8f9010', 'M-2', 10000001, 'GIL HDPE CRATE', 'RED', 200, 'KG', 'FJ-01-01-A');

-- --------------------------------------------------------

--
-- Table structure for table `tab_material_in`
--

CREATE TABLE `tab_material_in` (
  `id` char(36) NOT NULL,
  `kode_material_in` varchar(20) NOT NULL,
  `kode` varchar(20) NOT NULL,
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

INSERT INTO `tab_material_in` (`id`, `kode_material_in`, `kode`, `tanggal`, `shift`, `user`, `mm`, `item`, `warna`, `qty`, `uom`, `lokasi`) VALUES
('1', '', '', '2020-11-20', 'dua', 'MENDOL', 1000001, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
('2', '', '', '2020-11-20', 'SATU', 'MENDOL', 1000001, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
('3', '', '', '2020-11-20', 'DUA', 'ANTON', 1000001, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
('5', '', '', '0000-00-00', '2', 'rdvdvr', 1000002, 'GIL ABS JSR', 'BLACK', 25, 'Kg', 'insert se'),
('ad022c0e-45d0-11eb-867c-a8a1594577cb', 'MI-1', 'M-2', '2020-12-24', '', '', 10000001, 'GIL HDPE CRATE', 'RED', 200, 'KG', 'FJ-01-01-A');

-- --------------------------------------------------------

--
-- Table structure for table `tab_material_out`
--

CREATE TABLE `tab_material_out` (
  `id` char(36) NOT NULL,
  `kode_material_out` varchar(20) NOT NULL,
  `kode` varchar(20) NOT NULL,
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

INSERT INTO `tab_material_out` (`id`, `kode_material_out`, `kode`, `tanggal`, `shift`, `user`, `mm`, `item`, `warna`, `qty`, `uom`, `lokasi`) VALUES
('1', '', '', '2020-11-23', 'satu', 'asep', 0, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
('10', '', '', '2020-11-28', 'dua', 'kubil', 10001101, 'gil gear oil', '', 100, 'kg', 'fj-01-01-a'),
('11', '', '', '2020-11-28', 'dua', 'kubil', 10001101, 'gil gear oil', 'BLACK', 50, 'kg', 'fj-03-03-a'),
('12', '', '', '0000-00-00', '1', 'rdn', 1000002, 'GIL ABS JSR', '', 50, 'Kg', 'fe-11'),
('3', '', '', '2020-11-20', 'DUA', 'ANTON', 1000001, 'GIL HDPE CRATE', 'RED', 100, 'KG', 'FJ-01-01-A'),
('c6fa3aa1-45d2-11eb-867c-a8a1594577cb', 'MO-1', 'M-2', '2020-12-24', '', '', 10000001, 'GIL HDPE CRATE', 'RED', 200, 'KG', 'FJ-01-01-A');

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
  ADD PRIMARY KEY (`mm`,`id`) USING BTREE;

--
-- Indexes for table `tab_material_in`
--
ALTER TABLE `tab_material_in`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tab_material_out`
--
ALTER TABLE `tab_material_out`
  ADD PRIMARY KEY (`id`) USING BTREE,
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
  MODIFY `mm` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10000102;

--
-- AUTO_INCREMENT for table `tab_user`
--
ALTER TABLE `tab_user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
