import personagens.*;
import itens.*;
import java.util.Scanner;
import java.util.Random;

public class Jogo {
    private Personagem jogador;
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();

    public void iniciar() {
        escolherPersonagem();
        System.out.println("Bem-vindo ao mundo de Wicked!\n");
        loopJogo();
    }

    private void escolherPersonagem() {
        System.out.println("Escolha seu personagem:\n1. Elphaba (Mago)\n2. Glinda (Arqueira)\n3. Fiyero (Guerreiro)");
        int escolha = sc.nextInt();
        switch (escolha) {
            case 1 -> jogador = new Elphaba();
            case 2 -> jogador = new Glinda();
            case 3 -> jogador = new Fiyero();
            default -> jogador = new Elphaba();
        }
    }

    private void loopJogo() {
        boolean rodando = true;
        while (rodando && jogador.estaVivo()) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("1. Explorar\n2. Usar Item\n3. Fugir\n4. Ver Inventário\n0. Sair");
            int escolha = sc.nextInt();
            switch (escolha) {
                case 1 -> explorar();
                case 2 -> usarItem();
                case 3 -> tentarFugir();
                case 4 -> jogador.getInventario().listarItens();
                case 0 -> rodando = false;
            }
        }
        if (!jogador.estaVivo()) System.out.println("Você foi derrotado! Fim de jogo.");
        else System.out.println("Até a próxima aventura!");
    }

    private void explorar() {
        System.out.println("Você explora e encontra...");
        if (rand.nextBoolean()) {
            Inimigo inimigo = new Inimigo("Guardião de Oz", 50, 12, 4, 1);
            System.out.println("Um inimigo aparece: " + inimigo.getNome());
            batalhar(inimigo);
        } else {
            System.out.println("Nada acontece. Mas você encontrou uma Poção de Magia Verde!");
            jogador.getInventario().adicionarItem(new Item("Poção de Magia Verde", "Recupera 20 HP", "cura", 1));
        }
    }

    private void batalhar(Inimigo inimigo) {
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            int dadoJogador = rand.nextInt(6) + 1;
            int dadoInimigo = rand.nextInt(6) + 1;

            int danoJogador = jogador.getAtaque() + dadoJogador - inimigo.getDefesa();
            if (danoJogador > 0) inimigo.setPontosVida(inimigo.getPontosVida() - danoJogador);
            System.out.println(jogador.getNome() + " causou " + danoJogador + " de dano. HP inimigo: " + inimigo.getPontosVida());

            if (!inimigo.estaVivo()) {
                System.out.println("Você derrotou o inimigo!");
                jogador.getInventario().adicionarItem(new Item("Moeda de Oz", "Recompensa", "ouro", 1));
                return;
            }

            int danoInimigo = inimigo.getAtaque() + dadoInimigo - jogador.getDefesa();
            if (danoInimigo > 0) jogador.setPontosVida(jogador.getPontosVida() - danoInimigo);
            System.out.println(inimigo.getNome() + " causou " + danoInimigo + " de dano. Seu HP: " + jogador.getPontosVida());
        }
    }

    private void usarItem() {
        jogador.getInventario().listarItens();
        System.out.println("Digite o nome do item que deseja usar:");
        sc.nextLine();
        String nome = sc.nextLine();
        jogador.getInventario().removerItem(nome);
        if (nome.equalsIgnoreCase("Poção de Magia Verde")) {
            jogador.setPontosVida(jogador.getPontosVida() + 20);
            System.out.println("Você recuperou 20 HP! HP atual: " + jogador.getPontosVida());
        }
    }

    private void tentarFugir() {
        if (rand.nextBoolean()) {
            System.out.println("Você conseguiu fugir!");
        } else {
            System.out.println("Falha na fuga! Um inimigo aparece!");
            Inimigo inimigo = new Inimigo("Soldado de Madame Morrible", 40, 10, 3, 1);
            batalhar(inimigo);
        }
    }
}
