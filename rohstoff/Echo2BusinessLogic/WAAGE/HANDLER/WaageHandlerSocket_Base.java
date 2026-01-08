package rohstoff.Echo2BusinessLogic.WAAGE.HANDLER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import panter.gmbh.basics4project.DEBUG;

public abstract class WaageHandlerSocket_Base extends WaageHandlerBase {
	
	// mögliche Parameter um die Waage anzusprechen
	public static enum ENUM_Parameter {IP_ADR, PORT, CMD_BEGIN_TAG, CMD_ENDE_TAG};
	
	protected Socket m_ip_socket = null;
	protected PrintWriter m_socket_writer = null;
	protected BufferedReader m_socket_reader = null;
	
	protected String m_CommandStringForWaage = null;
	
	
	/**
	 * Standard-Konstruktur
	 */
	public WaageHandlerSocket_Base(){
		super();
	}


	
	/**
	 * die kommunikatin initialisieren
	 */
	protected void listenSocket() throws Exception {
		
		String sHost = getParameter(ENUM_Parameter.IP_ADR.name());
		String sPort = getParameter(ENUM_Parameter.PORT.name());
		
		
		int nPort = 0;
		try {
			nPort = Integer.parseInt(sPort);
		} catch (Exception e1) {
			nPort = 0;
		}
		
		// prüfen, ob die beiden Parameter gegeben sind
		if (sHost == null || sHost.equals("") || nPort == 0){ 
			return;
		}
		
		// Create socket connection
		try {
			DEBUG.System_println("trying to establish socket: " + sHost + ":" + sPort, DEBUG.DEBUG_FLAG_DIVERS1);

			m_ip_socket = new Socket(sHost, nPort);
			m_ip_socket.setSoTimeout(5000);
			m_socket_writer = new PrintWriter(m_ip_socket.getOutputStream(), true);
			m_socket_reader = new BufferedReader(new InputStreamReader(m_ip_socket.getInputStream()));

			DEBUG.System_println("socket established : " + sHost + ":" + sPort, DEBUG.DEBUG_FLAG_DIVERS1);
			
		} catch (UnknownHostException e2) {
			System.out.println("Unbekannter Host:" + sHost);
			throw new UnknownHostException("Unknown Host: " + sHost  + " " + e2.getMessage());
			
		} catch (IOException e3) {
			System.out.println("Socket-Fehler: (" + sHost + ":" + nPort + " / " + e3.getMessage() +  ")");
			throw new IOException("Socket-Fehler! (" + sHost + ":" + nPort + " / " + e3.getMessage() +  ")", e3.getCause());
		}
	}
	
	

	/** 
	 * Standard-Lesevorgang mit readlLine().
	 * 
	 * 
	 * @return Die zurückgelieferte ZEILE
	 * @throws Exception 
	 * */
	@Override
	public String leseWaage() throws Exception {
		// Send data over socket
		String line = "";
		
		
		// Receive text from server
		try {
			// neu an den Socket binden
			this.listenSocket();
	
	
			// schicken des Kommandos zum Server
			m_socket_writer.println(m_CommandStringForWaage);


			
			// lesen des Rückgabewertes
			line = m_socket_reader.readLine();
			
			// umsetzen des Rückgabewertes in die Ergebnisstruktur WaageSatzXXX
			parseWaageResult(line);
			
		} catch (IOException e) {
			throw new IOException("Lesen der Waagedaten Fehlgeschlagen",e.getCause() );
		} finally{
			
			if (m_socket_reader != null ) m_socket_reader.close();
			if (m_socket_writer != null ) m_socket_writer.close();
			if (m_ip_socket != null ) m_ip_socket.close();
		}

		return line;

	}

	

	
	
//	
//	@Override
//	protected void finalize() throws Throwable {
//		
//		try {   m_socket_reader.close(); } 		catch (Exception e1) { System.out.println("base-finalize: error in.close()");/* war wohl schon zu */ }
//		try { 	m_socket_writer.close();  } 	catch (Exception e2) { /* war wohl schon zu */ }
//		try { 	m_ip_socket.close(); } 	catch (Exception e3) { /* war wohl schon zu */ }
//		
//		super.finalize();
//		
//	}




	
}
