/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.cli.model.args;

import picocli.CommandLine;

public class Metadata {
    @CommandLine.Option(names = "--metadata-file", required = true, paramLabel = "<path>", description = "Path to descriptive metadata file.")
    String metadataFile;
    @CommandLine.Option(names = "--metadata-type", required = true, paramLabel = "<type>", description = "Descriptive metadata type.")
    String metadataType;
    @CommandLine.Option(names = "--metadata-version", paramLabel = "<version>", description = "Descriptive metadata version.")
    String metadataVersion;
    @CommandLine.Option(names = {
            "--metadata-schema"}, description = "Path to descriptive metadata schema file.", paramLabel = "<path>")
    String metadataSchema;

    public String getMetadataFile() {
        return metadataFile;
    }

    public String getMetadataType() {
        return metadataType;
    }

    public String getMetadataVersion() {
        return metadataVersion;
    }

    public String getMetadataSchema() {
        return metadataSchema;
    }
}
