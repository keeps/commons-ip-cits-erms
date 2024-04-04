package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsRelation;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsRelationValidatorFactory {
  public ErmsRelationValidatorFactory() {
    // empty constructor
  }

  public ErmsRelationValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new ErmsRelationValidator204();
    }
    return new ErmsRelationValidator210();
  }
}
