package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {
<<<<<<< HEAD
    Minim minim; //Connect to Minim
    AudioInput ai;//How to Coonect to mic
    AudioBuffer ab;//Samples
    AudioPlayer ap;//Samples
=======

    Minim minim; // Connect to minim
    AudioInput ai; // How to connect to mic
    AudioPlayer ap;
    AudioBuffer ab; // Samples
>>>>>>> fb1c3b2ed1447d3008220c7f350fce359ec6b150

    public void settings()
    {
        size(512, 512);
    }

    public void setup()
    {
        minim = new Minim(this);
<<<<<<< HEAD
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
=======
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        //ap = minim.loadFile("scale.wav", width);        
        // ap.play();
        ab = ai.mix; // Connect the buffer to mic
        //ab = ap.mix; // Connect the buffer to the mp3 file
        colorMode(HSB);

    }

    public void draw()
    {
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        for(int i = 0 ; i < ab.size(); i ++)
        {
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
            line(i, halfHeight - ab.get(i) * halfHeight, i, halfHeight + ab.get(i) * halfHeight);
            //println(ab.get(i));
        }

        // Calculate the AVERAGE amplitude

        ellipse(width / 2, 100, average * 100, average * 100);
>>>>>>> fb1c3b2ed1447d3008220c7f350fce359ec6b150
    }   
}