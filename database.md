pls read this for our database: 

follow video 14 with the following alterations: 
- when in root user and create database --> 
 	
	create database projectdb;
	
- when 'create user', do the following: --> 
        
	create user team3@localhost identified by 'team3';
        grant all on project.* to team3@localhost;
	
- then exit root user and go to team3 
         
	 use projectdb;
         create table walle(id int auto_increment primary key not null, speed int not null, turnangle int not null, maxobs int not null, securitydis float (2,2) not null)engine=InnoDB;
         desc walle (just to see if all the changed are done) 
	 

in video 15 just change the appengine-web.html file as: 
-     <property name="localusername" value="team3"/>
	    <property name="localpassword" value="team3"/>
      (so just change the localuser and localpassword)
