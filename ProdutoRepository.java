

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoRepository implements IRepository<Produto> {
    private List<Produto> lista = new ArrayList<>();

    @Override
    public void cadastrar(Produto obj) {
        lista.add(obj);
    }

    @Override
    public boolean remover(int id) {
        return lista.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Produto> listar() {
        return new ArrayList<>(lista);
    }

    @Override
    public boolean alterar(Produto obj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == obj.getId()) {
                lista.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public Produto buscarPorId(int id) {
        return lista.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Métodos específicos
    public List<Produto> buscarPorNome(String nome) {
        return lista.stream()
            .filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Produto> buscarPorLoja(int lojaId) {
        return lista.stream()
            .filter(p -> p.getLojaId() == lojaId)
            .collect(Collectors.toList());
    }

    public List<Produto> buscarPorFaixaPreco(double precoMin, double precoMax) {
        return lista.stream()
            .filter(p -> p.getPreco() >= precoMin && p.getPreco() <= precoMax)
            .collect(Collectors.toList());
    }

    public List<Produto> buscarComEstoque() {
        return lista.stream()
            .filter(Produto::temEstoque)
            .collect(Collectors.toList());
    }

    public List<Produto> buscarMaisVendidos(int limite) {
        return lista.stream()
            .sorted((p1, p2) -> Integer.compare(p2.getQuantidadeVendida(), p1.getQuantidadeVendida()))
            .limit(limite)
            .collect(Collectors.toList());
    }
}
