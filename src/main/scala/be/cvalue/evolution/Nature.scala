package be.cvalue.evolution

trait Nature {

	type SomeOrganism <: Organism

	def select(currentGeneration:List[SomeOrganism ]) : List[SomeOrganism]

	def canEvolve(organisms: List[SomeOrganism]): Boolean

	def offSpring(organism: SomeOrganism) : List[SomeOrganism]

	def evolve(organism: SomeOrganism)  {
		val offspring = offSpring(organism)

		if(canEvolve(offspring)) {
			val fittest = select(offspring);
			evolve(fittest.head)
		}
	}

}
