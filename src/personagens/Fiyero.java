package personagens;

public class Fiyero extends Personagem {

    public Fiyero() {
        super("Fiyero", 100, 14, 10, 1);
    }

    public Fiyero(Fiyero outro) {
        super(outro);
    }

    @Override
    public void usarHabilidade(Personagem alvo) {
        System.out.println(nome + " desfere ataque poderoso!");
        alvo.pontosVida -= ataque;
    }
}
