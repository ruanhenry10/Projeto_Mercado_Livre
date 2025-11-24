

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioRepository implements IRepository<Usuario> {
    private List<Usuario> lista = new ArrayList<>();

    @Override
    public void cadastrar(Usuario u) {
        lista.add(u);
    }

    @Override
    public boolean remover(int id) {
        return lista.removeIf(u -> u.getId() == id);
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(lista);
    }

    @Override
    public boolean alterar(Usuario obj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == obj.getId()) {
                lista.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public Usuario buscarPorId(int id) {
        return lista.stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Métodos específicos
    public Usuario buscarPorEmail(String email) {
        return lista.stream()
            .filter(u -> u.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);
    }

    public List<Usuario> buscarPorNome(String nome) {
        return lista.stream()
            .filter(u -> u.getNome().toLowerCase().contains(nome.toLowerCase()))
            .collect(Collectors.toList());
    }
}
