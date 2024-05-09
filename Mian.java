import java.util.ArrayList;
import java.util.Scanner;

abstract class Funcionario {
    protected String nome;
    protected String matricula;

    public Funcionario(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public abstract double calcularSalario();
}

interface Trabalhavel {
    void trabalhar();
    void relatarProgresso();
}

class Gerente extends Funcionario implements Trabalhavel {
    private double bonusAnual;
    private String equipe;

    public Gerente(String nome, String matricula, double bonusAnual, String equipe) {
        super(nome, matricula);
        this.bonusAnual = bonusAnual;
        this.equipe = equipe;
    }

    @Override
    public double calcularSalario() {
        return 5000 + bonusAnual;
    }

    @Override
    public void trabalhar() {
        System.out.println("O gerente está gerenciando a equipe.");
    }

    @Override
    public void relatarProgresso() {
        System.out.println("O gerente está relatando o progresso do projeto.");
    }
}

class Desenvolvedor extends Funcionario implements Trabalhavel {
    private String[] tecnologias;

    public Desenvolvedor(String nome, String matricula, String[] tecnologias) {
        super(nome, matricula);
        this.tecnologias = tecnologias;
    }

    @Override
    public double calcularSalario() {
        return 5000;
    }

    @Override
    public void trabalhar() {
        System.out.println("O desenvolvedor está codificando.");
    }

    @Override
    public void relatarProgresso() {
        System.out.println("O desenvolvedor está relatando o progresso do projeto.");
    }
}

class Estagiario extends Funcionario implements Trabalhavel {
    private int horasTrabalho;
    private String supervisor;

    public Estagiario(String nome, String matricula, int horasTrabalho, String supervisor) {
        super(nome, matricula);
        this.horasTrabalho = horasTrabalho;
        this.supervisor = supervisor;
    }

    @Override
    public double calcularSalario() {
        return 1000;
    }

    @Override
    public void trabalhar() {
        System.out.println("O estagiário está aprendendo e contribuindo com o projeto.");
    }

    @Override
    public void relatarProgresso() {
        System.out.println("O estagiário está relatando seu progresso.");
    }
}

public class SistemaGerenciamentoFuncionarios {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void removerFuncionario(String matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.matricula.equals(matricula)) {
                funcionarios.remove(funcionario);
                System.out.println("Funcionário removido com sucesso.");
                return;
            }
        }
        System.out.println("Funcionário com a matrícula " + matricula + " não encontrado.");
    }

    public void exibirFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
        } else {
            System.out.println("Lista de funcionários:");
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario.nome + " - " + funcionario.matricula + " - Salário: R$" + funcionario.calcularSalario());
            }
        }
    }

    public void buscarFuncionario(String termoBusca) {
        boolean encontrado = false;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.nome.equals(termoBusca) || funcionario.matricula.equals(termoBusca)) {
                System.out.println("Funcionário encontrado:");
                System.out.println(funcionario.nome + " - " + funcionario.matricula + " - Salário: R$" + funcionario.calcularSalario());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum funcionário encontrado com o termo de busca: " + termoBusca);
        }
    }

    public static void main(String[] args) {
        SistemaGerenciamentoFuncionarios sistema = new SistemaGerenciamentoFuncionarios();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n### Menu ###");
            System.out.println("1. Adicionar funcionário");
            System.out.println("2. Remover funcionário");
            System.out.println("3. Exibir todos os funcionários");
            System.out.println("4. Buscar funcionário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do funcionário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a matrícula do funcionário: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Digite o tipo de funcionário (1 - Gerente, 2 - Desenvolvedor, 3 - Estagiário): ");
                    int tipoFuncionario = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (tipoFuncionario) {
                        case 1:
                            System.out.print("Digite o bônus anual do gerente: ");
                            double bonusAnual = scanner.nextDouble();
                            scanner.nextLine(); 
                            System.out.print("Digite a equipe do gerente: ");
                            String equipe = scanner.nextLine();
                            sistema.adicionarFuncionario(new Gerente(nome, matricula, bonusAnual, equipe));
                            break;
                        case 2:
                            System.out.print("Digite as tecnologias que o desenvolvedor domina (separadas por vírgula): ");
                            String tecnologiasString = scanner.nextLine();
                            String[] tecnologias = tecnologiasString;
                            sistema.adicionarFuncionario(new Desenvolvedor(nome, matricula, tecnologias));
                            break;
                        case 3:
                            System.out.print("Digite as horas de trabalho do estagiário: ");
                            int horasTrabalho = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Digite o nome do supervisor do estagiário: ");
                            String supervisor = scanner.nextLine();
                            sistema.adicionarFuncionario(new Estagiario(nome, matricula, horasTrabalho, supervisor));
                            break;
                        default:
                            System.out.println("Tipo de funcionário inválido.");
                    }
                    break;
                case 2:
                    System.out.print("Digite a matrícula do funcionário a ser removido: ");
                    String matriculaRemover = scanner.nextLine();
                    sistema.removerFuncionario(matriculaRemover);
                    break;
                case 3:
                    sistema.exibirFuncionarios();
                    break;
                case 4:
                    System.out.print("Digite o nome ou matrícula do funcionário a ser buscado: ");
                    String termoBusca = scanner.nextLine();
                    sistema.buscarFuncionario(termoBusca);
                    break;
                case 5:
                    executando = false;
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }

        scanner.close();
    }
}