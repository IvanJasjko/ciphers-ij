package ciphersij

object Caesar {

  private val alphabetMap = ('A' to 'Z')
    .zipWithIndex
    .map { case (letter, index) => (letter, index + 1) }
    .toMap

  def encrypt(plainText: String, number: Int, direction: String="right"): String = {

    val dir = direction.toLowerCase match {
      case "right" => 1
      case "left" => -1
      case _ => throw new RuntimeException("Invalid direction, please specify left or right")
    }

    plainText
      .toSeq
      .map(character => shift(character, number, dir))
      .mkString("")
  }

  private def shift(character: Char, number: Int, direction: Int): Char = {
    if (character.isLetter) {
      val letterIndex = alphabetMap(character)
      val newIndex = slide(letterIndex + number * direction, number)
      alphabetMap.find({ case (_, index) => index == newIndex}).get._1
    } else {
      character
    }
  }

  private def slide(newIndex: Int, number: Int): Int = {
    if (newIndex > 26)
      newIndex - 26
    else if (newIndex < 1)
      newIndex + 26
    else
      newIndex
  }
}
