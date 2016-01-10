/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.cluster;

import cz.fai.utb.data.dto.SimplifiedFileDTO;
import java.util.List;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;

/**
 *
 * @author radek
 */
public class SimpleDBSCANClusterer {
    
    double epsilon;
    int minPoints;

    public SimpleDBSCANClusterer(double epsilon, int minPoints) {
        this.epsilon = epsilon;
        this.minPoints = minPoints;
    }

    public List<Cluster<SimplifiedFileDTO>> clusterize(List<SimplifiedFileDTO> files) {
        DBSCANClusterer dbscan = new DBSCANClusterer(epsilon, minPoints);
        List<Cluster<SimplifiedFileDTO>> cluster = dbscan.cluster(files);
        return cluster;
    }

}
