ALTER SESSION SET "_ORACLE_SCRIPT"=true;
drop user video cascade;
CREATE USER video IDENTIFIED BY video
 DEFAULT TABLESPACE USERS
 TEMPORARY TABLESPACE TEMP;
 GRANT CONNECT, RESOURCE, DBA To video;