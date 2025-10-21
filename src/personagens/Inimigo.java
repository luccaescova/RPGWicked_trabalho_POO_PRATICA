package jogo.personagens;

public class Inimigo extends Personagem {

    public Inimigo(String nome, int hp, int atk, int def, int nivel) {
        super(nome, hp, atk, def, nivel);
    }

    public Inimigo(Inimigo outro) {
        super(outro);
    }

    @Override
    public void usarHabilidade(Personagem alvo) {
        System.out.println(nome + " ataca ferozmente!");
        int dano = this.ataque - alvo.defesa;
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
    }
}
