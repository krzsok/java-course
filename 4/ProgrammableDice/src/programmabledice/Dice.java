/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmabledice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author dm
 */
class Dice extends ProgrammableDice {

    private int faces;
    private Random rnd = new Random();
    private Integer activeprogramid = null;
    private List<Program> programs = new ArrayList<>();
    private List<Integer> lastmoves = new ArrayList<>();
    private int counter = 0;
    
    
    @Override
    public void setNumberOfFaces(int faces) {
        this.faces = faces;
    }

    @Override
    public void addProgram(int[] initializationSequence, int interspace, int[] outputSequence, int repetitions) {
        
        programs.add(new Program(initializationSequence, interspace, outputSequence, repetitions));
        Collections.sort(programs);
        
    }

    public Boolean checkForInitialization(List<Integer> tempmoves, Program p, int id)
    {
        //int counter = 0;
        
        if(p.repetitions == 0) //bledy 2 i 3
        {
            //programs.remove(id);
            return false;
        }
        if(tempmoves.isEmpty())
        {
            return false;
        }
        if(!tempmoves.isEmpty() && tempmoves.size() < p.initializationSequence.length)
        {
            return false;
        }
        
        
        for(int i=0;i<p.initializationSequence.length;i++)//while(counter != p.initializationSequence.length) //blad nr0
        {
            
            if(!tempmoves.isEmpty())
            {
                if(tempmoves.get(i) != p.initializationSequence[i])
                {
                    return false;
                }
                //counter++;
            }
            
                
            
        }
        
        
        
        p.repetitions--;
        return true;
    }
    
    @Override
    public int get() {
        //dla programu o N initialization sequence sprawdzac ostatnie N symboli wylosowanych przez get()
        
        if(!programs.isEmpty())
        {
                for(int i=0; i<programs.size();i++)
            {
            
                Program p = programs.get(i);
                List<Integer> tempmoves = lastmoves.subList(Math.max(lastmoves.size() - p.initializationSequence.length, 0), lastmoves.size()); //tu mozna lepiej blad
                if(activeprogramid == null) //blad nr1
                {
                    if(checkForInitialization(tempmoves, p, i))
                    {
                        activeprogramid = i;
               
                    }
                }
            }
        }
        
        
        if(activeprogramid == null)
        { 
            int move = rnd.get(faces);
            lastmoves.add(move);
            return move;
        }
        else
        {
            Program p = programs.get(activeprogramid);
            if(p.interspace != 0) //ew. while?
            {
                p.interspace--;
                return rnd.get(faces);
            }
            else if(p.interspace == 0)
            {
                
                
                    int move = p.outputSequence[counter++];
                    lastmoves.add(move);
                    
                    if(p.outputSequence.length == counter) //blad nr5
                    {
                    activeprogramid = null;
                    counter = 0;
                    
                    }
                    
                    
                    return move;
                
                
            } 
        }
            
        
        
        int move = rnd.get(faces);
        lastmoves.add(move);
        return move;
        
          
    }
 
    
}

class Program implements Comparable<Program>{
    int[] initializationSequence;
    int interspace;
    int[] outputSequence;
    int repetitions;
    
    public Program (int[] initializationSequence, int interspace, int[] outputSequence, int repetitions) {
        this.initializationSequence = initializationSequence;
        this.interspace = interspace;
        this.outputSequence = outputSequence;
        this.repetitions = repetitions;
    }
    
    @Override
    public int compareTo(Program p)
    {
        if ( this.initializationSequence.length < p.initializationSequence.length )
            return 1;
        else if ( this.initializationSequence.length > p.initializationSequence.length)
            return -1;
        else 
        {
             return 0;
        }
    }
}
