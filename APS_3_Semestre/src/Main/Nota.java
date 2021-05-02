package Main;
/*Victor: Classe Nota responsável simplesmente por armazenar o valor 
de uma nota */
public class Nota {
    
    //Declaração de atributos
    private double valor;
    
    //Constructor
    public Nota(double aNota){
        this.valor = aNota;
    }
    
    //Getters e Setters
    public double getValor() {
        return valor;
    }

    public void setValor(double aValor) {
        this.valor = aValor;
    }
}
