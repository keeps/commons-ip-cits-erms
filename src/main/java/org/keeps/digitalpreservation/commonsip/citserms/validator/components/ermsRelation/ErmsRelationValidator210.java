package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsRelation;

import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsRelationValidator210 extends ErmsRelationValidator {
  @Override
  protected String getERMSVersion() {
    return Constants.VALIDATION_REPORT_HEADER_ERMS_VERSION;
  }
}
