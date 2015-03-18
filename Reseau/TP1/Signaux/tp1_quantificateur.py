import math
import numpy as np
import matplotlib . pyplot as plt
from matplotlib . ticker import MultipleLocator
from Signaux.tp1_0 import *

def QS ( sig_s , vmax , b ) :
    '''
    Quantificateur sur b bits d'un signal sig_s
    d'amplitude max vmax
    Cette fonction rend le signal quantifie
    et le bruit sur chaque echantillon .
    '''
    # pour l'instant le signal quantie est le meme que le
    # signal original.
    # le signal de bruit est donc 0 puisqu'il n'y a pas de
    # quantication.
    return sig_s , [0] * len(sig_s)

def plot ( inx , iny , leg , fmt='-bo ' , l="" ) :
    plt . plot ( inx , iny , fmt , label=l )
    plt . xlabel ( 'time (s)' )
    plt . ylabel ( 'voltage (V)' )
    plt . title ( leg )
    plt . ylim ( [ -5.5 , +5.5] )
    plt . grid ( True )
    
if __name__ == '__main__ ' :
    np . set_printoptions ( linewidth=250)
    np . set_printoptions ( precision=3, suppress=True )
    a=5.0
    b=3
    step = 2*a /(2 ** b )
    fe = 2000.0
    f = 50.0
    nT=2
    s = Signal( a , 0 , f=f , fe=fe , nT=nT )
    x , y= s. 
    #print y
    #y = np.array(y)+a
    z , err = QS ( y , a , b )
    # plot du signal quantie
    fig = plt . figure ( figsize=(12 ,12) )
    ax = fig . add_subplot ( 1 , 1 , 1 )
    majorLocator = MultipleLocator ( step )
    ax . yaxis . set_major_locator ( majorLocator )
    plot ( x , y , "" , 'bo ' , l=" Signal " )
    plot ( x , z , "" , 'rs ' , l=" Quantized " )
    plot ( x , err , "" , '--x' , l=" Diff " )
    plt . title ( " Sinusoide : fe =" + str ( fe ) + ", f = " + str ( f ) + ", d = " + str ( nT ) )
    plt . legend ( )
    plt . show ( )