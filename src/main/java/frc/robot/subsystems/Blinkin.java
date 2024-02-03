
package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Blinkin extends SubsystemBase {


   
  private static Spark m_blinkin;

  /**
   * Creates a new Blinkin LED controller.
   * 
   */
  public Blinkin() {
    m_blinkin = new Spark(Constants.blinkinPort);
  }

  /*
   * Set the color and blink pattern of the LED strip.
   * 
   * Consult the Rev Robotics Blinkin manual Table 5 for a mapping of values to patterns.
   * 
   * @param val The LED blink color and patern value [-1,1]
   * 
   */ 

  public void SetRed() {
    m_blinkin.set(0.61);
  }

  public void SetGreen() {
    m_blinkin.set(0.71);
  }
  public void setYellow() {
    m_blinkin.set(0.69);
  }

  
  

}