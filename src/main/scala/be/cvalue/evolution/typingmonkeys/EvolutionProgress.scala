package be.cvalue.evolution.typingmonkeys

import be.cvalue.evolution.Organism

case class EvolutionProgress(attempt:EvolutionAttempt, fittestSoFar: Organism, generation:Int)
