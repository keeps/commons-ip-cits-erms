/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator.state;

import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ErmsType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;

import java.util.ArrayList;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class ErmsValidatorState {
  /**
   * {@link Erms}.
   */
  private ErmsType erms = null;
  /**
   * Type of the ip.
   */
  private String ipType = null;
  /**
   * Mets name.
   */
  private String ermsName = null;
  /**
   * Mets path.
   */
  private String ermsPath = null;
  /**
   * {@link ArrayList} with the internal ids.
   */
  private ArrayList<String> ermsInternalIds = new ArrayList<>();


  public ErmsType getErms() {
    return erms;
  }

  public void setErms(ErmsType instatiateErmsFile) {
    this.erms = instatiateErmsFile;
  }

  public String getIpType() {
    return ipType;
  }

  /**
   * Delete all entries from metsInternalIds list
   */
  public void flushEntries() {
    ermsInternalIds.clear();
  }

  public void setErmsPath(String ermsPath) {
    this.ermsPath = ermsPath;
  }

  public void setErmsName(String ermsName) {
    this.ermsName=ermsName;
  }

  public String getErmsName() { return ermsName; }

}
