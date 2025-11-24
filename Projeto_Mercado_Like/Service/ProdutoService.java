

import java.util.List;

public class ProdutoService implements IService<Produto> {
    private ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void cadastrar(Produto obj) {
        repo.cadastrar(obj);
    }

    @Override
    public boolean remover(int id) {
        return repo.remover(id);
    }

    @Override
    public List<Produto> listar() {
        return repo.listar();
    }

    @Override
    public boolean alterar(Produto obj) {
        return repo.alterar(obj);
    }

    @Override
    public Produto buscarPorId(int id) {
        return repo.buscarPorId(id);
    }

    // Métodos específicos
    public List<Produto> buscarPorNome(String nome) {
        return repo.buscarPorNome(nome);
    }

    public List<Produto> buscarPorLoja(int lojaId) {
        return repo.buscarPorLoja(lojaId);
    }

    public List<Produto> buscarPorFaixaPreco(double precoMin, double precoMax) {
        return repo.buscarPorFaixaPreco(precoMin, precoMax);
    }

    public List<Produto> buscarComEstoque() {
        return repo.buscarComEstoque();
    }

    public List<Produto> buscarMaisVendidos(int limite) {
        return repo.buscarMaisVendidos(limite);
    }

    public boolean venderProduto(int produtoId, int quantidade) {
        Produto produto = repo.buscarPorId(produtoId);
        if (produto != null && produto.vender(quantidade)) {
            repo.alterar(produto);
            return true;
        }
        return false;
    }

    public boolean reporEstoque(int produtoId, int quantidade) {
        Produto produto = repo.buscarPorId(produtoId);
        if (produto != null) {
            produto.repor(quantidade);
            repo.alterar(produto);
            return true;
        }
        return false;
    }
}
