/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.cli;

import org.keeps.digitalpreservation.commonsip.citserms.validator.EARKERMSValidator;
import org.roda_project.commons_ip2.cli.model.ExitCodes;
import org.roda_project.commons_ip2.cli.model.enums.ReportTypeEnums;
import org.roda_project.commons_ip2.cli.model.exception.CLIException;
import org.roda_project.commons_ip2.cli.model.exception.ValidationException;
import org.roda_project.commons_ip2.cli.utils.CLI.ValidateCommandUtils;
import org.roda_project.commons_ip2.utils.LogSystem;
import org.roda_project.commons_ip2.validator.EARKPyIPValidator;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJSONPyIP;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import picocli.CommandLine;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.Callable;

import static org.roda_project.commons_ip2.cli.model.enums.ReportTypeEnums.ReportType.COMMONS_IP;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name = "validate", showDefaultValues = true, description = "Validates E-ARK CITS SIARD packages against the specification")
public class Validate implements Callable<Integer> {

  private static final Logger LOGGER = LoggerFactory.getLogger(org.roda_project.commons_ip2.cli.Validate.class);
  @CommandLine.Spec
  CommandLine.Model.CommandSpec spec;
  @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help and exit")
  boolean help;

  @CommandLine.Option(names = {"-i",
    "--inputs"}, split = ",", required = true, description = "Paths to the SIPs archive file or files", paramLabel = "<path>")
  List<String> sipPaths;

  @CommandLine.Option(names = {"-r",
    "--reporter-type"}, paramLabel = "<type>", description = "Report type (possible values: ${COMPLETION-CANDIDATES})")
  ReportTypeEnums.ReportType reportType = COMMONS_IP;

  @CommandLine.Option(names = {"-o",
    "--output-report-dir"}, paramLabel = "<path>", description = "Path to save the validation report. If not set a report will be generated in the same folder as the IP package.")
  String reportPathDir = System.getProperty("user.dir");

  @CommandLine.Option(names = {"-v",
    "--verbose"}, description = "Verbose command line output with all validation steps")
  boolean verbose;

  @CommandLine.Option(names = {"--specification-version"}, description = "E-ARK CSIP version")
  String version = "2.1.0";

  public Integer call() throws ValidationException, CLIException {
    for (String sip : sipPaths) {
      try {
        handleSipValidation(sip, reportPathDir, verbose);
      } catch (IOException e) {
        throw new ValidationException("Unable to create necessary files to start validation");
      } catch (ParserConfigurationException | SAXException | NoSuchAlgorithmException e) {
        throw new ValidationException("Failed to validate");
      }
    }

    return ExitCodes.EXIT_CODE_OK;
  }

  private void handleSipValidation(final String sip, final String reportPathDir,
                                   final boolean verbose)
    throws IOException, ParserConfigurationException, SAXException, CLIException, NoSuchAlgorithmException {
    final Path sipPath = Paths.get(sip);

    Path reportPath = ValidateCommandUtils.obtainReportPath(sipPath, reportPathDir);
    CommandLine cmd = spec.commandLine();
    String commandLineString = String.join(" ", cmd.getParseResult().originalArgs());


    LogSystem.logOperatingSystemInfo();
    LOGGER.debug("command executed: {}", commandLineString);
    switch (reportType) {
      case COMMONS_IP -> {
        final OutputStream outputStream = ValidateCommandUtils.createReportOutputStream(reportPath);
        if (outputStream != null) {
          final ValidationReportOutputJson jsonReporter = new ValidationReportOutputJson(sipPath, outputStream);
          final EARKSIPValidator earksipValidator = new EARKSIPValidator(jsonReporter, version);
          final EARKERMSValidator earkermsValidator = new EARKERMSValidator(jsonReporter, version);
          if (verbose) {
            earksipValidator.addObserver(new ProgressValidationLoggerObserver());
          }
          earksipValidator.validate(version);
          earkermsValidator.validate(version);
        }
      }
      case PYIP -> {
        final ValidationReportOutputJSONPyIP jsonReporter = new ValidationReportOutputJSONPyIP(reportPath, sipPath);
        final EARKPyIPValidator earkPyIPValidator = new EARKPyIPValidator(jsonReporter, version);
        if (verbose) {
          earkPyIPValidator.addObserver(new ProgressValidationLoggerObserver());
        }
        earkPyIPValidator.validate();
      }
      default -> throw new CLIException("Unexpected value: " + reportType);
    }


    new CommandLine(this).getOut().printf("E-ARK SIP validation report at '%s'%n",
      reportPath.normalize().toAbsolutePath());
  }

}
