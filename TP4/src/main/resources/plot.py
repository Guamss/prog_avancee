import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

NBR_COEURS = 16

def expected(x):
    return 1  # La fonction attendue est linéaire

file_path = 'output_pi_faible_monpc.csv'
df = pd.read_csv(file_path, sep=";")
t1 = df["Temps(ms)"][0]

x = [nProc for nProc in df["Nprocessus"]]
y = [t1/tp for tp in df["Temps(ms)"]]

x_expected = list(range(NBR_COEURS + 2))
y_expected = [expected(j) for j in x_expected]

plt.figure(figsize=(10, 6))  # Optionnel : pour agrandir la figure
plt.scatter(x, y, color='red', label=f"Réalité (Expérience sur {format(df['Ntotal'][0], '.1E')} points)")
plt.plot(x_expected, y_expected, '--', label="Attendu")

plt.xlim(1, NBR_COEURS)
plt.ylim(0, 1.1)

# Échelles personnalisées
plt.xticks(range(1, NBR_COEURS + 2, 1))  # Échelle de 1 en 1 pour les processus
plt.yticks(np.arange(0, 1.1, 0.1))  # De 0 à 1.1 par pas de 0.1

# Ajout d'une grille avec pas de 0.1
plt.grid(True, which='both', linestyle='--', linewidth=0.5)

plt.axhline(0, color='black', linewidth=0.5, ls='--')  # Ligne horizontale à y=0
plt.axvline(0, color='black', linewidth=0.5, ls='--')  # Ligne verticale à x=0

plt.xlabel('Nombre de processus')
plt.ylabel('Speedup')
plt.legend()
plt.title('Graphique de Speedup en fonction du Nombre de Processus')
#plt.savefig('Speedup_Scalabilite_faible_feffrgtbrpi_monpc.png')
plt.show()
