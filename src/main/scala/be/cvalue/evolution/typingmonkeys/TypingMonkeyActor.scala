package be.cvalue.evolution.typingmonkeys

import akka.actor.Actor
import be.cvalue.evolution.Evolution
import be.cvalue.evolution.typingmonkeys.TypingMonkeys.{TextNature, TextOrganism, TextDna}

class TypingMonkeyActor extends Actor {

	def receive  = {

		case EvolutionAttempt(goal) => {
			val evolution = new Evolution {
				val nature = new TextNature(goal)
				val progressTracker = TypingMonkeyActor.this.context.actorFor("akka://MySystem/user/ProgressActor")
			}

			evolution.evolve(new TextOrganism(new TextDna(" ")))
			println("Stopped evolving after "+evolution.nature.generationCount+" generations")
		}
	}



}

