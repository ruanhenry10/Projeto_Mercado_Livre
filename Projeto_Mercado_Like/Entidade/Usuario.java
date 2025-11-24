

public class Usuario extends Entidade {
    private String nome;
    private String email;
    private String senha;
    private double saldo;

    public Usuario(String nome, String email, String senha) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.saldo = 0.0;
    }

    public Usuario(int id, String nome, String email, String senha, double saldo) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
    }

    // Métodos de autenticação
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    // Métodos de saldo
    public boolean adicionarSaldo(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        }
        return false;
    }

    public boolean removerSaldo(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    // Sobrecarga de método (atualização)
    public void atualizar(String nome) { 
        this.nome = nome; 
    }
    
    public void atualizar(String nome, String email) { 
        this.nome = nome; 
        this.email = email; 
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    // Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public double getSaldo() { return saldo; }

    @Override
    public String getResumo() {
        return String.format("Usuario[id=%d, nome=%s, email=%s, saldo=R$ %.2f]", 
            id, nome, email, saldo);
    }

    @Override
    public String toString() { 
        return getResumo(); 
    }
}
