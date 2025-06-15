-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Cze 14, 2025 at 10:17 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lowisko`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `fedderrods`
--

CREATE TABLE `fedderrods` (
  `id_wedki` int(11) NOT NULL,
  `podajnik` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fedderrods`
--

INSERT INTO `fedderrods` (`id_wedki`, `podajnik`) VALUES
(13, '25g'),
(14, '25g'),
(15, '35g'),
(16, '35g'),
(17, '30g'),
(18, '30g');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `fedder_lakes`
--

CREATE TABLE `fedder_lakes` (
  `id_lowiska` int(11) NOT NULL,
  `czy_mozna_necic_lodka` tinyint(1) DEFAULT NULL,
  `zakazane_smaki_zanety` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fedder_lakes`
--

INSERT INTO `fedder_lakes` (`id_lowiska`, `czy_mozna_necic_lodka`, `zakazane_smaki_zanety`) VALUES
(5, 1, 'orzech tygrysi'),
(6, 1, 'Halibut Czarny'),
(7, 0, '-');

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `fedder_lakes_view`
-- (See below for the actual view)
--
CREATE TABLE `fedder_lakes_view` (
`id_lowiska` int(11)
,`czy_mozna_necic_lodka_display` varchar(3)
,`zakazane_smaki_zanety` varchar(255)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `fishingrods`
--

CREATE TABLE `fishingrods` (
  `id_wedki` int(11) NOT NULL,
  `kolowrotek` varchar(255) DEFAULT NULL,
  `wedzisko` varchar(255) DEFAULT NULL,
  `zylka` varchar(255) DEFAULT NULL,
  `stan` double DEFAULT NULL,
  `rodzaj_wedki` enum('1','2','3') DEFAULT NULL COMMENT '1-SPININGOWA, 2-SPLAWIKOWA, 3-GRUNTOWA',
  `cena_wypozyczenia` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fishingrods`
--

INSERT INTO `fishingrods` (`id_wedki`, `kolowrotek`, `wedzisko`, `zylka`, `stan`, `rodzaj_wedki`, `cena_wypozyczenia`) VALUES
(1, 'Kraken Black Pearl 2000', 'Hype Tackle H5 2,03m 5-21g 2cz S', '0.30mm', 100, '1', 50),
(2, 'Kraken Black Pearl 2000', 'Hype Tackle H5 2,03m 5-21g 2cz S', '0.30mm', 100, '1', 50),
(3, 'Abu Garcia ABUMATIC STX 10', 'Savage Gear SG2 Ultra Light Game', '0.25mm', 100, '1', 55),
(4, 'Abu Garcia ABUMATIC STX 10', 'Savage Gear SG2 Ultra Light Game', '0.25mm', 100, '1', 55),
(5, 'Daiwa 23 Airity PCLT2500-H', 'Dragon Express Spinn 30 245cm 10-30g', '0.35mm', 100, '1', 45),
(6, 'Daiwa 23 Airity PCLT2500-H', 'Dragon Express Spinn 30 245cm 10-30g', '0.35mm', 100, '1', 45),
(7, 'Mikado Carus', 'Jaxon Happy Weekend', '0.20mm', 100, '2', 40),
(8, 'Mikado Carus', 'Jaxon Happy Weekend', '0.20mm', 100, '2', 40),
(9, 'Mikado Trython', 'DAM Nova Expedition', '0.25mm', 100, '2', 45),
(10, 'Mikado Trython', 'DAM Nova Expedition', '0.25mm', 100, '2', 45),
(11, 'Mikado Shinju', 'Tenesa Tele Travel Jaxon', '0.15mm', 100, '2', 50),
(12, 'Mikado Shinju', 'Tenesa Tele Travel Jaxon', '0.15mm', 100, '2', 50),
(13, 'Jaxon Satori FRT', 'Method Feeder Sasori + Avenger', '0.25mm', 100, '3', 50),
(14, 'Jaxon Satori FRT', 'Method Feeder Sasori + Avenger', '0.25mm', 100, '3', 50),
(15, 'Mikado Toxy FR', 'Mikado Ultraviolet Method Feeder', '0.30mm', 100, '3', 40),
(16, 'Mikado Toxy FR', 'Mikado Ultraviolet Method Feeder', '0.30mm', 100, '3', 40),
(17, 'DAM Quick 3 FS', 'Robinson Stinger Feeder', '0.30mm', 100, '3', 45),
(18, 'DAM Quick 3 FS', 'Robinson Stinger Feeder', '0.30mm', 100, '3', 45);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `floatrods`
--

CREATE TABLE `floatrods` (
  `id_wedki` int(11) NOT NULL,
  `splawik` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `floatrods`
--

INSERT INTO `floatrods` (`id_wedki`, `splawik`) VALUES
(7, 'Przezroczysty'),
(8, 'Przezroczysty'),
(9, 'Odległościowy'),
(10, 'Odległościowy'),
(11, 'Karpiowy'),
(12, 'Karpiowy');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `float_lakes`
--

CREATE TABLE `float_lakes` (
  `id_lowiska` int(11) NOT NULL,
  `czy_mozna_lowic_z_lodki` tinyint(1) DEFAULT NULL,
  `czy_haczyki_z_zadziorami` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `float_lakes`
--

INSERT INTO `float_lakes` (`id_lowiska`, `czy_mozna_lowic_z_lodki`, `czy_haczyki_z_zadziorami`) VALUES
(1, 1, 1),
(2, 0, 0),
(3, 0, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `houses`
--

CREATE TABLE `houses` (
  `id_domu` int(11) NOT NULL,
  `ilu_osobowy` int(11) NOT NULL,
  `przy_jakim_lowisku` int(11) DEFAULT NULL,
  `cena_wynajmu` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `houses`
--

INSERT INTO `houses` (`id_domu`, `ilu_osobowy`, `przy_jakim_lowisku`, `cena_wynajmu`) VALUES
(1, 7, 1, 800.00),
(2, 5, 3, 600.00),
(3, 7, 5, 800.00),
(4, 5, 7, 600.00),
(5, 7, 8, 800.00),
(6, 5, 10, 600.00);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lakes`
--

CREATE TABLE `lakes` (
  `id_lowiska` int(11) NOT NULL,
  `powierzchnia` double DEFAULT NULL,
  `max_glebokosc` double DEFAULT NULL,
  `liczba_stanowisk` int(11) DEFAULT NULL,
  `rodzaj_ryb` varchar(255) DEFAULT NULL,
  `rodzaj_lowiska` enum('1','2','3') DEFAULT NULL COMMENT '1 - FloatLakes, 2 - FedderLakes, 3 - SpiningLakes',
  `cena_wstepu` double DEFAULT NULL,
  `godziny_otwarcia` varchar(50) DEFAULT NULL,
  `reserved_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lakes`
--

INSERT INTO `lakes` (`id_lowiska`, `powierzchnia`, `max_glebokosc`, `liczba_stanowisk`, `rodzaj_ryb`, `rodzaj_lowiska`, `cena_wstepu`, `godziny_otwarcia`, `reserved_by`) VALUES
(1, 120.5, 8, 14, 'Leszcz, Wzdręga, Płoć, Kiełb, Karaś', '1', 20, '7-18', NULL),
(2, 100, 6, 12, 'Wzdręga, Płoć, Karp, Lin', '1', 30, '6-19', NULL),
(3, 80, 4, 10, 'Karp, Lin, Amur', '1', 35, '6-20', NULL),
(5, 110, 6, 15, 'Leszcz, Wzdręga, Płoć, Kiełb, Karaś, Karp, Lin', '2', 25, '6-19', NULL),
(6, 90, 5, 11, 'Karp', '2', 35, '5-21', NULL),
(7, 70, 6.5, 9, 'Karp, Lin, Jesiotr, Amur', '2', 40, '0-24', NULL),
(8, 100, 6, 13, 'Sandacz, Szczupak', '3', 30, '5-21', NULL),
(9, 115, 5, 11, 'Okoń', '3', 35, '6-20', NULL),
(10, 80, 4.5, 12, 'Sandacz, Szczupak, Okoń', '3', 40, '5-22', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservation_history`
--

CREATE TABLE `reservation_history` (
  `id` int(11) NOT NULL,
  `id_users` int(11) NOT NULL,
  `id_lowiska` int(11) DEFAULT NULL,
  `id_wedki` int(11) DEFAULT NULL,
  `id_domu` int(11) DEFAULT NULL,
  `cena_wynajmu` double NOT NULL,
  `data_rozpoczecia` datetime NOT NULL,
  `data_zakonczenia` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation_history`
--

INSERT INTO `reservation_history` (`id`, `id_users`, `id_lowiska`, `id_wedki`, `id_domu`, `cena_wynajmu`, `data_rozpoczecia`, `data_zakonczenia`) VALUES
(5, 1, 2, NULL, NULL, 100, '2025-06-14 00:00:00', '2025-06-14 00:00:00'),
(6, 1, 1, NULL, NULL, 100, '2025-06-14 00:00:00', '2025-06-14 00:00:00'),
(7, 1, 2, NULL, NULL, 100, '2025-06-14 00:00:00', '2025-06-14 00:00:00'),
(8, 1, 3, NULL, NULL, 100, '2025-06-14 00:00:00', '2025-06-14 00:00:00'),
(9, 1, 3, NULL, NULL, 100, '2025-06-14 00:00:00', '2025-06-14 00:00:00'),
(10, 1, 3, NULL, NULL, 100, '2025-06-14 00:00:00', '2025-06-14 00:00:00'),
(11, 1, 3, NULL, NULL, 100, '2025-06-14 15:10:25', '2025-06-14 15:12:25'),
(12, 1, 2, NULL, NULL, 100, '2025-06-14 15:14:36', '2025-06-14 15:15:36'),
(13, 1, 3, NULL, NULL, 100, '2025-06-14 15:22:37', '2025-06-14 15:24:37'),
(14, 1, 2, NULL, NULL, 100, '2025-06-14 15:24:13', '2025-06-14 15:26:13'),
(15, 1, 2, NULL, NULL, 100, '2025-06-14 15:25:18', '2025-06-14 15:27:18'),
(16, 1, 2, NULL, NULL, 100, '2025-06-14 15:33:51', '2025-06-14 15:35:51'),
(17, 1, 2, NULL, NULL, 100, '2025-06-14 15:50:08', '2025-06-14 15:51:08'),
(18, 1, 2, NULL, NULL, 0, '2025-06-14 16:00:45', '2025-06-14 16:01:45'),
(19, 1, 2, NULL, NULL, 0, '2025-06-14 16:04:32', '2025-06-14 16:05:32'),
(20, 1, 9, NULL, NULL, 0, '2025-06-14 16:05:02', '2025-06-14 16:06:02'),
(21, 1, 6, NULL, NULL, 0, '2025-06-14 16:09:49', '2025-06-14 16:11:49'),
(23, 1, 2, NULL, NULL, 0, '2025-06-14 16:21:48', '2025-06-14 16:23:48'),
(24, 1, 3, NULL, NULL, 35, '2025-06-14 16:25:39', '2025-06-14 16:27:39'),
(25, 1, NULL, 9, NULL, 45, '2025-06-14 16:37:00', '2025-06-14 16:39:00'),
(26, 1, NULL, 6, NULL, 45, '2025-06-14 16:37:30', '2025-06-14 16:39:30'),
(27, 1, NULL, 2, NULL, 50, '2025-06-14 16:44:58', '2025-06-14 16:46:58'),
(28, 1, NULL, 15, NULL, 40, '2025-06-14 16:45:47', '2025-06-14 16:46:47'),
(29, 1, NULL, 11, NULL, 50, '2025-06-14 16:45:47', '2025-06-14 16:46:47'),
(30, 1, 2, NULL, NULL, 30, '2025-06-14 16:47:24', '2025-06-14 16:48:24'),
(31, 1, 10, NULL, NULL, 40, '2025-06-14 16:47:24', '2025-06-14 16:48:24'),
(32, 1, 6, NULL, NULL, 35, '2025-06-14 16:47:24', '2025-06-14 16:48:24'),
(33, 1, 1, NULL, NULL, 20, '2025-06-14 16:48:55', '2025-06-14 16:49:55'),
(34, 1, 8, NULL, NULL, 30, '2025-06-14 16:48:55', '2025-06-14 16:49:55'),
(35, 1, 5, NULL, NULL, 25, '2025-06-14 16:48:55', '2025-06-14 16:49:55'),
(36, 1, 2, NULL, NULL, 30, '2025-06-14 16:55:00', '2025-06-14 16:56:00'),
(37, 1, 6, NULL, NULL, 35, '2025-06-14 16:55:00', '2025-06-14 16:56:00'),
(38, 1, 8, NULL, NULL, 30, '2025-06-14 16:55:00', '2025-06-14 16:56:00'),
(39, 1, NULL, NULL, 5, 45, '2025-06-14 18:58:14', '2025-06-14 18:59:14'),
(40, 1, 2, NULL, NULL, 30, '2025-06-14 18:59:45', '2025-06-14 19:00:45'),
(41, 1, NULL, 11, NULL, 50, '2025-06-14 19:06:48', '2025-06-14 19:07:48'),
(42, 1, NULL, 1, NULL, 50, '2025-06-14 19:06:48', '2025-06-14 19:07:48'),
(43, 1, NULL, 18, NULL, 45, '2025-06-14 19:06:48', '2025-06-14 19:07:48'),
(44, 1, 2, NULL, NULL, 30, '2025-06-14 19:08:07', '2025-06-14 19:09:07'),
(45, 1, 8, NULL, NULL, 30, '2025-06-14 19:08:07', '2025-06-14 19:09:07'),
(46, 1, 7, NULL, NULL, 40, '2025-06-14 19:08:07', '2025-06-14 19:09:07'),
(47, 1, NULL, NULL, 1, 50, '2025-06-14 19:08:29', '2025-06-14 19:09:29'),
(48, 1, NULL, NULL, 6, 45, '2025-06-14 19:08:29', '2025-06-14 19:09:29'),
(49, 2, 2, NULL, NULL, 30, '2025-06-14 19:17:57', '2025-06-14 19:18:57'),
(50, 2, NULL, NULL, 6, 45, '2025-06-14 19:29:41', '2025-06-14 19:30:41'),
(51, 2, NULL, NULL, 4, 600, '2025-06-14 19:38:30', '2025-06-14 19:39:30'),
(52, 2, 3, NULL, NULL, 35, '2025-06-14 19:51:40', '2025-06-14 19:52:40');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `spiningrods`
--

CREATE TABLE `spiningrods` (
  `id_wedki` int(11) NOT NULL,
  `przyneta` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `spiningrods`
--

INSERT INTO `spiningrods` (`id_wedki`, `przyneta`) VALUES
(1, 'Wobler'),
(2, 'Wobler'),
(3, 'Błystka'),
(4, 'Błystka'),
(5, 'Cannibal'),
(6, 'Cannibal');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `spining_lakes`
--

CREATE TABLE `spining_lakes` (
  `id_lowiska` int(11) NOT NULL,
  `czy_mozna_lowic_z_lodki` tinyint(1) DEFAULT NULL,
  `czy_mozna_zabierac_ryby` tinyint(1) DEFAULT NULL,
  `typ_dna` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `spining_lakes`
--

INSERT INTO `spining_lakes` (`id_lowiska`, `czy_mozna_lowic_z_lodki`, `czy_mozna_zabierac_ryby`, `typ_dna`) VALUES
(8, 1, 0, 'Kamieniste'),
(9, 1, 1, 'Piasczysto-Kamieniste'),
(10, 1, 0, 'Kamienisto-Rośline');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id_users` int(11) NOT NULL,
  `Imię` varchar(50) NOT NULL,
  `Nazwisko` varchar(50) NOT NULL,
  `Login` varchar(50) NOT NULL,
  `Hasło` varchar(255) NOT NULL,
  `nr_telefonu` varchar(15) DEFAULT NULL,
  `e_mail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_users`, `Imię`, `Nazwisko`, `Login`, `Hasło`, `nr_telefonu`, `e_mail`) VALUES
(1, 'Kamil', 'Kopczyk', 'kk134927', 'kamil1212', '786995004', 'kk134927@stud.ur.edu.pl'),
(2, 'Test', 'testowy', 'test', 'test1212', '657876786', 'test@gmail.com');

-- --------------------------------------------------------

--
-- Struktura widoku `fedder_lakes_view`
--
DROP TABLE IF EXISTS `fedder_lakes_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `fedder_lakes_view`  AS SELECT `fedder_lakes`.`id_lowiska` AS `id_lowiska`, CASE WHEN `fedder_lakes`.`czy_mozna_necic_lodka` = 1 THEN 'Tak' WHEN `fedder_lakes`.`czy_mozna_necic_lodka` = 0 THEN 'Nie' ELSE NULL END AS `czy_mozna_necic_lodka_display`, `fedder_lakes`.`zakazane_smaki_zanety` AS `zakazane_smaki_zanety` FROM `fedder_lakes` ;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `fedderrods`
--
ALTER TABLE `fedderrods`
  ADD PRIMARY KEY (`id_wedki`);

--
-- Indeksy dla tabeli `fedder_lakes`
--
ALTER TABLE `fedder_lakes`
  ADD PRIMARY KEY (`id_lowiska`);

--
-- Indeksy dla tabeli `fishingrods`
--
ALTER TABLE `fishingrods`
  ADD PRIMARY KEY (`id_wedki`);

--
-- Indeksy dla tabeli `floatrods`
--
ALTER TABLE `floatrods`
  ADD PRIMARY KEY (`id_wedki`);

--
-- Indeksy dla tabeli `float_lakes`
--
ALTER TABLE `float_lakes`
  ADD PRIMARY KEY (`id_lowiska`);

--
-- Indeksy dla tabeli `houses`
--
ALTER TABLE `houses`
  ADD PRIMARY KEY (`id_domu`),
  ADD KEY `przy_jakim_lowisku` (`przy_jakim_lowisku`);

--
-- Indeksy dla tabeli `lakes`
--
ALTER TABLE `lakes`
  ADD PRIMARY KEY (`id_lowiska`),
  ADD KEY `reserved_by` (`reserved_by`);

--
-- Indeksy dla tabeli `reservation_history`
--
ALTER TABLE `reservation_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_users` (`id_users`),
  ADD KEY `id_lowiska` (`id_lowiska`),
  ADD KEY `id_wedki` (`id_wedki`),
  ADD KEY `id_domu` (`id_domu`);

--
-- Indeksy dla tabeli `spiningrods`
--
ALTER TABLE `spiningrods`
  ADD PRIMARY KEY (`id_wedki`);

--
-- Indeksy dla tabeli `spining_lakes`
--
ALTER TABLE `spining_lakes`
  ADD PRIMARY KEY (`id_lowiska`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_users`),
  ADD UNIQUE KEY `Login` (`Login`),
  ADD UNIQUE KEY `e_mail` (`e_mail`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fishingrods`
--
ALTER TABLE `fishingrods`
  MODIFY `id_wedki` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `houses`
--
ALTER TABLE `houses`
  MODIFY `id_domu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `lakes`
--
ALTER TABLE `lakes`
  MODIFY `id_lowiska` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `reservation_history`
--
ALTER TABLE `reservation_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_users` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fedderrods`
--
ALTER TABLE `fedderrods`
  ADD CONSTRAINT `fedderrods_ibfk_1` FOREIGN KEY (`id_wedki`) REFERENCES `fishingrods` (`id_wedki`) ON DELETE CASCADE;

--
-- Constraints for table `fedder_lakes`
--
ALTER TABLE `fedder_lakes`
  ADD CONSTRAINT `fedder_lakes_ibfk_1` FOREIGN KEY (`id_lowiska`) REFERENCES `lakes` (`id_lowiska`) ON DELETE CASCADE;

--
-- Constraints for table `floatrods`
--
ALTER TABLE `floatrods`
  ADD CONSTRAINT `floatrods_ibfk_1` FOREIGN KEY (`id_wedki`) REFERENCES `fishingrods` (`id_wedki`) ON DELETE CASCADE;

--
-- Constraints for table `float_lakes`
--
ALTER TABLE `float_lakes`
  ADD CONSTRAINT `float_lakes_ibfk_1` FOREIGN KEY (`id_lowiska`) REFERENCES `lakes` (`id_lowiska`) ON DELETE CASCADE;

--
-- Constraints for table `houses`
--
ALTER TABLE `houses`
  ADD CONSTRAINT `houses_ibfk_1` FOREIGN KEY (`przy_jakim_lowisku`) REFERENCES `lakes` (`id_lowiska`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `lakes`
--
ALTER TABLE `lakes`
  ADD CONSTRAINT `lakes_ibfk_1` FOREIGN KEY (`reserved_by`) REFERENCES `users` (`id_users`);

--
-- Constraints for table `reservation_history`
--
ALTER TABLE `reservation_history`
  ADD CONSTRAINT `reservation_history_ibfk_1` FOREIGN KEY (`id_users`) REFERENCES `users` (`id_users`) ON DELETE CASCADE,
  ADD CONSTRAINT `reservation_history_ibfk_2` FOREIGN KEY (`id_lowiska`) REFERENCES `lakes` (`id_lowiska`) ON DELETE SET NULL,
  ADD CONSTRAINT `reservation_history_ibfk_3` FOREIGN KEY (`id_wedki`) REFERENCES `fishingrods` (`id_wedki`) ON DELETE SET NULL,
  ADD CONSTRAINT `reservation_history_ibfk_4` FOREIGN KEY (`id_domu`) REFERENCES `houses` (`id_domu`) ON DELETE SET NULL;

--
-- Constraints for table `spiningrods`
--
ALTER TABLE `spiningrods`
  ADD CONSTRAINT `spiningrods_ibfk_1` FOREIGN KEY (`id_wedki`) REFERENCES `fishingrods` (`id_wedki`) ON DELETE CASCADE;

--
-- Constraints for table `spining_lakes`
--
ALTER TABLE `spining_lakes`
  ADD CONSTRAINT `spining_lakes_ibfk_1` FOREIGN KEY (`id_lowiska`) REFERENCES `lakes` (`id_lowiska`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
