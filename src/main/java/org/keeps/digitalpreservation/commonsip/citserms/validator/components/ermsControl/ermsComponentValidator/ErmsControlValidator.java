package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator;

import java.util.List;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ClassificationSchema;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ClassificationSchema.TextualDescriptionOfClassificationSchema;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ControlType;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.Identification;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.MaintenanceType.MaintenanceAgency;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.MaintenanceType.MaintenanceStatus;
import org.keeps.digitalpreservation.commonsip.citserms.utils.Message;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class ErmsControlValidator {

  /*
   * emrs/control/identification Identification of the ERMS document itself.
   */
  protected ReporterDetails validateERMS1(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    List<Identification> identification = controlType.getIdentification();
    if (identification.isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/identification can't be null, in %1$s the erms/control/identification does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * emrs/control/identification/@identificationType A description of the
   * identifier.
   */
  protected ReporterDetails validateERMS2(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    List<Identification> identification = controlType.getIdentification();

    for (Identification id : identification) {
      if (id.getIdentificationType() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "erms/control/identification/@identificationType can't be null, in %1$s the erms/control/identification/@identification does not exist",
          ermsValidatorState.getErmsName()));
        return details;
      }
    }
    return details;
  }

  /*
   * erms/control/informationClass Information class for the whole document based
   * on information security classification.
   */
  protected ReporterDetails validateERMS3(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    String informationClass = controlType.getInformationClass();

    if (informationClass == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/informationClass can't be null, in %1$s the erms/control/informationClass does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/classificationSchema/ A grouping element for a description of
   * the classification schema used for the records management system.
   */
  protected ReporterDetails validateERMS4(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    ClassificationSchema classificationSchema = controlType.getClassificationSchema();

    if (classificationSchema == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/classificationSchema can't be null, in %1$s the erms/control/classificationSchema does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/classificationSchema/textualDescriptionOfClassificationSchema/ A
   * textual description of the classification schema used
   */
  protected ReporterDetails validateERMS5(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    ClassificationSchema classificationSchema = controlType.getClassificationSchema();
    TextualDescriptionOfClassificationSchema tdcs = classificationSchema.getTextualDescriptionOfClassificationSchema();

    if (tdcs == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/classificationSchema/textualDescriptionOfClassificationSchema/ can't be null, in %1$s the erms/control/classificationSchema/textualDescriptionOfClassificationSchema/ does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/classificationSchema/textualDescriptionOfClassificationSchema/p
   * The textual description is carried out in one or more paragraph elements
   * (abbreviated p-elements).
   */

  protected ReporterDetails validateERMS6(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    ClassificationSchema classificationSchema = controlType.getClassificationSchema();
    TextualDescriptionOfClassificationSchema tdcs = classificationSchema.getTextualDescriptionOfClassificationSchema();
    if (tdcs.getP().isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/classificationSchema/textualDescriptionOfClassificationSchema/p can't be null, in %1$s the erms/control/classificationSchema/textualDescriptionOfClassificationSchema/p does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/classificationSchema/additionalInformation Link to a document or
   * webpage describing the classification and to add the infromation in the
   * document
   */
  protected ReporterDetails validateERMS7(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    ClassificationSchema classificationSchema = controlType.getClassificationSchema();
    if (classificationSchema.getAdditionalInformation() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/classificationSchema/additionalInformation can't be null, in %1$s the erms/control/classificationSchema/additionalInformation does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/securityClass Security class for the whole document
   */
  protected ReporterDetails validateERMS8(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    if (controlType.getSecurityClass() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/securityClass can't be null, in %1$s the erms/control/securityClass does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/dates Grouping elemnt for dates to the whole document
   */
  protected ReporterDetails validateERMS9(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    if (controlType.getDates() == null) {
      details.setValid(false);
      details.addIssue(
        Message.createErrorMessage("erms/control/dates can't be null, in %1$s the erms/control/dates does not exist",
          ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation Grouping elemnt for the maintenance
   * information
   */
  protected ReporterDetails validateERMS10(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    if (controlType.getMaintenanceInformation() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation can't be null, in %1$s the erms/control/maintenanceInformation does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceStatus/@value The maintenance
   * status of the document
   */
  protected ReporterDetails validateERMS11(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceStatus mstatus = controlType.getMaintenanceInformation().getMaintenanceStatus();
    if (mstatus == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceStatus can't be null, in %1$s the erms/control/maintenanceInformation/maintenanceStatus does not exist",
        ermsValidatorState.getErmsName()));
    } else if (mstatus.getValue().isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceStatus/@value can't be null, in %1$s the erms/control/maintenanceInformation/maintenanceStatus/@value does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency Grouping element for
   * describing the agency
   */
  protected ReporterDetails validateERMS12(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency==null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceAgency can't be null, in %1$s the erms/control/maintenanceInformation/maintenanceAgency does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency/agencyCode The identifying code for the agency
   */
  protected ReporterDetails validateERMS13(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency.getAgencyCode()==null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceAgency/agencyCode can't be null, in %1$s the erms/control/maintenanceInformation/maintenanceAgency/agencyCode does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency/@type The identifying code for the agency
   */
  protected ReporterDetails validateERMS14(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency.get==null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceAgency/agencyCode can't be null, in %1$s the erms/control/maintenanceInformation/maintenanceAgency/agencyCode does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }
  protected abstract String getERMSVersion();
}
