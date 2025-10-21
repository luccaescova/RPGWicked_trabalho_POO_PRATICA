package jogo.Jogo;

import jogo.itens.Item;
import jogo.personagens.*;

import java.util.Scanner;
import java.util.Random;

public class Jogo {
    private Personagem jogador;
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();

    public void iniciar() {
        escolherPersonagem();
        System.out.println("Bem-vindo ao mundo mágico de Wicked!\n");
        loopJogo();
    }

    private void escolherPersonagem() {
        System.out.println("Escolha seu personagem:\n1. Elphaba (Mago)\n2. Glinda (Arqueira)\n3. Fiyero (Guerreiro)\n4. Boq (Tanque)\n5. Nessarose (Suporte/Cura)");
        int escolha = sc.nextInt();
        switch (escolha) {
            case 1 -> jogador = new Elphaba();
            case 2 -> jogador = new Glinda();
            case 3 -> jogador = new Fiyero();
            case 4 -> jogador = new Boq();
            case 5 -> jogador = new Nessarose();
            default -> jogador = new Elphaba();
        }
    }

    private void loopJogo() {
        boolean rodando = true;
        while (rodando && jogador.estaVivo()) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("1. Explorar\n2. Usar Item\n3. Ver Inventário\n4. Loja\n0. Sair");
            int escolha = sc.nextInt();
            switch (escolha) {
                case 1 -> explorar();
                case 2 -> usarItem();
                case 3 -> jogador.getInventario().listarItens();
                case 4 -> loja();
                case 0 -> rodando = false;
                default -> System.out.println("Escolha inválida!");
            }
        }
        if (!jogador.estaVivo()) System.out.println("Você foi derrotado! Fim de jogo.");
        else System.out.println("Até a próxima aventura!");
    }

    private void explorar() {
        System.out.println("Você explora os caminhos de Oz e encontra...");

        int evento = rand.nextInt(5); // 0 a 4

        switch (evento) {
            case 0 -> {
                Inimigo inimigo = new Inimigo("Guardião de Oz", 50, 12, 4, 1);
                System.out.println("Um inimigo aparece: " + inimigo.getNome() + "!");
                batalhar(inimigo);
            }
            case 1 -> {
                System.out.println("Nada acontece. Mas você encontrou uma Poção de Magia Verde!");
                jogador.getInventario().adicionarItem(
                        new Item("Poção de Magia Verde", "Recupera 20 HP", "cura", 1)
                );
            }
            case 2 -> {
                System.out.println("Você encontra um aliado misterioso que te entrega uma Poção de Força!");
                jogador.getInventario().adicionarItem(
                        new Item("Poção de Força", "Aumenta ataque temporariamente", "buff", 1)
                );
            }
            case 3 -> {
                System.out.println("Você encontrou um baú antigo!");
                if (rand.nextBoolean()) {
                    System.out.println("Dentro do baú, havia uma Poção de Magia Verde!");
                    jogador.getInventario().adicionarItem(
                            new Item("Poção de Magia Verde", "Recupera 20 HP", "cura", 1)
                    );
                } else {
                    System.out.println("O baú estava vazio, que azar...");
                }
            }
            case 4 -> {
                System.out.println("Você caiu em uma armadilha! Perdeu 10 HP.");
                jogador.receberDano(10);
                if (!jogador.estaVivo()) {
                    System.out.println("Você não resistiu ao dano da armadilha...");
                }
            }
        }
    }

    private void batalhar(Inimigo inimigo) {
        System.out.println("\n⚔  Um combate começou contra " + inimigo.getNome() + "!");

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\nSeu turno!");
            System.out.println("1. Atacar\n2. Usar Item\n3. Fugir");
            System.out.print("Escolha: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1 -> atacar(jogador, inimigo);
                case 2 -> usarItem();
                case 3 -> {
                    if (rand.nextInt(100) < 50) {
                        System.out.println("Você conseguiu fugir!");
                        return;
                    } else {
                        System.out.println("Falha na fuga! O inimigo bloqueou sua saída!");
                    }
                }
                default -> System.out.println("Ação inválida!");
            }

            if (!inimigo.estaVivo()) {
                System.out.println("Você derrotou o inimigo e encontrou uma Moeda de Oz!");
                jogador.getInventario().adicionarItem(
                        new Item("Moeda de Oz", "Recompensa pela vitória", "ouro", 1)
                );
                return;
            }

            System.out.println("\nTurno do inimigo!");
            atacar(inimigo, jogador);

            System.out.println("\nStatus atual:");
            System.out.println(jogador.getNome() + " - HP: " + jogador.getPontosVida());
            System.out.println(inimigo.getNome() + " - HP: " + inimigo.getPontosVida());
        }

        if (!jogador.estaVivo()) {
            System.out.println("\n Você foi derrotado por " + inimigo.getNome() + "...");
        }
    }

    private void atacar(Personagem atacante, Personagem defensor) {
        int dado = rand.nextInt(6) + 1;
        int dano = atacante.getAtaque() + dado - defensor.getDefesa();
        if (dano < 0) dano = 0;

        defensor.receberDano(dano);
        System.out.println(atacante.getNome() + " rolou " + dado + " e causou " + dano + " de dano em " + defensor.getNome() + "!");
    }

    private void usarItem() {
        jogador.getInventario().listarItens();
        System.out.println("Digite o nome do item que deseja usar:");
        sc.nextLine();
        String nome = sc.nextLine();

        if (jogador.getInventario().removerItem(nome)) {
            if (nome.equalsIgnoreCase("Poção de Magia Verde")) {
                jogador.setPontosVida(jogador.getPontosVida() + 20);
                System.out.println("Você usou uma Poção de Magia Verde e recuperou 20 HP! HP atual: " + jogador.getPontosVida());
            } else if (nome.equalsIgnoreCase("Poção de Força")) {
                System.out.println("Você usou uma Poção de Força e seu ataque aumentará temporariamente!");
            } else {
                System.out.println("Você usou o item " + nome + ".");
            }
        } else {
            System.out.println("Você não possui esse item!");
        }
    }

    private void loja() {
        System.out.println("\n--- Loja de Oz ---");
        int moedas = jogador.getInventario().contarItem("Moeda de Oz");
        System.out.println("Você tem " + moedas + " Moeda(s) de Oz.");

        if (moedas <= 0) {
            System.out.println("Você não tem moedas suficientes para comprar nada.");
            return;
        }

        System.out.println("Itens disponíveis para compra:");
        System.out.println("1. Poção de Magia Verde (20 HP) - 3 moedas");
        System.out.println("2. Poção de Força (Aumenta ataque temporariamente) - 5 moedas");
        System.out.println("0. Voltar");

        System.out.print("Escolha o item para comprar: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        switch (escolha) {
            case 1 -> {
                if (moedas >= 3) {
                    removerMoedas(3);
                    jogador.getInventario().adicionarItem(
                            new Item("Poção de Magia Verde", "Recupera 20 HP", "cura", 1)
                    );
                    System.out.println("Você comprou uma Poção de Magia Verde!");
                } else {
                    System.out.println("Moedas insuficientes!");
                }
            }
            case 2 -> {
                if (moedas >= 5) {
                    removerMoedas(5);
                    jogador.getInventario().adicionarItem(
                            new Item("Poção de Força", "Aumenta ataque temporariamente", "buff", 1)
                    );
                    System.out.println("Você comprou uma Poção de Força!");
                } else {
                    System.out.println("Moedas insuficientes!");
                }
            }
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opção inválida!");
        }
    }

    private void removerMoedas(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            jogador.getInventario().removerItem("Moeda de Oz");
        }
    }
}
