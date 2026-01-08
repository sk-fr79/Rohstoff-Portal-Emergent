package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVColumn;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVLine;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVReserved;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVText;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVZahl;
/**
 * Model for a Datev CSV header line. See the specs.
 * @author nils
 *
 */
public class HeaderLine extends DatevCSVLine {
	public HeaderLine() {
		HeaderLine s = this;
		s.put(1, 	new DatevCSVColumn("DATEV-Format-KZ", 					true, 	new DatevCSVText(4,4)));
		s.put(2, 	new DatevCSVColumn("Versionsnummer", 					true, 	new DatevCSVZahl(3,0,3)));
		s.put(3, 	new DatevCSVColumn("Datenkategorie", 					true, 	new DatevCSVZahl(2,0,2)));
		s.put(4, 	new DatevCSVColumn("FormatName", 						true, 	new DatevCSVText(0,150)));
		s.put(5, 	new DatevCSVColumn("Formatversion",						true, 	new DatevCSVZahl(3,0,3)));
		s.put(6, 	new DatevCSVColumn("Erzeugt am", 						true, 	new DatevCSVZahl(17,0,17)));
		
		// Reserviert, null
		s.put(7, 	new DatevCSVColumn("Importiert", 						true, 	new DatevCSVReserved()));

		s.put(8, 	new DatevCSVColumn("Herkunft", 							true, 	new DatevCSVText(0,2)));
		s.put(9, 	new DatevCSVColumn("Exportiert von", 					true, 	new DatevCSVText(0,25)));
		
		// reserviert
		s.put(10, 	new DatevCSVColumn("Importiert von", 					true, 	new DatevCSVReserved()));

		s.put(11, 	new DatevCSVColumn("Berater", 							true, 	new DatevCSVZahl(7,0,7)));
		s.put(12, 	new DatevCSVColumn("Mandant", 							true, 	new DatevCSVZahl(5,0,5)));
		
		s.put(13, 	new DatevCSVColumn("WJ-Begrinn", 						true, 	new DatevCSVZahl(8,0,8)));
		s.put(14, 	new DatevCSVColumn("Sachkontennummernlänge", 			true, 	new DatevCSVZahl(1,0,1)));

		s.put(15, 	new DatevCSVColumn("Datum von", 						true, 	new DatevCSVZahl(8,0,8)));
		s.put(16, 	new DatevCSVColumn("Datum bis", 						true, 	new DatevCSVZahl(8,0,8)));
		
		s.put(17, 	new DatevCSVColumn("Bezeichnung", 						false, 	new DatevCSVText(0,30)));
		s.put(18, 	new DatevCSVColumn("Diktatkürzel", 						false, 	new DatevCSVText(0,2)));
		s.put(19, 	new DatevCSVColumn("Buchungstyp", 						false, 	new DatevCSVZahl(1,0,1)));
		s.put(20, 	new DatevCSVColumn("Rechnungslegungszweck", 			false, 	new DatevCSVZahl(2,0,2)));
		
		s.put(21, 	new DatevCSVColumn("reserviert", 						false, 	new DatevCSVReserved()));
		s.put(22, 	new DatevCSVColumn("WKZ", 								false, 	new DatevCSVText(0,3)));
		s.put(23, 	new DatevCSVColumn("reserviert", 						false, 	new DatevCSVReserved()));
		s.put(24, 	new DatevCSVColumn("reserviert", 						false, 	new DatevCSVReserved()));
		s.put(25, 	new DatevCSVColumn("reserviert", 						false, 	new DatevCSVReserved()));
		s.put(26, 	new DatevCSVColumn("reserviert", 						false, 	new DatevCSVReserved()));
	}
}