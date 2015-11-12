package br.usjt.cervejap3.model;

import java.io.Serializable;

/**
 * Created by asbonato on 9/5/15.
 */
public class Cerveja implements Comparable<Cerveja>, Serializable{
    private String nome;
    private String imagem;
    private double preco;
    private String estilo;
    private String cor;
    private String pais;
    public static final String NAO_ENCONTRADA = "Não encontrada.";

    public Cerveja(String nome, String estilo, String cor, String pais, String imagem, double preco) {
        this.nome = nome;
        this.imagem = imagem;
        this.preco = preco;
        this.estilo = estilo;
        this.cor = cor;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public double getPreco() {
        return preco;
    }

    public String getEstilo() {
        return estilo;
    }

    public String getCor() {
        return cor;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "br.usjt.cervejap1.Cerveja{" +
                "nome='" + nome + '\'' +
                ", imagem='" + imagem + '\'' +
                ", preco='" + preco + '\'' +
                ", estilo='" + estilo + '\'' +
                ", cor='" + cor + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }

    @Override
    public int compareTo(Cerveja cerveja) {
        if (nome.equals(cerveja.getNome())
                && estilo.equals(cerveja.getEstilo())
                && cor.equals(cerveja.getCor())
                && pais.equals(cerveja.getPais())) {
            return 0;
        }
        return this.getNome().compareTo(cerveja.getNome());
    }
}
