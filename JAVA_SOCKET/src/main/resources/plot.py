import pandas as pd
import matplotlib.pyplot as plt

NBR_COEURS = 64

def expected(x):
    return 1

file_path = 'javasocket_faible_distribuee.csv'
df = pd.read_csv(file_path, sep=";")
t1 = df["Temps(ms)"][0]

x = df["Nprocessus"]
y = t1 / df["Temps(ms)"]

x_expected = list(range(1, NBR_COEURS + 2, 2))  # Espacement x2 pour plus d'écart
y_expected = [expected(j) for j in x_expected]

plt.figure(figsize=(12, 7))  # Plus large
plt.scatter(x, y, color='red', label=f"Réalité (Expérience sur {format(df['Ntotal'][0], '.1E')} points)", alpha=0.7, s=80)
plt.plot(x_expected, y_expected, '--', label="Attendu", linewidth=2)

plt.xlim(1, NBR_COEURS)
plt.ylim(0, 1.1)

plt.xticks(range(1, NBR_COEURS + 2, 4))  # Écart plus grand sur X
plt.yticks([i * 0.1 for i in range(12)])

plt.grid(True, which='both', linestyle='--', linewidth=0.6, alpha=0.8)

plt.axhline(0, color='black', linewidth=0.7, ls='--')  
plt.axvline(0, color='black', linewidth=0.7, ls='--')  

plt.xlabel('Nombre de processus')
plt.ylabel('Speedup')
plt.legend()
plt.savefig('Speedup_faible_javasocket.png')
plt.title('Speedup scalabilité faible de Java socket en mémoire distribué')

plt.show()
