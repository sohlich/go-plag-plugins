/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.java.lexer;

import cz.fai.utb.java.parser.Java8Parser;
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
public class JavaLexerImpl extends LexerBase {

    //lexer splits input into tokens
    public TokenStream process(String inputString) {
        CharStream input = new JavaUnicodeInputStream(new ANTLRInputStream(inputString));
        Lexer lex = new Java8Lexer(input);
        lex.removeErrorListeners();
        TokenStream tokens = new CommonTokenStream(lex);
        return tokens;
    }

    //lexer splits input into tokens
    public String generateAST(String inputString) {
        CharStream input = new JavaUnicodeInputStream(new ANTLRInputStream(inputString));
        TokenStream tokens = new CommonTokenStream(new Java8Lexer(input));

        Java8Parser parser = new Java8Parser(tokens);
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
                case 21:
                case 50:
                case 13:
                    loopCount++;
                    break;
                case 100:
                    identifierCount++;
                    break;
                case 22:
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

        for (int i = 0; i < tokens.length; i++) {
            NGram ngram = new NGram(n, tokens, i);
            nGrams.add(ngram.toString());
        }
    }
}
