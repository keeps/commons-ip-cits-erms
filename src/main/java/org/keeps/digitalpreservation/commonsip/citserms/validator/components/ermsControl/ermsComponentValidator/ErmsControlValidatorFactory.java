/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator;

import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator.MetsHeaderValidator;
import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator.MetsHeaderValidator204;
import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator.MetsHeaderValidator210;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsControlValidatorFactory {
  public ErmsControlValidatorFactory() {
    // empty constructor
  }

  public ErmsControlValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new ErmsControlValidator204();
    }
    return new ErmsControlValidator210();
  }
}
