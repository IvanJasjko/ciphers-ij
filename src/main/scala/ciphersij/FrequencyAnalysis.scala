package ciphersij

object FrequencyAnalysis {

  private val sampleText = TxtReader.readFile("sampleText.txt")

  def decrypt(cipherText: String): String = {
    val cipherTextFrequencies = getLetterFrequencies(cipherText)
    val sampleTextFrequencies = getLetterFrequencies(sampleText)

    val decryptMapping = cipherTextFrequencies.zipWithIndex.map({case (letter, index) => (letter, sampleTextFrequencies(index))}).toMap

    cipherText
      .toSeq
      .map(character => replaceLetter(character, decryptMapping))
      .mkString("")
  }

  private def getLetterFrequencies(text: String): Seq[Char] = {
    text
      .replaceAll("[0-9]| ", "")
      .distinct.map(character => (character, text.count(_ == character)))
      .sortBy({ case (_, frequency) => -frequency })
      .map(x => x._1)
  }

  private def replaceLetter(character: Char, mapping: Map[Char, Char]): Char = {
    if (character.isLetter)
      mapping(character)
    else
      character
  }
}
