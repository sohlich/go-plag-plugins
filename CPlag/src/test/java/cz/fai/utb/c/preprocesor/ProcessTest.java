/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.c.preprocesor;

import cz.fai.utb.c.api.CProcessor;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 *
 * @author radek
 */
public class ProcessTest {

    @Test
    public void processDocument() throws IOException, URISyntaxException {
        String content = FileUtils.readFileToString(new File(this.getClass().getResource("/test/fcepole.c").toURI()));
        CProcessor instance = new CProcessor();
        instance.parseSource(content);
    }
}
