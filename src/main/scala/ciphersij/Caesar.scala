package ciphersij

import java.lang.Math._

object Caesar {

  val alphabetMap = ('A' to 'Z')
    .zipWithIndex
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
      val newIndex = floorMod(letterIndex + number * direction, 26)
      alphabetMap.find({ case (_, index) => index == newIndex}).get._1
    } else {
      character
    }
  }
}
