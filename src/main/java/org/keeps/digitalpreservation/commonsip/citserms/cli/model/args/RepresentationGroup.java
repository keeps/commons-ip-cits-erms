package org.keeps.digitalpreservation.commonsip.citserms.cli.model.args;

import picocli.CommandLine;

public class RepresentationGroup {
    @CommandLine.ArgGroup(exclusive = false)
    Representation representation;

    public Representation getRepresentation() {
        return representation;
    }
}
