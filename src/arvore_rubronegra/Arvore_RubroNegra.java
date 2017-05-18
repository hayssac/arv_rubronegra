/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_rubronegra;

import entidade.No;
import static java.lang.System.out;

/**
 *
 * @author adrianny
 */
public class Arvore_RubroNegra {
    
    private No noRaiz; 
    
    private final No nil = new No();
    
    public No getRaiz(){
        return this.noRaiz;
    }
        
    public No inserir(No no, int chave){
        
        nil.setCor("black");
        nil.setFilho_Direito(null);
        nil.setFilho_Esquerdo(null);
        nil.setPai(null);
        nil.setChave(-1);
        
        if(noRaiz == null){
            no = new No();
            no.setChave(chave);
            no.setCor("black");
            no.setFilho_Direito(nil);
            no.setFilho_Esquerdo(nil);
    
            noRaiz = no;
            
            return noRaiz;
            
        }else{
            if(chave < no.getChave()){ //lado esquerdo
                if(no.getFilho_Esquerdo() != nil){
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
                    
                    if(no.getCor().equals("red")){
                        rebalancearInsercao(novoNo);
                    }
                    
                    // Fora desse IF, é o CASO 1, em que o pai do nó inserido é preto.
                   
                }
                return null;
               
            } else { // lado direito                
                if(no.getFilho_Direito()!= nil){
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
                    
                    if(no.getCor().equals("red")){
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
        
        
        if(avo.getCor().equals("black")){
            if(avo.getFilho_Direito() == pai){ // O tio é filho esquerdo do AVÓ pois o pai é filho direito
                // caso 2
                No tio = avo.getFilho_Esquerdo(); // ESSA REFERÊNCIA QUE QUEBRA
                if(tio.getCor().equals("red")){
                    tio.setCor("black");
                    pai.setCor("black");
                    if(avo.getPai()!=null){ // teste se ele não é nó raiz
                        avo.setCor("red");
                        if(avo.getPai().getCor()=="red"){
                            rebalancearInsercao(avo);
                        }
                    }
                } else { // Se o tio for black OU null, que também se considera black
                    if(pai.getFilho_Direito() == inserido){
                        rotacaoSimplesEsquerda(inserido);
                    }else{
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
                        if(avo.getPai().getCor()=="red"){
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
    
    private void rotacaoSimplesDireita(No inserido){ // Caso 3a da inserção
        //out.println("rotação simples direita");
        No avo = inserido.getPai().getPai();
        
        
        avo.setFilho_Esquerdo(inserido.getPai().getFilho_Direito());
        if(inserido.getPai().getFilho_Direito() != nil){
            inserido.getPai().getFilho_Direito().setPai(avo);
        }
        inserido.getPai().setFilho_Direito(avo);
        
        if(avo.getPai() != null){
            inserido.getPai().setPai(avo.getPai());
            if(avo.getPai().getFilho_Direito()==avo){
               avo.getPai().setFilho_Direito(inserido.getPai()); 
            }else{
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
    
    private void rotacaoSimplesEsquerda(No inserido){ // Caso 3b da inseção
        //out.println("rotação simples esquerda");
        No avo = inserido.getPai().getPai();
        
        avo.setFilho_Direito(inserido.getPai().getFilho_Esquerdo());
        if(inserido.getPai().getFilho_Esquerdo() != nil){
            inserido.getPai().getFilho_Esquerdo().setPai(avo);
        }
        inserido.getPai().setFilho_Esquerdo(avo);
        
        
        if(avo.getPai() != null){
            inserido.getPai().setPai(avo.getPai());
            if(avo.getPai().getFilho_Direito()==avo){
               avo.getPai().setFilho_Direito(inserido.getPai()); 
            }else{
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
    
    private void rotacaoDuplaDireita(No inserido){ // Caso 3d da inserção
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
    
    private void rotacaoDuplaEsquerda(No inserido){ // Caso 3c da inserção
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
    
    private void remover(No no, int chave){
        
        //out.println(no.getChave());
        
        if(no.getChave() != chave){
            if(no.getChave() < chave){
                remover(no.getFilho_Direito(), chave);
            } else {
                remover(no.getFilho_Esquerdo(), chave);
            }
        } else { // ENCONTROU O NÓ A SER REMOVIDO
            if(no.getFilho_Direito() == nil){
                if(no.getFilho_Esquerdo() == nil){
                    if(no.getCor()=="red"){
                        if(no.getPai().getFilho_Direito()==no){
                            no.getPai().setFilho_Direito(nil);
                        } else {
                            no.getPai().setFilho_Esquerdo(nil);
                        }
                    }else{
                        nil.setDuplo(true);
                        situacao3(no, nil);
                    }
                } else { // nó só tem filho esquerdo.
                    if(no.getCor() == "red" && no.getFilho_Esquerdo().getCor() == "red"){
                        situacao1(no, no.getFilho_Esquerdo());
                    }else if(no.getCor() == "black" && no.getFilho_Esquerdo().getCor() == "red"){
                        situacao2(no, no.getFilho_Esquerdo());
                    } else if(no.getCor() == "black" && no.getFilho_Esquerdo().getCor() == "black"){
                        no.getFilho_Esquerdo().setDuplo(true);
                        situacao3(no, no.getFilho_Esquerdo());
                    } else {
                        situacao4(no, no.getFilho_Esquerdo());
                    }
                }
            } else {
                No filhoDireito = no.getFilho_Direito();
            
                while(filhoDireito.getFilho_Esquerdo()!=nil){
                    filhoDireito = filhoDireito.getFilho_Esquerdo();
                }
                
                if(no.getCor() == "red" && filhoDireito.getCor() == "red"){
                    situacao1(no, filhoDireito);
                }else if(no.getCor() == "black" && filhoDireito.getCor() == "red"){
                    situacao2(no, filhoDireito);
                } else if(no.getCor() == "black" && filhoDireito.getCor() == "black"){
                    filhoDireito.setDuplo(true);
                    situacao3(no, filhoDireito);
                } else {
                    situacao4(no, filhoDireito);
                }
                
            }
            
        }
        
    }
    
    private void situacao1(No removido, No sucessor){
        if(removido.getPai().getFilho_Esquerdo()==removido){
            if(sucessor.getPai().getFilho_Direito() == sucessor){
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
        }else {
            if(sucessor.getPai().getFilho_Esquerdo()== sucessor){
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
    
    private void situacao2(No removido, No sucessor){
        if(removido.getPai().getFilho_Esquerdo()==removido){
            if(sucessor.getPai().getFilho_Direito() == sucessor){
                sucessor.getPai().setFilho_Direito(nil);
            } else {
                sucessor.getPai().setFilho_Esquerdo(nil);
            }
            
            if(removido.getPai() == null){
                noRaiz = sucessor;
            }
            
            removido.getPai().setFilho_Esquerdo(sucessor);
            removido.getFilho_Direito().setPai(sucessor);
            removido.getFilho_Esquerdo().setPai(sucessor);
            sucessor.setPai(removido.getPai());
            sucessor.setFilho_Direito(removido.getFilho_Direito());
            sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());
            
            sucessor.setCor("black");
        } else {
            if(sucessor.getPai().getFilho_Esquerdo()== sucessor){
                sucessor.getPai().setFilho_Esquerdo(nil);
            } else {
                sucessor.getPai().setFilho_Direito(nil);
            }
            
            if(removido.getPai() == null){
                noRaiz = sucessor;
            }
            
            removido.getPai().setFilho_Direito(sucessor);
            removido.getFilho_Esquerdo().setPai(sucessor);
            removido.getFilho_Direito().setPai(sucessor);
            sucessor.setPai(removido.getPai());
            sucessor.setFilho_Esquerdo(removido.getFilho_Esquerdo());
            sucessor.setFilho_Direito(removido.getFilho_Direito());
            
            sucessor.setCor("black");
        }
    }
    
    private void situacao3(No removido, No sucessor){
        //out.println("entrou na situação 3");
        
        No irmao;
        No pai = removido.getPai();
        
        if(pai.getFilho_Direito() == removido){
            irmao = pai.getFilho_Esquerdo();
        } else {
            irmao = pai.getFilho_Direito();
        }
        
        //caso 1
        if(sucessor.getCor()=="black" && irmao.getCor()=="red" && pai.getCor()=="black"){
            //situacao3_1(pai, irmao, sucessor);
        }
    }
    
    public void situacao3_1(){
        
    }
    
    public void situacao3_2a(){
        
    }
    
    public void situacao3_2b(No sucessor){
    }
    
    public void situacao3_3(){
        
    }
    
    public void situacao3_4(){
        
        
    }
    
    private void situacao4(No removido, No sucessor){
        //out.println(sucessor.getChave() +" "+ sucessor.getCor());
        if(removido.getPai().getFilho_Esquerdo()==removido){
            if(sucessor.getPai().getFilho_Direito() == sucessor){
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
        }
        
        situacao3(removido, sucessor);
    }

    public static void emOrdem(No raiz) {
        if(raiz != null) {
            if (raiz.getChave() != -1) { // ignore a impressão de nós nulos
                emOrdem(raiz.getFilho_Esquerdo());
                out.print(raiz.toString());
                emOrdem(raiz.getFilho_Direito());
            }
            
        }
    }
    
    public static void main(String[] args) {
        Arvore_RubroNegra aRN = new Arvore_RubroNegra();
        
        aRN.inserir(aRN.getRaiz(), 7);
        aRN.inserir(aRN.getRaiz(), 2);
        aRN.inserir(aRN.getRaiz(), 10);
        aRN.inserir(aRN.getRaiz(), 1);
        aRN.inserir(aRN.getRaiz(), 8);
        aRN.inserir(aRN.getRaiz(), 5);
        aRN.inserir(aRN.getRaiz(), 15);
        aRN.inserir(aRN.getRaiz(), 13);
        
        aRN.remover(aRN.getRaiz(), 10);
        
        
        
        // TESTES DO PROFESSOR
        
        // teste 1
        /*aRN.inserir(aRN.getRaiz(), 10);
        aRN.inserir(aRN.getRaiz(), 5);
        aRN.inserir(aRN.getRaiz(), 15);
        aRN.inserir(aRN.getRaiz(), 20);*/    
        
        // teste 2
        /*aRN.inserir(aRN.getRaiz(), 10);
        aRN.inserir(aRN.getRaiz(), 5);
        aRN.inserir(aRN.getRaiz(), 15);
        aRN.inserir(aRN.getRaiz(), 1);  */
        
        // teste 3
        /*aRN.inserir(aRN.getRaiz(),20);
        aRN.inserir(aRN.getRaiz(),10);
        aRN.inserir(aRN.getRaiz(),30);
        aRN.inserir(aRN.getRaiz(),5);
        aRN.inserir(aRN.getRaiz(),50);
        aRN.inserir(aRN.getRaiz(),15);
        aRN.inserir(aRN.getRaiz(),25);
        aRN.inserir(aRN.getRaiz(),1);
        aRN.inserir(aRN.getRaiz(),60);
        aRN.inserir(aRN.getRaiz(),45);
        aRN.inserir(aRN.getRaiz(),70);*/
        
        //teste 4
        /*aRN.inserir(aRN.getRaiz(), 20);
        aRN.inserir(aRN.getRaiz(), 10);
        aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 5);
        aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 15);
        aRN.inserir(aRN.getRaiz(), 25);
        aRN.inserir(aRN.getRaiz(), 2);
        aRN.inserir(aRN.getRaiz(), 60);
        aRN.inserir(aRN.getRaiz(), 45);
        aRN.inserir(aRN.getRaiz(), 70);
        aRN.inserir(aRN.getRaiz(), 1);
        aRN.inserir(aRN.getRaiz(), 80);*/
        
        // teste 5 e 6
        /*aRN.inserir(aRN.getRaiz(), 1);
        aRN.inserir(aRN.getRaiz(), 2);
        aRN.inserir(aRN.getRaiz(), 3);*/
        
        // teste 7
        /*aRN.inserir(aRN.getRaiz(), 20);
        aRN.inserir(aRN.getRaiz(), 10);
        aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 5);
        aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 15);
        aRN.inserir(aRN.getRaiz(), 25);
        aRN.inserir(aRN.getRaiz(), 1);
        aRN.inserir(aRN.getRaiz(), 60);
        aRN.inserir(aRN.getRaiz(), 45);
        aRN.inserir(aRN.getRaiz(), 70);
        aRN.inserir(aRN.getRaiz(), 80);*/
        
        // teste 8
        /*aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 40);
        aRN.inserir(aRN.getRaiz(), 30);*/
        
        //teste 9
        /*aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 40);
        aRN.inserir(aRN.getRaiz(), 51);*/
        
        // teste 10
        /*aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 40);
        aRN.inserir(aRN.getRaiz(), 35);*/
        
        // teste 11
        /*aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 20);
        aRN.inserir(aRN.getRaiz(), 25);*/
        
        //teste 12
        /*aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 20);
        aRN.inserir(aRN.getRaiz(), 80);
        aRN.inserir(aRN.getRaiz(), 70);
        aRN.inserir(aRN.getRaiz(), 60);*/
        
        // teste 13
        /*aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 80);
        aRN.inserir(aRN.getRaiz(), 40);
        aRN.inserir(aRN.getRaiz(), 45);*/
        
        // teste 14
        /*aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 80);
        aRN.inserir(aRN.getRaiz(), 40);
        aRN.inserir(aRN.getRaiz(), 35);*/
        
        // teste 15
        /*aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 80);
        aRN.inserir(aRN.getRaiz(), 70);
        aRN.inserir(aRN.getRaiz(), 75);*/
        
        // teste 16
        /*aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 80);
        aRN.inserir(aRN.getRaiz(), 20);
        aRN.inserir(aRN.getRaiz(), 25);*/
        
        // teste 17
        /*aRN.inserir(aRN.getRaiz(), 50);
        aRN.inserir(aRN.getRaiz(), 30);
        aRN.inserir(aRN.getRaiz(), 80);
        aRN.inserir(aRN.getRaiz(), 90);
        aRN.inserir(aRN.getRaiz(), 85);*/
        
        
        out.println(aRN.getRaiz());
        emOrdem(aRN.getRaiz());
                
    }   
}
