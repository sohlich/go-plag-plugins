/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.ngram;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *Document to store parsed source codes. The content is footprint of source code. 
 *Complete sequence, the footprint is generated from.
 * 
 * @author Radek
 */
public class NGramDocument {

    Long id;
    HashMap<String, Integer> footprint = new LinkedHashMap<>();
    List<String> sequence = new LinkedList<>();
    String fileName;

    public void push(NGram nGram) {
        Integer count = footprint.get(nGram.getValue());
        sequence.add(nGram.toString());

        if (count == null) {
            footprint.put(nGram.getValue(), 1);
        } else {
            footprint.put(nGram.getValue(), ++count);
        }
    }

    public HashMap<String, Integer> getFootprint() {
        return footprint;
    }

    public void addSequence(List<String> sequence) {
        this.sequence.addAll(sequence);
    }

    public List<String> getSequence() {
        return sequence;
    }

    public void setSequence(List<String> seq) {
        this.sequence = seq;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.footprint.keySet());
        hash = 97 * hash + Objects.hashCode(this.sequence);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NGramDocument other = (NGramDocument) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.footprint, other.footprint)) {
            return false;
        }
        if (!Objects.equals(this.sequence, other.sequence)) {
            return false;
        }
        return true;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
