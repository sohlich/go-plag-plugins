package cz.fai.utb.ngram
/**
 * Created by Radek on 12/29/2014.
 */
class NGramGenerator {

    def n = 4
    List<String> resultList = []
    Map<String,Integer> footPrint = [:]

    NGramGenerator(int count) {
        this.n = count
    }

    void generate(List<String> tokens) {
        resultList = new LinkedList<>();
        tokens.eachWithIndex { obj, index ->
            def ngram = []
            n.times { n ->
                ngram.add( tokens[index + n]== null?0:tokens[index + n])
            }
            resultList.add(ngram)
            footPrint.put(ngram,footPrint.getOrDefault(ngram,0)+1)
        }
    }


}
