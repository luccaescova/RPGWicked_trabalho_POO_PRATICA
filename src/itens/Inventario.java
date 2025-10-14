package itens;

import java.util.ArrayList;
import java.util.Collections;

public class Inventario implements Cloneable {
    private ArrayList<Item> itens;

    public Inventario() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        for (Item i : itens) {
            if (i.equals(item)) {
                i.aumentarQuantidade(item.getQuantidade());
                return;
            }
        }
        itens.add(item.clone());
    }

    public void removerItem(String nome) {
        for (Item i : itens) {
            if (i.getNome().equalsIgnoreCase(nome)) {
                i.usar();
                if (i.getQuantidade() <= 0) itens.remove(i);
                return;
            }
        }
    }

    public void listarItens() {
        Collections.sort(itens);
        for (Item i : itens) {
            System.out.println(i);
        }
    }

    @Override
    public Inventario clone() {
        Inventario copia = new Inventario();
        for (Item i : itens) {
            copia.adicionarItem(i.clone());
        }
        return copia;
    }
}
