package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ControlType;
import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ErmsValidatorImpl;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsControlComponentValidator extends ErmsValidatorImpl {
  /**
   * {@link String} moduleName.
   */
  private final String moduleName;
  /**
   * {@link List} of {@link String} with OAIS package types.
   */
  //private final List<String> oaisPackageTypes;
  /**
   * {@link ControlType}.
   */
  private ControlType controlType;



  public ErmsControlComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.ERMS_MODULE_NAME_2;
    //this.oaisPackageTypes = ControlledVocabularyParser
    //  .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE);
  }


  @Override
  public void addObserver(ValidationObserver observer) {

  }

  @Override
  public void removeObserver(ValidationObserver observer) {

  }

  @Override
  public Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState, ErmsValidatorState ermsValidatorState) throws IOException {

    controlType = ermsValidatorState.getErms().getControl();

    ErmsControlValidatorFactory ermsControlValidatorFactory = new ErmsControlValidatorFactory();
    ErmsControlValidator generator = ermsControlValidatorFactory.getGenerator("2.1.0");

    final Map<String, ReporterDetails> results = new HashMap<>();

    /* ERMS1 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS1_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS1_ID,
      generator.validateERMS1(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));


    return null;
  }
}
