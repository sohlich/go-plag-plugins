/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fai.utb.lang.api;

/**
 *
 * @author Radek
 */
public interface Preprocessor {
    public String process(String input);
    public String process();
    public String processAll();
    public Preprocessor removeComments();
}
