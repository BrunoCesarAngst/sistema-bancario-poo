import java.util.ArrayList;

public class Conta {

    /*
     * numero
     * agencia
     * titular
     * valorEmConta
     * */

    private long numero;
    private double limite;
    private final double limiteMax;
    private String agencia;
    private ArrayList<Correntista> titular;
    private double valorEmConta;

    Conta(long numero, String agencia, ArrayList<Correntista> titular, double valorEmConta, double limite) {
        this.numero = numero;
        this.agencia = agencia;
        this.titular = titular;
        this.valorEmConta = valorEmConta;
        this.limite = limite;
        this.limiteMax = limite;
    }

    public long getNumero() {
        return numero;
    }

    public void mostrarSaldo() {
        System.out.println("Valor em Conta: " + this.valorEmConta);
    }

    public void deposito(double valor) {
        if (limite < limiteMax) {
             ajustarLimite(valor);
        } else {
            this.valorEmConta += valor;
        }
    }

    private void ajustarLimite(Double deposito) {

        double divida = limiteMax - limite;
        double sobra;

        if (deposito > divida) {
            sobra = deposito - divida;
            limite = limiteMax;
            valorEmConta = sobra;
        } else {
            limite += deposito;
            valorEmConta = limite - limiteMax;
        }

    }

    public void extrato() {
        System.out.println("Valor em conta: " + this.valorEmConta);
        System.out.println("Limite Disponivel: " + this.limite);
        System.out.println("Total (saldo + limite): " + (this.valorEmConta + this.limite));
    }

    public void saque(double valor) {
        if (this.valorEmConta + limite < valor) {
            System.out.println("Valor maior que o saldo e limite");
        } else {
            if (valor > this.valorEmConta) {
                double aux = valorEmConta;
                valorEmConta = valorEmConta - valor;
                limite = (limite + aux) - valor;
            } else {
                this.valorEmConta -= valor;
            }
        }
    }
}