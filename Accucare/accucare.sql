DROP DATABASE IF EXISTS accucare;
CREATE DATABASE accucare;
USE accucare;

CREATE TABLE student (
  studentid int NOT NULL,
  contactno varchar(255) DEFAULT NULL,
  studentname varchar(255) DEFAULT NULL,
  PRIMARY KEY (studentid)
);

CREATE TABLE patient_reg (
  patientid int NOT NULL,
  address varchar(255) DEFAULT NULL,
  age int DEFAULT NULL,
  gender varchar(255) DEFAULT NULL,
  patientname varchar(255) DEFAULT NULL,
  phoneno varchar(255) DEFAULT NULL,
  PRIMARY KEY (patientid)
);

CREATE TABLE inventory_item (
  itemid int NOT NULL,
  item_name varchar(255) DEFAULT NULL,
  quantity int DEFAULT NULL,
  unit_price decimal(38,2) DEFAULT NULL,
  vendorname varchar(255) DEFAULT NULL,
  PRIMARY KEY (itemid)
);

CREATE TABLE bill (
  bill_id int NOT NULL,
  bill_date date DEFAULT NULL,
  medical_treatment varchar(255) DEFAULT NULL,
  total_price decimal(38,2) DEFAULT NULL,
  patient_id int DEFAULT NULL,
  student_id int DEFAULT NULL,
  PRIMARY KEY (bill_id),
  KEY FKs7tmn6ivdixy3xmnsnvsrb3e9 (patient_id),
  KEY FKnoehrqlmecr18m24os13lm9wa (student_id),
  CONSTRAINT FKnoehrqlmecr18m24os13lm9wa FOREIGN KEY (student_id) REFERENCES student (studentid),
  CONSTRAINT FKs7tmn6ivdixy3xmnsnvsrb3e9 FOREIGN KEY (patient_id) REFERENCES patient_reg (patientid)
);

CREATE TABLE billitem (
  bill_item_id int NOT NULL AUTO_INCREMENT,
  quantity int NOT NULL,
  subtotal decimal(38,2) DEFAULT NULL,
  bill_id int DEFAULT NULL,
  item_id int DEFAULT NULL,
  PRIMARY KEY (bill_item_id),
  KEY FKeskieu5kyyjyhwb9ryx57w545 (bill_id),
  KEY FKa0n7tsoos9p11qiwbtbskjds3 (item_id),
  CONSTRAINT FKa0n7tsoos9p11qiwbtbskjds3 FOREIGN KEY (item_id) REFERENCES inventory_item (itemid),
  CONSTRAINT FKeskieu5kyyjyhwb9ryx57w545 FOREIGN KEY (bill_id) REFERENCES bill (bill_id)
);

DROP DATABASE IF EXISTS accucare;
CREATE DATABASE accucare;
USE accucare;

CREATE TABLE student (
  studentid int NOT NULL,
  contactno varchar(255) DEFAULT NULL,
  studentname varchar(255) DEFAULT NULL,
  PRIMARY KEY (studentid)
);

CREATE TABLE patient_reg (
  patientid int NOT NULL,
  address varchar(255) DEFAULT NULL,
  age int DEFAULT NULL,
  gender varchar(255) DEFAULT NULL,
  patientname varchar(255) DEFAULT NULL,
  phoneno varchar(255) DEFAULT NULL,
  PRIMARY KEY (patientid)
);

CREATE TABLE inventory_item (
  itemid int NOT NULL,
  item_name varchar(255) DEFAULT NULL,
  quantity int DEFAULT NULL,
  unit_price decimal(38,2) DEFAULT NULL,
  vendorname varchar(255) DEFAULT NULL,
  PRIMARY KEY (itemid)
);

CREATE TABLE bill (
  bill_id int NOT NULL,
  bill_date date DEFAULT NULL,
  medical_treatment varchar(255) DEFAULT NULL,
  total_price decimal(38,2) DEFAULT NULL,
  patient_id int DEFAULT NULL,
  student_id int DEFAULT NULL,
  PRIMARY KEY (bill_id),
  KEY FKs7tmn6ivdixy3xmnsnvsrb3e9 (patient_id),
  KEY FKnoehrqlmecr18m24os13lm9wa (student_id),
  CONSTRAINT FKnoehrqlmecr18m24os13lm9wa FOREIGN KEY (student_id) REFERENCES student (studentid),
  CONSTRAINT FKs7tmn6ivdixy3xmnsnvsrb3e9 FOREIGN KEY (patient_id) REFERENCES patient_reg (patientid)
);

CREATE TABLE billitem (
  bill_item_id int NOT NULL AUTO_INCREMENT,
  quantity int NOT NULL,
  subtotal decimal(38,2) DEFAULT NULL,
  bill_id int DEFAULT NULL,
  item_id int DEFAULT NULL,
  PRIMARY KEY (bill_item_id),
  KEY FKeskieu5kyyjyhwb9ryx57w545 (bill_id),
  KEY FKa0n7tsoos9p11qiwbtbskjds3 (item_id),
  CONSTRAINT FKa0n7tsoos9p11qiwbtbskjds3 FOREIGN KEY (item_id) REFERENCES inventory_item (itemid),
  CONSTRAINT FKeskieu5kyyjyhwb9ryx57w545 FOREIGN KEY (bill_id) REFERENCES bill (bill_id)
);

CREATE VIEW latest_patients AS
SELECT 
    patient_reg.patientname, 
    bill.medical_treatment, 
    bill.student_id, 
    bill.total_price, 
    bill.bill_date
FROM bill
INNER JOIN patient_reg ON bill.patient_id = patient_reg.patientid
ORDER BY bill.bill_date DESC;


CREATE VIEW patient_count AS
SELECT COUNT(*) AS noOfPatients
FROM patient_reg;

CREATE VIEW student_count AS
SELECT COUNT(*) AS noOfStudents
FROM student;

CREATE VIEW total_earnings AS
SELECT SUM(total_price) AS total_income 
FROM bill;

CREATE VIEW report AS
SELECT bill_date,SUM(total_price) AS total_income 
FROM bill 
GROUP BY bill_date
ORDER BY bill_date;

CREATE VIEW patient_count AS
SELECT COUNT(*) AS noOfPatients
FROM patient_reg;

CREATE VIEW student_count AS
SELECT COUNT(*) AS noOfStudents
FROM student;

CREATE VIEW total_earnings AS
SELECT SUM(total_price) AS total_income 
FROM bill;

CREATE VIEW report AS
SELECT bill_date,SUM(total_price) AS total_income 
FROM bill 
GROUP BY bill_date
ORDER BY bill_date;