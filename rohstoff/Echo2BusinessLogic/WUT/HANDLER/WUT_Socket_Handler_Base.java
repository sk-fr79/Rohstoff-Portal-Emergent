/**
 * rohstoff.Echo2BusinessLogic.WUT.HANDLER
 * @author manfred
 * @date 21.11.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.WUT.HANDLER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;



/**
 * @author manfred
 * @date 21.11.2016
 *
 */
public class WUT_Socket_Handler_Base {

	
	protected Socket m_ip_socket = null;
	protected PrintWriter m_socket_writer = null;
//	protected BufferedReader m_socket_reader = null;
	protected InputStream m_socket_reader = null;
//	protected InputStream    m_socket_in = null;

	protected String  _hostIP = null;
	protected String  _hostPort = null;
	protected String  _password = null;
	
	// die Commands, die ausgeführt werden sollen
	protected Vector<WUT_Command> _vCommands = null;
	
	// verschiedene Kommandos (key) und Rückgabewerte (values)  die für einen Socket abgeschickt werden können.
//	private LinkedHashMap<String,String> 	_Commands;

	
	public WUT_Socket_Handler_Base() {
//		_Commands = new LinkedHashMap<>();
		_vCommands = new Vector<>();
	}
	
	
	public WUT_Socket_Handler_Base setHostIP(String sIP){
		_hostIP = sIP;
		return this;
	}
	
	public WUT_Socket_Handler_Base setPort(String sPort){
		_hostPort = sPort;
		return this;
	}
	
	public WUT_Socket_Handler_Base setPassword(String Pwd){
		_password = Pwd;
		return this;
	}
	
	
	
	
	/**
	 * Command in der Liste definieren, noch kein Value
	 * @author manfred
	 * @date 24.11.2016
	 *
	 * @param Name
	 */
	public void addCommand(WUT_Command command){
		_vCommands.add(command);
	}
	
	
	/**
	 * gibt den gelesenen Wert des Kommandos zurück
	 * @author manfred
	 * @date 24.11.2016
	 *
	 * @param Name
	 * @return
	 */
	protected String getCommandString(WUT_Command cmd){
		String sRet = cmd.createCommand(_password);
		return sRet;
	}
	

	/**
	 * setzt den Wert eines Aufgerufenen Kommandos, true,wenn das Kommando vorher gesetzt war, sonst false
	 * @author manfred
	 * @date 24.11.2016
	 *
	 * @param Name
	 */
	protected void setCommandResult(WUT_Command cmd, String Value){
		cmd.SetOrigResult(Value);
		
	}
	
	
	/**
	 * gibt alle commands zurück, auch mit Ergebniswerten
	 * @author manfred
	 * @date 02.12.2016
	 *
	 * @return
	 */
	public Vector<WUT_Command> getCommands(){
		return _vCommands;
	}
	
	/**
	 * gibt spezifisches Kommando an Stelle x im Commandoarray zurück
	 * @author manfred
	 * @date 02.12.2016
	 *
	 * @param idx
	 * @return
	 */
	public ArrayList<String> getResultToken(int idx){
		if (_vCommands.size()  < idx ) return null;
		return _vCommands.elementAt(idx).getResultToken();
	}
	
	/**
	 * gibt den kompletten Resultstring des Kommandos
	 * @author manfred
	 * @date 02.12.2016
	 *
	 * @param idx
	 * @return
	 */
	public String getResultOri(int idx){
		if (_vCommands.size()  < idx ) return null;
		return _vCommands.elementAt(idx).getResultOri();
		
	}
	
	/**
	 * Kommunikation initialisieren  
	 * @author manfred
	 * @date 24.11.2016
	 *
	 * @throws Exception
	 */
	protected void listenSocket() throws Exception {
		
		int nPort = 0;
		try {
			nPort = Integer.parseInt(_hostPort);
		} catch (Exception e1) {
			nPort = 0;
		}
		
		// prüfen, ob die beiden Parameter gegeben sind
		if (_hostIP == null || _hostIP.equals("") || nPort == 0){ 
			return;
		}
		
		// Create socket connection
		try {
			DEBUG.System_println("trying to establish socket: " + _hostIP + ":" + _hostPort, DEBUG.DEBUG_FLAG_DIVERS1);

			m_ip_socket = new Socket(_hostIP, nPort);
			m_ip_socket.setSoTimeout(5000);
			m_socket_writer = new PrintWriter(m_ip_socket.getOutputStream(), true);
//			m_socket_reader = new BufferedReader(new InputStreamReader(m_ip_socket.getInputStream()));
			m_socket_reader = m_ip_socket.getInputStream();

			DEBUG.System_println("socket established : " + _hostIP + ":" + _hostPort, DEBUG.DEBUG_FLAG_DIVERS1);
			
		} catch (UnknownHostException e2) {
			System.out.println("Unbekannter Host:" + _hostIP);
			throw new UnknownHostException("Unknown Host: " + _hostIP + " " + e2.getMessage());
			
		} catch (IOException e3) {
			System.out.println("Socket-Fehler: (" + _hostIP + ":" + nPort + " / " + e3.getMessage() +  ")");
			throw new IOException("Socket-Fehler! (" + _hostIP + ":" + nPort + " / " + e3.getMessage() +  ")", e3.getCause());
		}
	}

	
	/**
	 * führt alle Kommandos aus
	 * 
	 * @author manfred
	 * @date 24.11.2016
	 *
	 * @return
	 * @throws Exception
	 */
	public boolean readWUT() throws Exception {
		boolean bRet = true;
		String line = "";
		
		for (WUT_Command cmd: _vCommands){
			// Receive text from server
			line = "";
			try {
				// neu an den Socket binden
				this.listenSocket();
		
	
					// schicken des Kommandos zum Server
					m_socket_writer.print(getCommandString(cmd));
					m_socket_writer.flush();
					
					
					try {
						   int sign;
						   while ((sign = m_socket_reader.read()) != 0) { 
							   	if(sign!=0){
							   		
							   		line = line + (char)sign;
							   	
							   	}
						   }  
						}catch (IOException e) {
						}

					
					
					// lesen des Rückgabewertes
//					line = m_socket_reader.readLine();

					// Result setzen und parsen...
					setCommandResult(cmd, line);

			
			} catch (IOException e) {
				bRet = false;
			} finally{
				
				if (m_socket_reader != null ) m_socket_reader.close();
				if (m_socket_writer != null ) m_socket_writer.close();
				if (m_ip_socket != null ) m_ip_socket.close();
			}
		}

		return bRet;

	}
	
	
	
}
