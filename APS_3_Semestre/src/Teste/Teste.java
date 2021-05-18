package Teste;

import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

public class Teste {
    public static void main(String args[]){
        //lerArquivo("../arquivosCSV/alunos.csv");
        
        for(String[] values: stringLer("../arquivosCSV/alunos.csv")){
            System.out.println(values[0]);
            System.out.println(values[1]);
        }
    }
    
    public static void lerArquivo(String filePath){
        
        
        try (   InputStream is = new FileInputStream(filePath); 
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            
            String line;
            int i=0;
            
            while((line = br.readLine()) != null){
                
                System.out.println("linha " + i++);
                
                String[] values = line.split(";");
                
                for(String v: values){
                    System.out.println("Valor: " + v);
                }
                
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }    
        
    }
    
    public static ArrayList<String[]> stringLer(String filePath){
        ArrayList<String[]> matriz = new ArrayList<String[]>();
        
        try (   InputStream is = new FileInputStream(filePath); 
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            
            String line;
            int i=0;
            
            while((line = br.readLine()) != null){
                
                System.out.println("linha " + i++);
                
                String[] values = line.split(";");
                matriz.add(values);
                
                for(String v: values){
                    System.out.println("Valor: " + v);
                }
                
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }    
        
        return matriz;
    }
}
