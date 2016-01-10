/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.data.dto;

import org.apache.commons.math3.ml.clustering.Clusterable;

/**
 *
 * @author Radek
 */
public class SimplifiedFileDTO implements Clusterable {

    String id;
    double[] clusterAttributes;

    public SimplifiedFileDTO() {
    }

    public SimplifiedFileDTO(String id, double[] metrics) {
        this.id = id;
        this.clusterAttributes = metrics;
    }

    @Override
    public double[] getPoint() {
        return clusterAttributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double[] getClusterAttributes() {
        return clusterAttributes;
    }

    public void setClusterAttributes(double[] metrics) {
        this.clusterAttributes = metrics;
    }

    @Override
    public String toString() {
        return "SimplifiedFileDTO{" + "id=" + id + '}';
    }
   
}
