package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import static org.junit.Assert.*;

import org.junit.Test;

public class UP_DOWN_FileName_Checker_Test {

	@Test
	public void test() {

		String entry = "/daten/entwicklungstools/tomcat/webapps/rohstoff_app/archiv_old/MANDANT_0000000001/MASCHINEN/MASCHINEN_ID_1455_Auftragsbestätigung_RTL.pdf";
		String correctOut = "/daten/entwicklungstools/tomcat/webapps/rohstoff_app/archiv_old/MANDANT_0000000001/MASCHINEN/MASCHINEN_ID_1455_Auftragsbestaetigung_RTL.pdf";

		String correction = UP_DOWN_FileName_Checker.correctIllegalCharList(entry);

		assertEquals(correctOut, correction);
	}
}
