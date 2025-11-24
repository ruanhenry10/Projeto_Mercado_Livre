

import java.util.List;

public class LojaService implements IService<Loja> {
    private LojaRepository repo;

    public LojaService(LojaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void cadastrar(Loja obj) {
        repo.cadastrar(obj);
    }

    @Override
    public boolean remover(int id) {
        return repo.remover(id);
    }

    @Override
    public List<Loja> listar() {
        return repo.listar();
    }

    @Override
    public boolean alterar(Loja obj) {
        return repo.alterar(obj);
    }

    @Override
    public Loja buscarPorId(int id) {
        return repo.buscarPorId(id);
    }

    // Métodos específicos
    public List<Loja> buscarPorDono(int usuarioId) {
        return repo.buscarPorDono(usuarioId);
    }

    public List<Loja> buscarPorNome(String nome) {
        return repo.buscarPorNome(nome);
    }

    public List<Loja> listarAtivas() {
        return repo.listarAtivas();
    }

    public boolean ehDono(int lojaId, int usuarioId) {
        Loja loja = repo.buscarPorId(lojaId);
        return loja != null && loja.ehDono(usuarioId);
    }

    public boolean adicionarProdutoNaLoja(int lojaId, int produtoId) {
        Loja loja = repo.buscarPorId(lojaId);
        if (loja != null) {
            loja.adicionarProdutoId(produtoId);
            return repo.alterar(loja);
        }
        return false;
    }

    public boolean removerProdutoDaLoja(int lojaId, int produtoId) {
        Loja loja = repo.buscarPorId(lojaId);
        if (loja != null) {
            loja.removerProdutoId(produtoId);
            return repo.alterar(loja);
        }
        return false;
    }
}
