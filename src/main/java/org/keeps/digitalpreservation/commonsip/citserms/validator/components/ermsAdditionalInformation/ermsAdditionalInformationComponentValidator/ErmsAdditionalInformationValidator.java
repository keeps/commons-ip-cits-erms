package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsAdditionalInformation.ermsAdditionalInformationComponentValidator;

import java.util.List;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.AdditionalInformation;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.AppendixType;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ExtendingComplexType;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.OwnElement;
import org.keeps.digitalpreservation.commonsip.citserms.utils.Message;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class ErmsAdditionalInformationValidator {

  /*
   * additionalInformation/appendix Grouping of additional information in the form
   * of a link to a document
   */
  protected ReporterDetails validateERMS28(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    if (appendix.isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "additionalInformation/appendix can't be empty, in %1$s the additionalInformation/appendix does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * additionalInformation/appendix/@disposable Boolean indicator if the appendix
   * can be disposed of.
   */

  // ADICIONAR NOME DO DISPOSABLE QUE ESTA A FALHAR
  protected ReporterDetails validateERMS29(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    for (AppendixType at : appendix) {
      if (at.isDisposable() == null)
        details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "additionalInformation/appendix/@disposable can't be null, in %1$s the additionalInformation/appendix/@disposable with name %1$s does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * additionalInformation/appendix/@name The name of the appendix
   */

  protected ReporterDetails validateERMS30(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    for (AppendixType at : appendix) {
      if (at.getName() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "additionalInformation/appendix/@name can't be null, in %1$s the additionalInformation/appendix/@name does not exist",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  /*
   * additionalInformation/appendix/@description A description of the appendix
   */

  protected ReporterDetails validateERMS31(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    for (AppendixType at : appendix) {
      if (at.getDescription() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "additionalInformation/appendix/@description can't be null, in %1$s the additionalInformation/appendix/@description does not exist",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  /*
   * additionalInformation/appendix/@FileFormat The file format for the appendix
   */

  protected ReporterDetails validateERMS32(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    for (AppendixType at : appendix) {
      if (at.getFileFormat() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "additionalInformation/appendix/@FileFormat can't be null, in %1$s the additionalInformation/appendix/@FileFormat does not exist",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  /*
   * additionalInformation/appendix/@originalFileFormat If the appendix has been
   * transformed to the current format and the format the transformation occurred
   * from are registered, this element can contain the original file format
   * information
   */

  protected ReporterDetails validateERMS33(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    for (AppendixType at : appendix) {
      if (at.getOriginalFileFormat() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "additionalInformation/appendix/@originalFileFormat can't be null, in %1$s the additionalInformation/appendix/@originalFileFormat does not exist",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  /*
   * additionalInformation/appendix/@Path The path to the appendix
   */

  protected ReporterDetails validateERMS34(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    for (AppendixType at : appendix) {
      if (at.getPath() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "additionalInformation/appendix/@Path can't be null, in %1$s the additionalInformation/appendix/@Path does not exist",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  /*
   * additionalInformation/appendix/@eSignatureHasExisted Boolean indicating if an
   * eSignature has been present but disposed of before transfer.
   */

  protected ReporterDetails validateERMS35(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    for (AppendixType at : appendix) {
      if (at.isESignatureHasExisted() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "additionalInformation/appendix/@eSignatureHasExisted can't be null, in %1$s the additionalInformation/appendix/@eSignatureHasExisted does not exist",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  /*
   * additionalInformation/appendix/eSignature The appendix can have a saved
   * eSignature described in a grouping element.
   */

  protected ReporterDetails validateERMS36(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<AppendixType> appendix = additionalInformation.getAppendix();
    for (AppendixType at : appendix) {
      if (at.getESignature() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "additionalInformation/appendix/eSignature can't be null, in %1$s the additionalInformation/appendix/eSignature does not exist",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  /*
   * additionalInformation/appendix/eSignature/@present Boolean indicating the
   * presence of an eSignature
   */

  // FALTA 37,38 E 39
  /*
   * protected ReporterDetails validateERMS37(final ErmsValidatorState
   * ermsValidatorState, AdditionalInformation additionalInformation) { final
   * ReporterDetails details = new ReporterDetails(); List<AppendixType> appendix
   * = additionalInformation.getAppendix(); for (AppendixType at : appendix) { if
   * (at.getESignature() != null) { details.setValid(false);
   * details.addIssue(Message.createErrorMessage(
   * "additionalInformation/appendix/eSignature can't be null, in %1$s the additionalInformation/appendix/eSignature does not exist"
   * , ermsValidatorState.getErmsName())); } } return details; }
   * 
   * protected ReporterDetails validateERMS38(final ErmsValidatorState
   * ermsValidatorState, AdditionalInformation additionalInformation) { final
   * ReporterDetails details = new ReporterDetails(); List<AppendixType> appendix
   * = additionalInformation.getAppendix(); for (AppendixType at : appendix) { if
   * (at.getESignature() == null) { details.setValid(false);
   * details.addIssue(Message.createErrorMessage(
   * "additionalInformation/appendix/eSignature can't be null, in %1$s the additionalInformation/appendix/eSignature does not exist"
   * , ermsValidatorState.getErmsName())); } } return details; }
   * 
   */

  /*
   * additionalInformation/ownElement Additional information in the form of
   * creation of small number of extending elements creation a grouping by using
   * elements present for generic construction.
   */

  protected ReporterDetails validateERMS40(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<OwnElement> elements = additionalInformation.getOwnElement();

    if (elements.isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "ERMS file may contain a additionalInformation/ownElement, in %1$s the additionalInformation/ownElement does not exist",
        ermsValidatorState.getErmsName()));
    }

    return details;
  }

  /*
   * additionalInformation/ownElement/ownElementDescription A description of the
   * own elements purpose
   */

  protected ReporterDetails validateERMS41(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();
    List<OwnElement> elements = additionalInformation.getOwnElement();

    for (OwnElement oe : elements) {

      if (oe.getOwnElementDescription() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "ERMS file should contain a additionalInformation/ownElement/ownElementDescription, in %1$s the additionalInformation/ownElement/ownElementDescription does not exist",
          ermsValidatorState.getErmsName()));
      }
    }

    return details;
  }

  /*
   * additionalInformation/ownElement/ The elements and attributes for the own
   * element are seen in the example
   */
  // PENSO QUE A ESPECIFICACAO ESTA MAL
  // protected ReporterDetails validateERMS42(final ErmsValidatorState
  // ermsValidatorState,
  // AdditionalInformation additionalInformation) {
  // final ReporterDetails details = new ReporterDetails();
  // List<OwnElement> elements = additionalInformation.getOwnElement();
  //
  // for (OwnElement oe : elements) {
  //
  // if (oe.getOwnElementDescription() == null) {
  // details.setValid(false);
  // details.addIssue(Message.createErrorMessage(
  // "ERMS file should contain a
  // additionalInformation/ownElement/ownElementDescription, in %1$s the
  // additionalInformation/ownElement/ownElementDescription does not exist",
  // ermsValidatorState.getErmsName()));
  // }
  // }
  //
  // return details;
  // }

  /*
   * additionalInformation/additionalXMLData Additional information in the form of
   * extending XML data that is inserted.
   */

  protected ReporterDetails validateERMS43(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();

    List<ExtendingComplexType> xmlData = additionalInformation.getAdditionalXMLData();

    if (xmlData.isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "ERMS file may contain a additionalInformation/additionalXMLData, in %1$s the additionalInformation/additionalXMLData does not exist",
        ermsValidatorState.getErmsName()));
    }

    return details;
  }

  /*
   * additionalInformation/additionalBinData in the form of inserted binary 64
   * data.
   */

  protected ReporterDetails validateERMS44(final ErmsValidatorState ermsValidatorState,
    AdditionalInformation additionalInformation) {
    final ReporterDetails details = new ReporterDetails();

    List<byte[]> binData = additionalInformation.getAdditionalBinData();

    if (binData.isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "ERMS file may contain a additionalInformation/additionalBinData, in %1$s the additionalInformation/additionalBinData does not exist",
        ermsValidatorState.getErmsName()));
    }

    return details;
  }


  protected abstract String getERMSVersion();
}
