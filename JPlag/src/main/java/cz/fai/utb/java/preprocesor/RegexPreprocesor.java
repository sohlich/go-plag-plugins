/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.java.preprocesor;

import cz.fai.utb.lang.api.Preprocessor;

/**
 *
 * @author Radek
 */
public class RegexPreprocesor implements Preprocessor {

    private String input;
    static String commentRegex = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)";
    static String annotations = "[@]{1}.*";
    static String newLineRegex = "^(.*)(\\r?\\n\\1)+$";

    public RegexPreprocesor() {
        this.input = "";
    }

    public RegexPreprocesor(String input) {
        this.input = input;
    }

    @Override
    public RegexPreprocesor removeComments() {
        input = input.replaceAll(commentRegex, "");
        return this;
    }

    public RegexPreprocesor removeAnnotations() {
        input = input.replaceAll(annotations, "");
        return this;
    }

//    public RegexPreprocesor removeWhiteSpaces() {
//        input = input.replaceAll("\\s+" + System.lineSeparator(), "");
//        return this;
//    }
    @Override
    public String process() {
        return input;
    }

    @Override
    public String process(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String processAll() {
        return this
                .removeComments()
                .removeAnnotations()
                .process();
    }

}
