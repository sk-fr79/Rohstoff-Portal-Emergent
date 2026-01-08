package rohstoff.Echo2BusinessLogic.WAAGE.HANDLER;

import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzSystec;


public class WaageHandlerSocket_SYSTEC extends WaageHandlerSocket_Base {
	
	// Waagespezifische Parameter
	public static enum ENUM_Parameter_SYSTEC {WAAGENR};
	
	// Waagespezifische Kommandos
	//-- keine
	
	
	/**
	 * Standard-Konstruktur
	 */
	public WaageHandlerSocket_SYSTEC(){
		super();
		// die Kommandos dieser Waage setzen
		setParameter(ENUM_Commands.CMD_READ.name(), "RN");
		setParameter(ENUM_Commands.CMD_READTEMP.name(), "RM");
		setParameter(ENUM_Commands.CMD_TARA_AUSGLEICH.name(), "TA");
		setParameter(ENUM_Commands.CMD_TARA_LOESCHEN.name(), "TC");
		setParameter(ENUM_Commands.CMD_TARA_MANUELL.name(),"TM");
		
		// Basisparameter
		setParameter(ENUM_Parameter.CMD_BEGIN_TAG.name(), "<");
		setParameter(ENUM_Parameter.CMD_ENDE_TAG.name(),  ">");

	}


	
	
	


	
	/**
	 * Setzt das Kommando für die Anfrage an die Waage
	 * Das Kommando ist ein Enum-Name, der hier dann aus der Liste übersetzt wird 
	 */
	@Override
	public void setCommand(WaageHandlerBase.ENUM_Commands Command) {
		m_currentCommand = Command;
		
		String sCmd = null;
		m_CommandStringForWaage = "";
		
		if (Command != null){
			sCmd = getParameter(Command.name());
			String sWaageNr = getParameter(ENUM_Parameter_SYSTEC.WAAGENR.name());
			String sBegin = getParameter(ENUM_Parameter.CMD_BEGIN_TAG.name());
			String sEnde = getParameter(ENUM_Parameter.CMD_ENDE_TAG.name());
			
			if (Command.equals(ENUM_Commands.CMD_TARA_MANUELL.name())){
				// tara-Wert lesen.
				String sTara = "00000000" + getParameter(ENUM_Commands.TARA_MANUELL_WERT.name());
				sTara = sTara.substring(sTara.length() - 8);
				m_CommandStringForWaage = sBegin + sCmd + sTara + sWaageNr + sEnde;
			} else {
				m_CommandStringForWaage = sBegin + sCmd +  sWaageNr + sEnde;
			}
		}
	}
	
	
	@Override
	protected void parseWaageResult(String WaageResultValue) {
		if (m_currentCommand.equals(WaageHandlerBase.ENUM_Commands.CMD_READ)){

			m_WaageSatz = new WaageSatzSystec();
			m_WaageSatz.parseWaageData(WaageResultValue);
			
		} else if (m_currentCommand.equals(WaageHandlerBase.ENUM_Commands.CMD_READTEMP)){
			
			m_WaageSatz = new WaageSatzSystec();
			m_WaageSatz.parseWaageData(WaageResultValue);
			
		} else {
			m_WaageSatz = null;
		}
	}

	
	@Override
	public WaageSatzBase getWaageSatz() {
		return m_WaageSatz;
	}

	
//	@Override
//	protected void finalize() throws Throwable {
//		super.finalize();
//	}








	
}
