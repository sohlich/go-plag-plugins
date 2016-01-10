/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.ngram;

import java.util.Arrays;

/**
 *
 * @author Radek
 */
public class NGram {

    int n;
    int[] content = {-1, -1, -1, -1};
    int counter;

    public NGram(int n) {
        this.n = n;
        this.content = new int[n];
        Arrays.fill(this.content, 0);
    }

    public NGram(int n, String[] input, int startIndex) {
        this.n = n;
        this.content = new int[n];
        //this.content = new String[n];
        for (int i = 0; i < n; i++) {
            if (input.length > startIndex + i) {
                content[i] = Integer.parseInt(input[startIndex + i]);
            } else {
                content[i] = 0;
            }
        }

    }

    public String getValue() {
        return Arrays.toString(content);
    }

    public int hash() {
        return 73 * content[0] * content[1] * content[2] * content[3];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        //builder.append("[");
        for (int token : content) {
            builder.append(token);
            builder.append(";");
        }
        //builder.append("]");
        return builder.toString();
    }
}
