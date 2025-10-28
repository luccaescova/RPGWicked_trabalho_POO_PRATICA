package jogo.itens;

public class Item implements Comparable<Item>, Cloneable {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = Math.max(1, quantidade); // evita itens com 0 ou quantidade negativa
    }

    // --- Getters e Setters ---
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEfeito() {
        return efeito;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    // --- Métodos Utilitários ---
    public void usar() {
        // Este método pode ser sobrescrito por subclasses (itens especiais)
        System.out.println("Você usou " + nome + "!");
        if (quantidade > 1) {
            quantidade--;
        } else {
            quantidade = 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item i = (Item) o;
        return nome.equalsIgnoreCase(i.nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }

    @Override
    public int compareTo(Item i) {
        return nome.compareToIgnoreCase(i.nome);
    }

    @Override
    public Item clone() {
        // Retorna uma nova instância com os mesmos dados
        return new Item(nome, descricao, efeito, quantidade);
    }

    @Override
    public String toString() {
        return nome + " (" + quantidade + "x) - " + descricao + " | Efeito: " + efeito;
    }
}
