/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package airlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author dm 12.10
 */
class Airlock implements AirlockInterface {

    private State state;
    private List<State> history = new ArrayList<>();
    private Map<State, Integer> usagecounters = new HashMap<>();
    
    /*
    public static void main(String[] args) {
       Airlock al = new Airlock();
       
       al.setState(State.INTERNAL_DOOR_CLOSED);
       State a = al.getState();
       al.closeInternalDoor();
       a = al.getState();
       List c = al.getHistory();
       al.openInternalDoor();
       a = al.getState();
       c = al.getHistory();
       al.closeExternalDoor();
       a = al.getState();
       c = al.getHistory();
       al.openExternalDoor();
       Set d = al.newStates();
       al.openInternalDoor();
       al.closeExternalDoor();
       a = al.getState();
       d = al.newStates();
       c = al.getHistory();
       al.closeInternalDoor();
       a = al.getState();
       c = al.getHistory();
       al.openExternalDoor();
       a = al.getState();
       c = al.getHistory();
       al.openInternalDoor();
       a = al.getState();
       c = al.getHistory();
       Map b = al.getUsageCounters();
       al.setState(State.EXTERNAL_DOOR_OPENED);
       d = al.newStates();
       b = al.getUsageCounters();
       a = al.getState();
       
       
       
    }
    */
    @Override
    public void setState(State state) {
        this.state = state;
        usagecounters.clear();
        usagecounters.put(State.EXTERNAL_DOOR_CLOSED, 0);
        usagecounters.put(State.EXTERNAL_DOOR_OPENED, 0);
        usagecounters.put(State.INTERNAL_DOOR_CLOSED, 0);
        usagecounters.put(State.INTERNAL_DOOR_OPENED, 0);
        usagecounters.put(State.DISASTER, 0);
        usagecounters.replace(state, 1);
        history.clear();
        history.add(state);
        
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Set<State> newStates() {
        if (getState() == State.INTERNAL_DOOR_CLOSED)
        {
            return new HashSet<State>(){{
                add(State.EXTERNAL_DOOR_CLOSED);
                add(State.EXTERNAL_DOOR_OPENED);
                add(State.INTERNAL_DOOR_OPENED);
                
                
            }}; 
        }
        else if (getState() == State.INTERNAL_DOOR_OPENED)
        {
            return new HashSet<State>(){{
                add(State.EXTERNAL_DOOR_CLOSED);
                add(State.INTERNAL_DOOR_CLOSED);
                add(State.DISASTER);
                
            }}; 
        }
        else if (getState() == State.EXTERNAL_DOOR_CLOSED)
        {
            return new HashSet<State>(){{
                add(State.EXTERNAL_DOOR_OPENED);
                add(State.INTERNAL_DOOR_CLOSED);
                add(State.INTERNAL_DOOR_OPENED);
                
                
            }}; 
        }
        else if (getState() == State.EXTERNAL_DOOR_OPENED)
        {
            return new HashSet<State>(){{
                add(State.EXTERNAL_DOOR_CLOSED);
                add(State.INTERNAL_DOOR_CLOSED);
                add(State.DISASTER);
                
            }}; 
        }
        else if (getState() == State.DISASTER)
        {
            return new HashSet<State>(){{
                
           
            }}; 
        }
               
                    
       return null;     
    }

    @Override
    public List<State> getHistory() {
        return history;
    }

    @Override
    public Map<State, Integer> getUsageCounters() {
        return usagecounters;
    }

    public void execute(State s)
    {
        if(getState() == State.DISASTER || getState() == State.EXTERNAL_DOOR_OPENED && s == State.INTERNAL_DOOR_OPENED || getState() == State.INTERNAL_DOOR_OPENED && s == State.EXTERNAL_DOOR_OPENED)
        {
            s = State.DISASTER;
        }
        
        state = s;
        history.add(s);
        Integer currentcount = usagecounters.get(s);
        usagecounters.replace(s, currentcount + 1);
        
        
        
        
    }
    
    @Override
    public void closeInternalDoor() {
        execute(State.INTERNAL_DOOR_CLOSED);
    }

    @Override
    public void closeExternalDoor() {
        execute(State.EXTERNAL_DOOR_CLOSED);
    }

    @Override
    public void openInternalDoor() {
        execute(State.INTERNAL_DOOR_OPENED);
        }

    @Override
    public void openExternalDoor() {
        execute(State.EXTERNAL_DOOR_OPENED);
        }
    
}
