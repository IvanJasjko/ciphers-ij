package ciphersij

import ciphersij.ciphers.{Vigenere, VigenereKey}
import ciphersij.utils.TextParser

object Main {

  def main(args: Array[String]): Unit = {
    val plaintext = TextParser.readFile("vigenereText.txt")
    val ciphertext = Vigenere.encrypt(plaintext, VigenereKey("flex"))
    val decipherAttempt = Vigenere.decrypt(ciphertext, VigenereKey("flex"))

    println(s"Plaintext:\n$plaintext\n")
    println(s"Ciphertext:\n$ciphertext\n")
    println(s"Decipher Attmept:\n$decipherAttempt\n")
  }
}
