/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.algorithms.winnowing;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;

/**
 *
 * @author Radek
 */
public class WinnowingTest {

    public WinnowingTest() {
    }
   
    int[] trivialInput = {77, 72, 42, 17, 98, 50, 17, 98, 8, 88, 67, 39, 77, 72, 42, 17, 98};
    
    /**
     * test against sequence described in original ResearchPaper
     */
    @Test
    public void trivialTest() throws Exception {
        System.out.println("Trivial test");
        Winnowing w = new Winnowing();
        WinnowFingerPrint out = w.createFingerPrint(trivialInput, 4);
        Assert.assertArrayEquals("Winnowing primitive test fails", new Integer[]{17, 17, 8, 39, 17}, out.getFingerPrint());

        Map<Integer, Integer> assertMap = new HashMap<>();
        assertMap.put(17, 3);
        assertMap.put(8, 1);
        assertMap.put(39, 1);
        Assert.assertEquals("Winnowing fingerprintMap test failed", assertMap, out.getFingerMap());
    }

}
