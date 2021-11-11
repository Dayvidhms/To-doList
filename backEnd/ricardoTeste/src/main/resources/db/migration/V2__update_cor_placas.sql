ALTER TABLE placas add cor varchar (15);
UPDATE placas SET cor = '#6f8daa' WHERE id > 0;