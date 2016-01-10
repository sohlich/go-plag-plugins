/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.cluster;

import cz.fai.utb.cluster.*;
import cz.fai.utb.ngram.NGramDocument;
import cz.fai.utb.data.dto.SimplifiedFileDTO;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.clusterers.DBSCAN;
import weka.clusterers.OPTICS;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Normalize;

/**
 *
 * @author Radek
 */
public class Clusterizer {

    List<SimplifiedFileDTO> documents;
    ClusterEvaluation eval;
    Clusterer clusterer;
    Instances data;

    Clusterizer(List<SimplifiedFileDTO> inputDocuments) throws Exception {
        this.documents = inputDocuments;
        eval = new ClusterEvaluation();
        //clusterer.setOptions(new String[]{"-L", "COMPLETE"});
    }

    Instances buildInstances() throws Exception {

        //Gather all nGrams
//        HashSet<String> allNgrams = new LinkedHashSet();
//        for (SimplifiedFileDTO doc : documents) {
//            allNgrams.addAll(doc.getFootPrint().keySet());
//        }
//
//        //Generate filling map
//        HashMap<String, Integer> allNgramMap = new LinkedHashMap<>();
//        for (String nGram : allNgrams) {
//            allNgramMap.put(nGram, 0);
//        }
//
//        //Fill
//        for (SimplifiedFileDTO doc : documents) {
//            doc.mergeFootprint(allNgramMap);
//        }
//
//        //Create vectors
//        double[][] paramList = new double[documents.size()][allNgrams.size()];
//        Object[] allNGramsArray = allNgrams.toArray();
//        for (int i = 0; i < allNgrams.size(); i++) {
//            {
//                for (int j = 0; j < documents.size(); j++) {
//                    String key = String.valueOf(allNGramsArray[i]);
//                    paramList[j][i] = documents.get(j).getFootPrint().get(key);
//                }
//            }
//        }
//
//        data = buildArff(paramList, allNgrams);
//
//        //Normalize training data
//        Normalize norm = new Normalize();
//        norm.setInputFormat(data);
//        data = Filter.useFilter(data, norm);
//
        return data;
    }

    void process() throws Exception {
        // Cluster network
        clusterer.buildClusterer(data);
        eval.setClusterer(clusterer);
        eval.evaluateClusterer(data);
    }

    public String clusterResultsToString() {
        return eval.clusterResultsToString();
    }

    public void setEval(ClusterEvaluation eval) {
        this.eval = eval;
    }

    public Clusterer getClusterer() {
        return clusterer;
    }

    public void setClusterer(String clusterer,Double epsilon,Integer minPoints) throws Exception {
        this.clusterer = createClusterer(clusterer,epsilon,minPoints);
    }

    public static Instances buildArff(double[][] array, HashSet<String> allNgrams) throws Exception {
        FastVector atts = new FastVector();
        for (String nGram : allNgrams) {
            atts.addElement(new Attribute(nGram));
        }
        // 2. create Instances object
        Instances test = new Instances("ngams", atts, array.length);
        // 3. fill with data
        for (double[] nGrams : array) {
            test.add(new Instance(1.0, nGrams));
        }
        return test;
    }

    public HashMap<String, Double> getAssignments() {
        double[] assign = eval.getClusterAssignments();

        HashMap<String, Double> assignments = new HashMap<>();
        for (int i = 0; i < assign.length; i++) {
            assignments.put(documents.get(i).getId(), assign[i]);
        }

        return assignments;
    }

    /**
     * Create a DBSCAN Clusterer.
     *
     * @return The DBSCAN Clusterer.
     */
    private Clusterer createClusterer(String type,Double epsilon,Integer minPoints) throws Exception {
        
        Double epsilonInput = epsilon != null ?epsilon : 0.21;
        Integer minPointsInput = minPoints != null ?minPoints : 2;
        
        switch (type) {
            case "DBSCAN":
                DBSCAN dbscan = new DBSCAN();   // new instance of clusterer
                dbscan.setEpsilon(epsilonInput); //0.21
                dbscan.setMinPoints(minPointsInput);
//                dbscan.setDatabase_Type("weka.clusterers.forOPTICSAndDBScan.Databases.SequentialDatabase");
                dbscan.setDatabase_distanceType("cz.fai.utb.cluster.JaccardIndexDataObject");
                return dbscan;
            case "OPTICS":
                OPTICS optics = new OPTICS();
                //optics.setEpsilon(0.21D);
                //optics.setDatabase_distanceType("cz.fai.utb.cluster.JaccardIndexDataObject");
                optics.setMinPoints(2);
                optics.setShowGUI(false);
                
                return optics;
            default:
                SimpleKMeans kmeans = new SimpleKMeans();
                kmeans.setNumClusters(4);
                kmeans.setDistanceFunction(new EuclideanDistance());
                return kmeans;
        }
    }

}
