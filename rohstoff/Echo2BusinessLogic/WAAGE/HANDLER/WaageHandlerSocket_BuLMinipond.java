package rohstoff.Echo2BusinessLogic.WAAGE.HANDLER;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase.ENUM_WaageResultStatus;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBuLGewicht;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBuLGewichtTemp;

public class WaageHandlerSocket_BuLMinipond extends WaageHandlerSocket_Base {
	
	// mögliche Parameter um die Waage anzusprechen
	public static enum ENUM_Commands_BuLMinipond {  CMD_SET_DATE, CMD_SET_TIME };
	
	
	/**
	 * Standard-Konstruktur
	 */
	public WaageHandlerSocket_BuLMinipond(){
		super();
		// die Kommandos dieser Waage setzen
		setParameter(ENUM_Commands.CMD_READ.name(), "p");
		setParameter(ENUM_Commands.CMD_READTEMP.name(), "LD");
		setParameter(ENUM_Commands.CMD_INIT.name(),"iC");

		setParameter(ENUM_Commands_BuLMinipond.CMD_SET_DATE.name(),"Ds");
		setParameter(ENUM_Commands_BuLMinipond.CMD_SET_TIME.name(),"Hs");

		// Basisparameter
		setParameter(ENUM_Parameter.CMD_BEGIN_TAG.name(), Character.toString((char)2 ));
		setParameter(ENUM_Parameter.CMD_ENDE_TAG.name(),  Character.toString((char)3 ));

	}

	
	public String leseWaage() throws Exception {
		String line = null;
		line = _leseWaage(m_CommandStringForWaage);
		parseWaageResult(line);

		// prüfen, ob alles ok war, oder ob der fehler "1" zurückgekommen ist,
		// wenn ja, muss man das Kommando iC schicken
		if (m_WaageSatz.getStatus().equals(ENUM_WaageResultStatus.ERROR)){
				
			if (m_WaageSatz.getData(WaageSatzBase.WF_Fehlercode).equals("1")){
			
				String initCmd = getParameter(ENUM_Parameter.CMD_BEGIN_TAG.name()) + 
								 getParameter(ENUM_Commands.CMD_INIT.name()) + 
								 getParameter(ENUM_Parameter.CMD_ENDE_TAG.name() );
				
				DEBUG.System_println("init : " + initCmd, DEBUG.DEBUG_FLAG_DIVERS1);
				Thread.sleep(200);
				
				_leseWaage(initCmd);
				
				// nochmal lesen...
				line = _leseWaage(m_CommandStringForWaage);
				
				parseWaageResult(line);
			
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(m_WaageSatz.getErrorDescription()));
			}
			
		}
		
		return line;
	}

	
	/**
	 * führt das Kommando aus, speichert die geparste Rückgabewerte 
	 * und gibt die originale Zeile zurück
	 * 
	 * @param CommandStringForWaage
	 * @return
	 * @throws Exception
	 */
	private String _leseWaage(String CommandStringForWaage) throws Exception {
		// Send data over socket
		String line = null;
		StringBuffer instr = new StringBuffer();

		String endeTag = getParameter(ENUM_Parameter.CMD_ENDE_TAG.name());
		int sizeEndeTag = endeTag.length();
		
		
		
		// Receive text from server
		try {		
			
			// neu an den Socket binden
			listenSocket();
			
			// schicken des Kommandos zum Sockeserver
			m_socket_writer.println(CommandStringForWaage);
			
			int nChar;
			
			while(true){
				// zeichen lesen
				nChar = m_socket_reader.read();
			
				if (nChar == 13 || 
					nChar == -1 || 
					nChar == endeTag.charAt(0) ){
					
					// abbrechen, wenn das ende-Tag auftaucht.
					instr.append((char)nChar);
					
					break;
				}
				
				
				instr.append((char)nChar);
				
			}

			
			line = instr.toString();
			
		} catch (IOException e) {
			System.out.println("error" + e.getMessage());
			throw new IOException("Lesen der Waagedaten Fehlgeschlagen",e );
			
		} finally {
			DEBUG.System_println("close socket bul", DEBUG.DEBUG_FLAG_DIVERS1);
			
			if (m_socket_reader != null ) m_socket_reader.close();
			if (m_socket_writer != null ) m_socket_writer.close();
			if (m_ip_socket != null ) m_ip_socket.close();
			
		}
		return line;
	}

	
	/**
	 * Setzt das Kommando für die Anfrage an die Waage
	 * Das Kommando ist ein Enum-Name, der hier dann aus der Liste übersetzt wird 
	 */
	@Override
	public void setCommand(WaageHandlerBase.ENUM_Commands Command) {
		m_currentCommand = Command;
		
		m_CommandStringForWaage = "";
		
		// den Kommando-Text lesen
		String sCmd = getParameter(Command.name());
		
		if (sCmd != null){
			String sBegin = getParameter(ENUM_Parameter.CMD_BEGIN_TAG.name());
			String sEnde = getParameter(ENUM_Parameter.CMD_ENDE_TAG.name());
			
			
			// Einzelne Kommando-Parameter einfügen
			if (Command.equals(ENUM_Commands_BuLMinipond.CMD_SET_DATE)){
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
				String sDate = df.format(new Date());
				m_CommandStringForWaage = sBegin + sCmd + sDate +  sEnde;
			} 
			else if (Command.equals(ENUM_Commands_BuLMinipond.CMD_SET_TIME)){
				SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
				String sDate = df.format(new Date());
				m_CommandStringForWaage = sBegin + sCmd + sDate +  sEnde;
			}
			else
			{
				m_CommandStringForWaage = sBegin + sCmd + sEnde;
			}
		}
	}



	@Override
	public WaageSatzBase getWaageSatz() {
		return m_WaageSatz;
	}
	



	@Override
	protected void parseWaageResult(String WaageResultValue) {
		if (m_currentCommand.equals(WaageHandlerBase.ENUM_Commands.CMD_READ)){

			m_WaageSatz = new WaageSatzBuLGewicht();
			m_WaageSatz.parseWaageData(WaageResultValue);
			
		} else if (m_currentCommand.equals(WaageHandlerBase.ENUM_Commands.CMD_READTEMP)){
			
			m_WaageSatz = new WaageSatzBuLGewichtTemp();
			m_WaageSatz.parseWaageData(WaageResultValue);
			
		} else {
			m_WaageSatz = null;
		}
	}







	
	



	
}
