package modelos;

public class ItemPedido {
   private Produto produto;
   private int quantidade;

   public ItemPedido(Produto produto, int quantidade) {
    this.produto = produto;
    this.quantidade = quantidade;
   }

   public double calcularSubTotal (){
    return produto.getPreco()*quantidade;
   }

   public String getDescricao (){
    return produto.getNome() + " x" + quantidade + " = R$ " + calcularSubTotal();
   }
    
}
