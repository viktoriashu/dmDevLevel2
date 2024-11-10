INSERT INTO users(id, first_name, last_name, login, password, phone_number, role)
VALUES (1, 'Владимир', 'Павлов', 'pablo', 'pablitto', '89309302723', 'USER'),
       (2, 'Денис', 'Адамов', 'adam', 'damadam', '89309307891', 'USER'),
       (3, 'Максим', 'Тимофеев', 'tim', 'timtim', '89209307891', 'USER'),
       (4,'Страхиня', 'Эракович', 'hz', 'hzhz','89209307820', 'ADMIN');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users))