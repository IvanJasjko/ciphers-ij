package ciphersij

import ciphersij.breakers.FrequencyAnalysis
import ciphersij.ciphers.{Caesar, CaesarKey, Vigenere, VigenereKey}
import ciphersij.utils.TextParser

object Main {

  def main(args: Array[String]): Unit = {
    val plaintext = TextParser.readFile("text.txt")
    val ciphertext = Vigenere.encrypt(plaintext, VigenereKey("recs"))
//    val decipherAttempt = Caesar.decrypt(ciphertext, CaesarKey(FrequencyAnalysis.generateKey(ciphertext)))

//    println(s"Plaintext:\n$plaintext\n")
//    println(s"Ciphertext:\n$ciphertext\n")
//    println(s"Decipher Attmept:\n$decipherAttempt\n")

  }
}
