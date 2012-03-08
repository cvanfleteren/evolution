package be.cvalue.evolution

import scala.util.Random

object RandomUtils {

	private val RANDOM = new Random();

	def randomString(length: Int, chars: String): String = {
		val buf = new StringBuilder();

		for (i <- 1 to length) {
			buf.append(randomChar(chars))
		}

		return buf.toString();
	}

	def randomInt(max: Int) : Int  =  RANDOM.nextInt(max);

	def randomChar(chars: String) : Char = chars.charAt(randomInt(chars.length()));

	def randomBoolean() = RANDOM.nextBoolean();
}
