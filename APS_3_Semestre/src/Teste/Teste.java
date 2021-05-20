package Teste;

import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.util.Scanner;
public class Teste {
    public static void main(String args[]){
        testaTry();

        //lerArquivo("../arquivosCSV/alunos.csv");
        /*
        for(String[] values: stringLer("../arquivosCSV/alunos.csv")){
            System.out.println(values[0]);
            System.out.println(values[1]);
        }
        
        saveArquivo("../testeCSV/save.csv");
        */
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
    
    public static void saveArquivo(String filePath){
        try(    OutputStream os = new FileOutputStream(filePath/*, true*/);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            
            for(int i = 0;i<5;i++){
                pw.print("\n"+i+";"+(i*2)+";"+(i*3));
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        System.out.println("Arquivos salvos com sucesso!");
    }
    
    public static void testaTry(){
        boolean erro = true;
        do{
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.print("Digite um número: ");
                int num = scanner.nextInt();
                
                if(num>=0){
                System.out.println("Número: "+num);
                erro = false; 
                } else {
                    IOException e = new IOException();
                    throw e;
                }
                
            } catch(Exception e){
                System.out.println("Entrada Inválida, Digite Novamente");
            }
        }while(erro);
    }
}
