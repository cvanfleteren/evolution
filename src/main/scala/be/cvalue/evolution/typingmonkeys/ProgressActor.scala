package be.cvalue.evolution.typingmonkeys

import akka.actor.Actor

class ProgressActor extends Actor {

	var currentProgress =  Map[EvolutionAttempt,EvolutionProgress]()

	def receive  = {
		case EvolutionProgress(attempt,fittestSoFar,generation) => {
			println("Fittest so far in generation "+generation+" is "+fittestSoFar+" ")
		}
	}

}
