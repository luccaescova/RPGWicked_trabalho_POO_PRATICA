package personagens;

import itens.Inventario;

public abstract class Personagem implements Cloneable {
    protected String nome;
    protected int pontosVida;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Inventario inventario;

    public Personagem() {
        this.nome = "Desconhecido";
        this.pontosVida = 100;
        this.ataque = 10;
        this.defesa = 5;
        this.nivel = 1;
        this.inventario = new Inventario();
    }

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.inventario = outro.inventario.clone();
    }

    public abstract void usarHabilidade(Personagem alvo);

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida + ", ATK=" + ataque + ", DEF=" + defesa + ", NIVEL=" + nivel + "]";
    }
}
