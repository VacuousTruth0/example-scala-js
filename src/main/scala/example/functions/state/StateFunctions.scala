package example.functions.state

import example.{CanvasDim, DrawParams}
import StateHelperFunctions.{getClearFlag, getYs}

/** Contains the program state, and functions used to access and update it. */
object StateFunctions {
  
  /** Program state. */
  private object State {
    
    /** Current horizontal position (x-value) on the canvas. */
    var x: Int = 0
  }
  
  import State.x
  
  /** Updates the program state.
    *
    * Moves the current horizontal position (x-value) on the canvas one pixel to the right,
    * looping back around to the left side if it would otherwise move off the canvas.
    *
    * @param canvasDim Canvas dimensions.
    */
  def update(canvasDim: CanvasDim): Unit = {
    import canvasDim.width
    x = (x + 1) % width
  }
  
  /** Returns the parameters derived from the current program state that are required to update the canvas contents.
    *
    * @param canvasDim Canvas dimensions.
    */
  def drawParams(canvasDim: CanvasDim): DrawParams = {
    
    val ys: Seq[Int] = getYs(x, canvasDim)
    val clearFlag: Boolean = getClearFlag(x)
    
    DrawParams(x, ys, clearFlag)
  }
}
