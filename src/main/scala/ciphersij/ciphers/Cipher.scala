package ciphersij.ciphers


trait Key {} //Marker Trait

case class CaesarKey(value: Int) extends Key

case class VigenereKey(value: String) extends Key

trait Cipher[T <: Key] {

  val alphabetMap: Map[Char, Int] = ('A' to 'Z')
    .zipWithIndex
    .toMap

  def encrypt(plainText: String, key: T): String

  def decrypt(cipherText: String, key: T): String
}
