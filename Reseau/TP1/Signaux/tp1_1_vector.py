#-*- coding : utf-8 *-*
import matplotlib.pyplot as plt
import numpy as np
from scipy import signal as sg

#Dessine un signal : classe mère de tous les autres signaux, à ne pas instancier
#(lèvera une exception)
class Signal(object):

    def __init__(self, a, ph, f, fe, nT):
        #Amplitude du signal
        self.a = a
        #Phase à l'origine du signal
        self.ph = ph
        #Fréquence du signal
        self.f = f
        #Fréquence d'échantillonnage du signal
        self.fe = fe
        #Nombre de périodes du signal
        self.nT = nT
        #Angle correspondant à la fréquence du signal
        self.omega = 2*np.pi*self.f
        #Nombre de points (fréquence d'échantillonnage / fréquence)
        self.N = int(self.fe/self.f)
        #Période d'échantillonnage
        self.te = 1.0/self.fe
        #Période du signal
        self.T = 1/self.f
        #Abscisse
        self.sig_t = []
        #Ordonnée
        self.sig_s = []
        self.name = ""
        #Plage de valeurs de x
        self.range = np.arange(self.N * self.nT)
#         self.calculate_wave(self.range)

    #Calcul des données de la courbe
    def calculate_wave(self, range):
        
        #Vectorisation de la methode calc_sig pour eviter d'utiliser une boucle
        vectorize = np.vectorize(self.calc_sig)
        
        #Appel a la fonction vectorisee et attribution des valeurs aux tableaux d'abscisse et d'ordonnee
        self.sig_s, self.sig_t = vectorize(range)
    
    #Dessin de la courbe
    def plot(self, format='-bo'):
        plt.plot(self.sig_s, self.sig_t, format)
        plt.xlabel('time (s)')
        plt.ylabel('voltage (V)')
        plt.title("Un signal " + self.name)
        plt.grid(True)
        
#Dessine une sinusoïde avec les paramètres donnés
class Sinusoide(Signal):
    
    def __init__(self, a=1.0, ph=0, f=440.0, fe=8000.0, nT=1):
        Signal.__init__(self, a, ph, f, fe, nT)
        self.name = "sinusoïdal"
    
    def calc_sig(self, point):
        t = self.te * point
        return t, self.a * np.sin((self.omega * t) + self.ph)
    
#Dessine un signal carré avec les pramètres donnés
class Carre(Signal):
    def __init__(self, a=1.0, ph=0, f=440.0, fe=8000.0, nT=1):
        Signal.__init__(self, a, ph, f, fe, nT)
        plt.xlim([0, 0.05])
        self.name = "carré"
    
    def calc_sig(self, point):
        #Abscisse
        t = self.te * point
        
        #Ordonnee
        x = self.a * np.sin(self.omega * t + self.ph)
        
        return t, self.sgn(x)
        
    def sgn(self, x):
        if(x > 0):
            return 1
        elif(x==0):
            return 0
        else:
            return -1
        
#Dessine un signal en dent de scie avec les paramètres donnés
class DentDeScie(Signal):
    def __init__(self, a=1.0, ph=0, f=440.0, fe=8000.0, nT=1):
        Signal.__init__(self, a, ph, f, fe, nT)
        self.name = "en dent de scie"
        
    def calc_sig(self, point):
        t = self.te * point
        return t, 2 * self.a * (t/self.T - np.floor(t/self.T) - 1/2)
    
#Dessine un signal en triangle avec les paramètres donnés
class Triangle(Signal):
    def __init__(self, a=1.0, ph=0, f=440.0, fe=8000.0, nT=1):
        Signal.__init__(self, a, ph, f, fe, nT)
        self.name = "triangle"
        
    def calc_sig(self, point):
        t = self.te * point
        return t, self.a * (4 * (np.fabs(t / self.T - np.floor(t / self.T + 1 / 2))) - 1.0)
        
#Point d'entrée du programme
if __name__ == '__main__':
#     #On dessine les sinusoïdes
    Sinusoide(1.25, f=50.0, fe=500.0, nT=2).plot("o")
#     
    Sinusoide(0.5, ph=np.pi, f=50.0, fe=1000.0, nT=2).plot(".r")
#     
    plt.show()
#     
#     #On dessine le carré
    Carre(a=3, f=50.0, fe=1000.0, nT=3).plot()
     
    plt.show()
#     
#     #On dessine la dent de scie
    DentDeScie(a=2, f=50.0, fe=1000.0, nT=2).plot()
     
    plt.show()
#     
#     #On dessine le triangle
    Triangle(a=3, f=50.0, fe=1000.0, nT=2).plot()
#     
    plt.show()
    