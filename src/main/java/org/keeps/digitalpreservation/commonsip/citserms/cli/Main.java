/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.cli;

import org.keeps.digitalpreservation.commonsip.citserms.cli.exception.handlers.PrintExceptionMessageHandler;
import org.keeps.digitalpreservation.commonsip.citserms.cli.exception.handlers.ShortErrorMessageHandler;
import org.keeps.digitalpreservation.commonsip.citserms.cli.providers.VersionProvider;
import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name = "commons-ip-cits-ERMS", subcommands = {Create.class,
    Validate.class}, mixinStandardHelpOptions = true, versionProvider = VersionProvider.class)
public class Main implements Runnable {

  public static void main(String... args) {
    System.exit(new CommandLine(new Main()).setExecutionExceptionHandler(new PrintExceptionMessageHandler())
        .setParameterExceptionHandler(new ShortErrorMessageHandler()).setUsageHelpAutoWidth(true).execute(args));
  }

  @CommandLine.Spec
  CommandLine.Model.CommandSpec spec;

  @Override
  public void run() {
    // if the command was invoked without subcommand, show the usage help
    spec.commandLine().usage(System.err);
  }
}
