package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.Echo2.MyE2_String;

public abstract class JasperFileDef
{
	public static String ENDING_PDF = ".pdf";
	public static String ENDING_HTML = ".html";
	public static String ENDING_XML = ".xml";
	public static String ENDING_XLS = ".xls";
	public static String ENDING_CSV = ".csv";
	public static String ENDING_ZIP = ".zip";

	public static String MIMETYP_PDF	= 	"application/pdf";
	public static String MIMETYP_HTML	= 	"application/html";
	public static String MIMETYP_XML 	= 	"application/xml";
	public static String MIMETYP_XLS 	= 	"application/xls";
	public static String MIMETYP_CSV 	=	"application/csv";
	public static String MIMETYP_ZIP 	=	"application/zip";

	public static MyE2_String UserText_PDF = 	new MyE2_String("PDF-Dokument");
	public static MyE2_String UserText_HTML = 	new MyE2_String("HTML-Dokument");
	public static MyE2_String UserText_XML = 	new MyE2_String("XML-Dokument");
	public static MyE2_String UserText_XLS = 	new MyE2_String("Excel-Dokument");
	public static MyE2_String UserText_CSV = 	new MyE2_String("CSV-Dokument");
	public static MyE2_String UserText_ZIP = 	new MyE2_String("ZIP-Dokument");
	
	
	public String 		Endung = null;
	public String 		MimeType = null;
	public MyE2_String 	UserText = null;
	
	
	
	
	public enum FileType {
		
		pdf(JasperFileDef.ENDING_PDF, JasperFileDef.MIMETYP_PDF, JasperFileDef.UserText_PDF),
		html(JasperFileDef.ENDING_HTML, JasperFileDef.MIMETYP_HTML, JasperFileDef.UserText_HTML),
		xml(JasperFileDef.ENDING_XML, JasperFileDef.MIMETYP_XML, JasperFileDef.UserText_XML),
		xls(JasperFileDef.ENDING_XLS, JasperFileDef.MIMETYP_XLS, JasperFileDef.UserText_XLS),
		csv(JasperFileDef.ENDING_CSV, JasperFileDef.MIMETYP_CSV, JasperFileDef.UserText_CSV),
		zip(JasperFileDef.ENDING_ZIP, JasperFileDef.MIMETYP_ZIP, JasperFileDef.UserText_ZIP);
		
		public String 		ftEndung = null;
		public String 		ftMimeType = null;
		public MyE2_String 	ftUserText = null;
		private FileType(String endung, String mimeType, MyE2_String userText) {
			this.ftEndung=endung;
			this.ftMimeType = mimeType;
			this.ftUserText = userText;
		}
		
		public String getFtEndung() {
			return ftEndung;
		}
		public String getFtMimeType() {
			return ftMimeType;
		}
		public MyE2_String getFtUserText() {
			return ftUserText;
		}

		
	}
	
	
	/**
	 * versucht einen dateinamen zu lesen und eine filedef zurueckzugeben
	 * @param FileName ist der downloadname ohne pfad
	 * @return
	 */
	public static JasperFileDef  findFileDef(String FileName) {
		JasperFileDef_Free fileDef = null;
		
		for (JasperFileDef.FileType type: JasperFileDef.FileType.values()) {
			if (FileName.toUpperCase().endsWith(type.ftEndung.toUpperCase())) {
				fileDef = new JasperFileDef_Free(type.ftEndung, type.ftMimeType, type.ftUserText);
				break;
			} else if (FileName.toUpperCase().contains(type.ftEndung.toUpperCase()+"_")) {
				fileDef = new JasperFileDef_Free(type.ftEndung, type.ftMimeType, type.ftUserText);
				break;
			} 
		}
		
		if (fileDef==null) {
			fileDef = new JasperFileDef_Free(".bin", "application/bin", new MyE2_String("undefiniert"));
		}
		
		return fileDef;
	}
	
	
}
