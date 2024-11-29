/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.cli.model.args;

import picocli.CommandLine;

public class RepresentationGroup {
    @CommandLine.ArgGroup(exclusive = false)
    Representation representation;

    public Representation getRepresentation() {
        return representation;
    }
}
