/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicehistogram;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dm
 */
class DiceHistogramImplementation implements DiceHistogram {

    private DiceInterface di;
    private int intervals;
    private List<Bin> bins = new ArrayList<>();
    
    
    
    @Override
    public void setDice(DiceInterface di) {
        this.di = di;
        intervals = di.getNumberOfIntervals();
        
        for(int i=0;i<intervals;i++)
        {
            int dotsInInterval = di.getInterval(i).upperEndpoint() - di.getInterval(i).lowerEndpoint();
            int temp = di.getInterval(i).lowerEndpoint();
            for(int j=0;j<=dotsInInterval;j++)
            {
                bins.add(new Bin(temp++));
            }
        }
        
    }

    @Override
    public Histogram getHistogram(int atLeastCounts) {
   
       while(checkBins(atLeastCounts))
       {
           throwDice();
       }
    
       return new HistogramImplementation(bins);
       
    }
    
    public void throwDice()
    {
        int temp = di.getNumberOfDots();
            
            for(Bin b : bins)
            {
                if(b.dots == temp)
                {
                    b.count++;
                }
            }
    }
    
    public Boolean checkBins(int atLeastCounts)
    {
        for(Bin b : bins)
            {
                if(b.count < atLeastCounts)
                {
                   return true;  
                }
            }
        
        return false;
    }
    
    /*
    public static void main(String[] args) {
        
        PMO_Dice myDice = new PMO_Dice();
        List<Integer> dots = new ArrayList<>();
        dots.add(1);
        dots.add(2);
        dots.add(3);
        
        myDice.addDots(dots);
		myDice.addInterval(new PMO_Interval(10, 12));
                
                DiceHistogramImplementation dhi = new DiceHistogramImplementation();
		dhi.setDice(myDice);
		HistogramImplementation asd = (HistogramImplementation) dhi.getHistogram(7);
                System.out.println(asd.numberOfBins());
                System.out.println(asd.getNumberOfDots(0));
                System.out.println(asd.numberOfCounts(0));
                
        
    }
 */   
}

class HistogramImplementation implements Histogram {

    private List<Bin> bins;
    
    
    public HistogramImplementation(List<Bin> bins)
    {
        this.bins = bins; 
    }
    
    @Override
    public int numberOfBins() {
        return bins.size();
    }

    @Override
    public int getNumberOfDots(int bin) {
        return bins.get(bin).dots;
    }

    @Override
    public int numberOfCounts(int bin) {
        return bins.get(bin).count;
    }
    
    
    
}

class Bin {
    final int dots;
    int count = 0;
    
    public Bin(int dots)
    {
        this.dots = dots;
    }
}

class PMO_Interval implements Interval {
	
	private final int lowerEnd;
	private final int upperEnd;

	public PMO_Interval( int lowerEnd, int upperEnd ) {
		this.lowerEnd = lowerEnd;
		this.upperEnd = upperEnd;
	}
	
	@Override
	public int lowerEndpoint() {
		return lowerEnd;
	}

	@Override
	public int upperEndpoint() {
		return upperEnd;
	}

}

class PMO_Dice implements DiceInterface {

	private final List<Interval> intervals = new ArrayList<>();
	private List<Integer> dots;
	private int counter;

	public void addInterval(Interval interval) {
		intervals.add(interval);
	}

	public void addDots(List<Integer> dots) {
		this.dots = dots;
	}

	@Override
	public int getNumberOfDots() {
		return dots.get(counter++ % dots.size());
	}

	@Override
	public int getNumberOfIntervals() {
		return intervals.size();
	}

	@Override
	public Interval getInterval(int intervalID) {
		return intervals.get(intervalID);
	}

}