package ciphersij.utils

import scala.io.Source

object Utils {
  val alphabetMap: Map[Char, Int] = ('A' to 'Z')
    .zipWithIndex
    .toMap

  def readFile(filename: String): String = {
    val file = Source.fromResource(filename)
    val fileContents = file.getLines.mkString
      .replaceAll("[^\\w ]", "")
      .toUpperCase()
    file.close()
    fileContents
  }
}
