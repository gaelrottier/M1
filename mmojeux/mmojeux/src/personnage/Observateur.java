/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personnage;

/**
 *
 * @author Justin
 */

/** Interface observable  
*/
public interface Observateur {
    public void update(String classe,int vie,int force,int defense);
}
