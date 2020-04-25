
package Util;

import Estado.EstadosdelJuego;
import Estado.ManagerEstados;
import java.awt.Color;
import java.awt.Graphics2D;

public class EstadoJugar extends EstadosdelJuego{
    
    public EstadoJugar(ManagerEstados me){
        super(me);
    }
    public void update(){
        
    }
    public void input(MouseHandler mouse,KeyHandler key){
        if (key.up.down) {
            System.out.println("W is pressed");
        }
    }
    public void render(Graphics2D g){
        g.setColor(Color.red);
        g.fillRect(0, 0, 64, 64);
    }
}
