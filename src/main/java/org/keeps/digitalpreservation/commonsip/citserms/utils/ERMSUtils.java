/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.keeps.digitalpreservation.commonsip.citserms.model.ErmsWrapper;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ErmsType;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.LocType;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType.FLocat;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType.MdRef;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.MetsHdr.Agent.Note;
import org.roda_project.commons_ip2.model.IPAgent;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.utils.IanaMediaTypes;
import org.roda_project.commons_ip2.utils.ResourceResolver;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.keeps.digitalpreservation.commonsip.citserms.model.IPErmsConstants;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public final class ERMSUtils {

  private ERMSUtils() {
    // do nothing
  }

  public static Mets instantiateERMSFromFile(Path ermsFile) throws JAXBException, SAXException, IOException {
    JAXBContext jaxbContext = JAXBContext.newInstance(ErmsType.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    factory.setResourceResolver(new ResourceResolver());
    InputStream ermsSchemaInputStream = ERMSUtils.class
      .getResourceAsStream(IPErmsConstants.SCHEMA_ERMS_RELATIVE_PATH_FROM_RESOURCES);
    Source ermsSchemaSource = new StreamSource(ermsSchemaInputStream);
    Schema schema = factory.newSchema(ermsSchemaSource);
    jaxbUnmarshaller.setSchema(schema);
    return (Mets) jaxbUnmarshaller.unmarshal(new BufferedInputStream(Files.newInputStream(ermsFile.toAbsolutePath())));
  }

  public static Path marshallERMS(ErmsType erms, Path tempERMSFile, boolean rootERMS)
    throws JAXBException, IOException, IPException {
    JAXBContext context = JAXBContext.newInstance(ErmsType.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    if (rootERMS) {
      m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
        "http://www.loc.gov/METS/ schemas/" + IPErmsConstants.SCHEMA_ERMS_FILENAME_WITH_VERSION
          + " http://www.w3.org/1999/xlink schemas/" + IPConstants.SCHEMA_XLINK_FILENAME);
    }

    try (OutputStream metsOutputStream = Files.newOutputStream(tempERMSFile)) {
      m.marshal(erms, metsOutputStream);
    }

    return tempERMSFile;
  }
/*
  public static void addMainERMSToZip(Map<String, ZipEntryInfo> zipEntries, ErmsWrapper ermsWrapper, String ermsPath,
                                      Path buildDir) throws IPException {
    try {
      addERMSToZip(zipEntries, ermsWrapper, ermsPath, buildDir, true, null);
    } catch (JAXBException | IOException e) {
      throw new IPException(e.getMessage(), e);
    }
  }


 */
  /*public static void addMainERMSToZip(Map<String, ZipEntryInfo> zipEntries, ErmsWrapper mainERMSWrapper, Path buildDir)
    throws IPException {
    addMainERMSToZip(zipEntries, mainERMSWrapper, IPErmsConstants.ERMS_FILE, buildDir);
  }

   */

 /* public static void addERMSToZip(Map<String, ZipEntryInfo> zipEntries, ErmsWrapper ermsWrapper, String ermsPath,
                                  Path buildDir, boolean mainErms, FileType fileType) throws JAXBException, IOException, IPException {
    Path temp = Files.createTempFile(buildDir, IPErmsConstants.ERMS_FILE_NAME, IPErmsConstants.ERMS_FILE_EXTENSION);
    ZIPUtils.addERMSFileToZip(zipEntries, temp, ermsPath, ermsWrapper.getErms(), mainErms, fileType);
  }

  */

  /*public static Agent createERMSAgent(IPAgent ipAgent) {
    Agent agent = new Agent();
    agent.setName(ipAgent.getName());
    Note note = new Note();
    note.setValue(ipAgent.getNote());
    note.setNOTETYPE(ipAgent.getNoteType().asString());
    agent.getNote().add(note);

    agent.setROLE(ipAgent.getRole());
    agent.setOTHERROLE(ipAgent.getOtherRole());
    agent.setTYPE(ipAgent.getType().toString());
    agent.setOTHERTYPE(ipAgent.getOtherType());
    return agent;
  }

   */

  /*public static FLocat createFileLocation(String filePath) {
    FLocat fileLocation = new FLocat();
    fileLocation.setType(IPErmsConstants.ERMS_TYPE_SIMPLE);
    fileLocation.setLOCTYPE(LocType.URL.toString());
    fileLocation.setHref(encodeHref(filePath));
    return fileLocation;
  }

   */

  /*public static FLocat createShallowFileLocation(String filePath) {
    FLocat fileLocation = new FLocat();
    fileLocation.setType(IPErmsConstants.ERMS_TYPE_SIMPLE);
    fileLocation.setLOCTYPE(LocType.URL.toString());
    fileLocation.setHref(filePath);
    return fileLocation;
  }

   */

  public static MdRef setFileBasicInformation(Path file, MdRef mdRef) throws IPException, InterruptedException {
    // mimetype info.
    try {
      mdRef.setMIMETYPE(getFileMimetype(file));
    } catch (IOException e) {
      throw new IPException("Error probing file content (" + file + ")", e);
    }

    // date creation info.
    try {
      mdRef.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    // size info.
    try {
      mdRef.setSIZE(Files.size(file));
    } catch (IOException e) {
      throw new IPException("Error getting file size (" + file + ")", e);
    }

    return mdRef;
  }

  public static void setFileBasicInformation(Logger logger, Path file, FileType fileType)
    throws IPException, InterruptedException {
    // mimetype info.
    try {
      logger.debug("Setting mimetype {}", file);
      fileType.setMIMETYPE(getFileMimetype(file));
      logger.debug("Done setting mimetype");
    } catch (IOException e) {
      throw new IPException("Error probing content-type (" + file.toString() + ")", e);
    }

    // date creation info.
    try {
      fileType.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting curent calendar (" + file.toString() + ")", e);
    }

    // size info.
    try {
      logger.debug("Setting file size {}", file);
      fileType.setSIZE(Files.size(file));
      logger.debug("Done setting file size");
    } catch (IOException e) {
      throw new IPException("Error getting file size (" + file.toString() + ")", e);
    }
  }

  private static String getFileMimetype(Path file) throws IOException {
    String probedContentType = Files.probeContentType(file);
    if (probedContentType == null) {
      probedContentType = "application/octet-stream";
    } else {
      if (!IanaMediaTypes.getIanaMediaTypesList().contains(probedContentType)) {
        probedContentType = "application/octet-stream";
      }
    }
    return probedContentType;
  }

  /**
   * Decodes a value from a METS HREF attribute.
   *
   * <p>
   * 20170511 hsilva: a global variable called
   * {//@link IPErmsConstants.ERMS_ENCODE_AND_DECODE_HREF} is used to enable/disable the
   * effective decode (done this way to avoid lots of changes in the methods that
   * use this method)
   * </p>
   */
  public static String decodeHref(String value) {
    if (IPErmsConstants.ERMS_ENCODE_AND_DECODE_HREF) {
      try {
        value = URLDecoder.decode(value, "UTF-8");
      } catch (NullPointerException | UnsupportedEncodingException e) {
        // do nothing
      }
    }
    return value;
  }

  /**
   * Encodes a value to put in METS HREF attribute.
   *
   * <p>
   * 20170511 hsilva: a global variable called
   * {//@link IPErmsConstants.ERMS_ENCODE_AND_DECODE_HREF} is used to enable/disable the
   * effective encode (done this way to avoid lots of changes in the methods that
   * use this method). This method is not multi-thread safe when using different
   * SIP formats.
   * </p>
   */
  public static String encodeHref(String value) {
    if (IPErmsConstants.ERMS_ENCODE_AND_DECODE_HREF) {
      value = escapeSpecialCharacters(value);
    }
    return value;
  }

  public static String escapeSpecialCharacters(String input) {
    StringBuilder resultStr = new StringBuilder();
    for (char ch : input.toCharArray()) {
      if (isSafeChar(ch)) {
        resultStr.append(ch);
      } else {
        resultStr.append(encodeUnsafeChar(ch));
      }
    }
    return resultStr.toString();
  }

  private static boolean isSafeChar(char ch) {
    return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
      || ":/$-_.!*'(),".indexOf(ch) >= 0;
  }

  private static String encodeUnsafeChar(char ch) {
    String ret = String.valueOf(ch);
    try {
      ret = URLEncoder.encode(ret, "UTF-8");
    } catch (NullPointerException | UnsupportedEncodingException e) {
      // do nothing & return original value
    }
    return ret;
  }
}
