package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet {
    ArrayList<Star> stars = new ArrayList<Star>();
    float border;
    int startStar = -1;
    int endStar = -1;

    public void drawgrid(){
        background(0);
        for(int i = -5; i <= 5; i++){
            float X = map(i, -5, 5, border, width - border);
            stroke(240, 100, 100);
            line(X, border, X, height - border);
            line(border, X, height - border, X);
            fill(360);
            text(i, X, border / 2);
            text(i, border / 2, X);
        }
    }

    public void drawStars(){
        for(Star star : stars){
            star.render(this);
        }
    }

    public void loadStars(){
        Table table = loadTable("HabHYG15ly.csv", "header");
        for(TableRow row : table.rows()){
            Star star = new Star(row);
            stars.add(star);
        }
    }

    public void setup(){
        colorMode(HSB, 360, 100, 100);
        border = width * 0.1f;
        loadStars();
        drawgrid();
        drawStars();
        
    }

    public void mouseClicked(){
        for(int i = 0; i < stars.size(); i++){
            Star s = stars.get(i);
            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);
            if(dist(mouseX, mouseY, x, y) < s.getAbsMag() / 2){
                if(startStar == -1){
                    startStar = i;
                    break;
                }
                else if(endStar == -1){
                    endStar = i;
                    break;
                }
                else{
                    startStar = i;
                    endStar = -1;
                }
            }
        }
    }

    public void settings(){
        size(800, 800);
    }

    public void draw(){
        colorMode(HSB, 360, 100, 100);
        background(0);
        drawgrid();
        drawStars();
        
        if(startStar != -1 && endStar == -1){
            fill(360, 100, 100);
            float x = map(stars.get(startStar).getxG(), -5, 5, border, width - border);
            float y = map(stars.get(startStar).getyG(), -5, 5, border, width - border);
            line(x, y, mouseX, mouseY);
        }else if(startStar != -1 && endStar != -1){

            float x1 = map(stars.get(startStar).getxG(), -5, 5, border, width - border);
            float y1 = map(stars.get(startStar).getyG(), -5, 5, border, width - border);
            
            float x2 = map(stars.get(endStar).getxG(), -5, 5, border, width - border);
            float y2 = map(stars.get(endStar).getyG(), -5, 5, border, width - border);
            fill(360, 100, 100);
            line(x1, y1, x2, y2);

            float distance = dist(
                                stars.get(startStar).getxG(),
                                stars.get(startStar).getyG(),
                                stars.get(startStar).getzG(),
                                stars.get(endStar).getxG(),
                                stars.get(endStar).getyG(),
                                stars.get(endStar).getzG()
            );
            fill(360);
            textAlign(CENTER, CENTER);
            text("Distance between " + stars.get(startStar).getDisplayName() + " and " + stars.get(endStar).getDisplayName() + " is " + distance + " parsecs",
                width / 2,
                height - (border / 2)
            );
        }
    }
}
