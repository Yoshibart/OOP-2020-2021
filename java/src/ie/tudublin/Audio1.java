package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {
    Minim minim; //Connect to Minim
    AudioInput ai;//How to Coonect to mic
    AudioBuffer ab;//Samples
    AudioPlayer ap;//Samples

    public void settings()
    {
        size(512, 512);
    }

    public void setup()
    {
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, width,  44100, 16);
        ap = minim.loadFile("heroplanet.mp3", width);
        //ap.play();
        ab = ai.mix;
        ab = ap.mix;
        colorMode(HSB);
    }

    public void draw()
    {   background(0);
        stroke(255);
        float halfHeight  = height / 2;
        float average = 0;
        for(int i = 0; i < ab.size(); i++){
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
            line(i, halfHeight, i, halfHeight + ab.get(i) * halfHeight);
            println(ab.get(i));
        }
        
        ellipse();
    }   
}