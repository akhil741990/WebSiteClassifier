create database webclassifier;

use webclassifier;

create table web_page_details (
	id int primary key AUTO_INCREMENT ,
    url varchar(200),
	content text
);


ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
