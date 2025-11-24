

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Entidade {
    private int usuarioId;
    private List<CarrinhoItem> itens;
    private double total;
    private String status; // "Aguardando Pagamento", "Pago", "Enviado", "Entregue", "Cancelado"
    private LocalDateTime dataPedido;
    private int pagamentoId;

    public Pedido(int usuarioId, List<CarrinhoItem> itens, double total) {
        super();
        this.usuarioId = usuarioId;
        this.itens = new ArrayList<>(itens);
        this.total = total;
        this.status = "Aguardando Pagamento";
        this.dataPedido = LocalDateTime.now();
        this.pagamentoId = -1;
    }

    public Pedido(int id, int usuarioId, List<CarrinhoItem> itens, double total, 
                  String status, LocalDateTime dataPedido, int pagamentoId) {
        super(id);
        this.usuarioId = usuarioId;
        this.itens = new ArrayList<>(itens);
        this.total = total;
        this.status = status;
        this.dataPedido = dataPedido;
        this.pagamentoId = pagamentoId;
    }

    // Métodos de gerenciamento de status
    public void marcarComoPago(int pagamentoId) {
        this.status = "Pago";
        this.pagamentoId = pagamentoId;
    }

    public void marcarComoEnviado() {
        if ("Pago".equals(this.status)) {
            this.status = "Enviado";
        }
    }

    public void marcarComoEntregue() {
        if ("Enviado".equals(this.status)) {
            this.status = "Entregue";
        }
    }

    public void cancelar() {
        if (!"Enviado".equals(this.status) && !"Entregue".equals(this.status)) {
            this.status = "Cancelado";
        }
    }

    // Getters
    public int getUsuarioId() { return usuarioId; }
    public List<CarrinhoItem> getItens() { return new ArrayList<>(itens); }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public int getPagamentoId() { return pagamentoId; }

    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataPedido.format(formatter);
    }

    @Override
    public String getResumo() {
        return String.format("Pedido[id=%d, total=R$ %.2f, status=%s, data=%s]",
            id, total, status, getDataFormatada());
    }

    public String getResumoDetalhado() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(60)).append("\n");
        sb.append(String.format("PEDIDO #%d\n", id));
        sb.append("=".repeat(60)).append("\n");
        sb.append(String.format("Data: %s\n", getDataFormatada()));
        sb.append(String.format("Status: %s\n", status));
        sb.append("\nItens:\n");
        sb.append("-".repeat(60)).append("\n");
        
        for (CarrinhoItem item : itens) {
            sb.append(String.format("  %s x%d - R$ %.2f\n",
                item.getProduto().getNome(), 
                item.getQuantidade(), 
                item.getSubtotal()));
        }
        
        sb.append("-".repeat(60)).append("\n");
        sb.append(String.format("TOTAL: R$ %.2f\n", total));
        sb.append("=".repeat(60));
        
        return sb.toString();
    }

    @Override
    public String toString() {
        return getResumo();
    }
}
