/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.cluster;

import cz.fai.utb.data.dto.SimplifiedFileDTO;
import java.util.List;

/**
 *
 * @author Radek
 */
public class ClusterBuilder {

    Clusterizer clusterizer;
    String clustererType;
    Double epsilon;
    Integer minPoints;

    public static final String DBSCAN = "DBSCAN";
    public static final String OPTICS = "OPTICS";
    public static final String KMEANS = "KMEANS";

    public static ClusterBuilder getInstance(List<SimplifiedFileDTO> lst) throws Exception {
        return new ClusterBuilder(lst);
    }

    public ClusterBuilder(List<SimplifiedFileDTO> lst) throws Exception {
        this.clusterizer = new Clusterizer(lst);
        this.clustererType = ClusterBuilder.OPTICS;
    }

    public void setClusterer(String type) throws Exception {
        this.clustererType = type;
    }
    
    public void setEpsilon(Double epsilon) throws Exception {
        this.epsilon = epsilon;
    }
    
    public void setMinPoints(Integer minPoints) throws Exception {
        this.minPoints = minPoints;
    }

    public Clusterizer build() throws Exception {
        clusterizer.setClusterer(clustererType,epsilon,minPoints);
        clusterizer.buildInstances();
        clusterizer.process();
        return clusterizer;
    }

}
