package ie.tudublin;

import processing.core.PApplet;

public class Health{
    float x, y;
    float dx, dy;
    float w = 50;
    float halfW = w / 2;
    YASC yasc;
    float rotation;
    int health;


    public Health(YASC yasc, float x, float y, int health){
        this.yasc = yasc;
        this.x = x;
        this.y = y;
        this.health = health;
        rotation = 0;
    }

    void render()
    {
        yasc.pushMatrix();
        yasc.text("Health : " + health, x + 30,  y);
        yasc.popMatrix();
    }
    void update()
    {
        dx = PApplet.sin(rotation);
        dy =  - PApplet.cos(rotation);
        
        if (yasc.checkKey(PApplet.UP))
        {
            x += dx;
            y += dy;
        }
        if (yasc.checkKey(PApplet.DOWN))
        {
            x -= dx;
            y -= dy;
        }
        if (yasc.checkKey(PApplet.LEFT))
        {
            rotation -= 0.1f;
        }
        if (yasc.checkKey(PApplet.RIGHT))
        {
            rotation += 0.1f;
        }        
    }
}
