/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolTrie;



/**
 *
 * @author yocis
 */
public class NodoTrie<T> {
    
    private NodoTrie<T>[] hijos;
    private boolean finPal; 
    private int maxHijo=-1;
        
    public NodoTrie(){
         hijos = new NodoTrie[256];                 
         finPal = false;
    }
    public boolean getFin(){
        return finPal;
    }
    public void setFin(boolean r){
        finPal= r;
        
    }       
    public NodoTrie<T> getSig(char letra){                
        return hijos[(int)letra];
    }    
    public void setSig(NodoTrie<T> a, char l){
        int pos = (int)l;
        hijos[pos] = a;                            
        if(pos > maxHijo)
            maxHijo = pos;        
        
    }
    
    public boolean estaVacio(){
        int i = 0;
        while(i < 256 && hijos[i] == null)
            i++;
        return i == 256;
    }
    public int getMaxHijo(){
        return maxHijo;
    }
}
