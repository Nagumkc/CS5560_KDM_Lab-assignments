import java.io.File

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.feature.{HashingTF, IDF, Word2Vec, Word2VecModel}
import org.apache.spark.rdd.RDD

import scala.collection.immutable.HashMap

/**
  * Created by Mayanka on 19-06-2017.
  */
object TF_IDF {
  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "E:\\UMKC\\Sum_May\\KDM\\winutils")

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    //Reading the Text File
    val documents = sc.textFile("E:\\UMKC\\Sum_May\\KDM\\Week 3\\Tutorial 3 Source Code\\Tutorial 3 Source Code\\Spark_TFIDF_W2V\\data\\Article.txt")

    //Getting the Lemmatised form of the words in TextFile
    val documentseq = documents.map(f => {

     val splitString = f.split(" ")
      splitString.toSeq
     // x.toSeq
    })

    // val lemmatised = CoreNLP.returnLemma(f)
    //val x=NGRAM.getNGrams(f,2).map(f=>{f.mkString(" ")})
    //Creating an object of HashingTF Class
    val hashingTF = new HashingTF()

    //Creating Term Frequency of the document
    val tf = hashingTF.transform(documentseq)
    tf.cache()


    val idf = new IDF().fit(tf)

    //Creating Inverse Document Frequency
    val tfidf = idf.transform(tf)

    val tfidfvalues = tfidf.flatMap(f => {
      val ff: Array[String] = f.toString.replace(",[", ";").split(";")
      val values = ff(2).replace("]", "").replace(")", "").split(",")
      values
    })

    val tfidfindex = tfidf.flatMap(f => {
      val ff: Array[String] = f.toString.replace(",[", ";").split(";")
      val indices = ff(1).replace("]", "").replace(")", "").split(",")
      indices
    })

    tfidf.foreach(f => println(f))

    val tfidfData = tfidfindex.zip(tfidfvalues)

    var hm = new HashMap[String, Double]

    tfidfData.collect().foreach(f => {
      hm += f._1 -> f._2.toDouble
    })

    val mapp = sc.broadcast(hm)

    val documentData = documentseq.flatMap(_.toList)
    val dd = documentData.map(f => {
      val i = hashingTF.indexOf(f)
      val h = mapp.value
      (f, h(i.toString))
    })

    val dd1 = dd.distinct().sortBy(_._2, false).map(w => (
      w._1
    )).collect()
    dd1.take(20).foreach(f => {
      println(f)
    })

   wv(dd1.take(20), documents, sc)
  }

  def wv(strings: Array[String], value: RDD[String], context: SparkContext) {
    var i = 0
    for (i <- 0 to 19) {

      val input = value.map(line => line.split(" ").toSeq)
      val modelFolder = new File("myModelPath")

      if (modelFolder.exists()) {
        val sameModel = Word2VecModel.load(context, "myModelPath")
        val synonyms = sameModel.findSynonyms(strings(i), 40)

        for ((synonym, cosineSimilarity) <- synonyms) {
          println(s"$synonym $cosineSimilarity")
        }
      }
      else {

        val word2vec = new Word2Vec().setVectorSize(1000).setMinCount(1)

        val model = word2vec.fit(input)

        val synonyms = model.findSynonyms(strings(i), 40)

        for ((synonym, cosineSimilarity) <- synonyms) {
          println(s"$synonym $cosineSimilarity")
        }

        model.getVectors.foreach(f => println(f._1 + ":" + f._2.length))

        // Save and load model
        model.save(context, "myModelPath")
      }
    }
  }
}
