

import java.util.List;

public class PedidoService implements IService<Pedido> {
    private PedidoRepository repo;

    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void cadastrar(Pedido obj) {
        repo.cadastrar(obj);
    }

    @Override
    public boolean remover(int id) {
        return repo.remover(id);
    }

    @Override
    public List<Pedido> listar() {
        return repo.listar();
    }

    @Override
    public boolean alterar(Pedido obj) {
        return repo.alterar(obj);
    }

    @Override
    public Pedido buscarPorId(int id) {
        return repo.buscarPorId(id);
    }

    // Métodos específicos
    public List<Pedido> buscarPorUsuario(int usuarioId) {
        return repo.buscarPorUsuario(usuarioId);
    }

    public List<Pedido> buscarPorStatus(String status) {
        return repo.buscarPorStatus(status);
    }

    public boolean marcarComoPago(int pedidoId, int pagamentoId) {
        Pedido pedido = repo.buscarPorId(pedidoId);
        if (pedido != null) {
            pedido.marcarComoPago(pagamentoId);
            return repo.alterar(pedido);
        }
        return false;
    }

    public boolean marcarComoEnviado(int pedidoId) {
        Pedido pedido = repo.buscarPorId(pedidoId);
        if (pedido != null) {
            pedido.marcarComoEnviado();
            return repo.alterar(pedido);
        }
        return false;
    }

    public boolean marcarComoEntregue(int pedidoId) {
        Pedido pedido = repo.buscarPorId(pedidoId);
        if (pedido != null) {
            pedido.marcarComoEntregue();
            return repo.alterar(pedido);
        }
        return false;
    }

    public boolean cancelarPedido(int pedidoId) {
        Pedido pedido = repo.buscarPorId(pedidoId);
        if (pedido != null) {
            pedido.cancelar();
            return repo.alterar(pedido);
        }
        return false;
    }
}
