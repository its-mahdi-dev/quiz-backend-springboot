-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 31, 2025 at 09:31 PM
-- Server version: 8.0.31
-- PHP Version: 8.1.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `question`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
CREATE TABLE IF NOT EXISTS `answers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NOT NULL,
  `body` text NOT NULL,
  `answer_order` int NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `question_id` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `question_id`, `body`, `answer_order`, `createdAt`, `updatedAt`) VALUES
(1, 1, 'ans3', 3, '2024-11-25 15:25:46', '2024-11-25 15:25:46'),
(2, 1, 'ans1', 1, '2024-11-25 15:25:55', '2024-11-25 15:25:55'),
(6, 1, 'ans4', 4, '2024-11-25 15:26:54', '2024-11-25 15:26:54'),
(7, 1, 'ans2', 2, '2024-11-25 15:26:54', '2024-11-25 15:26:54'),
(8, 2, 'یک', 0, '2024-12-17 14:46:32', '2024-12-17 14:46:32'),
(9, 2, 'دو', 1, '2024-12-17 14:46:32', '2024-12-17 14:46:32'),
(10, 2, 'سه', 2, '2024-12-17 14:46:32', '2024-12-17 14:46:32'),
(11, 2, 'چهار', 3, '2024-12-17 14:46:32', '2024-12-17 14:46:32'),
(12, 3, 'ض1', 0, '2024-12-17 14:47:14', '2024-12-17 14:47:14'),
(13, 3, 'ض2', 1, '2024-12-17 14:47:14', '2024-12-17 14:47:14'),
(14, 3, 'ض3', 2, '2024-12-17 14:47:14', '2024-12-17 14:47:14'),
(15, 3, 'ض4', 3, '2024-12-17 14:47:14', '2024-12-17 14:47:14'),
(16, 4, 'ض1', 0, '2024-12-17 14:52:05', '2024-12-17 14:52:05'),
(17, 4, 'ض2', 1, '2024-12-17 14:52:05', '2024-12-17 14:52:05'),
(18, 4, 'ض3', 2, '2024-12-17 14:52:05', '2024-12-17 14:52:05'),
(19, 4, 'ض4', 3, '2024-12-17 14:52:05', '2024-12-17 14:52:05'),
(20, 5, 'fds', 0, '2024-12-17 15:10:42', '2024-12-17 15:10:42'),
(21, 5, 'fdsa', 1, '2024-12-17 15:10:42', '2024-12-17 15:10:42'),
(22, 5, 'fsa', 2, '2024-12-17 15:10:42', '2024-12-17 15:10:42'),
(23, 5, 'fas', 3, '2024-12-17 15:10:42', '2024-12-17 15:10:42'),
(24, 6, 'بسش', 0, '2024-12-17 21:04:14', '2024-12-17 21:04:14'),
(25, 6, '23ثصی', 1, '2024-12-17 21:04:15', '2024-12-17 21:04:15'),
(26, 6, 'یسش32', 2, '2024-12-17 21:04:15', '2024-12-17 21:04:15'),
(27, 6, '32یش', 3, '2024-12-17 21:04:15', '2024-12-17 21:04:15'),
(28, 7, 'گ1', 0, '2024-12-17 21:05:05', '2024-12-17 21:05:05'),
(29, 7, 'گ2', 1, '2024-12-17 21:05:05', '2024-12-17 21:05:05'),
(30, 7, 'گ3', 2, '2024-12-17 21:05:05', '2024-12-17 21:05:05'),
(31, 7, 'گ4', 3, '2024-12-17 21:05:05', '2024-12-17 21:05:05'),
(32, 8, 'گ1', 0, '2024-12-17 21:22:24', '2024-12-17 21:22:24'),
(33, 8, 'گ2', 1, '2024-12-17 21:22:24', '2024-12-17 21:22:24'),
(34, 8, 'گ3', 2, '2024-12-17 21:22:24', '2024-12-17 21:22:24'),
(35, 8, 'گ4', 3, '2024-12-17 21:22:24', '2024-12-17 21:22:24'),
(36, 9, 'گ1', 0, '2024-12-17 21:47:01', '2024-12-17 21:47:01'),
(37, 9, 'گ2', 1, '2024-12-17 21:47:01', '2024-12-17 21:47:01'),
(38, 9, 'گ3', 2, '2024-12-17 21:47:01', '2024-12-17 21:47:01'),
(39, 9, 'گ4', 3, '2024-12-17 21:47:01', '2024-12-17 21:47:01'),
(40, 10, 'یسش', 0, '2024-12-28 10:37:10', '2024-12-28 10:37:10'),
(41, 10, 'بی', 1, '2024-12-28 10:37:10', '2024-12-28 10:37:10'),
(42, 10, 'یسش', 2, '2024-12-28 10:37:10', '2024-12-28 10:37:10'),
(43, 10, 'بیس', 3, '2024-12-28 10:37:10', '2024-12-28 10:37:10'),
(44, 18, 't1', 0, '2025-01-29 16:22:26', '2025-01-29 16:22:26'),
(45, 18, 't2', 1, '2025-01-29 16:22:26', '2025-01-29 16:22:26'),
(46, 18, 't3', 2, '2025-01-29 16:22:26', '2025-01-29 16:22:26'),
(47, 18, 't4', 3, '2025-01-29 16:22:26', '2025-01-29 16:22:26'),
(48, 19, 't1', 0, '2025-01-29 16:22:27', '2025-01-29 16:22:27'),
(49, 19, 't2', 1, '2025-01-29 16:22:27', '2025-01-29 16:22:27'),
(50, 19, 't3', 2, '2025-01-29 16:22:27', '2025-01-29 16:22:27'),
(51, 19, 't4', 3, '2025-01-29 16:22:27', '2025-01-29 16:22:27'),
(52, 20, 't1', 0, '2025-01-29 16:25:02', '2025-01-29 16:25:02'),
(53, 20, 't2', 1, '2025-01-29 16:25:02', '2025-01-29 16:25:02'),
(54, 20, 't3', 2, '2025-01-29 16:25:02', '2025-01-29 16:25:02'),
(55, 20, 't4', 3, '2025-01-29 16:25:02', '2025-01-29 16:25:02'),
(56, 21, 't1', 0, '2025-01-29 16:25:47', '2025-01-29 16:25:47'),
(57, 21, 't2', 1, '2025-01-29 16:25:47', '2025-01-29 16:25:47'),
(58, 21, 't3', 2, '2025-01-29 16:25:47', '2025-01-29 16:25:47'),
(59, 21, 't4', 3, '2025-01-29 16:25:47', '2025-01-29 16:25:47'),
(60, 22, 't1', 0, '2025-01-29 16:27:16', '2025-01-29 16:27:16'),
(61, 22, 't2', 1, '2025-01-29 16:27:16', '2025-01-29 16:27:16'),
(62, 22, 't3', 2, '2025-01-29 16:27:16', '2025-01-29 16:27:16'),
(63, 22, 't4', 3, '2025-01-29 16:27:16', '2025-01-29 16:27:16'),
(64, 23, 't1', 0, '2025-01-29 16:28:07', '2025-01-29 16:28:07'),
(65, 23, 't2', 1, '2025-01-29 16:28:07', '2025-01-29 16:28:07'),
(66, 23, 't3', 2, '2025-01-29 16:28:07', '2025-01-29 16:28:07'),
(67, 23, 't4', 3, '2025-01-29 16:28:07', '2025-01-29 16:28:07'),
(68, 24, 't1', 0, '2025-01-29 16:30:07', '2025-01-29 16:30:07'),
(69, 24, 't2', 1, '2025-01-29 16:30:07', '2025-01-29 16:30:07'),
(70, 24, 't3', 2, '2025-01-29 16:30:07', '2025-01-29 16:30:07'),
(71, 24, 't4', 3, '2025-01-29 16:30:07', '2025-01-29 16:30:07'),
(72, 25, 't1', 0, '2025-01-29 16:35:08', '2025-01-29 16:35:08'),
(73, 25, 't2', 1, '2025-01-29 16:35:08', '2025-01-29 16:35:08'),
(74, 25, 't3', 2, '2025-01-29 16:35:08', '2025-01-29 16:35:08'),
(75, 25, 't4', 3, '2025-01-29 16:35:08', '2025-01-29 16:35:08'),
(76, 26, 't1', 0, '2025-01-29 19:18:37', '2025-01-29 19:18:37'),
(77, 26, 't2', 1, '2025-01-29 19:18:37', '2025-01-29 19:18:37'),
(78, 26, 't3', 2, '2025-01-29 19:18:37', '2025-01-29 19:18:37'),
(79, 26, 't4', 3, '2025-01-29 19:18:37', '2025-01-29 19:18:37'),
(80, 27, 'sda', 0, '2025-01-31 12:39:31', '2025-01-31 12:39:31'),
(81, 27, '21', 1, '2025-01-31 12:39:31', '2025-01-31 12:39:31'),
(82, 27, 'sa', 2, '2025-01-31 12:39:31', '2025-01-31 12:39:31'),
(83, 27, 'dsa', 3, '2025-01-31 12:39:31', '2025-01-31 12:39:31');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `createdAt`, `updatedAt`) VALUES
(1, 'test_category', '2024-11-25 15:24:34', '2024-11-25 15:24:34'),
(2, 'test', '2024-11-30 17:44:14', '2024-11-30 17:44:14'),
(3, 'dsawq', '2024-12-15 11:00:25', '2024-12-15 11:00:25'),
(4, 'dsa', '2024-12-15 11:00:57', '2024-12-15 11:00:57'),
(5, 'dsaaw1', '2024-12-15 11:01:15', '2024-12-15 11:01:15'),
(6, 'jsjdsa', '2024-12-17 21:06:23', '2024-12-17 21:06:23'),
(7, 'تستی 34', '2024-12-17 21:22:48', '2024-12-17 21:22:48'),
(8, 'اغغغ', '2024-12-17 21:47:25', '2024-12-17 21:47:25'),
(9, 'ds', '2024-12-27 19:50:05', '2024-12-27 19:50:05'),
(10, 'بیسسیش', '2024-12-28 10:37:33', '2024-12-28 10:37:33'),
(11, 'ytsr32', '2025-01-19 01:28:10', '2025-01-19 01:28:10'),
(12, '213rds', '2025-01-31 12:50:48', '2025-01-31 12:50:48');

-- --------------------------------------------------------

--
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
CREATE TABLE IF NOT EXISTS `follows` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `follower_id` bigint NOT NULL,
  `following_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `follows`
--

INSERT INTO `follows` (`id`, `follower_id`, `following_id`) VALUES
(1, 14, 6),
(5, 10, 14),
(6, 8, 14),
(9, 6, 14);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `category_id` int NOT NULL,
  `body` text NOT NULL,
  `correct_answer_id` bigint DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `user_id`, `category_id`, `body`, `correct_answer_id`, `start_time`, `end_time`, `duration`, `createdAt`, `updatedAt`) VALUES
(1, 9, 1, 'test question', 1, '2023-11-02 15:24:44', '2024-11-21 00:00:00', 120, '2024-11-25 15:25:17', '2025-01-29 12:25:45'),
(2, 12, 4, 'سوال تست', 1, '1403-12-01 20:34:16', '0140-12-02 20:34:16', 120, '2024-12-17 14:46:31', '2024-12-17 14:46:31'),
(3, 12, 3, 'یسش', 1, '1402-02-02 20:34:16', '1402-05-05 20:34:16', 120, '2024-12-17 14:47:14', '2024-12-17 14:47:14'),
(4, 12, 3, 'یسش', 18, '1402-02-02 20:34:16', '1402-05-05 20:34:16', 120, '2024-12-17 14:52:05', '2024-12-17 14:52:05'),
(5, 12, 5, '434332', 22, '1402-05-05 20:34:16', '1403-07-07 20:34:16', 58, '2024-12-17 15:10:41', '2024-12-17 15:10:42'),
(6, 12, 3, 'سوال تستی', 25, '2024-10-11 20:30:00', '2024-12-14 20:30:00', 21, '2024-12-17 21:04:14', '2024-12-17 21:04:15'),
(7, 12, 4, 'سوال تستی شماره 2', 30, '2024-12-14 20:30:00', '2025-01-24 11:59:45', 120, '2024-12-17 21:05:05', '2025-01-29 11:59:49'),
(8, 12, 2, 'سوال تستی ارائه', 34, '2024-10-11 20:30:00', '2025-12-04 20:30:00', 120, '2024-12-17 21:22:24', '2024-12-17 21:22:24'),
(9, 12, 5, 'سوال تستی ارائه 2', 37, '2024-10-04 20:30:00', '2025-10-11 20:30:00', 140, '2024-12-17 21:47:01', '2024-12-17 21:47:01'),
(10, 12, 6, 'یشسیشیشسشیص', 41, '2023-08-06 20:30:00', '2026-09-02 20:30:00', 920, '2024-12-28 10:37:09', '2025-01-18 00:14:00'),
(18, 14, 4, 'sausad', NULL, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 16:22:26', '2025-01-29 16:22:26'),
(19, 14, 4, 'sausad', NULL, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 16:22:27', '2025-01-29 16:22:27'),
(20, 14, 4, 'sausad', NULL, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 16:25:02', '2025-01-29 16:25:02'),
(21, 14, 4, 'sausad', NULL, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 16:25:47', '2025-01-29 16:25:47'),
(22, 14, 4, 'sausad', NULL, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 16:27:16', '2025-01-29 16:27:16'),
(23, 14, 4, 'sausad', NULL, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 16:28:07', '2025-01-29 16:28:07'),
(24, 14, 4, 'sausad', NULL, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 16:30:07', '2025-01-29 16:30:07'),
(25, 14, 4, 'sausad', 74, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 16:35:08', '2025-01-29 16:35:08'),
(26, 14, 4, 'sausad', 78, '2024-12-08 23:15:48', '2025-11-09 22:15:00', 100, '2025-01-29 19:18:36', '2025-01-29 19:18:38'),
(27, 14, 10, 'fsa21ersafa', 83, '2024-12-08 21:18:00', '2025-12-08 21:18:00', 180, '2025-01-31 12:39:30', '2025-01-31 12:39:31');

-- --------------------------------------------------------

--
-- Table structure for table `sequelizemeta`
--

DROP TABLE IF EXISTS `sequelizemeta`;
CREATE TABLE IF NOT EXISTS `sequelizemeta` (
  `name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `sequelizemeta`
--

INSERT INTO `sequelizemeta` (`name`) VALUES
('20241124080818-create-users-table.js'),
('20241124080901-create-categories-table.js'),
('20241124080914-create-questions-table.js'),
('20241124081008-create-answers-table.js'),
('20241124081033-create-user_answers-table.js');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(500) NOT NULL,
  `type` enum('designer','player') NOT NULL,
  `score` int NOT NULL DEFAULT '0',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `phone`, `email`, `password`, `type`, `score`, `createdAt`, `updatedAt`) VALUES
(1, 'test', 'sa', '09369488096', 'as@m.c', '$2b$10$Z6JposVQvommc0Xg9Mtnju3zSTcU3Dl1brlw/m1ErwYhd5OSbFRrq', 'player', 0, '2024-11-24 10:12:43', '2024-11-24 10:12:43'),
(6, 'test', 'sa', '09369488097', 'ass@m.c', '$2b$10$gYOkl4B9kN7pM7Lpbj5ixe0dWonOtmZSPMI4B/YokUewdxzUEYsam', 'player', 0, '2024-11-24 10:32:47', '2024-11-24 10:32:47'),
(7, 'test', 'sa', '09369123456', 'assdd@m.c', '$2b$10$BghN2liMZeD3WCy.bs2bN.wqKuVNyU1k0FTtthEaoFgaOIf7DJdTi', 'designer', 0, '2024-11-24 10:57:25', '2024-11-30 21:09:39'),
(8, 'test', 'sa', '09121234567', 'assdddsa@m.c', '$2b$10$seijtcxijrJuq6RZa2U5z.uczLYNqiYEa8w4LIN98qw7uIMSmpbme', 'player', 0, '2024-11-24 10:58:53', '2024-11-24 10:58:53'),
(9, 'test', 'sa', '09121234568', 'assdddsadd@m.c', '$2b$10$tmYlkpNLH.dwsyF5kmeFA.tsFuG0BznB2PZ0dK6WIDALYasxzoLb.', 'player', 0, '2024-11-24 10:59:49', '2024-11-24 10:59:49'),
(10, 'test', 'sa', '09121234569', 'assddsadsadd@m.c', '$2b$10$56aJlurIuFTtTOuhgI2knOy5VM1DTiSvWHSuFF5mu3JuwofiJ5BIy', 'designer', 0, '2024-11-30 17:39:56', '2024-11-30 17:39:56'),
(12, 'مهدی', 'رسولزاده', '12345678912', 'info@itsmahdi.com', '$2b$10$4KX0IoNv0Gxv1diuho/X8.Pc4R5Pp/IkoSUMOIb21kFd0k/k3njOG', 'player', 0, '2024-12-13 19:28:57', '2025-01-17 15:27:54'),
(14, 'test', 'sa', '01234567891', 'assddsadsadd@gmail.com', '$2a$10$IRHa4FPBmRHDwXrcdC3Jue9UfjAQ8reLF46yIKf6pYgoxroNUHhhy', 'player', 0, '2025-01-17 17:02:20', '2025-01-17 17:05:52');

-- --------------------------------------------------------

--
-- Table structure for table `user_answers`
--

DROP TABLE IF EXISTS `user_answers`;
CREATE TABLE IF NOT EXISTS `user_answers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `question_id` int NOT NULL,
  `answer_id` int NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `question_id` (`question_id`),
  KEY `answer_id` (`answer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_answers`
--

INSERT INTO `user_answers` (`id`, `user_id`, `question_id`, `answer_id`, `createdAt`, `updatedAt`) VALUES
(3, 12, 5, 22, '2024-12-17 19:52:36', '2024-12-17 19:52:36'),
(5, 12, 1, 1, '2024-12-17 20:57:10', '2024-12-17 20:57:10'),
(7, 12, 8, 34, '2024-12-17 21:45:04', '2024-12-17 21:45:04'),
(12, 12, 9, 38, '2024-12-28 10:34:19', '2024-12-28 10:34:19'),
(14, 14, 10, 43, '2025-01-18 00:17:02', '2025-01-29 12:26:19'),
(15, 14, 8, 32, '2025-01-31 12:08:18', '2025-01-31 12:08:18');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`);

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `questions_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `user_answers`
--
ALTER TABLE `user_answers`
  ADD CONSTRAINT `user_answers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_answers_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
  ADD CONSTRAINT `user_answers_ibfk_3` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
