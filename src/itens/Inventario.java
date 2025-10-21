package jogo.itens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Inventario implements Cloneable {
    private ArrayList<Item> itens = new ArrayList<>();

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

    public void adicionarItem(Item item) {
        adicionar(item);
    }

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

    public int contarItem(String nome) {
        int count = 0;
        for (Item item : itens) {  // 'itens' é a lista interna de itens do inventário
            if (item.getNome().equalsIgnoreCase(nome)) {
                count += item.getQuantidade();
            }
        }
        return count;
    }

}
