
package arvores;

import entidades.Cidade;

public class AvlCidades {

    private class No {
        Cidade dado;
        No esq, dir;
        int hEsq, hDir;
    }

    private No root = null;

    public void inserirAVL(Cidade cidade) {
        root = inserirAVL(root, cidade);
    }

    private No inserirAVL(No p, Cidade cidade) {

        if (p == null) {
            p = new No();
            p.dado = cidade;
            p.esq = null;
            p.dir = null;
            p.hEsq = 0;
            p.hDir = 0;

        } else {

            int comparacao = cidade.getNome()
                    .compareToIgnoreCase(p.dado.getNome());

            if (comparacao < 0) {
                p.esq = inserirAVL(p.esq, cidade);

            } else if (comparacao > 0) {
                p.dir = inserirAVL(p.dir, cidade);

            } else {
                System.out.println("Cidade ja cadastrada: "
                        + cidade.getNome());
            }
        }

        atualizaAlturas(p);
        p = balanceamento(p);
        atualizaAlturas(p);

        return p;
    }

    private No rotacaoDireita(No p) {

        No q = p.esq;
        No temp = q.dir;

        q.dir = p;
        p.esq = temp;

        atualizaAlturas(p);
        atualizaAlturas(q);

        return q;
    }

    private No rotacaoEsquerda(No p) {

        No q = p.dir;
        No temp = q.esq;

        q.esq = p;
        p.dir = temp;

        atualizaAlturas(p);
        atualizaAlturas(q);

        return q;
    }

    private No balanceamento(No p) {

        int FB = p.hDir - p.hEsq;

        if (FB > 1) {

            int fbFilhoDir = p.dir.hDir - p.dir.hEsq;

            if (fbFilhoDir >= 0) {
                p = rotacaoEsquerda(p);

            } else {
                p.dir = rotacaoDireita(p.dir);
                p = rotacaoEsquerda(p);
            }

        } else if (FB < -1) {

            int fbFilhoEsq = p.esq.hDir - p.esq.hEsq;

            if (fbFilhoEsq <= 0) {
                p = rotacaoDireita(p);

            } else {
                p.esq = rotacaoEsquerda(p.esq);
                p = rotacaoDireita(p);
            }
        }

        return p;
    }

    private int altura(No p) {

        if (p == null) {
            return 0;
        }

        return Math.max(p.hEsq, p.hDir) + 1;
    }

    private void atualizaAlturas(No p) {

        if (p != null) {
            p.hEsq = altura(p.esq);
            p.hDir = altura(p.dir);
        }
    }

    public void mostraFB() {
        mostraFB(root);
    }

    private void mostraFB(No p) {

        if (p != null) {
            mostraFB(p.esq);
            mostraFB(p.dir);

            System.out.println("Cidade: " + p.dado.getNome()
                    + " | FB: " + (p.hDir - p.hEsq));
        }
    }

    public Cidade pesquisaAVL(String nome) {
        return pesquisaAVL(root, nome);
    }

    private Cidade pesquisaAVL(No p, String nome) {
        if (p == null) {
            return null;
        }
        int comparacao = nome.compareToIgnoreCase(
                p.dado.getNome());

        if (comparacao == 0) {
            return p.dado;

        } else if (comparacao < 0) {
            return pesquisaAVL(p.esq, nome);

        } else {
            return pesquisaAVL(p.dir, nome);
        }
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

    public Cidade[] gerarVetorAlerta() {
        int quantidade = contarCidadesAlerta(root);
        Cidade[] vetor = new Cidade[quantidade];
        preencherVetor(root, vetor, 0);
        return vetor;
    }

    private int contarCidadesAlerta(No p) {
        if (p == null) {
            return 0;
        }

        int cont = 0;

        if (p.dado.getPorcentagem() < 80 && p.dado.getNCasos() >= 1) {
            cont = 1;
        }

        cont += contarCidadesAlerta(p.esq);
        cont += contarCidadesAlerta(p.dir);

        return cont;
    }

    private int preencherVetor(No p, Cidade[] vetor, int indice) {
        if (p != null) {
            indice = preencherVetor(p.esq, vetor, indice);
            if (p.dado.getPorcentagem() < 80 &&
                    p.dado.getNCasos() >= 1) {
                vetor[indice] = p.dado;
                indice++;
            }

            indice = preencherVetor(p.dir, vetor, indice);
        }

        return indice;
    }

    public void gerarAbbAlerta(AbbCidadesBaixaVacinacao abb) {
        gerarAbbAlerta(root, abb);
    }

    private void gerarAbbAlerta(No p, AbbCidadesBaixaVacinacao abb) {

        if (p != null) {
            gerarAbbAlerta(p.esq, abb);
            if (p.dado.getPorcentagem() < 80 &&
                    p.dado.getNCasos() >= 1) {
                abb.inserir(p.dado);
            }
            gerarAbbAlerta(p.dir, abb);
        }
    }
}

