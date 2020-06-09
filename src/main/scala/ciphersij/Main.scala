package ciphersij

object Main {

  def main(args: Array[String]): Unit = {
    val plaintext = TxtReader.readFile("text.txt")
    val encrypted = Caesar.encrypt(plaintext, 0, "left")
    println(plaintext)
    println(encrypted)
  }
}
