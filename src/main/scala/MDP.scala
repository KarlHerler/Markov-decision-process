package com.karlherler.mdp


class MDP extends TwoDimensionalWorld 
			  with FunctionalValueIteration
			  with TwoDimensionalTransition {

	val startState = (0, 0)
	val goalStates = List((1, 3), (2, 3))
	val r = -0.04
	//val gamma = 0.6
	val gamma = 0.8

	
	private def prettify(u:Array[Array[State]]) = {
		(u.map(_.map(_.utility.toString) mkString ", ") mkString "\n")
	}

	override def toString = prettify(valueIteration(u, t, r, gamma))
}
object MDP {
	def main(args: Array[String]): Unit = {
	  val mdp = new MDP
	  println(mdp)
	}
	
}



case class State(utility: Double, coordinates: (Int, Int), enabled: Boolean = true, goal: Boolean = false)
case class PossibleState(probability: Double, coordinates: (Int, Int))
case class Action(direction: String)
