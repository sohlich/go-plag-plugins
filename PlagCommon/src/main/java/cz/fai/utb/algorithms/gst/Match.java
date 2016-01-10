/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.algorithms.gst;

/*
 * Class to store matches
 */
public class Match {

    int patternStart;
    int sequenceStart;
    int length;

    public Match() {
    }

    public Match(int patternStart, int sequenceStart, int length) {
        this.patternStart = patternStart;
        this.sequenceStart = sequenceStart;
        this.length = length;
    }

    public int getPatternStart() {
        return patternStart;
    }

    public void setPatternStart(int patternStart) {
        this.patternStart = patternStart;
    }

    public int getSequenceStart() {
        return sequenceStart;
    }

    public void setSequenceStart(int sequenceStart) {
        this.sequenceStart = sequenceStart;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Match{" + "patternStart=" + patternStart + ", sequenceStart=" + sequenceStart + ", length=" + length + '}';
    }
}
