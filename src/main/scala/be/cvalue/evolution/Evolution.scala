package be.cvalue.evolution

import be.cvalue.evolution.typingmonkeys.{EvolutionProgress, ProgressActor}
import akka.actor.ActorRef


trait Evolution {

	type SomeOrganism = nature.SomeOrganism

	val nature: Nature
	val progressTracker:ActorRef

	def evolve(organism: SomeOrganism)  {
		var offspring = nature.offSpring(organism)

		var generation = 1;
		while(nature.canEvolve(offspring)) {
				offspring = evolve(offspring)
				generation +=1
			if( generation % 1000 == 0 || (generation < 1000 && generation % 100 == 0)) {
				progressTracker ! EvolutionProgress(null,offspring.head,generation)
			}
		}
		println("Reached endpoint with offspring: "+offspring)
	}

	private def evolve(organisms: List[SomeOrganism]) : List[SomeOrganism] = {
		nature.select(organisms.map(nature.offSpring(_)).flatten)
	}

}
