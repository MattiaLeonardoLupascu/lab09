package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the {@link Controller} interface.
 */
public final class SimpleController implements Controller {
    private final List<String> outputList = new ArrayList<>();

    public void setNextString(String out){
        if (out == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        this.outputList.add(out);
    }

    public String getNextString(){
        String temp = this.outputList.getLast();
        return temp;
    }

    public List<String> getHistory(){
        return new ArrayList<>(this.outputList);
    }

    @Override
    public void printOnStandardOutput(){
        if (this.outputList.isEmpty()) {
            throw new IllegalStateException();
        } else {
            IO.println(this.outputList.getLast());
        }
    }
}
