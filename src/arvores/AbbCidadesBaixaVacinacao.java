
package arvores;

import entidades.Cidade;

public class AbbCidadesBaixaVacinacao {

    private class No {
        Cidade dado;
        No esq, dir;
    }

    private No root = null;

    public void inserir(Cidade cidade) {
        root = inserir(root, cidade);
    }

    private No inserir(No p, Cidade cidade) {
        if (p == null) {
            p = new No();
            p.dado = cidade;
            p.esq = null;
            p.dir = null;

        } else if (cidade.getPorcentagem() < p.dado.getPorcentagem()) {
            p.esq = inserir(p.esq, cidade);

        } else {
            p.dir = inserir(p.dir, cidade);
        }
        return p;
    }

    public void mostrarEmOrdem() {
        mostrarEmOrdem(root);
    }

    private void mostrarEmOrdem(No p) {

        if (p != null) {
            mostrarEmOrdem(p.esq);
            p.dado.apresentar();
            mostrarEmOrdem(p.dir);
        }
    }
}

