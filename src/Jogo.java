package jogo;

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
        System.out.println("Bem-vindo ao mundo mágico de Wicked!\n");
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
                default -> System.out.println("Escolha inválida!");
            }
        }
        if (!jogador.estaVivo()) System.out.println("Você foi derrotado! Fim de jogo.");
        else System.out.println("Até a próxima aventura!");
    }

    private void explorar() {
        System.out.println("Você explora os caminhos de Oz e encontra...");
        if (rand.nextBoolean()) {
            Inimigo inimigo = new Inimigo("Guardião de Oz", 50, 12, 4, 1);
            System.out.println("Um inimigo aparece: " + inimigo.getNome() + "!");
            batalhar(inimigo);
        } else {
            System.out.println("Nada acontece. Mas você encontrou uma Poção de Magia Verde!");
            jogador.getInventario().adicionarItem(
                    new Item("Poção de Magia Verde", "Recupera 20 HP", "cura", 1)
            );
        }
    }

    // 🔄 Combate de turno
    private void batalhar(Inimigo inimigo) {
        System.out.println("\n⚔️  Um combate começou contra " + inimigo.getNome() + "!");

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            // --- TURNO DO JOGADOR ---
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
                System.out.println("✨ Você derrotou o inimigo e encontrou uma Moeda de Oz!");
                jogador.getInventario().adicionarItem(
                        new Item("Moeda de Oz", "Recompensa pela vitória", "ouro", 1)
                );
                return;
            }

            // --- TURNO DO INIMIGO ---
            System.out.println("\nTurno do inimigo!");
            atacar(inimigo, jogador);

            System.out.println("\nStatus atual:");
            System.out.println(jogador.getNome() + " - HP: " + jogador.getPontosVida());
            System.out.println(inimigo.getNome() + " - HP: " + inimigo.getPontosVida());
        }

        if (!jogador.estaVivo()) {
            System.out.println("\n💀 Você foi derrotado por " + inimigo.getNome() + "...");
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
            } else {
                System.out.println("Você usou o item " + nome + ".");
            }
        } else {
            System.out.println("Você não possui esse item!");
        }
    }

    private void tentarFugir() {
        if (rand.nextBoolean()) {
            System.out.println("Você conseguiu fugir da área com sucesso!");
        } else {
            System.out.println("Falha na fuga! Um inimigo aparece!");
            Inimigo inimigo = new Inimigo("Soldado de Madame Morrible", 40, 10, 3, 1);
            batalhar(inimigo);
        }
    }
}
