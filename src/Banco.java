import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    ArrayList<Conta> contas = new ArrayList<>();
    ArrayList<Correntista> correntistas = new ArrayList<>();

    public void criarConta() {
        Scanner scanner = new Scanner(System.in);
        String resp;

        do {

            System.out.println("Já é cadastrado? (s/n)");
            resp = scanner.nextLine();

            if (resp.toLowerCase().equals("s")) {
                //busca correntista por cpf e cadastra a conta
                System.out.println("Informe o cpf");
                String cpf = scanner.next();

                Correntista correntistaCadastrado = buscaCorrentistaPorCpf(cpf);

                if (correntistaCadastrado != null) {
                    cadastrarConta(correntistaCadastrado);
                } else {
                    System.out.println("cpf incorreto ou não cadastrado");
                    resp = "-1";
                }

            } else if (resp.toLowerCase().equals("n")) {
                //cadastra correntista e depois cadastra a conta
                Correntista novoCorrentista = cadastrarCorrentista();
                if (novoCorrentista != null) {
                    cadastrarConta(novoCorrentista);
                } else {
                    resp = "-1";
                }

            } else {
                System.out.println("operacao invalida");
            }

        } while (!resp.equals("s") && !resp.equals("n"));

    }

    private void cadastrarConta(Correntista correntista) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Correntista> titulares = new ArrayList<>();
        titulares.add(correntista);
        String resp;
        int maisvelho = 0;

        System.out.println("Valor de abertura de conta: ");
        double valorAbertura = scanner.nextDouble();

        System.out.println("Agencia: ");
        String agencia = scanner.next();

        System.out.println("Numero da conta");
        long numero = scanner.nextLong();

        if (buscarConta(numero) != null) {
            System.out.println("Esta conta já existe");
        } else {

            do {
                System.out.println("Deseja torna essa conta uma conta conjunta? (s/n)");
                resp = scanner.next();

                if (resp.toLowerCase().equals("s")) {
                    titulares.addAll(adicionarOutroCorrentista());
                }

            } while (resp.toLowerCase().equals("s"));

            for (Correntista titular : titulares) {
                if (titular.getIdade() > maisvelho) {
                    maisvelho = titular.getIdade();
                }
            }

            Conta conta = new Conta(numero, agencia, titulares, valorAbertura, atribuirLimite(maisvelho));
            contas.add(conta);
        }
    }

    public Conta buscarConta(long numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                System.out.println("ACHOU");
                return conta;
            }
        }
        System.out.println("nao achou");
        return null;
    }

    private double atribuirLimite(int idade) {
        if (idade >= 60) {
            return 1000;
        } else if (idade < 18) {
            return 100;
        } else {
            return 300;
        }
    }

    private ArrayList<Correntista> adicionarOutroCorrentista() {
        Scanner scanner = new Scanner(System.in);
        String resp;
        ArrayList<Correntista> correntistas = new ArrayList<>();

        do {

            System.out.println("Correntista já é cliente? (s/n)");
            resp = scanner.next().toLowerCase();
            Correntista correntista;

            if (resp.equals("s")) {
                System.out.println("Informe o CPF: ");
                String cpf = scanner.next();
                correntista = buscaCorrentistaPorCpf(cpf);

                if (correntista != null) {
                    correntistas.add(correntista);
                }

            } else if (resp.equals("n")) {
                correntista = cadastrarCorrentista();

                if (correntista != null) {
                    correntistas.add(correntista);
                }
            }

        } while (!resp.equals("s") && !resp.equals("n"));

        return correntistas;
    }


    // cadastrar correntista
    public Correntista cadastrarCorrentista() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe os dados");
        System.out.println("cpf: ");
        String cpf = scanner.nextLine();
        // Validar CPF (se é valido e se ja esta cadastrado)

        Correntista correntistaCadastrado = buscaCorrentistaPorCpf(cpf);
        if (correntistaCadastrado != null) {
            System.out.println("Este CPF já esta cadastrado");
            return null;
        }

        System.out.println("nome: ");
        String nome = scanner.nextLine();

        System.out.println("idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("data de nascimento: ");
        String dataNascimento = scanner.nextLine();

        System.out.println("renda: ");
        double renda = scanner.nextDouble();

        Correntista correntista = new Correntista(nome, idade, cpf, dataNascimento, renda);

        correntistas.add(correntista);
        return correntista;
    }

    public Correntista buscaCorrentistaPorCpf(String cpf) {
        for (Correntista correntista : correntistas) {
            if (correntista.getCpf().equals(cpf)) {
                System.out.println("ACHOU");
                return correntista;
            }
        }
        System.out.println("nao achou");
        return null;
    }

}
