/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.cli;

import org.keeps.digitalpreservation.commonsip.citserms.builder.CITSBuilder;
import org.keeps.digitalpreservation.commonsip.citserms.cli.model.ExitCodes;
import org.roda_project.commons_ip2.cli.model.args.RepresentationGroup;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.cli.model.args.MetadataGroup;
import org.roda_project.commons_ip2.cli.model.exception.InvalidPathException;
import org.roda_project.commons_ip2.cli.model.exception.SIPBuilderException;
import org.roda_project.commons_ip2.cli.utils.CLI.CreateCommandUtils;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name = "create", description = "Creates E-ARK CITS ERMS packages%n", showDefaultValues = true)
public class Create implements Callable<Integer> {

    @CommandLine.ArgGroup(exclusive = false, multiplicity = "0..*", heading = "%nThis is the descriptive metadata section:%n")
    List<MetadataGroup> metadataListArgs = new ArrayList<>();

    @CommandLine.ArgGroup(exclusive = false, multiplicity = "0..*", heading = "%nThis is the representation section:%n")
    List<RepresentationGroup> representationListArgs = new ArrayList<>();

    @CommandLine.Option(names = {"--submitter-name"}, description = "Submitter agent name", paramLabel = "<name>")
    String submitterAgentName;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean help;

    @CommandLine.Option(names = {"-o", "--output"}, showDefaultValue = CommandLine.Help.Visibility.NEVER, paramLabel = "<path>", arity = "1", description = "Path where the E-ARK CITS SIARD will be saved.")
    String outputPath = System.getProperty("user.dir");

    @CommandLine.Option(names = {"-d",
            "--documentation"}, description = "Path(s) to documentation file(s).", split = ",", paramLabel = "<path>", showDefaultValue = CommandLine.Help.Visibility.NEVER)
    List<String> documentation = new ArrayList<>();

    @Override
    public Integer call() throws InvalidPathException {

        if (!CreateCommandUtils.validateMetadataSchemaPaths(metadataListArgs)) {
            throw new InvalidPathException("Make sure if all the descriptive metadata schema paths exists");
        }

        //if (representationListArgs.isEmpty()) {
        //    throw new CLIException("There MUST be a minimum of one representation");
       // }

        CITSBuilder builder = new CITSBuilder();
        try {
            builder.setMetadataArgs(metadataListArgs).setRepresentationArgs(representationListArgs).setSubmitterAgentName(submitterAgentName).build();
        } catch (InterruptedException | IPException | SIPBuilderException e) {
            System.out.printf(e.getMessage());
            System.out.println("TESTE!!!!");
        }

        return ExitCodes.EXIT_CODE_OK;
    }
}
