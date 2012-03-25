package com.karlherler.mdp

import math.max
import math.abs

trait FunctionalValueIteration {
	
	val epsilon = 0.001
	val actions = List(new Action("up"), new Action("right"), new Action("down"), new Action("left"))


	def valueIteration(u: Array[Array[State]], t: (State, Action) => List[PossibleState], r: Double, gamma: Double): Array[Array[State]] = {
		var delta = 100.0
		var util = u
		var iter = 0

		def bellman(s: State, u: Array[Array[State]]): Double = {
			val (direction, maxUtility) = actions.map(a =>
					(a.direction, t(s, a).map(p =>
						(p.probability*u(p.coordinates._1)(p.coordinates._2).utility)
					).sum) //∑T(s,a,s')U(s')
			).maxBy(_._2) //grab max a

			r+(gamma*maxUtility) //R[s]+γmax∑T(s,a,s')U(s')
		}

		def recursiveValueIteration(util: Array[Array[State]]): Array[Array[State]] = {
			val newUtil = util.map(_.map { u =>
				if (u.enabled && !u.goal)
					State(bellman(u, util), u.coordinates, u.enabled, u.goal) 
				else
					u 
			})

			val flatUtil = util.flatten 
			val delta = newUtil.flatten.zip(flatUtil).foldLeft(0.0)((a, b) => max(a, abs(b._1.utility - b._2.utility)))
			
			if ((delta > epsilon*(1-gamma)/gamma)) recursiveValueIteration(newUtil)
			else newUtil
			
		}
		
		
		recursiveValueIteration(util)
	}

}
