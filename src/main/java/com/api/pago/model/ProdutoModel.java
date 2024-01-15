package com.api.pago.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProdutoModel implements Serializable {

    private static final long serialVersionUID = -2949414671172713476L;

    private String nome_produto;
    private int quantidade;
    private String url_imagem;
    private String descricao;
    private double preco;
}
