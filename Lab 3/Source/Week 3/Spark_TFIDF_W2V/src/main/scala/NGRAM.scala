
/**
  * Created by Mayanka on 19-06-2017.
  */
object NGRAM {

 /* def main(args: Array[String]): Unit = {
    val a = getNGrams("the bee is the bee of the bees",2)
   val x= a.map(f=>(f.mkString(" ")))

  }*/

  def getNGrams(sentence: String, n:Int): Array[Array[String]]= {
    val words = sentence
    val ngrams = words.split(' ').sliding(n)
    ngrams.toArray
  //  val a= ngrams.map(f=>(f.mkString(" ")))
    //a.mkString(" ")

  }

}


