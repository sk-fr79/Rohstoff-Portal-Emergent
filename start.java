
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import panter.gmbh.indep.bibALL;




/*
 * 
 * servlet nur zum einloggen, aufbauen der umgebung und dann weitergeben an eine
 * fehlermeldung oder an die echo-starterklasse  
 * 
 *  
 */

public class start extends HttpServlet
{
    public void init() throws ServletException
    {
    }

    public void destroy()
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)   throws IOException, ServletException
    {
    	
//    	String cTest = "23323";
//    	java.lang.CharSequence
  	
//    	//test
//    	Vector<String>  v1 = new Vector<String>();
//    	Vector<String>  v2 = new Vector<String>();
//    	v1.add("A");
//    	v1.add("B");
//    	v1.add("C");
//    	
//    	v2.add("1");
//    	v2.add("B");
//    	v2.add("2");
//    	v2.add("3");
//    	
//    	List<String>  v3 = ListUtils.retainAll(v1, v2);
//    	
//    	for (int i=0;i<v3.size();i++)
//    	{
//    		System.out.println(v3.get(i));
//    	}
//    	System.out.println("----------------------");
//    	for (int i=0;i<v1.size();i++)
//    	{
//    		System.out.println(v1.get(i));
//    	}
//    	System.out.println("----------------------");
//    	for (int i=0;i<v2.size();i++)
//    	{
//    		System.out.println(v2.get(i));
//    	}
//    	
    	
//    	System.out.println(myCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString());
//    	System.out.println(myCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString());
//    	System.out.println(myCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString());
    	
   
    	//new java.text.SimpleDateFormat().format(new java.util.Date());
    	
    	
        /*
         * hier auf jeden fall eine neue session eroeffnen
         */
        HttpSession SES = request.getSession(true);
        System.out.println("start::doGet::request.getSession()...bestehende Session..." + SES.getId());
        
        if (! SES.isNew())
        {
        	try
        	{
        		bibALL.CloseConnection_In_SESSION(SES);   // falls die session bereits aktiv war, dann die connections zur datenbank schliessen
        	}
        	catch (Exception ex)
        	{}
        	
            SES.invalidate();
            SES = request.getSession(true);
            System.out.println("start::doGet::request.getSession()...neue Session..." + SES.getId());
        }
        
        
        // dann wird über einen request-dispatcher das servlet sl_makeHTMLFrames gerufen
       	RequestDispatcher oDP = getServletContext().getRequestDispatcher("/servlet/panter/gmbh/basics4project/E2_Container");

       	String cAblaufFehler = "";
       	
        if (oDP == null)
        {
            cAblaufFehler = "Es konnte kein RequestDispatcher aufgebaut werden !!";
        }
        else
        {
            oDP.forward(request, response);
        }

        // falls ein fehler aufgetreten ist, dies jetzt melden
        // zum beispiel falsches passwort
        if (!cAblaufFehler.equals(""))
        {
        	response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(this.cHTML_BaueFehlerMeldung(cAblaufFehler));
            SES.invalidate(); // session abschiessen
        }

        System.gc();
        
        
    }

  
    
    
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
    
    
    
    
    private String cHTML_BaueFehlerMeldung(String cError)
    {
        String cHTML = "";
         cHTML +="<HTML>"+"\n";
         cHTML +="<HEAD>"+"\n";
         cHTML +="<TITLE>Blank</TITLE>"+"\n";
         cHTML +="<STYLE TYPE=\"text/css\">"+"\n";
         cHTML +="BODY {font-family: sans-serif; font-size: 100%; Background-color: #DCDCDC;}"+"\n";
         cHTML +="INPUT {font-family: sans-serif; font-size: 100%; border-style: none; }"+"\n";
         cHTML +="BUTTON {font-family: sans-serif ; font-size: 100%; text-align: center;}"+"\n";
         cHTML +="TABLE  {font-family: sans-serif; font-size: 100%; text-align: left;  background-color: #DCDCDC; border-style: none;}"+"\n";
         cHTML +="H1 {font-family: sans-serif; font-size: 130%; text-align: left; }"+"\n";
         cHTML +="A  {text-decoration: none;}"+"\n";
         cHTML +="</STYLE>"+"\n";
         cHTML +="</HEAD>"+"\n";

        cHTML +="<BODY>"+"\n";
//        cHTML +="<P><IMG SRC=\"../icons/portallogo.png\"  HSPACE=\"0\" VSPACE=\"0\" BORDER=\"0\"></P>"+"\n";
        cHTML +="<H1>Anmeldung :</H1>"+"\n";
        cHTML +="<FORM name=\"login\">"+"\n";
        cHTML +="<TABLE WIDTH=\"250\" BORDER=\"0\">"+"\n";
        cHTML +="<TR>"+"\n";
        cHTML +="<TD WIDTH=\"100\">"+ cError+ "</TD>"+"\n";
        cHTML +="</TR>"+"\n";
        cHTML +="<TR>"+"\n";
        cHTML +="<TD>&nbsp;</TD>"+"\n";
        cHTML +="</TR>"+"\n";
        cHTML +="<TR>"+"\n";
        cHTML +="<TD WIDTH=\"100\"><INPUT style=\"font-size: 12pt; color: black; background-color: gainsboro;border-color: black;  border-top-width: 1px; border-left-width: 1px;border-bottom-width: 2px;border-right-width: 2px;border-style:solid; text-align: center; padding-left: 5px; padding-bottom: 0px;padding-right: 5px;padding-top: 0px;\" TYPE=\"BUTTON\"    NAME=\"Start\" VALUE=\"Fenster schliessen\" onclick=\"window.close();\" SIZE=\"12\">"+"\n";
        cHTML +="</TD>"+"\n";
        cHTML +="</TR>"+"\n";
        cHTML +="</TABLE>"+"\n";
        cHTML +="</FORM>"+"\n";
        cHTML +="</BODY>"+"\n";
        cHTML +="</HTML>"+"\n";
        
        
        return cHTML;
        
    }
    
    
    
    
}
