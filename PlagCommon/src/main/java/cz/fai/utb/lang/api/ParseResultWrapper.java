/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.lang.api;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author radek
 */
public class ParseResultWrapper {

    List<String> nGrams;
    double[] metrics;

    public ParseResultWrapper() {
    }
    
    public ParseResultWrapper(List<String> nGrams, double[] metrics) {
        this.nGrams = nGrams;
        this.metrics = metrics;
    }
    
    public List<String> getnGrams() {
        return nGrams;
    }

    public void setnGrams(List<String> nGrams) {
        this.nGrams = nGrams;
    }

    public double[] getMetrics() {
        return metrics;
    }

    public void setMetrics(double[] metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        return "ParseResultWrapper{" + "nGrams=" + nGrams.size() + ", metrics=" + Arrays.toString(metrics) + '}';
    }
    
    
}
