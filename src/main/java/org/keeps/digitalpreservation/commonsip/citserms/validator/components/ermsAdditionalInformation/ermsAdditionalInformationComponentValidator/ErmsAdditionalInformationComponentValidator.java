package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsAdditionalInformation.ermsAdditionalInformationComponentValidator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.AdditionalInformation;
import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ErmsValidatorImpl;
import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsAdditionalInformationComponentValidator extends ErmsValidatorImpl {

  private AdditionalInformation additionalInformation;

  /**
   * {@link String} moduleName.
   */
  private final String moduleName;

  public ErmsAdditionalInformationComponentValidator() throws ParserConfigurationException, SAXException {
    this.moduleName = Constants.ERMS_MODULE_NAME_3;
    // this.oaisPackageTypes = ControlledVocabularyParser
    // .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE);
  }

  @Override
  public Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState,
    ErmsValidatorState ermsValidatorState) throws IOException {

    additionalInformation = ermsValidatorState.getErms().getAdditionalInformation();

    ErmsAdditionalInformationValidatorFactory ermsInformationValidatorFactory = new ErmsAdditionalInformationValidatorFactory();
    ErmsAdditionalInformationValidator generator = ermsInformationValidatorFactory.getGenerator("2.1.0");

    final Map<String, ReporterDetails> results = new HashMap<>();

    /* ERMS28 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS28_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS28_ID,
      generator.validateERMS28(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

    if (ResultsUtils.isResultValid(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS28_ID)) {

      /* ERMS29 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS29_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS29_ID, generator
        .validateERMS29(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS30 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS30_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS30_ID, generator
        .validateERMS30(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS31 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS31_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS31_ID, generator
        .validateERMS31(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS32 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS32_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS32_ID, generator
        .validateERMS32(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS33 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS33_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS33_ID, generator
        .validateERMS33(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS34 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS34_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS34_ID, generator
        .validateERMS34(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS35 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS35_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS35_ID, generator
        .validateERMS35(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS36 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS36_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS36_ID, generator
        .validateERMS36(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS37 */

//      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS10_ID);
//      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS10_ID, generator
//        .validateERMS37(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

      /* ERMS38 */

//      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS38_ID);
//      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS38_ID, generator
//        .validateERMS38(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));



      /* ERMS39 */

/*      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS39_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS39_ID, generator
        .validateERMS39(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));


 */
    }

    /* ERMS40 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS40_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS40_ID,
      generator.validateERMS40(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));


    /* ERMS41 */
/*
    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS41_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS41_ID,
      generator.validateERMS41(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

 */
    /* ERMS42 */
/*
    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS42_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS42_ID,
      generator.validateERMS42(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

 */
    /* ERMS43 */
/*
    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS43_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS43_ID,
      generator.validateERMS43(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

 */
    /* ERMS44 */
/*
    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS44_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS44_ID,
      generator.validateERMS44(ermsValidatorState, additionalInformation).setSpecification(generator.getERMSVersion()));

 */
    return null;
  }
}
