from Signaux.tp1_0 import Signal
import math
import matplotlib.pyplot as plt
import numpy as np

class Cos(Signal):
    def __init__(self, a, ph, f, fe, nT):
        Signal.__init__(self, a, ph, f, fe, nT)

        self.freqReduites = []
        
        self.freqReduites.append(fe * 0.01)
        self.freqReduites.append(fe * 0.02)
        self.freqReduites.append(fe * 0.4)
        
    def calc_sig(self, t):
        value = 0
        self.N = 300
        x = np.arange(0, self.N * 1.0/self.fe, 1.0/self.fe)
        
        for freq in self.freqReduites:
            value += np.cos((2 * math.pi * freq * x) + self.ph)
        
        return value
        
if __name__ == "__main__":
    Cos(1, 0, 440.0, 1000.0, 1).plot(format="r")
    plt.show()