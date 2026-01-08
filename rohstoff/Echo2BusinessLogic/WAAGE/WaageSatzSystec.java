package rohstoff.Echo2BusinessLogic.WAAGE;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Systec-Waage Offenburg / Kehl
 * @author manfred
 *
 */
public class WaageSatzSystec extends WaageSatzBase {
	
	/**
	 * Der Wagesatz-Aufbau der Systec-Waage
	 * Reihenfolge der Felder entspricht der Reihenfolge im Satzaufbau
	 */
	public WaageSatzSystec() {
		super();
		this.addNewField(WF_Fehlercode, 2);
		this.addNewField(WF_WaagenStatus, 2);
		this.addNewField(WF_Datum, 8);
		this.addNewField(WF_Zeit, 5);
		this.addNewField(WF_IdentNummer,4 );
		this.addNewField(WF_WaagenNummer, 1);
		this.addNewField(WF_BruttoGewicht, 8);
		this.addNewField(WF_TaraGewicht, 8);
		this.addNewField(WF_NettoGewicht, 8);
		this.addNewField(WF_Einheit,2 );
		this.addNewField(WF_TaraCode, 2);
		this.addNewField(WF_Waegebereich, 1);
		this.addNewField(WF_TerminalNr, 3);
		this.addNewField(WF_Pruefziffer, 8);
		this.setBeginTag("<");
		this.setEndeTag(">");
		this.setDatumsFormat("dd.MM.yy");
	}
	
	@Override
	public String getErrorDescription() {
		String fehlercode = this.getData(WF_Fehlercode);
		String message = null;
		m_StatusWaageResult = ENUM_WaageResultStatus.EMPTY;
		if (fehlercode != null){
			
			if (fehlercode.equalsIgnoreCase("00")){
				m_StatusWaageResult = ENUM_WaageResultStatus.OK;
				message = "OK"; 
			} else {
				m_StatusWaageResult = ENUM_WaageResultStatus.ERROR;
				
				if (fehlercode.equalsIgnoreCase("11")){
					message = "Allgemeiner Waagefehler (z.B. Verbindung zur Lastzelle gestört)";
				} else if (fehlercode.equalsIgnoreCase("12")){
					message = "Waage in Überlast";
				} else if (fehlercode.equalsIgnoreCase("13")){
					message = "Waage in Bewegung (10 Sek. ohne Ruhe) ";
				} else if (fehlercode.equalsIgnoreCase("14")){
					message = "Waage nicht verfügbar";
				} else if (fehlercode.equalsIgnoreCase("15")){
					message = "Tarierungsfehler";
				} else if (fehlercode.equalsIgnoreCase("16")){
					message = "Gewichtsdrucker nicht bereit";
				} else if (fehlercode.equalsIgnoreCase("17")){
					message = "Druckmuster enthält ungültiges Kommando";				
				} else if (fehlercode.equalsIgnoreCase("31")){
					message = "Übertragungsfehler";				
				} else if (fehlercode.equalsIgnoreCase("32")){
					message = "Ungültiger Befehl";
				} else if (fehlercode.equalsIgnoreCase("33")){
					message = "Ungültiger Parameter";
				} else {
					message = "Unbekannter Fehlercode." ;
				}
			}
			message += " (" + fehlercode + ")";
		}
		
		// TODO Auto-generated method stub
		return message;
	}

	
	@Override
	public String getDemoData() {
		SimpleDateFormat fmtDate = new SimpleDateFormat("dd.MM.yy");
		SimpleDateFormat fmtTime = new SimpleDateFormat("HH:mm");
		Random rnd = new Random();
		int Ruhe = rnd.nextInt(1);
		
		DecimalFormat decFormatWeight = new DecimalFormat("####0.00");
		DecimalFormat decFormat1 = new DecimalFormat("0");
		
		int Brutto = rnd.nextInt(30000);
		int Tara = 400;
		if (Brutto < Tara) Brutto = Tara + Brutto;
		int Netto = Brutto - Tara;
		
		
		Date dt = new Date();
		String 
		sRet  = "00";
		sRet += decFormat1.format(Ruhe) + "1";
		sRet += fmtDate.format(dt);
		sRet += fmtTime.format(dt);
		sRet += "   1";
		sRet += "1";
		String sBrutto = "000000" + decFormatWeight.format(Brutto);
		sRet += sBrutto.substring(sBrutto.length() - 8);
		String sTara = "000000" + decFormatWeight.format(Tara);
		sRet += sTara.substring(sTara.length() - 8);
		String sNetto = "000000" + decFormatWeight.format(Netto);
		sRet += sNetto.substring(sNetto.length() - 8);
		sRet += "kg";
		sRet += "PT";
		sRet += "2";
		sRet += "001";
		sRet += CRC16(sRet);
	
		return sRet;
	}
	
	
}
