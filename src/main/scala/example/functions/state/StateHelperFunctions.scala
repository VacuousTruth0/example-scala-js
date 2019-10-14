package example.functions.state

import example.Point

import scala.util.Random

/** Contains functions that are used in updating the program state but cannot access the state directly. */
private[state] object StateHelperFunctions {
  
  /** Vertices of the Sierpinski triangle. */
  private val vertices: Seq[Point] = {
    
    val topVertex: Point = Point(128, 0)
    val leftVertex: Point = Point(0, 255)
    val rightVertex: Point = Point(255, 255)
    
    Seq(topVertex, leftVertex, rightVertex)
  }
  
  /** Returns a random element of a sequence.
    *
    * @param xs Input sequence.
    * @tparam T Type of the elements in the input sequence.
    */
  private def randomFromSeq[T](xs: Seq[T]): T = xs(Random.nextInt(xs.length))
  
  /** Returns the next position (halfway between the current position and a randomly chosen vertex).
    *
    * @param p Current position.
    */
  def nextPosition(p: Point): Point = {
    val vertex: Point = randomFromSeq(vertices)
    (p + vertex) / 2
  }
}
