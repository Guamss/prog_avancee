# Compte rendu Monte Carlo

## La méthode de Monte Carlo, c'est quoi ?

La méthode de Monte Carlo c'est une méthode d'approximation de $\pi$ grâce à un grand nombre de simulation 
aléatoires. Concrètement, Afin d'approximer $\pi$ on va effectuer des lancers aléatoire sur un quart de disque 
(disque de rayon $r = 1$) et pour chacun des lancers on va évaluer sa distance depuis le centre. Pour ça 
on utilise la formule suivante : 
$$
d = \sqrt{x^2 + y^2} = x + y
$$
étant donné qu'on a un rayon $r = 1$ il est inutile de faire les puissances et les racines carrées. 
Sur $n$ on va approximer $\pi$ en faisant $\frac{nCible}{nTotal}$ avec $nCible$ le nombre de points 
avec une distance $d \le 1$.
![monte_carlo.png](monte_carlo.png)

## Estimation de la probabilité qu'un lancé $X_p$ soit dans le quart de disque

A_1/4 et A_C à définir
$$
p = \frac{A_{\frac{1}4D}}{A_C} \Leftrightarrow p(X_p | d \le r)
$$
$$
p = \frac{\frac{\pi r^2}4}{r^2}
$$
$$
p = \frac{\pi}4
$$
on peut donc approximer que : 
$$
p \approx \frac{nCible}{nTotal} = \frac{\pi}4 \approx \frac{nCible}{nTotal}
$$
$$
p = \pi \approx 4 \frac{nCible}{nTotal}
$$
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