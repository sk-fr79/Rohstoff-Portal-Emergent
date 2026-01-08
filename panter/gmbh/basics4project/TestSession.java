package panter.gmbh.basics4project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static org.easymock.EasyMock.*;

import org.easymock.IAnswer;
import org.easymock.IExpectationSetters;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Class to be uses in tests, to make the database connection available to the
 * session. Usage:
 * 
 * ts = new TestSession("username", "password");
 * 
 * @author nils, 27.02.2015
 * 
 */
public class TestSession {
	// Session aufbauen, damit die DB-Verbindung korrekt initialisiert wird und
	// der Benutzer dem Mandanten richtig zugeordnet wird.

	/*
	 * private ServletContext ctx;
	 * 
	 * public ServletContext getContext() { return ctx; }
	 * 
	 * public void setContext(ServletContext ctx) { this.ctx = ctx; }
	 * 
	 * 
	 * public TestSession(String username, String password) throws Exception {
	 * HttpSession ses = new HttpSession() { private HashMap<String, Object>
	 * attributes = new HashMap<String, Object>();
	 * 
	 * @Override public void setMaxInactiveInterval(int arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void setAttribute(String arg0, Object arg1) { // TODO
	 * Auto-generated method stub attributes.put(arg0, arg1); }
	 * 
	 * @Override public void removeValue(String arg0) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void removeAttribute(String arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void putValue(String arg0, Object arg1) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public boolean isNew() { // TODO Auto-generated method stub
	 * return false; }
	 * 
	 * @Override public void invalidate() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public String[] getValueNames() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public Object getValue(String arg0) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public HttpSessionContext getSessionContext() { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public ServletContext getServletContext() { return ctx; }
	 * 
	 * @Override public int getMaxInactiveInterval() { // TODO Auto-generated
	 * method stub return 0; }
	 * 
	 * @Override public long getLastAccessedTime() { // TODO Auto-generated
	 * method stub return 0; }
	 * 
	 * @Override public String getId() { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * @Override public long getCreationTime() { // TODO Auto-generated method
	 * stub return 0; }
	 * 
	 * @Override public Enumeration getAttributeNames() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public Object getAttribute(String key) { return
	 * attributes.get(key); }
	 * 
	 * }; bibE2.setSessionManually(ses);
	 * 
	 * HttpServletRequest request = new HttpServletRequest() {
	 * 
	 * @Override public void setCharacterEncoding(String arg0) throws
	 * UnsupportedEncodingException { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void setAttribute(String arg0, Object arg1) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void removeAttribute(String arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public boolean isSecure() { // TODO Auto-generated method stub
	 * return false; }
	 * 
	 * @Override public int getServerPort() { // TODO Auto-generated method stub
	 * return 0; }
	 * 
	 * @Override public String getServerName() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getScheme() { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * @Override public RequestDispatcher getRequestDispatcher(String arg0) { //
	 * TODO Auto-generated method stub return null; }
	 * 
	 * @Override public int getRemotePort() { // TODO Auto-generated method stub
	 * return 0; }
	 * 
	 * @Override public String getRemoteHost() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getRemoteAddr() { // TODO Auto-generated method
	 * stub return "192.168.0.1"; }
	 * 
	 * @Override public String getRealPath(String arg0) { String s =
	 * TestSession.class.getClassLoader().getResource(".") .toString(); s =
	 * s.substring(5); // Strip file: s = s.substring(0, s.indexOf("/WEB-INF/")
	 * + 9); return s; }
	 * 
	 * @Override public BufferedReader getReader() throws IOException { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public String getProtocol() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String[] getParameterValues(String arg0) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Enumeration getParameterNames() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public Map getParameterMap() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getParameter(String arg0) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Enumeration getLocales() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public Locale getLocale() { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * @Override public int getLocalPort() { // TODO Auto-generated method stub
	 * return 0; }
	 * 
	 * @Override public String getLocalName() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getLocalAddr() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public ServletInputStream getInputStream() throws IOException {
	 * // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public String getContentType() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public int getContentLength() { // TODO Auto-generated method
	 * stub return 0; }
	 * 
	 * @Override public String getCharacterEncoding() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public Enumeration getAttributeNames() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public Object getAttribute(String arg0) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public boolean isUserInRole(String arg0) { // TODO
	 * Auto-generated method stub return false; }
	 * 
	 * @Override public boolean isRequestedSessionIdValid() { // TODO
	 * Auto-generated method stub return false; }
	 * 
	 * @Override public boolean isRequestedSessionIdFromUrl() { // TODO
	 * Auto-generated method stub return false; }
	 * 
	 * @Override public boolean isRequestedSessionIdFromURL() { // TODO
	 * Auto-generated method stub return false; }
	 * 
	 * @Override public boolean isRequestedSessionIdFromCookie() { // TODO
	 * Auto-generated method stub return false; }
	 * 
	 * @Override public Principal getUserPrincipal() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public HttpSession getSession(boolean arg0) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public HttpSession getSession() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getServletPath() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getRequestedSessionId() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public StringBuffer getRequestURL() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public String getRequestURI() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getRemoteUser() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getQueryString() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getPathTranslated() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public String getPathInfo() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getMethod() { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * @Override public int getIntHeader(String arg0) { // TODO Auto-generated
	 * method stub return 0; }
	 * 
	 * @Override public Enumeration getHeaders(String arg0) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Enumeration getHeaderNames() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public String getHeader(String arg0) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public long getDateHeader(String arg0) { // TODO Auto-generated
	 * method stub return 0; }
	 * 
	 * @Override public Cookie[] getCookies() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getContextPath() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getAuthType() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * 
	 * };
	 * 
	 * 
	 * ServletContext ctx = new ServletContext() {
	 * 
	 * @Override public void setAttribute(String arg0, Object arg1) {
	 * attributes.put(arg0, arg1); }
	 * 
	 * @Override public void removeAttribute(String arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void log(String arg0, Throwable arg1) {
	 * System.out.println(arg0); System.out.println(arg1.toString());
	 * arg1.printStackTrace(); }
	 * 
	 * @Override public void log(Exception arg0, String arg1) {
	 * System.out.println(arg1); System.out.println(arg0.toString());
	 * arg0.printStackTrace();
	 * 
	 * }
	 * 
	 * @Override public void log(String arg0) { System.out.println(arg0); }
	 * 
	 * @Override public Enumeration getServlets() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public Enumeration getServletNames() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public String getServletContextName() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public Servlet getServlet(String arg0) throws ServletException
	 * { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public String getServerInfo() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public Set getResourcePaths(String arg0) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public InputStream getResourceAsStream(String arg0) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public URL getResource(String arg0) throws
	 * MalformedURLException { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public RequestDispatcher getRequestDispatcher(String arg0) { //
	 * TODO Auto-generated method stub return null; }
	 * 
	 * @Override public String getRealPath(String arg0) { String s =
	 * TestSession.class.getClassLoader().getResource(".") .toString();
	 * 
	 * s = s.substring(5); // Strip file: s = s.substring(0,
	 * s.indexOf("/WEB-INF/") + 0); // new
	 * NullPointerException("Blöde").printStackTrace(); return s; }
	 * 
	 * @Override public RequestDispatcher getNamedDispatcher(String arg0) { //
	 * TODO Auto-generated method stub return null; }
	 * 
	 * @Override public int getMinorVersion() { // TODO Auto-generated method
	 * stub return 0; }
	 * 
	 * @Override public String getMimeType(String arg0) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public int getMajorVersion() { // TODO Auto-generated method
	 * stub return 0; }
	 * 
	 * @Override public Enumeration getInitParameterNames() { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public String getInitParameter(String arg0) { return
	 * ip.get(arg0); }
	 * 
	 * @Override public String getContextPath() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public ServletContext getContext(String arg0) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Enumeration getAttributeNames() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * private HashMap<String, Object> attributes = new HashMap<String,
	 * Object>();
	 * 
	 * @Override public Object getAttribute(String key) {
	 * 
	 * return attributes.get(key); }
	 * 
	 * 
	 * }; setContext(ctx);
	 */


	public TestSession(String username, String password) throws Exception {

		HashMap<Object, Object> sessAttr = new HashMap<Object, Object>();

		HttpSession session = createMock(HttpSession.class);
		expect(session.getId()).andReturn("1").anyTimes();

		// expect(ses2.setAttribute("__JIL_VALUES", o));
		/*
		 * expect(ses2.setAttribute("_JIL_VALUES", new Object())).andAnswer(new
		 * IAnswer<Object>() { public Object answer() throws Throwable { return
		 * getCurrentArguments()[0].toString(); }});
		 */

		// session.setAttribute(eq("__JIL_VALUES"), isA(Object.class));
		// expect(session.getAttribute("__JIL_VALUES")).andReturn(null);

		HashMap<Object, Object> createdObject = new HashMap<Object, Object>();
		session.setAttribute("__JIL_VALUES", createdObject);

		expect(session.getAttribute("__JIL_VALUES")).andReturn(createdObject)
				.anyTimes();

		/*
		 * expectLastCall().andAnswer(new IAnswer<HashMap>() { public HashMap
		 * answer() throws Throwable { createdObject = (HashMap)
		 * EasyMock.getCurrentArguments()[1]; return null; } });
		 */
		// replay(session);

		bibE2.setSessionManually(session);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		expect(request.getSession()).andReturn(session);
		ServletContext context = createMock(ServletContext.class);

		context.log(anyString());
		expectLastCall().times(10); 

		HashMap<String, WeakReference<HttpSession>> au = new HashMap<String, WeakReference<HttpSession>>();
		context.setAttribute("applications.users", au);
		expect(context.getAttribute("applications.users")).andReturn(au)
				.anyTimes();
		// expect(context.log(anyString())).andReturn(null).anyTimes();

		expect(request.getRemoteAddr()).andReturn("127.0.0.1").anyTimes();
		expect(context.getRealPath("")).andAnswer(new IAnswer<String>() {
			public String answer() throws Throwable {
				String s = TestSession.class.getClassLoader().getResource(".")
						.toString();
				s = s.substring(5);
//				s = s.substring(0, s.indexOf("/WEB-INF/") + 9);
				s = s.substring(0, s.indexOf("/WEB-INF/") + 1);
				return s;
			}
		}).anyTimes();

		
		expect(context.getInitParameter(anyString())).andAnswer(new IAnswer<String>() {
			public String answer() throws Throwable {
				return ip.get(getCurrentArguments()[0].toString());
			}
		}).anyTimes();
		
		session.setMaxInactiveInterval(anyInt());
		expectLastCall().anyTimes();
		
		session.setAttribute(anyString(), anyString());
		session.setAttribute(anyString(), anyString());
		session.setAttribute(anyString(), anyString());
		
		
		expect(session.getMaxInactiveInterval()).andReturn(1000000).anyTimes();
		
		replay(session);
		replay(context);
		replay(request);

		createSession oSES_Cr = new createSession(username, password,
		// bibE2.get_CurrSession(),
				session, context, request);
		
		//System.out.println("WRP="+bibALL.get_WEBROOTPATH());

		WriteTimeStamp();
	}

	static HashMap<String, String> ip = new HashMap<String, String>();
	static {
		try {

			File stocks = new File(getWebInfPath() + "web.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();

			System.out.println("root of xml file"
					+ doc.getDocumentElement().getNodeName());

			NodeList nodes = doc.getElementsByTagName("context-param");
			System.out.println("==========================");

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					ip.put(getValue("param-name", element),
							getValue("param-value", element));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String getWebInfPath() {
		String s = TestSession.class.getClassLoader().getResource(".")
				.toString();
		s = s.substring(5); // Strip file:
		s = s.substring(0, s.indexOf("/WEB-INF/") + 9);
		return s;
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		if (node == null)
			return "";
		return node.getNodeValue();
	}
	
	/**
	 * Hilfsmethode zum schreiben eines Timestamps für die Sicherstellung der Verarbeitung der Daten
	 * @throws myException
	 */
	private void WriteTimeStamp()throws myException{
		String cStamp = bibDB.EinzelAbfrage("SELECT "+DB_META.ORA_TIMESTAMP_MILLISECS+" FROM DUAL");
		if (cStamp.startsWith("2"))                 //sicherheitsabfrage, gilt bis ins Jahr 2999, dann beginnt der stamp mit 3
		{
			bibE2.set_LAST_DB_TIMESTAMP(cStamp);
			DEBUG.System_println("DB-Timestamp geschrieben:   ...: " + cStamp + "  <Batchverarbeitung>", DEBUG.DEBUG_FLAG_SQL_TIMESTAMP);
		}
		else
		{
			throw new myException(this,"Error with Timestamp !!");
		}
	}

}
