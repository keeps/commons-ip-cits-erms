package org.keeps.digitalpreservation.commonsip.citserms.builder;

import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.impl.eark.EARKSIP;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CITSBuilder {

    public void build() throws IPException, InterruptedException {
        EARKSIP earksip = new EARKSIP();
        earksip.setProfile("https://citssiard.dilcis.eu/profile/E-ARK-SIARD-ROOT.xml");

        IPContentInformationType siard2 = new IPContentInformationType("SIARD2");
        siard2.setOtherType("SIARD_2.1");

        earksip.setContentInformationType(siard2);

        earksip.setId("test");

        IPRepresentation ipRepresentation = new IPRepresentation();

        ipRepresentation.setContentInformationType(new IPContentInformationType(IPContentInformationType.IPContentInformationTypeEnum.SIARD2));

        earksip.addRepresentation(ipRepresentation);

        Path build = earksip.build(Paths.get("/home/mguimaraes/Desktop"));
        System.out.printf(build.toString());
    }
}
