
package juegovol2;

import javax.swing.JFrame;

public class Ventana extends JFrame{
       
    public Ventana(){
        setTitle("Prueba");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new PanelJuego(1280,720));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
}
