package example

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

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
    
    def run(): Unit = ???
    
    dom.window.setInterval(() => run(), ???)
  }
}
