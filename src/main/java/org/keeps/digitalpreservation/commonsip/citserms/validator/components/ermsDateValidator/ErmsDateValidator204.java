package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsDateValidator;
import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;
/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsDateValidator204 extends ErmsDateValidator {
  @Override
  protected String getERMSVersion() {
    return Constants.VALIDATION_REPORT_HEADER_ERMS_VERSION;
  }
}
