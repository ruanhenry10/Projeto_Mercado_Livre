

public class Produto extends Entidade {
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;
    private int lojaId;
    private int quantidadeVendida;

    public Produto(String nome, String descricao, double preco, int estoque, int lojaId) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.lojaId = lojaId;
        this.quantidadeVendida = 0;
    }

    public Produto(int id, String nome, String descricao, double preco, int estoque, int lojaId) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.lojaId = lojaId;
        this.quantidadeVendida = 0;
    }

    // Sobrecarga: venda com quantidade diferente
    public boolean vender() { 
        return vender(1); 
    }
    
    public boolean vender(int quantidade) {
        if (quantidade <= 0) return false;
        if (estoque >= quantidade) { 
            estoque -= quantidade;
            quantidadeVendida += quantidade;
            return true; 
        }
        return false;
    }

    public void repor(int quantidade) { 
        if (quantidade > 0) {
            estoque += quantidade; 
        }
    }

    public boolean temEstoque() {
        return estoque > 0;
    }

    public boolean temEstoque(int quantidade) {
        return estoque >= quantidade;
    }

    // Getters
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }
    public int getLojaId() { return lojaId; }
    public int getQuantidadeVendida() { return quantidadeVendida; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String getResumo() {
        return String.format("Produto[id=%d, nome=%s, preco=R$ %.2f, estoque=%d, vendidos=%d]", 
            id, nome, preco, estoque, quantidadeVendida);
    }

    public String getResumoDetalhado() {
        return String.format("ID: %d | %s\n" +
                           "Descrição: %s\n" +
                           "Preço: R$ %.2f | Estoque: %d unidades\n" +
                           "Vendidos: %d unidades",
            id, nome, descricao, preco, estoque, quantidadeVendida);
    }

    @Override
    public String toString() { 
        return getResumo(); 
    }
}
