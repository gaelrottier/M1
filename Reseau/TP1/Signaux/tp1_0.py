#-*- coding : utf-8 *-*
import math
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
        self.omega = 2*math.pi*self.f
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
        
        return self

    #Calcul des données de la courbe
    def calculate_wave(self):
        #Pour tous les points
        for i in range(self.N*self.nT):
            #Abscisse correspondante                        
            self.t=self.te*i                                               
            self.sig_t.append(self.t)
            #Valeur de l'ordonnée                                      
            self.sig_s.append(self.calc_sig(self.t))                                      
    
    #Dessin de la courbe
    def plot(self, format='-bo'):
        self.calculate_wave()
        plt.plot(self.sig_t, self.sig_s, format)
        plt.xlabel('time (s)')
        plt.ylabel('voltage (V)')
        plt.title("Un signal " + self.name)
        plt.grid(True)
    
    #Calcul des coordonnées en abscisse des données de la courbe
    def calc_sig(self, t):
        raise NotImplementedError
        
#Dessine une sinusoïde avec les paramètres donnés
class Sinusoide(Signal):
    
    def __init__(self, a=1.0, ph=0, f=440.0, fe=8000.0, nT=1):
        Signal.__init__(self, a, ph, f, fe, nT)
        self.name = "sinusoïdal"
    
    def calc_sig(self, t):
        return self.a * math.sin((self.omega * self.t) + self.ph)
    
#Dessine un signal carré avec les pramètres donnés
class Carre(Signal):    
    def __init__(self, a=1.0, ph=0, f=440.0, fe=8000.0, nT=1):
        Signal.__init__(self, a, ph, f, fe, nT)
        plt.xlim([0, 0.05])
        self.name = "carré"
    
    def calc_sig(self, t):
        x = self.a * math.sin(self.omega * t + self.ph)
        
        return self.sgn(x)
        
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
        
    def calc_sig(self, t):
        return 2 * self.a * (t/self.T - math.floor(t/self.T) - 1/2)
    
#Dessine un signal en triangle avec les paramètres donnés
class Triangle(Signal):
    def __init__(self, a=1.0, ph=0, f=440.0, fe=8000.0, nT=1):
        Signal.__init__(self, a, ph, f, fe, nT)
        self.name = "triangle"
        
    def calc_sig(self, t):
        return self.a * (4 * math.fabs(t / self.T - math.floor(t / self.T + 1 / 2)) - 1.0)

class ScipySignal:
    def __init__(self):
        #Définition du nombre de périodes
        nT = 2  
        
        #Fréquence
        Hz = 50
        
        #Définition de l'abscisse
        x = np.linspace(start=0, stop=nT, num=50)
        
        plt.grid(True)
        plt.ylim(-2, 2)
        
        # Génération d'un carré
        plt.plot(x, sg.square(2 * np.pi * Hz * x), c='RED')
        
        #Génération d'un signal en dent de scie
        plt.plot(x, sg.sawtooth(2 * np.pi * Hz * x), c='ORANGE')
        
        #Génération d'un signal en triangle
        plt.plot(x, sg.sawtooth(2 * np.pi * Hz * x, width = 0.5), c='CYAN')
        
        #Dessin
        plt.show()
        
#Point d'entrée du programme
if __name__ == '__main__':
#     #On dessine les sinusoïdes
#     Sinusoide(1.25, f=50.0, fe=500.0, nT=2).plot("o")
#     
#     Sinusoide(0.5, ph=math.pi, f=50.0, fe=1000.0, nT=2).plot(".r")
#     
#     plt.show()
#     
#     #On dessine le carré
#     Carre(a=3, f=50.0, fe=1000.0, nT=3).plot()
#     
#     plt.show()
#     
#     #On dessine la dent de scie
#     DentDeScie(a=2, f=50.0, fe=1000.0, nT=2).plot()
#     
#     plt.show()
#     
#     #On dessine le triangle
#     Triangle(a=3, f=50.0, fe=1000.0, nT=2).plot()
#     
#     plt.show()
    
    #Using scipy
    ScipySignal()