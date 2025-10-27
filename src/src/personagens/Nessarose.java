package jogo.personagens;

public class Nessarose extends Personagem {

    public Nessarose() {
        super("Nessarose", 80, 8, 6, 1); 
    }

    public Nessarose(Nessarose outro) {
        super(outro);
    }

    @Override
    public void usarHabilidade(Personagem alvo) {
        System.out.println(nome + " conjura uma magia curativa!");
        int cura = 20;
        alvo.pontosVida += cura;
        if (alvo.pontosVida > alvo.getVidaMaxima()) {
            alvo.pontosVida = alvo.getVidaMaxima();
        }
        System.out.println(alvo.nome + " recuperou " + cura + " pontos de vida! HP atual: " + alvo.pontosVida);
    }
}
