package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet{
    private int size = 100;
    private float cellSize;
    int mode = 0;
    boolean paused = false;
    boolean[][] board = new boolean[size][size];
    boolean[][] next = new boolean[size][size];

    public void settings(){
        size(500, 500);
    }

    public int countNeighbours(int row, int col){
        int count = 0;

        for(int r = row - 1; r <= row + 1; r++){        
            
            for(int c = col - 1; c <= col + 1; c++){
                
                if(! (r == row && c == col)){
                    
                    if(getCell(board, r, c)){
                        
                        count++;
                    }
                }
            }  
        }


        return count;
    }

    public void randomize(){
        for(int r = 0; r < size; r++){        
            
            for(int c = 0; c < size; c++){

                float dice = random(0.0f, 1.0f);

                board[r][c] =  dice < 0.5f ? true : false;
            }
        }
    }

    public void clear(){
        for(int r = 0; r < size; r++){        
            
            for(int c = 0; c < size; c++){
                setCell(board, r, c, false);
            }
        }
    }

    public boolean getCell(boolean[][] board, int row, int col){
        
        if(row >= 0 && row < size - 1 && col >= 0 & col < size - 1){
           
            return  board[row][col];
        }
        return false;
    }

    public void setCell(boolean[][] board, int row, int col, boolean b){
        
        if(row >= 0 && row < size - 1 && col >= 0 & col < size - 1){
           
            board[row][col] = b;
        }
    }

    public void printBoard(boolean[][] board){
        for(int r = 0; r < size; r++){        
            
            for(int c = 0; c < size; c++){

                println(board[r][c] ? 1 : 0);
            }
        }
    }

    public void setup(){
        colorMode(HSB, 360, 100, 100);
        randomize();

        println(countNeighbours(0, 2));

        cellSize = width / (float)size;

        //printBoard(board);
    }

    private void updateBoard(){
 
        for(int r = 0; r < size; r++){        
            
            for(int c = 0; c < size; c++){

                int count = countNeighbours(r, c);
                if(getCell(board, r, c)){
                    next[r][c] = (count == 2 || count == 3) ? true : false;
                }
                else{
                    next[r][c] = (count == 3) ? true : false;
                }
            }
        }
        boolean[][] temp = board;
        board = next;
        next = temp;       
    }

    public void drawBoard(boolean[][] board){
        //float border = width * 0.1f;

        for(int r = 0; r < size; r++){        
            
            for(int c = 0; c < size; c++){
                
                float X = map(r, 0, size, 0, width);
                // float X = map(r, 0, size, border, width - border);
                float Y = map(c, 0, size, 0, height);
                // float Y = map(c, 0, size, border, height - border);
                //fill(360);
                if( board[r][c]){
                    rect(X, Y, cellSize, cellSize);
                }
            }  
        }

    }

    public void draw(){
        background(0);
        drawBoard(board);
        if(!paused){
            updateBoard();
        }
    }

    public void keyPressed(){
        
        if(keyCode == ' '){

            paused = ! paused;
        }
        if(keyCode == '2'){

            clear();
        }
        if(keyCode == '3'){

           makeCross();
        }
    }
    public void makeCross(){
        for(int i = 0; i < size; i++ ){
            setCell(board, size / 2, i, true);
            setCell(board, i, size / 2, true);
        }
    }
    
}
