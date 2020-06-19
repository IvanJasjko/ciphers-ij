package ciphersij.breakers

import ciphersij.ciphers.Caesar._
import ciphersij.utils.Utils._

object FrequencyAnalysis {

  def generateKey(cipherText: String): Int = {
    val sampleFreqs = getFreqs(readFile("sampleText.txt"))
    val expectedFreqs = sampleFreqs map { case (key, freq) => (key, freq / sampleFreqs.values.sum) }

    val attempts = (0 to 25).map { key =>
      val attemptText = decrypt(cipherText, key)
      val attemptFreqs = getFreqs(attemptText)
      val diff = attemptFreqs.keys.toList diff expectedFreqs.keys.toList

      if (diff.nonEmpty) {
        (key, -1.toFloat)
      } else {
        val chi = attemptFreqs.map { case (key, value) =>
          math.pow(2, value - value * expectedFreqs(key)) / value * expectedFreqs(key)
        }.sum
        (key, chi.toFloat)
      }
    }.toMap

    val candidates = attempts.filter({ case (_, value) => value != -1 })
    candidates.minBy(_._2)._1
  }

  private def getFreqs(text: String): Map[Char, Float] = {
    text.replaceAll("""[0-9]| """, "").distinct
      .map(character => (character, text.count(_ == character).toFloat)).toMap
  }
}
