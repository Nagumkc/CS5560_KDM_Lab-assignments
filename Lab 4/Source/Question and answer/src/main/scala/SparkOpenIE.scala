import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Mayanka on 27-Jun-16.
  */
object SparkOpenIE {

  def main(args: Array[String]) {
    // Configuration
    System.setProperty("hadoop.home.dir", "E:\\UMKC\\Sum_May\\KDM\\winutils")

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val c: CoreNLP = new CoreNLP();
    // Turn off Info Logger for Console
 Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val input = sc.textFile("E:\\UMKC\\Sum_May\\KDM\\week1\\bbcsport\\cricket\\001.txt").map(line => {
      //Getting OpenIE Form of the word using lda.CoreNLP

      val t=c.returnTriplets(line)
      t
    })
println(input.collect().mkString("\n"))
 //println(c.returnTriplets("How many runs scored by vettori"))
   // println(input.collect().mkString("\n"))


  }

}
