package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsNoteValidator;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsNoteValidatorFactory {
  public ErmsNoteValidatorFactory() {
    // empty constructor
  }

  public ErmsNoteValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new ErmsNoteValidator204();
    }
    return new ErmsNoteValidator210();
  }
}
