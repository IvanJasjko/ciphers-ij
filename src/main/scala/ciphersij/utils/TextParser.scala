package ciphersij.utils

import scala.io.Source

object TextParser {

  def readFile(filename: String): String = {
    val file = Source.fromResource(filename)
    val fileContents = file.getLines.mkString
      .replaceAll("[^\\w ]", "")
      .toUpperCase()
    file.close()
    fileContents
  }
}
