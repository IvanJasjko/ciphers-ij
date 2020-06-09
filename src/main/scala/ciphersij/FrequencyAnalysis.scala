package ciphersij

object FrequencyAnalysis {

  private val sampleText = TxtReader.readFile("sampleText.txt")

  def decrypt(cipherText: String): String = {
    val cipherTextFrequencies = getLetterFrequencies(cipherText)
    val sampleTextFrequencies = getLetterFrequencies(sampleText)

    println(s"Ciphertext letter frequencies: $cipherTextFrequencies\n")
    println(s"Sampletext letter frequencies: $sampleTextFrequencies\n")

    val cipherToSampleMapping = cipherTextFrequencies.map(x => x._1)
      .zipWithIndex
      .map({ case (letter, index) =>
        (letter, sampleTextFrequencies(index)._1)
      }).toMap

    println(s"Frequency based cipher to sample mapping: $cipherToSampleMapping\n")

    val keyCandidates = cipherTextFrequencies.take(5)
      .map({ case (cipherLetter, frequency) =>
        (cipherLetter, frequency, Caesar.alphabetMap(cipherLetter) - Caesar.alphabetMap(cipherToSampleMapping(cipherLetter)))
      })

    println(s"Candidate keys based on distance between most frequent letter positions: $keyCandidates\n")

    val keyNums = keyCandidates.groupBy { case (_, _, distance) => distance }
      .map({ case (key, letters) => (key, letters.size) })

    if (keyNums.maxBy(_._2)._2 > 1) {
      decipher(cipherText, keyNums.maxBy(_._2)._1)
    } else {
      println("None of the keys have the same distance, attempting all of them:")
      keyNums.keys.foreach { n =>
        println(s"${decipher(cipherText, n)}")
      }
      decipher(cipherText, keyNums.keys.head)
    }
  }

  private def decipher(cipherText: String, keyNum: Int): String = {
    if (keyNum > 0) {
      Caesar.encrypt(cipherText, math.abs(keyNum), "left")
    } else {
      Caesar.encrypt(cipherText, math.abs(keyNum), "right")
    }
  }

  private def getLetterFrequencies(text: String): Seq[(Char, Int)] = {
    text
      .replaceAll("""[0-9]| """, "")
      .distinct.map(character => (character, text.count(_ == character)))
      .sortBy({ case (_, frequency) => -frequency })
  }
}
