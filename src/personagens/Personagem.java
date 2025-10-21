package personagens;

import itens.Inventario;

public abstract class Personagem implements Cloneable {
    protected String nome;  // Alterado para protected para permitir acesso direto em subclasses (evita erros de acesso privado)
    protected int pontosVida;  // Alterado para protected
    protected int ataque;  // Alterado para protected
    protected int defesa;  // Alterado para protected
    protected int nivel;
    private Inventario inventario;

    public Personagem() {
        this.nome = "Desconhecido";
        this.pontosVida = 100;
        this.ataque = 10;
        this.defesa = 5;
        this.nivel = 1;
        this.inventario = new Inventario();
    }

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    public Personagem(Personagem outro) {
        // Usando getters para consistência (embora acesso direto também funcione, pois estamos na mesma classe)
        this.nome = outro.getNome();
        this.pontosVida = outro.getPontosVida();
        this.ataque = outro.getAtaque();
        this.defesa = outro.getDefesa();
        this.nivel = outro.nivel;  // nivel já é protected, acesso direto ok
        this.inventario = outro.getInventario().clone();  // Usando getter para inventario
    }

    // 🔹 Getters (mantidos)
    public String getNome() { return nome; }
    public int getPontosVida() { return pontosVida; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public Inventario getInventario() { return inventario; }

    // 🔹 Setters adicionados para permitir modificações controladas (com validações básicas)
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Nome inválido! Mantendo o atual.");
        }
    }

    public void setPontosVida(int pontosVida) {
        if (pontosVida >= 0) {
            this.pontosVida = pontosVida;
        } else {
            this.pontosVida = 0;  // Evita valores negativos
        }
    }

    public void setAtaque(int ataque) {
        if (ataque >= 0) {
            this.ataque = ataque;
        } else {
            System.out.println("Ataque não pode ser negativo! Mantendo o atual.");
        }
    }

    public void setDefesa(int defesa) {
        if (defesa >= 0) {
            this.defesa = defesa;
        } else {
            System.out.println("Defesa não pode ser negativa! Mantendo o atual.");
        }
    }

    // 🔹 Outros métodos (mantidos)
    public boolean estaVivo() { return pontosVida > 0; }

    public void atacar(Personagem alvo, int rolagemDado) {
        int dano = (this.ataque + rolagemDado) - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
        System.out.println(this.nome + " atacou " + alvo.getNome() + " causando " + dano + " de dano!");
    }

    public void receberDano(int dano) {
        this.pontosVida -= dano;
        if (this.pontosVida < 0) this.pontosVida = 0;
        System.out.println(this.nome + " agora tem " + this.pontosVida + " de HP.");
    }

    public abstract void usarHabilidade(Personagem alvo);

    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida + ", ATK=" + ataque + ", DEF=" + defesa + ", NIVEL=" + nivel + "]";
    }

    @Override
    public Personagem clone() {
        try {
            Personagem copia = (Personagem) super.clone();
            copia.inventario = this.inventario.clone();
            return copia;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
