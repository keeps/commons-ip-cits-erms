package org.keeps.digitalpreservation.commonsip.citserms.validator.components;

import java.io.IOException;
import java.util.Map;

import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;

/** {@author João Gomes <jgomes@keep.pt>}. */
public interface ErmsValidator {

  /**
   * Adds the observer to the {@link java.util.List} of observers.
   *
   * @param observer
   *          {@link ValidationObserver}
   */
  void addObserver(ValidationObserver observer);

  /**
   * Removes the observer of the {@link java.util.List} of observers.
   *
   * @param observer
   *          {@link ValidationObserver}
   */
  void removeObserver(ValidationObserver observer);

  /**
   * Validates the IP.
   *
   * @param structureValidatorState
   *          {@link StructureValidatorState}
   * @param ermsValidatorState
   *          {@link ErmsValidatorState}
   * @return {@link Map} with the results.
   * @throws IOException
   *        if some error occurs.
   */
  Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState,
                                        ErmsValidatorState ermsValidatorState) throws IOException;
}
