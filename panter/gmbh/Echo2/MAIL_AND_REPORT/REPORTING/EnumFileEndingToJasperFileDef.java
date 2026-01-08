/**
 * panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING
 * @author martin
 * @date 16.02.2021
 * 
 */
package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.indep.S;

/**
 * @author martin
 * @date 16.02.2021
 *
 */
public enum EnumFileEndingToJasperFileDef {
	txt(new JasperFileDef_Free(".txt","Application/txt",S.ms("Textdatei")))
	,xlsx(new JasperFileDef_Free(".xlsx","Application/txt",S.ms("Tabellendokument")))
	,docx(new JasperFileDef_Free(".docx","Application/docx",S.ms("Textdokument")))
	,jpeg(new JasperFileDef_Free(".jpeg","Application/jpeg",S.ms("Bild")))
	,html(new JasperFileDef_Free(".html","Application/html",S.ms("Internetseite")))
	,pdf(new JasperFileDef_Free(".pdf","Application/pdf",S.ms("PDF-Datei")))
	,csv(new JasperFileDef_Free(".csv","Application/csv",S.ms("CSV-Datei")))
	,xls(new JasperFileDef_Free(".xls","Application/xls",S.ms("Tabellendokument")))
	,doc(new JasperFileDef_Free(".doc","Application/doc",S.ms("Textdokument")))
	,zip(new JasperFileDef_Free(".zip","Application/zip",S.ms("Archiv")))
	,jpg(new JasperFileDef_Free(".jpg","Application/jpg",S.ms("Bild")))
	,png(new JasperFileDef_Free(".png","Application/png",S.ms("Bild")))
	,xml(new JasperFileDef_Free(".xml","Application/xml",S.ms("XML-Datei")))
	;
	
	
	private JasperFileDef fileDef = null; 
	
	private EnumFileEndingToJasperFileDef(JasperFileDef jasperFileDef) {
		fileDef = jasperFileDef;
	}

	public static JasperFileDef findFileDef(String endung) {
		JasperFileDef def = txt.fileDef;   //wenn nichts gefunden wird, dann txt
		
		String endungVgl = S.NN(endung);
		
		if (endung.startsWith(".")) {
			endungVgl = endung.substring(1);
		}
		for (EnumFileEndingToJasperFileDef en: EnumFileEndingToJasperFileDef.values()) {
			if (endungVgl.toUpperCase().contains(en.name().toUpperCase())) {
				def = en.fileDef;
				break;
			}
			
		}
		return def;
	}
	
	
	
}
