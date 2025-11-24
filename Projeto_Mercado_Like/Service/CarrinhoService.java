

import java.util.ArrayList;
import java.util.List;

public class CarrinhoService {
    private List<CarrinhoItem> itens;

    public CarrinhoService() {
        this.itens = new ArrayList<>();
    }

    // Adicionar produto ao carrinho
    public boolean adicionarProduto(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return false;
        }

        // Verificar se o produto já está no carrinho
        for (CarrinhoItem item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                // Se já existe, incrementa a quantidade
                item.incrementarQuantidade(quantidade);
                return true;
            }
        }

        // Se não existe, adiciona novo item
        itens.add(new CarrinhoItem(produto, quantidade));
        return true;
    }

    // Remover produto do carrinho
    public boolean removerProduto(int produtoId) {
        return itens.removeIf(item -> item.getProduto().getId() == produtoId);
    }

    // Atualizar quantidade de um produto
    public boolean atualizarQuantidade(int produtoId, int novaQuantidade) {
        if (novaQuantidade <= 0) {
            return removerProduto(produtoId);
        }

        for (CarrinhoItem item : itens) {
            if (item.getProduto().getId() == produtoId) {
                item.setQuantidade(novaQuantidade);
                return true;
            }
        }
        return false;
    }

    // Obter total do carrinho
    public double calcularTotal() {
        return itens.stream()
            .mapToDouble(CarrinhoItem::getSubtotal)
            .sum();
    }

    // Verificar se o carrinho está vazio
    public boolean estaVazio() {
        return itens.isEmpty();
    }

    // Obter quantidade de itens diferentes
    public int getQuantidadeItens() {
        return itens.size();
    }

    // Obter quantidade total de produtos
    public int getQuantidadeTotalProdutos() {
        return itens.stream()
            .mapToInt(CarrinhoItem::getQuantidade)
            .sum();
    }

    // Listar itens do carrinho
    public List<CarrinhoItem> getItens() {
        return new ArrayList<>(itens);
    }

    // Limpar carrinho
    public void limpar() {
        itens.clear();
    }

    // Validar estoque antes de finalizar compra
    public boolean validarEstoque() {
        for (CarrinhoItem item : itens) {
            if (!item.getProduto().temEstoque(item.getQuantidade())) {
                return false;
            }
        }
        return true;
    }

    // Exibir carrinho formatado
    public String exibirCarrinho() {
        if (estaVazio()) {
            return "Carrinho vazio!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(60)).append("\n");
        sb.append("CARRINHO DE COMPRAS\n");
        sb.append("=".repeat(60)).append("\n");

        for (int i = 0; i < itens.size(); i++) {
            CarrinhoItem item = itens.get(i);
            sb.append(String.format("%d. %s\n", i + 1, item.toString()));
        }

        sb.append("-".repeat(60)).append("\n");
        sb.append(String.format("TOTAL: R$ %.2f\n", calcularTotal()));
        sb.append(String.format("Total de itens: %d\n", getQuantidadeTotalProdutos()));
        sb.append("=".repeat(60));

        return sb.toString();
    }
}
