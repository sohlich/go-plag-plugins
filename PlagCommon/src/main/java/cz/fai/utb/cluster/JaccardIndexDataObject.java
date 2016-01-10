/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.cluster;

import cz.fai.utb.match.jaccard.Jaccard;
import java.util.LinkedHashMap;
import java.util.Map;
import weka.clusterers.forOPTICSAndDBScan.DataObjects.DataObject;
import weka.clusterers.forOPTICSAndDBScan.Databases.Database;
import weka.core.Instance;

/**
 *
 * @author Radek
 */
public class JaccardIndexDataObject extends weka.clusterers.forOPTICSAndDBScan.DataObjects.EuclideanDataObject {

    public JaccardIndexDataObject(Instance originalInstance, String key, Database database) {
        super(originalInstance, key, database);
    }

    @Override
    public double distance(DataObject dataObject) {

        double dist = 0.0;

        if (!(dataObject instanceof weka.clusterers.forOPTICSAndDBScan.DataObjects.EuclideanDataObject)) {
            return Double.NaN;
        }

        if (getInstance().equalHeaders(dataObject.getInstance())) {

            double[] local = getInstance().toDoubleArray();
            double[] compared = dataObject.getInstance().toDoubleArray();

            Map<String, Integer> localMap = new LinkedHashMap<>();
            Map<String, Integer> comparedMap = new LinkedHashMap<>();

            for (int i = 0; i < compared.length; i++) {
                localMap.put(String.valueOf(i), (int) (local[i]*100));
                comparedMap.put(String.valueOf(i), (int) (compared[i]*100));
            }
            
            double similarity = Jaccard.compute(localMap, comparedMap);
            
            //System.out.println("Jaccard index: "+similarity);
            
            return similarity;
        }
        return Double.NaN;
    }

}
