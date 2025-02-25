package com.akikanellis.skyspark.scala.api.algorithms.bnl

import com.akikanellis.skyspark.scala.api.algorithms.Point

import scala.collection.mutable.ListBuffer

/**
  * The BNL algorithm for computing the skylines out of a set of points. Computing with pre-comparison will first check
  * if any point needs to be removed by using the pre-comparison algorithm. For details check the paper mentioned in
  * [[com.akikanellis.skyspark.scala.api.algorithms.bnl.BlockNestedLoop]]
  */
private[bnl] class BnlAlgorithm extends Serializable {

  private[bnl] def computeSkylinesWithPreComparison(flagsWithPoints: Traversable[(Flag, Point)]): Traversable[Point] = {
    val skylines = ListBuffer[Point]()

    flagsWithPoints
      .filter(fp => passesPreComparison(fp._1))
      .map(_._2)
      .foreach(addDiscardOrDominate(skylines, _))

    skylines
  }

  private def passesPreComparison(flag: Flag): Boolean = {
    val anyBitIsFalse = flag.bits.contains(false)
    anyBitIsFalse
  }

  private def addDiscardOrDominate(currentSkylines: ListBuffer[Point], candidateSkyline: Point) {
    currentSkylines.foreach {pointToCheckAgainst =>
      if (pointToCheckAgainst.dominates(candidateSkyline)) return

      if (candidateSkyline.dominates(pointToCheckAgainst)) currentSkylines -= pointToCheckAgainst
    }

    currentSkylines += candidateSkyline
  }

  private[bnl] def computeSkylinesWithoutPreComparison(points: Traversable[Point]): Traversable[Point] = {
    val skylines = ListBuffer[Point]()

    points.foreach(addDiscardOrDominate(skylines, _))

    skylines
  }
}
