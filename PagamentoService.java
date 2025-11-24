

public class PagamentoService {
    
    public boolean processar(Pagamento p) {
        return p.processar();
    }
    
    public boolean processarComDesconto(Pagamento p, double desconto) {
        return p.processar(desconto);
    }
    
    public boolean processarParcelado(Pagamento p, int parcelas) {
        return p.processar(parcelas);
    }
}
