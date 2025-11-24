

import java.util.Scanner;

public class MenuUtil {
    
    // Limpar console (simulado com linhas em branco)
    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Exibir linha separadora
    public static void exibirLinha() {
        System.out.println("=".repeat(60));
    }

    public static void exibirLinhaSimples() {
        System.out.println("-".repeat(60));
    }

    // Exibir título formatado
    public static void exibirTitulo(String titulo) {
        System.out.println();
        exibirLinha();
        System.out.println(centralizarTexto(titulo, 60));
        exibirLinha();
    }

    // Exibir subtítulo
    public static void exibirSubtitulo(String subtitulo) {
        System.out.println();
        exibirLinhaSimples();
        System.out.println(subtitulo);
        exibirLinhaSimples();
    }

    // Centralizar texto
    private static String centralizarTexto(String texto, int largura) {
        int espacos = (largura - texto.length()) / 2;
        return " ".repeat(Math.max(0, espacos)) + texto;
    }

    // Pausar e aguardar Enter
    public static void pausar(Scanner sc) {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    // Exibir mensagem de sucesso
    public static void exibirSucesso(String mensagem) {
        System.out.println("\n✓ " + mensagem);
    }

    // Exibir mensagem de erro
    public static void exibirErro(String mensagem) {
        System.out.println("\n✗ ERRO: " + mensagem);
    }

    // Exibir mensagem de aviso
    public static void exibirAviso(String mensagem) {
        System.out.println("\n⚠ AVISO: " + mensagem);
    }

    // Exibir mensagem de informação
    public static void exibirInfo(String mensagem) {
        System.out.println("\nℹ " + mensagem);
    }

    // Formatar valor monetário
    public static String formatarValor(double valor) {
        return String.format("R$ %.2f", valor);
    }

    // Exibir opções de menu
    public static void exibirOpcoes(String[] opcoes) {
        System.out.println();
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println(opcoes[i]);
        }
        System.out.println();
    }

    // Solicitar opção
    public static int solicitarOpcao(Scanner sc) {
        System.out.print("❯ Escolha uma opção: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Solicitar confirmação (S/N)
    public static boolean confirmar(Scanner sc, String mensagem) {
        System.out.print(mensagem + " (S/N): ");
        String resposta = sc.nextLine().trim().toUpperCase();
        return resposta.equals("S") || resposta.equals("SIM");
    }
}
