package ciphersij

import ciphersij.breakers.FrequencyAnalysis
import ciphersij.ciphers.Caesar
import ciphersij.utils.Utils

object Main {

  def main(args: Array[String]): Unit = {
    val plaintext = Utils.readFile("text.txt")
    val ciphertext = Caesar.encrypt(plaintext, 24)
    val decipherAttempt = Caesar.decrypt(ciphertext, FrequencyAnalysis.generateKey(ciphertext))

    println(s"Plaintext:\n$plaintext\n")
    println(s"Ciphertext:\n$ciphertext\n")
    println(s"Decipher Attmept:\n$decipherAttempt\n")
  }
}
