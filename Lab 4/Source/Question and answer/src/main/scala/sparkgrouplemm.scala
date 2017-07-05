import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by VenkatNag on 6/17/2017.
  */
object sparkgrouplemm {
 def main(args:Array[String]): Unit ={

   System.setProperty("hadoop.home.dir","E:\\UMKC\\Sum_May\\KDM\\winutils")

   val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

   val sc=new SparkContext(sparkConf)
   val call:NLP=new NLP();
val text=sc.textFile("E:\\UMKC\\Sum_May\\KDM\\out.txt");
   val t1=text.map(l=>{call.lemm(l)})

   val t2=t1.flatMap(d=>{d.split(" ")}).filter(f=>(!(f.contains(",")|f.contains(".")|(f.isEmpty)))).map(w=>(w))
   val t3=t2.groupBy(g=>{g.charAt(0)})
   t3.collect().foreach(println)
 }
}
