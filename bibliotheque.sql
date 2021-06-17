-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 06 déc. 2020 à 14:17
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bibliotheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `idLivre` int(11) DEFAULT NULL,
  `idLecteur` int(11) DEFAULT NULL,
  `note` int(11) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`idLivre`, `idLecteur`, `note`, `commentaire`) VALUES
(1, 4, 8, 'test'),
(1, 4, 10, 'te'),
(1, 4, 2, 'e'),
(1, 4, 4, 'dd'),
(1, 4, 7, 'VRAIMENT'),
(1, 4, 1, 'P');

-- --------------------------------------------------------

--
-- Structure de la table `bibliotheque`
--

CREATE TABLE `bibliotheque` (
  `idBibliotheque` int(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `idManager` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bibliotheque`
--

INSERT INTO `bibliotheque` (`idBibliotheque`, `nom`, `adresse`, `idManager`) VALUES
(1, 'Evere', 'rue bibli', 2),
(2, 'Laeken', 'rue bi', 5),
(3, 'Anderlecht', 'rue de la biblio', 3);

-- --------------------------------------------------------

--
-- Structure de la table `exemplaire`
--

CREATE TABLE `exemplaire` (
  `idExemplaire` int(255) NOT NULL,
  `idDuLivre` int(255) NOT NULL,
  `type` varchar(1) NOT NULL,
  `disponible` tinyint(255) DEFAULT NULL,
  `vérifié` tinyint(255) DEFAULT NULL,
  `rendu` tinyint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `exemplaire`
--

INSERT INTO `exemplaire` (`idExemplaire`, `idDuLivre`, `type`, `disponible`, `vérifié`, `rendu`) VALUES
(1, 1, 'P', 0, NULL, NULL),
(2, 1, 'N', 0, NULL, NULL),
(3, 1, 'N', 0, NULL, NULL),
(4, 2, 'P', 1, NULL, NULL),
(5, 2, 'N', 1, NULL, NULL),
(6, 5, 'N', 1, NULL, NULL),
(7, 3, 'P', 1, NULL, NULL),
(8, 3, 'N', 1, NULL, NULL),
(9, 5, 'P', 1, NULL, NULL),
(10, 4, 'P', 0, NULL, NULL),
(11, 3, 'P', 1, NULL, NULL),
(12, 3, 'N', 1, NULL, NULL),
(13, 5, 'N', 0, NULL, NULL),
(14, 4, 'P', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `idUser` int(255) NOT NULL,
  `idBibliotheque` int(255) NOT NULL,
  `cotisationPaye` tinyint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`idUser`, `idBibliotheque`, `cotisationPaye`) VALUES
(4, 1, NULL),
(1, 1, NULL),
(4, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `idLivre` int(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) NOT NULL,
  `edition` varchar(255) NOT NULL,
  `nbPage` int(255) NOT NULL,
  `note` float(255,0) DEFAULT NULL,
  `prix` float(255,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`idLivre`, `titre`, `auteur`, `edition`, `nbPage`, `note`, `prix`) VALUES
(1, 'Livre 1', 'Auteur1', 'Edition1', 100, 5, 1),
(2, 'Livre2', 'auteur2', 'edition2', 200, NULL, 2),
(3, 'Livre3', 'auteur3', 'edition3', 300, NULL, 3),
(4, 'Livre4', 'auteur4', 'edition4', 400, NULL, 4),
(5, 'LivreTest', 'AuteurTest', 'EditionTest', 66, NULL, 7);

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `idLocation` int(255) NOT NULL,
  `idExemplaire` int(255) NOT NULL,
  `idUser` int(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`idLocation`, `idExemplaire`, `idUser`, `date`) VALUES
(3, 1, 1, '2020-11-29'),
(4, 5, 1, '2020-12-10'),
(6, 6, 1, '2021-03-29'),
(7, 1, 1, '2021-05-09'),
(8, 1, 1, '2020-11-29'),
(9, 1, 1, '2020-11-29'),
(10, 1, 1, '2020-11-29'),
(11, 1, 4, '2020-11-29'),
(12, 1, 4, '2020-11-29'),
(13, 1, 4, '2020-11-29'),
(14, 1, 4, '2020-11-29'),
(15, 1, 4, '2020-11-29'),
(16, 1, 4, '2020-11-29'),
(17, 1, 4, '2020-11-29'),
(18, 7, 4, '0007-07-14'),
(19, 5, 4, '2022-07-12'),
(20, 7, 4, '2020-12-18'),
(21, 4, 2, '2020-12-10'),
(22, 4, 3, '2021-07-22'),
(23, 8, 3, '2020-12-25');

-- --------------------------------------------------------

--
-- Structure de la table `possede`
--

CREATE TABLE `possede` (
  `idExemplaire` int(11) NOT NULL,
  `idBibliotheque` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `possede`
--

INSERT INTO `possede` (`idExemplaire`, `idBibliotheque`) VALUES
(2, 1),
(3, 1),
(4, 1),
(6, 1),
(7, 1),
(8, 2),
(1, 2),
(5, 2),
(11, 1),
(12, 2),
(14, 2),
(9, 2);

-- --------------------------------------------------------

--
-- Structure de la table `qr`
--

CREATE TABLE `qr` (
  `idQuestion` int(255) NOT NULL,
  `idAuteur` int(255) NOT NULL,
  `idBibliotecaire` int(255) DEFAULT NULL,
  `question` varchar(255) NOT NULL,
  `reponse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idReservation` int(255) NOT NULL,
  `idExemplaire` int(255) NOT NULL,
  `idUser` int(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `idRole` int(255) NOT NULL,
  `nomRole` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`idRole`, `nomRole`) VALUES
(1, 'manager general'),
(2, 'bibliotecaire manager'),
(3, 'bibliotecaire'),
(4, 'lecteur');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `idUser` int(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `idRole` int(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `amende` float(255,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `nom`, `prenom`, `adresse`, `telephone`, `email`, `idRole`, `login`, `password`, `amende`) VALUES
(1, 'manager', 'general', 'rue b', '0000', 'mana@gene.ral', 1, 'managerG', 'root', NULL),
(2, 'manager', 'bibliotecaire', 'rue m', '1111', 'mana@ger.bi', 2, 'managerB', 'root', NULL),
(3, 'bibliotecaire', 'debibli', 'rue d', '2222', 'biblio@tecaire.bi', 3, 'bibliotecaire', 'root', NULL),
(4, 'lecteur', 'hani', 'rue l', '333', 'lec@teur.bi', 4, 'lecteur', 'root', NULL),
(5, 'manager2', 'bibliotecaire2', 'rue m2', '444', 'man@bi', 2, 'managerB2', 'root', NULL),
(11, 'efjoe', 'jordan', 'dajnd', '0465157358', 'jordan@gmail.com', 4, 'dcdzc', 'zcezc', NULL),
(12, 'efjoe', 'jordan', 'dajnd', '0465157358', 'jordan@gmail.com', 4, 'evrev', 'eveve', NULL),
(13, 'efjoe', 'jordan', 'dajnd', '0465157358', 'jordan@gmail.com', 4, 'dv', 'sdvd', NULL),
(14, 'efjoe', 'jordan', 'dajnd', '0465157358', 'jordan@gmail.com', 4, 'aaaaaaa', 'aaaaa', NULL),
(15, 'efjoe', 'dczczc', 'dajnd', '0465157358', 'jordan@gmail.com', 4, 'bbbbbbb', 'bbbbbbb', NULL),
(16, 'efjoe', 'dczczc', 'dajnd', '0465157358', 'jordan@gmail.com', 4, 'bbbbbbbdz', 'dczcz', NULL),
(17, 'efjoe', 'dczczc', 'dajnd', '0465157358', 'jordan@gmail.com', 4, 'bbbbbbbdz', 'dczcez', NULL),
(18, 'efjoe', 'jordan', 'dajnd', '0465157358', 'jordan@gmail.com', 4, 'grbve', 'evee', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  ADD PRIMARY KEY (`idBibliotheque`),
  ADD KEY `idMan` (`idManager`);

--
-- Index pour la table `exemplaire`
--
ALTER TABLE `exemplaire`
  ADD PRIMARY KEY (`idExemplaire`),
  ADD KEY `idLi` (`idDuLivre`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD KEY `idUserInscr` (`idUser`),
  ADD KEY `idBibliot` (`idBibliotheque`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`idLivre`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`idLocation`),
  ADD KEY `idExem` (`idExemplaire`),
  ADD KEY `idUs` (`idUser`);

--
-- Index pour la table `possede`
--
ALTER TABLE `possede`
  ADD KEY `idExemp` (`idExemplaire`),
  ADD KEY `idBibli` (`idBibliotheque`);

--
-- Index pour la table `qr`
--
ALTER TABLE `qr`
  ADD PRIMARY KEY (`idQuestion`),
  ADD KEY `idAuteur` (`idAuteur`),
  ADD KEY `idBibliotecaire` (`idBibliotecaire`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idReservation`),
  ADD KEY `idEx` (`idExemplaire`),
  ADD KEY `idU` (`idUser`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`idRole`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`),
  ADD KEY `idRole` (`idRole`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  MODIFY `idBibliotheque` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `exemplaire`
--
ALTER TABLE `exemplaire`
  MODIFY `idExemplaire` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `idLivre` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `location`
--
ALTER TABLE `location`
  MODIFY `idLocation` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `qr`
--
ALTER TABLE `qr`
  MODIFY `idQuestion` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `idRole` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  ADD CONSTRAINT `idMan` FOREIGN KEY (`idManager`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `exemplaire`
--
ALTER TABLE `exemplaire`
  ADD CONSTRAINT `idLi` FOREIGN KEY (`idDuLivre`) REFERENCES `livre` (`idLivre`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `idBibliot` FOREIGN KEY (`idBibliotheque`) REFERENCES `bibliotheque` (`idBibliotheque`),
  ADD CONSTRAINT `idUserInscr` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `idExem` FOREIGN KEY (`idExemplaire`) REFERENCES `exemplaire` (`idExemplaire`),
  ADD CONSTRAINT `idUs` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `possede`
--
ALTER TABLE `possede`
  ADD CONSTRAINT `idBibli` FOREIGN KEY (`idBibliotheque`) REFERENCES `bibliotheque` (`idBibliotheque`),
  ADD CONSTRAINT `idExemp` FOREIGN KEY (`idExemplaire`) REFERENCES `exemplaire` (`idExemplaire`);

--
-- Contraintes pour la table `qr`
--
ALTER TABLE `qr`
  ADD CONSTRAINT `idAuteur` FOREIGN KEY (`idAuteur`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `idBibliotecaire` FOREIGN KEY (`idBibliotecaire`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `idEx` FOREIGN KEY (`idExemplaire`) REFERENCES `exemplaire` (`idExemplaire`),
  ADD CONSTRAINT `idU` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `idRole` FOREIGN KEY (`idRole`) REFERENCES `role` (`idRole`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
