/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.algorithms;

/**
 * Generic interface for fingerprinting algorithms.
 * 
 * @author radek
 */
public interface FingerPrintAlgorithm {
    public FingerPrint processTokensToFingerPrint(String[] hashes);
}
