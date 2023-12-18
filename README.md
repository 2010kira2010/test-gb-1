# Итоговая контрольная работа

## Информация о проекте
Необходимо организовать систему учета для питомника в котором живут
домашние и вьючные животные.

## Как сдавать проект
Для сдачи проекта необходимо создать отдельный общедоступный
репозиторий(Github, gitlub, или Bitbucket). Разработку вести в этом
репозитории, использовать пул реквесты на изменения. Программа должна
запускаться и работать, ошибок при выполнении программы быть не должно.
Программа, может использоваться в различных системах, поэтому необходимо
разработать класс в виде конструктора

## Задание
1. Используя команду cat в терминале операционной системы Linux, создать
   два файла Домашние животные (заполнив файл собаками, кошками,
   хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и
   ослы), а затем объединить их. Просмотреть содержимое созданного файла.
   Переименовать файл, дав ему новое имя (Друзья человека).
   ![Question 1](https://github.com/2010kira2010/test-gb-1/blob/main/screenshots/1.png?raw=true)
2. Создать директорию, переместить файл туда.
   ![Question 2](https://github.com/2010kira2010/test-gb-1/blob/main/screenshots/2.png?raw=true)
3. Подключить дополнительный репозиторий MySQL. Установить любой пакет
   из этого репозитория.
   ![Question 3](https://github.com/2010kira2010/test-gb-1/blob/main/screenshots/3.png?raw=true)
4. Установить и удалить deb-пакет с помощью dpkg.
   ![Question 4](https://github.com/2010kira2010/test-gb-1/blob/main/screenshots/4.png?raw=true)
5. Выложить [историю](https://github.com/2010kira2010/test-gb-1/blob/main/HistoryCommands.md) команд в терминале ubuntu
   ![Question 5](https://github.com/2010kira2010/test-gb-1/blob/main/screenshots/5.png?raw=true)
6. Нарисовать [диаграмму](https://github.com/2010kira2010/test-gb-1/blob/main/animals.drawio), в которой есть класс родительский класс, домашние
   животные и вьючные животные, в составы которых в случае домашних
   животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
   войдут: Лошади, верблюды и ослы).
   ![Question 6](https://github.com/2010kira2010/test-gb-1/blob/main/screenshots/6.png?raw=true)
7. В подключенном MySQL репозитории создать базу данных “Друзья
   человека”
```sql
CREATE DATABASE `friends_of_man`;
```
8. Создать таблицы с иерархией из диаграммы в БД
```sql
USE friends_of_man;
CREATE TABLE animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	name VARCHAR(255)
);

INSERT INTO animals (name)
VALUES ('Домашние'),
('Вьючные');  


CREATE TABLE pack_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (255),
    animals_id INT,
    FOREIGN KEY (animals_id) REFERENCES animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pack_animals (name, animals_id)
VALUES ('Лошади', 2),
('Верблюды', 2),  
('Ослы', 2); 
    
CREATE TABLE home_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (255),
    animals_id INT,
    FOREIGN KEY (animals_id) REFERENCES animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO home_animals (name, animals_id)
VALUES ('Собаки', 1),
('Кошки', 1),  
('Хомяки', 1); 
```
9. Заполнить низкоуровневые таблицы именами(животных), командами
   которые они выполняют и датами рождения
```sql
CREATE TABLE dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(50), 
    birthday DATE,
    commands VARCHAR(255),
    animals_id int,
    Foreign KEY (animals_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO dogs (name, birthday, commands, animals_id)
VALUES ('Чарли', '2023-12-01', 'лежать, лапу, голос', 1),
('Чапа', '2021-12-02', 'сидеть, лежать', 1);

CREATE TABLE cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(50), 
    birthday DATE,
    commands VARCHAR(255),
    animals_id int,
    Foreign KEY (animals_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO cats (name, birthday, commands, animals_id)
VALUES ('Басик', '2023-12-03', 'кис-кис,брысь', 2),
('Рыжик', '2021-12-04', 'кис-кис,брысь', 2);

CREATE TABLE hamsters 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(50), 
    birthday DATE,
    commands VARCHAR(255),
    animals_id int,
    Foreign KEY (animals_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO hamsters (name, birthday, commands, animals_id)
VALUES ('Грызун', '2023-12-05', '', 3),
('Пират', '2021-12-06', '', 3);

CREATE TABLE horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    animals_id int,
    Foreign KEY (animals_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO horses (name, birthday, commands, animals_id)
VALUES ('Гром', '2023-12-07', 'стоп,галоп', 1), 
('Молния', '2021-12-08', 'стоп,галоп', 1);

CREATE TABLE camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    animals_id int,
    Foreign KEY (animals_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO camels (name, birthday, commands, animals_id)
VALUES ('Фрум', '2023-12-09', 'стоп', 2),
('Герро', '2021-12-10', 'стоп', 2);

CREATE TABLE donkeys 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    animals_id int,
    Foreign KEY (animals_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO donkeys (name, birthday, commands, animals_id)
VALUES ('Персик', '2023-12-11', 'стоп', 3),
('Шир', '2021-12-12', 'стоп', 3);
```
10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
    питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
```sql
DELETE FROM camels;

CREATE TABLE horses_donkeys AS
SELECT * FROM horses
UNION ALL
SELECT * FROM donkeys;
```
11. Создать новую таблицу “молодые животные” в которую попадут все
    животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
    до месяца подсчитать возраст животных в новой таблице.
```sql
CREATE TEMPORARY TABLE animals_tmp AS 
SELECT *, 'Лошади' as class FROM horses
UNION SELECT *, 'Ослы' AS class FROM donkeys
UNION SELECT *, 'Собаки' AS class FROM dogs
UNION SELECT *, 'Кошки' AS class FROM cats
UNION SELECT *, 'Хомяки' AS class FROM hamsters;

CREATE TABLE young_animals AS
SELECT name, birthday, commands, class, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age
FROM animals_tmp WHERE birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
```
12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
    прошлую принадлежность к старым таблицам.
```sql
CREATE TABLE all_animals AS
SELECT h.name, h.birthday, h.commands, pa.animals_id, ya.age 
FROM horses h
LEFT JOIN young_animals ya ON ya.name = h.name
LEFT JOIN pack_animals pa ON pa.Id = h.animals_id
UNION 
SELECT d.name, d.birthday, d.commands, pa.animals_id, ya.age 
FROM donkeys d 
LEFT JOIN young_animals ya ON ya.name = d.name
LEFT JOIN pack_animals pa ON pa.Id = d.animals_id
UNION
SELECT c.name, c.birthday, c.commands, ha.animals_id, ya.age 
FROM cats c
LEFT JOIN young_animals ya ON ya.name = c.name
LEFT JOIN home_animals ha ON ha.Id = c.animals_id
UNION
SELECT d.name, d.birthday, d.commands, ha.animals_id, ya.age 
FROM dogs d
LEFT JOIN young_animals ya ON ya.name = d.name
LEFT JOIN home_animals ha ON ha.Id = d.animals_id
UNION
SELECT hm.name, hm.birthday, hm.commands, ha.animals_id, ya.age 
FROM hamsters hm
LEFT JOIN young_animals ya ON ya.name = hm.name
LEFT JOIN home_animals ha ON ha.Id = hm.animals_id;
```
13. Создать класс с Инкапсуляцией методов и наследованием по диаграмме.

14. Написать программу, имитирующую работу реестра домашних животных.
        В программе должен быть реализован следующий функционал:
    ```
    14.1 Завести новое животное
    14.2 определять животное в правильный класс
    14.3 увидеть список команд, которое выполняет животное
    14.4 обучить животное новым командам
    14.5 Реализовать навигацию по меню
    ```

15. Создайте класс Счетчик, у которого есть метод add(), увеличивающий̆
    значение внутренней̆ int переменной̆на 1 при нажатие “Завести новое
    животное” Сделайте так, чтобы с объектом такого типа можно было работать в
    блоке try-with-resources. Нужно бросить исключение, если работа с объектом
    типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение
    считать в ресурсе try, если при заведения животного заполнены все поля.