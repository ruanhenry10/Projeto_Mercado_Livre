

import java.util.Scanner;

public class ValidadorUtil {

    // Validar email
    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Validação simples de email
        return email.contains("@") && email.contains(".");
    }

    // Validar senha (mínimo 6 caracteres)
    public static boolean validarSenha(String senha) {
        return senha != null && senha.length() >= 6;
    }

    // Validar valor monetário (positivo)
    public static boolean validarValor(double valor) {
        return valor > 0;
    }

    // Validar quantidade (positivo)
    public static boolean validarQuantidade(int quantidade) {
        return quantidade > 0;
    }

    // Validar string não vazia
    public static boolean validarTextoNaoVazio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    // Solicitar texto com validação
    public static String solicitarTexto(Scanner sc, String mensagem) {
        String texto;
        do {
            System.out.print(mensagem);
            texto = sc.nextLine().trim();
            if (!validarTextoNaoVazio(texto)) {
                MenuUtil.exibirErro("Campo obrigatório!");
            }
        } while (!validarTextoNaoVazio(texto));
        return texto;
    }

    // Solicitar email com validação
    public static String solicitarEmail(Scanner sc) {
        String email;
        do {
            System.out.print("❯ Email: ");
            email = sc.nextLine().trim();
            if (!validarEmail(email)) {
                MenuUtil.exibirErro("Email inválido! Use o formato: email@exemplo.com");
            }
        } while (!validarEmail(email));
        return email;
    }

    // Solicitar senha com validação
    public static String solicitarSenha(Scanner sc, String mensagem) {
        String senha;
        do {
            System.out.print(mensagem);
            senha = sc.nextLine().trim();
            if (!validarSenha(senha)) {
                MenuUtil.exibirErro("Senha deve ter no mínimo 6 caracteres!");
            }
        } while (!validarSenha(senha));
        return senha;
    }

    // Solicitar valor monetário com validação
    public static double solicitarValor(Scanner sc, String mensagem) {
        double valor;
        while (true) {
            try {
                System.out.print(mensagem);
                valor = Double.parseDouble(sc.nextLine().trim());
                if (!validarValor(valor)) {
                    MenuUtil.exibirErro("Valor deve ser maior que zero!");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                MenuUtil.exibirErro("Valor inválido! Digite um número.");
            }
        }
    }

    // Solicitar quantidade com validação
    public static int solicitarQuantidade(Scanner sc, String mensagem) {
        int quantidade;
        while (true) {
            try {
                System.out.print(mensagem);
                quantidade = Integer.parseInt(sc.nextLine().trim());
                if (!validarQuantidade(quantidade)) {
                    MenuUtil.exibirErro("Quantidade deve ser maior que zero!");
                } else {
                    return quantidade;
                }
            } catch (NumberFormatException e) {
                MenuUtil.exibirErro("Quantidade inválida! Digite um número inteiro.");
            }
        }
    }

    // Solicitar número inteiro com validação
    public static int solicitarInteiro(Scanner sc, String mensagem) {
        int numero;
        while (true) {
            try {
                System.out.print(mensagem);
                numero = Integer.parseInt(sc.nextLine().trim());
                return numero;
            } catch (NumberFormatException e) {
                MenuUtil.exibirErro("Valor inválido! Digite um número inteiro.");
            }
        }
    }
}
