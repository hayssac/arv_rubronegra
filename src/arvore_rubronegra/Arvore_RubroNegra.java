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
                    }
                } else { // Se o tio for black OU null, que também se considera black
                    if(pai.getFilho_Direito() == inserido){
                        rotacaoSimplesEsquerda();
                    }else{
                        rotacaoDuplaEsquerda();
                    }
                }
                
            } else { // O tio é filho direito do AVÔ pois o pai é filho esquerdo
                No tio = avo.getFilho_Direito();
                
                if (tio.getCor().equals("red")) {
                    tio.setCor("black");
                    pai.setCor("black");
                    if (avo.getPai() != null) {
                        avo.setCor("red");
                    }
                } else { // Se o tio for black
                    if (pai.getFilho_Esquerdo() == inserido) {
                        rotacaoSimplesDireita();
                    } else {
                        rotacaoDuplaDireita();
                    }
                }
            }
        }
         
    }
    
    private void recolorir(No inserido){
        
    }
    
    private void rotacaoSimplesDireita(){
        out.println("rotação simples direita");
    }
    
    private void rotacaoSimplesEsquerda(){
        out.println("rotação simples esquerda");
    }
    
    private void rotacaoDuplaDireita(){
        out.println("rotação dupla direita");
    }
    
    private void rotacaoDuplaEsquerda(){
        out.println("rotação dupla esquerda");
    }

    public static void emOrdem(No raiz) {
        if(raiz != null) {
//            if (raiz.getChave() != -1) { // ignore a impressão de nós nulos
                emOrdem(raiz.getFilho_Esquerdo());
                out.print(raiz.toString());
                emOrdem(raiz.getFilho_Direito());
  //          }
            
        }
    }
    
    public static void main(String[] args) {
        Arvore_RubroNegra aRN = new Arvore_RubroNegra();
        
        aRN.inserir(aRN.getRaiz(), 1);
        aRN.inserir(aRN.getRaiz(), 2);
        aRN.inserir(aRN.getRaiz(), 3);
        
        emOrdem(aRN.getRaiz());
                
    }
    


    
    
}
