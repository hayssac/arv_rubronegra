package arvore_rubronegra;

import entidade.No;
import static java.lang.System.out;

public class Arvore_RubroNegra {

    private No noRaiz;

    private final No nil = new No();

    public No getRaiz() {
        return this.noRaiz;
    }

    public No inserir(No no, int chave) {

        nil.setCor("black");
        nil.setFilho_Direito(null);
        nil.setFilho_Esquerdo(null);
        nil.setPai(null);
        nil.setChave(-1);

        if (noRaiz == null) {
            no = new No();
            no.setChave(chave);
            no.setCor("black");
            no.setFilho_Direito(nil);
            no.setFilho_Esquerdo(nil);

            noRaiz = no;

            return noRaiz;

        } else {
            if (chave < no.getChave()) { //lado esquerdo
                if (no.getFilho_Esquerdo() != nil) {
                    inserir(no.getFilho_Esquerdo(), chave);
                } else {
                    No novoNo = new No();

                    // Construindo o novo nó
                    novoNo.setChave(chave);
                    novoNo.setCor("red");
                    novoNo.setPai(no);
                    novoNo.setFilho_Direito(nil);
                    novoNo.setFilho_Esquerdo(nil);

                    no.setFilho_Esquerdo(novoNo);

                    if (no.getCor().equals("red")) {
                        rebalancearInsercao(novoNo);
                    }

                    // Fora desse IF, é o CASO 1, em que o pai do nó inserido é preto.
                }
                return null;

            } else { // lado direito                
                if (no.getFilho_Direito() != nil) {
                    inserir(no.getFilho_Direito(), chave);
                } else {
                    No novoNo = new No();

                    // formando o novo Nó
                    novoNo.setChave(chave);
                    novoNo.setCor("red");
                    novoNo.setPai(no);
                    novoNo.setFilho_Direito(nil);
                    novoNo.setFilho_Esquerdo(nil);

                    no.setFilho_Direito(novoNo);

                    if (no.getCor().equals("red")) {
                        rebalancearInsercao(novoNo);
                    }
                }
                return null;

            }
        }
    }

    // Método em que se trabalha os casos 2 e 3
    private void rebalancearInsercao(No inserido) {
        //Entrou aqui pois o PAI dele é RED. (duplo red)
        No pai = inserido.getPai();
        No avo = pai.getPai();

        if (avo.getCor().equals("black")) {
            if (avo.getFilho_Direito() == pai) { // O tio é filho esquerdo do AVÓ pois o pai é filho direito
                // caso 2
                No tio = avo.getFilho_Esquerdo(); // ESSA REFERÊNCIA QUE QUEBRA
                if (tio.getCor().equals("red")) {
                    tio.setCor("black");
                    pai.setCor("black");
                    if (avo.getPai() != null) { // teste se ele não é nó raiz
                        avo.setCor("red");
                        if (avo.getPai().getCor() == "red") {
                            rebalancearInsercao(avo);
                        }
                    }
                } else { // Se o tio for black OU null, que também se considera black
                    if (pai.getFilho_Direito() == inserido) {
                        rotacaoSimplesEsquerda(inserido);
                    } else {
                        rotacaoDuplaEsquerda(inserido);
                    }
                }

            } else { // O tio é filho direito do AVÔ pois o pai é filho esquerdo
                No tio = avo.getFilho_Direito();

                if (tio.getCor().equals("red")) {
                    tio.setCor("black");
                    pai.setCor("black");
                    if (avo.getPai() != null) {
                        avo.setCor("red");
                        if (avo.getPai().getCor() == "red") {
                            rebalancearInsercao(avo);
                        }
                    }
                } else { // Se o tio for black
                    if (pai.getFilho_Esquerdo() == inserido) {
                        rotacaoSimplesDireita(inserido);
                    } else {
                        rotacaoDuplaDireita(inserido);
                    }
                }
            }
        }

    }

    private void rotacaoSimplesDireita(No inserido) { // Caso 3a da inserção
        //out.println("rotação simples direita");
        No avo = inserido.getPai().getPai();

        avo.setFilho_Esquerdo(inserido.getPai().getFilho_Direito());
        if (inserido.getPai().getFilho_Direito() != nil) {
            inserido.getPai().getFilho_Direito().setPai(avo);
        }
        inserido.getPai().setFilho_Direito(avo);

        if (avo.getPai() != null) {
            inserido.getPai().setPai(avo.getPai());
            if (avo.getPai().getFilho_Direito() == avo) {
                avo.getPai().setFilho_Direito(inserido.getPai());
            } else {
                avo.getPai().setFilho_Esquerdo(inserido.getPai());
            }

        } else {
            inserido.getPai().setPai(avo.getPai());
            noRaiz = inserido.getPai();
        }

        avo.setPai(inserido.getPai());

        avo.setCor("red");
        inserido.getPai().setCor("black");
    }

    private void rotacaoSimplesEsquerda(No inserido) { // Caso 3b da inseção
        //out.println("rotação simples esquerda");
        No avo = inserido.getPai().getPai();

        avo.setFilho_Direito(inserido.getPai().getFilho_Esquerdo());
        if (inserido.getPai().getFilho_Esquerdo() != nil) {
            inserido.getPai().getFilho_Esquerdo().setPai(avo);
        }
        inserido.getPai().setFilho_Esquerdo(avo);

        if (avo.getPai() != null) {
            inserido.getPai().setPai(avo.getPai());
            if (avo.getPai().getFilho_Direito() == avo) {
                avo.getPai().setFilho_Direito(inserido.getPai());
            } else {
                avo.getPai().setFilho_Esquerdo(inserido.getPai());
            }
        } else {
            inserido.getPai().setPai(avo.getPai());
            noRaiz = inserido.getPai();
        }

        avo.setPai(inserido.getPai());

        avo.setCor("red");
        inserido.getPai().setCor("black");
    }

    private void rotacaoDuplaDireita(No inserido) { // Caso 3d da inserção
        //out.println("rotação dupla direita");

        No pai = inserido.getPai();
        No avo = pai.getPai();

        pai.setPai(inserido);
        inserido.setPai(avo);
        inserido.setFilho_Esquerdo(pai);
        avo.setFilho_Esquerdo(inserido);
        pai.setFilho_Direito(nil);

        rotacaoSimplesDireita(pai);
    }

    private void rotacaoDuplaEsquerda(No inserido) { // Caso 3c da inserção
        //out.println("rotação dupla esquerda");

        No pai = inserido.getPai();
        No avo = pai.getPai();

        pai.setPai(inserido);
        inserido.setPai(avo);
        inserido.setFilho_Direito(pai);

        avo.setFilho_Direito(inserido);
        pai.setFilho_Esquerdo(nil);

        rotacaoSimplesEsquerda(pai);
    }

    private void remover(No no, int chave) {

        //out.println(no.getChave());
        if (no.getChave() != chave) { // chave não for encontrada.
            if (no.getChave() < chave) {
                remover(no.getFilho_Direito(), chave);
            } else if (no.getChave() >= chave) {
                remover(no.getFilho_Esquerdo(), chave);
            } else {
                out.println("Não encontrado nenhum nó com a chave no valor de " + chave);
            }
        } else { // ENCONTROU O NÓ A SER REMOVIDO
            if (no.getFilho_Direito() == nil) { // Nó a ser removido não tem filho direito
                if (no.getFilho_Esquerdo() == nil) { // Nó a ser removido não tem filho esquerdo
                    // Nó FOOOLLLLHHHHAAA
                    if (no.getCor() == "red") {
                        if (no.getPai().getFilho_Direito() == no) { // removido é filho direito
                            no.getPai().setFilho_Direito(nil);
                        } else { // removido é filho ESQUERDO
                            no.getPai().setFilho_Esquerdo(nil);
                        }
                    } else { //NÓ é folha e NEGRO
                        if (no.getPai().getFilho_Direito() == no) { // removido é filho direito
                            no.getPai().setFilho_Direito(nil);
                        } else { // removido é filho ESQUERDO
                            no.getPai().setFilho_Esquerdo(nil);
                        }
                        nil.setDuplo(true);
                        nil.setPai(no.getPai());
                        situacao3(no, nil);
                    }
                } else { // NÓ só tem filho ESQUERDO.
                    if (no.getCor() == "red" && no.getFilho_Esquerdo().getCor() == "red") {
                        /*situacao1(no, no.getFilho_Esquerdo());
                        no.setChave(no.getFilho_Esquerdo().getChave());
                        no.setFilho_Esquerdo(nil);*/
                        out.println("entrou no filho esquerdo direto sendo substituto");
                    } else if (no.getCor() == "black" && no.getFilho_Esquerdo().getCor() == "red") {
                        No pai = no.getPai();
                        if (pai.getFilho_Direito() == no) { // removido é filho direito
                            pai.setFilho_Direito(no.getFilho_Esquerdo());
                        } else { // removido é filho ESQUERDO
                            pai.setFilho_Esquerdo(no.getFilho_Esquerdo());
                        }

                        no.getFilho_Esquerdo().setPai(pai);

                        situacao2(null, no.getFilho_Esquerdo());
                    } else if (no.getCor() == "black" && no.getFilho_Esquerdo().getCor() == "black") {
                        No pai = no.getPai();
                        if (pai.getFilho_Direito() == no) { // removido é filho direito
                            pai.setFilho_Direito(no.getFilho_Esquerdo());
                        } else { // removido é filho ESQUERDO
                            pai.setFilho_Esquerdo(no.getFilho_Esquerdo());
                        }

                        no.getFilho_Esquerdo().setPai(pai);
                        situacao3(no, no.getFilho_Esquerdo());
                    } else {
                        No pai = no.getPai();
                        if (pai.getFilho_Direito() == no) { // removido é filho direito
                            pai.setFilho_Direito(no.getFilho_Esquerdo());
                        } else { // removido é filho ESQUERDO
                            pai.setFilho_Esquerdo(no.getFilho_Esquerdo());
                        }
                        no.getFilho_Esquerdo().setPai(pai);
                        no.getFilho_Esquerdo().setDuplo(true);
                        situacao4(null, no.getFilho_Esquerdo());
                    }
                }
            } else {
                No sucessor = no.getFilho_Direito();

                while (sucessor.getFilho_Esquerdo() != nil) {
                    sucessor = sucessor.getFilho_Esquerdo();
                }

                if (no.getCor() == "red" && sucessor.getCor() == "red") {
                    situacao1(no, sucessor);
                } else if (no.getCor() == "black" && sucessor.getCor() == "red") {
                    situacao2(no, sucessor);
                } else if (no.getCor() == "black" && sucessor.getCor() == "black") {
                    situacao3(no, sucessor);
                } else {
                    situacao4(no, sucessor);
                }

            }

        }

    }

    private void situacao1(No removido, No sucessor) {
        if (removido.getPai().getFilho_Esquerdo() == removido) {
            if (sucessor.getPai().getFilho_Direito() == sucessor) {
                sucessor.getPai().setFilho_Direito(nil);
            } else {
                sucessor.getPai().setFilho_Esquerdo(nil);
            }

            removido.getPai().setFilho_Esquerdo(sucessor);
            removido.getFilho_Direito().setPai(sucessor);
            removido.getFilho_Esquerdo().setPai(sucessor);
            sucessor.setPai(removido.getPai());
            sucessor.setFilho_Direito(removido.getFilho_Direito());
            sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());
        } else {
            if (sucessor.getPai().getFilho_Esquerdo() == sucessor) {
                sucessor.getPai().setFilho_Esquerdo(nil);
            } else {
                sucessor.getPai().setFilho_Direito(nil);
            }

            removido.getPai().setFilho_Direito(sucessor);
            removido.getFilho_Esquerdo().setPai(sucessor);
            removido.getFilho_Direito().setPai(sucessor);
            sucessor.setPai(removido.getPai());
            sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());
            sucessor.setFilho_Direito(removido.getFilho_Direito());
        }
    }

    private void situacao2(No removido, No sucessor) {
        if (removido == null) {
            sucessor.setCor("black");
        } else {
            if (removido.getPai() == null) {
                if (sucessor.getPai().getFilho_Direito() == sucessor) {
                    sucessor.getPai().setFilho_Direito(nil);
                } else {
                    sucessor.getPai().setFilho_Esquerdo(nil);
                }

                sucessor.setPai(removido.getPai());
                sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());
                sucessor.setFilho_Direito(removido.getFilho_Direito());
                removido.getFilho_Esquerdo().setPai(sucessor);
                removido.getFilho_Direito().setPai(sucessor);

                noRaiz = sucessor;

            } else {
                if (removido.getPai().getFilho_Esquerdo() == removido) {
                    if (sucessor.getPai().getFilho_Direito() == sucessor) {
                        sucessor.getPai().setFilho_Direito(nil);
                    } else {
                        sucessor.getPai().setFilho_Esquerdo(nil);
                    }

                    removido.getPai().setFilho_Esquerdo(sucessor);
                    removido.getFilho_Direito().setPai(sucessor);
                    removido.getFilho_Esquerdo().setPai(sucessor);
                    sucessor.setPai(removido.getPai());
                    sucessor.setFilho_Direito(removido.getFilho_Direito());
                    sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());

                } else {
                    if (sucessor.getPai().getFilho_Esquerdo() == sucessor) {
                        sucessor.getPai().setFilho_Esquerdo(nil);
                    } else {
                        sucessor.getPai().setFilho_Direito(nil);
                    }

                    if (removido.getPai() == null) {
                        noRaiz = sucessor;
                    }

                    removido.getPai().setFilho_Direito(sucessor);
                    removido.getFilho_Esquerdo().setPai(sucessor);
                    removido.getFilho_Direito().setPai(sucessor);
                    sucessor.setPai(removido.getPai());
                    sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());
                    sucessor.setFilho_Direito(removido.getFilho_Direito());

                }
            }

            sucessor.setCor("black");
        }

    }

    private void situacao3(No removido, No sucessor) {
        //out.println("entrou na situação 3");
        // o no ja entra com duplo na situacao 3

        //LEMBRETE: settar o duplo para "false" de cada nó que vai ser alterado        
        if (sucessor.isDuplo() == false) {
            removido.setChave(sucessor.getChave());
            sucessor.setChave(0);
            sucessor.setDuplo(true);

        }

        No irmao;
        No pai = sucessor.getPai(); //500000000000000

        if (pai.getFilho_Esquerdo() == sucessor) {
            irmao = pai.getFilho_Direito();
        } else {
            irmao = pai.getFilho_Esquerdo(); //300000000000000
        }

        if (irmao.getCor() == "red") {
            // CASO 3.1
            // Essencialmente, os nós filhos do irmão são NEGROS
            // E o pai JAMAIS pode ser rubro, senão estaria desbalanceado
            situacao3_1(sucessor);

        } else { // mas se ele for negro
            if (irmao.getFilho_Esquerdo().getCor() == "black") {
                if (irmao.getFilho_Direito().getCor() == "black") {
                    if (pai.getCor() == "black") {
                        // CASO 3.2.a
                        situacao3_2a(sucessor);
                    } else {
                        // CASO 3.2.b
                        situacao3_2b(sucessor);
                    }

                } else if (irmao.getFilho_Direito().getCor() == "red" && irmao.getFilho_Esquerdo().getCor() == "black" && pai.getCor() == "black") {
                    situacao3_3(sucessor);
                } else {
                    // CASO 3.4
                    situacao3_4(false, sucessor);
                }

            } else {
                if (irmao.getFilho_Direito().getCor() == "black" && irmao.getFilho_Esquerdo().getCor() == "red" && pai.getCor() == "black") {

                    if (pai.getFilho_Esquerdo() == sucessor) {
                        situacao3_3(sucessor);
                    } else {
                        // CASO 3.3
                        situacao3_4(false, sucessor);
                    }
                } else {
                    // CASO 3.4
                    situacao3_3(sucessor);
                }
            }
        }

    }

    public void situacao3_1(No sucessor) {
        //LEMBRETE: settar o duplo para "false" de cada nó que vai ser alterado
        //LEMBRETE 2: para todos os casos nao terminais, sobe o duplo para o pai

        // Rotacao Simples a Esquerda
        // Pintar o irmao de negro
        // Pintar pai de rubro
        No pai = sucessor.getPai();
        No irmao;

        if (pai.getFilho_Esquerdo() == sucessor) {

            irmao = pai.getFilho_Direito();

            if (pai.getPai() == null) {
                noRaiz = irmao;
            }
            // Rotacao simples a esquerda 
            irmao.setPai(pai.getPai());
            pai.setPai(irmao);

            if (irmao.getFilho_Esquerdo() != null) {
                pai.setFilho_Direito(irmao.getFilho_Esquerdo());
                irmao.getFilho_Esquerdo().setPai(pai);
                irmao.setFilho_Esquerdo(pai);

            } else {
                irmao.setFilho_Esquerdo(pai);
            }

        } else {
            irmao = pai.getFilho_Esquerdo();

            if (pai.getPai() == null) {
                noRaiz = irmao;
            }
            // Rotacao simples a direita
            irmao.setPai(pai.getPai());
            pai.setPai(irmao);

            if (irmao.getFilho_Direito() != null) {
                pai.setFilho_Esquerdo(irmao.getFilho_Direito());
                irmao.getFilho_Direito().setPai(pai);
                irmao.setFilho_Direito(pai);

            } else {
                irmao.setFilho_Direito(pai);
            }
        }

        irmao.setCor("black");
        pai.setCor("red");

        situacao3(null, sucessor);

    }

    public void situacao3_2a(No sucessor) {
        //LEMBRETE: se o duplo subir para o nó RAIZ, o duplo é absorvido
        //LEMBRETE 2: para todos os casos nao terminais, sobe o duplo para o pai
        No irmao;
        No pai = sucessor.getPai();

        if (pai.getFilho_Esquerdo() == sucessor) {
            irmao = pai.getFilho_Direito();
        } else {
            irmao = pai.getFilho_Esquerdo();
        }

        irmao.setCor("red");

        // CASO TERMINAL
        sucessor.setDuplo(false);

        if (pai.getPai() != null) {
            pai.setDuplo(true);
            situacao3(null, pai);

        }

    }

    public void situacao3_2b(No sucessor) {
        // LEMBRETE: settar o duplo para "false" de cada nó que vai ser alterado
        // CASO TERMINAL: absorve duplo, setta pra false
        No irmao;
        No pai = sucessor.getPai();

        if (pai.getFilho_Esquerdo() == sucessor) {
            irmao = pai.getFilho_Direito();
        } else {
            irmao = pai.getFilho_Esquerdo();
        }

        if (pai.getFilho_Esquerdo() == sucessor) {
            pai.setFilho_Esquerdo(nil);
        } else {
            pai.setFilho_Direito(nil);
        }

        irmao.setCor("red");
        pai.setCor("black");
        sucessor.setDuplo(false);

    }

    public void situacao3_3(No sucessor) {
        No irmao;
        No pai = sucessor.getPai();

        // Rotacao simples a direita no irmao
        // Trocar as cores com o filho esquerdo
        if (pai.getFilho_Esquerdo() == sucessor) {
            irmao = pai.getFilho_Direito();

            irmao.getPai().setFilho_Direito(irmao.getFilho_Esquerdo());
            irmao.getFilho_Esquerdo().setPai(pai);
            irmao.getFilho_Esquerdo().setFilho_Direito(irmao);
            irmao.setPai(irmao.getFilho_Esquerdo());
            irmao.setFilho_Esquerdo(nil);

        } else {
            irmao = pai.getFilho_Esquerdo();
            No sobrinho = irmao.getFilho_Direito();

            sobrinho.setPai(pai);
            pai.setFilho_Esquerdo(sobrinho);
            sobrinho.setFilho_Esquerdo(irmao);
            irmao.setPai(sobrinho);
            irmao.setFilho_Direito(nil);

//            irmao.getPai().setFilho_Esquerdo(irmao.getFilho_Direito());
//            irmao.getFilho_Direito().setPai(pai);
//            //irmao.getFilho_Direito().setFilho_Esquerdo(irmao);
//            irmao.setPai(pai.getFilho_Esquerdo());
//            irmao.setFilho_Direito(nil);
//            
        }

        String cor = irmao.getCor(); //black
        irmao.setCor(irmao.getPai().getCor());
        irmao.getPai().setCor(cor);

        situacao3_4(true, sucessor);

    }

    public void situacao3_4(boolean veiode3_3, No sucessor) {
        //LEMBRETE: settar o duplo para "false" de cada nó que vai ser alterado
        // CASO TERMINAL: absorve duplo, setta pra false

        // Rotacao simples a esquerda no pai
        // Pinte o pai de negro
        // Cor do irmao igual a cor antiga de pai
        // Pinter o sobrinho direito de negro
        No irmao;
        No pai = sucessor.getPai();
        String corReservadaDoPai = pai.getCor();

        if (veiode3_3) {
            if (pai.getFilho_Direito() == sucessor) {

                irmao = pai.getFilho_Esquerdo();

                // rotacao simples a esquerda no pai
                if (pai.getPai() == null) {
                    noRaiz = irmao;
                } else {
                    if (pai.getPai().getFilho_Direito() == pai) {
                        pai.getPai().setFilho_Direito(irmao);
                    } else {
                        pai.getPai().setFilho_Esquerdo(irmao);
                    }
                }

                irmao.setPai(pai.getPai());

                irmao.setFilho_Direito(pai);

                pai.setPai(irmao);
                // setta a cor do filho direito anterior logo agora, antes de trocar referencia
                irmao.getFilho_Esquerdo().setCor("black");
                pai.setFilho_Esquerdo(nil);

                pai.setCor("black");
                irmao.setCor(corReservadaDoPai);
                irmao.getFilho_Esquerdo().setCor("black");

                sucessor.setDuplo(false);
                veiode3_3 = false;

            } else {

                irmao = pai.getFilho_Direito();

                // rotacao simples a esquerda no pai
                if (pai.getPai() == null) {
                    noRaiz = irmao;
                } else {
                    if (pai.getPai().getFilho_Esquerdo() == pai) {
                        pai.getPai().setFilho_Esquerdo(irmao);
                    } else {
                        pai.getPai().setFilho_Direito(irmao);
                    }
                }

                irmao.setPai(pai.getPai());

                irmao.setFilho_Esquerdo(pai);

                pai.setPai(irmao);
                // setta a cor do filho direito anterior logo agora, antes de trocar referencia
                irmao.getFilho_Direito().setCor("black");
                pai.setFilho_Direito(nil);

                pai.setCor("black");
                irmao.setCor(corReservadaDoPai);
                irmao.getFilho_Esquerdo().setCor("black");
                //        pai.setFilho_Esquerdo(nil);

                sucessor.setDuplo(false);
                veiode3_3 = false;

            }

        } else {
            if (sucessor == pai.getFilho_Esquerdo()) {
                // isso significa que irmao eh filho direito
                irmao = pai.getFilho_Direito();

                // rotacao simples a esquerda no pai
                if (pai.getPai() == null) {
                    noRaiz = irmao;
                } else {
                    if (pai.getPai().getFilho_Direito() == pai) {
                        pai.getPai().setFilho_Direito(irmao);
                    } else {
                        pai.getPai().setFilho_Esquerdo(irmao);
                    }
                }

                irmao.setPai(pai.getPai());

                if (irmao.getFilho_Esquerdo() != nil) {
                    irmao.getFilho_Esquerdo().setPai(pai);
                    pai.setFilho_Direito(irmao.getFilho_Esquerdo());

                } else {
                    pai.setFilho_Direito(nil);
                }
                irmao.setFilho_Esquerdo(pai);
                pai.setPai(irmao);

                // setta a cor do filho direito anterior logo agora, antes de trocar referencia
                irmao.getFilho_Direito().setCor("black");
                pai.setFilho_Esquerdo(nil);

            } else {
                irmao = pai.getFilho_Esquerdo();

                // rotacao simples a esquerda no pai
                if (pai.getPai() == null) {
                    noRaiz = irmao;
                } else {
                    if (pai.getPai().getFilho_Esquerdo() == pai) {
                        pai.getPai().setFilho_Esquerdo(irmao);
                    } else {
                        pai.getPai().setFilho_Direito(irmao);
                    }
                }

                irmao.setPai(pai.getPai());

                if (irmao.getFilho_Direito() != nil) {
                    irmao.getFilho_Direito().setPai(pai);
                    pai.setFilho_Esquerdo(irmao.getFilho_Direito());

                } else {
                    pai.setFilho_Esquerdo(nil);
                }
                irmao.setFilho_Direito(pai);
                pai.setPai(irmao);

                // setta a cor do filho direito anterior logo agora, antes de trocar referencia
                irmao.getFilho_Esquerdo().setCor("black");
                pai.setFilho_Direito(nil);
            }

            pai.setCor("black");
            irmao.setCor(corReservadaDoPai);
            irmao.getFilho_Esquerdo().setCor("black");
//        pai.setFilho_Esquerdo(nil);

            sucessor.setDuplo(false);
        }

    }

    private void situacao4(No removido, No sucessor) {
        //out.println(sucessor.getChave() +" "+ sucessor.getCor());
        if (removido == null) {
            sucessor.setCor("red");
            sucessor.setDuplo(true);
        } else {
            if (removido.getPai().getFilho_Esquerdo() == removido) {
                if (sucessor.getPai().getFilho_Direito() == sucessor) {
                    sucessor.getPai().setFilho_Direito(nil);
                } else {
                    sucessor.getPai().setFilho_Esquerdo(nil);
                }

                removido.getPai().setFilho_Esquerdo(sucessor);
                removido.getFilho_Direito().setPai(sucessor);
                removido.getFilho_Esquerdo().setPai(sucessor);
                sucessor.setPai(removido.getPai());
                sucessor.setFilho_Direito(removido.getFilho_Direito());
                sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());

                sucessor.setCor("red");
                sucessor.setDuplo(true);
            } else {

                if (sucessor.getPai().getFilho_Esquerdo() == sucessor) {
                    sucessor.getPai().setFilho_Esquerdo(nil);
                } else {
                    sucessor.getPai().setFilho_Direito(nil);
                }

                removido.getPai().setFilho_Direito(sucessor);
                removido.getFilho_Esquerdo().setPai(sucessor);
                removido.getFilho_Direito().setPai(sucessor);
                sucessor.setPai(removido.getPai());
                sucessor.setFilho_Direito(removido.getFilho_Direito());
                sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());

                sucessor.setCor("red");
                sucessor.setDuplo(true);
            }
        }
        situacao3(nil, sucessor);
    }

    public static void emOrdem(No raiz) {
        if (raiz != null) {
            if (raiz.getChave() != -1) { // ignore a impressão de nós nulos
                emOrdem(raiz.getFilho_Esquerdo());
                out.print(raiz.toString());
                emOrdem(raiz.getFilho_Direito());
            }

        }
    }

    public static void main(String[] args) {
        Arvore_RubroNegra aRN = new Arvore_RubroNegra();

        // TESTES DE REMOÇÃO 
        //teste 18
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 10);
//        aRN.inserir(aRN.getRaiz(), 40);
//        aRN.remover(aRN.getRaiz(), 40);
        //teste 19
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 10);
//        aRN.inserir(aRN.getRaiz(), 40);
//        aRN.inserir(aRN.getRaiz(), 50);
//        aRN.remover(aRN.getRaiz(), 50);
//        aRN.remover(aRN.getRaiz(), 10);  
        //teste extra
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 10);
//        aRN.inserir(aRN.getRaiz(), 40);
//        aRN.inserir(aRN.getRaiz(), 50);
//        aRN.inserir(aRN.getRaiz(), 35);
//        aRN.inserir(aRN.getRaiz(), 60);
//        aRN.remover(aRN.getRaiz(), 60);
//        aRN.remover(aRN.getRaiz(), 35);
        //teste 20
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 10);
//        aRN.inserir(aRN.getRaiz(), 40);
//        aRN.inserir(aRN.getRaiz(), 50);
//        aRN.remover(aRN.getRaiz(), 50);
//        aRN.remover(aRN.getRaiz(), 40);  
        //teste 22
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 10);
//        aRN.inserir(aRN.getRaiz(), 40);
//        aRN.inserir(aRN.getRaiz(), 50);
//        aRN.inserir(aRN.getRaiz(), 35);
//        aRN.inserir(aRN.getRaiz(), 60);
//        aRN.remover(aRN.getRaiz(), 60);
//        aRN.remover(aRN.getRaiz(), 50);
        //teste 23
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 10);
//        aRN.inserir(aRN.getRaiz(), 40);
//        aRN.inserir(aRN.getRaiz(), 50);
//        aRN.inserir(aRN.getRaiz(), 35);
//        aRN.inserir(aRN.getRaiz(), 60);
//        aRN.remover(aRN.getRaiz(), 60);
//        aRN.remover(aRN.getRaiz(), 10);
        //teste 24
        aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 10);
        aRN.inserir(aRN.getRaiz(), 40);
        aRN.inserir(aRN.getRaiz(), 5);
        aRN.inserir(aRN.getRaiz(), 25);
        aRN.inserir(aRN.getRaiz(), 1);
        aRN.remover(aRN.getRaiz(), 1);
        aRN.remover(aRN.getRaiz(), 40);
//   caso 1 espelhado
        //teste 26
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 10);
//        aRN.inserir(aRN.getRaiz(), 40);
//        aRN.inserir(aRN.getRaiz(), 5);
//        aRN.inserir(aRN.getRaiz(), 25);
//        aRN.inserir(aRN.getRaiz(), 1);
//        aRN.remover(aRN.getRaiz(), 1);
//        aRN.remover(aRN.getRaiz(), 40);
//        aRN.remover(aRN.getRaiz(), 5);

        //teste 28
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 20);
//        aRN.inserir(aRN.getRaiz(), 50);
//        aRN.inserir(aRN.getRaiz(), 70);
//        aRN.inserir(aRN.getRaiz(), 40);
//        aRN.inserir(aRN.getRaiz(), 80);
//        aRN.remover(aRN.getRaiz(), 80);
//        aRN.remover(aRN.getRaiz(), 20);
//        aRN.remover(aRN.getRaiz(), 70);
        // caso 3 e 4
//        aRN.inserir(aRN.getRaiz(), 15);
//        aRN.inserir(aRN.getRaiz(), 11);
//        aRN.inserir(aRN.getRaiz(), 20);
//        aRN.inserir(aRN.getRaiz(), 10);
//        aRN.inserir(aRN.getRaiz(), 12);
//        aRN.inserir(aRN.getRaiz(), 9);
//        aRN.inserir(aRN.getRaiz(), 18);
//        aRN.inserir(aRN.getRaiz(), 25);
//        aRN.inserir(aRN.getRaiz(), 28);
//        aRN.inserir(aRN.getRaiz(), 30);
//        aRN.inserir(aRN.getRaiz(), 35);
//        aRN.remover(aRN.getRaiz(), 20);
//        aRN.remover(aRN.getRaiz(), 11);
        out.println(aRN.getRaiz());
        emOrdem(aRN.getRaiz());

    }
}
