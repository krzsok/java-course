/*to
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package histogramgenerator;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author dm
 */
class HistogramGenerator implements HistogramGeneratorInterface, Runnable {

    private HistogramInterface histogram;
    private DiceInterface dice;
    private int threads;
    private Thread[] threadsTable;
    private int throwsPerThread;
    List<Integer> synGetList = Collections.synchronizedList(new ArrayList<Integer>());
    private volatile int lastGet = 0;
    Iterator<Integer> iter = synGetList.iterator();
    List<Object> locks = new ArrayList<>();
    /*
    public static void main(String[] args) throws InterruptedException {
        HistogramGenerator hg = new HistogramGenerator();
        dice dc = new dice();
        hist hs = new hist();
        
        
        hg.setDice(dc);
        hg.setHistogram(hs);
        hg.setNumberOfThreads(3);
        long startTime = System.currentTimeMillis();
        hg.start(211111100);
        //hist a = hg.startr(600000000);

        Thread.sleep(5000);
        
        long endTime = System.currentTimeMillis();
        
        for(int i=0;i<2;i++)
        {
            System.out.println("bin get" + hs.get(i));
        }
        
        
        System.out.println("That took " + (endTime - startTime) / 100 + " deciseconds");
        
        
        //hg.start(2);
        
        //hg.start(4);
    }*/
    
    @Override
    public void setHistogram(HistogramInterface histogram) {
        this.histogram = histogram;
    }

    @Override
    public void setDice(DiceInterface dice) {
        this.dice = dice;
    }

    @Override
    public void setNumberOfThreads(int threads) {
        this.threads = threads;
    }

    
    
    @Override
    public void start(int numberOfDraws) {
        
        this.throwsPerThread = numberOfDraws / threads;
        int remainder = numberOfDraws % threads;
        histogram.setSize(dice.getNumberOfFaces());
        int bb = dice.getNumberOfFaces();
        for(int i=0;i<bb;i++)
        {
            locks.add(new Object());
        }
        if(remainder != 0)
        {
            for(int i=0;i<remainder;i++)
            {
                histogram.increment(dice.get());
                //System.out.println("tu remainder");
            }
        }
        
        threadsTable = new Thread[threads];
        
        for(int i=0;i<threadsTable.length;i++)
        {
            threadsTable[i] = new Thread(this);
            threadsTable[i].start();
            
        }
        /*
        for (Thread t : threadsTable) {
            try {
                t.join();
            }catch (InterruptedException ex) {
                Logger.getLogger(HistogramGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
        
    }

    @Override
    public void run() 
    {
        for(int i=0;i<throwsPerThread;i++)
        {
           //synGetList.add(dice.get());
           
           int a = dice.get();
           //lastGet = a;
           
           
              synchronized(this.locks.get(a - 1))
              {
                    histogram.increment(a);
                    //System.out.println("tu synchronized watek " + Thread.currentThread().getName());
              }
           
           
           /*
           
           */
        }

        try {
                Thread.currentThread().join();
            }catch (InterruptedException ex) {
                Logger.getLogger(HistogramGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*
        synchronized(this)
           {
               
               
                while (iter.hasNext())
                {
                    histogram.increment(iter.next());
                }

               //Thread.currentThread().yield();
               //histogram.increment(a);
           }
       
     */
    }
        
  
    
} 