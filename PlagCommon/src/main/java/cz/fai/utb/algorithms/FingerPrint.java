/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.algorithms;

import java.util.Map;

/**
 * The fingerprint generated from @link cz.fai.utb.algorithms.FingerPrintAlgorithm
 * @author radek
 */
public interface FingerPrint {
    public Integer[] getFingerPrint();
    public Integer[] getIndexFingerPrint();
    public Map<Integer, Integer> getFingerMap();
   
}
