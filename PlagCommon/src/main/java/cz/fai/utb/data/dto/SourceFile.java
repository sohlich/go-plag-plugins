/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.data.dto;

import cz.fai.utb.ngram.NGramDocument;

/**
 *
 * @author Radek
 */
public class SourceFile {
    byte[] file;
    NGramDocument nGramDoc;
    String assignmentId;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public NGramDocument getnGramDoc() {
        return nGramDoc;
    }

    public void setnGramDoc(NGramDocument nGramDoc) {
        this.nGramDoc = nGramDoc;
    }
}
