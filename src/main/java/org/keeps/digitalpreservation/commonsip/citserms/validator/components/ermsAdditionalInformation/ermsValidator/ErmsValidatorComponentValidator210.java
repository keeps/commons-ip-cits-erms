/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsAdditionalInformation.ermsValidator;

import java.io.IOException;
import java.util.Map;

import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ErmsValidatorImpl;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsValidatorComponentValidator210 extends ErmsValidatorImpl {
  @Override
  public void addObserver(ValidationObserver observer) {

  }

  @Override
  public void removeObserver(ValidationObserver observer) {

  }

  @Override
  public Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState, ErmsValidatorState ermsValidatorState) throws IOException {
    return null;
  }
}
