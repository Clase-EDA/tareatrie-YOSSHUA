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
    public Trie(){                
        root = new NodoTrie();
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
        StringBuilder r = new StringBuilder();
        toString(root, r, "");
        return r.toString();
        
    }
    private void toString(NodoTrie act, StringBuilder resp, String palA){        
        if(act.getFin())
            resp.append(palA + "\n");
        for(int i = 0; i < 256; i++){            
            if(act.getSig((char) i) != null){
                if(act.getMaxHijo() > i){
                    toString(act.getSig((char)i), resp, palA + (char)i);
                }
            }
        }
    }
}
