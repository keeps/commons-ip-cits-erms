/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.model;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ErmsType;

import java.nio.file.Path;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsWrapper {
  private ErmsType erms;
  private Path ermsPath;

  public ErmsWrapper(ErmsType erms, Path ermsPath) {
    super();
    this.erms = erms;
    this.ermsPath = ermsPath;
  }

  public ErmsType getErms() {
    return erms;
  }

  public void setErms(ErmsType mets) {
    this.erms = erms;
  }

  public Path getErmsPath() {
    return ermsPath;
  }

  public void setErmsPath(Path ermsPath) {
    this.ermsPath = ermsPath;
  }

}
