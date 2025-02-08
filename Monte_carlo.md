# Compte rendu Monte Carlo

## La méthode de Monte Carlo, c'est quoi ?

La méthode de Monte Carlo c'est une méthode d'approximation de $\pi$ grâce à un grand nombre de simulation
aléatoires. Concrètement, Afin d'approximer $\pi$ on va effectuer des lancers aléatoire sur un quart de disque
(disque de rayon $r = 1$) et pour chacun des lancers on va évaluer sa distance depuis le centre. Pour ça
on utilise la formule suivante :

$d = \sqrt{x^2 + y^2} = x^2 + y^2$

étant donné qu'on a un rayon $r = 1$ il est inutile de faire les puissances et les racines carrées.
Sur $n$ on va approximer $\pi$ en faisant $\frac{nCible}{nTotal}$ avec $nCible$ le nombre de points
avec une distance $d \le 1$.
![monte_carlo.png](images/monte_carlo.png)

## Estimation de la probabilité qu'un lancé $X_p$ soit dans le quart de disque

A_1/4 et A_C à définir

$p = \frac{A_{\frac{1}4D}}{A_C} \Leftrightarrow p(X_p | d \le r)$

$p = \frac{\frac{\pi r^2}4}{r^2}$

$p = \frac{\pi}4$

on peut donc approximer que :

$p \approx \frac{nCible}{nTotal} = \frac{\pi}4 \approx \frac{nCible}{nTotal}$

$p = \pi \approx 4 \frac{nCible}{nTotal}$

## Implémentation de la méthode de Monte Carlo

Afin d'illustrer une implémentation possible de cette méthode, j'ai
réalisé un pseudo-code en python :

```java
int n_total = 100000;
int n_cible = 0;
for(
int i = 0;
i <=n_total;i++){
double x = Math.random();
double y = Math.random();
double d = Math.pow(x, 2) + Math.pow(y, 2);
    if(d <=1){
n_cible ++;
        }
        }
double pi = 4 * (n_cible / n_total);
```

ici dans ce code, la ressource critique est `n_cible` étant donné qu'elle est
partagée par chaque itération du programme, c'est donc un programme parallèlisable mais
mais il faudrait gérer l'accès à cette ressource (comme par exemple en utilisant un moniteur ou une sémaphore)
dans le cas ou nous faisons des itérations parallèles.

Une autre implémentation de ce programme est possible avec le paradigme Master/Worker.

### Le Master / Worker

Le paradigme Master / Worker c'est :

* Des workers qui ont chacun une tâche attitrée, dans notre cas c'est $n$ lancés aléatoires
* Un master qui attitre des tâches aux workers et qui traite le résultat de leur travail. Dans notre cas, le master
  va s'occuper du calcul `4* (n_cible /n_total)`

Voici un schéma explicatif :
![img.png](images/masterWorker.png)

On aura donc $n$ processus worker indépendant, ils vont simultanément calculer $\frac{nTotal}n$
tirage aléatoire chacun. Et une fois que chacun des workers a fini sa tâche individuelle, le master va estimer $pi$.
Afin d'évaluer l'efficacité de la parallélisation d'un programme, il existe une méthode, c'est le speedup.

## Le speedup, c'est quoi ?

Un speedup c'est ça :

$S_p=\frac{T_1}{T_p}$

Un Speedup $S_p$ pour $p$ processus. Dans la formule $T_1$ est le temps d'exécution du programme pour $1$
processus, et $T_p$ est le temps d'exécution pour $p$ processus.

### Ça sert à quoi ?

Comme dit un peu plus haut ça sert à évaluer l'efficacité de la parallélisation d'un programme. Un speedup de $2$
ça veut dire que le programme va deux fois plus vite que si on l'exécutait avec un seul processus.
mais concrètement
il y a deux manières de mesurer un speedup.

#### <u>Scalabilité forte</u>

La scalabilité forte consiste à augmenter le nombre de processus à chaque expérience
tout en gardant la même charge, dans ce cas on espère dans le meilleur des cas une évolution du speedup linéaire
![img.png](images/scal_forte.png)

#### <u>Scalabilité faible</u>

La scalabilité faible c'est le fait d'augmenter la charge proportionnellement au nombre de processus. Dans le meilleur
des cas on espère un speedup constant
![img_1.png](images/scal_faible.png)

## L'importance de la configuration et l'environnement pour l'évaluation d'un speedup

Il est très important avant d'effectuer des tests sur le programme ciblé car le processeur a un impact majeur sur la
performance du programme, sur un processeur à 8 coeur on peut remarquer une baisse de performance en utilisant
9 processus sur un programme. La fréquence du processeur a aussi un impact sur le temps d'exécution (car cela
influe sur le nombre de cycle). Enfin l'environnement d'exécution a aussi un impact sur les performances du programme
et peut influencer sur le résultat.

Voici ma configuration personnelle sur laquelle j'ai effectué tous les tests :

| Composant  | Détails                               |
|------------|---------------------------------------|
| RAM        | 16 GB                                 |
| Processeur | Intel Core i5-13420H (4.60GHz)        
| Coeurs     | 8 coeurs physique et 6 coeurs logique |
| Cache L1   | 704 KB                                |
| Cache L2   | 8 MB                                  |
| Cache L3   | 12 MB                                 |

Voici une machine de l'IUT (en G26) sur laquelle j'ai aussi effectué tous les tests :

| Composant  | Détails                       |
|------------|-------------------------------|
| RAM        | 32 GB                         |
| Processeur | Intel Core i7-9700 (3.00 GHz) |
| Coeurs     | 8 coeurs physique             |
| Cache L1   | 512 KB                        |
| Cache L2   | 2 MB                          |
| Cache L3   | 12 MB                         |

## Evaluation des speedup de différentes implémentation de la méthode de Monte Carlo

### Pi.java

![pi_conception.jpg](images/pi_conception.jpg)

#### <u>Evaluation de la scalabilité forte de Pi.java sur ma machine :</u>

| Erreur                | Ntotal    | Nprocessus | Temps (ms) |
|-----------------------|-----------|------------|------------|
| 2.652998634675312E-5  | 100000000 | 1          | 3445       |
| 7.803252593125184E-5  | 100000000 | 2          | 1673       |
| 1.257367308080578E-4  | 100000000 | 4          | 1074       |
| 2.8066525331449137E-5 | 100000000 | 8          | 630        |
| 8.770506560683454E-5  | 100000000 | 16         | 433        |

![Speedup_Scalabilite_forte_pi_monpc.png](images/Speedup_Scalabilite_forte_pi_monpc.png)

#### <u>Evaluation de la scalabilité forte de Pi.java sur la machine de l'IUT :</u>

| Erreur                | Ntotal    | Nprocessus | Temps (ms) |
|-----------------------|-----------|------------|------------|
| 7.844861411660524E-5  | 100000000 | 1          | 3156       |
| 3.164840931658016E-5  | 100000000 | 2          | 1610       |
| 7.804117746236309E-5  | 100000000 | 4          | 829        |
| 1.0963025056722495E-4 | 100000000 | 8          | 453        |
| 8.967932160437934E-6  | 100000000 | 16         | 500        |

![Speedup_Scalabilite_forte_pi_machine_G26.png](images/Speedup_Scalabilite_forte_pi_machine_G26.png)

#### <u>Evaluation de la scalabilité faible de Pi.java sur ma machine :</u>

| Erreur                | Ntotal    | Nprocessus | Temps (ms) |
|-----------------------|-----------|------------|------------|
| 1.9574352184204526E-4 | 10000000  | 1          | 332        |
| 3.993646404928803E-6  | 20000000  | 2          | 328        |
| 5.555650180506118E-6  | 30000000  | 3          | 333        |
| 1.5996214447351963E-5 | 40000000  | 4          | 334        |
| 4.0009512261139814E-5 | 50000000  | 5          | 349        |
| 6.967158625431769E-5  | 60000000  | 6          | 349        |
| 5.988886241878413E-5  | 70000000  | 7          | 355        |
| 2.1216496580845564E-5 | 80000000  | 8          | 359        |
| 2.3460064333349932E-5 | 90000000  | 9          | 415        |
| 9.379449679221532E-6  | 100000000 | 10         | 430        |
| 6.722674376552224E-5  | 110000000 | 11         | 469        |
| 3.382156807373275E-5  | 120000000 | 12         | 475        |
| 5.75970312383363E-5   | 130000000 | 13         | 574        |
| 7.022029186756727E-5  | 140000000 | 14         | 620        |
| 9.748852414555198E-6  | 150000000 | 15         | 626        |
| 3.89815343908859E-6   | 160000000 | 16         | 654        |

![Speedup_Scalabilite_faible_pi_monpc.png](images/Speedup_Scalabilite_faible_pi_monpc.png)

#### <u>Evaluation de la scalabilité faible de Pi.java sur la machine de l'IUT :</u>

| Erreur                | Ntotal    | Nprocessus | Temps (ms) |
|-----------------------|-----------|------------|------------|
| 9.999590807803488E-5  | 10000000  | 1          | 321        |
| 1.3969143621836067E-4 | 20000000  | 2          | 326        |
| 3.985656024836787E-5  | 30000000  | 3          | 329        |
| 6.158153630970143E-6  | 40000000  | 4          | 330        |
| 5.594181983020367E-5  | 50000000  | 5          | 345        |
| 5.950689055542328E-5  | 60000000  | 6          | 345        |
| 7.158221514336294E-5  | 70000000  | 7          | 360        |
| 8.25070651655211E-5   | 80000000  | 8          | 362        |
| 1.3150733536701468E-4 | 90000000  | 9          | 472        |
| 4.9906664388084404E-5 | 100000000 | 10         | 520        |
| 5.488789508659971E-5  | 110000000 | 11         | 568        |
| 6.369797072353024E-5  | 120000000 | 12         | 627        |
| 1.0580857310292882E-4 | 130000000 | 13         | 665        |
| 6.057778764957703E-5  | 140000000 | 14         | 722        |
| 1.7529080735863494E-6 | 150000000 | 15         | 800        |
| 5.88327673805791E-5   | 160000000 | 16         | 816        |

![Speedup_Scalabilite_faible_pi_machine_G26.png](images/Speedup_Scalabilite_faible_pi_machine_G26.png)

### Assignment102

#### <u>Evaluation de la scalabilité forte de Assignment102 sur ma machine :</u>

| Erreur                | Ntotal   | Nprocessus | Temps (ms) |
|-----------------------|----------|------------|------------|
| 3.421660694865587E-4  | 10000000 | 1          | 845        |
| 8.576974149886625E-5  | 10000000 | 2          | 812        |
| 1.09324673076508E-4   | 10000000 | 3          | 760        |
| 1.319883369727388E-4  | 10000000 | 4          | 747        |
| 1.0244917953488176E-4 | 10000000 | 5          | 696        |
| 2.7582694304421727E-5 | 10000000 | 6          | 745        |
| 1.5796242368537624E-4 | 10000000 | 7          | 723        |
| 7.838495213933131E-5  | 10000000 | 8          | 734        |
| 4.056973766081112E-5  | 10000000 | 9          | 781        |
| 1.4281087310294552E-4 | 10000000 | 10         | 821        |
| 1.3411872787692897E-4 | 10000000 | 11         | 804        |
| 4.63026617738537E-6   | 10000000 | 12         | 840        |
| 6.361537342040279E-5  | 10000000 | 13         | 771        |
| 3.55696524982708E-4   | 10000000 | 14         | 823        |
| 1.0903256268406217E-5 | 10000000 | 15         | 929        |
| 2.4212355759231684E-4 | 10000000 | 16         | 813        |

![Speedup_Scalabilite_forte_assigment102_monpc.png](images/Speedup_Scalabilite_forte_assigment102_monpc.png)

#### <u>Evaluation de la scalabilité forte de Assignment102 sur la machine de l'IUT :</u>

| Erreur                | Ntotal   | Nprocessus | Temps (ms) |
|-----------------------|----------|------------|------------|
| 1.3835453469645634E-4 | 10000000 | 1          | 1034       |
| 3.407621598254574E-5  | 10000000 | 2          | 2096       |
| 3.0884130973652025E-4 | 10000000 | 3          | 2935       |
| 8.360523427282492E-5  | 10000000 | 4          | 2935       |
| 4.6748695701041864E-4 | 10000000 | 5          | 3322       |
| 1.3666520696633115E-4 | 10000000 | 6          | 3494       |
| 1.6926013931164047E-4 | 10000000 | 7          | 3851       |
| 9.048072781445114E-5  | 10000000 | 8          | 3839       |
| 8.815478031201076E-5  | 10000000 | 9          | 4009       |
| 1.5872636741223932E-4 | 10000000 | 10         | 3824       |
| 2.160221469252729E-4  | 10000000 | 11         | 4130       |
| 3.127508898418919E-5  | 10000000 | 12         | 4131       |
| 1.2266357284234394E-6 | 10000000 | 13         | 4347       |
| 8.21705544516541E-5   | 10000000 | 14         | 4457       |
| 1.695147872205948E-4  | 10000000 | 15         | 4425       |
| 9.633762972011859E-5  | 10000000 | 16         | 4023       |

![Speedup_Scalabilite_forte_assigment102_machine_G26.png](images/Speedup_Scalabilite_forte_assigment102_machine_G26.png)

#### <u>Evaluation de la scalabilité faible de Assignment102 sur ma machine :</u>

| Erreur                | Ntotal    | Nprocessus | Temps (ms) |
|-----------------------|-----------|------------|------------|
| 1.3160971127002872E-5 | 10000000  | 1          | 843        |
| 1.8724693321427893E-4 | 20000000  | 2          | 1333       |
| 1.0881537725859929E-4 | 30000000  | 3          | 2134       |
| 9.907280940805767E-5  | 40000000  | 4          | 3016       |
| 1.4621115739054989E-5 | 50000000  | 5          | 3766       |
| 4.037875172898934E-5  | 60000000  | 6          | 4747       |
| 6.137702543789971E-6  | 70000000  | 7          | 5238       |
| 2.7691817431330862E-5 | 80000000  | 8          | 6226       |
| 1.2134054495042494E-5 | 90000000  | 9          | 6875       |
| 3.7072409777124166E-5 | 100000000 | 10         | 7983       |
| 5.677521298670445E-5  | 110000000 | 11         | 8789       |
| 2.5400312580458155E-6 | 120000000 | 12         | 9767       |
| 3.5672662488709846E-5 | 130000000 | 13         | 10833      |
| 1.7086478417457924E-6 | 140000000 | 14         | 11827      |
| 3.9406845543191645E-5 | 150000000 | 15         | 13135      |
| 2.311839815068496E-5  | 160000000 | 16         | 16641      |

![Speedup_Scalabilite_faible_assigment102_monpc.png](images/Speedup_Scalabilite_faible_assigment102_monpc.png)

#### <u>Evaluation de la scalabilité faible de Assignment102 sur la machine de l'IUT :</u>

| Erreur                | Ntotal    | Nprocessus | Temps (ms) |
|-----------------------|-----------|------------|------------|
| 1.0993117740169408E-6 | 10000000  | 1          | 964        |
| 5.012238040746224E-6  | 20000000  | 2          | 4348       |
| 2.138626185338756E-5  | 30000000  | 3          | 7918       |
| 1.1189735166068215E-5 | 40000000  | 4          | 12546      |
| 2.2485735251743418E-7 | 50000000  | 5          | 17499      |
| 9.295465013335847E-7  | 60000000  | 6          | 21170      |
| 1.2143863788783024E-4 | 70000000  | 7          | 24589      |
| 4.265466741522471E-5  | 80000000  | 8          | 29124      |
| 1.2849734793962089E-5 | 90000000  | 9          | 37074      |
| 4.4656836599309415E-5 | 100000000 | 10         | 41255      |
| 8.166125863374539E-5  | 110000000 | 11         | 41147      |
| 1.485806070391984E-4  | 120000000 | 12         | 46665      |
| 2.5614070085366214E-5 | 130000000 | 13         | 44727      |
| 1.0801731720988503E-4 | 140000000 | 14         | 55690      |
| 5.3802940662386415E-5 | 150000000 | 15         | 59059      |
| 3.4848117456656396E-5 | 160000000 | 16         | 64518      |

![Speedup_Scalabilite_faible_assigment102_machine_G26.png](images/Speedup_Scalabilite_faible_assigment102_machine_G26.png)

### JAVA SOCKET vs Pi.java, c quoi la diff sur les perfs des processus lourds et léger 