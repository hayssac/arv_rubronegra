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
    
    public No getRaiz(){
        return this.noRaiz;
    }
    
    public No inserir(No no, int chave){
        
        if(noRaiz == null){
            no = new No();
            no.setChave(chave);
            no.setCor("black");
            noRaiz = no;
            
            return noRaiz;
        }else{
            if(chave < no.getChave()){ //lado esquerdo
                if(no.getFilho_Esquerdo()!=null){
                    inserir(no.getFilho_Esquerdo(), chave);
                } else {
                    No novoNo = new No();
                    novoNo.setChave(chave);
                    novoNo.setCor("red");
                    novoNo.setPai(no);
                    no.setFilho_Esquerdo(novoNo);
                    
                    if(no.getCor() == "red"){
                        rebalancearInsercao(no.getFilho_Esquerdo());
                    }
                   
                }
                return null;
               
            } else { // lado direito
                
                if(no.getFilho_Direito()!=null){
                    inserir(no.getFilho_Direito(), chave);
                } else {
                    No novoNo = new No();
                    novoNo.setChave(chave);
                    novoNo.setCor("red");
                    novoNo.setPai(no);
                    no.setFilho_Direito(novoNo);
                    
                    if(no.getCor() == "red"){
                        rebalancearInsercao(no.getFilho_Direito());
                    }
                }
                return null;
                
            }
        }
    }
    
    private void rebalancearInsercao(No inserido) {
        //Entrou aqui pois o PAI dele é RED. (duplo red)
        No pai = inserido.getPai();
        No avo = pai.getPai();
        
        if(avo.getCor() == "black"){
            if(avo.getFilho_Direito() == pai){ // O tio é filho esquerdo do AVÓ.
                No tio = avo.getFilho_Esquerdo();
                if(tio.getCor()=="red"){
                    tio.setCor("black");
                    pai.setCor("black");
                    if(avo.getPai()!=null){
                        avo.setCor("red");
                    }
                } else { // Se o tio for black.
                    if(pai.getFilho_Direito() == inserido){
                        rotacaoSimplesEsquerda();
                    }else{
                        rotacaoDuplaEsquerda();
                    }
                }
                
            } else {
                No tio = avo.getFilho_Direito();
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
            emOrdem(raiz.getFilho_Esquerdo());
            out.print(raiz.getChave() + " " + raiz.getCor());
            emOrdem(raiz.getFilho_Direito());
        }
    }
    
    public static void main(String[] args) {
        Arvore_RubroNegra aRN = new Arvore_RubroNegra();
        
        aRN.inserir(aRN.getRaiz(), 3);
        aRN.inserir(aRN.getRaiz(), 2);
        aRN.inserir(aRN.getRaiz(), 1);
        
        emOrdem(aRN.getRaiz());
                
    }
    


    
    
}
