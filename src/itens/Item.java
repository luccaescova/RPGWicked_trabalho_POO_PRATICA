package itens;

public class Item implements Cloneable, Comparable<Item> {
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

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void usar() {
        if (quantidade > 0) quantidade--;
    }

    public void aumentarQuantidade(int qtd) {
        quantidade += qtd;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) return false;
        Item outro = (Item) obj;
        return this.nome.equalsIgnoreCase(outro.nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }

    @Override
    public int compareTo(Item outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public Item clone() {
        return new Item(nome, descricao, efeito, quantidade);
    }

    @Override
    public String toString() {
        return nome + " x" + quantidade + " (" + efeito + ")";
    }
}
