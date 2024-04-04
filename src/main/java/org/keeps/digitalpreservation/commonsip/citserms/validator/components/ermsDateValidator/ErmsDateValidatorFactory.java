package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsDateValidator;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsDateValidatorFactory {
  public ErmsDateValidatorFactory() {
    // empty constructor
  }

  public ErmsDateValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new ErmsDateValidator204();
    }
    return new ErmsDateValidator210();
  }
}
