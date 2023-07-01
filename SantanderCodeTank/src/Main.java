import java.text.DecimalFormat;
import java.util.Scanner;

/*By Hebert Barbosa*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        Double[] grossSalaries = new Double[5];
        String[] employersName = new String[5];

        System.out.println(
                "Este programa tem como objetivo calcular o salário líquido de 5 funcionários a partir de seus salários brutos. \n" +
                        "Ele realiza os cálculos dos descontos de imposto de renda e INSS, fornecendo como resultado o valor total do salário líquido referente ao mês em questão. \n"
        );

        for (int i = 0; i < grossSalaries.length; i++) {
            System.out.println("Informe o nome do " + (i + 1) + "º funcionário:");
            input = sc.next();
            employersName[i] = input;

            boolean isValid = false;
            while (!isValid) {
                System.out.println("Informe o salário do(a) " + employersName[i] + ":");
                input = sc.next();

                if (isValidSalary(input)) {
                    grossSalaries[i] = Double.parseDouble(input.replace(',', '.'));
                    isValid = true;
                } else {
                    System.out.println("Salário inválido! Digite nesse formato do exemplo: '1000.00'");
                }
            }
        }

        for (int i = 0; i < grossSalaries.length; i++) {
            System.out.println("Informações do " + (i + 1) + "º funcionário:");
            System.out.println("Nome: " + employersName[i]);

            Double grossSalary = grossSalaries[i];
            System.out.println("Salário bruto: R$ " + formatNetSalary(grossSalary));

            Double InssDeduction = calculateINSSDeduction(grossSalary);
            System.out.println("Valor do desconto do INSS: R$ " + formatNetSalary(InssDeduction));

            Double incomeTax = calculateIncomeTaxDeduction(grossSalary);
            System.out.println("Valor do desconto do imposto de Renda: R$ " + formatNetSalary(incomeTax));

            Double netSalary = (grossSalary - InssDeduction) - incomeTax;
            System.out.println("Salário líquido: R$ " + formatNetSalary(netSalary));

            System.out.println("----------------------------------- ");
        }
    }

    private static double calculateINSSDeduction(Double grossSalary) {
        if (grossSalary > 3641.04) {
            return grossSalary * 0.14;
        } else if (grossSalary <= 3641.03 && grossSalary >= 2427.36) {
            return grossSalary * 0.12;
        } else if (grossSalary <= 2427.35 && grossSalary >= 1212.01) {
            return grossSalary * 0.09;
        } else {
            return grossSalary * 0.075;
        }
    }

    private static double calculateIncomeTaxDeduction(Double grossSalary) {
        if (grossSalary > 4664.68) {
            return grossSalary * 0.275;
        } else if (grossSalary <= 4664.68 && grossSalary >= 3751.06) {
            return grossSalary * 0.225;
        } else if (grossSalary <= 3751.05 && grossSalary >= 2826.66) {
            return grossSalary * 0.15;
        } else if (grossSalary <= 2826.65 && grossSalary > 1903.99) {
            return grossSalary * 0.075;
        } else {
            return grossSalary * 0;
        }
    }

    private static boolean isValidSalary(String salary) {
        String regex = "\\d+(\\.\\d+)?";
        return salary.matches(regex);
    }

    private static String formatNetSalary(double netSalary) {
        DecimalFormat netSalaryFormat = new DecimalFormat("0.00");
        return netSalaryFormat.format(netSalary);
    }
}