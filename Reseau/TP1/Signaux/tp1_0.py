#-*- coding : utf-8 *-*
'''
File : TP1_0.py
Creer un signal numérique et l'afficher
On utilise ici le B,A,BA de Python
    => Donc ça doit être compris !!!
'''
import math
import matplotlib.pyplot as plt
import numpy as np

class Signal:

    # a = Amplitude
    # f = Fréquence
    # fe = Fréquence d'échantillonage
    # nT = Nombre de périodes
    # N = Nombre de points
    # ph = Phase à l'origine
    def __init__(self, a, ph, f, fe, nT):
        self.a = a
        self.ph = ph
        self.f = f
        self.fe = fe
        self.nT = nT
        self.omega = 2*math.pi*self.f
        self.N = int(self.fe/self.f)
        self.te = 1.0/self.fe
        self.sig_t = []
        self.sig_s = []
        self.name = ""

    #Calcul des données de la courbe
    def calculate_wave(self):
        for i in range(self.N*self.nT):                                    
            self.t=self.te*i                                               
            self.sig_t.append(self.t)                                      
            self.sig_s.append(self.calc_sig(self.t))                                      
        
    def plot(self, format='-bo'):
        plt.plot(self.sig_t, self.sig_s, format)
        plt.xlabel('time (s)')
        plt.ylabel('voltage (V)')
        plt.title("Un signal " + self.name)
        #plt.xlim([-1.2, 1.2])
        plt.grid(True)
    
class Sinusoide(Signal):
    
    def __init__(self, a=1.0, ph=0, f=440.0, fe=8000.0, nT=1):
        Signal.__init__(self, a, ph, f, fe, nT)
        self.name = "sinusoïdal"
    
    def calc_sig(self, t):
        return self.a*math.sin((self.omega*self.t)+self.ph)
    
if __name__ == '__main__':
    sin = Sinusoide(1.2, f=50.0, fe=600, nT=2)
    
    sin.calculate_wave()
    sin.plot("-o")
    
    plt.show()