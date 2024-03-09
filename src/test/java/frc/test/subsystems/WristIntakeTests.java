package frc.test.subsystems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import frc.robot.Constants;
import frc.robot.Constants.wrist;
import frc.robot.subsystems.WristIntake;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WristIntakeTests {

  WristIntake wristIntake = new WristIntake();

  @BeforeEach // this method will run before each test
  void setup() {
  }

  @SuppressWarnings("PMD.SignatureDeclareThrowsException")
  @AfterEach // this method will run after each test
  void shutdown() throws Exception {
  }

  @Test // marks this method as a test
  void constructor_setsEncoder_asExpected() {
    double result = wristIntake.encoder.getPositionConversionFactor();
    System.out.println("Hey look at this: " + result);
    assertEquals((360 / Constants.wrist.gearRatio), result);
  }

  @Test
  void resetEncoder_setsPositionToZero(){
    wristIntake.encoder.setPosition(12);
    wristIntake.resetEncoder();
    System.out.println(wristIntake.encoder.getPosition());
    assertEquals(
      0d, 
      wristIntake.encoder.getPosition());
  }
}
