create table user (
  user_id bigint not null auto_increment, 
  create_date datetime, 
  modified_date datetime, 
  auth varchar(32) default 'User', 
  birth varchar(20) not null, 
  name varchar(20) not null, 
  password varchar(255) not null, 
  phone varchar(20) not null,
  username varchar(100) not null, 
  primary key (user_id) 
);

create table analyst (
  contents varchar(255), 
  user_id bigint not null, 
  img VARCHAR(255),
  primary key (user_id)
);

alter table analyst add constraint FKn9mpqegubp8yhvgov699dhm00 foreign key (user_id) references user (user_id);
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);

create table video_list(
  list_id bigint(20) auto_increment NOT NULL,
  user_id bigint(20),
  FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
  avg_score float,
  video_id bigint(20),
  PRIMARY KEY (list_id)
);

create table video(
  video_id bigint(20) auto_increment NOT NULL,
  title VARCHAR(255) NOT NULL,
  url VARCHAR(255) NOT NULL,
  list_id bigint(20),
  graph VARCHAR(255),
  FOREIGN KEY (list_id) REFERENCES video_list(list_id) ON DELETE CASCADE,
  PRIMARY KEY (video_id)
);

create table comment(
  comment_id bigint(20) auto_increment NOT NULL,
  list_id bigint(20),
  FOREIGN KEY (list_id) REFERENCES video_list(list_id) ON DELETE CASCADE,
  user_id bigint(20),
  FOREIGN KEY (user_id) REFERENCES analyst(user_id) ON DELETE CASCADE,
  contents longtext NOT NULL,
  score int(11) default 0 NOT NULL,
  PRIMARY KEY (comment_id)
);

create table persistent_logins (
  series varchar(64) not null,
  last_used datetime not null, 
  token varchar(64) not null, 
  username varchar(64) not null, 
  primary key (series)
);

create table notice(
  id bigint(20) auto_increment NOT NULL,
  title VARCHAR(255) NOT NULL,
  content VARCHAR(255) NOT NULL,
  views int(11) default 0,
  PRIMARY KEY (id)
);

create table faq(
  faq_id bigint(20) auto_increment NOT NULL,
  title VARCHAR(255) NOT NULL,
  contents VARCHAR(255) NOT NULL,
  PRIMARY KEY (faq_id)
);