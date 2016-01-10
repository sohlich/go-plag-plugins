/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.c.preprocesor;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author radek
 */
public class RegexPreprocesorTest {

    String input;

    public RegexPreprocesorTest() {
    }

    @Before
    public void load() throws IOException {
        //Loads example cpp
        InputStream is = this.getClass().getResourceAsStream("/test/testfile.cpp");
//        InputStream is = this.getClass().getResourceAsStream("/test/fcepole.c");
        input = IOUtils.toString(is);
    }

    /**
     * Trivial to test if preprocessor removes anything from sourcefile
     *
     * @throws IOException
     */
    @Test
    public void testParseSourceCode() throws IOException {
        System.out.println("Test remove comments and whitespaces");
        RegexPreprocesor preprocessor = new RegexPreprocesor(input);
        String output = preprocessor.processAll();

        System.out.println(output);
//        Assert that
        assertFalse(input.equals(output));
    }
}
