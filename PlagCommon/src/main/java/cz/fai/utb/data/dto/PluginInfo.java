/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.data.dto;

/**
 *
 * @author radek
 */
public class PluginInfo {
    String language;
    String[] extensions;

    public PluginInfo(String language, String[] extensions) {
        this.language = language;
        this.extensions = extensions;
    }
    
}
