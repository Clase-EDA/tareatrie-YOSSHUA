/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolTrie;

import java.util.ArrayList;
import static java.util.Arrays.sort;

/**
 *
 * @author yocis
 */
public class Trie <T extends Comparable<Trie>>{
    private NodoTrie<T> root;
    public static Character[] simbolos;
    public Trie(Character[] sim){
        sort(sim);
        simbolos = sim.clone();
        root = new NodoTrie(sim);
    }   
    
    public boolean busca(String word){
        int l = word.length(), i = 1;
        boolean resp = true;
        NodoTrie act = root;        
        if(l >= 1){
            resp = false;
            act = act.getSig(word.charAt(0));
            while(act != null && i < l){
                act = act.getSig(word.charAt(i));
                i++;
            }
            if(act != null){
                resp = act.getFin();
            }            
        }
        return resp;
    }
    
    public void insert(String pal){
        if(!pal.equals(""))
            insert(root, pal, 0);
        
    }
    
    private void  insert(NodoTrie<T> n, String pal, int index){
        if(index == pal.length()-1){            
            NodoTrie temp = n.getSig(pal.charAt(index));
            if(temp == null){
                temp = new NodoTrie();
                n.setSig(temp, pal.charAt(index));
            }
            temp.setFin(true);            
            return ;
        }        
        NodoTrie temp = n.getSig(pal.charAt(index));
        
        if(temp == null){
            temp = new NodoTrie();
            n.setSig(temp, pal.charAt(index));
        }
        
        insert(temp, pal, index+1);        
    }
    
    public int remove(String pal){
        if(root == null || pal == "")
            return -1;        
        int r = remove(root, pal, 0);                
        return r;        
    }
    private int remove(NodoTrie<T> n, String pal, int index){
        if(index == pal.length()-1){
            NodoTrie<T> temp = n.getSig(pal.charAt(index));
            if(temp == null)
                return 0;
            else{
                if(temp.getFin()){
                    temp.setFin(false);   
                    if(temp.estaVacio()){
                        n.setSig(null, pal.charAt(index));                                           
                        return 1;
                    }
                    return 2;
                }
                return -1;
            }            
        }
        NodoTrie<T> temp = n.getSig(pal.charAt(index));
        if(temp == null)
            return 0;
        else{
            int r = remove(temp, pal, index+1);            
            if(!temp.getFin() && r == 1){                
                n.setSig(null, pal.charAt(index));                
            }
            return r;
        }        
    }
    public String toString(){
        return toString(root, new StringBuilder(), "");
    }
    private String toString(NodoTrie act, StringBuilder resp, String palA){        
        for(int i = 0; i < simbolos.length; i++){            
            NodoTrie aux = act;            
            NodoTrie aux2 = aux.getSig(simbolos[i]);
                        
            if(aux2 != null){                                
                
                if(aux2.getFin()){
                    resp.append(palA+simbolos[i] +'\n');                    
                }
                toString(aux2, resp, palA+simbolos[i]);
            }
            if(aux.getMaxHijo()!= -1){
                    if(i > aux.getMaxHijo())
                        break;
            }else{
                break;
            }
            
        }
        return resp.toString();
    }
}
