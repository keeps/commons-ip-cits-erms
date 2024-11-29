/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.keeps.digitalpreservation.commonsip.citserms.validator.common.InstatiateErms;
import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ErmsValidator;
import org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator.ErmsControlComponentValidator;
import org.keeps.digitalpreservation.commonsip.citserms.validator.constants.Constants;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.components.StructureValidatorImpl;
import org.roda_project.commons_ip2.validator.components.fileComponent.StructureComponentValidator210;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public class EARKERMSValidator {
  /**
   * IP path.
   */
  private final Path earkermsPath;

  /**
   * {@link ValidationReportOutputJson}.
   */
  private final ValidationReportOutputJson validationReportOutputJson;
  /**
   * {@link StructureValidatorImpl}.
   */
  private final StructureValidatorImpl structureComponent;
  /**
   * the contextual structural state {@link StructureValidatorState}.
   */
  private final StructureValidatorState structureValidatorState;
  /**
   * List of ERMS components to validate.
   */
  private final List<ErmsValidator> ermsComponents = new ArrayList<>();
  /**
   * The contextual mets state {@link ErmsValidatorState}.
   */
  private final ErmsValidatorState ermsValidatorState;

  private final String version;

  /**
   * Initializes Validation Objects.
   *
   * @param reportOutputJson
   *          the {@link ValidationReportOutputJson}
   * @throws IOException
   *           if some I/O error occurs.
   * @throws ParserConfigurationException
   *           if some error occurred.
   * @throws SAXException
   *           if some error occurred.
   */
  public EARKERMSValidator(final ValidationReportOutputJson reportOutputJson, String version)
    throws IOException, ParserConfigurationException, SAXException {

    this.earkermsPath = reportOutputJson.getSipPath().toAbsolutePath().normalize();

    this.validationReportOutputJson = reportOutputJson;

    this.version = version;

    this.structureValidatorState = new StructureValidatorState(
      reportOutputJson.getSipPath().toAbsolutePath().normalize());

    this.structureComponent = new StructureComponentValidator210();

    // if (version.equals("2.1.0")) {
    // this.structureComponent = new StructureComponentValidator210();
    // } else {
    // this.structureComponent = new StructureComponentValidator204();
    // }
    this.ermsValidatorState = new ErmsValidatorState();
    setupComponents(version);
  }

  /**
   * Setup Validation Components.
   *
   * @throws IOException
   *           if some I/O error occurs.
   * @throws ParserConfigurationException
   *           if some error occur.
   * @throws SAXException
   *           if some error occur.
   */
  private void setupComponents(String version) throws IOException, ParserConfigurationException, SAXException {
    this.ermsComponents.addAll(getComponentsForVersion(version));
  }

  /**
   * Add {@link ValidationObserver} to the lists of observers.
   *
   * @param observer
   *          the {@link ValidationObserver}
   */
  public void addObserver(final ValidationObserver observer) {
    structureComponent.addObserver(observer);
    ermsComponents.forEach(c -> c.addObserver(observer));
  }

  /**
   * Remove {@link ValidationObserver} from the lists of observers.
   *
   * @param observer
   *          the {@link ValidationObserver}
   */
  public void removeObserver(final ValidationObserver observer) {
    structureComponent.removeObserver(observer);
    ermsComponents.forEach(c -> c.removeObserver(observer));
  }

  /**
   * Validates the Information Package.
   *
   * @return if the Information package is valid or not
   * @throws IOException
   *           if some I/O error occurs.
   */
  public boolean validate(String version) throws IOException {
    structureComponent.notifyObserversIPValidationStarted();
    final Map<String, ReporterDetails> structureValidationResults = structureComponent
      .validate(structureValidatorState);
    validationReportOutputJson.getResults().putAll(structureValidationResults);
    validateErms();

    /*
     * if (validationReportOutputJson.validFileComponent()) { final Map<String,
     * InputStream> subMets; if (structureValidatorState.isZipFileFlag()) {
     * ermsValidatorState.setErmsFiles(structureValidatorState.getZipManager().
     * getFiles(earkermsPath)); subMets =
     * structureValidatorState.getZipManager().getSubMets(earkermsPath); } else {
     * metsValidatorState.setMetsFiles(structureValidatorState.getFolderManager().
     * getFiles(earkermsPath)); subMets =
     * structureValidatorState.getFolderManager().getSubMets(earkermsPath); }
     * 
     * if (subMets.size() > 0) { validateSubMets(subMets,
     * structureValidatorState.isZipFileFlag()); } validateRootMets();
     * 
     * if (!validationReportOutputJson.getResults()
     * .containsKey(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID)) {
     * final ReporterDetails csipStr0 = new
     * ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION + version,
     * "", true, false);
     * csipStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION +
     * version); validationReportOutputJson.getResults().put(ConstantsCSIPspec.
     * VALIDATION_REPORT_SPECIFICATION_CSIP0_ID, csipStr0); } }
     * writeReport(version); return validationReportOutputJson.getErrors() == 0;
     */
    return true;
  }

  /**
   * Iterates over all components and merge all results from components
   * validations.
   *
   * @throws IOException
   *           if some I/O error occurs.
   */
  private void validateComponents() throws IOException {
    for (ErmsValidator component : ermsComponents) {
      final Map<String, ReporterDetails> componentResults = component.validate(structureValidatorState,
        ermsValidatorState);
      ResultsUtils.mergeResults(validationReportOutputJson.getResults(), componentResults);
    }
    ermsValidatorState.flushEntries();
    validateIpTypeExtendedComponents();
  }

  /**
   * Validate METS files inside representations.
   *
   * @param subMets
   *          the {@link Map } with path to sub METS and InputStream of file.
   * @param isZip
   *          flag if the Information Package is in compact format or if it is a
   *          folder.
   */
  /*
   * private void validateSubMets(final Map<String, InputStream> subMets, final
   * boolean isZip) { for (Map.Entry<String, InputStream> entry :
   * subMets.entrySet()) {
   * 
   * final InstatiateErms instatiateMets = new InstatiateErms(entry.getValue());
   * try { ermsValidatorState.setErms(instatiateMets.instatiateMetsFile());
   * setupMetsValidatorState(entry.getKey(), isZip, false); validateComponents();
   * } catch (IOException | JAXBException | SAXException e) { final String message
   * = createExceptionMessage(e, entry.getKey()); final ReporterDetails csipStr0 =
   * new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message,
   * false, false);
   * csipStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
   * ResultsUtils.addResult(validationReportOutputJson.getResults(),
   * ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID, csipStr0); } } }
   * 
   */

  /**
   * Creates Message for Exception.
   *
   * @param e
   *          the {@link Exception}
   * @param mets
   *          the path to METS file
   * @return a message
   */
  /*
   * private String createExceptionMessage(final Exception e, final String mets) {
   * final StringBuilder message = new StringBuilder();
   * 
   * Throwable cause = e; if (e.getMessage() != null) {
   * message.append(Constants.OPEN_SQUARE_BRACKET).append(e.getClass().
   * getSimpleName())
   * .append(Constants.CLOSE_SQUARE_BRACKET).append(Constants.EMPTY_SPACE).append(
   * e.getMessage()); } while (cause.getCause() != null) { cause =
   * cause.getCause(); if (message.length() > 0) { message.append(" caused by ");
   * }
   * 
   * message.append(Constants.OPEN_SQUARE_BRACKET).append(cause.getClass().
   * getSimpleName())
   * .append(Constants.CLOSE_SQUARE_BRACKET).append(Constants.EMPTY_SPACE).append(
   * cause.getMessage());
   * 
   * if (cause instanceof SAXParseException e1) {
   * message.append(" (file: ").append(mets).append(", line: ").append(e1.
   * getLineNumber()).append(", column: ")
   * .append(e1.getColumnNumber()).append(")"); } }
   * 
   * return message.toString(); }
   * 
   */

  /**
   * Validates METS file in root of Information Package.
   */
  private void validateErms() {
    final InputStream ermsStream;
    final String ipPath;
    try {

      if (structureValidatorState.isZipFileFlag()) {
        ermsStream = structureValidatorState.getZipManager().getErmsInputStream(earkermsPath);
        ipPath = earkermsPath.toString();
      } else {
        ermsStream = structureValidatorState.getFolderManager().getErmsInputStream(earkermsPath);
        ipPath = earkermsPath.resolve(Constants.ERMS_FILE).toString();
      }

      final InstatiateErms erms = new InstatiateErms();
      ermsValidatorState.setErmsPath(earkermsPath.toString());
      ermsValidatorState.setErmsName(ipPath);

      ermsValidatorState.setErms(erms.instatiateErmsFile(ermsStream));
      // erms.setIpType(metsValidatorState.getMets().getMetsHdr().getOAISPACKAGETYPE());
      validateComponents();
    } catch (IOException | JAXBException | SAXException e) {
      // final String message = createExceptionMessage(e,
      // earkermsPath.toString() + Constants.SEPARATOR + Constants.ERMS_FILE);
      // final ReporterDetails ermsStr0 = new
      // ReporterDetails(Constants.VALIDATION_REPORT_HEADER_ERMS_VERSION, message,
      // false, false);
      // ermsStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_ERMS_VERSION);
      // ResultsUtils.addResult(validationReportOutputJson.getResults(),
      // ConstantsERMSspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID, ermsStr0);
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Setup State of METS.
   *
   * @param key
   *          the METS file path
   * @param isZip
   *          Flag if Information package is compacted or not
   * @param isRootMets
   *          Flag if METS file is root or representation METS
   */
  /*
   * private void setupMetsValidatorState(final String key, final boolean isZip,
   * final boolean isRootMets) { this.metsValidatorState.setMetsName(key);
   * this.metsValidatorState.setIsRootMets(isRootMets); if (isZip) { final
   * StringBuilder metsPath = new StringBuilder(); for (String path :
   * key.split(Constants.SEPARATOR)) { if (!path.equals(Constants.METS_FILE)) {
   * metsPath.append(path).append("/"); } }
   * this.metsValidatorState.setMetsPath(metsPath.toString()); } else {
   * this.metsValidatorState.setMetsPath(Paths.get(key).getParent().toString()); }
   * }
   * 
   */

  /**
   * Notify all observers.
   */
  public void notifyIndicatorsObservers() {
    structureComponent.notifyIndicators(this.validationReportOutputJson.getErrors(),
      this.validationReportOutputJson.getSuccess(), this.validationReportOutputJson.getWarnings(),
      this.validationReportOutputJson.getNotes(), this.validationReportOutputJson.getSkipped());
  }

  /**
   * Validate SIP specifications or AIP Specifications if the type is SIP or AIP.
   *
   * @throws IOException
   *           if some I/O error occurs.
   */
  private void validateIpTypeExtendedComponents() throws IOException {
    if (ermsValidatorState.getIpType() != null && ermsValidatorState.getIpType().equals("ERMS")) {
      validateERMSComponents();
    }
  }

  /**
   * Iterate over ERMS components and merge the results with CSIP validations.
   *
   * @throws IOException
   *           if some I/O error occurs.
   */
  private void validateERMSComponents() throws IOException {

  }

  /**
   * Iterate over AIP components and merges the results with CSIP validations
   * results.
   *
   * @throws IOException
   *           if some I/O error occurs.
   */

  /**
   * Write the report.
   *
   * @throws IOException
   *           if some I/O error occurs.
   */
  private void writeReport(String version) throws IOException {
    if (ermsValidatorState.getErms() != null) {
      validationReportOutputJson.setIpType(ermsValidatorState.getIpType());
    }

    validationReportOutputJson.init(version);
    validationReportOutputJson.validationResults();
    validationReportOutputJson.writeFinalResult();
    notifyIndicatorsObservers();
    validationReportOutputJson.close();
    structureComponent.notifyObserversIPValidationFinished();
  }

  private List<ErmsValidator> getComponentsForVersion(String version)
    throws IOException, ParserConfigurationException, SAXException {
    List<ErmsValidator> values = new ArrayList<>();
    // if (version.equals("2.0.4")) {
    // values.add(new ErmsValidatorComponentValidator20);
    // } else {
    values.add(new ErmsControlComponentValidator());
    // }

    return values;
  }

}
