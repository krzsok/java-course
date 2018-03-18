/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author dm
 */
class Wrapper implements WrappingInterface {

    private int slots;
    private SourceInterface source;
    private Integer[] data;
    private int startIndex = 0;
    private boolean EOD = false;
    
    
    public static void main(String[] args) {
        System.out.println("hello");
        
        Source s = new Source();
        Wrapper w = new Wrapper();
        
        w.setSource(s);
        w.setNumberOfSlots(2);
        Integer[] a = w.get();
        System.out.println("po a");
        Integer[] b = w.get();
        System.out.println("po b");
        
        Integer[] c = w.get();
        System.out.println("po c");
        
    }
    
    
    @Override
    public void setNumberOfSlots(int slots) {
        this.slots = slots;
    }

    @Override
    public void setSource(SourceInterface stream) {
        this.source = stream;
        this.EOD = false;
    }

    
    @Override
    public Integer[] get() {
        
        if(startIndex == 0)
        {
            data = new Integer[slots];
        }

        try
        {
            if(this.EOD == false)
            {
                for(int i=startIndex;i<data.length;i++)
                {
                    startIndex = i;
                    data[i] = source.getValue();
                }
            }
            else
            {
                return data;
            }
        }
        catch(EndOfDataException e)
        {
            startIndex = 0;
            this.EOD = true;
            return data;
        }
        catch(UrgentException u)
        {
            startIndex = 0;
            return data;
        }
        catch(TemporaryNoDataException t)
        {
            get();
        }
        catch(WaitException w)
        {
            try 
            {
                Thread.sleep(w.doNothingTime);
                get();
                
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Wrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        startIndex = 0;
            
        return data;
            
    }  

    
    
}
/*
class Source implements SourceInterface {

    private int counter = 0;
    private ArrayList<Integer> values = new ArrayList<>();;
    
    Source()
    {
        for(int i = 0;i<11;i++)
        {
            values.add(i);
        }
    }
    
    @Override
    public int getValue() throws EndOfDataException, TemporaryNoDataException, WaitException, UrgentException {
        
        int value = values.get(values.size() - 1);
        values.remove(values.size() - 1);
        counter++;
        
        if(counter == 4 || counter == 6)
        {
            throw new WaitException(1000);
        }
 
        
        
        return value;
        
    }
    
    
}
*/