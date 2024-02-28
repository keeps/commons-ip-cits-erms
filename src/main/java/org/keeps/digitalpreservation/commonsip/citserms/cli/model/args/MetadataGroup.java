package org.keeps.digitalpreservation.commonsip.citserms.cli.model.args;

import picocli.CommandLine;

public class MetadataGroup {
    @CommandLine.ArgGroup(exclusive = false)
    Metadata metadata;

    public Metadata getMetadata() {
        return metadata;
    }
}
