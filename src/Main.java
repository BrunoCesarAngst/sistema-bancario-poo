import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        Conta conta;

        int op = 0;

        do {
            op = menu();

            switch (op) {
                case 1:
                    banco.criarConta();
                    break;
                case 2:
                    System.out.println("Informe o numero da Conta");
                    conta = banco.buscarConta(scanner.nextLong());

                    if (conta != null) {
                        conta.extrato();
                    } else {
                        System.out.println("Conta não existe");
                    }

                    break;
                case 3:
                    System.out.println("Informe o numero da Conta");
                    conta = banco.buscarConta(scanner.nextLong());

                    if (conta != null) {
                        conta.mostrarSaldo();
                    }

                    break;
                case 4:
                    System.out.println("Informe o numero da sua conta");
                    conta = banco.buscarConta(scanner.nextLong());

                    if (conta != null) {
                        System.out.println("Informe o valor do deposito");
                        conta.deposito(scanner.nextDouble());
                        conta.mostrarSaldo();
                    } else {
                        System.out.println("Caixa fora do ar\ntente novamente mais tarde");
                        op = 0;
                    }

                    break;
                case 5:
                    System.out.println("Informe o numero da sua conta");
                    conta = banco.buscarConta(scanner.nextLong());

                    if (conta != null) {
                        System.out.println("Informe o numero da conta do recebedor");
                        Conta contaRecebedor = banco.buscarConta(scanner.nextLong());

                        if (contaRecebedor != null) {
                            System.out.println("Informe o valor a ser transferido");
                            double valor = scanner.nextDouble();
                            contaRecebedor.deposito(valor);
                            conta.mostrarSaldo();
                            contaRecebedor.mostrarSaldo();
                        } else {
                            System.out.println("Esta conta não existe");
                            op = 0;
                        }
                    } else {
                        System.out.println("Caixa fora do ar\ntente novamente mais tarde");
                        op = 0;
                    }

                    break;

                case 6:
                    System.out.println("Informe o numero da Conta");
                    conta = banco.buscarConta(scanner.nextLong());
                    scanner.nextLine();

                    System.out.println("Informe seu cpf");
                    Correntista correntista = banco.buscaCorrentistaPorCpf(scanner.nextLine());

                    if (correntista != null && conta != null) {
                        conta.deposito(correntista.getRenda()*5);
                    } else {
                        //Só diz que deu errado, não informa o erro
                        //pois sistema de caixa é assim mesmo!
                        System.out.println("Algo deu errado");
                    }

                    break;

                case 7:
                    System.out.println("Informe o numero da Conta");
                    conta = banco.buscarConta(scanner.nextLong());

                    if (conta != null) {
                        conta.mostrarSaldo();
                        System.out.println("Quando deseja sacar? ");
                        conta.saque(scanner.nextDouble());
                        conta.mostrarSaldo();
                    } else {
                        System.out.println("Conta não existe");
                    }
                    break;
            }
        } while (op != 0);


    }

    public static int menu() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem vindo ao Banco da Coreia do Norte");
        System.out.println("Selecione uma operação");
        System.out.println("1 Abrir uma conta"); // Feito
        System.out.println("2 Tirar um Extrato"); // Feito
        System.out.println("3 Verificar Saldo"); // Feito
        System.out.println("4 Deposito"); // Feito
        System.out.println("5 Transferencia"); // Feito
        System.out.println("6 Emprestimos"); // Mas na conta conjunta é como? é o do mais velho?
        System.out.println("7 Saque"); // Feito
        System.out.println("0 Sair");

        return scanner.nextInt();
    }
}
