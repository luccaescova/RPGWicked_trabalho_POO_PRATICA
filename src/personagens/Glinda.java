package jogo.personagens;

public class Glinda extends Personagem {

    public Glinda() {
        super("Glinda", 80, 12, 8, 1);
    }

    public Glinda(Glinda outro) {
        super(outro);
    }

    @Override
    public void usarHabilidade(Personagem alvo) {
        System.out.println(nome + " usa Feitiço Encantador!");
        int dano = this.ataque + 3 - alvo.defesa;
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
    }
}
