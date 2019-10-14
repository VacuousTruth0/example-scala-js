package example.functions.draw

import example.{DrawParams, Point}
import org.scalajs.dom

/** Contains functions used to update the canvas. */
object DrawFunctions {
  
  /** RGB component values that define a colour.
    *
    * @param r Intensity of red, from 0 to 255.
    * @param g Intensity of green, from 0 to 255.
    * @param b Intensity of blue, from 0 to 255.
    */
  private case class Rgb(r: Int, g: Int, b: Int)
  
  /** Clears the canvas.
    *
    * @param ctx Rendering context for the canvas.
    */
  private def clear(ctx: dom.CanvasRenderingContext2D): Unit = {
    ctx.fillStyle = "black"
    ctx.fillRect(0, 0, 255, 255)
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
  private def plotPoint(ctx: dom.CanvasRenderingContext2D, p: Point): Unit = {
    val Rgb(r, g, b) = getRgb(p)
    
    ctx.fillStyle = s"rgb($r, $g, $b)"
    ctx.fillRect(p.x, p.y, 1, 1)
  }
  
  /** Updates the canvas, based on the current program state.
    *
    * Clears the canvas if enough points have been plotted.
    * Then plots a point at the current position.
    *
    * Note that the number of points plotted is initially one less than the number of positions generated,
    * since the most recently generated position has not yet been plotted.
    *
    * @param ctx    Rendering context for the canvas.
    * @param params Parameters derived from the current program state that are required to update the canvas.
    */
  def draw(ctx: dom.CanvasRenderingContext2D, params: DrawParams): Unit = {
    
    import params.{count, p}
    val numPoints: Int = count - 1
    
    if (numPoints % 3000 == 0) clear(ctx)
    plotPoint(ctx, p)
  }
}
