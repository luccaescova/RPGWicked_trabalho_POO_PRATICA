package itens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Inventario implements Cloneable {
    private ArrayList<Item> itens = new ArrayList<>();

    // Método principal usado internamente: adiciona Item (mantém ordenação)
    public void adicionar(Item item) {
        for (Item i : itens) {
            if (i.equals(item)) {
                i.setQuantidade(i.getQuantidade() + item.getQuantidade());
                Collections.sort(itens);
                return;
            }
        }
        itens.add(item.clone());
        Collections.sort(itens);
    }

    // Compatibilidade com o nome usado no Jogo: adicionarItem
    public void adicionarItem(Item item) {
        adicionar(item);
    }

    // Remove UMA unidade do item com o nome dado. Retorna true se removeu algo, false caso não possua.
    public boolean removerItem(String nome) {
        Iterator<Item> it = itens.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i.getNome().equalsIgnoreCase(nome)) {
                int q = i.getQuantidade();
                if (q > 1) {
                    i.setQuantidade(q - 1);
                } else {
                    it.remove();
                }
                return true;
            }
        }
        return false;
    }

    // Também ofereço um método que remove pelo objeto Item, caso outras partes do código usem
    public void remover(Item item) {
        for (Item i : new ArrayList<>(itens)) {
            if (i.equals(item)) {
                i.setQuantidade(i.getQuantidade() - 1);
                if (i.getQuantidade() <= 0) itens.remove(i);
                break;
            }
        }
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }
        Collections.sort(itens);
        for (Item i : itens) {
            System.out.println(i);
        }
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    @Override
    public Inventario clone() {
        Inventario copia = new Inventario();
        for (Item i : itens) {
            copia.itens.add(i.clone());
        }
        return copia;
    }
}
