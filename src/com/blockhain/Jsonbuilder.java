package com.blockhain;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Jsonbuilder {
    protected ArrayList<Block> data;
    public Jsonbuilder(ArrayList<Block> data){
        this.data = data;
    }

    //target
    
    public void write(String path){
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("[" + "\n");
            int k = 1;
            for(Block i: this.data){
                if(k < this.data.size()){
                    sb.append("\t" + i + ",");
                }else{
                    sb.append("\t" + i);
                }
                sb.append("\n");
                k += 1; 
            }
            sb.append("]");
            String output = sb.toString();
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(output);
            
            // Always close the writer to free resources
            bufferedWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
