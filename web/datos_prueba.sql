-- Insertar algunos campeones de prueba en la tabla
-- Primero eliminar datos existentes si los hay
DELETE FROM campeones;

-- Insertar datos de prueba
INSERT INTO campeones (ID_Campeon, Nombre_Campeon, Descripcion_Campeon, Estado) VALUES 
('AAT1', 'Aatrox', 'La Espada de los Mundos Sombríos', 1),
('AHR1', 'Ahri', 'La Vastaya de Nueve Colas', 1),
('AKA1', 'Akali', 'La asesina sin maestro', 1),
('AKS1', 'Akshan', 'El Centinela Rebelde', 1),
('ALI1', 'Alistar', 'El Minotauro', 1),
('AMU1', 'Amumu', 'La Momia Triste', 1),
('ASH1', 'Ashe', 'La Arquera de Hielo', 1),
('BRA1', 'Brand', 'La Venganza Ardiente', 1),
('CAI1', 'Caitlyn', 'La sheriff de Piltover', 1),
('DAR1', 'Darius', 'La Mano de Noxus', 1),
('EKK1', 'Ekko', 'El Niño que Fragmentó el Tiempo', 1),
('EZR1', 'Ezreal', 'El Explorador Pródigo', 1),
('GAR1', 'Garen', 'El Poder de Demacia', 1),
('JIN1', 'Jinx', 'La Bala Perdida', 1),
('LUX1', 'Lux', 'La Dama de la Luz', 1),
('YAS1', 'Yasuo', 'El Imperdonado', 1),
('ZED1', 'Zed', 'El Maestro de las Sombras', 1);