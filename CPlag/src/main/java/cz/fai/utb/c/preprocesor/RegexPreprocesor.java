/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.c.preprocesor;

import cz.fai.utb.lang.api.Preprocessor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Radek
 */
public class RegexPreprocesor implements Preprocessor {

    private String input;
    //static String blockComments = "/\\\\*(?:.|[\\\\n\\\\r])*?\\\\*/.*";
    static String blockComments = "(?s)/\\*.+?\\*/";//(?s)/\\\\*.+?\\\\*/";
    static String lineComments = "//.*";
    static String newLineRegex = "^(.*)(\\r?\\n\\1)+$";

    private final Pattern blockCommentRegex;
    private final Pattern lineCommentsRegex;

    public RegexPreprocesor() {
        this("");
    }

    public RegexPreprocesor(String input) {
        this.input = input;
        blockCommentRegex = Pattern.compile(blockComments);
        lineCommentsRegex = Pattern.compile(lineComments);
    }

    @Override
    public RegexPreprocesor removeComments() {
        Matcher matcher = lineCommentsRegex.matcher(input);
        input = matcher.replaceAll("");
        matcher = blockCommentRegex.matcher(input);
        input = matcher.replaceAll("");
        return this;
    }

//    public RegexPreprocesor removeWhiteSpaces(){
//
//
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
                //                .removeAnnotations()
                .process();
    }

}
