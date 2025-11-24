

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SuporteService implements IService<Suporte> {
    private List<Suporte> lista = new ArrayList<>();

    @Override
    public void cadastrar(Suporte obj) {
        lista.add(obj);
    }

    @Override
    public boolean remover(int id) {
        return lista.removeIf(s -> s.getId() == id);
    }

    @Override
    public List<Suporte> listar() {
        return new ArrayList<>(lista);
    }

    @Override
    public boolean alterar(Suporte obj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == obj.getId()) {
                lista.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public Suporte buscarPorId(int id) {
        return lista.stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Métodos específicos
    public List<Suporte> buscarPorUsuario(int usuarioId) {
        return lista.stream()
            .filter(s -> s.getUsuarioId() == usuarioId)
            .collect(Collectors.toList());
    }

    public List<Suporte> listarPendentes() {
        return lista.stream()
            .filter(s -> !s.isResolvido())
            .collect(Collectors.toList());
    }

    public List<Suporte> listarResolvidos() {
        return lista.stream()
            .filter(Suporte::isResolvido)
            .collect(Collectors.toList());
    }

    public boolean marcarComoResolvido(int id) {
        Suporte suporte = buscarPorId(id);
        if (suporte != null) {
            suporte.marcarComoResolvido();
            return alterar(suporte);
        }
        return false;
    }
}
