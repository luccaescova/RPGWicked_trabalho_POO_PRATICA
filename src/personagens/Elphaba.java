package personagens;

public class Elphaba extends Personagem {

    public Elphaba() {
        super("Elphaba", 90, 15, 5, 1);
    }

    public Elphaba(Elphaba outro) {
        super(outro);
    }

    @Override
    public void usarHabilidade(Personagem alvo) {
        System.out.println(nome + " lança Magia Verde!");
        alvo.pontosVida -= ataque + 5;
    }
}
