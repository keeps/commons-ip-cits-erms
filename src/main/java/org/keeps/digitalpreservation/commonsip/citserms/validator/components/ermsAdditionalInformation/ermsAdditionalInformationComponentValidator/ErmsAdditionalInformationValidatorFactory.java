/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
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
