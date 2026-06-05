-- =============================================
-- Datos de runas extraídos del RunasController
-- =============================================

-- Tabla de categorías de runas
DROP TABLE IF EXISTS `categorias_runas`;
CREATE TABLE `categorias_runas` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(50) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `color` varchar(7) NOT NULL,
  `imagen_principal` varchar(255) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `categorias_runas` (`id_categoria`, `nombre_categoria`, `descripcion`, `color`, `imagen_principal`, `estado`) VALUES
(1, 'Precisión',   'Mejora tus ataques básicos y sustentabilidad en combate prolongado',       '#C8AA6E', '/img/1_Runas/Presicion/runas-precision-league-of-legends.jpg', 1),
(2, 'Dominación',  'Aumenta tu poder explosivo y capacidades de eliminación instantánea',    '#DA1E37', '/img/1_Runas/Dominacion/runas-dominacion.jpg', 1),
(3, 'Brujería',    'Potencia tus habilidades mágicas y utilidad de combate',                  '#4E7FCC', '/img/1_Runas/Brujeria/runas-brujeria.jpg', 1),
(4, 'Valor',       'Incrementa tu resistencia y capacidades defensivas',                     '#00C851', '/img/1_Runas/Valor/runas-valor.jpg', 1),
(5, 'Inspiración', 'Ofrece utilidades únicas y opciones creativas de juego',                 '#42A5F5', '/img/1_Runas/Inspiracion/runas-inspiracion.jpg', 1);

-- Agregar columna id_categoria a la tabla runas
ALTER TABLE `runas` ADD COLUMN IF NOT EXISTS `id_categoria` int(11) DEFAULT NULL AFTER `tipo_runa`;
ALTER TABLE `runas` ADD FOREIGN KEY (`id_categoria`) REFERENCES `categorias_runas`(`id_categoria`);

-- Vaciar datos existentes y cargar todos desde cero
TRUNCATE TABLE `runas`;

-- Precisión (id_categoria = 1)
INSERT INTO `runas` (`Id_runa`, `nombre_runa`, `descripcion_runa`, `tipo_runa`, `id_categoria`, `url_img`, `estado`) VALUES
(1,  'Conquistador',          'Ganas poder de ataque adaptativo al luchar contra campeones',                                                         'principal',   1, '/img/1_Runas/Presicion/conqueror-55x.png', 1),
(2,  'Tempo Letal',           'Ganas velocidad de ataque que supera el límite',                                                                     'principal',   1, '/img/1_Runas/Presicion/lethal-tempo-55x.png', 1),
(3,  'Ataque Concentrado',    'Tus ataques contra el mismo objetivo se vuelven más letales',                                                        'principal',   1, '/img/1_Runas/Presicion/press-the-attack-55x.png', 1),
(4,  'Pies Veloces',          'Ganas velocidad de movimiento y curación',                                                                           'principal',   1, '/img/1_Runas/Presicion/fleet-footwork-55x.png', 1),
(5,  'Presencia Mental',      'Restaura maná cuando dañas a campeones enemigos',                                                                    'sub_runa_1',  1, '/img/1_Runas/Presicion/presence-of-mind-55x.png', 1),
(6,  'Triunfo',               'Las eliminaciones restauran salud',                                                                                  'sub_runa_1',  1, '/img/1_Runas/Presicion/triumph-55x.png', 1),
(7,  'Absorber Vida',         'Curas cuando dañas a campeones enemigos',                                                                            'sub_runa_1',  1, '/img/1_Runas/Presicion/absorb-life-55x.png', 1),
(8,  'Leyenda: Presteza',     'Ganas velocidad de ataque permanente por eliminación única',                                                         'sub_runa_2',  1, '/img/1_Runas/Presicion/legend-alacrity-55x.png', 1),
(9,  'Leyenda: Torrente de Vida', 'Ganas robo de vida permanente por eliminación única',                                                           'sub_runa_2',  1, '/img/1_Runas/Presicion/legend-bloodline-55x.png', 1),
(10, 'Leyenda: Tenacidad',    'Ganas reducción de enfriamiento de habilidades permanente',                                                          'sub_runa_2',  1, '/img/1_Runas/Presicion/legend-haste-55x.png', 1),
(11, 'Golpe de Gracia',       'Infliges daño extra a enemigos con poca vida',                                                                       'sub_runa_3',  1, '/img/1_Runas/Presicion/coup-de-grace-55x.png', 1),
(12, 'Derribar',              'Infliges daño extra a enemigos con más vida máxima que tú',                                                           'sub_runa_3',  1, '/img/1_Runas/Presicion/cut-down-55x.png', 1),
(13, 'Última Resistencia',    'Infliges más daño basado en tu vida perdida',                                                                        'sub_runa_3',  1, '/img/1_Runas/Presicion/last-stand-55x.png', 1);

-- Dominación (id_categoria = 2)
INSERT INTO `runas` (`Id_runa`, `nombre_runa`, `descripcion_runa`, `tipo_runa`, `id_categoria`, `url_img`, `estado`) VALUES
(14, 'Electrocutar',          'Inflige daño extra tras golpear a un enemigo con 3 ataques o habilidades separadas',                                  'principal',   2, '/img/1_Runas/Dominacion/electrocute-55x.png', 1),
(15, 'Lluvia de Hojas',       'Las eliminaciones y asistencias descargan proyectiles que infligen daño',                                             'principal',   2, '/img/1_Runas/Dominacion/hail-of-blades-55x.png', 1),
(16, 'Cosecha Oscura',        'Inflige daño adaptativo extra a campeones con poca vida',                                                            'principal',   2, '/img/1_Runas/Dominacion/dark-harvest-55x.png', 1),
(17, 'Golpe Bajo',            'Inflige daño verdadero extra a campeones con movilidad reducida',                                                     'sub_runa_1',  2, '/img/1_Runas/Dominacion/cheap-shot-55x.png', 1),
(18, 'Sabor de Sangre',       'Cura vida cuando dañas a un campeón enemigo',                                                                        'sub_runa_1',  2, '/img/1_Runas/Dominacion/taste-of-blood-55x.png', 1),
(19, 'Impacto Repentino',     'Ganas penetración tras usar un dash, salto o teletransporte',                                                        'sub_runa_1',  2, '/img/1_Runas/Dominacion/sudden-impact-55x.png', 1),
(20, 'Guardián Vigilante',    'Recibes visión cuando un enemigo entra en un arbusto cercano',                                                       'sub_runa_2',  2, '/img/1_Runas/Dominacion/sixth-sense-55x.png', 1),
(21, 'Vigía Profundo',        'Ganas velocidad de movimiento cerca de wards y cuando las destruyes',                                                'sub_runa_2',  2, '/img/1_Runas/Dominacion/deep-ward-55x.png', 1),
(22, 'Recuerdos Macabros',    'Recolecta recuerdos de campeones enemigos para ganar poder adaptativo',                                              'sub_runa_2',  2, '/img/1_Runas/Dominacion/grisly-mementos-55x.png', 1),
(23, 'Cazador Definitivo',    'Reduce el enfriamiento de tu definitiva por eliminación única',                                                      'sub_runa_3',  2, '/img/1_Runas/Dominacion/ultimate-hunter-55x.png', 1),
(24, 'Cazador Incansable',    'Ganas velocidad de movimiento fuera de combate',                                                                     'sub_runa_3',  2, '/img/1_Runas/Dominacion/relentless-hunter-55x.png', 1),
(25, 'Cazador de Tesoros',    'Ganas oro extra por eliminación única',                                                                              'sub_runa_3',  2, '/img/1_Runas/Dominacion/treasure-hunter-55x.png', 1);

-- Brujería (id_categoria = 3)
INSERT INTO `runas` (`Id_runa`, `nombre_runa`, `descripcion_runa`, `tipo_runa`, `id_categoria`, `url_img`, `estado`) VALUES
(26, 'Cometa Arcano',         'Inflige daño extra tras dañar a un campeón con una habilidad',                                                        'principal',   3, '/img/1_Runas/Brujeria/arcane-comet-55x.png', 1),
(27, 'Invocación de Aery',    'Tus ataques y habilidades envían a Aery para dañar enemigos o proteger aliados',                                      'principal',   3, '/img/1_Runas/Brujeria/summon-aery-55x.png', 1),
(28, 'Paso Etéreo',           'Ganas velocidad de movimiento al usar hechizos de invocador',                                                         'principal',   3, '/img/1_Runas/Brujeria/phase-rush-55x.png', 1),
(29, 'Banda de Flujo de Maná','Golpear a un campeón enemigo con una habilidad permanentemente aumenta tu maná o regeneración',                       'sub_runa_1',  3, '/img/1_Runas/Brujeria/manaflow-band-55x.png', 1),
(30, 'Capa de Nimbo',         'Ganas velocidad de movimiento tras usar un hechizo de invocador',                                                     'sub_runa_1',  3, '/img/1_Runas/Brujeria/nimbus-cloak-55x.png', 1),
(31, 'Trascendencia',         'Ganas reducción de enfriamiento de habilidades al subir de nivel',                                                   'sub_runa_1',  3, '/img/1_Runas/Brujeria/transcendence-55x.png', 1),
(32, 'Foco Absoluto',         'Ganas poder de habilidad extra cuando tienes mucho maná',                                                            'sub_runa_2',  3, '/img/1_Runas/Brujeria/absolute-focus-55x.png', 1),
(33, 'Celeridad',             'Ganas poder de ataque o poder de habilidad basado en tu velocidad de movimiento extra',                               'sub_runa_2',  3, '/img/1_Runas/Brujeria/celerity-55x.png', 1),
(34, 'Chamuscar',             'Tu siguiente habilidad quema al enemigo objetivo',                                                                    'sub_runa_2',  3, '/img/1_Runas/Brujeria/scorch-55x.png', 1),
(35, 'Caminar Sobre Agua',    'Ganas velocidad de movimiento cuando estás en el río',                                                               'sub_runa_3',  3, '/img/1_Runas/Brujeria/waterwalking-55x.png', 1),
(36, 'Tormenta Creciente',    'Ganas poder de ataque adaptativo cada 10 minutos',                                                                   'sub_runa_3',  3, '/img/1_Runas/Brujeria/gathering-storm-55x.png', 1),
(37, 'Arcanista Axiomático',  'Tras obtener una eliminación, reduces el enfriamiento de tu definitiva',                                             'sub_runa_3',  3, '/img/1_Runas/Brujeria/axiom-arcanist-55x.png', 1);

-- Valor (id_categoria = 4)
INSERT INTO `runas` (`Id_runa`, `nombre_runa`, `descripcion_runa`, `tipo_runa`, `id_categoria`, `url_img`, `estado`) VALUES
(38, 'Guardián',              'Protege a aliados cercanos de daño explosivo y les otorga velocidad de movimiento',                                   'principal',   4, '/img/1_Runas/Valor/guardian-55x.png', 1),
(39, 'Réplica',               'Tras recibir daño, tu siguiente ataque básico contra un campeón inflige daño extra',                                 'principal',   4, '/img/1_Runas/Valor/aftershock-55x.png', 1),
(40, 'Agarre del Inmortal',   'Cada 4 segundos en combate, tu siguiente ataque contra un campeón te otorga vida',                                   'principal',   4, '/img/1_Runas/Valor/grasp-of-the-undying-55x.png', 1),
(41, 'Demoler',               'Tras estar cerca de una torreta enemiga, cargas un ataque poderoso contra ella',                                      'sub_runa_1',  4, '/img/1_Runas/Valor/demolish-55x.png', 1),
(42, 'Fuente de Vida',        'Inmovilizar a un campeón enemigo marca a ese objetivo',                                                              'sub_runa_1',  4, '/img/1_Runas/Valor/font-of-life-55x.png', 1),
(43, 'Golpe de Escudo',       'Cuando ganas un escudo, tu siguiente ataque básico inflige daño extra',                                              'sub_runa_1',  4, '/img/1_Runas/Valor/shield-bash-55x.png', 1),
(44, 'Acondicionamiento',     'Ganas resistencias mágica y de armadura extra',                                                                      'sub_runa_2',  4, '/img/1_Runas/Valor/conditioning-55x.png', 1),
(45, 'Segundo Viento',        'Tras recibir daño, regeneras vida durante un tiempo',                                                                'sub_runa_2',  4, '/img/1_Runas/Valor/second-wind-55x.png', 1),
(46, 'Placas Óseas',          'Tras recibir daño, bloqueas los próximos 3 ataques o habilidades',                                                   'sub_runa_2',  4, '/img/1_Runas/Valor/bone-plating-55x.png', 1),
(47, 'Sobrecrecimiento',      'Ganas vida máxima permanente cuando mueren súbditos cerca',                                                          'sub_runa_3',  4, '/img/1_Runas/Valor/overgrowth-55x.png', 1),
(48, 'Revitalizar',           'La curación y los escudos que otorgas o recibes son más fuertes cuando tienes poca vida',                            'sub_runa_3',  4, '/img/1_Runas/Valor/revitalize-55x.png', 1),
(49, 'Inquebrantable',        'Ganas resistencia a ralentizaciones cuando usas un hechizo de invocador',                                             'sub_runa_3',  4, '/img/1_Runas/Valor/unflinching-55x.png', 1);

-- Inspiración (id_categoria = 5)
INSERT INTO `runas` (`Id_runa`, `nombre_runa`, `descripcion_runa`, `tipo_runa`, `id_categoria`, `url_img`, `estado`) VALUES
(50, 'Primer Golpe',           'Cuando atacas primero a un campeón enemigo, infliges daño verdadero extra',                                          'principal',   5, '/img/1_Runas/Inspiracion/first-strike-55x.png', 1),
(51, 'Libro de Hechizos Liberado', 'Cambias periódicamente uno de tus hechizos de invocador por uno diferente',                                      'principal',   5, '/img/1_Runas/Inspiracion/unsealed-spellbook-55x.png', 1),
(52, 'Aumento Glacial',        'Inmovilizar a un campeón enemigo comienza un proceso que ralentiza a enemigos cercanos',                              'principal',   5, '/img/1_Runas/Inspiracion/glacial-augment-55x.png', 1),
(53, 'Calzado Mágico',         'Recibes botas gratis ligeramente mejoradas',                                                                         'sub_runa_1',  5, '/img/1_Runas/Inspiracion/magical-footwear-55x.png', 1),
(54, 'Entrega de Galletas',    'Recibes galletas gratuitas que restauran vida y maná',                                                               'sub_runa_1',  5, '/img/1_Runas/Inspiracion/biscuit-delivery-55x.png', 1),
(55, 'Devolución de Efectivo', 'Obtienes oro extra al comprar objetos',                                                                              'sub_runa_1',  5, '/img/1_Runas/Inspiracion/cash-back-55x.png', 1),
(56, 'Hexflash',               'Puedes usar el hechizo Flash mientras canalizas la vuelta a la base',                                                'sub_runa_2',  5, '/img/1_Runas/Inspiracion/hextech-flashtraption-55x.png', 1),
(57, 'Tónico Temporal',        'Las pociones y elixires duran más tiempo',                                                                           'sub_runa_2',  5, '/img/1_Runas/Inspiracion/time-warp-tonic-55x.png', 1),
(58, 'Velocidad de Acercamiento', 'Ganas velocidad de movimiento al acercarte a aliados dañados o enemigos con movilidad reducida',                  'sub_runa_2',  5, '/img/1_Runas/Inspiracion/approach-velocity-55x.png', 1),
(59, 'Perspicacia Cósmica',    'Ganas reducción de enfriamiento en objetos activos y hechizos de invocador',                                         'sub_runa_3',  5, '/img/1_Runas/Inspiracion/cosmic-insight-55x.png', 1),
(60, 'Jack de Todos los Oficios', 'Ganas poder de ataque adaptativo por cada tipo diferente de objeto legendario',                                    'sub_runa_3',  5, '/img/1_Runas/Inspiracion/jack-of-all-trades-55x.png', 1),
(61, 'Tónico Triple',          'Cuando usas una poción, otras dos se activan con menor potencia',                                                    'sub_runa_3',  5, '/img/1_Runas/Inspiracion/triple-tonic-55x.png', 1);
