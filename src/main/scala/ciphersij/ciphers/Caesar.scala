package ciphersij.ciphers

import java.lang.Math.floorMod

object Caesar extends Cipher[CaesarKey] {

  def encrypt(plainText: String, key: CaesarKey): String = {
    plainText
      .toSeq
      .map(character => shift(character, key.value))
      .mkString("")
  }

  def decrypt(cipherText: String, key: CaesarKey): String = {
    encrypt(cipherText, CaesarKey(-key.value))
  }

  def shift(character: Char, number: Int): Char = {
    if (character.isLetter) {
      val letterIndex = alphabetMap(character)
      val newIndex = floorMod(letterIndex + number, 26)
      alphabetMap.find({ case (_, index) => index == newIndex }).get._1
    } else {
      character
    }
  }
}
