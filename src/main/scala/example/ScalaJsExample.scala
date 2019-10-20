package example

import example.functions.draw.DrawFunctions
import example.functions.state.StateFunctions
import example.functions.state.StateFunctions.drawParams
import org.scalajs.dom
import org.scalajs.dom.{html, raw}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/** Plots graphs over time. */
@JSExportTopLevel("ScalaJsExample")
object ScalaJsExample {
  
  /** Resizes the canvas to fill its parent element. This automatically clears the canvas.
    *
    * @param canvas HTML canvas element.
    */
  private def resize(canvas: html.Canvas): Unit = {
    val parent: raw.HTMLElement = canvas.parentElement
    
    canvas.width = parent.clientWidth
    canvas.height = parent.clientHeight
  }
  
  /** Runs one iteration.
    *
    * Plots a single point for each graph at the current horizontal position (x-value) on the canvas.
    *
    * @param brush     Rendering context for the canvas.
    * @param canvasDim Canvas dimensions.
    */
  private def run(brush: dom.CanvasRenderingContext2D, canvasDim: CanvasDim): Unit = {
    StateFunctions.update(canvasDim)
    DrawFunctions.draw(brush, drawParams(canvasDim), canvasDim)
  }
  
  /** Main method. This will be called in the HTML page.
    *
    * Resizes the canvas to fill its parent element,
    * then runs iterations repeatedly with the specified time interval.
    *
    * @param canvas HTML canvas element.
    */
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    
    resize(canvas)
    val canvasDim: CanvasDim = CanvasDim(canvas.width, canvas.height)
    
    val brush: dom.CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    dom.window.setInterval(() => run(brush, canvasDim), 20)
  }
}
