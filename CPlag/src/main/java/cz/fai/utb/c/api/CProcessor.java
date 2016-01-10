/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.c.api;

import cz.fai.utb.c.lexer.CLexerImpl;
import cz.fai.utb.c.preprocesor.RegexPreprocesor;
import cz.fai.utb.lang.api.LanguageProcessor;
import cz.fai.utb.lang.api.ParseResultWrapper;
import cz.fai.utb.lang.api.Preprocessor;

/**
 *
 * @author Radek
 */
public class CProcessor implements LanguageProcessor {

  
    @Override
    public ParseResultWrapper parseSource(String file) {
        Preprocessor processor = new RegexPreprocesor(file);
        file = processor
                .removeComments()
                .process();

        CLexerImpl lexer = new CLexerImpl();
        lexer.nGramize(file, 4);

        ParseResultWrapper result = new ParseResultWrapper(lexer.getnGrams(), new double[]{lexer.getLoopCount(), lexer.getIdentifierCount(), lexer.getIfCount()});

        return result;
    }

    @Override
    public String getLang() {
        return "C";
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"cpp", "c"};
    }
}
