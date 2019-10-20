package example.functions.draw

import example.util.ConfigProvider.config
import example.{CanvasDim, DrawParams}
import org.scalajs.dom

import scala.collection.JavaConverters.asScalaBufferConverter

/** Contains functions used to update the canvas contents. */
object DrawFunctions {
  
  /** Canvas background colour. */
  private val backgroundColour: String = config.getString("colour.background")
  
  /** Colour for each graph, arranged in the vertical order of the graphs on the page. */
  private val graphColours: Seq[String] = config.getStringList("colour.graphs").asScala
  
  /** Height and width of the points plotted. */
  private val pointSize: Int = config.getInt("plot.pointSize")
  
  /** Clears the canvas.
    *
    * @param brush     Rendering context for the canvas.
    * @param canvasDim Canvas dimensions.
    */
  private def clear(brush: dom.CanvasRenderingContext2D, canvasDim: CanvasDim): Unit = {
    import canvasDim.{height, width}
    
    brush.fillStyle = backgroundColour
    brush.fillRect(0, 0, width, height)
  }
  
  /** Colours in a small square on the canvas, with its top left corner at the current position for the graph.
    *
    * @param brush  Rendering context for the canvas.
    * @param x      Current horizontal position (x-value) on the canvas.
    * @param y      Current vertical position (y-value) on the canvas for the graph.
    *               This has been scaled and offset relative to the x-axis of the graph.
    * @param colour Colour for the graph.
    */
  private def plotPoint(brush: dom.CanvasRenderingContext2D, x: Int, y: Int, colour: String): Unit = {
    brush.fillStyle = colour
    brush.fillRect(x, y, pointSize, pointSize)
  }
  
  /** Updates the canvas, based on the current program state.
    *
    * Clears the canvas if the graphs have looped back around to the left side of the canvas.
    * Then plots a point at the current position for each graph.
    *
    * @param brush      Rendering context for the canvas.
    * @param drawParams Parameters derived from the current program state that are required to update the canvas contents.
    * @param canvasDim  Canvas dimensions.
    */
  def draw(brush: dom.CanvasRenderingContext2D, drawParams: DrawParams, canvasDim: CanvasDim): Unit = {
    
    import drawParams.{clearFlag, x, ys}
    if (clearFlag) clear(brush, canvasDim)
    
    ys.zip(graphColours)
      .foreach { case (y, colour) => plotPoint(brush, x, y, colour) }
  }
}
