drop database WorkBoard;
create database WorkBoard;
use WorkBoard;


create table state(
	code char(4) not null,
	name nvarchar(255) not null,
	isactive bit not null default 1,
	
	primary key(code)
);

-- insert into state(code, name, isactive) values('ACTI', 'Active', 1);
-- insert into state(code, name, isactive) values('DELE', 'Deleted', 1);
-- insert into state(code, name, isactive) values('USED', 'Used', 1);

create table type(
	code char(4) not null,
	name nvarchar(255) not null,
	isactive bit not null default 1,
	
	primary key(code)
);
-- insert into type(code, name, isactive) values('REGI', 'Registration', 1);
-- insert into type(code, name, isactive) values('PSSW', 'Password', 1);

-- drop table company;
create table company(
	idx int not null auto_increment,
	name nvarchar(255) not null,
	state char(4) not null default 'ACTI',
	create_date datetime not null default now(),
    last_update datetime null,
	
	primary key(idx),
	foreign key (state) references state (code)
);
-- drop table user;
create table user(
	idx int not null auto_increment,
	id nvarchar(255) not null,
	name nvarchar(255) not null,
	img longblob null,
	company int not null,
	state char(4) not null default 'ACTI',
	isAdmin bit not null,
	create_date datetime not null default now(),
    last_update datetime null,
	
	primary key (idx),
	foreign key (company) references company (idx),
	foreign key (state) references state (code)
);
-- drop table password;
create table password(
    idx int not null auto_increment,
    user int not null,
    password nvarchar(255) not null,
    state char(4) not null default 'ACTI',
    create_date datetime not null default now(),
    last_update datetime null,

    primary key (idx),
    foreign key (user) references user(idx),
    foreign key (state) references state (code)
);

-- uuidGenerator
create table uuidGenerator(
	idx int not null auto_increment,
	email nvarchar(255) not null,
	uuid nvarchar(255) not null,
	state char(4) not null default 'ACTI',
	type  char(4) not null,
	create_date datetime not null default now(),
    last_update datetime null,
    
    primary key (idx),
    foreign key (state) references state (code),
    foreign key (type) references type (code)
);

-- menu
create table menu(
	code char(4) not null,
	name nvarchar(255) not null,
	url nvarchar(255) not null,
	subMenu char(4) null,
	isactive bit not null default 1,
	
	primary key(code),
	foreign key (subMenu) references menu (code)
);

insert into menu (code, name, url, subMenu, isAdmin, isactive) values('PBLC', 'Public', null, null, 0, 1);
insert into menu (code, name, url, subMenu, isAdmin, isactive) values('PBLA', 'All Open', '/public/index.html', 'PBLC', 0, 1);
insert into menu (code, name, url, subMenu, isAdmin, isactive) values('GRUP', 'Group', null, null, 0, 1);
insert into menu (code, name, url, subMenu, isAdmin, isactive) values('GRPA', 'All Open', '/group/index.html', 'GRUP', 0, 1);
insert into menu (code, name, url, subMenu, isAdmin, isactive) values('CHAT', 'Message Chat', '/chat/index.html', null, 0, 1);
insert into menu (code, name, url, subMenu, isAdmin, isactive) values('STTN', 'Setting', null, null, 0, 1);
insert into menu (code, name, url, subMenu, isAdmin, isactive) values('PRFL', 'Profile', '/setting/profile.html', 'STTN', 0, 1);
insert into menu (code, name, url, subMenu, isAdmin, isactive) values('PRMS', 'Permission', '/setting/Permission.html', 'STTN', 1, 1);
