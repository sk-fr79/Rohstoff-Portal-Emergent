package rohstoff.Echo2BusinessLogic.WAAGE.HANDLER;


public class WaageHandlerSocket_SYSTEC_read extends WaageHandlerSocket_SYSTEC{
	
	
	
	public String leseWaage() throws Exception {
		// Send data over socket
		String line = null;
		StringBuffer instr = new StringBuffer();

		String endeTag = getParameter(ENUM_Parameter.CMD_ENDE_TAG.name());
		
		// Receive text from server
		try {		
			// neu an den Socket binden
			this.listenSocket();
	
			// schicken des Kommandos zum Sockeserver
			m_socket_writer.println(m_CommandStringForWaage);
			
			
			int nChar;

			while(true){
				// zeichen lesen
				nChar = m_socket_reader.read();
			
				// Standardzeichen prüfen
				if (nChar == 13 || 
					nChar == -1  ){
					// abbrechen, wenn das ende-Tag auftaucht.
					instr.append((char)nChar);
					break;
				}
				
				instr.append((char)nChar);

				// ende-Tag prüfen
				if (instr.toString().endsWith(endeTag)){
					break;
				}
				
			}

			
			line = instr.toString();
			
			// umsetzen des Rückgabewertes in die Ergebnisstruktur WaageSatzXXX
			parseWaageResult(line);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause() );
			
		} finally {
			// socket und streams schliessen und die objekte nullen
			if (m_socket_reader != null ) m_socket_reader.close();
			if (m_socket_writer != null ) m_socket_writer.close();
			if (m_ip_socket != null ) m_ip_socket.close();
			
			m_socket_reader = null;
			m_socket_writer = null;
			m_ip_socket = null;
		}
		
		return line;
	}	
	

}
