import pandas as pd
import matplotlib.pyplot as plt

def expected(x):
    return x

file_path = 'output_assignment102.csv'
df = pd.read_csv(file_path, sep=";")

x = [nProc for nProc in df["Nprocessus"]]
y = [(tp/nb_proc)/tp for nb_proc, tp in zip(df["Nprocessus"], df["Temps(ms)"])]

x_expected = [i for i in range(len(x)+1)]
y_expected = [expected(j) for j in x_expected]

plt.plot(x, y, label = f"Réalité (Expérience sur {format(df["Ntotal"][0], ".1E")} points)")
plt.plot(x_expected, y_expected, '--', label = "Attendu")

plt.xlim(1, 12)

plt.xticks(range(1, 13, 1))
plt.yticks(range(1, 13, 1))

plt.xlabel('Nombre de processus')
plt.ylabel('Speedup')
plt.legend()
plt.savefig('Speedup_MonteCarlo.png')
plt.show()
