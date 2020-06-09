package ciphersij

import scala.io.Source

object TxtReader {

  def readFile(filename: String): String = {
    val file = Source.fromResource(filename)

    val fileContents = file.getLines.mkString
      .replaceAll("[^\\w ]", "")
      .toUpperCase()

    file.close()
    fileContents
  }
}