package example.functions

import example.Point
import org.scalajs.dom

import scala.util.Random

/** Functions used to move the current position and update the canvas. */
object HelperFunctions {
  
  /** RGB component values that define a colour.
    *
    * @param r Intensity of red, from 0 to 255.
    * @param g Intensity of green, from 0 to 255.
    * @param b Intensity of blue, from 0 to 255.
    */
  private case class Rgb(r: Int, g: Int, b: Int)
  
  /** Vertices of the Sierpinski triangle. */
  private val vertices: Seq[Point] = {
    
    val topVertex: Point = Point(128, 0)
    val leftVertex: Point = Point(0, 255)
    val rightVertex: Point = Point(255, 255)
    
    Seq(topVertex, leftVertex, rightVertex)
  }
  
  /** Clears the canvas.
    *
    * @param ctx Rendering context for the canvas.
    */
  def clear(ctx: dom.CanvasRenderingContext2D): Unit = {
    ctx.fillStyle = "black"
    ctx.fillRect(0, 0, 255, 255)
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
  
  /** Returns the RGB component values for the current position.
    *
    * @param p Current position.
    */
  private def getRgb(p: Point): Rgb = {
    val height: Double = 512.0 / (255 + p.y)
    
    val r: Int = (height * p.x).toInt
    val g: Int = (height * (255 - p.x)).toInt
    val b: Int = p.y
    
    Rgb(r, g, b)
  }
  
  /** Colours in the pixel at the current position on the canvas.
    *
    * @param ctx Rendering context for the canvas.
    * @param p   Current position.
    */
  def plotPoint(ctx: dom.CanvasRenderingContext2D, p: Point): Unit = {
    val Rgb(r, g, b) = getRgb(p)
    
    ctx.fillStyle = s"rgb($r, $g, $b)"
    ctx.fillRect(p.x, p.y, 1, 1)
  }
}
