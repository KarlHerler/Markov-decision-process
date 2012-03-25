package com.karlherler.mdp

import math.max
import math.abs

trait ImperativeValueIteration {
	
	val epsilon = 0.001
	val actions = List(Action("up"), Action("right"), Action("down"), Action("left"))

	

	def valueIteration(u: Array[Array[State]], t: (State, Action) => List[PossibleState], r: Double, gamma: Double): Array[Array[State]] = {
		
		def bellman(s: State, u: Array[Array[State]]): Double = {
			val res = new Array[(String, Double)](actions.length)
			
			for (i <- (0 until actions.length)) {
				val transitions = t(s, actions(i))
				val nonNormalizedUtilities = new Array[Double](transitions.length)
					for (j <- (0 until transitions.length)) {
						nonNormalizedUtilities(j) = (
							transitions(j).probability * 
							u(transitions(j).coordinates._1)(transitions(j).coordinates._2).utility
						)
					}  
					res(i) = (actions(i).direction, nonNormalizedUtilities.sum) //∑T(s,a,s')U(s')
			}
			
			val (direction, maxUtility) = res.maxBy(_._2) //grab max a
			r+(gamma*maxUtility) //R[s]+γmax∑T(s,a,s')U(s')
			
		}


		var delta = 100.0
		var util = u
		var iter = 0
		
		while((delta > epsilon*(1-gamma)/gamma)) {
			delta=0
			var uprim = for(uu <- util) yield { for(u <- uu) yield u }

			for (i <- (0 until util.length)) {
				for (j <- (0 until util(0).length)) {
					if (util(i)(j).enabled && !util(i)(j).goal) {
						uprim(i)(j) = State(bellman(util(i)(j), util), 
											util(i)(j).coordinates, 
											util(i)(j).enabled, 
											util(i)(j).goal)
					} 

					if (abs(uprim(i)(j).utility - util(i)(j).utility) > delta) { 
						delta = abs(uprim(i)(j).utility - util(i)(j).utility)
					}
				}
			}
			iter+=1
			util = uprim
		}
		util
	}
























	/*def valueIteration(u: Array[Array[State]], t: (State, Action) => List[PossibleState], r: Double, gamma: Double): Array[Array[State]] = {
		val actions = List(new Action("up"), new Action("right"), new Action("down"), new Action("left"))	
		
		def bellman(s: State) = {
			val res = actions.map { a => {
					val nonNormalizedUtilities = t(s, a).map { p =>
						(p.probability*u(p.coordinates._1)(p.coordinates._2).utility)
					}
					

					(a.direction, nonNormalizedUtilities.sum) //∑T(s,a,s')U(s')
				}
			}

			val (direction, maxUtility) = res.maxBy(_._2) //grab max
			
			r+(gamma*maxUtility)
		}

		u.map(_.map{ state =>
				if (state.enabled && !state.goal) 
					(new State(bellman(state), state.coordinates, state.enabled, state.goal)) 
				else 
					state
			})
	}*/
}
