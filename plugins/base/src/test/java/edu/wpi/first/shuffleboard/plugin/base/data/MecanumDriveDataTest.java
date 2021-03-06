package edu.wpi.first.shuffleboard.plugin.base.data;

import edu.wpi.first.shuffleboard.api.util.Vector2D;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MecanumDriveDataTest {

  @ParameterizedTest
  @MethodSource("createMomentArguments")
  public void testCalculateMoment(MecanumDriveData data, double expectedMoment) {
    double calculatedMoment = data.getMoment();
    assertEquals(expectedMoment, calculatedMoment);
  }

  @ParameterizedTest
  @MethodSource("createVectorArguments")
  public void testCalculateDirectionVector(MecanumDriveData data, Vector2D expectedVector) {
    Vector2D calculatedVector = data.getDirection();
    assertEquals(expectedVector, calculatedVector);
  }

  private static Stream<Arguments> createMomentArguments() {
    return Stream.of(
        Arguments.of(new MecanumDriveData(0, 0, 0, 0), 0.0),     // no motion
        Arguments.of(new MecanumDriveData(1, 1, 1, 1), 0.0),     // moments cancel, no rotation
        Arguments.of(new MecanumDriveData(-1, -1, -1, -1), 0.0), // moments cancel, no rotation
        Arguments.of(new MecanumDriveData(1, -1, 1, -1), -1.0),  // rotate clockwise
        Arguments.of(new MecanumDriveData(-1, 1, -1, 1), 1.0),   // rotate counter-clockwise
        Arguments.of(new MecanumDriveData(-1, -1, 1, 1), 0.0)    // no motion
    );
  }

  private static Stream<Arguments> createVectorArguments() {
    return Stream.of(
        Arguments.of(new MecanumDriveData(0, 0, 0, 0), new Vector2D(0, 0)),       // no motion
        Arguments.of(new MecanumDriveData(1, 1, 1, 1), new Vector2D(0, 1)),       // full speed forward
        Arguments.of(new MecanumDriveData(-1, -1, -1, -1), new Vector2D(0, -1)),  // full speed back
        Arguments.of(new MecanumDriveData(1, -1, -1, 1), new Vector2D(1, 0)),     // full speed right
        Arguments.of(new MecanumDriveData(-1, 1, 1, -1), new Vector2D(-1, 0)),    // full speed left
        Arguments.of(new MecanumDriveData(-1, -1, 1, 1), new Vector2D(0, 0)),     // no motion
        Arguments.of(new MecanumDriveData(1, 0, 0, 1), new Vector2D(.5, .5)),     // diagonal +x +y
        Arguments.of(new MecanumDriveData(0, 1, 1, 0), new Vector2D(-.5, .5)),    // diagonal -x +y
        Arguments.of(new MecanumDriveData(-1, 0, 0, -1), new Vector2D(-.5, -.5)), // diagonal -x -y
        Arguments.of(new MecanumDriveData(0, -1, -1, 0), new Vector2D(.5, -.5))   // diagonal +x -y
    );
  }

}
