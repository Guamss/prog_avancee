import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

file_path = 'output_assigment102_error.csv'
df = pd.read_csv(file_path, sep=";")

median_errors = df.groupby('Ntotal')['Erreur'].median().reset_index()

median_errors['log_erreur'] = np.log10(median_errors['Erreur'])

plt.grid(True, which="both", linestyle="--", linewidth=0.5)

plt.plot(median_errors["Ntotal"], median_errors['log_erreur'], 'b--', label='Médiane de l\'erreur')

plt.scatter(df["Ntotal"], np.log10(df["Erreur"]), color="r", label=f"Erreur pour {df['Ntotal'].max()} points par worker")

plt.xlabel("Nombre de points")
plt.ylabel("Erreur (log10)")
plt.title("Tendance de l'erreur sur Assignment102") 
plt.xscale('log')

y_ticks = np.arange(int(np.floor(min(np.log10(df["Erreur"])))), int(np.ceil(max(np.log10(df["Erreur"])))) + 1, 1)
plt.yticks(y_ticks)

plt.legend()
#plt.savefig('tendance_erreur_assigment102.png')


plt.show()
