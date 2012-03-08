package be.cvalue.evolution.support

import be.cvalue.evolution.Nature

trait AbstractNature extends  Nature {

	private var count = 0

	def generationCount = count

	final def select(currentGeneration: List[SomeOrganism]) = {
		count+=1
		doNaturalSelection(currentGeneration)
	}

	def doNaturalSelection(currentGeneration: List[SomeOrganism]) : List[SomeOrganism]
}
