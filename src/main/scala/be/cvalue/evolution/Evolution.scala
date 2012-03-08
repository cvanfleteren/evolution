package be.cvalue.evolution

trait Evolution {

	type SomeOrganism = nature.SomeOrganism

	val nature: Nature

	def evolve(organism: SomeOrganism)  {
		var offspring = nature.offSpring(organism)

		while(nature.canEvolve(offspring)) {
				offspring = evolve(offspring)
		}
		println("Reached endpoint with offspring: "+offspring)
	}

	private def evolve(organisms: List[SomeOrganism]) : List[SomeOrganism] = {
		nature.select(organisms.map(nature.offSpring(_)).flatten)
	}

}
