package org.keeps.digitalpreservation.commonsip.citserms.cli.model.args;

import picocli.CommandLine;

public class RepresentationMetadataGroup {
    @CommandLine.ArgGroup(exclusive = false)
    RepresentationMetadata representationMetadata;

    public RepresentationMetadata getRepresentationMetadata() {
        return representationMetadata;
    }
}
