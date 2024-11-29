/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
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
