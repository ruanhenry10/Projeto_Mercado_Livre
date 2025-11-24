

public class CarrinhoItem {
    private Produto produto;
    private int quantidade;

    public CarrinhoItem(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void incrementarQuantidade() {
        this.quantidade++;
    }

    public void incrementarQuantidade(int qtd) {
        this.quantidade += qtd;
    }

    public void decrementarQuantidade() {
        if (this.quantidade > 0) {
            this.quantidade--;
        }
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return String.format("%s x%d = R$ %.2f",
            produto.getNome(), quantidade, getSubtotal());
    }

    public String toStringDetalhado() {
        return String.format("Produto: %s\n" +
                           "Preço unitário: R$ %.2f\n" +
                           "Quantidade: %d\n" +
                           "Subtotal: R$ %.2f",
            produto.getNome(), produto.getPreco(), quantidade, getSubtotal());
    }
}
