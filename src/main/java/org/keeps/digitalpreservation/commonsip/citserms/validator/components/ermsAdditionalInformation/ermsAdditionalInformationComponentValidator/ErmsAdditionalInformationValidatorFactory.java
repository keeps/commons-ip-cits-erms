package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsAdditionalInformation.ermsAdditionalInformationComponentValidator;

import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator.ErmsControlValidator;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsAdditionalInformationValidatorFactory {
  public ErmsAdditionalInformationValidatorFactory() {
    // empty constructor
  }

  public ErmsAdditionalInformationValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new ErmsAdditionalInformationValidator204();
    }
    return new ErmsAdditionalInformationValidator210();
  }
}
