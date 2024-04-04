package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsNoteValidator;

import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsNoteValidator210 extends ErmsNoteValidator {
  @Override
  protected String getERMSVersion() {
    return Constants.VALIDATION_REPORT_HEADER_ERMS_VERSION;
  }
}
