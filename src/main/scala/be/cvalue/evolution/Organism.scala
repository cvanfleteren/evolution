package be.cvalue.evolution

trait Organism {

	type ThisOrganism <: Organism

	def reproduce : List[ThisOrganism]

}
