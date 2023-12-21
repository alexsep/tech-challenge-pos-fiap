package br.com.fiap.techchallenge.model;

import br.com.fiap.techchallenge.model.enums.CategoriaProduto;

import java.math.BigDecimal;
import java.util.List;

public class Produto {

    private String nome;
    private BigDecimal valor;
    private String descricao;
    private CategoriaProduto categoria;
    private List<String> imagens;
}
