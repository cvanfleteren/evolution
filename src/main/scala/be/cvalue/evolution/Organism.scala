package be.cvalue.evolution

trait Organism {

	type SomeDna <: Dna
	type ThisOrganism <: Organism

	val dna: SomeDna

	def reproduce : List[ThisOrganism]

}
