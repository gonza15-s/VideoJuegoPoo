
package Estado;

import Util.KeyHandler;
import Util.MouseHandler;
import java.awt.Graphics2D;


public abstract class EstadosdelJuego {
    
    private ManagerEstados me;
    
    public EstadosdelJuego(ManagerEstados me){
        this.me = me;
    }
    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
    
}
