pls read this for our database so that we all have to same stuff (makes further work easier for us): 

follow video 14 with the following alterations: 
- when in root user and create database --> 
 	
	create database projectdb;
	
- when 'create user', do the following: --> 
        
	create user team3@localhost identified by 'team3';
        
	grant all on projectdb.* to team3@localhost;
	
- then exit root user and go to team3 
         
	 use projectdb;

         create table walle(id int auto_increment primary key not null, speed int not null, turnangle int not null, maxobs int not null, securitydis float (3,1) not null)engine=InnoDB;
	 
	 desc walle (just to see if all the changes are done) 
	 
	 ![2023-04-29 01_31_56-restful - project_src_main_webapp_html_ev3 html - Eclipse IDE](https://user-images.githubusercontent.com/111871043/235300170-863acc64-a0b4-45af-8bf4-146c85d2d969.png)

	 

in video 15 just change the appengine-web.xml file as: 
-     <property name="localusername" value="team3"/>
	    <property name="localpassword" value="team3"/>
      (so just change the localuser and localpassword)
