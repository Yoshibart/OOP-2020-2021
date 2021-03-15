package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet{
    int mode = -1;
    float[] rainfall = {45, 37, 55, 27, 38, 50, 79, 48, 104, 31, 100, 58};
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public float map1(float from, float start1, float end1, float start2, float end2){
        float range1 = end1 - start1;
        float range2 = end2 - start2;
        float howFar = from - start1;
        return start2 + (howFar / range1 ) * range2;
    }

    public void settings(){
        size(500, 500);
    }

    public void setup(){
        colorMode(HSB, 360, 100, 100);
        background(0);
        drawgrid();
        float w = width / (float) rainfall.length;
        for(int i = 0; i < rainfall.length; i++){
            noStroke();
            fill(random(255), 255, 255);
            float x = map(i, 0, rainfall.length, 0, width);
            rect(x, height, w, -rainfall[i]);
        }

    }

    public void drawgrid(){
        stroke(360);
        float border  = width * 0.1f;
        textAlign(CENTER, CENTER);
        for(int i = -5; i <= 5; i++){
            float x = map1(i, -5, 5, border, width - border);
            line(x, border, x, height - border);
            line(border, x, width - border, x);
            fill(360);
            text(i, x, border * 0.5f);
            text(i, border * 0.5f, x);
        }
    }

    public float sum(float[] array){
        float sum = 0.0f;
        for(float arr : array)
            sum += arr;
        return sum;
    }

    public void keyPressed(){
        if(keyCode <= '9' && keyCode >= '0'){
            mode = keyCode - '0';
        }
    }
    
    public void draw(){
        switch(mode){
            case 0:{
                background(0);
                float border = width * 0.1f;
                float range = 120;
                stroke(360);
                line(border, border, border, height - border);
                line(border, height - border, width - border, height - border);
                textAlign(CENTER, CENTER);
                for(float f = 0; f <= range; f += 10){
                    float y = map(f, 0, range, height - border, border);
                    line(border - 5, y, border, y);
                    fill(360);
                    text((int)f, border * 0.5f, y);
                }

                float w = (width - (border * 2))/ (float)rainfall.length;
                for(int i = 0; i < rainfall.length; i++){
                    float x = map(i, 0, rainfall.length, border, width - border);
                    float h = map(rainfall[i], 0, range, 0, height - (border * 2));
                    float c = map(i, 0, rainfall.length, 0, 360);
                    
                    // noStroke();
                    fill(c, 100 , 100);
                    rect(x, height - border , w, -h);

                    fill(360);
                    text(months[i], x + w * 0.5f, height - border / 2);
                 }

                 fill(360);
                 text("Rainfall Bar Chart", width / 2, border * 0.5f);
                 break;
            }
            case 1:{
                background(0);
                float border = width * 0.1f;
                float range = 120;
                stroke(360);
                line(border, border, border, height - border);
                line(border, height - border, width - border, height - border);
                textAlign(CENTER, CENTER);
                for(float f = 0; f <= range; f += 10){
                    float y = map(f, 0, range, height - border, border);
                    line(border - 5, y, border, y);
                    fill(360);
                    text((int)f, border * 0.5f, y);
                }

                float w = (width - (border * 2))/ (float)rainfall.length;
                for(int i = 0; i < rainfall.length; i++){
                    float x = map(i, 0, rainfall.length, border, width - border);
                    
                    fill(360);
                    text(months[i], x + w * 0.5f, height - border / 2);
                 }

                for(int i = 0; i < rainfall.length - 1; i++){
                
                float x1 = map(i, 0, rainfall.length - 1, border + (w / 2), width - border - (w / 2));
                float y1 = map(rainfall[i], 0 , range, height - border, border);
                
                float x2 = map(i+1, 0, rainfall.length - 1 , border + (w / 2), width - border - (w / 2));
                float y2 = map(rainfall[i + 1], 0 , range, height - border, border);

                line(x1, y1, x2, y2);
                point(x1, y1);
                // line(x1, y1, x1, height - border);
                }
                

                fill(360);
                text("Rainfall TrendLine Chart", width / 2, border * 0.5f);
                break;
            }

            case 2:{
                background(0);
                float border = width * 0.1f, sum = sum(rainfall), thetaPrev = 0, X = width / 2, Y = height / 2;
                textAlign(CENTER);
                
                for(int i = 0; i < rainfall.length; i++){
                    
                    float x = map(rainfall[i], 0, sum, 0, TWO_PI);git 
                    // textAlign(CENTER);
                    float c = map(i, 0, rainfall.length, 0, 360);
                    float thetaNext = thetaPrev + x;
                    float radius = X * 0.6f;
                    float sinY = X + sin(thetaPrev + (x * 0.5f) + HALF_PI) * radius;
                    float cosX = Y - cos(thetaPrev + (x * 0.5f) + HALF_PI) * radius;
                    
                    fill(255);
                    text(months[i], sinY, cosX);
                    fill(c, 100, 100);
                    stroke(255);
                    arc(X, Y, X, Y, thetaPrev, thetaNext);
                    thetaPrev = thetaNext;
                }

                fill(360);
                text("Rainfall Pie Chart", width / 2, border * 0.5f);
            }
        }
    }
}
