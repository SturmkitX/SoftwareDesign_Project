-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 26, 2018 at 09:17 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `watch2gether2`
--

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE `album` (
  `id` int(11) NOT NULL,
  `authors` varchar(255) NOT NULL,
  `cover_path` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`id`, `authors`, `cover_path`, `title`) VALUES
(12, 'Future', '/cover/0_Future EP_1526852024804.jpg', 'Future EP');

-- --------------------------------------------------------

--
-- Table structure for table `album_songs`
--

CREATE TABLE `album_songs` (
  `album_id` int(11) NOT NULL,
  `songs_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `album_songs`
--

INSERT INTO `album_songs` (`album_id`, `songs_id`) VALUES
(12, 14),
(12, 15);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(18),
(18),
(18);

-- --------------------------------------------------------

--
-- Table structure for table `media`
--

CREATE TABLE `media` (
  `id` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `media`
--

INSERT INTO `media` (`id`, `duration`, `path`, `title`) VALUES
(14, 100, '/media/0_Jackson_1526883205233.mp3', 'Jackson'),
(15, 200, '/media/0_johan strauss_1526883374911.mp3', 'johan strauss');

-- --------------------------------------------------------

--
-- Table structure for table `media_albums`
--

CREATE TABLE `media_albums` (
  `media_id` int(11) NOT NULL,
  `albums_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `media_albums`
--

INSERT INTO `media_albums` (`media_id`, `albums_id`) VALUES
(14, 12),
(15, 12);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `enabled` tinyint(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `password`, `role`, `enabled`) VALUES
(16, 'bogdi@secure.com', 'Bogdan Security', '$2a$10$K8ID6lF.3h2.BFbFVYJwbuG8SXw4uYmWMaVY.YpuOo/H0biWN5a/S', 'USER', 1),
(17, 'abcd@abcd.com', 'abcd', '$2a$10$tqjSuVSGLUx4mZp8CqXb3.gWBvHMKzCnbNp67k2cSzpzs4kFYVR.i', 'USER', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `album_songs`
--
ALTER TABLE `album_songs`
  ADD PRIMARY KEY (`album_id`,`songs_id`),
  ADD KEY `FK33k3x5shvk3gfsmqbya5kbu1x` (`songs_id`);

--
-- Indexes for table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `media_albums`
--
ALTER TABLE `media_albums`
  ADD PRIMARY KEY (`media_id`,`albums_id`),
  ADD KEY `FKetv0i3xvdjnhtb62q61jpag6a` (`albums_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
