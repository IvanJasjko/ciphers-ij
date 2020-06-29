package ciphersij.ciphers


import scala.annotation.tailrec

object Vigenere extends Cipher[VigenereKey] {

  def encrypt(plaintext: String, key: VigenereKey): String = {
    vigenereEncrypt(plaintext, key)
  }

  def decrypt(ciphertext: String, key: VigenereKey): String = {
    vigenereEncrypt(ciphertext, key, reverse = true)
  }

  def vigenereEncrypt(plaintext: String, key: VigenereKey, reverse: Boolean = false): String = {
    val keySize = key.value.length
    val chunks = splitText(plaintext, keySize)
    val keyList = key.value.map(letter => alphabetMap(letter.toUpper))
    chunks.map(chunk => applyKeys(chunk, keyList, reverse)).mkString("")
  }

  @tailrec
  private def applyKeys(chunk: String, keys: Seq[Int], reverse: Boolean, encodedChunk: String = ""): String = {
    val direction = if (reverse) -1 else 1
    if (chunk.isEmpty) {
      encodedChunk
    } else if (chunk.head == ' ') {
      applyKeys(chunk.tail, keys,  reverse, encodedChunk ++ chunk.head.toString)
    } else {
      applyKeys(chunk.tail, keys.tail, reverse, encodedChunk ++ Caesar.shift(chunk.head, keys.head * direction).toString)
    }
  }

  @tailrec
  private def splitText(plaintext: String, keySize: Int, index: Int = 0, chunks: List[String] = List()): List[String] = {
    val newIndex = getChunkIndex(plaintext, keySize)
    val (textHead, textTail) = plaintext.splitAt(newIndex)
    val output = chunks ++ List(textHead)
    if (getLetterCount(textTail) < keySize)
      output ++ List(textTail)
    else
      splitText(textTail, keySize, newIndex, output)
  }

  @tailrec
  private def getChunkIndex(text: String, keySize: Int, index: Int = 0): Int = {
    val chunk = text.take(index)
    if (getLetterCount(chunk) == keySize)
      index
    else
      getChunkIndex(text, keySize, index + 1)
  }

  private def getLetterCount(text: String): Int = {
    text.replace(" ", "").length
  }
}