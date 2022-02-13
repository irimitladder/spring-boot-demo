DROP DATABASE IF EXISTS `the_pax_romana_news`;
CREATE DATABASE `the_pax_romana_news` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT ENCRYPTION='N';
USE `the_pax_romana_news`;

SET FOREIGN_KEY_CHECKS = 0;
SET TIME_ZONE = '+00:00';

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `text` text DEFAULT NULL,
  `publication_date` timestamp DEFAULT NULL,
  `theme_id` int NOT NULL DEFAULT 1,
  `appearance_id` int NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `FK_THEME_idx` (`theme_id`),
  CONSTRAINT `FK_THEME` FOREIGN KEY (`theme_id`) REFERENCES `news_theme` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `FK_APPEARANCE_idx` (`appearance_id`),
  CONSTRAINT `FK_APPEARANCE` FOREIGN KEY (`appearance_id`) REFERENCES `news_appearance` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  UNIQUE KEY `TITLE_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `news_author`;
CREATE TABLE `news_author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `praenomen` varchar(255) DEFAULT NULL,
  `nomen` varchar(255) DEFAULT NULL,
  `cognomen` varchar(255) DEFAULT NULL,
  `agnomen` varchar(255) DEFAULT NULL,
  `extra_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `news_news_author`;
CREATE TABLE `news_news_author` (
  `news_id` int NOT NULL,
  `news_author_id` int NOT NULL,
  PRIMARY KEY (`news_id`, `news_author_id`),
  KEY `FK_NEWS_idx` (`news_id`),
  CONSTRAINT `FK_NEWS` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `FK_NEWS_AUTHOR_idx` (`news_author_id`),
  CONSTRAINT `FK_NEWS_AUTHOR` FOREIGN KEY (`news_author_id`) REFERENCES `news_author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `news_theme`;
CREATE TABLE `news_theme` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NAME_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `news_appearance`;
CREATE TABLE `news_appearance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `horizontal_position` tinyint NOT NULL DEFAULT 0,
  `vertical_position` tinyint NOT NULL DEFAULT 0,
  `highlighted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

INSERT INTO `news` (`id`, `title`, `text`, `publication_date`, `theme_id`, `appearance_id`) VALUES (1, 'Dion Epaminondas', 'Neque vero haec Dionysium fugiebant; nam quanto esset sibi ornamento, sentiebat. Quo fiebat, ut uni huic maxime indulgeret neque eum secus diligeret ac filium; qui quidem, cum Platonem Tarentum venisse fama in Siciliam esset perlata, adulescenti negare non potuerit, quin eum accerseret, cum Dion eius audiendi cupiditate flagraret. Dedit ergo huic veniam magnaque eum ambitione Syracusas perduxit. Quem Dion adeo admiratus est atque adamavit, ut se ei totum traderet. Neque vero minus ipse Plato delectatus est Dione. Itaque cum a tyranno crudeliter violatus esset, quippe quem venum dari iussisset, tamen eodem rediit eiusdem Dionis precibus adductus. Interim in morbum incidit Dionysius. Quo cum gravi conflictaretur, quaesivit a medicis Dion, quemadmodum se haberet, simulque ab his petiit, si forte maiori inesset periculo, ut sibi faterentur: nam velle se cum eo colloqui de partiendo regno, quod sororis suae filios ex illo natos partem regni putabat debere habere. Id medici non tacuerunt at ad Dionysium filium sermonem rettulerunt. Quo ille commotus, ne agendi esset Dioni potestas, patri soporem medicos dare coegit. Hoc aeger sumpto ut somno sopitus diem obiit supremum.
Tale initium fuit Dionis et Dionysii simultatis, eaque multis rebus aucta est. Sed tamen primis temporibus aliquamdiu simulata inter eos amicitia mansit. Cum Dion non desisteret obsecrare Dionysium, ut Platonem Athenis arcesseret et eius consiliis uteretur, ille, qui in aliqua re vellet patrem imitari, morem ei gessit. Eodemque tempore Philistum historicum Syracusas reduxit, hominem amicum non magis tyranno quam tyrannidi. Sed de hoc in eo libro plura sunt exposita, qui de historicis Graecis conscriptus est. Plato autem tantum apud Dionysium auctoritate potuit valuitque eloquentia, ut ei persuaserit tyrannidis facere finem libertatemque reddere Syracusanis; a qua voluntate Philisti consilio deterritus aliquanto crudelior esse coepit.', '1998-04-20 12:31:50', 2, 1);
INSERT INTO `news` (`id`, `title`, `text`, `publication_date`, `theme_id`, `appearance_id`) VALUES (2, 'In Thessalia autem Orchomenus', 'Tertius Europae sinus Acrocerauniis incipit montibus, finitur Hellesponto, amplectitur praeter minores sinus XXV passuum. in eo Epirus, Acarnania, Aetolia, Phocis, Locris, Achaia, Messenia, Laconica, Argolis, Megaris, Attice, Boeotia iterumque ab ealio mari eadem Macedonia, Thracia. omnis Graeciae fabulositas sicut et litterarum claritas ex hoc primum sinu effulsit, quapropter paululum in eo conmorabimur.
Epiros in universum appellata a Cerauniis incipit montibus. in ea primi Chaones, a quibus Chaonia, dein Thesproti, Antigonenses, locus Aornos et pestifera avibus exhalatio, Cestrini, Perrhaebi, quorum mons Pindus, Cassopaei, Dryopes, Selloe, Hellopes, Molossi, apud quos Dodonaei Iovis templum oraculo inlustre, Talarus mons, centum fontibus circa radices Theopompo celebratus.
Proximi Aetolis Locri cognominantur Ozolae, immunes. oppidum Oeanthe, portus Apollinis Phaesti, sinus Crisaeus, intus oppida Argyna, Eupalia, Phaestum, Calamisus. ultra Cirrhaei Phocidis campi, oppidum Cirrha, portus Chalaeon, a quo VII p. introrsus liberum oppidum Delphi sub monte Parnaso, clarissimi in terris oraculi Apollinis.
Corinthiacus hinc, illinc Caronicus appellatur sinus; Lecheae hinc, Cenchreae illinc angustiarum termini, longo et ancipiti navium ambitu quas magnitudo plaustris transvehi prohibet. quam ob causam perfodere navigabili alveo angustias eas temptavere Demeterius rex, dictator Caesar, Gaius princeps, Domitius Nero, nefasto, ut omnium exitu patuit, incepto.', '2001-08-11 23:11:24', 2, 2);
INSERT INTO `news` (`id`, `title`, `text`, `publication_date`, `theme_id`, `appearance_id`) VALUES (3, 'Et miscere nouo docuisse coagula lacte', 'Quisquis adest, faueat: fruges lustramus et agros,
ritus ut a prisco traditus extat auo.
Bacche, ueni, dulcisque tuis e cornibus uua
pendeat, et spicis tempora cinge, Ceres.
luce sacra requiescat humus, requiescat arator,
et graue suspenso uomere cesset opus.', '1984-05-15 09:12:37', 1, 3);
INSERT INTO `news` (`id`, `title`, `text`, `publication_date`, `theme_id`, `appearance_id`) VALUES (4, 'P. Scipione Nasica', 'Conuiuium etiam sollemne maiores institue runt idque caristia appellauerunt, cui praeter cognatos et adfines nemo interponebatur, ut, si qua inter necessarias personas querella esset orta, apud sacra mensae et inter hilaritatem animorum et fautoribus concordiae adhibitis tolleretur.
Maiores natu in conuiuiis ad tibias egregia superiorum opera carmine conprehensa ~ pangebant, quo ad ea imitanda iuuentutem alacriorem redderent. quid hoc splendidius, quid etiam utilius certamine? pubertas canis suum decus reddebat, defuncta cursu aetas ingredientes actuosam uitam feruoris nutrimentis prosequebatur. quas Athenas, quam scholam, quae alienigena studia huic domesticae disciplinae praetulerim? inde oriebantur Camilli, Scipiones, Fabricii, Marcelli, Fabii, ac ne singula imperii nostri lumina simul percurrendo sim longior, inde, inquam, caeli clarissima pars, diui fulserunt Caesares.', '2017-12-02 02:32:42', 2, 4);
INSERT INTO `news` (`id`, `title`, `text`, `publication_date`, `theme_id`, `appearance_id`) VALUES (5, 'Notitia Dignitatum', 'Praefectus praetorio Orientis.
Praefectus praetorio Illyrici.
Praefectus urbis Constantinopolitanae.
Magistri equitum et peditum in praesenti duo.
Equitum et peditum per Orientem.
Equitum et peditum per Thracias.
Equitum et peditum per Illyricum.', '2006-07-29 19:41:47', 1, 5);

INSERT INTO `news_author` (`id`, `praenomen`, `nomen`, `cognomen`) VALUES (1, 'Gnaeus', 'Pompeius', 'Trogus');
INSERT INTO `news_author` (`id`, `nomen`, `cognomen`) VALUES (2, 'Cassius', 'Dio');
INSERT INTO `news_author` (`id`, `praenomen`, `extra_name`) VALUES (3, 'Eusebius', 'of Caesarea');

INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (1, 2);
INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (2, 2);
INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (2, 3);
INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (3, 1);
INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (3, 2);
INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (3, 3);
INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (4, 3);
INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (5, 1);
INSERT INTO `news_news_author` (`news_id`, `news_author_id`) VALUES (5, 3);

INSERT INTO `news_theme` (`id`, `name`) VALUES (1, 'Deorum cultus');
INSERT INTO `news_theme` (`id`, `name`) VALUES (2, 'Publica');

INSERT INTO `news_appearance` (`id`, `horizontal_position`, `vertical_position`, `highlighted`) VALUES (1, 4, 2, 0);
INSERT INTO `news_appearance` (`id`, `horizontal_position`, `vertical_position`, `highlighted`) VALUES (2, 3, 2, 1);
INSERT INTO `news_appearance` (`id`, `horizontal_position`, `vertical_position`, `highlighted`) VALUES (3, 7, 4, 0);
INSERT INTO `news_appearance` (`id`, `horizontal_position`, `vertical_position`, `highlighted`) VALUES (4, 6, 2, 0);
INSERT INTO `news_appearance` (`id`, `horizontal_position`, `vertical_position`, `highlighted`) VALUES (5, 1, 4, 0);

SET FOREIGN_KEY_CHECKS = 1;

DROP USER IF EXISTS 'tiger'@'localhost';
CREATE USER 'tiger'@'localhost' IDENTIFIED BY 'tiger';
GRANT SELECT, INSERT, UPDATE, DELETE ON `news` TO 'tiger'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON `news_author` TO 'tiger'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON `news_news_author` TO 'tiger'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON `news_theme` TO 'tiger'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON `news_appearance` TO 'tiger'@'localhost';
