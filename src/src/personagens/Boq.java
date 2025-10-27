package jogo.personagens;

public class Boq extends Personagem {

    public Boq() {
        super("Boq", 100, 10, 12, 1);  // HP alto, ataque médio, defesa alta, nível 1
    }

    public Boq(Boq outro) {
        super(outro);
    }

    @Override
    public void usarHabilidade(Personagem alvo) {
        System.out.println(nome + " usa Armadura Protetora e aumenta sua defesa!");
        this.defesa += 5;
        System.out.println(nome + " agora tem defesa " + this.defesa + "!");
    }
}
