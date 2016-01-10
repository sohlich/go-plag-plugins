package cz.fai.utb.ngram.test

import cz.fai.utb.algorithms.winnowing.Winnowing;
import cz.fai.utb.ngram.NGramGenerator;
import groovy.util.GroovyTestCase;
import org.junit.Test

class MyTest extends GroovyTestCase {


    @Test
    void testNgramizer() {

        def generator = new NGramGenerator(4);
        def list = ["11", "12", "14", "15"]

        generator.generate(list)
        println generator.resultList;
        println generator.footPrint;
    }
    
    
    String[] original = ['35;9;100;59;', 
        "9;100;59;35;", 
        "100;59;35;27;", 
        "59;35;27;100;", 
        "35;27;100;63;", 
        "27;100;63;35;", 
        "100;63;35;3;", 
        "63;35;3;100;", 
        "35;3;100;63;", 
        "3;100;63;35;", 
        "100;63;35;27;", 
        "63;35;27;100;", 
        "35;27;100;63;", 
        "27;100;63;35;", 
        "100;63;35;27;", 
        "63;35;27;100;", 
        "35;27;100;63;", 
        "27;100;63;35;", 
        "100;63;35;100;", 
        "63;35;100;57;", 
        "35;100;57;27;", 
        "100;57;27;100;", 
        "57;27;100;58;", 
        "27;100;58;59;", 
        "100;58;59;100;", 
        "58;59;100;66;", 
        "59;100;66;100;", 
        "100;66;100;63;", 
        "66;100;63;100;", 
        "100;63;100;66;", 
        "63;100;66;53;", 
        "100;66;53;63;", 
        "66;53;63;100;", 
        "53;63;100;66;", 
        "63;100;66;82;", 
        "100;66;82;51;", 
        "66;82;51;63;", 
        "82;51;63;100;", 
        "51;63;100;66;", 
        "63;100;66;82;", 
        "100;66;82;51;", 
        "66;82;51;63;", 
        "82;51;63;60;", 
        "51;63;60;60;", 
        "63;60;60;0;", 
        "60;60;0;0;", 
        "60;0;0;0;"
    ];
    
    @Test
    public void testWinnowing(){
         Winnowing w = new Winnowing();
        int[] out = w.createHash(original);
        out.each{ object ->
            println  object;
        }
    }


}