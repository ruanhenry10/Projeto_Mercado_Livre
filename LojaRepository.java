

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LojaRepository implements IRepository<Loja> {
    private List<Loja> lista = new ArrayList<>();

    @Override
    public void cadastrar(Loja obj) {
        lista.add(obj);
    }

    @Override
    public boolean remover(int id) {
        return lista.removeIf(l -> l.getId() == id);
    }

    @Override
    public List<Loja> listar() {
        return new ArrayList<>(lista);
    }

    @Override
    public boolean alterar(Loja obj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == obj.getId()) {
                lista.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public Loja buscarPorId(int id) {
        return lista.stream()
            .filter(l -> l.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Métodos específicos
    public List<Loja> buscarPorDono(int usuarioId) {
        return lista.stream()
            .filter(l -> l.getDono() != null && l.getDono().getId() == usuarioId)
            .collect(Collectors.toList());
    }

    public List<Loja> buscarPorNome(String nome) {
        return lista.stream()
            .filter(l -> l.getNome().toLowerCase().contains(nome.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Loja> listarAtivas() {
        return lista.stream()
            .filter(Loja::isAtiva)
            .collect(Collectors.toList());
    }
}
