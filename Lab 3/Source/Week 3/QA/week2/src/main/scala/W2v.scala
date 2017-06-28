import java.io.File

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.feature.{Word2Vec, Word2VecModel}

/**
  * Created by VenkatNag on 6/27/2017.
  */
object W2v {

 def vec(x:String,sc:SparkContext){
var count:Int=0
    var str:String=null
val i=sc.textFile("E:\\UMKC\\Sum_May\\KDM\\out.txt").map(line => line.split(" ").toSeq)
    val modelFolder = new File("myModel")

    if (modelFolder.exists()) {
      val sameModel = Word2VecModel.load(sc, "myModel")
      val synonyms = sameModel.findSynonyms(x, 40)

      for ((synonym, cosineSimilarity) <- synonyms) {
        if(count==0) {
          println(s"$synonym")
          str=s"$synonym"
          println(str)
        }
        count=count+1
      }
    }
    else {
      val word2vec = new Word2Vec().setVectorSize(1000).setMinCount(5)

      val model = word2vec.fit(i)

      val synonyms = model.findSynonyms(x, 40)

      for ((synonym, cosineSimilarity) <- synonyms) {
        if (count == 0) {
          println(s"$synonym")
          str=s"$synonym"
        }
        count+=1
      }
      //model.getVectors.foreach(f => println(f._1 + ":" + f._2.length))
      // Save and load model
      model.save(sc, "myModel")
     return str
    }
  }
  /*def main(args: Array[String]): Unit = {
  vec()
  }*/
}
