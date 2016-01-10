/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.match.jaccard;

import cz.fai.utb.ngram.NGramDocument;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Radek
 */
public class Jaccard {

    public static float compute(NGramDocument a, NGramDocument b) {
        Set<String> keysA = a.getFootprint().keySet();
        Set<String> keysB = b.getFootprint().keySet();
        Collection<String> intersection = CollectionUtils.intersection(keysB, keysA);
        Integer interCount = intersection.stream().map((string) -> a.getFootprint().get(string) + b.getFootprint().get(string)).reduce(0, Integer::sum);
        float totalBlocks = a.getSequence().size() + b.getSequence().size();
        float jaccard = (float) interCount / (float) (totalBlocks);
        return jaccard;
    }

//    public static double compute(Integer[] a, Integer[] b) {
//        Set<Integer> union = new HashSet<>();
//        Set<Integer> intersection = new HashSet<>();
//
//        union.addAll(Arrays.asList(b));
//        intersection.addAll(Arrays.asList(a));
//
//        intersection.retainAll(union);
//        union.addAll(Arrays.asList(a));
//        return (double) intersection.size() / union.size();
//
//    }

    public static float compute(Map<String, Integer> a, Map<String, Integer> b) {

//        Map<String, Integer> mapA
//                = a.entrySet()
//                .stream()
//                .filter(p -> p.getValue() != 0)
//                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
//
//        Map<String, Integer> mapB
//                = b.entrySet()
//                .stream()
//                .filter(p -> p.getValue() != 0)
//                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        Set<String> keysA = a.keySet();
        Set<String> keysB = b.keySet();

        Collection<String> intersection = CollectionUtils.intersection(keysB, keysA);
        
        Integer interCount = intersection.stream().map((string) -> Math.min(a.get(string),b.get(string))).reduce(0, Integer::sum);

        int countA = 0;
        countA = a.values().stream().map((val) -> val).reduce(countA, Integer::sum);

        int countB = 0;
        countB = b.values().stream().map((val) -> val).reduce(countB, Integer::sum);

        float totalBlocks = countA + countB - interCount;

        float jaccard = (float) interCount / (float) (totalBlocks);

        if (System.getProperty("plagdetector.debug") != null && System.getProperty("plagdetector.debug").equals("true")) {
            System.out.println(String.format("Jaccarduv index podobnosti výpočet interCount: %d  , totalBlocks %f, vysledek %f", interCount, totalBlocks, jaccard));
        }

        return jaccard;
    }

}
