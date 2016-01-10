/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.c.api;

import com.google.gson.Gson;
import cz.fai.utb.data.dto.PluginInfo;
import cz.fai.utb.lang.api.ParseResultWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author radek
 */
public class CmdMain {

    private static final CProcessor processor = new CProcessor();
    private static final PluginInfo info = new PluginInfo(processor.getLang(),
            processor.getExtensions());

    public static void main(String[] args) {
        String command = "NONE";
        if (args.length > 0) {
            command = args[0];
        }

        switch (command) {
            case "parse":
                parseInput();
                break;
            case "info":
                printInfo();
                break;
            default:
            //Nothing done
        }

    }

    private static void parseInput() {
        StringBuilder inputAppender = new StringBuilder();
        try {
            BufferedReader br
                    = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = br.readLine()) != null) {
                inputAppender.append(input);
            }
            ParseResultWrapper wrapper
                    = processor.parseSource(inputAppender.toString());
            System.out.println(new Gson().toJson(wrapper));
            System.exit(0);
        } catch (IOException | StackOverflowError io) {
            System.exit(1);
        }
    }

    private static void printInfo() {
        System.out.println(new Gson().toJson(info));
        System.exit(0);
    }
}
