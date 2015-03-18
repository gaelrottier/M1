import wave
import math
import matplotlib.pyplot as plt
import numpy as np
from Signaux.tp1_1_vector import Signal
from Signaux.tp1_1_vector import Sinusoide as Sin

class Son(Signal):
    def __init__ (self, a, f, ph = 0):
        print("Création d'un fichier audio au format WAV (PCM 8 bits stereo 44100 Hz)")
        #Amplitude
        self.a = a
        #Frequence
        self.f = f
#         self.duree = float(input('Durée (en secondes) ? '))
        self.duree = 1
        #Phase à l'origine
        self.ph = ph
        #Nombre de canaux
        self.nbCanal = 2
        #Taille d'un echantillon
        self.nbOctet = 1
        #Tableau de valeur des abscisses
        self.abs = []
        #Tableaux de stockage des valeurs quantifiées
        self.quantifiedY = []
        #Tableaux de stockage des valeurs non quantifiées
        self.unQuantifiedY = []
        #Tableau de stockage des valeurs sonores
        self.soundData = []
        #Nombre de valeurs calculees, sert pour l'ecriture dans le fichier
        self.nbVals = 0
       
    def write_as_wav(self, nom):
        print("Nombre d'échantillons : %s" % self.nbEchantillon)
        
        self.parametres = (self.nbCanal, self.nbOctet, self.fe, self.nbEchantillon, 'NONE', 'not compressed')
         
        #Instanciation de l'objet son
        fson = wave.open(nom, 'w')
        
        fson.setparams(self.parametres)
        
        print('Génération d\'un son sinusoïdal sur chaque canal...')
        for value in self.soundData:
            fson.writeframes(value* 2)
        
        fson.close()
        print("Fichier généré !")
        
    def make(self, fe, nT):
        #Frequence d'echantillonnage
        self.fe = fe
        #Période d'échantillonnage
        self.te = 1.0 / self.fe
        #Nombre d'echantillons par fréquence
        self.nbEchantillon = int(self.duree * self.fe)
        
        #Pour chaque fréquence choisie
        for freq in self.f:
            
            #Nombre de points par période
            N = int(self.fe / freq)
            nT = freq / self.duree / 2
            
            Signal.__init__(self, self.a, self.ph, freq, self.fe, nT)
            
            #Omega        
            self.omega = 2.0 * math.pi * freq
                
            #plage de valeurs de x pour lesquelles on calcule y
            self.range = np.arange(N * nT)
            
            #Vectorisation de la fontion calc
            calcVectorized = np.vectorize(self.calc)
            
            x, y = calcVectorized(self.range)
            y += 128.0
            
            self.abs.extend(x)
            self.unQuantifiedY.extend(y)
            
        #Calcul des valeurs quantifiées
        for value in self.unQuantifiedY:
            self.quantifiedY.append(int(value))
            self.soundData.append(wave.struct.pack('B', int(value)))
        
    def plot(self):
        print("Affichage des données sur le graphique...")
        self.sig_s = self.abs
        self.sig_t = self.quantifiedY
        Signal.plot(self, "-bo")
        
        self.sig_t = self.unQuantifiedY
        Signal.plot(self, "-ro")
        
        plt.show()
        
class Sinusoide(Son, Sin):
    def calc(self, i):
        #i = echantillon courant
        return Sin.calc_sig(self, i)
    
if __name__ == '__main__':
    niv = 0.8
    #Fréquences exploitées pour le son
#     freq = [264, 297, 330, 352, 396, 440, 495, 528]
    #C'est la musique de Funky Town !!
#     freq = [528, 528, 466, 528, 1, 396, 1, 396, 528, 698.5, 660, 528]
    freq = [440]
    s = Sinusoide(127.5 * niv, freq)
    s.make(44100.0, 500)
#     s.write_as_wav("sinusoide.wav")
    s.plot()