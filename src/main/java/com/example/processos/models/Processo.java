package com.example.processos.models;

public class Processo {
    private Integer id;
    private String numero;
    private String reu;
    private String autor;
    private Double valorPedido;
    private String assunto;

    public Processo() {
    }

    public Processo(Integer id, String numero, String reu, String autor, Double valorPedido, String assunto) {
        this.id = id;
        this.numero = numero;
        this.reu = reu;
        this.autor = autor;
        this.valorPedido = valorPedido;
        this.assunto = assunto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReu() {
        return reu;
    }

    public void setReu(String reu) {
        this.reu = reu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(Double valorPedido) {
        this.valorPedido = valorPedido;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return
                "Código: " + numero +
                "\nRéu: " + reu +
                "\nAutor: " + autor +
                "\nValor Pedido: R$" + valorPedido +
                "\nAssunto: " + assunto;
    }
}
