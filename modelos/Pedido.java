package modelos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemPedido> itens;
    private FormaDePagamento formaDePagamento;
    private double valorPago;
    private double troco;

      
    public double getTroco() {
        return troco;
    }

   

    public Pedido(){
        this.itens = new ArrayList<>();
        this.valorPago = 0.0;
        this.troco = 0.0;
        this.formaDePagamento = null;
    }

    public void adicionarItem (ItemPedido item) {
        itens.add(item);
    }

    public boolean pedidoVazio(){
        return itens.isEmpty();
    }

     public double calcularTotal (){
        double total = 0;
        for (ItemPedido item:itens){
            total +=item.calcularSubTotal();
        }
        return total;
    }

     public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public void setFormaDePagamento (FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

     public void calcularTroco (){
        this.troco = valorPago - calcularTotal();
    }

    public void mostrarPedido(){
        System.out.println("RESUMO DO PEDIDO: ");
        for(ItemPedido item : itens) {
            System.out.println(item.getDescricao());
        }

        System.out.println("Total: R$ " + calcularTotal());
            if (formaDePagamento != null) {
            System.out.println("Forma de pagamento: " + formaDePagamento.getDescricao());

            if (formaDePagamento.getDescricao().equalsIgnoreCase("Dinheiro")) {
                System.out.println("Valor pago: R$ " + valorPago);
                System.out.println("Troco: R$ " + troco);
            }
        } else {
            System.out.println("Forma de pagamento: não definida");
        }
    }   
}
