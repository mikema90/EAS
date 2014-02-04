CREATE DATABASE  IF NOT EXISTS `eas` /*!40100 DEFAULT CHARACTER SET utf8 */;
create user 'easadmin' identified by 'abcdefg';
grant all on eas.* to easadmin@'%' identified by 'abcdefg';
grant all on eas.* to easadmin@'localhost' identified by 'abcdefg';
