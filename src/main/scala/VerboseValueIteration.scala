package com.karlherler.mdp

import math.max

abstract trait VerboseValueIteration {
	def valueIteration(u: Array[Array[State]], t: (State, Action) => List[PossibleState], r: Double, gamma: Double): Array[Array[State]] = {
		val actions = List(new Action("up"), new Action("right"), new Action("down"), new Action("left"))	


		def bellman(s: State) = {
			val output = new StringBuilder
			output.append("State: "+ s.coordinates+"\n")

			val res = actions.map { a => {
					output.append("	Action: "+a.direction+" => ")
					
					val nonNormalizedUtilities = t(s, a).map { p =>
						output.append(p.probability+"*"+u(p.coordinates._1)(p.coordinates._2).utility+"+")
						(p.probability*u(p.coordinates._1)(p.coordinates._2).utility)
					}
					
					output.append("="+nonNormalizedUtilities.sum+"\n")

					(a.direction, nonNormalizedUtilities.sum) //∑T(s,a,s')U(s')
				}
			}

			print(output)
			val (direction, maxUtility) = res.maxBy(_._2) //grab max
			println("Maximizing action: "+direction)
			println("###########")
			r+(gamma*maxUtility)
		}

		u.map(_.map{ state =>
				if (state.enabled && !state.goal) (new State(bellman(state), state.coordinates, state.enabled, state.goal)) else state
			})
	}
}
