package Main;
/*Victor: Classe Nota responsável simplesmente por armazenar o valor 
de uma nota */

import java.text.DecimalFormat;

public class Nota {
    
    //Declaração de atributos
    private double valor;
    
    //Constructor
    public Nota(double aNota){
        DecimalFormat doisDigitos = new DecimalFormat();
        doisDigitos.setMaximumFractionDigits(2);
        this.valor = Double.parseDouble(doisDigitos.format(aNota));
    }
    
    //Getters e Setters
    public double getValor() {
        return valor;
    }

    public void setValor(double aValor) {
        this.valor = aValor;
    }
}
