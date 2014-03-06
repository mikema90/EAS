DELIMITER $$  
  
DROP FUNCTION IF EXISTS `eas`.`func_get_split_string`$$  
  
CREATE DEFINER=`easadmin`@`localhost` FUNCTION `func_get_split_string`(  
f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8  
BEGIN  
  declare result varchar(255) default '';  
  set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));  
  return result;  
END$$  
  
DELIMITER ; 


DELIMITER $$  
  
DROP FUNCTION IF EXISTS `eas`.`func_get_split_string_total`$$  
  
CREATE DEFINER=`easadmin`@`localhost` FUNCTION `func_get_split_string_total`(  
f_string varchar(1000),f_delimiter varchar(5)  
) RETURNS int(11)  
BEGIN  
  declare returnInt int(11);  
  if length(f_delimiter)=2  then  
     return 1+(length(f_string) - length(replace(f_string,f_delimiter,'')))/2;  
  else      
     return 1+(length(f_string) - length(replace(f_string,f_delimiter,'')));  
  end if;  
END$$  
  
DELIMITER ;  