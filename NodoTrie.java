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
    private static Character[] simb;
    private NodoTrie<T>[] hijos;
    private boolean finPal; 
    private int maxHijo=-1;
    
    public NodoTrie(Character[] simbolos){
         hijos = new NodoTrie[simbolos.length];
         simb = simbolos;
         finPal = false;
    }
    public NodoTrie(){
         hijos = new NodoTrie[simb.length];                 
         finPal = false;
    }
    public boolean getFin(){
        return finPal;
    }
    public void setFin(boolean r){
        finPal= r;
        
    }       
    public NodoTrie<T> getSig(char letra){
        int i = 0;
        while(i < simb.length && letra != simb[i] )
            i++;
        if(i == simb.length)
            throw new NullPointerException("No existe el simbolo");
        
        return hijos[i];
    }    
    public void setSig(NodoTrie<T> a, char l){
        int i = 0;
        while(i < simb.length && l != simb[i] )
            i++;
        if(i == simb.length)
            throw new NullPointerException("No existe el simbolo");
        else{
            hijos[i] = a;
            if(i > maxHijo)
                maxHijo = i;
        }
    }
    
    public boolean estaVacio(){
        int i = 0;
        while(i < simb.length && hijos[i] == null )
            i++;
        return i == simb.length;
    }
    public int getMaxHijo(){
        return maxHijo;
    }
}
