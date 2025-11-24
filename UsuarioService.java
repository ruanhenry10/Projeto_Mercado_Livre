

import java.util.List;

public class UsuarioService implements IService<Usuario> {
    private UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public void cadastrar(Usuario obj) {
        repo.cadastrar(obj);
    }

    @Override
    public boolean remover(int id) {
        return repo.remover(id);
    }

    @Override
    public List<Usuario> listar() {
        return repo.listar();
    }

    @Override
    public boolean alterar(Usuario obj) {
        return repo.alterar(obj);
    }

    @Override
    public Usuario buscarPorId(int id) {
        return repo.buscarPorId(id);
    }

    // Métodos específicos
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = repo.buscarPorEmail(email);
        if (usuario != null && usuario.autenticar(senha)) {
            return usuario;
        }
        return null;
    }

    public boolean emailJaExiste(String email) {
        return repo.buscarPorEmail(email) != null;
    }

    public List<Usuario> buscarPorNome(String nome) {
        return repo.buscarPorNome(nome);
    }

    public boolean adicionarSaldo(int usuarioId, double valor) {
        Usuario usuario = repo.buscarPorId(usuarioId);
        if (usuario != null && usuario.adicionarSaldo(valor)) {
            repo.alterar(usuario);
            return true;
        }
        return false;
    }

    public boolean removerSaldo(int usuarioId, double valor) {
        Usuario usuario = repo.buscarPorId(usuarioId);
        if (usuario != null && usuario.removerSaldo(valor)) {
            repo.alterar(usuario);
            return true;
        }
        return false;
    }
}
