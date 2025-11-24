

public class Sessao {
    private static Sessao instancia;
    private Usuario usuarioLogado;

    // Construtor privado (Singleton)
    private Sessao() {
        this.usuarioLogado = null;
    }

    // Método estático para obter a instância única
    public static Sessao getInstancia() {
        if (instancia == null) {
            instancia = new Sessao();
        }
        return instancia;
    }

    // Fazer login
    public void login(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    // Fazer logout
    public void logout() {
        this.usuarioLogado = null;
    }

    // Verificar se está logado
    public boolean isLogado() {
        return usuarioLogado != null;
    }

    // Obter usuário logado
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    // Obter ID do usuário logado
    public int getUsuarioLogadoId() {
        return usuarioLogado != null ? usuarioLogado.getId() : -1;
    }

    // Obter nome do usuário logado
    public String getNomeUsuarioLogado() {
        return usuarioLogado != null ? usuarioLogado.getNome() : "Não logado";
    }
}
