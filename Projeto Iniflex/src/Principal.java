import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
            new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários com todas suas informações.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario funcionario : funcionarios) {
            String dataFormatada = funcionario.getDataNascimento().format(formatter);
            String salarioFormatado = String.format("%,.2f", funcionario.getSalario()).replace('.', ',');
            System.out.println(funcionario.getNome() + " - " + dataFormatada + " - " + salarioFormatado + " - " + funcionario.getFuncao());
        }

        // 3.4 – Atualizar a lista de funcionários com novo valor do salário.
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.valueOf(1.10));
            funcionario.setSalario(novoSalario);
        }

        // 3.5 – Agrupar os funcionários por função em um MAP.
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            funcionariosPorFuncao.computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>()).add(funcionario);
        }

        // 3.6 – Imprimir os funcionários, agrupados por função.
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : funcionariosPorFuncao.get(funcao)) {
                System.out.println(funcionario.getNome());
            }
        }

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println(funcionario.getNome() + " - " + funcionario.getDataNascimento().format(formatter));
            }
        }

        // 3.9 – Imprimir o funcionário com a maior idade.
        Funcionario funcionarioMaisVelho = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
                funcionarioMaisVelho = funcionario;
            }
        }
        int idade = LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
        System.out.println("Funcionário mais velho: " + funcionarioMaisVelho.getNome() + " - Idade: " + idade);

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }

        // 3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("Total dos salários: " + String.format("%,.2f", totalSalarios).replace('.', ','));

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos");
        }
    }
}
