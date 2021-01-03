/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrls;

/**
 *
 * @author we
 */

public class ItemVenda {
    
    private int quantidade;
    private String descricao;
    private double preco;
            
    
    public ItemVenda( String desc, int qtd, double preco)
    {
        this.descricao = desc;
        this.quantidade = qtd;
        this.preco = preco;
        
        
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
    
}
