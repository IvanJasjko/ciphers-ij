package ciphersij

object Main {

  def main(args: Array[String]): Unit = {
    val plaintext = TxtReader.readFile("text.txt")
    val ciphertext = Caesar.encrypt(plaintext, 42, "right")
    val decrypted = FrequencyAnalysis.decrypt(ciphertext)

    println(s"Plaintext:\n$plaintext\n")
    println(s"Ciphertext:\n$ciphertext\n")
    println(s"Decipher Attempt:\n$decrypted\n")
  }
}
