package panter.gmbh.indep;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class xmlTool
{
    public String[][] cWerte = new String[100][2];

    public xmlTool()
    {
        // konstruktor initialisiert das array  
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                cWerte[i][j] = "";
            }
        }
    }

    /*
       methode ist dazu da, eine konfiguration zu definieren und diese in eine
       xml-datei namens webconf.xml zu schreiben.
       in dieser datei werden die folgenden parameter definiert

       ( kann jederzeit verlängert werden):
        <element>           <beispiel>
        datenbank-server :  192.168.0.222
        oracle-sid       :  ORCL
        java-login       :  abfrage
        java-passwort    :  leber
        base-color       :  blue
     */
    public boolean createConfigFile()
    {
        boolean bRueck = true;

        Document document = DocumentHelper.createDocument();

        //        Element root = document.addElement( "web-applikation" );
        //        Element e1 = root.addElement("dbkennung" ).addText( "SAPDB7" );
        //        Element e3 = root.addElement("jdbc-string" ).addText( "jdbc:sapdb://lxdb/PROVER" );
        //        Element e5 = root.addElement("java-login").addText("prover");
        //        Element e6 = root.addElement("java-passwort").addText("prover");
        //        Element e7 = root.addElement("base-color").addText("66CCFF");
        //        Element e8 = root.addElement("light-color").addText("66FFF");
        //        Element e9 = root.addElement("mid-color").addText("66CCFF");
        //        Element e10 = root.addElement("dark-color").addText("6699FF");
        //        Element e11 = root.addElement("table-owner").addText("prover");
        //        Element e12 = root.addElement("font-size").addText("100");
        //        Element e13 = root.addElement("font-size-plus").addText("110");
        //        Element e14 = root.addElement("font-size-pplus").addText("120");
        //        Element e15 = root.addElement("font-size-minus").addText("90");
        //        Element e16 = root.addElement("font-size-mminus").addText("80");
        document.normalize();

        try
        {
            FileWriter out = new FileWriter("/tmp/web-applikation.xml");
            document.write(out);
            out.close();
        }
        catch (IOException e)
        {
            bRueck = false;
        }

        return bRueck;
    }

    // liest ein beliebiges xml-file ein und gibt die ersten 100
    // werte bekannt (in das public array cWerte)
    // der filename wird hier in eine url umgesetzt
    public void LeseEin(String pFile)
    {
        // array vorher initialisieren
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                cWerte[i][j] = "";
            }
        }

        try
        {
            URL url = new URL("file://" + pFile);
            this.parse(url);
        }
        catch (Exception e)
        {
            // im fehlerfall ist einfach alles leer  
            for (int i = 0; i < 100; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    cWerte[i][j] = "";
                }
            }
        }
    }

    // liest ein beliebiges xml-URL ein und gibt die ersten 100
    public void LeseEin(URL pURL)
    {
        // array vorher initialisieren
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                cWerte[i][j] = "";
            }
        }

        try
        {
            this.parse(pURL);
        }
        catch (Exception e)
        {
            // im fehlerfall ist einfach alles leer  
            for (int i = 0; i < 100; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    cWerte[i][j] = "";
                }
            }
        }
    }

    // liest eine 2x2 - matrix von bis zu 100 einstellungen 
    // ein und gibt diese als string-array zurück
    // übergeben wird ein dateiname
    private void parse(URL pUrl) throws DocumentException
    {
        int iZahl = 0;

        SAXReader reader = new SAXReader();
        Document document = reader.read(pUrl);

        Element root = document.getRootElement();

        // iterate through child elements of root
        // alle root-childs auslesen und in das array kopieren
        for (Iterator i = root.elementIterator(); i.hasNext();)
        {
            Element element = (Element) i.next();
            cWerte[iZahl][0] = element.getQualifiedName();
            cWerte[iZahl][1] = element.getStringValue();
            iZahl++;

            if (iZahl == 100)
            {
                break;
            }
             // nur 100
        }
    }

    // suche nach einem wert implements eingelesenen array
    public String SucheWert(String pName)
    {
        String cRueck = "";

        for (int i = 0; i < 100; i++)
        {
            if (cWerte[i][0].trim().equals(pName.trim()))
            {
                cRueck = cWerte[i][1];

                break;
            }
        }

        return (cRueck);
    }

    // suche nach einem wert implements eingelesenen array
    // wobei der name in grossschreibung verglichen wird
    public String SucheWertUP(String pName)
    {
        String cRueck = "";

        for (int i = 0; i < 100; i++)
        {
            if (cWerte[i][0].toUpperCase().trim().equals(pName.toUpperCase().trim()))
            {
                cRueck = cWerte[i][1];

                break;
            }
        }

        return (cRueck);
    }
}
