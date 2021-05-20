package Main;
import java.util.Scanner;
/*Victor: Classe Main será responsável por rodar o programa, executando a 
lógica da interface com o usuário*/
public class Main {
    private static String status;
    public static void main(String args[]){
        status = "carregando";
        CSV.loadAll();
        
        status = "executando";
        int escolha;
        do{
            exibeMenu();
            escolha = lerEscolha();
            executaEscolha(escolha);
        }while(escolha!=0);
        
    }
    
    public static String getStatus(){
        return status;
    }
    
    //Função que exibe o menu
    public static void exibeMenu(){
       System.out.println("Escolha uma opção:");
       System.out.println("1 - Listar todos os cursos");
       System.out.println("2 - Listar todos os alunos");
       System.out.println("3 - Listar Histórico de um Aluno");
       System.out.println("4 - Listar o Rendimento de um Curso");
       System.out.println("5 - Incluir novo Aluno");
       System.out.println("6 - Incluir novo Curso");
       System.out.println("7 - Incluir novo Rendimento");
       System.out.println("0 - Sair\n");
    }
    
    //Função que lê a escolha do usuário
    public static int lerEscolha(){
        Scanner scanner = new Scanner(System.in);
        boolean digitarNovamente = true;
        int escolha = -1;
        
        do{
            try{
                System.out.print("Digite o número da opção desejada: ");
                String entrada = scanner.next();
                
                escolha = Integer.parseInt(entrada);
                if(escolha<0||escolha>7){
                    Exception e = new Exception();
                    throw e;
                }
                digitarNovamente = false;
            } catch(Exception e){
               System.out.println("Entrada Inválida, Digite Novamente\n"); 
            }
        }while(digitarNovamente);
        
        return escolha;
    }
    
    //Função que executa os comandos de acordo com a escolha desejada
    public static void executaEscolha(int aEscolha){
        switch(aEscolha){
            case 0:
                CSV.saveAll();
                break;
            case 1:
                listaCursos();
                break;
            case 2:
                listaAlunos();
                break;
            case 3:
                listaHistorico();
                break;
            case 4:
                listaRendimento();
                break;
            case 5:
                incluiAluno();
                break;
            case 6:
                incluiCurso();
                break;
            case 7:
                incluiRendimento();
                break;
            default:
                break;
        }
    }
    
    //Função que lista todos os cursos
    public static void listaCursos(){
        System.out.println(Curso.listaCursos());
    }
    
    //Função que lista todos os aluno
    public static void listaAlunos(){
        System.out.println(Aluno.listaAlunos());
    }
    
    //Função que lista o historico de um aluno
    public static void listaHistorico(){
        Scanner scanner = new Scanner(System.in);
        //Caso haja algum erro a flag irá impedir a execução do programa
        boolean flagErro = false;
        Aluno alunoEscolhido = null;
        
        System.out.println("\nEscolha um Aluno:");
        int i = 0;
        for(Aluno aluno: Aluno.getAlunos()){
            i += 1;
            System.out.println("\nN° de Opção: "+i);
            System.out.println("ID: "+aluno.getId());
            System.out.println("Nome: "+aluno.getNome());
        }
        int numeroAlunos = i;
        if(i==0){
            flagErro = true;
            System.out.println("\nNão existe Aluno Cadastrado, Operação Cancelada");
            System.out.println("Por favor, Cadastre um Aluno antes de realizar essa operação\n");
        }
        if(!flagErro){
            int numAluno = 0;
            boolean digitarNovamente = true;
            
            do{
              try{
                    System.out.print("\nDigite o N° da Opção desejada: ");
                    String entrada = scanner.next();
                
                    numAluno = Integer.parseInt(entrada);
                    if(numAluno<=0||numAluno>numeroAlunos){
                        Exception e = new Exception();
                        throw e;
                    }
                    digitarNovamente = false;
                }catch(Exception e){
                    System.out.println("Entrada Inválida, Digite Novamente");
                }  
            }while(digitarNovamente);
            
            i = 0;
            for(Aluno aluno: Aluno.getAlunos()){
                i += 1;
                if(i==numAluno){
                    alunoEscolhido = aluno;
                }
            }
            
            i = 0;
            for(Rendimento rendimento: Rendimento.getRendimentos()){
                i += 1;
                if(rendimento.getAluno()==alunoEscolhido){
                    System.out.println("\nNome do Curso: "+rendimento.getCurso().getNome());
                    System.out.println("Nível: "+rendimento.getCurso().getNivel());
                    System.out.println("Ano: "+rendimento.getCurso().getAno());
                    System.out.println("Nota NP1: "+rendimento.getNp1().getValor());
                    System.out.println("Nota NP2: "+rendimento.getNp2().getValor());
                    System.out.println("Nota Reposição: "+rendimento.getReposicao().getValor());
                    System.out.println("Nota Exame: "+rendimento.getExame().getValor());
                    System.out.println("Média: "+rendimento.calculaMedia());
                    if(rendimento.aprovado()){
                        System.out.println("Situação: Aprovado\n");
                    } else {
                        System.out.println("Situação: Reprovado\n");
                    }
                }
            }
            if(i==0){
                System.out.println("\nNenhuma Matéria Cadastrada para esse Aluno\n");
            }
        }
        
    }
    
    //Função que lista o rendimento de um curso
    public static void listaRendimento(){
        Scanner scanner = new Scanner(System.in);
        //Caso haja algum erro a flag irá impedir a execução do programa
        boolean flagErro = false;
        Curso cursoEscolhido = null;
        
        System.out.println("\nEscolha um Curso:");
        int i = 0;
        for(Curso curso: Curso.getCursos()){
            i += 1;
            System.out.println("\nN° de Opção: "+i);
            System.out.println("Nome: "+curso.getNome());
            System.out.println("Nível: "+curso.getNivel());
            System.out.println("Ano: "+curso.getAno());
        }
        int numeroCursos = i;
        if(i==0){
            flagErro = true;
            System.out.println("\nNão existe Curso Cadastrado, Operação Cancelada");
            System.out.println("Por favor, Cadastre um Curso antes de realizar essa operação\n");
        }
        if(!flagErro){
            boolean digitarNovamente = true;
            int numCurso = 0;
            
            do{
                try{
                    System.out.print("\nDigite o N° da Opção desejada: ");
                    String entrada = scanner.next();
                    numCurso = Integer.parseInt(entrada);
                    
                    if(numCurso<=0||numCurso>numeroCursos){
                        Exception e = new Exception();
                        throw e;
                    }
                    digitarNovamente = false;
                }catch(Exception e){
                    System.out.println("Entrada Inválida, Digite Novamente");
                }
            }while(digitarNovamente);
            
            
            i = 0;
            for(Curso curso: Curso.getCursos()){
                i += 1;
                if(i==numCurso){
                    cursoEscolhido = curso;
                }
            }
            
            i = 0;
            for(Rendimento rendimento: Rendimento.getRendimentos()){
                i += 1;
                if(rendimento.getCurso()==cursoEscolhido){
                    System.out.println("\nID: "+rendimento.getAluno().getId());
                    System.out.println("Aluno: "+rendimento.getAluno().getNome());
                    System.out.println("Nota NP1: "+rendimento.getNp1().getValor());
                    System.out.println("Nota NP2: "+rendimento.getNp2().getValor());
                    System.out.println("Nota Reposição: "+rendimento.getReposicao().getValor());
                    System.out.println("Nota Exame: "+rendimento.getExame().getValor());
                    System.out.println("Média: "+rendimento.calculaMedia());
                    if(rendimento.aprovado()){
                        System.out.println("Situação: Aprovado\n");
                    } else {
                        System.out.println("Situação: Reprovado\n");
                    }
                }
            }
            if(i==0){
                System.out.println("\nNenhuma Aluno Cadastrado para essa Matéria\n");
            }
        }
    }
    
    //Função que inclui novo aluno
    public static void incluiAluno(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o id do aluno: ");
        String id = scanner.next();
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.next();
        
        Aluno aluno = new Aluno(id,nome);
    }
    
    //Função que inclui novo curso
    public static void incluiCurso(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o nome do curso: ");
        String nome = scanner.next();
        System.out.println("Escolha o nível do curso: ");
        System.out.println("1 - GRADUACAO");
        System.out.println("2 - POS_GRADUACAO");
        
        int numNivel = 0;
        boolean digitarNovamente = true;
        do{
            try{
                System.out.print("\nDigite o N° da Opção desejada: ");
                String entrada = scanner.next();
                numNivel = Integer.parseInt(entrada);

                if(numNivel<=0||numNivel>2){
                    Exception e = new Exception();
                    throw e;
                }
                digitarNovamente = false;
            }catch(Exception e){
                System.out.println("Entrada Inválida, Digite Novamente");
            }
        }while(digitarNovamente);
        
        
        int ano = 0;
        digitarNovamente = true;
        do{
            try{
                System.out.print("\nDigite o Ano do curso: ");
                String entrada = scanner.next();
                ano = Integer.parseInt(entrada);

                if(ano<1900||ano>2100){
                    System.out.println("Anos abaixo de 1900 ou acima de 2100 são inválidos");
                    Exception e = new Exception();
                    throw e;
                }
                digitarNovamente = false;
            }catch(Exception e){
                System.out.println("Entrada Inválida, Digite Novamente");
            }
        }while(digitarNovamente);
        
        String nivel;
        switch(numNivel){
            case 1:
                nivel = "GRADUACAO";
                break;
            case 2:
                nivel = "POS_GRADUACAO";
                break;
            default:
                nivel = "GRADUACAO";
                break;
        }
        
        Curso curso = new Curso(nome,nivel,ano);
    }
    
    //Função que inclui novo Rendimento
    public static void incluiRendimento(){
        //Flag que para a execução da inclusão de rendimento se houver algum erro
        boolean flagErro = false;
        Aluno alunoEscolhido = null;
        Curso cursoEscolhido = null;
        Nota np1, np2, reposicao, exame;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Escolha um Aluno: \n");
        int i = 0;
        for(Aluno aluno: Aluno.getAlunos()){
            i += 1;
            System.out.println("N° da Opção: "+(i));
            System.out.println("ID: "+aluno.getId());
            System.out.println("Nome: "+aluno.getNome()+"\n");
        }
        int numeroAlunos = i;
        if(i==0){
            flagErro = true;
            System.out.println("Nenhum Aluno Cadastrado, Operação Cancelada");
            System.out.println("Por favor, Cadastre um Aluno antes de executar essa operação novamente\n");
        }
        
        if(!flagErro){
            int numEscolha = 0;
            boolean digitarNovamente = true;
            do{
              try{
                    System.out.print("\nDigite o N° de Opção do Aluno desejado: ");
                    String entrada = scanner.next();
                
                    numEscolha = Integer.parseInt(entrada);
                    if(numEscolha<=0||numEscolha>numeroAlunos){
                        Exception e = new Exception();
                        throw e;
                    }
                    digitarNovamente = false;
                }catch(Exception e){
                    System.out.println("Entrada Inválida, Digite Novamente");
                }  
            }while(digitarNovamente);
            
            i = 0;
            for(Aluno aluno : Aluno.getAlunos()){
                i += 1;
                if(i==numEscolha){
                    alunoEscolhido = aluno;
                }
            }
        }
        
        int numeroCursos = 0;
        if(!flagErro){
            System.out.println("\nEscolha um Curso:\n");
            i = 0;
            for(Curso curso : Curso.getCursos()){
                i += 1;
                System.out.println("N° da Opção: "+i);
                System.out.println("Nome: "+curso.getNome());
                System.out.println("Nível: "+curso.getNivel());
                System.out.println("Ano: "+curso.getAno()+"\n");
            }
            numeroCursos = i;
            if(i==0){
                flagErro = true;
                System.out.println("Nenhum Curso Cadastrado, Operação Cancelada");
                System.out.println("Por favor, Cadastre um Curso antes de executar essa operação novamente\n");
            }
        }
        if(!flagErro){
            int numEscolha = 0;
            boolean digitarNovamente = true;
            do{
                try{
                    System.out.print("Digite o N° de Opção do Curso desejado: ");
                    String entrada = scanner.next();
                    numEscolha = Integer.parseInt(entrada);
                    
                    if(numEscolha<=0||numEscolha>numeroCursos){
                        Exception e = new Exception();
                        throw e;
                    }
                    digitarNovamente = false;
                }catch(Exception e){
                    System.out.println("Entrada Inválida, Digite Novamente");
                }
            }while(digitarNovamente);
            
            i = 0;
            for(Curso curso : Curso.getCursos()){
                i += 1;
                if(i==numEscolha){
                    cursoEscolhido = curso;
                }
            }
        }
        if(!flagErro){
            double notaNP1 = lerNota("Digite a Nota NP1: ");
            double notaNP2 = lerNota("Digite a Nota NP2: ");
            double notaReposicao = lerNota("Digite a Nota da Reposicao: ");
            double notaExame = lerNota("Digite a Nota do Exame: ");
            
            np1 = new Nota(notaNP1);
            np2 = new Nota(notaNP2);
            reposicao = new Nota(notaReposicao);
            exame = new Nota(notaExame);
            
            Rendimento rendimento = new Rendimento(alunoEscolhido, cursoEscolhido,
            np1,np2,reposicao,exame);
            System.out.println("\nRendimento Cadastrado com Sucesso!!!\n");
        }
        
    }
    
    public static double lerNota(String mensagem){
        Scanner scanner = new Scanner(System.in);
        double nota = -1;
        
        boolean digitarNovamente = true;
        do{
            try{
                System.out.print(mensagem);
                String entrada = scanner.next();
                
                nota = Double.parseDouble(entrada);
                if(nota<0||nota>10){
                    System.out.println("Nota não pode ser Negativa ou Maior que 10");
                    Exception e = new Exception();
                    throw e;
                }
                digitarNovamente = false;
            }catch(Exception e){
                System.out.println("Entrada Inválida, Digite Novamente");
            }
        }while(digitarNovamente);
        
        return nota;
    }
}
