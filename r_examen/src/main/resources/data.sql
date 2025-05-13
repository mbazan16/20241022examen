
INSERT INTO REGIONS (REGION_ID, REGION_NAME)
VALUES (1, 'Europe');

INSERT INTO REGIONS (REGION_ID, REGION_NAME)
VALUES (2, 'Americas');


-- Inserciones para la tabla COUNTRIES
INSERT INTO COUNTRIES (COUNTRY_ID, COUNTRY_NAME, REGION_ID)
VALUES ('US', 'United States of America', 2);

INSERT INTO COUNTRIES (COUNTRY_ID, COUNTRY_NAME, REGION_ID)
VALUES ('DE', 'Germany', 1);

INSERT INTO COUNTRIES (COUNTRY_ID, COUNTRY_NAME, REGION_ID)
VALUES ('IT', 'Italy', 1);


INSERT INTO LOCATIONS (LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID)
VALUES (1700, '2004 Charade Rd', '98199', 'Seattle', 'Washington', 'US');

INSERT INTO LOCATIONS (LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID)
VALUES (2400, '8204 Arthur St', '21021', 'Baltimore', 'Maryland', 'US');

INSERT INTO LOCATIONS (LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID)
VALUES (2500, 'Magazinweg 28', '63729', 'Frankfurt', 'Hessen', 'DE');

INSERT INTO LOCATIONS (LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID)
VALUES (2700, 'Schwanthalerstr. 7031', '80336', 'Munich', 'Bavaria', 'DE');

INSERT INTO LOCATIONS (LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID)
VALUES (2800, 'Via della Luna 101', '00144', 'Rome', 'Lazio', 'IT');







INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (10, 'Administración', 200, 1700);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (20, 'Marketing', 201, 2800);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (30, 'Puestos Directivos', 114, 1700);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (40, 'Recursos Humanos', 203, 2400);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (50, 'Finanzas', 204, 1700);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (60, 'Tecnología', 205, 1700);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (70, 'Soporte Técnico', 206, 1700);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (80, 'Ventas', 207, 2500);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (90, 'Logística', 208, 2700);
INSERT INTO departments (department_id,department_name, manager_id,location_id) VALUES (100, 'Compras', 209, 2800);

