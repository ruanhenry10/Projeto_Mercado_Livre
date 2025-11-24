

import java.util.ArrayList;
import java.util.List;

public class Loja extends Entidade {
    private String nome;
    private String descricao;
    private Usuario dono;
    private List<Integer> produtoIds;
    private boolean ativa;

    public Loja(String nome, String descricao, Usuario dono) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.dono = dono;
        this.produtoIds = new ArrayList<>();
        this.ativa = true;
    }

    public Loja(int id, String nome, String descricao, Usuario dono) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.dono = dono;
        this.produtoIds = new ArrayList<>();
        this.ativa = true;
    }

    // Gerenciamento de produtos
    public void adicionarProdutoId(int produtoId) { 
        if (!produtoIds.contains(produtoId)) {
            produtoIds.add(produtoId); 
        }
    }

    public void removerProdutoId(int produtoId) {
        produtoIds.remove(Integer.valueOf(produtoId));
    }

    public boolean contemProduto(int produtoId) {
        return produtoIds.contains(produtoId);
    }

    public boolean ehDono(int usuarioId) {
        return dono != null && dono.getId() == usuarioId;
    }

    public void ativar() { this.ativa = true; }
    public void desativar() { this.ativa = false; }

    // Getters
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public Usuario getDono() { return dono; }
    public List<Integer> getProdutoIds() { return new ArrayList<>(produtoIds); }
    public boolean isAtiva() { return ativa; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String getResumo() {
        String status = ativa ? "ATIVA" : "INATIVA";
        return String.format("Loja[id=%d, nome=%s, dono=%s, produtos=%d, status=%s]", 
            id, nome, (dono != null ? dono.getNome() : "-"), produtoIds.size(), status);
    }

    public String getResumoDetalhado() {
        String status = ativa ? "ATIVA" : "INATIVA";
        return String.format("ID: %d | %s [%s]\n" +
                           "Descrição: %s\n" +
                           "Dono: %s\n" +
                           "Total de produtos: %d",
            id, nome, status, descricao, 
            (dono != null ? dono.getNome() : "-"), 
            produtoIds.size());
    }

    @Override
    public String toString() { 
        return getResumo(); 
    }
}
