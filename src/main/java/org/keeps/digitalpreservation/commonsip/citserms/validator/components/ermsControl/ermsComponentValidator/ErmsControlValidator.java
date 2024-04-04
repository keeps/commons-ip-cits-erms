package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsControl.ermsComponentValidator;

import java.util.List;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ClassificationSchema;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ClassificationSchema.TextualDescriptionOfClassificationSchema;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ControlType;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.Identification;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.MaintenanceType;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.MaintenanceType.MaintenanceAgency;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.MaintenanceType.MaintenanceHistory.MaintenanceEvent;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.MaintenanceType.MaintenanceStatus;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.SystemInfoType;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.SystemInfoType.Agents;
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
        "ERMS file should contain a erms/control/informationClass, in %1$s the erms/control/informationClass does not exist",
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
        "ERMS file should contain a erms/control/classificationSchema, in %1$s the erms/control/classificationSchema does not exist",
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
        "ERMS file may contain a erms/control/classificationSchema/textualDescriptionOfClassificationSchema/, in %1$s the erms/control/classificationSchema/textualDescriptionOfClassificationSchema/ does not exist",
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
        "ERMS file may contain a erms/control/classificationSchema/additionalInformation, in %1$s the erms/control/classificationSchema/additionalInformation does not exist",
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
        "ERMS file should contain a erms/control/securityClass, in %1$s the erms/control/securityClass does not exist",
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
        Message.createErrorMessage("ERMS file may contain a erms/control/dates, in %1$s the erms/control/dates does not exist",
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
    if (agency == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceAgency can't be null, in %1$s the erms/control/maintenanceInformation/maintenanceAgency does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency/agencyCode The
   * identifying code for the agency
   */
  protected ReporterDetails validateERMS13(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency.getAgencyCode() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "ERMS file should contain aerms/control/maintenanceInformation/maintenanceAgency/agencyCode, in %1$s the erms/control/maintenanceInformation/maintenanceAgency/agencyCode does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency/@type The type of
   * identification code
   */

  /*
   * REVER ISTO
   */
  protected ReporterDetails validateERMS14(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency.getAgencyCode().getType() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceAgency/@type can't be null, in %1$s the erms/control/maintenanceInformation/maintenanceAgency/@type does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency/otherAgencyCode
   * Identification code
   */
  protected ReporterDetails validateERMS15(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency.getOtherAgencyCode().isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "ERMS file may contain a erms/control/maintenanceInformation/maintenanceAgency/otherAgencyCode, in %1$s the erms/control/maintenanceInformation/maintenanceAgency/otherAgencyCode does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency/AgencyCode/@type Type
   * of the other identification code
   */
  protected ReporterDetails validateERMS16(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency.getAgencyCode().getType() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceAgency/agencyCode/@type can't be null, in %1$s the erms/control/maintenanceInformation/maintenanceAgency/agencyCode/@type does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency/agencyName The name of
   * the agency
   */
  protected ReporterDetails validateERMS17(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency.getAgencyName().isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceAgency/agencyName can't be empty, in %1$s the erms/control/maintenanceInformation/maintenanceAgency/agencyName does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceAgency/note A note describing
   * the agency
   */
  protected ReporterDetails validateERMS18(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceAgency agency = controlType.getMaintenanceInformation().getMaintenanceAgency();
    if (agency.getNote() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceAgency/note can't be empty, in %1$s the erms/control/maintenanceInformation/maintenanceAgency/note does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceHistory Maintenance history of
   * the document
   */
  protected ReporterDetails validateERMS19(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceType maintenanceInformation = controlType.getMaintenanceInformation();
    if (maintenanceInformation.getMaintenanceHistory() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceHistory can't be empty, in %1$s the erms/control/maintenanceInformation/maintenanceHistory does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceHistory/maintenanceEvent
   * Maintenance events pertaining to the document
   */
  protected ReporterDetails validateERMS20(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    MaintenanceType maintenanceInformation = controlType.getMaintenanceInformation();
    if (maintenanceInformation.getMaintenanceHistory().getMaintenanceEvent().isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/maintenanceInformation/maintenanceHistory/maintenanceEvent can't be empty, in %1$s the erms/control/maintenanceInformation/maintenanceHistory/maitenanceEvent does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceHistory/eventtype/@value
   * Maintenance events pertaining to the document
   */
  protected ReporterDetails validateERMS21(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    List<MaintenanceEvent> maintenanceEvents = controlType.getMaintenanceInformation().getMaintenanceHistory()
      .getMaintenanceEvent();
    for (MaintenanceEvent me : maintenanceEvents) {
      if (me.getEventType().getValue() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "erms/control/maintenanceInformation/maintenanceHistory/eventtype/@value can't be empty, in %1$s the erms/control/maintenanceInformation/maintenanceHistory/eventtype/@value does not exist",
          ermsValidatorState.getErmsName()));
        break;
      }
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceHistory/eventDateTime The date
   * and time the event occured
   */
  protected ReporterDetails validateERMS22(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    List<MaintenanceEvent> maintenanceEvents = controlType.getMaintenanceInformation().getMaintenanceHistory()
      .getMaintenanceEvent();
    for (MaintenanceEvent me : maintenanceEvents) {
      if (me.getEventDateTime() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "erms/control/maintenanceInformation/maintenanceHistory/eventDateTime can't be empty, in %1$s the erms/control/maintenanceInformation/maintenanceHistory/eventDateTime does not exist",
          ermsValidatorState.getErmsName()));
        break;
      }
    }
    return details;
  }

  /*
   * erms/control/maintenanceInformation/maintenanceHistory/agent The agent
   * responsible for the event
   */

  protected ReporterDetails validateERMS23(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    List<MaintenanceEvent> maintenanceEvents = controlType.getMaintenanceInformation().getMaintenanceHistory()
      .getMaintenanceEvent();
    for (MaintenanceEvent me : maintenanceEvents) {
      if (me.getAgent() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "erms/control/maintenanceInformation/maintenanceHistory/agent can't be empty, in %1$s the erms/control/maintenanceInformation/maintenanceHistory/agent does not exist",
          ermsValidatorState.getErmsName()));
        break;
      }
    }
    return details;
  }

  /*
   * erms/control/systemInformation Grouping elemnt where the exporting system can
   * add extra information
   */

  protected ReporterDetails validateERMS24(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    SystemInfoType systemInfoType = controlType.getSystemInformation();

    if (systemInfoType == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/systemInformation can't be empty, in %1$s the erms/control/systemInformation does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/systemInformation/extraMetadataInformation Exporting system can
   * include system information in its own XML format.
   */

  protected ReporterDetails validateERMS25(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    SystemInfoType systemInfoType = controlType.getSystemInformation();

    if (systemInfoType.getExtraMetadataInformation() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/systemInformation/extraMetadataInformation can't be empty, in %1$s the erms/control/systemInformation/extraMetadataInformation does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/systemInformation/extraMetadataInformation Information about
   * system agents
   */

  protected ReporterDetails validateERMS26(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    SystemInfoType systemInfoType = controlType.getSystemInformation();

    if (systemInfoType.getAgents() == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "erms/control/systemInformation/agents can't be empty, in %1$s the erms/control/systemInformation/agents does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * erms/control/systemInformation/extraMetadataInformation Information about
   * system agents
   */

  // AINDA NAO DA
  protected ReporterDetails validateERMS27(final ErmsValidatorState ermsValidatorState, ControlType controlType) {
    final ReporterDetails details = new ReporterDetails();
    Agents agents = controlType.getSystemInformation().getAgents();
    agents.getAgent();
    return details;
  }

  protected abstract String getERMSVersion();
}
