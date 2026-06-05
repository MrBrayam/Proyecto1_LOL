-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 05-06-2026 a las 22:55:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto1_lol`
--
CREATE DATABASE IF NOT EXISTS `proyecto1_lol` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `proyecto1_lol`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

CREATE TABLE `administradores` (
  `Id_Admin` int(11) NOT NULL,
  `Nombre` varchar(25) NOT NULL,
  `Contrasena` varchar(25) NOT NULL,
  `estado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`Id_Admin`, `Nombre`, `Contrasena`, `estado`) VALUES
(1, 'Brayam', '71490956', 1),
(2, 'Tuki', '123456789', 1),
(3, 'Karina', '987654321', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeones`
--

CREATE TABLE `campeones` (
  `ID_Campeon` int(11) NOT NULL,
  `Nombre_Campeon` varchar(50) NOT NULL,
  `Descripcion_Campeon` varchar(150) NOT NULL,
  `rutaimg` varchar(200) NOT NULL,
  `estado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `campeones`
--

INSERT INTO `campeones` (`ID_Campeon`, `Nombre_Campeon`, `Descripcion_Campeon`, `rutaimg`, `estado`) VALUES
(1, 'Aatrox', 'La Espada de los Oscuros', '/img/uploads/Aatrox_1764628309818.jpg', 1),
(2, 'Ahri', 'La Gumiho de Nueve Colas', '', 1),
(3, 'Akali', 'La Asesina Sombría', '/img/uploads/Akali_1775961947781.jpg', 1),
(4, 'Akshan', 'El Centinela Rebelde', '/img/uploads/Akshan_1764737234613.jpg', 1),
(5, 'Alistar', 'El Minotauro', '', 1),
(6, 'Ambessa', 'La Matrona de la Guerra', '', 1),
(7, 'Amumu', 'La Momia Triste', '', 1),
(8, 'Anivia', 'La Criofénix', '', 1),
(9, 'Annie', 'La Niña Oscura', '', 1),
(10, 'Aphelios', 'El Arma de los Fieles', '', 1),
(11, 'Ashe', 'La Arquera de Hielo', '', 1),
(12, 'Aurelion Sol', 'El Forjador de Estrellas', '', 1),
(13, 'Aurora', 'La Bruja de Entre Mundos', '', 1),
(14, 'Azir', 'El Emperador de las Arenas', '', 1),
(15, 'Bard', 'El Guardián Errante', '', 1),
(16, 'Bel\'Veth', 'La Emperatriz del Vacío', '', 1),
(17, 'Blitzcrank', 'El Gran Gólem de Vapor', '', 1),
(18, 'Brand', 'La Venganza Ardiente', '', 1),
(19, 'Braum', 'El Corazón de Freljord', '', 1),
(20, 'Briar', 'La Piltrafa Contenida', '', 1),
(21, 'Caitlyn', 'La Sheriff de Piltover', '', 1),
(22, 'Camille', 'La Sombra de Acero', '', 1),
(23, 'Cassiopeia', 'El Abrazo de la Serpiente', '', 1),
(24, 'Cho\'Gath', 'El Terror del Vacío', '', 1),
(25, 'Corki', 'El Bombardero Osado', '', 1),
(26, 'Darius', 'La Mano de Noxus', '', 1),
(27, 'Diana', 'El Desdén de la Luna', '', 1),
(28, 'Dr. Mundo', 'El Loco de Zaun', '', 1),
(29, 'Draven', 'El Ejecutor Glorioso', '', 1),
(30, 'Ekko', 'El Chico que Quebró el Tiempo', '', 1),
(31, 'Elise', 'La Reina Araña', '', 1),
(32, 'Evelynn', 'El Abrazo del Sufrimiento', '', 1),
(33, 'Ezreal', 'El Explorador Pródigo', '', 1),
(34, 'Fiddlesticks', 'El Terror Primitivo', '', 1),
(35, 'Fiora', 'La Gran Duelista', '', 1),
(36, 'Fizz', 'El Bromista de las Mareas', '', 1),
(37, 'Galio', 'El Coloso', '', 1),
(38, 'Gangplank', 'El Azote de los Mares', '', 1),
(39, 'Garen', 'El Poder de Demacia', '', 1),
(40, 'Gnar', 'El Eslabón Perdido', '', 1),
(41, 'Gragas', 'El Juerguista Rabioso', '', 1),
(42, 'Graves', 'El Forajido', '', 1),
(43, 'Gwen', 'La Costurera Consagrada', '', 1),
(44, 'Hecarim', 'La Sombra de la Guerra', '', 1),
(45, 'Heimerdinger', 'El Inventor Venerado', '', 1),
(46, 'Hwei', 'El Visionario', '', 1),
(47, 'Illaoi', 'La Sacerdotisa del Kraken', '', 1),
(48, 'Irelia', 'La Danzarina de Cuchillas', '', 1),
(49, 'Ivern', 'El Padre del Bosque', '', 1),
(50, 'Janna', 'La Furia de la Tormenta', '', 1),
(51, 'Jarvan IV', 'El Ejemplo de Demacia', '', 1),
(52, 'Jax', 'El Maestro de Armas', '', 1),
(53, 'Jayce', 'El Defensor del Mañana', '', 1),
(54, 'Jhin', 'El Virtuoso', '', 1),
(55, 'Jinx', 'La Bala Perdida', '', 1),
(56, 'K\'Sante', 'El Orgullo de Nazumah', '', 1),
(57, 'Kai\'Sa', 'Hija del Vacío', '', 1),
(58, 'Kalista', 'La Lanza de la Venganza', '', 1),
(59, 'Karma', 'La Iluminada', '', 1),
(60, 'Karthus', 'La Voz de la Muerte', '', 1),
(61, 'Kassadin', 'El Caminante del Vacío', '', 1),
(62, 'Katarina', 'La Daga Siniestra', '', 1),
(63, 'Kayle', 'La Justiciera', '', 1),
(64, 'Kayn', 'La Guadaña de las Sombras', '', 1),
(65, 'Kennen', 'El Corazón de la Tempestad', '', 1),
(66, 'Kha\'Zix', 'El Depredador del Vacío', '', 1),
(67, 'Kindred', 'Los Cazadores Eternos', '', 1),
(68, 'Kled', 'El Jinete Cantankerous', '', 1),
(69, 'Kog\'Maw', 'La Boca del Abismo', '', 1),
(70, 'LeBlanc', 'La Embaucadora', '', 1),
(71, 'Lee Sin', 'El Monje Ciego', '', 1),
(72, 'Leona', 'El Amanecer Radiante', '', 1),
(73, 'Lillia', 'La Tímida Capullona', '', 1),
(74, 'Lissandra', 'La Bruja de Hielo', '', 1),
(75, 'Lucian', 'El Purificador', '', 1),
(76, 'Lulu', 'La Hechicera Fae', '', 1),
(77, 'Lux', 'La Dama de la Luz', '', 1),
(78, 'Malphite', 'Fragmento del Monolito', '', 1),
(79, 'Malzahar', 'El Profeta del Vacío', '', 1),
(80, 'Maokai', 'El Ent Torcido', '', 1),
(81, 'Master Yi', 'El Espadachín Wuju', '', 1),
(82, 'Mel', 'La Gilded Wolf', '', 1),
(83, 'Milio', 'El Suave Llama', '', 1),
(84, 'Miss Fortune', 'La Cazarrecompensas', '', 1),
(85, 'Mordekaiser', 'El Emperador de Hierro', '', 1),
(86, 'Morgana', 'La Caída', '', 1),
(87, 'Naafiri', 'La Manada de Cuchillas', '', 1),
(88, 'Nami', 'La Invocadora de Mareas', '', 1),
(89, 'Nasus', 'El Conservador de las Arenas', '', 1),
(90, 'Nautilus', 'El Titán de las Profundidades', '', 1),
(91, 'Neeko', 'La Camaleona Curiosa', '', 1),
(92, 'Nidalee', 'La Cazadora Salvaje', '', 1),
(93, 'Nilah', 'La Alegría Desatada', '', 1),
(94, 'Nocturne', 'La Pesadilla Eterna', '', 1),
(95, 'Nunu y Willump', 'El Chico y su Yeti', '', 1),
(96, 'Olaf', 'El Berserker', '', 1),
(97, 'Orianna', 'La Doncella Mecánica', '', 1),
(98, 'Ornn', 'El Fuego Bajo la Montaña', '', 1),
(99, 'Pantheon', 'El Aspecto de la Guerra', '', 1),
(100, 'Poppy', 'La Guardiana del Martillo', '', 1),
(101, 'Pyke', 'El Destripador de las Aguas Sangrientas', '', 1),
(102, 'Qiyana', 'La Emperatriz de los Elementos', '', 1),
(103, 'Quinn', 'Las Alas de Demacia', '', 1),
(104, 'Rakan', 'El Encantador', '', 1),
(105, 'Rammus', 'El Armadillo', '', 1),
(106, 'Rek\'Sai', 'La Emperatriz del Vacío', '', 1),
(107, 'Rell', 'La Doncella de Hierro', '', 1),
(108, 'Renata Glasc', 'La Barona Química', '', 1),
(109, 'Renekton', 'El Carnicero de las Arenas', '', 1),
(110, 'Rengar', 'El Cazador de Primates', '', 1),
(111, 'Riven', 'El Exilio', '', 1),
(112, 'Rumble', 'La Amenaza Mecánica', '', 1),
(113, 'Ryze', 'El Mago Rúnico', '', 1),
(114, 'Samira', 'La Rosa del Desierto', '', 1),
(115, 'Sejuani', 'La Furia del Norte', '', 1),
(116, 'Senna', 'La Redentora', '', 1),
(117, 'Seraphine', 'La Cantante Soñadora', '', 1),
(118, 'Sett', 'El Jefe', '', 1),
(119, 'Shaco', 'El Bufón Demoniaco', '', 1),
(120, 'Shen', 'El Ojo del Crepúsculo', '', 1),
(121, 'Shyvana', 'La Semidragón', '', 1),
(122, 'Singed', 'El Químico Loco', '', 1),
(123, 'Sion', 'El Coloso No Muerto', '', 1),
(124, 'Sivir', 'La Señora de la Batalla', '', 1),
(125, 'Skarner', 'El Guardián de Cristal', '', 1),
(126, 'Smolder', 'El Dragón Ardiente', '', 1),
(127, 'Sona', 'La Maven de las Cuerdas', '', 1),
(128, 'Soraka', 'La Hija de las Estrellas', '', 1),
(129, 'Swain', 'El Visionario Noxiano', '', 1),
(130, 'Sylas', 'El Desenpatronado', '', 1),
(131, 'Syndra', 'La Soberana Oscura', '', 1),
(132, 'Tahm Kench', 'El Rey del Río', '', 1),
(133, 'Taliyah', 'La Tejedora de Piedra', '', 1),
(134, 'Talon', 'La Sombra de la Cuchilla', '', 1),
(135, 'Taric', 'El Escudo de Valoran', '', 1),
(136, 'Teemo', 'El Explorador Rápido', '', 1),
(137, 'Thresh', 'El Carcelero', '', 1),
(138, 'Tristana', 'La Yordle Artillera', '', 1),
(139, 'Trundle', 'El Rey Troll', '', 1),
(140, 'Tryndamere', 'El Rey Bárbaro', '', 1),
(141, 'Twisted Fate', 'El Maestro de Cartas', '', 1),
(142, 'Twitch', 'La Peste', '', 1),
(143, 'Udyr', 'El Caminante de los Espíritus', '', 1),
(144, 'Urgot', 'El Temor de Zaun', '', 1),
(145, 'Varus', 'La Flecha de la Venganza', '', 1),
(146, 'Vayne', 'La Cazadora Nocturna', '', 1),
(147, 'Veigar', 'El Pequeño Maestro del Mal', '', 1),
(148, 'Vel\'Koz', 'El Ojo del Vacío', '', 1),
(149, 'Vex', 'La Yordle Sombría', '', 1),
(150, 'Vi', 'La Rompehuesosde Piltover', '', 1),
(151, 'Viego', 'El Rey Arruinado', '', 1),
(152, 'Viktor', 'El Heraldo de las Máquinas', '', 1),
(153, 'Vladimir', 'El Cosechador Carmesí', '', 1),
(154, 'Volibear', 'La Tormenta Implacable', '', 1),
(155, 'Warwick', 'La Ira Desatada de Zaun', '', 1),
(156, 'Wukong', 'El Guerrero Mono', '', 1),
(157, 'Xayah', 'La Rebelde', '', 1),
(158, 'Xerath', 'El Magus Ascendido', '', 1),
(159, 'Xin Zhao', 'El Senescal de Demacia', '', 1),
(160, 'Yasuo', 'El Imperdonado', '', 1),
(161, 'Yone', 'El Olvidado', '', 1),
(162, 'Yorick', 'El Pastor de Almas', '', 1),
(163, 'Yunara', 'Campeona Personalizada', '', 1),
(164, 'Yuumi', 'El Gato Mágico', '', 1),
(165, 'Zac', 'La Arma Secreta', '', 1),
(166, 'Zed', 'El Maestro de las Sombras', '', 1),
(167, 'Zeri', 'La Chispa de Zaun', '', 1),
(168, 'Ziggs', 'El Experto en Hexplosivos', '', 1),
(169, 'Zilean', 'El Cronomago', '', 1),
(170, 'Zoe', 'El Aspecto del Crepúsculo', '', 1),
(171, 'Zyra', 'El Despertar de las Espinas', '', 1),
(172, 'AAA', 'AAA', '', 0),
(173, 'FISI', 'FISI', '/img/uploads/FISI_1764737895689.png', 0),
(174, 'Ursa', 'Test', '/img/uploads/Ursa_1764771380354.jpg', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_runas`
--

CREATE TABLE `categorias_runas` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(50) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `color` varchar(7) NOT NULL,
  `imagen_principal` varchar(255) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias_runas`
--

INSERT INTO `categorias_runas` (`id_categoria`, `nombre_categoria`, `descripcion`, `color`, `imagen_principal`, `estado`) VALUES
(1, 'Precisión', 'Mejora tus ataques básicos y sustentabilidad en combate prolongado', '#C8AA6E', '/img/1_Runas/Presicion/runas-precision-league-of-legends.jpg', 1),
(2, 'Dominación', 'Aumenta tu poder explosivo y capacidades de eliminación instantánea', '#DA1E37', '/img/1_Runas/Dominacion/runas-dominacion.jpg', 1),
(3, 'Brujería', 'Potencia tus habilidades mágicas y utilidad de combate', '#4E7FCC', '/img/1_Runas/Brujeria/runas-brujeria.jpg', 1),
(4, 'Valor', 'Incrementa tu resistencia y capacidades defensivas', '#00C851', '/img/1_Runas/Valor/runas-valor.jpg', 1),
(5, 'Inspiración', 'Ofrece utilidades únicas y opciones creativas de juego', '#42A5F5', '/img/1_Runas/Inspiracion/runas-inspiracion.jpg', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `runas`
--

CREATE TABLE `runas` (
  `Id_runa` int(11) NOT NULL,
  `nombre_runa` varchar(255) NOT NULL,
  `descripcion_runa` varchar(255) NOT NULL,
  `tipo_runa` enum('principal','sub_runa_1','sub_runa_2','sub_runa_3') DEFAULT 'principal',
  `id_categoria` int(11) DEFAULT NULL,
  `url_img` varchar(255) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `runas`
--

INSERT INTO `runas` (`Id_runa`, `nombre_runa`, `descripcion_runa`, `tipo_runa`, `id_categoria`, `url_img`, `estado`) VALUES
(1, 'Conquistador', 'Ganas poder de ataque adaptativo al luchar contra campeones', 'principal', 1, '/img/1_Runas/Presicion/conqueror-55x.png', 1),
(2, 'Tempo Letal', 'Ganas velocidad de ataque que supera el límite', 'principal', 1, '/img/1_Runas/Presicion/lethal-tempo-55x.png', 1),
(3, 'Ataque Concentrado', 'Tus ataques contra el mismo objetivo se vuelven más letales', 'principal', 1, '/img/1_Runas/Presicion/press-the-attack-55x.png', 1),
(4, 'Pies Veloces', 'Ganas velocidad de movimiento y curación', 'principal', 1, '/img/1_Runas/Presicion/fleet-footwork-55x.png', 1),
(5, 'Presencia Mental', 'Restaura maná cuando dañas a campeones enemigos', 'sub_runa_1', 1, '/img/1_Runas/Presicion/presence-of-mind-55x.png', 1),
(6, 'Triunfo', 'Las eliminaciones restauran salud', 'sub_runa_1', 1, '/img/1_Runas/Presicion/triumph-55x.png', 1),
(7, 'Absorber Vida', 'Curas cuando dañas a campeones enemigos', 'sub_runa_1', 1, '/img/1_Runas/Presicion/absorb-life-55x.png', 1),
(8, 'Leyenda: Presteza', 'Ganas velocidad de ataque permanente por eliminación única', 'sub_runa_2', 1, '/img/1_Runas/Presicion/legend-alacrity-55x.png', 1),
(9, 'Leyenda: Torrente de Vida', 'Ganas robo de vida permanente por eliminación única', 'sub_runa_2', 1, '/img/1_Runas/Presicion/legend-bloodline-55x.png', 1),
(10, 'Leyenda: Tenacidad', 'Ganas reducción de enfriamiento de habilidades permanente', 'sub_runa_2', 1, '/img/1_Runas/Presicion/legend-haste-55x.png', 1),
(11, 'Golpe de Gracia', 'Infliges daño extra a enemigos con poca vida', 'sub_runa_3', 1, '/img/1_Runas/Presicion/coup-de-grace-55x.png', 1),
(12, 'Derribar', 'Infliges daño extra a enemigos con más vida máxima que tú', 'sub_runa_3', 1, '/img/1_Runas/Presicion/cut-down-55x.png', 1),
(13, 'Última Resistencia', 'Infliges más daño basado en tu vida perdida', 'sub_runa_3', 1, '/img/1_Runas/Presicion/last-stand-55x.png', 1),
(14, 'Electrocutar', 'Inflige daño extra tras golpear a un enemigo con 3 ataques o habilidades separadas', 'principal', 2, '/img/1_Runas/Dominacion/electrocute-55x.png', 1),
(15, 'Lluvia de Hojas', 'Las eliminaciones y asistencias descargan proyectiles que infligen daño', 'principal', 2, '/img/1_Runas/Dominacion/hail-of-blades-55x.png', 1),
(16, 'Cosecha Oscura', 'Inflige daño adaptativo extra a campeones con poca vida', 'principal', 2, '/img/1_Runas/Dominacion/dark-harvest-55x.png', 1),
(17, 'Golpe Bajo', 'Inflige daño verdadero extra a campeones con movilidad reducida', 'sub_runa_1', 2, '/img/1_Runas/Dominacion/cheap-shot-55x.png', 1),
(18, 'Sabor de Sangre', 'Cura vida cuando dañas a un campeón enemigo', 'sub_runa_1', 2, '/img/1_Runas/Dominacion/taste-of-blood-55x.png', 1),
(19, 'Impacto Repentino', 'Ganas penetración tras usar un dash, salto o teletransporte', 'sub_runa_1', 2, '/img/1_Runas/Dominacion/sudden-impact-55x.png', 1),
(20, 'Guardián Vigilante', 'Recibes visión cuando un enemigo entra en un arbusto cercano', 'sub_runa_2', 2, '/img/1_Runas/Dominacion/sixth-sense-55x.png', 1),
(21, 'Vigía Profundo', 'Ganas velocidad de movimiento cerca de wards y cuando las destruyes', 'sub_runa_2', 2, '/img/1_Runas/Dominacion/deep-ward-55x.png', 1),
(22, 'Recuerdos Macabros', 'Recolecta recuerdos de campeones enemigos para ganar poder adaptativo', 'sub_runa_2', 2, '/img/1_Runas/Dominacion/grisly-mementos-55x.png', 1),
(23, 'Cazador Definitivo', 'Reduce el enfriamiento de tu definitiva por eliminación única', 'sub_runa_3', 2, '/img/1_Runas/Dominacion/ultimate-hunter-55x.png', 1),
(24, 'Cazador Incansable', 'Ganas velocidad de movimiento fuera de combate', 'sub_runa_3', 2, '/img/1_Runas/Dominacion/relentless-hunter-55x.png', 1),
(25, 'Cazador de Tesoros', 'Ganas oro extra por eliminación única', 'sub_runa_3', 2, '/img/1_Runas/Dominacion/treasure-hunter-55x.png', 1),
(26, 'Cometa Arcano', 'Inflige daño extra tras dañar a un campeón con una habilidad', 'principal', 3, '/img/1_Runas/Brujeria/arcane-comet-55x.png', 1),
(27, 'Invocación de Aery', 'Tus ataques y habilidades envían a Aery para dañar enemigos o proteger aliados', 'principal', 3, '/img/1_Runas/Brujeria/summon-aery-55x.png', 1),
(28, 'Paso Etéreo', 'Ganas velocidad de movimiento al usar hechizos de invocador', 'principal', 3, '/img/1_Runas/Brujeria/phase-rush-55x.png', 1),
(29, 'Banda de Flujo de Maná', 'Golpear a un campeón enemigo con una habilidad permanentemente aumenta tu maná o regeneración', 'sub_runa_1', 3, '/img/1_Runas/Brujeria/manaflow-band-55x.png', 1),
(30, 'Capa de Nimbo', 'Ganas velocidad de movimiento tras usar un hechizo de invocador', 'sub_runa_1', 3, '/img/1_Runas/Brujeria/nimbus-cloak-55x.png', 1),
(31, 'Trascendencia', 'Ganas reducción de enfriamiento de habilidades al subir de nivel', 'sub_runa_1', 3, '/img/1_Runas/Brujeria/transcendence-55x.png', 1),
(32, 'Foco Absoluto', 'Ganas poder de habilidad extra cuando tienes mucho maná', 'sub_runa_2', 3, '/img/1_Runas/Brujeria/absolute-focus-55x.png', 1),
(33, 'Celeridad', 'Ganas poder de ataque o poder de habilidad basado en tu velocidad de movimiento extra', 'sub_runa_2', 3, '/img/1_Runas/Brujeria/celerity-55x.png', 1),
(34, 'Chamuscar', 'Tu siguiente habilidad quema al enemigo objetivo', 'sub_runa_2', 3, '/img/1_Runas/Brujeria/scorch-55x.png', 1),
(35, 'Caminar Sobre Agua', 'Ganas velocidad de movimiento cuando estás en el río', 'sub_runa_3', 3, '/img/1_Runas/Brujeria/waterwalking-55x.png', 1),
(36, 'Tormenta Creciente', 'Ganas poder de ataque adaptativo cada 10 minutos', 'sub_runa_3', 3, '/img/1_Runas/Brujeria/gathering-storm-55x.png', 1),
(37, 'Arcanista Axiomático', 'Tras obtener una eliminación, reduces el enfriamiento de tu definitiva', 'sub_runa_3', 3, '/img/1_Runas/Brujeria/axiom-arcanist-55x.png', 1),
(38, 'Guardián', 'Protege a aliados cercanos de daño explosivo y les otorga velocidad de movimiento', 'principal', 4, '/img/1_Runas/Valor/guardian-55x.png', 1),
(39, 'Réplica', 'Tras recibir daño, tu siguiente ataque básico contra un campeón inflige daño extra', 'principal', 4, '/img/1_Runas/Valor/aftershock-55x.png', 1),
(40, 'Agarre del Inmortal', 'Cada 4 segundos en combate, tu siguiente ataque contra un campeón te otorga vida', 'principal', 4, '/img/1_Runas/Valor/grasp-of-the-undying-55x.png', 1),
(41, 'Demoler', 'Tras estar cerca de una torreta enemiga, cargas un ataque poderoso contra ella', 'sub_runa_1', 4, '/img/1_Runas/Valor/demolish-55x.png', 1),
(42, 'Fuente de Vida', 'Inmovilizar a un campeón enemigo marca a ese objetivo', 'sub_runa_1', 4, '/img/1_Runas/Valor/font-of-life-55x.png', 1),
(43, 'Golpe de Escudo', 'Cuando ganas un escudo, tu siguiente ataque básico inflige daño extra', 'sub_runa_1', 4, '/img/1_Runas/Valor/shield-bash-55x.png', 1),
(44, 'Acondicionamiento', 'Ganas resistencias mágica y de armadura extra', 'sub_runa_2', 4, '/img/1_Runas/Valor/conditioning-55x.png', 1),
(45, 'Segundo Viento', 'Tras recibir daño, regeneras vida durante un tiempo', 'sub_runa_2', 4, '/img/1_Runas/Valor/second-wind-55x.png', 1),
(46, 'Placas Óseas', 'Tras recibir daño, bloqueas los próximos 3 ataques o habilidades', 'sub_runa_2', 4, '/img/1_Runas/Valor/bone-plating-55x.png', 1),
(47, 'Sobrecrecimiento', 'Ganas vida máxima permanente cuando mueren súbditos cerca', 'sub_runa_3', 4, '/img/1_Runas/Valor/overgrowth-55x.png', 1),
(48, 'Revitalizar', 'La curación y los escudos que otorgas o recibes son más fuertes cuando tienes poca vida', 'sub_runa_3', 4, '/img/1_Runas/Valor/revitalize-55x.png', 1),
(49, 'Inquebrantable', 'Ganas resistencia a ralentizaciones cuando usas un hechizo de invocador', 'sub_runa_3', 4, '/img/1_Runas/Valor/unflinching-55x.png', 1),
(50, 'Primer Golpe', 'Cuando atacas primero a un campeón enemigo, infliges daño verdadero extra', 'principal', 5, '/img/1_Runas/Inspiracion/first-strike-55x.png', 1),
(51, 'Libro de Hechizos Liberado', 'Cambias periódicamente uno de tus hechizos de invocador por uno diferente', 'principal', 5, '/img/1_Runas/Inspiracion/unsealed-spellbook-55x.png', 1),
(52, 'Aumento Glacial', 'Inmovilizar a un campeón enemigo comienza un proceso que ralentiza a enemigos cercanos', 'principal', 5, '/img/1_Runas/Inspiracion/glacial-augment-55x.png', 1),
(53, 'Calzado Mágico', 'Recibes botas gratis ligeramente mejoradas', 'sub_runa_1', 5, '/img/1_Runas/Inspiracion/magical-footwear-55x.png', 1),
(54, 'Entrega de Galletas', 'Recibes galletas gratuitas que restauran vida y maná', 'sub_runa_1', 5, '/img/1_Runas/Inspiracion/biscuit-delivery-55x.png', 1),
(55, 'Devolución de Efectivo', 'Obtienes oro extra al comprar objetos', 'sub_runa_1', 5, '/img/1_Runas/Inspiracion/cash-back-55x.png', 1),
(56, 'Hexflash', 'Puedes usar el hechizo Flash mientras canalizas la vuelta a la base', 'sub_runa_2', 5, '/img/1_Runas/Inspiracion/hextech-flashtraption-55x.png', 1),
(57, 'Tónico Temporal', 'Las pociones y elixires duran más tiempo', 'sub_runa_2', 5, '/img/1_Runas/Inspiracion/time-warp-tonic-55x.png', 1),
(58, 'Velocidad de Acercamiento', 'Ganas velocidad de movimiento al acercarte a aliados dañados o enemigos con movilidad reducida', 'sub_runa_2', 5, '/img/1_Runas/Inspiracion/approach-velocity-55x.png', 1),
(59, 'Perspicacia Cósmica', 'Ganas reducción de enfriamiento en objetos activos y hechizos de invocador', 'sub_runa_3', 5, '/img/1_Runas/Inspiracion/cosmic-insight-55x.png', 1),
(60, 'Jack de Todos los Oficios', 'Ganas poder de ataque adaptativo por cada tipo diferente de objeto legendario', 'sub_runa_3', 5, '/img/1_Runas/Inspiracion/jack-of-all-trades-55x.png', 1),
(61, 'Tónico Triple', 'Cuando usas una poción, otras dos se activan con menor potencia', 'sub_runa_3', 5, '/img/1_Runas/Inspiracion/triple-tonic-55x.png', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`Id_Admin`);

--
-- Indices de la tabla `campeones`
--
ALTER TABLE `campeones`
  ADD PRIMARY KEY (`ID_Campeon`);

--
-- Indices de la tabla `categorias_runas`
--
ALTER TABLE `categorias_runas`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `runas`
--
ALTER TABLE `runas`
  ADD PRIMARY KEY (`Id_runa`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administradores`
--
ALTER TABLE `administradores`
  MODIFY `Id_Admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `campeones`
--
ALTER TABLE `campeones`
  MODIFY `ID_Campeon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=175;

--
-- AUTO_INCREMENT de la tabla `categorias_runas`
--
ALTER TABLE `categorias_runas`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `runas`
--
ALTER TABLE `runas`
  MODIFY `Id_runa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `runas`
--
ALTER TABLE `runas`
  ADD CONSTRAINT `runas_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias_runas` (`id_categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
