package example

import example.functions.HelperFunctions.{clear, nextPosition, plotPoint}
import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/** Randomly plots points to create a Sierpinski triangle. */
@JSExportTopLevel("ScalaJsExample")
object ScalaJsExample {
  
  /** Number of points plotted. */
  var count: Int = 0
  
  /** Current position on the canvas. */
  var p: Point = Point(0, 0)
  
  /** Runs one iteration.
    *
    * @param ctx Rendering context for the canvas.
    */
  private def run(ctx: dom.CanvasRenderingContext2D): Unit = (0 until 10).foreach { _ =>
    
    // Clear the canvas if enough points have been plotted
    if (count % 3000 == 0) clear(ctx)
    
    // Move to the next position
    p = nextPosition(p)
    
    // Plot a point at the current position
    plotPoint(ctx, p)
    
    // Increment the number of points plotted
    count += 1
  }
  
  /** Main method. This will be called in the HTML page.
    *
    * Runs iterations repeatedly with the specified time interval.
    *
    * @param canvas HTML canvas element.
    */
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val ctx: dom.CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    dom.window.setInterval(() => run(ctx), 50)
  }
}
