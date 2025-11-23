# 🛒 MercadoLike - Sistema de E-commerce em Java POO

## 📋 Descrição

O **MercadoLike** é um sistema completo de e-commerce desenvolvido em Java com Programação Orientada a Objetos (POO). É uma aplicação de console interativa que simula um marketplace completo com funcionalidades de compra, venda, gerenciamento de lojas e muito mais.

## ✨ Funcionalidades

### 🔐 Autenticação e Conta
- Criar conta de usuário
- Login/Logout com email e senha
- Gerenciar perfil
- Alterar senha
- Sistema de saldo virtual

### 🏪 Gestão de Lojas
- Criar sua própria loja virtual
- Gerenciar múltiplas lojas
- Adicionar, editar e remover produtos
- Visualizar estatísticas da loja
- Controlar estoque de produtos

### 🛍️ Catálogo e Compras
- Navegar por todos os produtos
- Buscar produtos por nome
- Filtrar produtos por faixa de preço
- Ver produtos mais vendidos
- Visualizar detalhes completos de produtos
- Sistema de carrinho de compras

### 🛒 Carrinho de Compras
- Adicionar/remover produtos
- Atualizar quantidades
- Visualizar total em tempo real
- Validação automática de estoque

### 💳 Sistema de Pagamento
- Múltiplas formas de pagamento:
  - Cartão de Crédito (com opção de parcelamento)
  - Boleto
  - PIX
- Processamento automático de pagamentos
- Dedução automática de saldo

### 📦 Pedidos
- Histórico completo de pedidos
- Acompanhamento de status
- Cancelamento de pedidos (quando permitido)
- Visualização detalhada de cada pedido
- Devolução automática de saldo em caso de cancelamento

### 💬 Suporte
- Sistema de tickets de suporte
- Abrir tickets com assunto e descrição
- Visualizar histórico de tickets
- Acompanhar status (pendente/resolvido)

## 🎓 Conceitos de POO Aplicados

### 1. **Herança**
```java
Entidade (classe abstrata base)
├── Usuario
├── Loja
├── Produto
├── Pagamento
├── Suporte
└── Pedido
```

### 2. **Polimorfismo**
- Método abstrato `getResumo()` implementado diferente em cada entidade
- Sobrecarga de métodos e construtores

### 3. **Encapsulamento**
- Atributos privados com getters e setters
- Validações internas nas classes

### 4. **Padrões de Projeto**
- **Repository Pattern**: Separação da lógica de acesso a dados
- **Service Pattern**: Camada de lógica de negócios
- **Singleton**: Gerenciamento de sessão única

### 5. **Interfaces Genéricas**
```java
IRepository<T extends Entidade>
IService<T extends Entidade>
```

## 📁 Estrutura do Projeto

```
Projeto_Mercado_Livre/
├── Entidade/
│   ├── Entidade.java          (Classe abstrata base)
│   ├── Usuario.java           (Gerenciamento de usuários)
│   ├── Loja.java              (Gestão de lojas)
│   ├── Produto.java           (Catálogo de produtos)
│   ├── Pedido.java            (Sistema de pedidos)
│   ├── Pagamento.java         (Processamento de pagamentos)
│   ├── Suporte.java           (Tickets de suporte)
│   ├── CarrinhoItem.java      (Itens do carrinho)
│   └── Sessao.java            (Gerenciamento de sessão)
│
├── Repository/
│   ├── IRepository.java       (Interface genérica)
│   ├── UsuarioRepository.java
│   ├── LojaRepository.java
│   ├── ProdutoRepository.java
│   └── PedidoRepository.java
│
├── Service/
│   ├── IService.java          (Interface genérica)
│   ├── UsuarioService.java    (Autenticação, saldo)
│   ├── LojaService.java       (Gestão de lojas)
│   ├── ProdutoService.java    (Busca, filtros)
│   ├── PedidoService.java     (Gerenciamento de pedidos)
│   ├── PagamentoService.java  (Processamento)
│   ├── SuporteService.java    (Tickets)
│   └── CarrinhoService.java   (Carrinho de compras)
│
├── Util/
│   ├── MenuUtil.java          (Utilitários de menu)
│   └── ValidadorUtil.java     (Validações)
│
├── MercadoLike.java           (Classe principal)
├── executar.sh                (Script de execução)
└── README.md                  (Este arquivo)
```

## 🚀 Como Compilar

```bash
cd /app/Projeto_Mercado_Livre
javac -encoding UTF-8 -cp . MercadoLike.java
```

## ▶️ Como Executar

### Opção 1: Usando o script
```bash
cd /app/Projeto_Mercado_Livre
bash executar.sh
```

### Opção 2: Comando direto
```bash
cd /app/Projeto_Mercado_Livre
java -cp . MercadoLike
```

## 📖 Como Usar

### 1. Primeira Execução
1. Execute o programa
2. Escolha opção **2 - Criar Conta**
3. Preencha seus dados (nome, email, senha)
4. Faça login com suas credenciais

### 2. Adicionar Saldo
1. No menu principal, escolha **5 - Minha Conta**
2. Escolha **1 - Adicionar Saldo**
3. Digite o valor desejado

### 3. Criar uma Loja
1. No menu principal, escolha **2 - Minhas Lojas**
2. Escolha **1 - Criar Nova Loja**
3. Preencha nome e descrição da loja

### 4. Adicionar Produtos
1. Em **Minhas Lojas**, escolha **3 - Gerenciar Loja**
2. Selecione sua loja pelo ID
3. Escolha **1 - Adicionar Produto**
4. Preencha os dados do produto

### 5. Comprar Produtos
1. No menu principal, escolha **1 - Explorar Produtos**
2. Navegue pelos produtos disponíveis
3. Adicione produtos ao carrinho
4. Vá para **3 - Carrinho de Compras**
5. Escolha **1 - Finalizar Compra**
6. Selecione a forma de pagamento

## 💡 Recursos Avançados

### Busca e Filtros
- **Buscar por nome**: Localiza produtos rapidamente
- **Filtrar por preço**: Define faixa de valores
- **Produtos com estoque**: Exibe apenas disponíveis
- **Mais vendidos**: Ranking dos produtos

### Gestão de Pedidos
- Visualize todos os seus pedidos
- Acompanhe o status de cada um
- Cancele pedidos (quando permitido)
- Receba reembolso automático

### Sistema de Suporte
- Abra tickets para dúvidas ou problemas
- Acompanhe o status dos seus tickets
- Visualize histórico completo

## 🛡️ Validações Implementadas

- ✅ Email no formato correto
- ✅ Senha mínima de 6 caracteres
- ✅ Valores monetários positivos
- ✅ Quantidades válidas
- ✅ Estoque disponível antes da compra
- ✅ Saldo suficiente para compras
- ✅ Verificação de permissões (dono da loja)

## 🎯 Diferenciais

1. **Código Organizado**: Separação em camadas (Entidade, Repository, Service, Util)
2. **Validações Robustas**: Tratamento de erros em todos os inputs
3. **Interface Amigável**: Menus intuitivos e mensagens claras
4. **Funcionalidades Completas**: Sistema end-to-end funcional
5. **POO Aplicada**: Uso correto de todos os conceitos OO
6. **Código Reutilizável**: Classes genéricas e utilitárias

## 🔧 Requisitos

- **Java JDK**: 11 ou superior
- **Terminal/Console**: Para executar a aplicação

## 📝 Notas Importantes

- Os dados são armazenados em memória (não persistem após fechar o programa)
- O sistema é single-user por execução (uma sessão por vez)
- Todos os valores são em Real (R$)
- O estoque é atualizado automaticamente após vendas

## 👨‍💻 Desenvolvimento

Projeto desenvolvido utilizando:
- ✅ Java POO puro
- ✅ Padrões de projeto
- ✅ Boas práticas de programação
- ✅ Código limpo e documentado

## 🎓 Aprendizados

Este projeto demonstra:
- Herança e polimorfismo
- Interfaces e classes abstratas
- Generics em Java
- Coleções (List, ArrayList)
- Streams e Lambdas
- Padrões Repository e Service
- Singleton Pattern
- Validações e tratamento de erros
- Estruturação de projetos Java

---

**Desenvolvido com 💙 usando Java POO**
