drop database WorkBoard;
create database WorkBoard;
use WorkBoard;


create table state(
	code char(4) not null,
	name nvarchar(255) not null,
	isactive bit not null default 1,
	
	primary key(code)
);

insert into state(code, name, isactive) values('ACTI', 'Active', 1);
insert into state(code, name, isactive) values('DELE', 'Deleted', 1);
insert into state(code, name, isactive) values('USED', 'Used', 1);

create table type(
	code char(4) not null,
	name nvarchar(255) not null,
	isactive bit not null default 1,
	
	primary key(code)
);
insert into type(code, name, isactive) values('REGI', 'Registration', 1);
insert into type(code, name, isactive) values('PSSW', 'Password', 1);

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
	url nvarchar(255) null,
	parent char(4) null,
	icon varchar(255) null,
	isAdmin bit not null default 0,
	isactive bit not null default 1,
	displayOrder int null,
	primary key(code),
	foreign key (parent) references menu (code)
);

-- group
create table groupteam (
	idx int not null auto_increment,
	name nvarchar(255) not null,
	company int not null,
	state char(4) not null default 'ACTI',
    create_date datetime not null default now(),
    last_update datetime null,
	primary key(idx),
	foreign key (company) references company (idx),
	foreign key (state) references state (code)
);

insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('DSBD', 'DashBoard', 'dashboard/index.html', null, 'fa fa-dashboard', 0, 1, 1);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('WORK', 'Work', null, null, 'fas fa-business-time', 0, 1, 2);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('CHAT', 'Message Chat', 'chat/index.html', null, 'fa fa-wechat', 0, 1, 3);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('STTN', 'Setting', null, null, 'fa fa-gears', 1, 1, 4);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('ADUR', 'User', 'setting/user.html', 'STTN', 'fa fa-user-plus', 1, 1, 5);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('ADWK', 'Project', 'setting/project.html', 'STTN', 'fa fa-file-archive-o', 1, 1, 6);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('ADWJ', 'Create Wizard', 'setting/wizard.html', 'STTN', 'fas fa-hat-wizard', 1, 1, 7);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('PRFL', 'Profile', 'setting/profile.html', 'STTN', 'fa fa-id-card', 1, 1, 8);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('PRMS', 'Permission', 'setting/permission.html', 'STTN', 'fa fa-gear', 1, 1, 9);
insert into menu (code, name, url, parent, icon, isAdmin, isactive, displayOrder) values('GRPS', 'Group', 'setting/group.html', 'STTN', 'fa fa-group', 1, 1, 10);



