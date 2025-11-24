

public class Suporte extends Entidade {
    private String assunto;
    private String mensagem;
    private boolean resolvido;
    private int usuarioId;

    public Suporte(String assunto, String mensagem, int usuarioId) {
        super();
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.usuarioId = usuarioId;
        this.resolvido = false;
    }

    public Suporte(int id, String assunto, String mensagem, int usuarioId, boolean resolvido) {
        super(id);
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.usuarioId = usuarioId;
        this.resolvido = resolvido;
    }

    // Sobrecarga: responder com texto simples ou com texto e marcar resolvido
    public void responder(String resposta) {
        System.out.println("\n📧 Resposta: " + resposta);
    }
    
    public void responder(String resposta, boolean marcarResolvido) {
        System.out.println("\n📧 Resposta: " + resposta);
        this.resolvido = marcarResolvido;
        if (marcarResolvido) {
            System.out.println("✓ Ticket marcado como resolvido!");
        }
    }

    public void marcarComoResolvido() {
        this.resolvido = true;
    }

    // Getters
    public String getAssunto() { return assunto; }
    public String getMensagem() { return mensagem; }
    public boolean isResolvido() { return resolvido; }
    public int getUsuarioId() { return usuarioId; }

    @Override
    public String getResumo() {
        String status = resolvido ? "✓ RESOLVIDO" : "⏳ PENDENTE";
        return String.format("Suporte[id=%d, assunto=%s, status=%s]", 
            id, assunto, status);
    }

    public String getResumoDetalhado() {
        String status = resolvido ? "✓ RESOLVIDO" : "⏳ PENDENTE";
        return String.format("ID: %d | %s\n" +
                           "Assunto: %s\n" +
                           "Mensagem: %s\n" +
                           "Status: %s",
            id, status, assunto, mensagem, status);
    }

    @Override
    public String toString() { 
        return getResumo(); 
    }
}
