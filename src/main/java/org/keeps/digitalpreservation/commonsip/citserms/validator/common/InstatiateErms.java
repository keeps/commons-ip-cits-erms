/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip-cits-siard
 */
package org.keeps.digitalpreservation.commonsip.citserms.validator.common;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.keeps.digitalpreservation.commonsip.citserms.model.beans.ErmsType;
import org.keeps.digitalpreservation.commonsip.citserms.utils.ERMSUtils;
import org.keeps.digitalpreservation.commonsip.citserms.model.IPErmsConstants;
import org.roda_project.commons_ip2.utils.ResourceResolver;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class InstatiateErms {
  public InstatiateErms() {
    // empty constructor
  }

  /**
   * Creates the {@link ErmsType} object from ERMS file.
   *
   * @return the {@link ErmsType} object.
   * @throws JAXBException if some schema error occurs.
   * @throws SAXException  if some parse error occurs.
   */
  public ErmsType instatiateErmsFile(InputStream stream) throws JAXBException, SAXException, XMLStreamException {
    final JAXBContext jaxbContext = JAXBContext.newInstance(ErmsType.class);
    final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    factory.setResourceResolver(new ResourceResolver());
    final InputStream ermsSchemaInputStream = ERMSUtils.class
      .getResourceAsStream(IPErmsConstants.SCHEMA_ERMS_RELATIVE_PATH_FROM_RESOURCES);
    final Source ermsSchemaSource = new StreamSource(ermsSchemaInputStream);
    final Schema schema = factory.newSchema(ermsSchemaSource);
    jaxbUnmarshaller.setSchema(schema);

    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(stream);

    JAXBElement<ErmsType> unmarshal = jaxbUnmarshaller.unmarshal(reader, ErmsType.class);

    return unmarshal.getValue();
  }
}
