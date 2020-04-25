
package Estado;

import Util.EstadoJugar;
import Util.KeyHandler;
import Util.MouseHandler;
import Util.Vector2f;

import java.awt.Graphics2D;
import java.util.ArrayList;
import juegovol2.PanelJuego;


public class ManagerEstados {
    
    private ArrayList<EstadosdelJuego> estados;
    
    public static Vector2f map;
    
    public static final int JUGAR=0;
    public static final int MENU=1;
    public static final int PAUSA=2;
    public static final int FINDELJUEGO=3;
    
    public ManagerEstados(){
        map = new Vector2f(PanelJuego.width,PanelJuego.height);
        Vector2f.setWorldVar(map.x, map.y);
        
        estados = new ArrayList<EstadosdelJuego>();
        estados.add(new EstadoJugar(this));
        
    }
    
    public void pop(int estado){
        estados.remove(estado);
    }
    public void add(int estado){
        if (estado==JUGAR) {
            estados.add(new EstadoJugar(this));
        }
        if (estado == MENU) {
            estados.add(new EstadoMenu(this));
        }
        if (estado == PAUSA) {
            estados.add(new EstadoPausa(this));
        }
        if (estado == FINDELJUEGO) {
            estados.add(new EstadoFin(this));
        }
    }
    public void addAndpop(int estado){
        estados.remove(0);
        add(estado);
    }
    
    public void update(){
        Vector2f.setWorldVar(map.x, map.y);
        for (int i = 0; i < estados.size(); i++) {
            estados.get(i).update();
        }
    }
    public void input(MouseHandler mouse, KeyHandler key){
        for (int i = 0; i < estados.size(); i++) {
            estados.get(i).input(mouse,key);
        }
    }
    public void render(Graphics2D g){
            for (int i = 0; i < estados.size(); i++) {
            estados.get(i).render(g);
        }
    }
}
