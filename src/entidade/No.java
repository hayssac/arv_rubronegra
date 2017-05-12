/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author adrianny
 */
public class No {
    private int chave;
    private No pai;
    private No filho_Esquerdo;
    private No filho_Direito;
    private String cor;
    private boolean duplo;

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getFilho_Esquerdo() {
        return filho_Esquerdo;
    }

    public void setFilho_Esquerdo(No filho_Esquerdo) {
        this.filho_Esquerdo = filho_Esquerdo;
    }

    public No getFilho_Direito() {
        return filho_Direito;
    }

    public void setFilho_Direito(No filho_Direito) {
        this.filho_Direito = filho_Direito;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isDuplo() {
        return duplo;
    }

    public void setDuplo(boolean duplo) {
        this.duplo = duplo;
    }
    
    @Override
    public String toString() {
        return chave + " " + cor; 
    
    }    
    
}
