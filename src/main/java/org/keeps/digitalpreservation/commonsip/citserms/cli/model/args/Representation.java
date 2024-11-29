/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.cli.model.args;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

public class Representation {
    @CommandLine.Option(names = {
            "--representation-data"}, required = true, split = ",", paramLabel = "<path>", description = "Path to representation file.")
    List<String> representationData;
    @CommandLine.Option(names = "--representation-type", paramLabel = "<type>", description = "Representation type.")
    String representationType;
    @CommandLine.Option(names = "--representation-id", paramLabel = "<id>", description = "Representation identifier. If not set a default value of rep<number> will be used.")
    String representationId;
    @CommandLine.Option(names = "--representation-documentation", paramLabel = "<path>", split = ",", description = "Path(s) to documentation file(s).", showDefaultValue = CommandLine.Help.Visibility.NEVER)
    List<String> representationDocumentation = new ArrayList<>();
    @CommandLine.ArgGroup(exclusive = false, multiplicity = "0..*", heading = "%nThis is the representation  metadata section:%n")
    List<RepresentationMetadataGroup> representationMetadataGroups = new ArrayList<>();

    public List<String> getRepresentationData() {
        return representationData;
    }

    public String getRepresentationType() {
        return representationType;
    }

    public String getRepresentationId() {
        return representationId;
    }

    public List<String> getRepresentationDocumentation() {
        return representationDocumentation;
    }

    public List<RepresentationMetadataGroup> getRepresentationMetadataGroups() {
        return representationMetadataGroups;
    }
}
