package be.cvalue.evolution.typingmonkeys

import be.cvalue.evolution.support.{AbstractNature}
import be.cvalue.evolution.support.RandomUtils._

import scala.math._
import be.cvalue.evolution.{Organism, Dna}
import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinRouter

object TypingMonkeys extends App {


	val system = ActorSystem("MySystem")
	val monkeys = system.actorOf(Props[TypingMonkeyActor].withRouter(RoundRobinRouter(nrOfInstances = 8)))
	val progressActor = system.actorOf(Props[ProgressActor], name="ProgressActor")

	for (i <- 1 to 1) {
		monkeys ! EvolutionAttempt("Dit is een test van de brandweerinstallatie. Blijf waar je bent, er is geen enkel gevaar.")
	}


	class TextDna(val text: String) extends Dna {
		val CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz, 1234567890."

		type ThisDna = TextDna

		def copy: TextDna = {
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

		def reproduce = List.fill(75)(new TextOrganism(dna.copy))

		override def toString = dna.text
	}

	class TextNature(goal:String) extends AbstractNature {

		type SomeOrganism = TextOrganism

		def doNaturalSelection(currentGeneration: List[TextOrganism]) =  List(currentGeneration.maxBy(fitnessOf(_)))

		def canEvolve(organisms: List[TextOrganism]) = organisms.filter(_.dna.text == goal).isEmpty

		def offSpring(organism: TextOrganism) = organism.reproduce

		private def fitnessOf(organism: TextOrganism): Int = {

			var fitness = 0;

			fitness += min(goal.length(), organism.dna.text.length()) -
				max(goal.length(), organism.dna.text.length());

			var i = 0
			while (i < organism.dna.text.length() && i < goal.length()) {
				if (organism.dna.text.charAt(i) == goal.charAt(i)) {
					fitness += 1;
				}
				i += 1;
			}

			return fitness;
		}
	}

}
