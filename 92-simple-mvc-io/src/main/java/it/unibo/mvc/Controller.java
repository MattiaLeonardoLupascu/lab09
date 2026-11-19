package it.unibo.mvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    
    private File currentFile = new File(System.getProperty("user.home")
        + File.separator + "output.txt");
    
    public void setCurrentFile(final String file){
        File tempFile = new File(file);
        this.currentFile = tempFile;
    }
    
    public File getCurrentFile(){
        File temp = this.currentFile;
        return temp;
    }

    public void writeOnFile(final String input){
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.currentFile)))){
            out.write(input);
        } catch (IOException e) {
            IO.println("Errore:" + e.getMessage());
        }
    }

}
