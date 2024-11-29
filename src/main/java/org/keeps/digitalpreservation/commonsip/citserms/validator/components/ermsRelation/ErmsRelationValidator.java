/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsRelation;

import java.util.List;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.Relation;
import org.keeps.digitalpreservation.commonsip.citserms.utils.Message;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class ErmsRelationValidator {

  /*
   * relation Each relation is described with a relation element.
   */
  public ReporterDetails validateERMS52(final ErmsValidatorState ermsValidatorState, List<Relation> relations) {
    final ReporterDetails details = new ReporterDetails();
    if (relations.isEmpty()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "ERMS file may contain a Relation list element, in %1$s the Relation list  does not exist",
        ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * relation/@relationType Classification of the type of relationship described
   */
  public ReporterDetails validateERMS53(final ErmsValidatorState ermsValidatorState, List<Relation> relations) {
    final ReporterDetails details = new ReporterDetails();

    for (Relation relation : relations) {
      if (relation.getRelationType().equals("")) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "relation/relationType can't be null, in %1$s the relation/relationType does not exist",
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
  public ReporterDetails validateERMS54(final ErmsValidatorState ermsValidatorState, List<Relation> relations) {
    final ReporterDetails details = new ReporterDetails();

    for (Relation rel : relations) {

      if (rel.getRelationType().equals("own_relation_definition") && (rel.getOtherRelationType() == null)) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "Relation element should have a relation[@relationType=\"own_relation_definition\"]/@otherRelationType, in %1$s the value is null",
          ermsValidatorState.getErmsName()));
      }
    }
    return details;
  }

  protected abstract String getERMSVersion();
}
