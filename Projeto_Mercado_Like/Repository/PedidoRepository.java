

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoRepository implements IRepository<Pedido> {
    private List<Pedido> lista = new ArrayList<>();

    @Override
    public void cadastrar(Pedido obj) {
        lista.add(obj);
    }

    @Override
    public boolean remover(int id) {
        return lista.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Pedido> listar() {
        return new ArrayList<>(lista);
    }

    @Override
    public boolean alterar(Pedido obj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == obj.getId()) {
                lista.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public Pedido buscarPorId(int id) {
        return lista.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Métodos específicos
    public List<Pedido> buscarPorUsuario(int usuarioId) {
        return lista.stream()
            .filter(p -> p.getUsuarioId() == usuarioId)
            .collect(Collectors.toList());
    }

    public List<Pedido> buscarPorStatus(String status) {
        return lista.stream()
            .filter(p -> p.getStatus().equalsIgnoreCase(status))
            .collect(Collectors.toList());
    }
}
