/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author dm
 */
public class Sorter implements SorterInterface {

    private Stream<? extends Box3D> stream;
    //List<? super Box3D> output = new ArrayList<>();
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sorter s = new Sorter();
        
        
    PMO_Box3D_A a1 = new PMO_Box3D_A(1);
	PMO_Box3D_A a2 = new PMO_Box3D_A(2);
	 PMO_Box3D_A a3 = new PMO_Box3D_A(3);
 PMO_Box3D_A a4 = new PMO_Box3D_A(4);

	PMO_Box3D_B b11 = new PMO_Box3D_B(11);
	PMO_Box3D_B b21 = new PMO_Box3D_B(21);
 PMO_Box3D_B b31 = new PMO_Box3D_B(31);
	PMO_Box3D_B b41 = new PMO_Box3D_B(41);

	 Box3D box3D21 = new Box3D(21);
	 Box3D box3D31 = new Box3D(31);
	 Box3D box3D51 = new Box3D(51);
	 Box3D box3D61 = new Box3D(61);

	Box3DWithSize boxWS10_10 = new Box3DWithSize(10, 10);
	 Box3DWithSize boxWS12_15 = new Box3DWithSize(12, 15);
	 Box3DWithSize boxWS10_05 = new Box3DWithSize(10, 5);
	 Box3DWithSize boxWS10_09 = new Box3DWithSize(10, 9);
    
		s.setInputStream(Stream.of(b31, b41, box3D21, boxWS10_05, box3D21, a1));

		List<? super Box3D> output = new ArrayList<>();
		List<Box3D> additional = Arrays.asList(a4, box3D51, box3D51, a1, b11, boxWS10_05, boxWS12_15);
		s.sizeSort(additional, output);
                System.out.println(output);
                int a = 4;
    }

    @Override
    public void setInputStream(Stream<? extends Box3D> stream) {
        this.stream = stream;
    }

    @Override
    public Map<String, List<Box3D>> find(List<? extends Box3D> additionalInput) {
        /*
        Map<String, List<Box3D>> map = new HashMap<>();
        List<Box3D> Box3DList = new ArrayList<>();
        List<Box3D> Box3DWithSizeList = new ArrayList<>();
        Set<String> names = new HashSet<>();
        List<Box3D> streamInput = stream.collect(Collectors.toList());
        streamInput.addAll(additionalInput);
        for(Box3D b : streamInput)
        {
            String name = SorterInterface.getName(b);
            if(name.contains("WithSize"))
            {
                Box3DWithSizeList.add(b);
            }
            else
            {
                Box3DList.add(b);
            }
            names.add(name);
            
        }
        
        for(String s : names)
        {
            if(s.contains("WithSize"))
            {
                map.put(s, Box3DWithSizeList);
            }
            else
            {
                map.put(s, Box3DList);
            }
            
        }
        
        return map;
        */
        
        Stream<? extends Box3D> concated = Stream.concat(this.stream,additionalInput.stream());
        
        Map<String, List<Box3D>> returnmap = concated.collect(Collectors.groupingBy(p -> SorterInterface.getName(p)));
 
        return returnmap;
    }

    @Override
    public void sizeSort(List<? extends Box3D> additionalInput, List<? super Box3D> output) {
        Stream<Box3D> concated = Stream.concat(this.stream,additionalInput.stream());
        
        //output = concated.sorted(Comparator.comparing(p -> p.getValue())).collect(Collectors.toList());
        List<Box3D> outputbis = concated.sorted((Box3D o1, Box3D o2)-> Integer.valueOf(o1.getValue()).compareTo(Integer.valueOf(o2.getValue()))).collect(Collectors.toList());
        
        
        for(Box3D b : outputbis)
        {
             output.add(b);
           
        }
        int a = 4;
        
    }

    @Override
    public void findObjectsDifferentThan(Box3D object, List<? super Box3D> output) {
        
        List<Box3D> outputbis = this.stream.filter((p -> !SorterInterface.getName(p).equals(SorterInterface.getName(object)))).collect(Collectors.toList());
        
        outputbis.stream().forEach((b) -> {
            output.add(b);
        });
 
    }

    @Override
    public List<? extends Box3D> findObjectsLargerThan(Box3DWithSize object2compare) {
        List<Box3D> out = new ArrayList<>();
        Integer size = object2compare.getSize();
        
        //stream.filter(p -> Arrays.asList(p.getClass().getMethods()).contains())
        
        List<Box3D> temp = stream.collect(Collectors.toList());
        
        
        for(Box3D b : temp)
        {
            Method[] methods = b.getClass().getMethods();
            for(Method m : methods)
            {
                if(m.getName().contains("getSize"))
                {
                    try {
                        Object result = m.invoke(b);
                        if(Integer.valueOf(String.valueOf(result)) > size)
                        {
                            out.add(b);
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(Sorter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            }
            
        }
       
        
        return out;
    }
    
}
