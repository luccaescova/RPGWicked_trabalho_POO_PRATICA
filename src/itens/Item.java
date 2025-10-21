package itens;

public class Item implements Comparable<Item>, Cloneable {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Item i) {
            return nome.equalsIgnoreCase(i.nome);
        }
        return false;
    }

    @Override
    public int compareTo(Item i) {
        return nome.compareToIgnoreCase(i.nome);
    }

    @Override
    public Item clone() {
        return new Item(nome, descricao, efeito, quantidade);
    }

    @Override
    public String toString() {
        return nome + " (" + quantidade + "x) - " + descricao;
    }
}