# proxibanquev3

Bienvenue dans ProxiBanqueSI Version 3.


Prérequis. 

Avant de lancer ProxiBanqueSi, vous devez vous assurer que Java est bien installé sur votre appareil. 
Si ce n'est pas le cas, vous pouvez le télécharger à cette adresse : https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html 
Pensez bien à vérifier le niveau de votre Système d'exploitation avant de télécharger. Vous pouvez vérifier votre système d'exploitation sous Windows dans Panneau de Configuration -> Système et Sécurité -> Système. 
Votre niveau de Système correspond à l'indication 32bits ou 64bits, et vous devez télécharger la distribution de Java correpondante. 
Vous devez également avoir un outil pour décompresser les fichiers installé sur votre ordinateur. Si vous n'en avez pas, vous pouvez en télécharger un gratuitement ici : https://www.7-zip.org/ 
De même téléchargez bien la version correspondant à votre niveau de Système. 

Afin d'utiliser l'application de manière optimale, nous vous recommendons d'utiliser Google Chrome. 
Vous pouvez télécharger Google Chrome ici : https://www.google.com/chrome/. Encore une fois n'oubliez pas de vérifier le niveau de votre Système. 

Pour information, l'application utilise un système de log pour vérifier son bon déroulement. Pour y avoir accès, il suffit de créer un dossier "logs" dans C://Programmes
L'application créera un fichier "proxibanquev3.log" dans ce dossier logs et enregistrera les messages dedans.

Afin de pouvoir profiter de l'application, il est nécessaire de télécharger et d'installer Tomcat. Il est téléchargeable à l'adresse suivante : https://tomcat.apache.org/download-80.cgi
Veuillez choisir l'installation correspondante à votre système d'exploitation dans "Binary Distributions --> Core". 
Par exemple, pour Windows 7 ou supérieur, veuillez cliquer sur "64-bit Windows.zip". Une fois l'archive téléchargée, veuillez l'extraire sur votre PC, le dossier de destination n'ayant pas d'importance.

Il est également nécessaire de définir une nouvelle variable d'environnement "JRE_HOME". Pour cela, dans l'explorateur de fichiers, faites clic droit sur "Ce PC" -> Propriétés. 
Selectionnez "Paramètres système avancés" sur la gauche. Dans la fenêtre ouverte, choisir "variables d'environnement". 
Sous le tableau "Variables système", Cliquer sur nouvelle : En "Nom de variable" indiquer JRE_HOME et en chemin de variable, il faut récupérer le chemin vers l'installation du jre ( par défaut C:\Programmes\java\jrexxx ).

Pour avoir accès aux données client, veuillez télécharger l'application MySQL à l'adresse suivante : https://dev.mysql.com/downloads/windows/installer/8.0.html. 
Selectionnez l'installation à 313Mo. Bien vérifier que MySQL Workbench est installé.


Exécution. 

Afin de deployer l'application ProxiBanque, veuillez coller l'archive "proxibanque3_Sarah_Quentin.war" dans le dossier webapps de votre dossier apache-tomcat-8.5.35. 
Ensuite, allez dans le dossier bin et double cliquez sur le fichier "startup.bat". Attendre qu'un dossier proxibanquev3 apparaisse dans le dossier webapps de Tomcat.
Veuillez ensuite lancer MySQL Workbench et créer un nouveau schéma nommé "proxibanquev3" (grâce à la 4ème icone en haut en forme de base de données). Ensuite, selectionner "Server" dans le menu supérieur puis "Data import". 
Selectionner l'option "Import from Self-Contained File et renseigner le chemin vers le fichier str.sql fourni. indiquer "proxibanquev3" dans "Default Target Schema". 
Enfin, répéter l'opération précédente avec le script "data.sql" fourni afin d'importer les données proprement dites.

Pour lancer l'application, lancer chrome et rentrer l'adresse suivante : http://localhost:8080/proxibanquev2.

Bonne navigation !!!


Utilisation. 

ProxiBanqueSI est une application permettant à un client de se connecter à son espace en ligne. 

Il existe actuellement les clients suivants dans la base de données de l'application:
-Sarah Ourabah
-Quentin Jouhault
-Jean Peplu
-Alain Terieur
-Harry Potter (à des fins de test, ce nom est présent en double dans la base de données et donc ne pourra pas se connecter à l'application).

Une fois connecté sous le nom d'un client, il sera possible d'explorer l'ensemble des possibilités de l'application grâce aux différents boutons.

/!\remarque importante/!\ pour les options de retrait, le retrait liquide est limité à 300 € et le virement entre comptes est limité à 900€.

Une fois que votre usage de l'application touche à son terme, lancez le fichier "shutdown.bat" dans le même dossier que "startup.bat"(Apache Tomcat/bin).



Ressources complémentaires :
-Le Diagramme de Classe;
-Les scripts sql pour générer la base de données.

Il est également possible de consulter le code de ProxiBanqueSI V3 à l'adresse suivante : https://github.com/Khantain/proxibanquev3

Merci d'avoir lu ce document. Nous vous souhaitons une bonne expérience avec ProxiBanqueSI.