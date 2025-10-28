package jogo.personagens;

public class Fiyero extends Personagem {

    public Fiyero() {
        super("Fiyero", 100, 14, 10, 1);
    }

    public Fiyero(Fiyero outro) {
        super(outro);
    }

    @Override
    public void usarHabilidade(Personagem alvo) {
        System.out.println(nome + " desfere um ataque poderoso!");
        int dano = this.ataque + 2 - alvo.defesa;
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
    }
}
