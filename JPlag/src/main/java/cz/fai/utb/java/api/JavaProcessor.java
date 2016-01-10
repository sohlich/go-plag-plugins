/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.java.api;

import cz.fai.utb.java.lexer.JavaLexerImpl;
import cz.fai.utb.java.preprocesor.RegexPreprocesor;
import cz.fai.utb.lang.api.LanguageProcessor;
import cz.fai.utb.lang.api.ParseResultWrapper;
import cz.fai.utb.lang.api.Preprocessor;


/**
 *
 * @author Radek
 */
public class JavaProcessor implements LanguageProcessor {

    @Override
    public ParseResultWrapper parseSource(String file) {
        Preprocessor processor = new RegexPreprocesor(file);
        file = processor
                .removeComments()
                .process();

        JavaLexerImpl lexer = new JavaLexerImpl();
        lexer.nGramize(file, 4);
        
        ParseResultWrapper result = new ParseResultWrapper(lexer.getnGrams(),new double[]{lexer.getLoopCount(),lexer.getIdentifierCount(),lexer.getIfCount()});
       return result;
    }

    @Override
    public String getLang() {
        return "JAVA";
    }

    @Override
    public String[] getExtensions() {
       return new String[]{"java"};
    }
}
