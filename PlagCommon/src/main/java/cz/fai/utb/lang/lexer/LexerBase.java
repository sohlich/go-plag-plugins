/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.lang.lexer;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author radek
 */
public abstract class LexerBase {
    protected List<String> nGrams = new LinkedList<>();
    protected int loopCount;
    protected int ifCount;
    protected int identifierCount;

    public List<String> getnGrams() {
        return nGrams;
    }

    public void setnGrams(List<String> nGrams) {
        this.nGrams = nGrams;
    }

    public int getLoopCount() {
        return loopCount;
    }

    public void setLoopCount(int loopCount) {
        this.loopCount = loopCount;
    }

    public int getIfCount() {
        return ifCount;
    }

    public void setIfCount(int ifCount) {
        this.ifCount = ifCount;
    }

    public int getIdentifierCount() {
        return identifierCount;
    }

    public void setIdentifierCount(int identifierCount) {
        this.identifierCount = identifierCount;
    }

   
    
    
    
}
