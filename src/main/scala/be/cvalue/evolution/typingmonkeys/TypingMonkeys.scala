package be.cvalue.evolution.typingmonkeys

import be.cvalue.evolution.RandomUtils._

import scala.math._
import be.cvalue.evolution.{Evolution, Organism, Dna}
import be.cvalue.evolution.support.AbstractNature

object TypingMonkeys extends App {

	val CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ "
	val GOAL = "TO BE OR NOT TO BE THAT IS THE QUESTION"


	val evolution = new Evolution {
		val nature = new TextNature
	}

	evolution.evolve(new TextOrganism(new TextDna("ZZZZ")))
	println("Stopped evolving after "+evolution.nature.generationCount+" generations")



	class TextDna(val text: String) extends Dna {
		type ThisDna = TextDna

		def copy: ThisDna = {
			val buf = new StringBuilder(text);

			// add a bit of mutation to the copy
			if (randomBoolean()) {
				val idxToMutate = randomInt(text.length());
				val mutation = randomChar(CHARS);
				buf.setCharAt(idxToMutate, mutation);
			}
			else {
				if (randomBoolean()) {
					// add letter
					buf.append(randomChar(CHARS));
				}
				else if (text.length() > 1) {
					// remove letter
					buf.deleteCharAt(buf.length - 1);
				}
			}

			return new TextDna(buf.toString());
		}

	}

	class TextOrganism(val dna: TextDna) extends Organism {

		type ThisOrganism = TextOrganism
		type SomeDna = TextDna

		def reproduce = {
			List.fill(100)(new TextOrganism(dna.copy))
		}

		override def toString = dna.text
	}

	class TextNature extends AbstractNature {

		type SomeOrganism = TextOrganism

		def doNaturalSelection(currentGeneration: List[TextOrganism]) =  List(currentGeneration.maxBy(fitnessOf(_)))

		def canEvolve(organisms: List[TextOrganism]) = organisms.filter(_.dna.text == GOAL).isEmpty

		def offSpring(organism: TextOrganism) = organism.reproduce

		private def fitnessOf(organism: TextOrganism): Int = {

			var fitness = 0;

			fitness += min(GOAL.length(), organism.dna.text.length()) -
				max(GOAL.length(), organism.dna.text.length());

			var i = 0
			while (i < organism.dna.text.length() && i < GOAL.length()) {
				if (organism.dna.text.charAt(i) == GOAL.charAt(i)) {
					fitness += 1;
				}
				i += 1;
			}

			return fitness;
		}
	}

}
