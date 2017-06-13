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
public class Main {
    public int train() {
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

                        // this is the NER label of the token
                        String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                        //   System.out.println("NER");
                        if (ne.contains("PERSON")) {
                            try {
                                File file = new File("E:\\UMKC\\Sum_May\\KDM\\week1\\person.txt");
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                                BufferedWriter bw = new BufferedWriter(fw);

                                String word = token.get(CoreAnnotations.TextAnnotation.class);
                                bw.write(word);
                                bw.write("\n");
                                bw.flush();
                                bw.close();
                            } catch (Exception e) {

                            }
                        }
                        if (ne.contains("LOCATION")) {
                            try {
                                File file = new File("E:\\UMKC\\Sum_May\\KDM\\week1\\location.txt");
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                String word = token.get(CoreAnnotations.TextAnnotation.class);
                                bw.write(word);
                                bw.write("\n");
                                bw.flush();
                                bw.close();
                            } catch (Exception e) {

                            }
                        }
                        if (ne.contains("DATE")) {
                            try {
                                File file = new File("E:\\UMKC\\Sum_May\\KDM\\week1\\date.txt");
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                String word = token.get(CoreAnnotations.TextAnnotation.class);
                                bw.write(word);
                                bw.write("\n");
                                bw.flush();
                                bw.close();
                            } catch (Exception e) {

                            }
                        }

                        //  System.out.println("\n\n");
                    }


    }


        return 0;
    }
    public static void main(String []args)
    {
     Main c=new Main();
     c.train();
        Scanner reader = new Scanner(System.in);
        System.out.println("enter first question");
        String text=reader.nextLine();
        try{
        BufferedReader b = new BufferedReader(new FileReader("E:\\UMKC\\Sum_May\\KDM\\week1\\person.txt"));
        String line=null;
        int i=0;
        while((line=b.readLine())!=null&&i<10)
        {
            System.out.println(line);
            i++;
        }
        b.close();
    }catch (Exception e){}
    System.out.println("Enter second question");
    text=reader.nextLine();
        try{
            BufferedReader b = new BufferedReader(new FileReader("E:\\UMKC\\Sum_May\\KDM\\week1\\location.txt"));
            String line=null;
            int i=0;
            while((line=b.readLine())!=null&&i<10)
            {
                System.out.println(line);
                i++;
            }
            b.close();
        }catch (Exception e){}
        System.out.println("Enter third question");
        text=reader.nextLine();
        try{
            BufferedReader b = new BufferedReader(new FileReader("E:\\UMKC\\Sum_May\\KDM\\week1\\date.txt"));
            String line=null;
            int i=0;
            while((line=b.readLine())!=null&&i<10)
            {
                System.out.println(line);
                i++;
            }
            b.close();
        }catch (Exception e){}

    }

    }
