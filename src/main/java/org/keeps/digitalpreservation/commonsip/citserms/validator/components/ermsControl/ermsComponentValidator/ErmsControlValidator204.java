package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator;
import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;
/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsControlValidator204 extends ErmsControlValidator {
  @Override
  protected String getERMSVersion() {
    return Constants.VALIDATION_REPORT_HEADER_ERMS_VERSION;
  }
}
