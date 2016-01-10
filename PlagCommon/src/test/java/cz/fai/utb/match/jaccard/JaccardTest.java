/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.match.jaccard;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author radek
 */
public class JaccardTest {
    
    public JaccardTest() {
    }
    /**
     * Test of compute method, of class Jaccard.
     */
    @Test
    public void trivialTest() {
        System.out.println("compute");
        Map<String, Integer> a = new HashMap<>();
        Map<String, Integer> b = new HashMap<>();
        a.put("1", 2);
        a.put("2", 2);
        b.put("1", 2);
        float expResult = (float)2/(float)4;
        float result = Jaccard.compute(a, b);
        assertEquals(expResult, result, 0.0);
    }
    
}
