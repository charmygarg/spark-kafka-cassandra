import org.json4s.jackson.JsonMethods._
import org.json4s._
class Combined {

  def getFootballData(filePath: String): List[Football] = {
    val fileLines = io.Source.fromFile(filePath).getLines.toList
    fileLines.map { line =>
      val array = line.split(",")
      val homeTeam = array(2)
      val awayTeam = array(3)
      val fullTimeHomeGoal = array(4)
      val fullTimeAwayGoal = array(5)
      val fullTimeResult = array(6)
      Football(homeTeam, awayTeam, fullTimeHomeGoal.toInt, fullTimeAwayGoal.toInt, fullTimeResult)
    }
  }

  def convertCaseClassToJson(football: Football): String = {
    implicit val formats = DefaultFormats
    pretty(Extraction.decompose(football))
  }

}

object Combined {

  def main(args: Array[String]): Unit = {

    val FilePath = "src/main/resources/D1.csv"

    val combined = new Combined

    combined.getFootballData(FilePath).take(10).map { line =>
      combined.convertCaseClassToJson(line)
    }.foreach(println)
  }

}