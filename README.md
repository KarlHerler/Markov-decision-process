#Installation

1.  Get [SBT](https://github.com/harrah/xsbt/wiki/Getting-Started-Setup)
2.  Clone this repo using `git clone git@github.com:KarlHerler/Markov-decision-process.git` 
3.  write: `sbt run` in the root of the newly cloned repo
4.  Done.


#Navigating the source

High level changes can be done in the (MDP.scala)[https://github.com/KarlHerler/Markov-decision-process/blob/master/src/main/scala/MDP.scala] file.

Changes to the way the world is defined can be done in the files:
(TwoDimensionalWorld.scala)[https://github.com/KarlHerler/Markov-decision-process/blob/master/src/main/scala/TwoDimensionalWorld.scala] and
(TwoDimensionalTransition.scala)[https://github.com/KarlHerler/Markov-decision-process/blob/master/src/main/scala/TwoDimensionalTransition.scala]. Most changes
to those files also prompt a change in the various value iteration files (mostly only changes in the dimensionality).
