/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.utb.algorithms.gst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author radek
 */
public class GreedyStringTiling {

    /**
     * Produces all matching sequences
     *
     * @param pattern
     * @param sequence
     * @param minLength
     * @return
     */
    public List<Match> process(final Integer[] pattern, final Integer[] sequence, int minLength) throws InterruptedException {

        //Prepare the set
        final List<Token> patternLst = new ArrayList<>(pattern.length);
        final List<Token> sequenceLst = new ArrayList<>(sequence.length);

        //Custom tread to fill tokenlist
        Thread patternRunnable = new Thread (()-> {
        for (Integer patternTok : pattern) {
                    patternLst.add(new Token(patternTok, false));
                }
        });
        patternRunnable.start();
        
        //Custom tread to fill tokenlist
        Thread squenceRunnable = new Thread (()-> {
        for (Integer sequenceTok : sequence) {
                    sequenceLst.add(new Token(sequenceTok, false));
                }
        });
        squenceRunnable.start();
        
        patternRunnable.join();
        squenceRunnable.join();
        

        //Start of alg
        List<Match> tiles = new ArrayList<>();
        int maxLength;
        do {

            maxLength = minLength;
            List<Match> matches = new ArrayList<>();

            //For all unmarked tokens in pattern
            for (int i = 0; i < patternLst.size(); i++) {
                if (patternLst.get(i).marked) {
                    i++;
                    continue;
                }

                //Compare tokens in sequence
                for (int j = 0; j < sequenceLst.size(); j++) {
                    if (sequenceLst.get(j).marked) {
                        j++;
                        continue;
                    }
                    int count = 0;
                    //While token equals try to maximize equal length
                    while (i + count < patternLst.size()
                            && j + count < sequenceLst.size()
                            && patternLst.get(i + count).token.equals(sequenceLst.get(j + count).token)
                            && !patternLst.get(i + count).marked
                            && !sequenceLst.get(j + count).marked) {
                        count++;

                        if (count == maxLength) {
                            matches.add(new Match(i, j, count));
                        } else if (count > maxLength) {
                            matches = new ArrayList<>();
                            matches.add(new Match(i, j, count));
                            maxLength = count;
                        }

                    }
                }
            }

            for (Match match : matches) {

                for (int k = 0; k < match.length; k++) {
                    patternLst.get(match.patternStart + k).marked = true;
                    sequenceLst.get(match.sequenceStart + k).marked = true;
                }
                if (!alreadyMatched(match, tiles)) {
                    tiles.add(match);
                }
            }
        } while (maxLength > minLength);

        return tiles;
    }

    private boolean alreadyMatched(Match match, List<Match> tiles) {
        boolean returnVal = false;
        if (match == null || tiles == null || tiles.isEmpty()) {
            returnVal = false;
        } else {
            for (Match tilesItem : tiles) {
                if (tilesItem.patternStart == match.patternStart || tilesItem.sequenceStart == match.sequenceStart) {
                    returnVal = true;
                }

            }
        }
        return returnVal;
    }

    //Count the highest similarity
    public double computeSimilarity(List<Match> matchList, int patternSize, int sequenceSize) {
        int similar = matchList.stream().mapToInt((m) -> m.length).sum();
        double result = (double) similar / ((double) (patternSize + sequenceSize - similar));
        return result;
    }

    public class Token {

        public Integer token;
        public boolean marked;

        public Token(Integer token, boolean marked) {
            this.token = token;
            this.marked = marked;
        }

    }

}
