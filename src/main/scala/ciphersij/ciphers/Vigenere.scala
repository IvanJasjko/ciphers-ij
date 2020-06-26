package ciphersij.ciphers

import scala.annotation.tailrec

object Vigenere extends Cipher[VigenereKey] {

  def encrypt(plaintext: String, key: VigenereKey): String = {
    val slices = Range(0, plaintext.length, key.value.length).sliding(2).toList
    val remainder = slices.last.last
    val textChunks = slices.map({ case Vector(x, y) => plaintext.slice(x, y) }) ++ List(plaintext.splitAt(remainder)._2)

    val cleanChunks = updateCycle(textChunks)

    cleanChunks.foreach(println)
    "wip"

  }

  def decrypt(ciphertext: String, key: VigenereKey): String = {
    "wip"
  }

  @tailrec
  private def takeNextLetter(headList: String, tailList: String): (String, String) = {
    val updatedHead = headList.concat(tailList.head.toString)
    val updatedTail = tailList.tail

    if (tailList.head != ' ' && headList.replace(" ", "").length == 3)
      (updatedHead, updatedTail)
    else
      takeNextLetter(updatedHead, updatedTail)
  }

  @tailrec
  private def updateCycle(chunks: List[String], index: Int=0): List[String] = {
    val slice = chunks.splitAt(index)._2
    println(slice)
    if (slice.isEmpty) {
      chunks
    } else {
      val newPair = takeNextLetter(chunks.head, chunks.tail.head)
      updateCycle(List(newPair._1, newPair._2) ++ slice, index+2)
    }
  }
}