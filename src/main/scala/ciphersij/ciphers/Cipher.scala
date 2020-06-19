package ciphersij.ciphers

trait Cipher {
  def encrypt(plainText: String, key: Int): String
  def decrypt(cipherText: String, key: Int): String
}
