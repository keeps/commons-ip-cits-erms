package org.keeps.digitalpreservation.commonsip.citserms.cli.exception.mappers;

import org.keeps.digitalpreservation.commonsip.citserms.cli.model.ExitCodes;
import org.keeps.digitalpreservation.commonsip.citserms.cli.exception.CLIException;
import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class  ExitCodeExceptionMapper implements CommandLine.IExitCodeExceptionMapper {

  @Override
  public int getExitCode(Throwable t) {
    if (t instanceof CLIException) {
      return ExitCodes.EXIT_CODE_CREATE_MISSING_ARGS;
    }

    return 1;
  }
}
