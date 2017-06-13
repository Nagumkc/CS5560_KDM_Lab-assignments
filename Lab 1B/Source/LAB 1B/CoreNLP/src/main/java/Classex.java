import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import jdk.internal.cmm.SystemResourcePressureImpl;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;


/**
 * Created by Mayanka on 13-Jun-16.
 */
public class Classex {
    public static void main(String []arg) {
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        String text = null;
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
                try {
                    BufferedReader b = new BufferedReader(new FileReader("E:\\UMKC\\Sum_May\\KDM\\week1\\bbcsport\\cricket\\001.txt"));
                    StringBuilder sb = new StringBuilder();
                    String line = b.readLine();
                    while (line != null) {
                        sb.append(line);
                        line = b.readLine();
                    }
                    // read some text in the text variable
                    text = sb.toString();
                    sb.setLength(0);
                    b.close();// Add your text here!
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }


// create an empty Annotation just with the given text

                Annotation document = new Annotation(text);

// run all Annotators on this text
                pipeline.annotate(document);

                // these are all the sentences in this document
// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
                List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

                for (CoreMap sentence : sentences) {
                    // traversing the words in the current sentence
                    // a CoreLabel is a CoreMap with additional token-specific methods
                    for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
/*
                  System.out.println("\n" + token);

                    // this is the text of the token
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    System.out.println("Text Annotation");
                    System.out.println(token + ":" + word);
                    // this is the POS tag of the token

                    String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                    System.out.println("Lemma Annotation");
                    System.out.println(token + ":" + lemma);
                    // this is the Lemmatized tag of the token*/

                  //  System.out.println("Parts of speech tagger");
                  /*  String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                    System.out.println(token + ":" + pos);

                        // this is the NER label of the token
                       /* System.out.println("Named Entity Relations");
                     String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                        System.out.println(token+ ":"+ne);


                    // this is the parse tree of the current sentence
               /*Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
                System.out.println(tree);
                // this is the Stanford dependency graph of the current sentence
                SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
                System.out.println(dependencies.toString());*/

                }

            }
       Map<Integer, CorefChain> graph =
                document.get(CorefCoreAnnotations.CorefChainAnnotation.class);
        System.out.println(graph.values().toString());
        }

    }


