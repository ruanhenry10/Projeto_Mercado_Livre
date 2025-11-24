import java.util.List;
import java.util.Scanner;

public class MercadoLike {
    // Repositories
    private static UsuarioRepository uRepo = new UsuarioRepository();
    private static LojaRepository lRepo = new LojaRepository();
    private static ProdutoRepository pRepo = new ProdutoRepository();
    private static PedidoRepository pedidoRepo = new PedidoRepository();

    // Services
    private static UsuarioService uService = new UsuarioService(uRepo);
    private static LojaService lService = new LojaService(lRepo);
    private static ProdutoService pService = new ProdutoService(pRepo);
    private static PedidoService pedidoService = new PedidoService(pedidoRepo);
    private static SuporteService suporteService = new SuporteService();
    private static PagamentoService pagamentoService = new PagamentoService();
    private static CarrinhoService carrinhoService = new CarrinhoService();

    // Sessão
    private static Sessao sessao = Sessao.getInstancia();

    // Scanner
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            if (!sessao.isLogado()) {
                running = menuInicial();
            } else {
                running = menuPrincipal();
            }
        }
        
        MenuUtil.exibirTitulo("OBRIGADO POR USAR O MERCADOLIKE!");
        sc.close();
    }

    // ========== MENU INICIAL (Não Logado) ==========
    private static boolean menuInicial() {
        MenuUtil.exibirTitulo("MERCADOLIKE - BEM-VINDO!");
        
        String[] opcoes = {
            "1)  Fazer Login",
            "2)  Criar Conta",
            "3)  Sobre o MercadoLike",
            "0)  Sair"
        };
        MenuUtil.exibirOpcoes(opcoes);
        
        int opt = MenuUtil.solicitarOpcao(sc);
        
        switch(opt) {
            case 1:
                fazerLogin();
                break;
            case 2:
                criarConta();
                break;
            case 3:
                exibirSobre();
                break;
            case 0:
                return false;
            default:
                MenuUtil.exibirErro("Opcao invalida!");
                MenuUtil.pausar(sc);
        }
        
        return true;
    }

    // ========== MENU PRINCIPAL (Logado) ==========
    private static boolean menuPrincipal() {
        Usuario usuarioLogado = sessao.getUsuarioLogado();
        
        MenuUtil.exibirTitulo("MERCADOLIKE");
        System.out.println("Usuario: " + usuarioLogado.getNome());
        System.out.println("Saldo: " + MenuUtil.formatarValor(usuarioLogado.getSaldo()));
        System.out.println("Itens no carrinho: " + carrinhoService.getQuantidadeTotalProdutos());
        
        String[] opcoes = {
            "1)  Explorar Produtos",
            "2)  Minhas Lojas",
            "3)  Carrinho de Compras",
            "4)  Meus Pedidos",
            "5)  Minha Conta",
            "6)  Suporte",
            "0)  Sair"
        };
        MenuUtil.exibirOpcoes(opcoes);
        
        int opt = MenuUtil.solicitarOpcao(sc);
        
        switch(opt) {
            case 1:
                menuProdutos();
                break;
            case 2:
                menuLojas();
                break;
            case 3:
                menuCarrinho();
                break;
            case 4:
                menuPedidos();
                break;
            case 5:
                menuConta();
                break;
            case 6:
                menuSuporte();
                break;
            case 0:
                sessao.logout();
                carrinhoService.limpar();
                MenuUtil.exibirSucesso("Logout realizado com sucesso!");
                MenuUtil.pausar(sc);
                break;
            default:
                MenuUtil.exibirErro("Opcao invalida!");
                MenuUtil.pausar(sc);
        }
        
        return true;
    }

    // ========== LOGIN E REGISTRO ==========
    private static void fazerLogin() {
        MenuUtil.exibirTitulo("LOGIN");
        
        String email = ValidadorUtil.solicitarEmail(sc);
        String senha = ValidadorUtil.solicitarSenha(sc, "Senha: ");
        
        Usuario usuario = uService.autenticar(email, senha);
        
        if (usuario != null) {
            sessao.login(usuario);
            MenuUtil.exibirSucesso("Login realizado com sucesso! Bem-vindo(a), " + usuario.getNome() + "!");
        } else {
            MenuUtil.exibirErro("Email ou senha incorretos!");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void criarConta() {
        MenuUtil.exibirTitulo("CRIAR CONTA");
        
        String nome = ValidadorUtil.solicitarTexto(sc, "Nome completo: ");
        String email = ValidadorUtil.solicitarEmail(sc);
        
        if (uService.emailJaExiste(email)) {
            MenuUtil.exibirErro("Este email ja esta cadastrado!");
            MenuUtil.pausar(sc);
            return;
        }
        
        String senha = ValidadorUtil.solicitarSenha(sc, "Senha (minimo 6 caracteres): ");
        String senhaConfirm = ValidadorUtil.solicitarSenha(sc, "Confirme a senha: ");
        
        if (!senha.equals(senhaConfirm)) {
            MenuUtil.exibirErro("As senhas nao conferem!");
            MenuUtil.pausar(sc);
            return;
        }
        
        Usuario novoUsuario = new Usuario(nome, email, senha);
        uService.cadastrar(novoUsuario);
        
        MenuUtil.exibirSucesso("Conta criada com sucesso!");
        MenuUtil.exibirInfo("Voce ja pode fazer login com seu email e senha.");
        MenuUtil.pausar(sc);
    }

    private static void exibirSobre() {
        MenuUtil.exibirTitulo("SOBRE O MERCADOLIKE");
        System.out.println("\nO MercadoLike e uma plataforma de e-commerce completa!");
        System.out.println("\nFuncionalidades:");
        System.out.println("   * Criar e gerenciar sua propria loja");
        System.out.println("   * Cadastrar e vender produtos");
        System.out.println("   * Comprar produtos de outras lojas");
        System.out.println("   * Carrinho de compras inteligente");
        System.out.println("   * Sistema de pagamento seguro");
        System.out.println("   * Historico completo de pedidos");
        System.out.println("   * Suporte ao cliente");
        System.out.println("\nDesenvolvido com Java POO (Programacao Orientada a Objetos)");
        System.out.println("   * Padroes: Repository, Service, Singleton");
        System.out.println("   * Conceitos: Heranca, Polimorfismo, Encapsulamento");
        MenuUtil.pausar(sc);
    }

    // ========== MENU PRODUTOS ==========
    private static void menuProdutos() {
        boolean voltar = false;
        
        while (!voltar) {
            MenuUtil.exibirTitulo("EXPLORAR PRODUTOS");
            
            String[] opcoes = {
                "1)  Ver Todos os Produtos",
                "2)  Buscar Produto por Nome",
                "3)  Filtrar por Faixa de Preco",
                "4)  Ver Produtos com Estoque",
                "5)  Ver Mais Vendidos",
                "6)  Ver Produto por ID",
                "0)  Voltar"
            };
            MenuUtil.exibirOpcoes(opcoes);
            
            int opt = MenuUtil.solicitarOpcao(sc);
            
            switch(opt) {
                case 1:
                    listarTodosProdutos();
                    break;
                case 2:
                    buscarProdutoPorNome();
                    break;
                case 3:
                    filtrarProdutosPorPreco();
                    break;
                case 4:
                    listarProdutosComEstoque();
                    break;
                case 5:
                    listarMaisVendidos();
                    break;
                case 6:
                    verDetalheProduto();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    MenuUtil.exibirErro("Opcao invalida!");
                    MenuUtil.pausar(sc);
            }
        }
    }

    private static void listarTodosProdutos() {
        MenuUtil.exibirSubtitulo("TODOS OS PRODUTOS");
        List<Produto> produtos = pService.listar();
        
        if (produtos.isEmpty()) {
            MenuUtil.exibirInfo("Nenhum produto cadastrado ainda.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.getResumo());
            }
            System.out.println("\nTotal: " + produtos.size() + " produtos");
            
            if (MenuUtil.confirmar(sc, "\nDeseja adicionar algum produto ao carrinho?")) {
                int id = ValidadorUtil.solicitarInteiro(sc, "ID do produto: ");
                adicionarProdutoAoCarrinho(id);
            }
        }
        
        MenuUtil.pausar(sc);
    }

    private static void buscarProdutoPorNome() {
        MenuUtil.exibirSubtitulo("BUSCAR PRODUTO");
        String nome = ValidadorUtil.solicitarTexto(sc, "Digite o nome do produto: ");
        
        List<Produto> produtos = pService.buscarPorNome(nome);
        
        if (produtos.isEmpty()) {
            MenuUtil.exibirInfo("Nenhum produto encontrado com esse nome.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.getResumo());
            }
            System.out.println("\nEncontrados: " + produtos.size() + " produtos");
            
            if (MenuUtil.confirmar(sc, "\nDeseja adicionar algum produto ao carrinho?")) {
                int id = ValidadorUtil.solicitarInteiro(sc, "ID do produto: ");
                adicionarProdutoAoCarrinho(id);
            }
        }
        
        MenuUtil.pausar(sc);
    }

    private static void filtrarProdutosPorPreco() {
        MenuUtil.exibirSubtitulo("FILTRAR POR PRECO");
        double precoMin = ValidadorUtil.solicitarValor(sc, "Preco minimo: R$ ");
        double precoMax = ValidadorUtil.solicitarValor(sc, "Preco maximo: R$ ");
        
        List<Produto> produtos = pService.buscarPorFaixaPreco(precoMin, precoMax);
        
        if (produtos.isEmpty()) {
            MenuUtil.exibirInfo("Nenhum produto encontrado nesta faixa de preco.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.getResumo());
            }
            System.out.println("\nEncontrados: " + produtos.size() + " produtos");
            
            if (MenuUtil.confirmar(sc, "\nDeseja adicionar algum produto ao carrinho?")) {
                int id = ValidadorUtil.solicitarInteiro(sc, "ID do produto: ");
                adicionarProdutoAoCarrinho(id);
            }
        }
        
        MenuUtil.pausar(sc);
    }

    private static void listarProdutosComEstoque() {
        MenuUtil.exibirSubtitulo("PRODUTOS COM ESTOQUE");
        List<Produto> produtos = pService.buscarComEstoque();
        
        if (produtos.isEmpty()) {
            MenuUtil.exibirInfo("Nenhum produto com estoque disponivel.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.getResumo());
            }
            System.out.println("\nTotal: " + produtos.size() + " produtos");
            
            if (MenuUtil.confirmar(sc, "\nDeseja adicionar algum produto ao carrinho?")) {
                int id = ValidadorUtil.solicitarInteiro(sc, "ID do produto: ");
                adicionarProdutoAoCarrinho(id);
            }
        }
        
        MenuUtil.pausar(sc);
    }

    private static void listarMaisVendidos() {
        MenuUtil.exibirSubtitulo("PRODUTOS MAIS VENDIDOS");
        List<Produto> produtos = pService.buscarMaisVendidos(10);
        
        if (produtos.isEmpty()) {
            MenuUtil.exibirInfo("Nenhuma venda registrada ainda.");
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                Produto p = produtos.get(i);
                System.out.println((i + 1) + "o - " + p.getResumo());
            }
            
            if (MenuUtil.confirmar(sc, "\nDeseja adicionar algum produto ao carrinho?")) {
                int id = ValidadorUtil.solicitarInteiro(sc, "ID do produto: ");
                adicionarProdutoAoCarrinho(id);
            }
        }
        
        MenuUtil.pausar(sc);
    }

    private static void verDetalheProduto() {
        int id = ValidadorUtil.solicitarInteiro(sc, "ID do produto: ");
        Produto produto = pService.buscarPorId(id);
        
        if (produto == null) {
            MenuUtil.exibirErro("Produto nao encontrado!");
        } else {
            MenuUtil.exibirSubtitulo("DETALHES DO PRODUTO");
            System.out.println(produto.getResumoDetalhado());
            
            Loja loja = lService.buscarPorId(produto.getLojaId());
            if (loja != null) {
                System.out.println("\nLoja: " + loja.getNome());
            }
            
            if (MenuUtil.confirmar(sc, "\nDeseja adicionar ao carrinho?")) {
                adicionarProdutoAoCarrinho(id);
            }
        }
        
        MenuUtil.pausar(sc);
    }

    private static void adicionarProdutoAoCarrinho(int produtoId) {
        Produto produto = pService.buscarPorId(produtoId);
        
        if (produto == null) {
            MenuUtil.exibirErro("Produto nao encontrado!");
            return;
        }
        
        if (!produto.temEstoque()) {
            MenuUtil.exibirErro("Produto sem estoque!");
            return;
        }
        
        int quantidade = ValidadorUtil.solicitarQuantidade(sc, "Quantidade: ");
        
        if (!produto.temEstoque(quantidade)) {
            MenuUtil.exibirErro("Estoque insuficiente! Disponivel: " + produto.getEstoque());
            return;
        }
        
        if (carrinhoService.adicionarProduto(produto, quantidade)) {
            MenuUtil.exibirSucesso("Produto adicionado ao carrinho!");
        } else {
            MenuUtil.exibirErro("Nao foi possivel adicionar o produto ao carrinho.");
        }
    }

    // ========== MENU LOJAS ==========
    private static void menuLojas() {
        boolean voltar = false;
        
        while (!voltar) {
            MenuUtil.exibirTitulo("MINHAS LOJAS");
            
            String[] opcoes = {
                "1)  Criar Nova Loja",
                "2)  Listar Minhas Lojas",
                "3)  Gerenciar Loja",
                "4)  Ver Todas as Lojas",
                "0)  Voltar"
            };
            MenuUtil.exibirOpcoes(opcoes);
            
            int opt = MenuUtil.solicitarOpcao(sc);
            
            switch(opt) {
                case 1:
                    criarLoja();
                    break;
                case 2:
                    listarMinhasLojas();
                    break;
                case 3:
                    gerenciarLoja();
                    break;
                case 4:
                    listarTodasLojas();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    MenuUtil.exibirErro("Opcao invalida!");
                    MenuUtil.pausar(sc);
            }
        }
    }

    private static void criarLoja() {
        MenuUtil.exibirSubtitulo("CRIAR NOVA LOJA");
        
        String nome = ValidadorUtil.solicitarTexto(sc, "Nome da loja: ");
        String descricao = ValidadorUtil.solicitarTexto(sc, "Descricao: ");
        
        Loja novaLoja = new Loja(nome, descricao, sessao.getUsuarioLogado());
        lService.cadastrar(novaLoja);
        
        MenuUtil.exibirSucesso("Loja criada com sucesso! ID: " + novaLoja.getId());
        MenuUtil.pausar(sc);
    }

    private static void listarMinhasLojas() {
        MenuUtil.exibirSubtitulo("MINHAS LOJAS");
        List<Loja> lojas = lService.buscarPorDono(sessao.getUsuarioLogadoId());
        
        if (lojas.isEmpty()) {
            MenuUtil.exibirInfo("Voce ainda nao possui nenhuma loja.");
        } else {
            for (Loja l : lojas) {
                System.out.println(l.getResumo());
            }
            System.out.println("\nTotal: " + lojas.size() + " lojas");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void gerenciarLoja() {
        List<Loja> lojas = lService.buscarPorDono(sessao.getUsuarioLogadoId());
        
        if (lojas.isEmpty()) {
            MenuUtil.exibirInfo("Voce ainda nao possui nenhuma loja.");
            MenuUtil.pausar(sc);
            return;
        }
        
        MenuUtil.exibirSubtitulo("SELECIONE UMA LOJA");
        for (Loja l : lojas) {
            System.out.println(l.getResumo());
        }
        
        int lojaId = ValidadorUtil.solicitarInteiro(sc, "\nID da loja: ");
        
        if (!lService.ehDono(lojaId, sessao.getUsuarioLogadoId())) {
            MenuUtil.exibirErro("Voce nao e dono desta loja!");
            MenuUtil.pausar(sc);
            return;
        }
        
        menuGerenciarLoja(lojaId);
    }

    private static void menuGerenciarLoja(int lojaId) {
        boolean voltar = false;
        Loja loja = lService.buscarPorId(lojaId);
        
        while (!voltar && loja != null) {
            MenuUtil.exibirTitulo("GERENCIAR LOJA: " + loja.getNome());
            
            String[] opcoes = {
                "1)  Adicionar Produto",
                "2)  Listar Produtos da Loja",
                "3)  Remover Produto",
                "4)  Editar Produto",
                "5)  Ver Detalhes da Loja",
                "0)  Voltar"
            };
            MenuUtil.exibirOpcoes(opcoes);
            
            int opt = MenuUtil.solicitarOpcao(sc);
            
            switch(opt) {
                case 1:
                    adicionarProdutoNaLoja(lojaId);
                    break;
                case 2:
                    listarProdutosDaLoja(lojaId);
                    break;
                case 3:
                    removerProdutoDaLoja(lojaId);
                    break;
                case 4:
                    editarProduto(lojaId);
                    break;
                case 5:
                    verDetalheLoja(lojaId);
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    MenuUtil.exibirErro("Opcao invalida!");
                    MenuUtil.pausar(sc);
            }
        }
    }

    private static void adicionarProdutoNaLoja(int lojaId) {
        MenuUtil.exibirSubtitulo("ADICIONAR PRODUTO");
        
        String nome = ValidadorUtil.solicitarTexto(sc, "Nome do produto: ");
        String descricao = ValidadorUtil.solicitarTexto(sc, "Descricao: ");
        double preco = ValidadorUtil.solicitarValor(sc, "Preco: R$ ");
        int estoque = ValidadorUtil.solicitarQuantidade(sc, "Quantidade em estoque: ");
        
        Produto novoProduto = new Produto(nome, descricao, preco, estoque, lojaId);
        pService.cadastrar(novoProduto);
        lService.adicionarProdutoNaLoja(lojaId, novoProduto.getId());
        
        MenuUtil.exibirSucesso("Produto adicionado com sucesso! ID: " + novoProduto.getId());
        MenuUtil.pausar(sc);
    }

    private static void listarProdutosDaLoja(int lojaId) {
        MenuUtil.exibirSubtitulo("PRODUTOS DA LOJA");
        List<Produto> produtos = pService.buscarPorLoja(lojaId);
        
        if (produtos.isEmpty()) {
            MenuUtil.exibirInfo("Nenhum produto cadastrado nesta loja.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.getResumo());
            }
            System.out.println("\nTotal: " + produtos.size() + " produtos");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void removerProdutoDaLoja(int lojaId) {
        listarProdutosDaLoja(lojaId);
        
        if (MenuUtil.confirmar(sc, "\nDeseja remover algum produto?")) {
            int produtoId = ValidadorUtil.solicitarInteiro(sc, "ID do produto: ");
            
            Produto produto = pService.buscarPorId(produtoId);
            if (produto == null || produto.getLojaId() != lojaId) {
                MenuUtil.exibirErro("Produto nao encontrado nesta loja!");
            } else {
                if (pService.remover(produtoId)) {
                    lService.removerProdutoDaLoja(lojaId, produtoId);
                    MenuUtil.exibirSucesso("Produto removido com sucesso!");
                } else {
                    MenuUtil.exibirErro("Nao foi possivel remover o produto.");
                }
            }
        }
        
        MenuUtil.pausar(sc);
    }

    private static void editarProduto(int lojaId) {
        listarProdutosDaLoja(lojaId);
        
        int produtoId = ValidadorUtil.solicitarInteiro(sc, "\nID do produto para editar: ");
        
        Produto produto = pService.buscarPorId(produtoId);
        if (produto == null || produto.getLojaId() != lojaId) {
            MenuUtil.exibirErro("Produto nao encontrado nesta loja!");
            MenuUtil.pausar(sc);
            return;
        }
        
        MenuUtil.exibirSubtitulo("EDITAR PRODUTO");
        System.out.println("Deixe em branco para manter o valor atual.\n");
        
        System.out.print("Novo nome [" + produto.getNome() + "]: ");
        String nome = sc.nextLine().trim();
        if (!nome.isEmpty()) produto.setNome(nome);
        
        System.out.print("Nova descricao [" + produto.getDescricao() + "]: ");
        String descricao = sc.nextLine().trim();
        if (!descricao.isEmpty()) produto.setDescricao(descricao);
        
        System.out.print("Novo preco [R$ " + produto.getPreco() + "]: R$ ");
        String precoStr = sc.nextLine().trim();
        if (!precoStr.isEmpty()) {
            try {
                double preco = Double.parseDouble(precoStr);
                if (preco > 0) produto.setPreco(preco);
            } catch (NumberFormatException e) {
                MenuUtil.exibirErro("Preco invalido, mantido o valor anterior.");
            }
        }
        
        if (pService.alterar(produto)) {
            MenuUtil.exibirSucesso("Produto atualizado com sucesso!");
        } else {
            MenuUtil.exibirErro("Nao foi possivel atualizar o produto.");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void verDetalheLoja(int lojaId) {
        Loja loja = lService.buscarPorId(lojaId);
        
        if (loja == null) {
            MenuUtil.exibirErro("Loja nao encontrada!");
        } else {
            MenuUtil.exibirSubtitulo("DETALHES DA LOJA");
            System.out.println(loja.getResumoDetalhado());
            
            List<Produto> produtos = pService.buscarPorLoja(lojaId);
            if (!produtos.isEmpty()) {
                System.out.println("\nProdutos:");
                for (Produto p : produtos) {
                    System.out.println("  * " + p.getNome() + " - " + MenuUtil.formatarValor(p.getPreco()));
                }
            }
        }
        
        MenuUtil.pausar(sc);
    }

    private static void listarTodasLojas() {
        MenuUtil.exibirSubtitulo("TODAS AS LOJAS");
        List<Loja> lojas = lService.listarAtivas();
        
        if (lojas.isEmpty()) {
            MenuUtil.exibirInfo("Nenhuma loja cadastrada ainda.");
        } else {
            for (Loja l : lojas) {
                System.out.println(l.getResumo());
            }
            System.out.println("\nTotal: " + lojas.size() + " lojas");
            
            if (MenuUtil.confirmar(sc, "\nDeseja ver os produtos de alguma loja?")) {
                int lojaId = ValidadorUtil.solicitarInteiro(sc, "ID da loja: ");
                listarProdutosDaLoja(lojaId);
            }
        }
        
        MenuUtil.pausar(sc);
    }

    // ========== MENU CARRINHO ==========
    private static void menuCarrinho() {
        boolean voltar = false;
        
        while (!voltar) {
            MenuUtil.exibirTitulo("CARRINHO DE COMPRAS");
            
            if (carrinhoService.estaVazio()) {
                MenuUtil.exibirInfo("Seu carrinho esta vazio!");
                MenuUtil.pausar(sc);
                return;
            }
            
            System.out.println(carrinhoService.exibirCarrinho());
            
            String[] opcoes = {
                "1)  Finalizar Compra",
                "2)  Remover Item",
                "3)  Atualizar Quantidade",
                "4)  Limpar Carrinho",
                "0)  Voltar"
            };
            MenuUtil.exibirOpcoes(opcoes);
            
            int opt = MenuUtil.solicitarOpcao(sc);
            
            switch(opt) {
                case 1:
                    finalizarCompra();
                    voltar = true;
                    break;
                case 2:
                    removerItemCarrinho();
                    break;
                case 3:
                    atualizarQuantidadeCarrinho();
                    break;
                case 4:
                    if (MenuUtil.confirmar(sc, "Tem certeza que deseja limpar o carrinho?")) {
                        carrinhoService.limpar();
                        MenuUtil.exibirSucesso("Carrinho limpo!");
                        voltar = true;
                    }
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    MenuUtil.exibirErro("Opcao invalida!");
                    MenuUtil.pausar(sc);
            }
        }
    }

    private static void removerItemCarrinho() {
        int produtoId = ValidadorUtil.solicitarInteiro(sc, "ID do produto para remover: ");
        
        if (carrinhoService.removerProduto(produtoId)) {
            MenuUtil.exibirSucesso("Produto removido do carrinho!");
        } else {
            MenuUtil.exibirErro("Produto nao encontrado no carrinho!");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void atualizarQuantidadeCarrinho() {
        int produtoId = ValidadorUtil.solicitarInteiro(sc, "ID do produto: ");
        int quantidade = ValidadorUtil.solicitarQuantidade(sc, "Nova quantidade: ");
        
        if (carrinhoService.atualizarQuantidade(produtoId, quantidade)) {
            MenuUtil.exibirSucesso("Quantidade atualizada!");
        } else {
            MenuUtil.exibirErro("Nao foi possivel atualizar a quantidade!");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void finalizarCompra() {
        // Validar estoque
        if (!carrinhoService.validarEstoque()) {
            MenuUtil.exibirErro("Alguns produtos nao tem estoque suficiente!");
            MenuUtil.pausar(sc);
            return;
        }
        
        double total = carrinhoService.calcularTotal();
        Usuario usuario = sessao.getUsuarioLogado();
        
        MenuUtil.exibirSubtitulo("FINALIZAR COMPRA");
        System.out.println("Total da compra: " + MenuUtil.formatarValor(total));
        System.out.println("Seu saldo: " + MenuUtil.formatarValor(usuario.getSaldo()));
        
        if (usuario.getSaldo() < total) {
            MenuUtil.exibirErro("Saldo insuficiente!");
            if (MenuUtil.confirmar(sc, "Deseja adicionar saldo?")) {
                double valor = ValidadorUtil.solicitarValor(sc, "Valor para adicionar: R$ ");
                uService.adicionarSaldo(usuario.getId(), valor);
                MenuUtil.exibirSucesso("Saldo adicionado com sucesso!");
            } else {
                MenuUtil.pausar(sc);
                return;
            }
        }
        
        // Criar pedido
        Pedido pedido = new Pedido(sessao.getUsuarioLogadoId(), carrinhoService.getItens(), total);
        pedidoService.cadastrar(pedido);
        
        // Processar pagamento
        MenuUtil.exibirSubtitulo("PAGAMENTO");
        String[] formasPagamento = {
            "1 - Cartao de Credito",
            "2 - Boleto",
            "3 - PIX"
        };
        
        for (String forma : formasPagamento) {
            System.out.println(forma);
        }
        
        int opcaoPagamento = MenuUtil.solicitarOpcao(sc);
        String formaPagamento = "";
        
        switch(opcaoPagamento) {
            case 1:
                formaPagamento = "Cartao de Credito";
                break;
            case 2:
                formaPagamento = "Boleto";
                break;
            case 3:
                formaPagamento = "PIX";
                break;
            default:
                formaPagamento = "Cartao de Credito";
        }
        
        Pagamento pagamento = new Pagamento(total, formaPagamento, pedido.getId());
        
        if (formaPagamento.equals("Cartao de Credito")) {
            if (MenuUtil.confirmar(sc, "Deseja parcelar?")) {
                int parcelas = ValidadorUtil.solicitarInteiro(sc, "Numero de parcelas: ");
                pagamentoService.processarParcelado(pagamento, parcelas);
            } else {
                pagamentoService.processar(pagamento);
            }
        } else {
            pagamentoService.processar(pagamento);
        }
        
        // Deduzir saldo
        uService.removerSaldo(usuario.getId(), total);
        
        // Atualizar estoque
        for (CarrinhoItem item : carrinhoService.getItens()) {
            pService.venderProduto(item.getProduto().getId(), item.getQuantidade());
        }
        
        // Marcar pedido como pago
        pedidoService.marcarComoPago(pedido.getId(), pagamento.getId());
        
        // Limpar carrinho
        carrinhoService.limpar();
        
        MenuUtil.exibirSucesso("Compra realizada com sucesso!");
        MenuUtil.exibirInfo("Pedido #" + pedido.getId() + " criado!");
        System.out.println("Novo saldo: " + MenuUtil.formatarValor(sessao.getUsuarioLogado().getSaldo()));
        
        MenuUtil.pausar(sc);
    }

    // ========== MENU PEDIDOS ==========
    private static void menuPedidos() {
        boolean voltar = false;
        
        while (!voltar) {
            MenuUtil.exibirTitulo("MEUS PEDIDOS");
            
            String[] opcoes = {
                "1)  Ver Todos os Pedidos",
                "2)  Ver Pedido por ID",
                "3)  Cancelar Pedido",
                "0)  Voltar"
            };
            MenuUtil.exibirOpcoes(opcoes);
            
            int opt = MenuUtil.solicitarOpcao(sc);
            
            switch(opt) {
                case 1:
                    listarMeusPedidos();
                    break;
                case 2:
                    verDetalhePedido();
                    break;
                case 3:
                    cancelarPedido();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    MenuUtil.exibirErro("Opcao invalida!");
                    MenuUtil.pausar(sc);
            }
        }
    }

    private static void listarMeusPedidos() {
        MenuUtil.exibirSubtitulo("HISTORICO DE PEDIDOS");
        List<Pedido> pedidos = pedidoService.buscarPorUsuario(sessao.getUsuarioLogadoId());
        
        if (pedidos.isEmpty()) {
            MenuUtil.exibirInfo("Voce ainda nao fez nenhum pedido.");
        } else {
            for (Pedido p : pedidos) {
                System.out.println(p.getResumo());
            }
            System.out.println("\nTotal: " + pedidos.size() + " pedidos");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void verDetalhePedido() {
        int pedidoId = ValidadorUtil.solicitarInteiro(sc, "ID do pedido: ");
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        
        if (pedido == null || pedido.getUsuarioId() != sessao.getUsuarioLogadoId()) {
            MenuUtil.exibirErro("Pedido nao encontrado!");
        } else {
            System.out.println(pedido.getResumoDetalhado());
        }
        
        MenuUtil.pausar(sc);
    }

    private static void cancelarPedido() {
        listarMeusPedidos();
        
        int pedidoId = ValidadorUtil.solicitarInteiro(sc, "\nID do pedido para cancelar: ");
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        
        if (pedido == null || pedido.getUsuarioId() != sessao.getUsuarioLogadoId()) {
            MenuUtil.exibirErro("Pedido nao encontrado!");
        } else if ("Enviado".equals(pedido.getStatus()) || "Entregue".equals(pedido.getStatus())) {
            MenuUtil.exibirErro("Nao e possivel cancelar pedidos ja enviados ou entregues!");
        } else {
            if (MenuUtil.confirmar(sc, "Tem certeza que deseja cancelar este pedido?")) {
                pedidoService.cancelarPedido(pedidoId);
                
                // Devolver saldo ao usuário
                uService.adicionarSaldo(sessao.getUsuarioLogadoId(), pedido.getTotal());
                
                MenuUtil.exibirSucesso("Pedido cancelado e saldo devolvido!");
            }
        }
        
        MenuUtil.pausar(sc);
    }

    // ========== MENU CONTA ==========
    private static void menuConta() {
        boolean voltar = false;
        
        while (!voltar) {
            Usuario usuario = sessao.getUsuarioLogado();
            
            MenuUtil.exibirTitulo("MINHA CONTA");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Saldo: " + MenuUtil.formatarValor(usuario.getSaldo()));
            
            String[] opcoes = {
                "1)  Adicionar Saldo",
                "2)  Alterar Senha",
                "3)  Editar Perfil",
                "0)  Voltar"
            };
            MenuUtil.exibirOpcoes(opcoes);
            
            int opt = MenuUtil.solicitarOpcao(sc);
            
            switch(opt) {
                case 1:
                    adicionarSaldo();
                    break;
                case 2:
                    alterarSenha();
                    break;
                case 3:
                    editarPerfil();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    MenuUtil.exibirErro("Opcao invalida!");
                    MenuUtil.pausar(sc);
            }
        }
    }

    private static void adicionarSaldo() {
        MenuUtil.exibirSubtitulo("ADICIONAR SALDO");
        double valor = ValidadorUtil.solicitarValor(sc, "Valor para adicionar: R$ ");
        
        if (uService.adicionarSaldo(sessao.getUsuarioLogadoId(), valor)) {
            MenuUtil.exibirSucesso("Saldo adicionado com sucesso!");
            System.out.println("Novo saldo: " + MenuUtil.formatarValor(sessao.getUsuarioLogado().getSaldo()));
        } else {
            MenuUtil.exibirErro("Nao foi possivel adicionar saldo.");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void alterarSenha() {
        MenuUtil.exibirSubtitulo("ALTERAR SENHA");
        
        String senhaAtual = ValidadorUtil.solicitarSenha(sc, "Senha atual: ");
        
        if (!sessao.getUsuarioLogado().autenticar(senhaAtual)) {
            MenuUtil.exibirErro("Senha atual incorreta!");
            MenuUtil.pausar(sc);
            return;
        }
        
        String novaSenha = ValidadorUtil.solicitarSenha(sc, "Nova senha: ");
        String confirmaSenha = ValidadorUtil.solicitarSenha(sc, "Confirme a nova senha: ");
        
        if (!novaSenha.equals(confirmaSenha)) {
            MenuUtil.exibirErro("As senhas nao conferem!");
        } else {
            sessao.getUsuarioLogado().alterarSenha(novaSenha);
            uService.alterar(sessao.getUsuarioLogado());
            MenuUtil.exibirSucesso("Senha alterada com sucesso!");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void editarPerfil() {
        MenuUtil.exibirSubtitulo("EDITAR PERFIL");
        Usuario usuario = sessao.getUsuarioLogado();
        
        System.out.println("Deixe em branco para manter o valor atual.\n");
        
        System.out.print("Novo nome [" + usuario.getNome() + "]: ");
        String nome = sc.nextLine().trim();
        
        if (!nome.isEmpty()) {
            usuario.atualizar(nome);
            uService.alterar(usuario);
            MenuUtil.exibirSucesso("Perfil atualizado com sucesso!");
        } else {
            MenuUtil.exibirInfo("Nenhuma alteracao realizada.");
        }
        
        MenuUtil.pausar(sc);
    }

    // ========== MENU SUPORTE ==========
    private static void menuSuporte() {
        boolean voltar = false;
        
        while (!voltar) {
            MenuUtil.exibirTitulo("SUPORTE");
            
            String[] opcoes = {
                "1)  Abrir Ticket de Suporte",
                "2)  Meus Tickets",
                "3)  Ver Ticket por ID",
                "0)  Voltar"
            };
            MenuUtil.exibirOpcoes(opcoes);
            
            int opt = MenuUtil.solicitarOpcao(sc);
            
            switch(opt) {
                case 1:
                    abrirTicketSuporte();
                    break;
                case 2:
                    listarMeusTickets();
                    break;
                case 3:
                    verDetalheTicket();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    MenuUtil.exibirErro("Opcao invalida!");
                    MenuUtil.pausar(sc);
            }
        }
    }

    private static void abrirTicketSuporte() {
        MenuUtil.exibirSubtitulo("ABRIR TICKET DE SUPORTE");
        
        String assunto = ValidadorUtil.solicitarTexto(sc, "Assunto: ");
        String mensagem = ValidadorUtil.solicitarTexto(sc, "Mensagem: ");
        
        Suporte ticket = new Suporte(assunto, mensagem, sessao.getUsuarioLogadoId());
        suporteService.cadastrar(ticket);
        
        MenuUtil.exibirSucesso("Ticket criado com sucesso! ID: " + ticket.getId());
        MenuUtil.exibirInfo("Em breve nossa equipe entrara em contato.");
        MenuUtil.pausar(sc);
    }

    private static void listarMeusTickets() {
        MenuUtil.exibirSubtitulo("MEUS TICKETS");
        List<Suporte> tickets = suporteService.buscarPorUsuario(sessao.getUsuarioLogadoId());
        
        if (tickets.isEmpty()) {
            MenuUtil.exibirInfo("Voce nao possui nenhum ticket de suporte.");
        } else {
            for (Suporte s : tickets) {
                System.out.println(s.getResumo());
            }
            System.out.println("\nTotal: " + tickets.size() + " tickets");
        }
        
        MenuUtil.pausar(sc);
    }

    private static void verDetalheTicket() {
        int ticketId = ValidadorUtil.solicitarInteiro(sc, "ID do ticket: ");
        Suporte ticket = suporteService.buscarPorId(ticketId);
        
        if (ticket == null || ticket.getUsuarioId() != sessao.getUsuarioLogadoId()) {
            MenuUtil.exibirErro("Ticket nao encontrado!");
        } else {
            MenuUtil.exibirSubtitulo("DETALHES DO TICKET");
            System.out.println(ticket.getResumoDetalhado());
        }
        
        MenuUtil.pausar(sc);
    }
}
