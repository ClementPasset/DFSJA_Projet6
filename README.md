# P6 - Développez une application full-stack complète

Ce repo correspond au code du projet 6 de la formation Développeur Full-Stack Java/Angular.\
Ci-dessous les différentes étapes qui vont permettre de cloner le projet et de le faire tourner sur votre machine

## Cloner le projet

Tout d'abord, il faudra cloner le repository, grâce à la commande suivante : 

`git clone https://github.com/ClementPasset/DFSJA_Projet6.git`

## Configurer le projet

Pour la partie base de données, il faudra configurer comme il faut les credentials pour le back-end.\
* Possibilité de modifier le fichier `application.properties` pour y inscrire en dur les éléments de connexion (déconseillé)
* Utiliser des variables d'environnement pour configurer l'URL d'accès, l'identifiant et le mot de passe

Également, il faudra configurer la clé utilisée pour les Json Web Token, de la même manière

## Lancer le back-end

Grâce à la commande suivante, le serveur back-end tournera en local sur le port 9000 :\
*Attention à être dans le bon dossier : back/*

`mvn spring-boot:run`

## Lancer le front-end

Tout d'abord, il faudra télécharger les dépendances :\
*Attention à être dans le bon dossier : front/*

`npm i`

Enfin vous pourrez lancer le front en local, sur le port 4200, grâce à la commande suivante :\
*Attention à être dans le bon dossier : front/*

`npm run start`

## Insérer des données dans la base de données

Pour que l'application fonctionne, nous avons besoins d'insérer certaines données.\
Les articles et les utilisateurs peuvent être créés depuis l'application front-end, mais il faut à minima charger des thèmes en base de données.

Des données d'exemple peuvent être chargées grâce au fichier `data.sql` qui se trouve dans le dossier `./back/src/main/resources/`