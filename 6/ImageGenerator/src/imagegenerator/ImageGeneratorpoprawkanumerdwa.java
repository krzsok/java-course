/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagegenerator;

import java.util.Stack;
//import java.util.Vector; v122


/**
 *
 * @author dm
 */
class ImageGenerator implements ImageGeneratorConfigurationInterface, ImageGeneratorInterface{

    private boolean[][] canvas;
    private int colposition;
    private int rowposition;
    private int maxrepeatcommands;
    Stack<Move> lastmoves = new Stack();
    Stack<Move> undoredobuffer = new Stack();
    Stack<Move> repeatbuffer = new Stack();
    Stack<Move> repeatbuffer2 = new Stack();
    
    

    @Override
    public void setCanvas(boolean[][] canvas) {
        this.canvas = canvas;
    }

    @Override
    public void setInitialPosition(int col, int row) {
        canvas[col][row] = true;
        this.colposition = col;
        this.rowposition = row;
       
    }

    @Override
    public void maxUndoRedoRepeatCommands(int commands) {
        this.maxrepeatcommands = commands;
    }

    @Override
    public void up(int steps) {
        execute(new Move('u', steps, rowposition, colposition), false, true, false);
    }

    @Override
    public void down(int steps) {
        execute(new Move('d', steps, rowposition, colposition), false, true, false);
    }

    @Override
    public void left(int steps) {
        execute(new Move('l', steps, rowposition, colposition), false, true, false);
    }

    @Override
    public void right(int steps) {
        execute(new Move('r', steps, rowposition, colposition), false, true, false);
    }

    @Override
    public void repeat(int commands) {
        int temp = commands;
        int temp2 = lastmoves.size() - 1;
        while(temp != 0)
            {
                //lastmoves.elementAt()
                
                ///////////////////////////////////////////////lastmoves.get(commands ostatnich) i wykonaj je, po czym zostaw znacznik '8'
                
                ///////////////////////////////////////////albo wez commands ostatnich ruchow i wbij do obiektu '8' (lista Move - dodac kolejne pole do klasy ktore bedzie uzywane tylko przy '8'?) ktory undo bedzie wykonywac
                repeatbuffer2.push(lastmoves.peek());
                
                repeatbuffer.push(lastmoves.elementAt(temp2--));
                temp--;
            }
        
        temp = commands;
        while(temp != 0)
        {
            execute(repeatbuffer.pop(),false,true,false);
            temp--;
        }
        
        lastmoves.push(new Move('8', repeatbuffer2.size(),rowposition, colposition));
    }
    
     public void undorepeat()
     {
         while(!repeatbuffer2.isEmpty())
        {
            execute(repeatbuffer2.pop(),true,false,false);
        }
     }

    @Override
    public void undo(int commands) {
        int temp = commands;
        if(commands <= maxrepeatcommands && lastmoves.size() >= commands)
        {
            while(temp != 0)
            {
                undoredobuffer.push(lastmoves.pop());
                if(undoredobuffer.peek().move == '8')
                {
                    /*
                    int temp2 = undoredobuffer.peek().steps;
                    Stack<Move> srak = new Stack<>();
                    
                    while(temp2 != 0)
                    {
                        srak.push(lastmoves.pop());
                        temp2--;
                    }
                    
                    temp2 = 2;
                    while(temp2 != 0)
                    {
                        for(int i=srak.size() - 1;i>=0;i--)
                        {
                            lastmoves.push(srak.elementAt(i));
                        }
                        temp2--;
                    }       
                        
                    
                    temp = temp + undoredobuffer.peek().steps;
                    undoredobuffer.pop();
                    srak.clear();
                    
                    */
                    
                    temp = temp + undoredobuffer.peek().steps - 1;
                    undoredobuffer.pop();
                    undoredobuffer.push(lastmoves.pop());
                    //Stack srak = undoredobuffer.push()
                }
                
                
                execute(new Move(undoredobuffer.peek().move, undoredobuffer.peek().steps, undoredobuffer.peek().row, undoredobuffer.peek().col ), true, false, false);
                temp--;
                
            }
            
            
            
       }
    }

    @Override
    public void redo(int commands) {
        int temp = commands;
        while(temp != 0)
            {
                if(undoredobuffer.size() > 1)
                {
                    execute(undoredobuffer.pop(), false, false, true);
                    lastmoves.push(new Move(undoredobuffer.peek().move, undoredobuffer.peek().steps, undoredobuffer.peek().row, undoredobuffer.peek().col ));
                }
                else
                {
                    execute(undoredobuffer.pop(), false, true, true);
                }
                temp--;
            }
    }
    
    public void print()
    {
        
        
        for(int i=canvas.length - 1;i>=0;i--)
        {
            System.out.println("");
            for(int j=0;j<canvas[0].length;j++)
            {
                if(canvas[i][j] == true)
                System.out.print("X ");
                else
                System.out.print("O ");
            }
        }
        
        System.out.println("");
        System.out.println("");
    }
    
    public void execute(Move move, boolean executeOpposite, boolean cleanURBuffer, boolean redo)
    {
        /*if(executeOpposite)
        {
            if(move.move == 'u')
                move.move = 'd';
            else if(move.move == 'd')
                move.move = 'u';
            else if(move.move == 'l')
                move.move = 'r';
            else if(move.move == 'r')
                move.move = 'l';
        }*/
        
        switch  (move.move) {
            case 'u':
            {
                int temp = move.steps;
                //canvas[rowposition][colposition] = !executeOpposite;
                while(temp != 0)
                {
                    if(executeOpposite)
                        canvas[move.col][move.row + temp] = false;
                    else if(redo)
                        canvas[move.col][move.row + temp] = true;       
                    else    
                        canvas[colposition][rowposition + temp] = true;
                    temp--;
                }
                rowposition = rowposition + move.steps;
                
                if(cleanURBuffer)
                {
                    lastmoves.push(new Move('u', move.steps, rowposition - move.steps, colposition));
                    undoredobuffer.clear();
                }
            }
                break;
            case 'd':
            {
                int temp = move.steps;
                //canvas[rowposition][colposition] = !executeOpposite;
                while(temp != 0)
                {
                    if(executeOpposite)
                        canvas[move.col][move.row - temp] = false;
                    else if(redo)
                        canvas[move.col][move.row - temp] = true;
                    else
                        canvas[colposition][rowposition - temp] = true;
                    temp--;
                }
                rowposition = rowposition - move.steps;
                if(cleanURBuffer)
                {
                    lastmoves.push(new Move('d', move.steps, rowposition + move.steps, colposition));
                    undoredobuffer.clear();
                }
            }
                break;
            case 'l':
            {
                int temp = move.steps;
                //canvas[rowposition][colposition] = !executeOpposite;
                while(temp != 0)
                {
                    if(executeOpposite)
                        canvas[move.col - temp][move.row] = false;
                    else if(redo)
                        canvas[move.col - temp][move.row] = true;
                    else
                     canvas[colposition - temp][rowposition] = true;
                    temp--;
                }
                colposition = colposition - move.steps;             
                if(cleanURBuffer)
                {
                    lastmoves.push(new Move('l', move.steps, rowposition, colposition + move.steps));
                    undoredobuffer.clear();
                }
            }
                break;
            case 'r':
            {
                int temp = move.steps;
                //canvas[rowposition][colposition] = !executeOpposite;
                while(temp != 0)
                {
                    if(executeOpposite)
                        canvas[move.col + temp][move.row] = false;
                    else if(redo)
                        canvas[move.col + temp][move.row] = true;
                    else
                        canvas[colposition + temp][rowposition] = true;
                    temp--;
                }
                colposition = colposition + move.steps;               
                if(cleanURBuffer)
                {
                    lastmoves.push(new Move('r', move.steps,rowposition, colposition - move.steps));
                    undoredobuffer.clear();
                }
            }
                break;
            case '8':
            {
               // undo(move.steps);
        
                //  int temp = move.steps;
                ////canvas[rowposition][colposition] = !executeOpposite;
                //while(temp != 0)
                //{
                  //  execute(lastmoves.pop(),false, true, false);
                    //temp--;
                //}
                
            }
                break;
                
                
                
                
               // undo(move.steps);
               // undorepeat();
            
            default:
                break;
        }
    }
    
    public static void main(String[] args) {
        
        ImageGenerator ig = new ImageGenerator();
        
        boolean[][] canvastest = new boolean[15][15];
        
        ig.setCanvas(canvastest);
        
        //ig.setInitialPosition(2, 1); // kolumna, wiersz
        
        ig.maxUndoRedoRepeatCommands(3);
        
       /* 
	ig.	up(2);
        ig.print();
	ig.	setInitialPosition(0, 2);
        ig.print();
	ig.	right(4);
        ig.print();
	ig.	undo(1);
        ig.print();
        
        */
        ig.setInitialPosition(0, 2);
	ig.	up(2);
	ig.	right(4);
	ig.	down(3);
	ig.	left(1);
        ig.print();
	ig.	repeat(1);
        ig.print();
	ig.	undo(2);
        ig.print();
	ig.	undo(1);
        ig.print();
	ig.	redo(2);
        ig.print();
	ig.	redo(1);
        ig.print();
        
        
        
        ///////////////////////////////////////////////////////////////////////REPEAT POWINNO DODAWAC POPRZEDNIE RUCHY DO LASTMOVES, a undo pilnowac zeby w razie repeat cofnac jeszcze N ruchow
        
    }
    
}

class Move {
    char move;
    int steps;
    int row;
    int col;
    
    public Move(char move, int steps, int row, int col)
    {
        this.move = move;
        this.steps = steps;
        this.row = row;
        this.col = col;
    }
}