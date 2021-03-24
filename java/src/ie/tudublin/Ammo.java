package ie.tudublin;

import processing.core.PApplet;

public class Ammo {
    float x, y;
    float dx, dy;
    float w = 50;
    float halfW = w / 2;
    YASC yasc;
    float rotation;
    int ammo;


    public Ammo(YASC yasc, float x, float y, int ammo){
        this.yasc = yasc;
        this.x = x;
        this.y = y;
        this.ammo = ammo;
        rotation = 0;
    }

    void render()
    {
        yasc.pushMatrix();
        yasc.text("Ammo : " + ammo, x + 30,  y + 15);
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
