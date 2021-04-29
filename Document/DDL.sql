create database WorkBoard;
use WorkBoard;


create table state(
	code char(4) not null,
	name nvarchar(255) not null,
	isactive bit not null default 1,
	
	primary key(code)
);

-- insert into state(code, name) values('ACTI', 'Active', 1);
-- insert into state(code, name) values('DELE', 'Deleted', 1);
-- drop table company;
create table company(
	code char(5) not null,
	name nvarchar(255) not null,
	state char(4) not null default 'ACTI',
	create_date datetime not null default now(),
    last_update datetime null,
	
	primary key(code),
	foreign key (state) references state (code)
);
-- drop table user;
create table user(
	id nvarchar(255) not null,
	name nvarchar(255) not null,
	img longblob null,
	company char(5) not null,
	state char(4) not null default 'ACTI',
	create_date datetime not null default now(),
    last_update datetime null,
	
	primary key (id),
	foreign key (company) references company (code),
	foreign key (state) references state (code)
);
-- drop table password;
create table password(
    idx int not null auto_increment,
    id nvarchar(255) not null,
    password nvarchar(255) not null,
    state char(4) not null default 'ACTI',
    create_date datetime not null default now(),

    primary key (idx, id),
    foreign key (id) references user(id),
    foreign key (state) references state (code)
);