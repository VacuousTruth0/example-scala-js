package example

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.util.Random

/** Randomly plots points to create a Sierpinski triangle. */
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
    val ctx: dom.CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    
    /** Number of points plotted. */
    var count: Int = 0
    
    /** Current position on the canvas. */
    var p: Point = Point(0, 0)
    
    /** Vertices of the Sierpinski triangle. */
    val vertices: Seq[Point] = Seq(Point(255, 255), Point(0, 255), Point(128, 0))
    
    /** Returns a random element of a sequence. */
    def randomFromSeq[T](xs: Seq[T]): T = xs(Random.nextInt(xs.length))
    
    /** Clears the canvas. */
    def clear(): Unit = {
      ctx.fillStyle = "black"
      ctx.fillRect(0, 0, 255, 255)
    }
    
    /** Runs one iteration. */
    def run(): Unit = for (_ <- 0 until 10) {
      
      // Reset the canvas if enough points have been plotted
      if (count % 3000 == 0) clear()
      
      // Increment the number of points plotted
      count += 1
      
      // Move to halfway between the previous position and a randomly chosen corner
      p = (p + randomFromSeq(vertices)) / 2
      
      // Set RGB values based on the current position
      val height: Double = 512.0 / (255 + p.y)
      
      val r: Int = (p.x * height).toInt
      val g: Int = ((255 - p.x) * height).toInt
      val b: Int = p.y
      
      // Colour in the pixel at the current position on the canvas
      ctx.fillStyle = s"rgb($r, $g, $b)"
      ctx.fillRect(p.x, p.y, 1, 1)
    }
    
    dom.window.setInterval(() => run(), 50)
  }
}
