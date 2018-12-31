# Scala-Projet-Tondeuse
Projet M2 logiciels language Scala

# Choix implémentation

  *La pelouse: vu comme un objet ayant 2 attributs (abscisse finale et ordonnée finale qui représentent la limite de sa surface) et une méthode (affichage qui affiche ses coordonnées)
  
  *La tondeuse : objet ayant 3 attibuts (abscisse et ordonnée qui sont des entiers indiquant sa position sur la pelouse et position qui est une lettre indiquant son orientation) et une méthode (affichage qui affiche ses coordonnées)
  
  *La classe principale ParcoursTondeuse qui contient la méthode Main et plusieurs autres méthodes :
    
    Méthode Main : Méthode appelé par le compilateur en premier lors de l'exécution du projet, elle définie l'enchainement du programme de l'initialisation de la pelouse à l'affichage des coordonnées de la dernière tondeuse.
    
    Méthode readFileTry : Méthode qui récupère le contenu du fichier des paramètres du programme, elle utilise Try pour gérér les erreurs qui peuvent survenir lors de l'ouverture ou de la récupération du contenu du fichier.
    
    Méthode initialisationPelouse : Méthode qui initialise les coordonnées de la pelouse dès accès au contenu du fichier des paramètres
    
    Méthode initialisationTondeuse : Méthode qui initialise les coordonnées de la tondeuse dès accès au contenu du fichier des paramètres
    
    Méthode parcoursTotal : Méthode récursive qui permet aux tondeuses de parcourir la pelouse selon les indications données dans le fichier. Elle appelle pas à pas la méthode de parcoursPartiel sur chaque lettre de la chaine du parcours et s'exécute de façon recursive sur la chaine de parcours excepté la lettre déja traiter jusqu'à ce qu'il n'y a plus de lettre à traiter.
    
    Méthode parcoursPartiel : Elle ne prend en compte qu'une lettre de la chaine de parcours et appelle à son tour les méthodes adaptées selon l'orientation de la tondeuse sur la pelouse. Nous avons au total 4 Méthodes (parcoursPartielNord, parcoursPartielSud, parcoursPartielOuest, parcoursPartielEst) correspondants aux 4 points cardinaux (N,S,O,E)
    

# Exécution du programme:

C'est un projet scala sous SBT
En ligne de commande (sbt shell), pour exécuter le programme, faire : 
#                                                                       run "cheminFichier.txt"
  
    Si vous ne précisez pas de chemin pour le fichier, le programme va s'arrêter en vous indiquant que vous n'avez pas spécifier le chemin du fichier    
    Mettre le chemin du fichier entre "" et ce fichier doit absolument être un fichier .txt
  
