import pandas as pd
import matplotlib.pyplot as plt

NBR_COEURS = 16

def expected(x):
    return x  # La fonction attendue est linéaire

file_path = 'output_assignment102_forte_monpc.csv'
df = pd.read_csv(file_path, sep=";")
t1 = df["Temps(ms)"][0]

x = df["Nprocessus"]
y = t1 / df["Temps(ms)"]

x_expected = list(range(NBR_COEURS + 2))
y_expected = [expected(j) for j in x_expected]

plt.figure(figsize=(10, 6))  
plt.scatter(x, y, color='red', label=f"Réalité (Expérience sur {format(df['Ntotal'][0], '.1E')} points)")
plt.plot(x_expected, y_expected, '--', label="Attendu")

# Définition des axes
plt.xlim(1, NBR_COEURS)
plt.ylim(0, 11)

# Graduation tous les 1 pour correspondre à la grille de taille 1
plt.xticks(range(1, NBR_COEURS + 2, 1))  
plt.yticks(range(0, 12, 1))  

# Ajout d'une grille avec des pas de 1
plt.grid(True, which='major', linestyle='--', linewidth=0.5)

# Axes x et y
plt.axhline(0, color='black', linewidth=0.5, ls='--')  
plt.axvline(0, color='black', linewidth=0.5, ls='--')  

# Labels et titre
plt.xlabel('Nombre de processus')
plt.ylabel('Speedup')
plt.legend()
plt.title('Graphique de Speedup en fonction du Nombre de Processus')
plt.savefig('Speedup_Scalabilite_forte_assigment102_monpc.png')
plt.show()