package personagens;

public class Glinda extends Personagem {

    public Glinda() {
        super("Glinda", 80, 12, 8, 1);
    }

    public Glinda(Glinda outro) {
        super(outro);
    }

    @Override
    public void usarHabilidade(Personagem alvo) {
        System.out.println(nome + " usa feitiço encantador!");
        alvo.pontosVida -= ataque + 3;
    }
}
