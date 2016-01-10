/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.algorithms.winnowing;

import cz.fai.utb.algorithms.FingerPrint;
import cz.fai.utb.algorithms.FingerPrintAlgorithm;

/**
 *
 * @author Radek
 */
public class Winnowing implements FingerPrintAlgorithm{

    private final int DEFAULT_WINDOW = 4;

    public int[] createHash(String[] ngrams) {
        int[] output = new int[ngrams.length];

        int index = 0;
        for (String ngram : ngrams) {
            output[index++] = ngram.hashCode();
        }
        return output;
    }

    /**
     *
     * @param input array of k-gram hashes
     * @param w window length
     * @return
     */
    public WinnowFingerPrint createFingerPrint(int[] input, int w) {
        WinnowFingerPrint fp = new WinnowFingerPrint();
        int n = input.length;
        int lastMin = -1;

        for (int i = 0; i < n - w + 1; i++) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int j = 0; j < w; j++) {
                if (input[i + j] < min) {
                    min = input[i + j];
                    index = i + j;
                }
            }

            if (lastMin != index) {
                fp.putHash(index, min);
                lastMin = index;
            }
        }
        return fp;
    }

    /**
     * Provides default procedure from String nGrams to FingerPrint
     * @param ngrams
     * @return 
     */
    @Override
    public FingerPrint processTokensToFingerPrint(String[] ngrams) {
        int[] hashes = createHash(ngrams);
        return createFingerPrint(hashes, DEFAULT_WINDOW);
    }
}
