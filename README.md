# javaWebApp
Library project for school

Used tools  :
 Apache Netbeans IDE 12.0
 Server TomCat 9.0.40
 phpMyAdmin (login : carmelo / mdp : carmelo)
 XAMPP 
 Bootstrap v5.0.0-beta1
 
 
Deployement steps :
 - Generate database 
 - Get all tools

For the webService, the file PRIDSOAP.zip contains all the class of the JAR file created and implemented in the WebSoapClient.war



Access to the application :

Manager General
Login : managerG
Password: root


Librarian  manager Evere
Login :managerE
Password:root


Librarian  manager Anderlecht
Login :managerA
Password:root


Librarian  manager Riche Claire
Login :managerRC
Password:root


Librarian  Anderlecht
Login :bibliothecaireA
Password:root


Librarian  Evere
Login :bibliothecaireE
Password:root


Librarian  Riche-Claire
Login :bibliothecaireRC
Password:root


Reader Evere
Login : Franci
Password: root


Reader Anderlecht
Login : myriamG
Password: root


Reader Riche Claire
Login : Franci
Password: root



Class AddEbookServlet => line 69 :
change XXX by the way to the folder where you want to save created numeric book
File file = new File("XXX",
item.getName())
