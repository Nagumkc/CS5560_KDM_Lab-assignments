import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

/**
 * Created by VenkatNag on 6/24/2017.
 */
public class NLP {
    public String lemm(String data) {

        Properties prop = new Properties();

        StringBuilder res = new StringBuilder();
        prop.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(prop);
        Annotation doc = new Annotation(data);

        pipeline.annotate(doc);

        List<CoreMap> sents = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sents) {
            for (CoreLabel token1 : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String lemma = token1.get(CoreAnnotations.LemmaAnnotation.class);
                res.append(lemma + " ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
