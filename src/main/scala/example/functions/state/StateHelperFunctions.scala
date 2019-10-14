package example.functions.state

import example.CanvasDim.{maxX, maxY}
import example.Point
import example.util.ConfigProvider.config

import scala.util.Random

/** Contains functions that are used in updating the program state but cannot access the state directly. */
private[state] object StateHelperFunctions {
  
  /** Clear the canvas whenever this number of points has been plotted (since the canvas was last cleared). */
  private val maxPoints: Int = config.getInt("plot.maxPoints")
  
  /** Scale down the copies of the fractal by this factor. */
  private val fractalScale: Int = config.getInt("fractalScale")
  
  /** Attractors of the Sierpinski carpet (vertices and midpoints of the sides). */
  private val attractors: Seq[Point] = {
  
    val midX: Int = maxX / 2
    val midY: Int = maxY / 2
    
    val topLeft: Point = Point(0, 0)
    val topRight: Point = Point(maxX, 0)
    val bottomLeft: Point = Point(0, maxY)
    val bottomRight: Point = Point(maxX, maxY)
    
    val midTop: Point = Point(midX, 0)
    val midBottom: Point = Point(midX, maxY)
    val midLeft: Point = Point(0, midY)
    val midRight: Point = Point(maxX, midY)
    
    val vertices: Seq[Point] = Seq(topLeft, topRight, bottomLeft, bottomRight)
    val sideMidpoints: Seq[Point] = Seq(midTop, midBottom, midLeft, midRight)
    
    vertices ++ sideMidpoints
  }
  
  /** Returns a random element of a sequence.
    *
    * @param xs Input sequence.
    * @tparam T Type of the elements in the input sequence.
    */
  private def randomFromSeq[T](xs: Seq[T]): T = xs(Random.nextInt(xs.length))
  
  /** Returns the next position (two thirds of the way from the current position to a randomly chosen attractor).
    *
    * @param p Current position.
    */
  def nextPosition(p: Point): Point = {
    val attractor: Point = randomFromSeq(attractors)
    (p + attractor * (fractalScale - 1)) / fractalScale
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
