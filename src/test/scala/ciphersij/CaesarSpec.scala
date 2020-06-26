package ciphersij

import ciphersij.ciphers.{Caesar, CaesarKey}
import org.scalatest._

class CaesarSpec extends FunSuite with Matchers {

  test("Caesar encrypt/decrypt test") {
    val plainText = "ENCRYPT ME PLEASE"
    val key = 1054
    val cipherText = Caesar.encrypt(plainText, CaesarKey(key))

    plainText shouldEqual Caesar.decrypt(cipherText, CaesarKey(key))
  }
}
