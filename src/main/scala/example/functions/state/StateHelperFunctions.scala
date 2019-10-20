package example.functions.state

import example.CanvasDim

import scala.math.{abs, sin}

/** Contains functions that are used in updating the program state but cannot access the state directly. */
private[state] object StateHelperFunctions {
  
  /** A function whose graph will be plotted.
    * It represents a real-valued mathematical function on the real numbers.
    */
  private type GraphFunction = Double => Double
  
  /** All functions whose graphs will be plotted,
    * arranged in the vertical order of the graphs on the page.
    */
  private val graphFunctions: Seq[GraphFunction] = Seq(
    sin,
    x => abs(x % 4 - 2) - 1,
    x => sin(x / 12) * sin(x)
  )
  
  /** Number of graphs to be plotted. */
  private val numGraphs: Int = graphFunctions.length
  
  /** Returns the current standardised horizontal position (x-value), which will be the input to the graph functions.
    *
    * This is calculated by rescaling the current horizontal position on the canvas
    * from the canvas width to the standard width.
    *
    * @param x     Current horizontal position on the canvas.
    * @param width Canvas width, in pixels.
    */
  private def getGraphX(x: Int, width: Int): Double = x * 75.0 / width
  
  /** Returns the current standardised vertical positions (y-values).
    *
    * These are calculated by applying the graph functions to the current standardised horizontal position (x-value).
    *
    * @param stdX Current standardised horizontal position (x-value).
    */
  private def getGraphYs(stdX: Double): Seq[Double] = graphFunctions.map(f => f(stdX))
  
  /** Returns the current scaled vertical positions (y-values).
    *
    * These are calculated by rescaling the current standardised vertical positions
    * from the standard height to the canvas height.
    *
    * @param stdYs  Current standardised vertical positions.
    * @param height Canvas height, in pixels.
    */
  private def getScaledYs(stdYs: Seq[Double], height: Int): Seq[Int] = stdYs.map(_ / 30.0 * height).map(_.toInt)
  
  /** Returns the current vertical positions (y-values) on the canvas of the x-axes of the graphs,
    * arranged in the vertical order of the graphs on the page.
    *
    * The canvas height is divided equally between the graphs
    * and the x-axes are positioned at the vertical midpoint of each division.
    *
    * @param height Canvas height, in pixels.
    */
  private def getOffsets(height: Int): Seq[Int] =
    (0 until numGraphs).map(i => (i + 0.5) * height / numGraphs).map(_.toInt)
  
  
  /** Returns the current vertical position (y-value) on the canvas for each graph,
    * arranged in the vertical order in which the graphs will be plotted on the page.
    *
    * The current standardised vertical positions are calculated by
    * applying the graph functions to the current standardised vertical position (x-value).
    *
    * These vertical positions are then scaled relative to the canvas height
    * and offset relative to the vertical positions of the x-axes of the graphs.
    *
    * @param x         Current horizontal position (x-value) on the canvas.
    * @param canvasDim Canvas dimensions.
    */
  def getYs(x: Int, canvasDim: CanvasDim): Seq[Int] = {
    import canvasDim.{height, width}
    
    val stdX: Double = getGraphX(x, width)
    val stdYs: Seq[Double] = getGraphYs(stdX)
    
    val scaledYs: Seq[Int] = getScaledYs(stdYs, height)
    val offsets: Seq[Int] = getOffsets(height)
    
    (scaledYs, offsets).zipped.map(_ + _)
  }
  
  /** Returns true if the canvas should be cleared before plotting the next point and false otherwise.
    *
    * The canvas should be cleared if the graphs have looped back around to the left side of the canvas.
    *
    * @param x Current horizontal position (x-value) on the canvas.
    */
  def getClearFlag(x: Int): Boolean = x == 0
}
