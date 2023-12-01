# tennis-kata
Technologies Utilisées :
- Spring Boot 2.04
- Angular 7
- HsqlDb 2.4.1
- modelmapper 2.3.2

Il y'a deux modules maven 
 - tennis-web : frontend avec angular
 - tennis-server : backend avec Spring Boot et HSQLDB en mode fichier.

Pour lancer les tests : mvn test
Il faut lancer "mvn package" dans le module parent avant de lancer l'application.
Pour lancer l'application il faut aller dans le module tennis-server et exécuter: mvn  spring-boot:run
