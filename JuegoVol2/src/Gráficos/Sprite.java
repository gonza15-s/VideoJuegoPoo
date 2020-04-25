
package Gráficos;

import Util.Vector2f;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Sprite{
    private  BufferedImage hojadeSprite=null;
    private BufferedImage[][] arraySprite;
    private final int TileSize = 32;
    public int w,h; 
    private int wSprite,hSprite;
    private Font f;
    public Sprite(String archivo){
        w=TileSize;
        h=TileSize;
        System.out.println("Cargando: "+archivo+"...");
        hojadeSprite = loadSprite(archivo);
        
        wSprite = hojadeSprite.getWidth()/w;
        hSprite = hojadeSprite.getHeight()/h;
        loadSpriteArray();
    }
    public Sprite(String archivo,int w,int h){
        this.w=w;
        this.h=h;
        System.out.println("Cargando: "+archivo+"...");
        hojadeSprite = loadSprite(archivo);
        loadSpriteArray();
        wSprite = hojadeSprite.getWidth()/w;
        hSprite = hojadeSprite.getHeight()/h;
    }
    public void setSize(int width,int height){
        setWidth(width);
        setHeight(height);
    }
    public void setWidth(int i){
        w=i;
        wSprite = hojadeSprite.getWidth()/w;
    }
    public void setHeight(int i){
        h=i;
        hSprite = hojadeSprite.getHeight()/h;
    }
    public int getWidth(){
        return w;
    }
    public int getHeight(){
        return h;
    }
    private BufferedImage loadSprite(String archivo){
        BufferedImage sprite = null;
        try{
            sprite=ImageIO.read(getClass().getClassLoader().getResourceAsStream(archivo));
        }catch(Exception e){
            System.out.println("Error: El archivo no pudo ser cargado"+archivo);
        }
        return sprite;
    }
    public void loadSpriteArray(){
        arraySprite = new BufferedImage[wSprite][hSprite];
        
        for (int x = 0; x < wSprite; x++) {
            for (int y = 0; y < hSprite; y++) {
                arraySprite[x][y] = getSprite(x,y);
            }
        }
    }
    public BufferedImage gethojadeSprite(){
        return hojadeSprite;
    }
    public BufferedImage getSprite(int x, int y){
        return hojadeSprite.getSubimage(x*w, y*h, w, h);
    }
    public BufferedImage[]getArraySprite(int i){
        return arraySprite[i];
    }
    public BufferedImage[][] getArraySprite2(int i){
        return arraySprite;
    }
    public static void drawArray(Graphics2D g, ArrayList<BufferedImage>img,Vector2f pos,int width,int height,int xOffset,int yOffset){
        float x = pos.x;
        float y = pos.y;
        for (int i = 0; i < img.size(); i++) {
            if (img.get(i)!=null) {
                g.drawImage(img.get(i),(int)x,(int)y,width,height,null);
            }
            x+=xOffset;
            y+=yOffset;
        }
    }
    public static void drawArray(Graphics2D g,Font f,String word,Vector2f pos,int width,int height,int xOffset,int yOffset){
        float x=pos.x;
        float y=pos.y;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i)!=32) {
                g.drawImage(f.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }
}
