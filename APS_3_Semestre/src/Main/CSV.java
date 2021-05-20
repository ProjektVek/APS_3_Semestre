package Main;

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

public class CSV {
    
    /*Classe Responsável por ler o CSV e devolver um ArrayList com os valores armazenados
    em um vetor de String */
    public static ArrayList<String[]> lerCSV(String localizacao){
        ArrayList<String[]> valores = new ArrayList<String[]>();
        
        try (   InputStream is = new FileInputStream(localizacao); 
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            
            String line;
            
            while((line = br.readLine()) != null){
                
                valores.add(line.split(";"));
                
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }    
        
        return valores;
    }
    
    //Método que executa todos os carregamentos de dados:
    public static void loadAll(){
        loadAlunos();
        loadCursos();
        loadRendimentos();
    }
    
    //Método que lê o arquivo de alunos e carrega os dados no programa
    public static void loadAlunos(){
        String localizacao = "../arquivosCSV/alunos.csv";
        ArrayList<String[]> dados = lerCSV(localizacao);
        
        for(String[] valores : dados){
            String id = valores[0];
            String nome = valores[1];
            
            Aluno aluno = new Aluno(id,nome);
        }
        
        System.out.println("Alunos Carregados com Sucesso!!!\n");
    }
    
    //Método que lê o arquivo de cursos e carrega os dados no programa
    public static void loadCursos(){
        String localizacao = "../arquivosCSV/cursos.csv";
        ArrayList<String[]> dados = lerCSV(localizacao);
        
        for(String[] valores : dados){
            String nome = valores[0];
            String nivel = valores[1];
            int ano = Integer.parseInt(valores[2]);
            
            Curso curso = new Curso(nome,nivel,ano);
        }
        
        System.out.println("Cursos Carregados com Sucesso!!!\n");
    }
    
    //Método que carrega os todos os rendimentos de cursos cadastrados
    public static void loadRendimentos(){
        
        for(Curso curso : Curso.getCursos()){
            String nome = curso.getNome();
            String nivel = curso.getNivel();
            int ano = curso.getAno();
            
            String localizacao = "../arquivosCSV/" + nome + "_" + nivel + "_" + ano;
            localizacao += ".csv";
            
            ArrayList<String[]> dados = lerCSV(localizacao);
            for(String[] valores: dados){
                Aluno alunoCSV = null;
                for(Aluno aluno: Aluno.getAlunos()){
                    
                    /*Verifica se não há diferenca entre os strings, se não houver
                    diferenca o algoritmo irá armazenar qual é o aluno*/
                    String idAluno = ""+aluno.getId();
                    String valorId = ""+valores[0];
                    
                    String diferenca = valorId.replace(idAluno,"");
                    if(diferenca==""){
                        alunoCSV = aluno;
                    } else {
                    }
                }
                Nota np1 = new Nota(Double.parseDouble(valores[1]));
                Nota np2 = new Nota(Double.parseDouble(valores[2]));
                Nota reposicao = new Nota(Double.parseDouble(valores[3]));
                Nota exame = new Nota(Double.parseDouble(valores[4]));
                
                Rendimento rendimento = new Rendimento(alunoCSV,curso,np1,np2,reposicao,exame);
            }
        }
        
        System.out.println("Rendimentos Carregados com Sucesso!!!\n");
        
    }
    
    //Método responsável por salvar uma String em um arquivo CSV
    public static void saveStringCSV(String localizacao, String texto){
        try(    OutputStream os = new FileOutputStream(localizacao);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            
            pw.print(texto);
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //Método que salva os alunos no arquivo alunos.csv
    public static void saveAlunos(){
        String texto = "";
        System.out.println("\nSalvando Alunos...");
        
        int i = 0;
        for(Aluno aluno: Aluno.getAlunos()){
            if(i!=0){
                texto += "\n";
            }
            texto += aluno.getId()+";"+aluno.getNome();
            i++;
        }
        
        String localizacao = "../arquivosCSV/alunos.csv";
        saveStringCSV(localizacao,texto);
        System.out.println("Alunos Salvos Com Sucesso!!!");
    }
    
    //Método que salva os cursos no arquivo cursos.csv
    public static void saveCursos(){
        String texto = "";
        System.out.println("\nSalvando Cursos...");
        
        int i = 0;
        for(Curso curso: Curso.getCursos()){
            if(i!=0){
                texto += "\n";
            }
            texto += curso.getNome()+";"+curso.getNivel()+";"+curso.getAno();
            i++;
        }
        
        String localizacao = "../arquivosCSV/cursos.csv";
        saveStringCSV(localizacao,texto);
        System.out.println("Cursos Salvos Com Sucesso!!!");
    }
    
    //Método que salva os rendimentos em seus respectivos arquivos
    public static void saveRendimentos(){
        System.out.println("\nSalvando Rendimentos");
        for(Curso curso : Curso.getCursos()){
           String texto = "";
           int i = 0;
           for(Rendimento rendimento : Rendimento.getRendimentos()){
               if(curso==rendimento.getCurso()){
                   if(i!=0){
                       texto += "\n";
                   }
                   texto += rendimento.getAluno().getId()+";";
                   texto += rendimento.getNp1().getValor()+";";
                   texto += rendimento.getNp2().getValor()+";";
                   texto += rendimento.getReposicao().getValor()+";";
                   texto += rendimento.getExame().getValor();
                   i++;
               }
           }
           
          String localizacao = "../arquivosCSV/"+curso.getNome()+"_"+curso.getNivel()+"_"+curso.getAno()+".csv";
          saveStringCSV(localizacao,texto);
        }
        
        System.out.println("Rendimentos Salvos Com Sucesso!!!");
    }
    
    //Método que executa todas as operações de salvamento
    public static void saveAll(){
        saveAlunos();
        saveCursos();
        saveRendimentos();
    }
        
}
