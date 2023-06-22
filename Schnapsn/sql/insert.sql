--Cards of type HERZ
insert into card (name, type, value)
values  ('Unter', 'HERZ', 2);
insert into card (name, type, value)
values  ('Ober', 'HERZ', 3);
insert into card (name, type, value)
values  ('König', 'HERZ', 4);
insert into card (name, type, value)
values  ('X', 'HERZ', 10);
insert into card (name, type, value)
values  ('Sau', 'HERZ', 11);
--Cards of type SCHELLE
insert into card (name, type, value)
values  ('Unter', 'SCHELLE', 2);
insert into card (name, type, value)
values  ('Ober', 'SCHELLE', 3);
insert into card (name, type, value)
values  ('König', 'SCHELLE', 4);
insert into card (name, type, value)
values  ('X', 'SCHELLE', 10);
insert into card (name, type, value)
values  ('Sau', 'SCHELLE', 11);
--Cards of type PIK
insert into card (name, type, value)
values  ('Unter', 'PIK', 2);
insert into card (name, type, value)
values  ('Ober', 'PIK', 3);
insert into card (name, type, value)
values  ('König', 'PIK', 4);
insert into card (name, type, value)
values  ('X', 'PIK', 10);
insert into card (name, type, value)
values  ('Sau', 'PIK', 11);
--Cards of type EICHEL
insert into card (name, type, value)
values  ('Unter', 'EICHEL', 2);
insert into card (name, type, value)
values  ('Ober', 'EICHEL', 3);
insert into card (name, type, value)
values  ('König', 'EICHEL', 4);
insert into card (name, type, value)
values  ('X', 'EICHEL', 10);
insert into card (name, type, value)
values  ('Sau', 'EICHEL', 11);

--Example Lecture
insert into lecture (LECTUREID, name, content)
values  (0, 'Doppeldeutsche Karten', 'Es gibt 20 Karten. Jeweils 5 von einer Farbe. Es gibt die Farben: PIK, HERZ, SCHELLE und EICHEL. Ausserdem gibt es die Typen: Unter, Ober, König, Zehn und Sau, diese haben die Werte: 2, 3, 4, 10 und 11');

--Example Player
--insert into player (username, password, email, games_played)
--values ('Matse', 'Z3E+0pfOFih2UJZlFGhkOw==', 'm.schablinger@students.htl-leonding.ac.at', 0);