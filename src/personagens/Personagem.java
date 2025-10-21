package jogo.personagens;

import jogo.itens.Inventario;

public abstract class Personagem implements Cloneable {
    protected String nome;
    protected int pontosVida;
    protected int vidaMaxima;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    private Inventario inventario;

    public Personagem() {
        this.nome = "Desconhecido";
        this.vidaMaxima = 100;
        this.pontosVida = vidaMaxima;
        this.ataque = 10;
        this.defesa = 5;
        this.nivel = 1;
        this.inventario = new Inventario();
    }

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.vidaMaxima = pontosVida;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    public Personagem(Personagem outro) {
        this.nome = outro.getNome();
        this.vidaMaxima = outro.getVidaMaxima();
        this.pontosVida = outro.getPontosVida();
        this.ataque = outro.getAtaque();
        this.defesa = outro.getDefesa();
        this.nivel = outro.nivel;
        this.inventario = outro.getInventario().clone();
    }

    public String getNome() { return nome; }
    public int getPontosVida() { return pontosVida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public Inventario getInventario() { return inventario; }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) this.nome = nome;
    }

    public void setPontosVida(int pontosVida) {
        if (pontosVida > vidaMaxima) {
            this.pontosVida = vidaMaxima;
        } else if (pontosVida < 0) {
            this.pontosVida = 0;
        } else {
            this.pontosVida = pontosVida;
        }
    }

    public void setVidaMaxima(int vidaMaxima) {
        if (vidaMaxima > 0) {
            this.vidaMaxima = vidaMaxima;
            if (this.pontosVida > vidaMaxima) {
                this.pontosVida = vidaMaxima;
            }
        }
    }

    public void setAtaque(int ataque) {
        if (ataque >= 0) this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        if (defesa >= 0) this.defesa = defesa;
    }

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    public void atacar(Personagem alvo, int rolagemDado) {
        int dano = (this.ataque + rolagemDado) - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
        System.out.println(this.nome + " atacou " + alvo.getNome() + " causando " + dano + " de dano!");
    }

    public void receberDano(int dano) {
        setPontosVida(this.pontosVida - dano);
        System.out.println(this.nome + " agora tem " + this.pontosVida + " de HP.");
    }

    public abstract void usarHabilidade(Personagem alvo);

    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida + "/" + vidaMaxima + ", ATK=" + ataque + ", DEF=" + defesa + ", NIVEL=" + nivel + "]";
    }

    @Override
    public Personagem clone() {
        try {
            Personagem copia = (Personagem) super.clone();
            copia.inventario = this.inventario.clone();
            return copia;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
