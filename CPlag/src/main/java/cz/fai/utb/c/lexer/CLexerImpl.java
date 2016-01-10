/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.c.lexer;

import cz.fai.utb.c.parser.CParser;
import cz.fai.utb.lang.lexer.LexerBase;
import cz.fai.utb.ngram.NGram;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author Radek
 */
public class CLexerImpl extends LexerBase {

    //lexer splits input into tokens
    public TokenStream process(String inputString) {
        CharStream input = new CUnicodeInputStream(new ANTLRInputStream(inputString));
        Lexer lex = new CLexer(input);
        lex.removeErrorListeners();
        TokenStream tokens = new CommonTokenStream(lex);
        return tokens;
    }

    //lexer splits input into tokens
    public String generateAST(String inputString) {
        CharStream input = new CUnicodeInputStream(new ANTLRInputStream(inputString));
        TokenStream tokens = new CommonTokenStream(new CLexer(input));

        CParser parser = new CParser(tokens);
        ParseTree tree = parser.compilationUnit();

        return tree.toStringTree(parser);
    }

    public String[] tokenize(String inputString) {
        TokenStream tokens = process(inputString);
        List<String> tokenList = new ArrayList<>();

        TokenSource source = tokens.getTokenSource();

        while (true) {
            Token t = source.nextToken();
            if (t.getText().contains("EOF")) {
                break;
            }

            int type = t.getType();
            switch (type) {
                case 28:
                case 48:
                case 22:
                    loopCount++;
                    break;
                case 105:
                    identifierCount++;
                    break;
                case 30:
                    ifCount++;
                    break;
            }
            tokenList.add(String.valueOf(type));
        }

        String[] stockArr = new String[tokenList.size()];
        stockArr = tokenList.toArray(stockArr);
        return stockArr;
    }

    public void nGramize(String inputString, int n) {
        String[] tokens = tokenize(inputString);

        //document = new NGramDocument();
        for (int i = 0; i < tokens.length; i++) {
            NGram ngram = new NGram(n, tokens, i);
            nGrams.add(ngram.toString());
        }

        //return document;
    }

}
