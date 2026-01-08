package panter.gmbh.basics4project;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author manfred
 *
 */
public class sessionContainer {
	private HttpSession m_Session = null;
	
	public void setSession(HttpSession ses) throws Exception{
		m_Session = ses;
	}
	
	public HttpSession getSession(){
		return m_Session;
	}
}
