
package Gráficos;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class Font {
    private  BufferedImage hojadeFuente=null;
    private BufferedImage[][] arraySprite;
    private final int TileSize = 32;
    public int w,h; 
    private int wLetra,hLetra;
    public Font(String archivo){
        w=TileSize;
        h=TileSize;
        System.out.println("Cargando: "+archivo+"...");
        hojadeFuente = loadFont(archivo);
        
        wLetra = hojadeFuente.getWidth()/w;
        hLetra = hojadeFuente.getHeight()/h;
        loadSpriteArray();
    }
    public Font(String archivo,int w,int h){
        this.w=w;
        this.h=h;
        System.out.println("Cargando: "+archivo+"...");
        hojadeFuente = loadFont(archivo);
        loadSpriteArray();
        wLetra = hojadeFuente.getWidth()/w;
        hLetra = hojadeFuente.getHeight()/h;
    }
    public void setSize(int width,int height){
        setWidth(width);
        setHeight(height);
    }
    public void setWidth(int i){
        w=i;
        wLetra = hojadeFuente.getWidth()/w;
    }
    public void setHeight(int i){
        h=i;
        hLetra = hojadeFuente.getHeight()/h;
    }
    public int getWidth(){
        return w;
    }
    public int getHeight(){
        return h;
    }
    private BufferedImage loadFont(String archivo){
        BufferedImage sprite = null;
        try{
            sprite=ImageIO.read(getClass().getClassLoader().getResourceAsStream(archivo));
        }catch(Exception e){
            System.out.println("Error: El archivo no pudo ser cargado"+archivo);
        }
        return sprite;
    }
    public void loadSpriteArray(){
        arraySprite = new BufferedImage[wLetra][hLetra];
        
        for (int x = 0; x < wLetra; x++) {
            for (int y = 0; y < hLetra; y++) {
                arraySprite[x][y] = getFont(x,y);
            }
        }
    }
    public BufferedImage gethojadeFuente(){
        return hojadeFuente;
    }
    public BufferedImage getFont(int x, int y) {
        BufferedImage img = hojadeFuente.getSubimage(x * w, y * h, w, h);
        return img;
    }
   public BufferedImage getLetter(char letter) {
        int value = letter;

        int x = value % wLetra;
        int y = value / wLetra;
        return getFont(x, y);
    }
}
