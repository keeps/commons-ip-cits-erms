/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.cli.model.args;

import picocli.CommandLine;

public class RepresentationMetadataGroup {
    @CommandLine.ArgGroup(exclusive = false)
    RepresentationMetadata representationMetadata;

    public RepresentationMetadata getRepresentationMetadata() {
        return representationMetadata;
    }
}
