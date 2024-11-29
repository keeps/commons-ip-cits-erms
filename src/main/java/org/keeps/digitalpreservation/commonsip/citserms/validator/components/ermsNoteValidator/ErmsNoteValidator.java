/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator.components.ermsNoteValidator;

import java.util.List;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.Note;
import org.keeps.digitalpreservation.commonsip.citserms.utils.Message;
import org.keeps.digitalpreservation.commonsip.citserms.validator.state.ErmsValidatorState;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class ErmsNoteValidator {

  /*
   * note A note regarding, for example, an aggregation or a record
   */
  public ReporterDetails validateERMS49(final ErmsValidatorState ermsValidatorState, List<Note> notes) {
    final ReporterDetails details = new ReporterDetails();
    if (notes.isEmpty()) {
      details.setValid(false);
      details.addIssue(
        Message.createErrorMessage("ERMS file may contain a Note list element, in %1$s the Note list  does not exist",
          ermsValidatorState.getErmsName()));
    }
    return details;
  }

  /*
   * note/@noteType A description of the identifier
   */
  public ReporterDetails validateERMS50(final ErmsValidatorState ermsValidatorState, List<Note> notes) {
    final ReporterDetails details = new ReporterDetails();

    for (Note note : notes) {
      if (note.getNoteType().equals("")) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage("Note may contain a note/@noteType, in %1$s the value is null",
          ermsValidatorState.getErmsName()));

      }
    }
    return details;
  }

  /*
   * note/@noteDate A description of the identifier
   */
  public ReporterDetails validateERMS51(final ErmsValidatorState ermsValidatorState, List<Note> notes) {
    final ReporterDetails details = new ReporterDetails();

    for (Note note : notes) {
      if (note.getNoteDate()==null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage("Note may contain a note/@noteDate, in %1$s the value is null",
          ermsValidatorState.getErmsName()));

      }
    }
    return details;
  }

  protected abstract String getERMSVersion();
}
