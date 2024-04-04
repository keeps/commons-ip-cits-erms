package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsAdditionalInformation.ermsAdditionalInformationComponentValidator;
import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator.ErmsControlValidator;
import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;
/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsAdditionalInformationValidator210 extends ErmsAdditionalInformationValidator {
  @Override
  protected String getERMSVersion() {
    return Constants.VALIDATION_REPORT_HEADER_ERMS_VERSION;
  }
}
