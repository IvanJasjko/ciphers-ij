package ciphersij.ciphers

import java.lang.Math.floorMod

import ciphersij.utils.Utils.alphabetMap

object Caesar extends Cipher {

  def encrypt(plainText: String, number: Int): String = {
    plainText
      .toSeq
      .map(character => shift(character, number))
      .mkString("")
  }

  def decrypt(cipherText: String, key: Int): String = {
    encrypt(cipherText, -key)
  }

  private def shift(character: Char, number: Int): Char = {
    if (character.isLetter) {
      val letterIndex = alphabetMap(character)
      val newIndex = floorMod(letterIndex + number, 26)
      alphabetMap.find({ case (_, index) => index == newIndex }).get._1
    } else {
      character
    }
  }
}
