package com.karlherler.mdp


class TwoDimensionalWorld {
	val u = Array(
					Array(State(0.0, (0,0)), State(0.0, (0,1)), State(0.0, (0,2)), State(0.0, (0,3))),
					Array(State(0.0, (1,0)), State(0.0, (1,1), false), State(0.0, (1,2)), State(-1.0, (1,3), true, true)),
					Array(State(0.0, (2,0)), State(0.0, (2,1)), State(0.0, (2,2)), State(1.0, (2,3), true, true))
				)
}