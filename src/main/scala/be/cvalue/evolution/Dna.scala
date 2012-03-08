package be.cvalue.evolution

trait Dna {

	type ThisDna <: Dna

	def copy: ThisDna;

}
