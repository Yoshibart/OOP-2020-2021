package ie.tudublin;

import processing.core.PApplet;

public class YASC extends PApplet {
    boolean[] keys = new boolean[1024];

    
    // Update your forks!
    // Create a branch for today monday9
    // Write drawPlayer
    // Write movePlayer

    Player p;
    Health h;
    Ammo a;
    int health = 20;
    int ammo = 100;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        p = new Player(this, width / 2, height / 2);
        h = new Health(this, width / 2, height / 2, health);
        a = new Ammo(this, width / 2, height / 2, ammo);
    }

    public void draw() {
        background(0);
        stroke(255);
        p.update();
        p.render();
        h.render();
        h.update();
        a.render();
        a.update();
        
        // Check collisions        
        checkCollisions();
    }

    void checkCollisions() 
    {
    }

    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    public void mousePressed() {
    }

    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }
}