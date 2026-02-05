package visualizar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelos.FormaDePagamento;
import modelos.ItemPedido;
import modelos.Pedido;
import modelos.Produto;

public class MenuView {
    private Scanner scanner = new Scanner(System.in);
    private Pedido pedido = new Pedido();
    private List<Produto> cardapio = new ArrayList<>();
    private List<FormaDePagamento> formaDePagamentos = new ArrayList<>();


    public MenuView(){
        carregarCardapio();
        carregarFormasDePagamento();
    }

    private void carregarCardapio() {
        cardapio.add(new Produto(1,"Pastel", 5.50));
        cardapio.add(new Produto(2, "Pao de queijo", 4.50));
        cardapio.add(new Produto(3, "Enroladinha de salsicha", 6.00));
        cardapio.add(new Produto(4, "Pizza", 8.00));
        cardapio.add(new Produto(5, "Bolo de chocolate", 3.90));
        cardapio.add(new Produto(6, "Café com leite", 4.00));
        cardapio.add(new Produto(7, "Refrigerante (lata)", 5.00));
        cardapio.add(new Produto(8, "Agua", 3.00));
    }

      private void carregarFormasDePagamento(){
            formaDePagamentos.add(new FormaDePagamento(1, "Dinheiro"));
            formaDePagamentos.add(new FormaDePagamento(2, "Cartao de debito"));
            formaDePagamentos.add(new FormaDePagamento(3,"Cartao de credito"));
            formaDePagamentos.add(new FormaDePagamento(4, "Pix"));
        }

    public void iniciarSistema (){
        int opcao;

        do{
            mostrarMenu();
            opcao = scanner.nextInt();

            switch (opcao){
                case 1: 
                    mostrarCardapio();
                    adicionarProduto();
                    break;
                case 2: 
                    pedido.mostrarPedido();
                    break;
                case 3:
                    finalizarPedido();
                    break;
                case 0: 
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção invalida.");
            }
        }while (opcao != 0);
        }

        private void mostrarMenu(){
            System.out.println("\nMenu Da Lanchonete: ");
            System.out.println("1- Fazer pedido");
            System.out.println("2- Ver pedido");
            System.out.println("3- Finalizar pedido");
            System.out.println("0- Sair");
            System.out.println("Escolha uma opcao: ");
        }

        private void mostrarCardapio(){
            System.out.println("\nCardapio: ");
            for(Produto p : cardapio){
                System.out.println(p);
            }
        }

        private void adicionarProduto(){
            System.out.println("\nDigite o codigo do produto: ");
            int id = scanner.nextInt();

            Produto produtoEscolhido = null;

            for(Produto p: cardapio){
                if (p.getId() == id) {
                    produtoEscolhido = p;
                    break;
                }
            }
            if (produtoEscolhido == null) {
                System.out.println("Produto nao encontrado. ");
                return;
            }

            System.out.println("Quantidade: ");
            int quantidadeEscolhida = scanner.nextInt();

            ItemPedido item = new ItemPedido(produtoEscolhido, quantidadeEscolhida);
            pedido.adicionarItem(item);
            System.out.println("Pedido adicionado. ");
        }

        private boolean escolherFormaPagamento(){
            System.out.println("\nEscolha a forma de pagamento: ");

            for (FormaDePagamento fp:formaDePagamentos){
                    System.out.println(fp.getCodigo()+ " - " + fp.getDescricao());
            }

            int opcao = scanner.nextInt();

            for (FormaDePagamento fp:formaDePagamentos){
                if(fp.getCodigo() == opcao){
                    pedido.setFormaDePagamento(fp);

                    //dinheiro, pedir troco
                    if (fp.getDescricao().equalsIgnoreCase("Dinheiro")){
                        System.out.println("Informe o valor pago: R$");
                        double valorPago = scanner.nextDouble();

                            if (valorPago < pedido.calcularTotal()) {
                                System.out.println("Valor insuficiente.");
                                pedido.setFormaDePagamento(null);
                                return false;
                            }

                            pedido.setValorPago(valorPago);
                            pedido.calcularTroco();
                    }
                    return true;
                }
            }
                System.out.println("Forma de pagamento inválida");
                return false;
            }
        

        private void finalizarPedido(){
            if(pedido.pedidoVazio()){
                System.out.println("Pedido vazio. ");
                return;
            }

            if(!escolherFormaPagamento()){
                System.out.println("Pedido nao finalizado.");
                return;
            }
            pedido.mostrarPedido();
            System.out.println("\nObrigada, volte sempre! ");
     }                 
 }
     

