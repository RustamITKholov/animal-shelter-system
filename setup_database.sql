--Создаю базу данных
CREATE DATABASE `Human Friends`;
--Выбираю созданную базу
USE `Human Friends`;
--Создаю таблицу Pets
CREATE TABLE Pets (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(50),
    Name VARCHAR(50),
    BirthDate DATE,
    Commands VARCHAR(255)
);
--Создаю таблицу PackAnimals
CREATE TABLE PackAnimals (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(50),
    Name VARCHAR(50),
    BirthDate DATE,
    Commands VARCHAR(255)
);
--Добавляю данные
INSERT INTO Pets (Type, Name, BirthDate, Commands) VALUES
('Dog', 'Fido', '2020-01-01', 'Sit, Stay, Fetch'),
('Cat', 'Whiskers', '2019-05-15', 'Sit, Pounce'),
('Hamster', 'Hammy', '2021-03-10', 'Roll, Hide'),
('Dog', 'Buddy', '2018-12-10', 'Sit, Paw, Bark'),
('Cat', 'Smudge', '2020-02-20', 'Sit, Pounce, Scratch'),
('Hamster', 'Peanut', '2021-08-01', 'Roll, Spin'),
('Dog', 'Bella', '2019-11-11', 'Sit, Stay, Roll'),
('Cat', 'Oliver', '2020-06-30', 'Meow, Scratch, Jump');
--Добавляю данные
INSERT INTO PackAnimals (Type, Name, BirthDate, Commands) VALUES
('Horse', 'Thunder', '2015-07-21', 'Trot, Canter, Gallop'),
('Camel', 'Sandy', '2016-11-03', 'Walk, Carry Load'),
('Donkey', 'Eeyore', '2017-09-18', 'Walk, Carry Load, Bray'),
('Horse', 'Storm', '2014-05-05', 'Trot, Canter'),
('Camel', 'Dune', '2018-12-12', 'Walk, Sit'),
('Donkey', 'Burro', '2019-01-23', 'Walk, Bray, Kick'),
('Horse', 'Blaze', '2016-02-29', 'Trot, Jump, Gallop'),
('Camel', 'Sahara', '2015-08-14', 'Walk, Run');
--Удаляю записи о верблюдах
DELETE FROM PackAnimals WHERE Type = 'Camel';
--Создам новую таблицу для объединения лошадей и ослов
CREATE TABLE CombinedAnimals (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(50),
    Name VARCHAR(50),
    BirthDate DATE,
    Commands VARCHAR(255)
);
--Заполню её данными о лошадях и ослах из таблицы PackAnimals
INSERT INTO CombinedAnimals (Type, Name, BirthDate, Commands)
SELECT Type, Name, BirthDate, Commands FROM PackAnimals
WHERE Type IN ('Horse', 'Donkey');
--Создам таблицу для животных в возрасте от 1 до 3 лет
CREATE TABLE YoungAnimals (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(50),
    Name VARCHAR(50),
    BirthDate DATE,
    Commands VARCHAR(255),
    Age INT
);
--Заполню ее данными о животных которые находятся в этом возрасте
INSERT INTO YoungAnimals (Type, Name, BirthDate, Commands, Age)
SELECT Type, Name, BirthDate, Commands,
    TIMESTAMPDIFF(YEAR, BirthDate, CURDATE()) AS Age
FROM Pets
WHERE TIMESTAMPDIFF(YEAR, BirthDate, CURDATE()) BETWEEN 1 AND 3
UNION
SELECT Type, Name, BirthDate, Commands,
    TIMESTAMPDIFF(YEAR, BirthDate, CURDATE()) AS Age
FROM CombinedAnimals
WHERE TIMESTAMPDIFF(YEAR, BirthDate, CURDATE()) BETWEEN 1 AND 3;
--Объединю все созданные таблицы в одну, сохраняя информацию о принадлежности к исходным таблицам
CREATE TABLE AllAnimals (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    SourceTable VARCHAR(50),
    Type VARCHAR(50),
    Name VARCHAR(50),
    BirthDate DATE,
    Commands VARCHAR(255)
);
--Заполню её данными
INSERT INTO AllAnimals (SourceTable, Type, Name, BirthDate, Commands)
SELECT 'Pets', Type, Name, BirthDate, Commands FROM Pets
UNION ALL
SELECT 'PackAnimals', Type, Name, BirthDate, Commands FROM PackAnimals
UNION ALL
SELECT 'CombinedAnimals', Type, Name, BirthDate, Commands FROM CombinedAnimals
UNION ALL
SELECT 'YoungAnimals', Type, Name, BirthDate, Commands FROM YoungAnimals;
