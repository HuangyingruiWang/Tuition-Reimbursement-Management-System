drop table if exists files;
drop table if exists events;
drop table if exists status;
drop table if exists gradeformat;
drop table if exists eventtype;
drop table if exists supervisors;
drop table if exists heads;
drop table if exists users;
drop table if exists login;
drop table if exists departments;



create table if not exists departments (
	d_id serial primary key,
	type varchar(50) unique --('Department a', 'Department b')),
);

create table if not exists login(
	l_id serial primary key,
	username varchar(50) unique not null,
	password varchar(50) not null
);

create table if not exists users (
	u_id serial primary key,
	name varchar(50) unique not null,
	email varchar(75) unique not null,
	isBenCo boolean default false,
	department_fk int not null references departments(d_id),
	log_fk int unique references login(l_id)
);	

create table if not exists heads (
	department_fk int primary key,
	departmenthead_fk int unique references users(u_id)
);

create table if not exists supervisors (
	user_id int primary key,
	supervisors_id int
);


create table if not exists eventtype(
	t_id serial primary key,
	type varchar(100),
	percetage numeric(4,2)
);

create table if not exists gradeformat(
	g_id serial primary key,
	cutoff_grade varchar(50),
	format varchar(50)
);

create table if not exists status(
	s_id serial primary key,
	name varchar(100) unique not null
);

create table if not exists events (
	e_id serial primary key,
	date bigint not null, -- event begin
 	location varchar(75),
	email_address varchar(75),
	description text,
	cost numeric(12, 2) check (cost > 0 and cost <= 1000),
	actual_grade varchar(50) default null,
	format int default 1 references gradeformat(g_id),
	type int references eventtype(t_id),
	exceeding_available boolean default false,
	work_related_justification boolean default true,
	last_update bigint not null,
	status_id int default 1 references status(s_id), 
	user_fk int references users(u_id) on delete cascade
);

create table if not exists files (
	file_id serial primary key,
	event_fk int references events(e_id), 
	uploader_id int references users(u_id),
	requirer_id int references users(u_id),
	filename varchar(100) default null,
	file bytea default null
);
-- status table
insert into status values(default,'new submission'),(default,'supervisor approval'),
(default,'department header approval'),(default,'benefits coordinator approval'),
(default,'grade updated'),(default,'presentation updated'),
(default,'supervisor approval presentation'),(default,'benefits coordinator final approval, money out'),
(default,'denied'),(default, 'supervisor requests employee to provided additional documents'),
(default, 'department header requests employee to provided additional documents'),(default, 'department header requests supervisor to provided additional documents'),
(default, 'benefits coordinator requests employee to provided additional documents'),(default, 'benefits coordinator requests supervisor to provided additional documents'),
(default, 'benefits coordinator requests department header to provided additional documents'),
(default, 'benefits coordinator changes the reimbursement amount'),(default, 'auto-denied');
-- gradeformat table
insert into gradeformat values(default, 'pass', 'pass/fail'), (default, 'fail', 'pass/fail'),
(default, 'A', 'letter grade'), (default, 'B', 'letter grade'), (default, 'C', 'letter grade'), (default, 'D', 'letter grade'),
(default, 'F', 'letter grade'), (default, 'pass presentation', 'presentation'),(default, 'fail presentation', 'presentation');
-- event type table
insert into eventtype values(default, 'University Courses', 0.80),(default, 'Seminars', 0.60),
(default, 'Certification Preparation Classes', 0.75),(default, 'Certification', 1.00),(default, 'Technical Training', 0.90),
(default, 'Other', 0.30);
-- departments table
insert into departments values(default, 'Computer Science Department'), (default, 'Mathematics Department'),
(default, 'English Department'),(default, 'Test Department');
-- login table
insert into login values(default, 'Chris', 'Chris123'), (default, 'CS', 'CS123'),
(default, 'Math', 'Math123'),(default, 'English', 'English123'), (default, 'Test1', '123'),
(default, 'Test2', '123'),(default, 'CS1', '123'),(default, 'CS2', '123'),
(default, 'Math1', '123'),(default, 'Math2', '123');
-- users table
insert into users values(default, 'Chris', 'Chris@123.com', true, 4, 1), (default, 'CS', 'CS@123.com', default, 1, 2),
(default, 'Math', 'Math@123.com', default, 2, 3),(default, 'English', 'English@123.com', default, 3, 4),
(default, 'Test1', 'Test1@123.com', default, 4, 5),(default, 'Test2', 'Test3@123.com', default, 4, 6),
(default, 'CS1', 'CS1@123.com', default, 1, 7),(default, 'CS2', 'CS2@123.com', default, 1, 8),
(default, 'Math1', 'Math1@123.com', default, 2, 9),(default, 'Math2', 'Math2@123.com', default, 2, 10);
-- head table
insert into heads values(1, 2),(2, 3),(3, 4),(4, 1);
-- supervisors table
insert into supervisors values(1,8),(2,1),(3,1),(4,1),(5,1),(6,1),(7,2),(8,2),(9,3),(10,3);

-- events table
insert into events values
(default, 1636610400, '123 Street', '123@123.com','English Class',400,default,default,1,default,default,1635193764,default,1),
(default, 1637469998, '1234 Street', '123@123.com','English Class',300,default,default,1,default,default,1635193764,default,1),
(default, 1636610400, '1235 Street', '123@123.com','English Class',200,default,default,1,default,default,1635193764,default,1),
(default, 1637469998, '1236 Street', '123@123.com','English Class',100,default,default,1,default,default,1635193764,default,2),
(default, 1637469998, '1237 Street', '123@123.com','English Class',500,default,default,1,default,default,1635193764,default,2),
(default, 1637469998, '1238 Street', '123@123.com','English Class',400,default,default,1,default,default,1635193764,default,2),
(default, 1637469998, '1239 Street', '123@123.com','English Class',300,default,default,1,default,default,1635193764,default,3),
(default, 1637469998, '1230 Street', '123@123.com','English Class',200,default,default,1,default,default,1635193764,default,4),
(default, 1637469998, '1231 Street', '123@123.com','English Class',100,default,default,1,default,default,1635193764,default,5),
(default, 1637469998, '1232 Street', '123@123.com','English Class',600,default,default,1,default,default,1635193764,default,6),
(default, 1637469998, '1233 Street', '123@123.com','English Class',500,default,default,1,default,default,1635193764,default,7),
(default, 1637469998, '1234 Street', '123@123.com','English Class',400,default,default,1,default,default,1635193764,default,4),
(default, 1636610400, '1235 Street', '123@123.com','English Class',300,default,default,1,default,default,1635193764,default,4),
(default, 1637469998, '1235 Street', '123@123.com','English Class',300,default,default,1,default,default,1635193764,default,4),
(default, 1637469998, '1235 Street', '123@123.com','English Class',300,default,default,1,default,default,1635193764,default,9),
(default, 1637469998, '1236 Street', '123@123.com','English Class',200,default,default,1,default,default,1635193764,default,10);
