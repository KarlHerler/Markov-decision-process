package com.karlherler.mdp

trait TwoDimensionalTransition extends TwoDimensionalWorld {

	/**
	 * A slightly modified version of the T(s,a,s') function, instead of just
	 * returning a probability a given action this function returns the 
	 * probabilty of all actions for a given state.
	 *
	 * @input s, a State where the action is taken
	 * @input a, an Action applied in State s
	 *
	 * @returns List[PossibleState], a list of PossibleStates, which are a 
	 *				decorated form of State that has a associated probability.
	 **/
	def t(s: State, a: Action): List[PossibleState] = {
		a.direction match {
			case "up" => {
				List(
						PossibleState(0.8, move((s.coordinates), (s.coordinates._1+1, s.coordinates._2))),
					 	PossibleState(0.1, move((s.coordinates), (s.coordinates._1, s.coordinates._2+1))),
					 	PossibleState(0.1, move((s.coordinates), (s.coordinates._1, s.coordinates._2-1)))
					)
			}
			case "right" => {
				List(
						PossibleState(0.8, move((s.coordinates), (s.coordinates._1, s.coordinates._2+1))),
					 	PossibleState(0.1, move((s.coordinates), (s.coordinates._1+1, s.coordinates._2))),
					 	PossibleState(0.1, move((s.coordinates), (s.coordinates._1-1, s.coordinates._2)))
					)
			}
			case "down"	=> {
				List(
						PossibleState(0.8, move((s.coordinates), (s.coordinates._1-1, s.coordinates._2))),
					 	PossibleState(0.1, move((s.coordinates), (s.coordinates._1, s.coordinates._2+1))),
					 	PossibleState(0.1, move((s.coordinates), (s.coordinates._1, s.coordinates._2-1)))
					)
			}
			case "left"	=> {
				List(
						PossibleState(0.8, move((s.coordinates), (s.coordinates._1, s.coordinates._2-1))),
					 	PossibleState(0.1, move((s.coordinates), (s.coordinates._1+1, s.coordinates._2))),
					 	PossibleState(0.1, move((s.coordinates), (s.coordinates._1-1, s.coordinates._2)))
					)
			}
			case _ 	=> throw new Exception(
								"I'm afraid I can't let you do that Dave. (unknown direction)"
							)
		}
	}

	def move(start: (Int, Int), dest: (Int, Int)): (Int, Int) = {
		val y = if ((dest._1 < 0) || (dest._1 >= u.length)) 	start._1 else dest._1
		val x = if ((dest._2 < 0) || (dest._2 >= u(y).length)) 	start._2 else dest._2
		if (u(y)(x).enabled) (y, x) else start
	}
}