package example

import org.scalajs.dom
import org.scalajs.dom.html

import scala.math.{abs, sin}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/** Plots graphs over time. */
@JSExportTopLevel("ScalaJsExample")
object ScalaJsExample {
  
  /** Main method. This will be called in the HTML page.
    *
    * Runs iterations repeatedly with the specified time interval.
    *
    * @param canvas HTML canvas element.
    */
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    
    /** Resizes the canvas to fill its parent element. This automatically clears the canvas. */
    def clear(): Unit = {
      canvas.width = canvas.parentElement.clientWidth
      canvas.height = canvas.parentElement.clientHeight
    }
    
    // Resize and clear the canvas
    clear()
    
    // Rendering context for the canvas
    val brush: dom.CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    
    /** Returns the height of the canvas. */
    def h: Int = canvas.height
    
    /** Returns the width of the canvas. */
    def w: Int = canvas.width
    
    /** Current horizontal position (x-value) on the canvas. */
    var x: Int = 0
    
    // Colour and function for a graph to be plotted
    type Graph = (String, Double => Double)
    
    // Colour and function for each graph to be plotted
    val graphs: Seq[(Graph, Int)] = Seq[Graph](
      ("red", sin),
      ("green", x => abs(x % 4 - 2) - 1),
      ("blue", x => sin(x / 12) * sin(x))
    )
      .zipWithIndex
    
    /** Runs one iteration. */
    def run(): Unit = {
      
      // Move the current horizontal position one pixel to the right,
      // looping back around to the left side if it would otherwise move off the canvas
      x = (x + 1) % w
      
      // Resize and clear the canvas if the current horizontal position is at the left side
      if (x == 0) clear()
      
      // Loop over each graph
      for (((color, f), i) <- graphs) {
        
        // Vertical offset for the graph (vertical position of the x-axis on the canvas)
        val offset: Double = h / 3 * (i + 0.5)
        
        // Y-value on the graph (relative to its x-axis)
        val y: Double = f(x / w * 75) * h / 30
        
        // Plot a point at the current position on the graph
        brush.fillStyle = color
        brush.fillRect(x, y + offset, 3, 3)
      }
    }
    
    dom.window.setInterval(() => run(), 20)
  }
}
