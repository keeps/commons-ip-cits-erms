/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsDateValidator;

import java.util.List;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.DateTypeComplex;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.DatesType;
import org.keeps.digitalpreservation.commonsip.citserms.utils.Message;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class ErmsDateValidator {

  /*
   * Dates A grouping element for all different kinds of dates occurring in the
   * document
   */
  public ReporterDetails validateERMS45(final ErmsValidatorState ermsValidatorState, DatesType dates) {
    final ReporterDetails details = new ReporterDetails();
    if (dates == null) {
      details.setValid(false);
      details.addIssue(
        Message.createErrorMessage("ERMS file should contain a Dates element, in %1$s the Dates does not exist",
          ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * dates/date One date element is present for each type of date described
   */
  public ReporterDetails validateERMS46(final ErmsValidatorState ermsValidatorState, DatesType dates) {
    final ReporterDetails details = new ReporterDetails();

    if (dates.getDate().isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("dates/date can't be null, in %1$s the value is null",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * dates/date/@dateType Classification of the type of date described.
   */
  public ReporterDetails validateERMS47(final ErmsValidatorState ermsValidatorState, DatesType dates) {
    final ReporterDetails details = new ReporterDetails();

    List<DateTypeComplex> date = dates.getDate();

    for (DateTypeComplex d : date) {

      if (d.getDateType().equals("")) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage("dates/date/@dateType can't be null, in %1$s the value is null",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  /*
   * dates/date[@dateType="other"]/@otherDateType When the date type is set to the
   * value "other" the otherDateType attribute is used to give the type of date
   * being described.
   */
  public ReporterDetails validateERMS48(final ErmsValidatorState ermsValidatorState, DatesType dates) {
    final ReporterDetails details = new ReporterDetails();

    List<DateTypeComplex> date = dates.getDate();

    for (DateTypeComplex d : date) {

      if (d.getDateType().equals("other") && (d.getOtherDateType() == null)) {
          details.setValid(false);
          details.addIssue(Message.createErrorMessage(
            "Date element should have a dates/date[@dateType=\"other\"]/@otherDateType, in %1$s the value is null",
            ermsValidatorState.getErmsName()));

      }
    }
    return details;
  }

  protected abstract String getERMSVersion();
}
