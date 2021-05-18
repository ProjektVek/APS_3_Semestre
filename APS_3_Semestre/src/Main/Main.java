package Main;
import java.util.Scanner;
/*Victor: Classe Main será responsável por rodar o programa, executando a 
lógica da interface com o usuário*/
public class Main {
    
    public static void main(String args[]){
        CSV.loadAll();
        
        int escolha;
        do{
            exibeMenu();
            escolha = lerEscolha();
            executaEscolha(escolha);
        }while(escolha!=0);
        
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
        
        System.out.print("Digite o número da opção desejada: ");
        int escolha = scanner.nextInt();
        System.out.println();
        return escolha;
    }
    
    //Função que executa os comandos de acordo com a escolha desejada
    public static void executaEscolha(int aEscolha){
        switch(aEscolha){
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
        if(i==0){
            flagErro = true;
            System.out.println("\nNão existe Aluno Cadastrado, Operação Cancelada");
            System.out.println("Por favor, Cadastre um Aluno antes de realizar essa operação\n");
        }
        if(!flagErro){
            System.out.print("\nDigite o N° da Opção desejada: ");
            int numAluno = scanner.nextInt();
            
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
        if(i==0){
            flagErro = true;
            System.out.println("\nNão existe Curso Cadastrado, Operação Cancelada");
            System.out.println("Por favor, Cadastre um Curso antes de realizar essa operação\n");
        }
        if(!flagErro){
            System.out.print("\nDigite o N° da Opção desejada: ");
            int numCurso = scanner.nextInt();
            
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
                    System.out.println("\nAluno: "+rendimento.getAluno().getNome());
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
        System.out.println("Aluno Cadastrado com Sucesso!!!\n");
    }
    
    //Função que inclui novo curso
    public static void incluiCurso(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o nome do curso: ");
        String nome = scanner.next();
        System.out.println("Escolha o nível do curso: ");
        System.out.println("1 - GRADUACAO");
        System.out.println("2 - POS_GRADUACAO");
        System.out.print("Digite o número da opção desejada: ");
        int numNivel = scanner.nextInt();
        System.out.print("Digite o Ano do curso: ");
        int ano = scanner.nextInt();
        
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
        System.out.println("\nCurso Adicionado com Sucesso!!!\n");
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
        if(i==0){
            flagErro = true;
            System.out.println("Nenhum Aluno Cadastrado, Operação Cancelada");
            System.out.println("Por favor, Cadastre um Aluno antes de executar essa operação novamente\n");
        }
        
        if(!flagErro){
            System.out.print("Digite o N° de Opção do Aluno desejado: ");
            int numEscolha = scanner.nextInt();
            i = 0;
            for(Aluno aluno : Aluno.getAlunos()){
                i += 1;
                if(i==numEscolha){
                    alunoEscolhido = aluno;
                }
            }
        }
        
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
            if(i==0){
                flagErro = true;
                System.out.println("Nenhum Curso Cadastrado, Operação Cancelada");
                System.out.println("Por favor, Cadastre um Curso antes de executar essa operação novamente\n");
            }
        }
        if(!flagErro){
            System.out.print("Digite o N° de Opção do Curso desejado: ");
            int numEscolha = scanner.nextInt();
            i = 0;
            for(Curso curso : Curso.getCursos()){
                i += 1;
                if(i==numEscolha){
                    cursoEscolhido = curso;
                }
            }
        }
        if(!flagErro){
            System.out.print("Digite a Nota NP1: ");
            double notaNP1 = scanner.nextDouble();
            System.out.print("Digite a Nota NP2: ");
            double notaNP2 = scanner.nextDouble();
            System.out.print("Digite a Nota da Reposicao: ");
            double notaReposicao = scanner.nextDouble();
            System.out.print("Digite a Nota do Exame: ");
            double notaExame = scanner.nextDouble();
            
            np1 = new Nota(notaNP1);
            np2 = new Nota(notaNP2);
            reposicao = new Nota(notaReposicao);
            exame = new Nota(notaExame);
            
            Rendimento rendimento = new Rendimento(alunoEscolhido, cursoEscolhido,
            np1,np2,reposicao,exame);
            System.out.println("\nRendimento Cadastrado com Sucesso!!!\n");
        }
        
    }
}
