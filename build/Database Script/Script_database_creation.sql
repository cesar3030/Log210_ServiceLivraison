-- phpMyAdmin SQL Dump
-- version 4.0.6
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 04 Décembre 2014 à 11:40
-- Version du serveur: 5.5.33
-- Version de PHP: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données: `deleveryservice`
--
CREATE DATABASE IF NOT EXISTS `deleveryservice` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `deleveryservice`;

-- --------------------------------------------------------

--
-- Structure de la table `tbmenu`
--

CREATE TABLE `tbmenu` (
  `MEN_idMenu` int(11) NOT NULL AUTO_INCREMENT,
  `MEN_idRestaurant` int(11) NOT NULL,
  `MEN_name` varchar(50) NOT NULL,
  `MEN_description` varchar(500) NOT NULL,
  `MEN_visible` tinyint(1) NOT NULL,
  PRIMARY KEY (`MEN_idMenu`),
  UNIQUE KEY `MEN_idMenu` (`MEN_idMenu`),
  KEY `MEN_idRestaurant` (`MEN_idRestaurant`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `tbmenu`
--

INSERT INTO `tbmenu` (`MEN_idMenu`, `MEN_idRestaurant`, `MEN_name`, `MEN_description`, `MEN_visible`) VALUES
(1, 1, 'Menu gourmet', 'une menu tres complet pour s''en mettre plein la pense.', 1),
(7, 1, 'Menu poisson', '', 1),
(8, 1, 'Menu vraiment en trop', 'Un super menu', 1),
(9, 1, 'Menu viande', '', 1),
(10, 1, 'Menu du soir', 'Menu romantique ', 1),
(11, 1, 'Menu du matin', '', 1);

-- --------------------------------------------------------

--
-- Structure de la table `tborder`
--

CREATE TABLE `tborder` (
  `ORD_idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `ORD_idUserAccount` int(11) NOT NULL COMMENT 'l''identifiant du client',
  `ORD_address` int(11) NOT NULL COMMENT 'id de l''adresse tbother adrress ou 0 pour celle lors de l''inscription  dans userAccont',
  `ORD_confirmationCode` int(11) DEFAULT NULL,
  `ORD_date` datetime NOT NULL COMMENT 'Date et heure de la livraison',
  `ORD_status` int(11) NOT NULL DEFAULT '0',
  `ORD_idDeliveryMan` int(11) DEFAULT NULL COMMENT 'L''id du userAccount étant un livreur (droits 3)',
  `ORD_dateAcceptedByDeliveryMan` datetime DEFAULT NULL,
  PRIMARY KEY (`ORD_idOrder`),
  UNIQUE KEY `ORD_comfimationCode` (`ORD_confirmationCode`),
  KEY `ORD_idUserAccount` (`ORD_idUserAccount`),
  KEY `ORD_address` (`ORD_address`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='table représentant une commande' AUTO_INCREMENT=67 ;

--
-- Contenu de la table `tborder`
--

INSERT INTO `tborder` (`ORD_idOrder`, `ORD_idUserAccount`, `ORD_address`, `ORD_confirmationCode`, `ORD_date`, `ORD_status`, `ORD_idDeliveryMan`, `ORD_dateAcceptedByDeliveryMan`) VALUES
(62, 1, 4, 62, '2014-12-04 10:56:00', 2, NULL, NULL),
(63, 1, 7, 63, '2014-12-18 14:50:00', 1, NULL, NULL),
(64, 1, 0, 64, '2014-12-25 09:05:00', 2, NULL, NULL),
(65, 1, 8, 65, '2014-12-24 22:50:00', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `tborderitem`
--

CREATE TABLE `tborderitem` (
  `ITM_idOrderItem` int(11) NOT NULL AUTO_INCREMENT,
  `ITM_idMeal` int(11) NOT NULL COMMENT 'id du plat voulu',
  `ITM_quantity` int(11) NOT NULL COMMENT 'quantité voulu ',
  `ITM_idOrder` int(11) NOT NULL COMMENT 'id de la commande',
  PRIMARY KEY (`ITM_idOrderItem`),
  KEY `ITM_idMeal` (`ITM_idMeal`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Table qui correspond a un item de la commande, c''est a dire un plat+quantité   ' AUTO_INCREMENT=85 ;

--
-- Contenu de la table `tborderitem`
--

INSERT INTO `tborderitem` (`ITM_idOrderItem`, `ITM_idMeal`, `ITM_quantity`, `ITM_idOrder`) VALUES
(81, 47, 1, 62),
(82, 50, 1, 63),
(83, 52, 1, 64),
(84, 49, 8, 65);

-- --------------------------------------------------------

--
-- Structure de la table `tbotheraddress`
--

CREATE TABLE `tbotheraddress` (
  `ADR_idAddress` int(11) NOT NULL AUTO_INCREMENT,
  `ADR_idUserAccount` int(11) NOT NULL,
  `ADR_address` varchar(500) NOT NULL,
  PRIMARY KEY (`ADR_idAddress`),
  KEY `ADR_idUserAccount` (`ADR_idUserAccount`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `tbotheraddress`
--

INSERT INTO `tbotheraddress` (`ADR_idAddress`, `ADR_idUserAccount`, `ADR_address`) VALUES
(0, 1, 'adresse du compte'),
(3, 1, '427 rue de bienville H2J1T1 montreal quebec'),
(4, 1, '1100 Rue Notre-Dame Ouest, Montreal, Quebec H3C 1K3'),
(7, 1, '356 Rue Roger Pilon Dollard-des-Ormeaux, QC H9G 2S1'),
(8, 1, '1887 59e Avenue Montréal, Québec');

-- --------------------------------------------------------

--
-- Structure de la table `tbplat`
--

CREATE TABLE `tbplat` (
  `PLA_idPlat` int(11) NOT NULL AUTO_INCREMENT,
  `PLA_idMenu` int(11) NOT NULL,
  `PLA_price` int(11) NOT NULL,
  `PLA_name` varchar(50) NOT NULL,
  `PLA_description` varchar(500) NOT NULL,
  PRIMARY KEY (`PLA_idPlat`),
  KEY `PLA_idMenu` (`PLA_idMenu`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Table contenant les différents plats des menus' AUTO_INCREMENT=54 ;

--
-- Contenu de la table `tbplat`
--

INSERT INTO `tbplat` (`PLA_idPlat`, `PLA_idMenu`, `PLA_price`, `PLA_name`, `PLA_description`) VALUES
(47, 1, 47, 'Pates', 'Pates sauce tomate'),
(48, 1, 18, 'Frites', ''),
(49, 1, 99, 'Poulet', 'Poulet en sauce'),
(50, 9, 23, 'Cote de porc', 'Une cote de porc de 6Kg'),
(52, 11, 34, 'Croissant', ''),
(53, 11, 12, 'Chouquettes', '10 chouquettes');

-- --------------------------------------------------------

--
-- Structure de la table `tbrestaurant`
--

CREATE TABLE `tbrestaurant` (
  `RES_idRestaurant` int(11) NOT NULL AUTO_INCREMENT,
  `RES_idUserAccount` int(11) NOT NULL,
  `RES_name` text NOT NULL,
  `RES_address` varchar(200) NOT NULL,
  `RES_phoneNumber` varchar(10) NOT NULL,
  `RES_kindOfFood` varchar(50) NOT NULL,
  `RES_visible` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`RES_idRestaurant`),
  UNIQUE KEY `RES_idRestaurant` (`RES_idRestaurant`),
  KEY `RES_idUserAccount` (`RES_idUserAccount`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `tbrestaurant`
--

INSERT INTO `tbrestaurant` (`RES_idRestaurant`, `RES_idUserAccount`, `RES_name`, `RES_address`, `RES_phoneNumber`, `RES_kindOfFood`, `RES_visible`) VALUES
(1, 3, 'La banquise', '994 rue Rachel H2J 2J3 Montréal', '5148529595', 'Italien', 1),
(12, 3, 'Le Quebecois', '6804 Chemin Kildare cote Saint-Luc, QC H4W 2V4', '5145676543', 'Québecois', 0);

-- --------------------------------------------------------

--
-- Structure de la table `tbuseraccount`
--

CREATE TABLE `tbuseraccount` (
  `USR_idUser` int(11) NOT NULL AUTO_INCREMENT,
  `USR_name` varchar(50) NOT NULL,
  `USR_firstName` varchar(50) NOT NULL,
  `USR_homeAddress` varchar(500) NOT NULL,
  `USR_email` varchar(50) NOT NULL,
  `USR_phoneNumber` varchar(10) NOT NULL,
  `USR_password` varchar(600) NOT NULL,
  `USR_rights` int(11) NOT NULL,
  `USR_birthday` date NOT NULL,
  `USR_visible` int(11) NOT NULL DEFAULT '1',
  `USR_idMainAddress` int(11) NOT NULL DEFAULT '0' COMMENT 'id de l''adresse par defut du compte. Si 0 c''est ll''adresse = homeAddress sinon s''est l''identifiant d''une adresse de la table tbotheraddress',
  PRIMARY KEY (`USR_idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Contenu de la table `tbuseraccount`
--

INSERT INTO `tbuseraccount` (`USR_idUser`, `USR_name`, `USR_firstName`, `USR_homeAddress`, `USR_email`, `USR_phoneNumber`, `USR_password`, `USR_rights`, `USR_birthday`, `USR_visible`, `USR_idMainAddress`) VALUES
(0, 'sans', 'restaurateur', 'sans', 'NE_PAS_SUPPRIMER@gmail.com', '0000000000', 'aaaaaa', 2, '2014-11-11', 1, 0),
(1, 'Paul', '(client) McCartney', '327 Rue Bienville H2J 1T1 Montréal, Québec', 'cesar.jeanroy@gmail.com', '4388315858', 'aaaaaa', 0, '1993-03-30', 1, 8),
(3, 'David', '(Restaurateur) Douillet', '4064 Rue Sainte-Catherine Ouest\nMontréal, Québec H3Z 2Z3', 'rcesar.jeanroy@gmail.com', '5142854152', 'aaaaaa', 1, '1992-01-01', 1, 0),
(11, 'Francois', '(Livreur)Hollande', '4160 Rue Sainte-Catherine Ouest\nMontréal, Québec ', 'lcesar.jeanroy@gmail.com', '4388315858', 'AAAAAA', 3, '1993-03-30', 1, 0),
(12, 'Angus', '(Admin)Young', '4360 Rue Sainte-Catherine Ouest\nMontréal, Québec', 'acesar.jeanroy@gmail.com', '4388315858', 'aaaaaa', 2, '1993-03-30', 1, 0);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `tbmenu`
--
ALTER TABLE `tbmenu`
  ADD CONSTRAINT `tbmenu_ibfk_1` FOREIGN KEY (`MEN_idRestaurant`) REFERENCES `tbrestaurant` (`RES_idRestaurant`);

--
-- Contraintes pour la table `tborder`
--
ALTER TABLE `tborder`
  ADD CONSTRAINT `tborder_ibfk_1` FOREIGN KEY (`ORD_idUserAccount`) REFERENCES `tbuseraccount` (`USR_idUser`);

--
-- Contraintes pour la table `tborderitem`
--
ALTER TABLE `tborderitem`
  ADD CONSTRAINT `tborderitem_ibfk_1` FOREIGN KEY (`ITM_idMeal`) REFERENCES `tbplat` (`PLA_idPlat`);

--
-- Contraintes pour la table `tbotheraddress`
--
ALTER TABLE `tbotheraddress`
  ADD CONSTRAINT `tbotheraddress_ibfk_1` FOREIGN KEY (`ADR_idUserAccount`) REFERENCES `tbuseraccount` (`USR_idUser`);

--
-- Contraintes pour la table `tbplat`
--
ALTER TABLE `tbplat`
  ADD CONSTRAINT `tbplat_ibfk_1` FOREIGN KEY (`PLA_idMenu`) REFERENCES `tbmenu` (`MEN_idMenu`);

--
-- Contraintes pour la table `tbrestaurant`
--
ALTER TABLE `tbrestaurant`
  ADD CONSTRAINT `tbrestaurant_ibfk_1` FOREIGN KEY (`RES_idUserAccount`) REFERENCES `tbuseraccount` (`USR_idUser`);
