/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ControlType;
import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ErmsValidatorImpl;
import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsDateValidator.ErmsDateValidator;
import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsDateValidator.ErmsDateValidatorFactory;
import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

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
  // private final List<String> oaisPackageTypes;
  /**
   * {@link ControlType}.
   */
  private ControlType controlType;

  public ErmsControlComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.ERMS_MODULE_NAME_2;
    // this.oaisPackageTypes = ControlledVocabularyParser
    // .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE);
  }

  @Override
  public void addObserver(ValidationObserver observer) {

  }

  @Override
  public void removeObserver(ValidationObserver observer) {

  }

  @Override
  public Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState,
    ErmsValidatorState ermsValidatorState) throws IOException {

    controlType = ermsValidatorState.getErms().getControl();

    ErmsControlValidatorFactory ermsControlValidatorFactory = new ErmsControlValidatorFactory();
    ErmsControlValidator generator = ermsControlValidatorFactory.getGenerator("2.1.0");

    final Map<String, ReporterDetails> results = new HashMap<>();

    /* ERMS1 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS1_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS1_ID,
      generator.validateERMS1(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    /* ERMS2 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS2_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS2_ID,
      generator.validateERMS2(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    /* ERMS3 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS3_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS3_ID,
      generator.validateERMS3(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    /* ERMS4 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS4_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS4_ID,
      generator.validateERMS4(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    if (ResultsUtils.isResultValid(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS4_ID)) {

      /* ERMS5 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS5_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS5_ID,
        generator.validateERMS5(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

      /* ERMS6 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS6_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS6_ID,
        generator.validateERMS6(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

      /* ERMS7 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS7_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS7_ID,
        generator.validateERMS7(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    }

    /* ERMS8 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS8_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS8_ID,
      generator.validateERMS8(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    /* ERMS9 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS9_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS9_ID,
      generator.validateERMS9(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    if (ResultsUtils.isResultValid(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS9_ID)) {

      ErmsDateValidatorFactory ermsDateValidatorFactory = new ErmsDateValidatorFactory();
      ErmsDateValidator dateGenerator = ermsDateValidatorFactory.getGenerator("2.1.0");

      /* ERMS45 */
      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS45_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS45_ID, dateGenerator
        .validateERMS45(ermsValidatorState, controlType.getDates()).setSpecification(generator.getERMSVersion()));

      /* ERMS46 */
      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS46_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS46_ID, dateGenerator
        .validateERMS46(ermsValidatorState, controlType.getDates()).setSpecification(generator.getERMSVersion()));

      /* ERMS47 */
      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS47_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS47_ID, dateGenerator
        .validateERMS47(ermsValidatorState, controlType.getDates()).setSpecification(generator.getERMSVersion()));

      /* ERMS48 */
      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS48_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS48_ID, dateGenerator
        .validateERMS48(ermsValidatorState, controlType.getDates()).setSpecification(generator.getERMSVersion()));

    }

    /* ERMS10 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS10_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS10_ID,
      generator.validateERMS10(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    if (ResultsUtils.isResultValid(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS10_ID)) {

      /* ERMS11 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS11_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS11_ID,
        generator.validateERMS11(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

      /* ERMS12 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS12_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS12_ID,
        generator.validateERMS12(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

      /* ERMS13 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS13_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS13_ID,
        generator.validateERMS13(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

      /* ERMS14 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS14_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS14_ID,
        generator.validateERMS14(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

      /* ERMS15 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS15_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS15_ID,
        generator.validateERMS15(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

      /* ERMS16 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS16_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS16_ID,
        generator.validateERMS16(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

      /* ERMS17 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS17_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS17_ID,
        generator.validateERMS17(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
      /* ERMS18 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS18_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS18_ID,
        generator.validateERMS18(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
      /* ERMS19 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS19_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS19_ID,
        generator.validateERMS19(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
      /* ERMS20 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS20_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS20_ID,
        generator.validateERMS20(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
      /* ERMS21 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS21_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS21_ID,
        generator.validateERMS21(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
      /* ERMS22 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS22_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS22_ID,
        generator.validateERMS22(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
      /* ERMS23 */

    }

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS23_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS23_ID,
      generator.validateERMS23(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
    /* ERMS24 */

    notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS24_ID);
    ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS24_ID,
      generator.validateERMS24(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    if (ResultsUtils.isResultValid(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS24_ID)) {

      /* ERMS25 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS25_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS25_ID,
        generator.validateERMS25(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
      /* ERMS26 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS26_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS26_ID,
        generator.validateERMS26(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));
      /* ERMS27 */

      notifyObserversValidationStarted(moduleName, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS27_ID);
      ResultsUtils.addResult(results, Constants.VALIDATION_REPORT_SPECIFICATION_ERMS27_ID,
        generator.validateERMS27(ermsValidatorState, controlType).setSpecification(generator.getERMSVersion()));

    }
    return null;
  }
}
