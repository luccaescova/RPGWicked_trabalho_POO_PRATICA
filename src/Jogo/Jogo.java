package jogo.Jogo;

import jogo.itens.Item;
import jogo.personagens.*;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Jogo {
    private Personagem jogador;
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();

    public void iniciar() {
        escolherPersonagem();
        System.out.println("Bem-vindo ao mundo mágico de Wicked!\n");
        System.out.println("Você chega à renomada Universidade de Shiz, um lugar repleto de magia, conhecimento e mistérios.\n");
        historiaUniversidade();
    }

    private void escolherPersonagem() {
        System.out.println("Escolha seu personagem:\n1. Elphaba (Mago)      (HP: 90 DANO: 15)\n2. Glinda (Arqueira)       (HP: 80 DANO: 12)\n3. Fiyero (Guerreiro)       (HP: 100 DANO: 14)\n4. Boq (Tanque)       (HP: 100 DANO: 10)\n5. Nessarose (Suporte/Cura)       (HP: 80 DANO: 8)");
        int escolha = sc.nextInt();
        switch (escolha) {
            case 1 -> jogador = new Elphaba();
            case 2 -> jogador = new Glinda();
            case 3 -> jogador = new Fiyero();
            case 4 -> jogador = new Boq();
            case 5 -> jogador = new Nessarose();
            default -> jogador = new Elphaba();
        }
        System.out.println("\nVocê selecionou: " + jogador.getNome() + "!");
    }

    // === HISTÓRIA PRINCIPAL ===

    private void historiaUniversidade() {
        System.out.println("Você começa sua jornada nos salões de Shiz, onde professores e alunos praticam feitiços diariamente.\n");
        System.out.println("Durante suas aulas, você conhece alguns alunos excêntricos, mas percebe que algo estranho ronda o campus...");
        System.out.println("Um professor menciona rumores sobre o Mágico de Oz e sua suposta benevolência.");

        System.out.println("\nApós uma semana intensa de estudos, você é chamado pela diretora de Shiz.");
        System.out.println("\"Você foi escolhido para representar a universidade na Cidade das Esmeraldas e conhecer o próprio Mágico de Oz!\"");
        System.out.println("Você aceita o convite e se prepara para a jornada.\n");

        System.out.println("=== Capítulo 1: Caminho para a Cidade das Esmeraldas ===");
        loopJogo();

        System.out.println("\nApós suas aventuras, você finalmente chega aos portões cintilantes da Cidade das Esmeraldas!");
        historiaCidade();
    }

    private void historiaCidade() {
        System.out.println("\nA Cidade das Esmeraldas é deslumbrante — ruas verdes, prédios reluzentes e guardas marchando com armaduras brilhantes.");
        System.out.println("Você é escoltado até o palácio do Mágico de Oz. Ele o recebe com um sorriso enigmático...");

        System.out.println("\"Ah, o prodígio de Shiz! Ouvi falar muito sobre você...\" diz o Mágico, enquanto observa seu cajado.");
        System.out.println("Ele pede que você demonstre um feitiço poderoso — algo para 'ajudar Oz a prosperar'.\n");

        System.out.println("Você conjura um feitiço, mas percebe tarde demais que o círculo mágico ao redor do salão não era um campo de proteção...");
        System.out.println("... e sim um ritual de aprisionamento!");
        System.out.println("\"HAHAHA! Ingênuo! Você tem o poder que eu preciso para me tornar invencível!\" grita o Mágico de Oz.");
        System.out.println("Você sente a energia drenando de seu corpo, mas com um último esforço quebra o selo mágico!\n");

        System.out.println("=== BOSS FIGHT: O MÁGICO DE OZ ===");
        bossFight();
    }

private void historiaJardim() {
    System.out.println("\n=== Capítulo 3: O Mistério do Jardim Encantado ===");
    System.out.println("Após a batalha com o Mágico de Oz, você decide explorar a Cidade das Esmeraldas.");
    System.out.println("As ruas são vibrantes, e você percebe um jardim escondido entre prédios dourados, repleto de magia e segredos.\n");

    loopJogoCapitulo3();

    System.out.println("\nVocê finalmente descobre o segredo do jardim: uma fonte mágica que pode restaurar completamente suas energias!");
    System.out.println("O povo da Cidade das Esmeraldas reconhece seu heroísmo e você se torna uma lenda viva da magia de Oz.\n");
    System.out.println("=== FIM DO JOGO ===");
}

private void loopJogoCapitulo3() {
    boolean rodando = true;
    int rodadas = 0;
    int limiteRodadas = 5;

    while (rodando && jogador.estaVivo()) {
        System.out.println("\nO que deseja fazer?");
        System.out.println("1. Explorar o Jardim\n2. Usar Item\n3. Ver Inventário\n4. Loja");
        int escolha = sc.nextInt();
        sc.nextLine();

        switch (escolha) {
            case 1 -> {
                explorarJardim();
                rodadas++;
            }
            case 2 -> usarItem();
            case 3 -> jogador.getInventario().listarItens();
            case 4 -> loja();
            default -> System.out.println("Escolha inválida!");
        }

        if (rodadas >= limiteRodadas) {
            rodando = false;
        }
    }
}

// === EXPLORAÇÃO DO JARDIM ===
private void explorarJardim() {
    System.out.println("Você explora o jardim mágico e encontra...");

    int evento = rand.nextInt(4);

    switch (evento) {
        case 0 -> {
            Inimigo plantaMágica = new Inimigo("Planta Mágica Selvagem", 30, 8, 2, 1);
            System.out.println("Um inimigo aparece: " + plantaMágica.getNome() + "!");
            batalhar(plantaMágica);
        }
        case 1 -> {
            System.out.println("Você encontra uma Poção de Vida Completa!");
            jogador.getInventario().adicionarItem(
                    new Item("Poção de Vida Completa", "Restaura completamente HP", "cura", 1)
            );
        }
        case 2 -> {
            System.out.println("Uma fada lhe dá uma Poção de Sorte!");
            jogador.getInventario().adicionarItem(
                    new Item("Poção de Sorte", "Aumenta chance de sucesso em eventos", "buff", 1)
            );
        }
        case 3 -> {
            System.out.println("Você encontra um baú misterioso, mas ele está trancado e vazio...");
        }
    }
}


    //  LOOP DE JOGO 

    private void loopJogo() {
        boolean rodando = true;
        int rodadas = 0; 
        int limiteRodadas = 10;
    
        while (rodando && jogador.estaVivo()) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("1. Explorar\n2. Usar Item\n3. Ver Inventário\n4. Loja");
            int escolha = sc.nextInt();
            sc.nextLine();
    
            switch (escolha) {
                case 1 -> {
                    explorar();
                    rodadas++; 
                }
                case 2 -> usarItem();
                case 3 -> jogador.getInventario().listarItens();
                case 4 -> loja();
                default -> System.out.println("Escolha inválida!");
            }
    
            if (rodadas >= limiteRodadas) {
                rodando = false; 
            }
        }
    }
    
    

    private void explorar() {
        System.out.println("Você explora os caminhos de Oz e encontra...");

        int evento = rand.nextInt(5);

        switch (evento) {
            case 0 -> {
                Inimigo inimigo = new Inimigo("Aluno Rebelde de Shiz", 40, 10, 3, 1);
                System.out.println("Um inimigo aparece: " + inimigo.getNome() + "!");
                batalhar(inimigo);
            }
            case 1 -> {
                System.out.println("Você encontrou uma Poção de Magia Verde!");
                jogador.getInventario().adicionarItem(
                        new Item("Poção de Magia Verde", "Recupera 20 HP", "cura", 1)
                );
            }
            case 2 -> {
                System.out.println("Um velho alquimista te entrega uma Poção de Força!");
                jogador.getInventario().adicionarItem(
                        new Item("Poção de Força", "Aumenta ataque temporariamente", "buff", 1)
                );
            }
            case 3 -> {
                System.out.println("Você achou um baú encantado!");
                if (rand.nextBoolean()) {
                    System.out.println("Dentro havia uma Moeda de Oz!");
                    jogador.getInventario().adicionarItem(
                            new Item("Moeda de Oz", "Recompensa valiosa", "ouro", 1)
                    );
                } else {
                    System.out.println("O baú estava vazio...");
                }
            }
            case 4 -> {
                System.out.println("Uma armadilha mágica explode! Você perde 10 HP.");
                jogador.receberDano(10);
            }
        }
    }

    // === BATALHA ===

    private void bossFight() {
        Inimigo magico = new Inimigo("Mágico de Oz", 120, 18, 6, 5);
        Inimigo guarda = new Inimigo("Guarda de Oz", 60, 12, 3, 2);
        Inimigo macaco = new Inimigo("Macaco Voador", 40, 14, 2, 1);

        System.out.println("O Mágico convoca seus capangas para protegê-lo!");
        batalhar(guarda);
        if (jogador.estaVivo()) batalhar(macaco);
        if (jogador.estaVivo()) batalhar(magico);

        if (jogador.estaVivo()) {
            System.out.println("\nVocê derrotou o Mágico de Oz!");
            System.out.println("O salão se desfaz em fumaça verde... o verdadeiro rosto por trás da cortina era apenas um homem comum.");
            System.out.println("Com o fim da tirania, Oz será livre novamente. Parabéns, herói!");
        } else {
            System.out.println("\nVocê caiu diante do poder do Mágico... Oz continuará sob seu domínio sombrio.");
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
                    if (rand.nextInt(100) < 40) {
                        System.out.println("Você conseguiu fugir!");
                        return;
                    } else {
                        System.out.println("Falha na fuga!");
                    }
                }
                default -> System.out.println("Ação inválida!");
            }

            if (!inimigo.estaVivo()) {
                System.out.println("Você derrotou " + inimigo.getNome() + " e encontrou uma Moeda de Oz!");
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
            System.out.println("\nVocê foi derrotado por " + inimigo.getNome() + "...");
        }
    }

    private void atacar(Personagem atacante, Personagem defensor) {
        int dado = rand.nextInt(6) + 1;
        int dano = atacante.getAtaque() + dado - defensor.getDefesa();
        if (dano < 0) dano = 0;

        defensor.receberDano(dano);
        System.out.println(atacante.getNome() + " rolou " + dado + " e causou " + dano + " de dano em " + defensor.getNome() + "!");
    }

    // === INVENTÁRIO ===

    private void usarItem() {
        if (jogador.getInventario().getItens().isEmpty()) {
            System.out.println("Seu inventário está vazio!");
            return;
        }
    
        System.out.println("\n=== Itens no Inventário ===");
        jogador.getInventario().listarItensNumerados();
    
        System.out.print("Escolha o número do item que deseja usar (0 para cancelar): ");
        int escolha = sc.nextInt();
        sc.nextLine();
    
        if (escolha == 0) {
            System.out.println("Ação cancelada.");
            return;
        }
    
    
        Item itemEscolhido = jogador.getInventario().getItemPorIndice(escolha - 1);
    
        if (itemEscolhido == null) {
            System.out.println("Escolha inválida!");
            return;
        }
    
        String nome = itemEscolhido.getNome();
    
        
        jogador.getInventario().removerItem(nome);
    
        switch (nome.toLowerCase()) {
            case "poção de magia verde" -> {
                jogador.setPontosVida(jogador.getPontosVida() + 20);
                System.out.println("Você usou uma Poção de Magia Verde e recuperou 20 HP! HP atual: " + jogador.getPontosVida());
            }
            case "poção de força" -> {
                System.out.println("Você usou uma Poção de Força! Seu ataque aumentará temporariamente!");
            }
            default -> System.out.println("Você usou o item " + nome + ".");
        }
    }
    
    

    // === LOJA ===

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
