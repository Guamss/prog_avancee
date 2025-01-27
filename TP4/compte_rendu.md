callable c'est un runnable mais qui renvoit le type spécifié

exec.invokeAll(tasks) => execute la liste de taches (comme Thread.start())

total += f.get() => ajoute au total le total du worker f
dans 
```java
for (Future<Long> f : results) {
    total += f.get();
}
```     
total += f.get()<br>
est la ressource critique

donc on va attendre que tout les workers aient terminés avant de sortir de la boucle

un future prend en param un callable (donne le résultat des callable pas dans l'immédiat mais dans un futur)


atomicInteger est un moniteur et la section critique c'est l'incrémentation. ici l'objet il protège un entier

en G-26 sur les pc du fond à droite on a ça comme config : 
* 32 GB de RAM
* I7-9700 cadencé à 3.00 GHz :
  * 8 coeurs logique (0 hyperthreadé)
* SSD