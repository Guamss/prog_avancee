import pandas as pd
import matplotlib.pyplot as plt

NBR_COEURS = 16

def expected(x):
    return 1

file_path = 'output_assigment102_faible.csv'
df = pd.read_csv(file_path, sep=";")
t1 = df["Temps(ms)"][0]

x = df["Nprocessus"]
y = t1 / df["Temps(ms)"]

x_expected = list(range(NBR_COEURS + 2))
y_expected = [expected(j) for j in x_expected]

plt.figure(figsize=(10, 6))  
plt.scatter(x, y, color='red', label=f"Réalité (Expérience sur {format(df['Ntotal'][0], '.1E')} points)")
plt.plot(x_expected, y_expected, '--', label="Attendu")

plt.xlim(1, NBR_COEURS)
plt.ylim(0, 1.1)  # Fixe l'axe Y entre 0 et 1.1

plt.xticks(range(1, NBR_COEURS + 2, 1))  
plt.yticks([i * 0.1 for i in range(12)])  # Yticks en pas de 0.1 pour bien voir les détails

plt.grid(True, which='both', linestyle='--', linewidth=0.5, alpha=0.7)  # Grid visible proprement

plt.axhline(0, color='black', linewidth=0.5, ls='--')  
plt.axvline(0, color='black', linewidth=0.5, ls='--')  

plt.xlabel('Nombre de processus')
plt.ylabel('Speedup')
plt.legend()
plt.title('Speedup scalabilité faible d\'Assignement102')
plt.savefig('Speedup_Scalabilite_faible_assignment102_monpc.png')

plt.show()