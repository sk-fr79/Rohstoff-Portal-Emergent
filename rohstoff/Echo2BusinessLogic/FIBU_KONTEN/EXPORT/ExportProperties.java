package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import java.util.Date;

/**
 * Class to specify properties for an Export of Data in the DATEV export
 * 
 * @author nils
 * 
 */
public class ExportProperties {
	/** Description/name of export set */
	private String name;

	/** The date when the export runs (= current date) */
	private Date exportDate;
	/** The start date range for a new export set */
	private Date start;
	/** The end date range for a new export set */
	private Date end;
	/** The ID of an existing export set */
	private int id;
	/** File name of export set to be written */
	private String fileName;
	/** User name of exporting user */
	private String userName = "";
	/** */
	private boolean festgeschrieben = false;
	
	/** For exporting/debugging singular Belege ("einzeln") */
	private String debugBelegNummer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getExportDate() {
		return exportDate;
	}

	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}

	public String toString() {
		String answer = "";
		if (this.getId() == 0) {
			answer = "Neuexport " + getName();
		} else {
			answer = "Export "
					+ getId()
					+ " "
					+ (festgeschrieben == false ? "festschreiben"
							: "wiederholen") + " (" + getName() + ") ";
		}
		return answer;

	}

	public void setIsMaterialized(boolean f) {
		festgeschrieben = f;
	}

	public boolean isMaterialized() {
		return festgeschrieben;
	}

	public String getDebugBelegNummer() {
		return debugBelegNummer;
	}

	public void setDebugBelegNummer(String debugBelegNummer) {
		this.debugBelegNummer = debugBelegNummer;
	}
}
