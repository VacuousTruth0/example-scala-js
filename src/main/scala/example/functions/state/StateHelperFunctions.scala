package example.functions.state

import example.CanvasDim.{maxX, maxY}
import example.Point
import example.util.ConfigProvider.config

import scala.util.Random

/** Contains functions that are used in updating the program state but cannot access the state directly. */
private[state] object StateHelperFunctions {
  
  /** Clear the canvas whenever this number of points has been plotted (since the canvas was last cleared). */
  private val maxPoints: Int = config.getInt("plot.maxPoints")
  
  /** Vertices of the Sierpinski triangle. */
  private val vertices: Seq[Point] = {
    
    val topVertex: Point = Point(maxX / 2, 0)
    val leftVertex: Point = Point(0, maxY)
    val rightVertex: Point = Point(maxX, maxY)
    
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
  
  /** Returns true if the canvas should be cleared before plotting the next point and false otherwise.
    *
    * Note that the number of points plotted is one less than the number of positions generated,
    * since the most recently generated position has not yet been plotted.
    *
    * @param count Number of positions generated.
    */
  def getClearFlag(count: Int): Boolean = {
    val numPoints: Int = count - 1
    numPoints % maxPoints == 0
  }
}
