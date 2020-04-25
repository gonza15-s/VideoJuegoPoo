
package juegovol2;

import Estado.ManagerEstados;
import Util.KeyHandler;
import Util.MouseHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PanelJuego extends JPanel implements Runnable{
    
    
    public static int width;
    public static int height;
    
    private boolean running=false;
    
    private Thread thread;
    private BufferedImage img;
    private Graphics2D g;
    
    private  MouseHandler mouse;
    private KeyHandler key;
    private ManagerEstados me;
    
    public PanelJuego(int width,int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }
    
    public void addNotify(){
        super.addNotify();
        if(thread==null){
            thread = new Thread(this,"hiloJuego");
            thread.start();
        }
    }
    public void init(){
        running = true;
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D)img.getGraphics();
        
        mouse = new MouseHandler(this);
        key = new KeyHandler(this);
        
        me = new ManagerEstados();
    }
    public void run(){
        init();
        this.requestFocusInWindow(running);
        final double juego_Hertz = 60.0;
        final double tiempoRender = 1000000000/juego_Hertz; // Tiempo antes de actualizar
        final int RDR = 5; // Mayor cantidad de actualizaciones antes del render
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;
        final double FPSO = 60.0;
        final double TTR = 1000000000/FPSO;
        int frameCount=0;
        int lastSecondTime = (int)(lastUpdateTime/1000000000);//Tiempo total antes del renderf
        int antcontadorFrame = 0;
        
        while (running) {
            double actual = System.nanoTime();
            int actualizado = 0;
            while (((actual - lastUpdateTime)>tiempoRender)&& (actualizado < RDR)) {
                update();
                input(mouse,key);
                lastUpdateTime += tiempoRender;
                actualizado++;
            }
            
            if (actual -lastUpdateTime>tiempoRender) {
                lastUpdateTime = actual - tiempoRender;
            }
            input(mouse,key);
            render();
            draw();
            lastRenderTime = actual;
            frameCount++;
            
            int segundos = (int) (lastUpdateTime/1000000000);
            if (segundos>lastSecondTime) {
                if (frameCount != antcontadorFrame) {
                    System.out.println("Nuevo segundo"+segundos+" "+frameCount);
                    antcontadorFrame = frameCount;
                }
                frameCount=0;
                lastSecondTime = segundos;
            }
            while (actual - lastRenderTime < TTR && actual-lastUpdateTime <tiempoRender) {
                Thread.yield();
                try{
                    Thread.sleep(1);
                    
                }catch(Exception e){
                    System.out.println("Error: yielding thread");
                }
                actual = System.nanoTime();
            }
        }
    }
    
    public void update(){
        me.update();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        me.input(mouse, key);
    }
    
    public void render(){
        if (g!=null) {
            g.setColor(new Color(66,134,244));
            g.fillRect(0, 0, width, height);
            me.render(g);
        }
    }
    public void draw(){
        Graphics g2 = (Graphics)this.getGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
    }
}
