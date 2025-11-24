

public class Pagamento extends Entidade {
    private double valor;
    private String forma; // "Cartão de Crédito", "Boleto", "PIX"
    private String status; // "Pendente", "Aprovado", "Recusado"
    private int pedidoId;

    public Pagamento(double valor, String forma, int pedidoId) {
        super();
        this.valor = valor;
        this.forma = forma;
        this.pedidoId = pedidoId;
        this.status = "Pendente";
    }

    public Pagamento(int id, double valor, String forma, int pedidoId, String status) {
        super(id);
        this.valor = valor;
        this.forma = forma;
        this.pedidoId = pedidoId;
        this.status = status;
    }

    // Sobrecarga: processar pagamento simples, com desconto, ou parcelado
    public boolean processar() {
        System.out.println("✓ Processando " + forma + " no valor de R$ " + String.format("%.2f", valor));
        this.status = "Aprovado";
        return true;
    }

    public boolean processar(double desconto) {
        double vFinal = valor - desconto;
        if (vFinal < 0) {
            System.out.println("✗ Desconto inválido!");
            return false;
        }
        System.out.println("✓ Processando " + forma + " com desconto de R$ " + 
                         String.format("%.2f", desconto) + " -> Total: R$ " + 
                         String.format("%.2f", vFinal));
        this.valor = vFinal;
        this.status = "Aprovado";
        return true;
    }

    public boolean processar(int parcelas) {
        if (parcelas <= 0) {
            System.out.println("✗ Número de parcelas inválido!");
            return false;
        }
        double valorParcela = valor / parcelas;
        System.out.println("✓ Processando em " + parcelas + "x de R$ " + 
                         String.format("%.2f", valorParcela) + " via " + forma);
        this.status = "Aprovado";
        return true;
    }

    public void recusar() {
        this.status = "Recusado";
    }

    // Getters
    public double getValor() { return valor; }
    public String getForma() { return forma; }
    public String getStatus() { return status; }
    public int getPedidoId() { return pedidoId; }

    @Override
    public String getResumo() {
        return String.format("Pagamento[id=%d, valor=R$ %.2f, forma=%s, status=%s]", 
            id, valor, forma, status);
    }

    @Override
    public String toString() { 
        return getResumo(); 
    }
}
