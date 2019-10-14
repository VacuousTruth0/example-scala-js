package example

import example.functions.draw.DrawFunctions
import example.functions.state.StateFunctions
import example.functions.state.StateFunctions.drawParams
import example.util.ConfigProvider.config
import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/** Randomly plots points to create a Sierpinski triangle. */
@JSExportTopLevel("ScalaJsExample")
object ScalaJsExample {
  
  /** Number of points plotted per run. */
  private val pointsPerRun: Int = config.getInt("plot.pointsPerRun")
  
  /** Time between runs, in milliseconds. */
  private val waitTime: Int = config.getInt("plot.waitTime")
  
  /** Runs one iteration.
    *
    * Repeatedly updates the program state and then updates the canvas using the current state,
    * until the required number of points has been plotted.
    *
    * @param ctx Rendering context for the canvas.
    */
  private def run(ctx: dom.CanvasRenderingContext2D): Unit = (0 until pointsPerRun).foreach { _ =>
    StateFunctions.update()
    DrawFunctions.draw(ctx, drawParams)
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
    dom.window.setInterval(() => run(ctx), waitTime)
  }
}
