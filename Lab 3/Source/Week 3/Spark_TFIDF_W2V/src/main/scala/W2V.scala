import java.io.File

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.feature.{Word2Vec, Word2VecModel}

/**
  * Created by Mayanka on 19-06-2017.
  */
object W2V {
  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "E:\\UMKC\\Sum_May\\KDM\\winutils")

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")
      .set("spark.driver.memory", "6g").set("spark.executor.memory", "6g")

    val sc = new SparkContext(sparkConf)

    val input = sc.textFile("E:\\UMKC\\Sum_May\\KDM\\Week 3\\Tutorial 3 Source Code\\Tutorial 3 Source Code\\Spark_TFIDF_W2V\\data\\text.txt").map(line => line.split(" ").toSeq)

    val modelFolder = new File("myModel")

    if (modelFolder.exists()) {
      val sameModel = Word2VecModel.load(sc, "myModel")
      val synonyms = sameModel.findSynonyms("location", 2)

      for ((synonym, cosineSimilarity) <- synonyms) {
        println(s"$synonym $cosineSimilarity")
      }
    }
    else {
      val word2vec = new Word2Vec().setVectorSize(1000).setMinCount(1)

      val model = word2vec.fit(input)

      val synonyms = model.findSynonyms("location", 2)

      for ((synonym, cosineSimilarity) <- synonyms) {
        println(s"$synonym $cosineSimilarity")
      }

      model.getVectors.foreach(f => println(f._1 + ":" + f._2.length))

      // Save and load model
      model.save(sc, "myModel")

    }

  }
}
