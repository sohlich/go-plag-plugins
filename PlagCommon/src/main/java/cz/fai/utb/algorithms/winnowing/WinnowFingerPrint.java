/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.algorithms.winnowing;

import cz.fai.utb.algorithms.FingerPrint;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Radek
 */
public class WinnowFingerPrint implements FingerPrint{

    List<Hash> fingerprint = new LinkedList<>();
    int[] fullDoc;

    public void putHash(int index, int hashVal) {
        fingerprint.add(new Hash(index, hashVal));
    }

    public class Hash {

        int index;
        int hashVal;

        public Hash(int index, int hashVal) {
            this.index = index;
            this.hashVal = hashVal;
        }

        @Override
        public String toString() {
            return "Hash{" + "index=" + index + ", hashVal=" + hashVal + '}';
        }

    }

    @Override
    public Integer[] getFingerPrint() {
        Integer[] output = new Integer[fingerprint.size()];
        List<Integer> fingerPrintList = fingerprint.stream().map(hash -> hash.hashVal).collect(Collectors.toList());
        fingerPrintList.toArray(output);
        return output;
    }
    
    @Override
    public Integer[] getIndexFingerPrint() {
        Integer[] output = new Integer[fingerprint.size()];
        List<Integer> fingerPrintList = fingerprint.stream().map(hash -> hash.index).collect(Collectors.toList());
        fingerPrintList.toArray(output);
        return output;
    }
    

    @Override
    public Map<Integer, Integer> getFingerMap() {
        Map<Integer, Integer> fingerPrintList = fingerprint.stream().collect(Collectors.toMap(s -> s.hashVal, s -> 1, Integer::sum));
        return fingerPrintList;
    }

    public void debugPrint(PrintStream ps) {
        for (Hash p : fingerprint) {
            ps.println(p.toString());
        }
    }

}
