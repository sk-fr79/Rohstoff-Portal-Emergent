package rohstoff.Echo2BusinessLogic.FIBU;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_LIST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Info;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Info_Entry;

public class FIBU_AUSWERTE_BLOCK extends MyE2_Grid
{
	
	private E2_NavigationList  		oNaviList = null;
	private GridLayoutData 			oGLZahl = null;
	
	private MyE2_Grid				oGridHelp = new MyE2_Grid(2);  		
	private FIBU_LIST_Selector   	oFIBU_LIST_Selector = null;
	
	public FIBU_AUSWERTE_BLOCK(E2_NavigationList NaviList, FIBU_LIST_Selector FIBU_LIST_Selector)
	{
		super(2);
		
		this.add(oGridHelp,E2_INSETS.I_0_0_0_0);
		
		this.oNaviList = NaviList;
		this.oFIBU_LIST_Selector = FIBU_LIST_Selector;
		
		
		this.oGLZahl = new GridLayoutData();
		oGLZahl.setInsets(E2_INSETS.I_2_2_10_2);
		oGLZahl.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
	
		
		
		
	}

	
	public void fill_Block() throws myException
	{
		oGridHelp.removeAll();
		

		if (this.oNaviList.get_vIDs_From_Search().size()>0)
		{
			oGridHelp.add(new MyE2_Label(new MyE2_String("Bitte die Suche leeren ...")), E2_INSETS.I_2_2_2_2);
			return;
		}
		
		// die abfrage fuer die ID-Liste besorgen
		String cSQL_ID_FIBU = this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_CompleteSQLQueryFor_ID_VECTOR("",true);
		if (cSQL_ID_FIBU.toUpperCase().indexOf("ORDER BY")>=0)
		{
			cSQL_ID_FIBU = cSQL_ID_FIBU.substring(0, cSQL_ID_FIBU.toUpperCase().indexOf("ORDER BY"));
		}
		
		String cSQL_Ganz = 			 "SELECT WAEHRUNG_FREMD, SUM(FAKTOR_BUCHUNG_PLUS_MINUS*ZAHLUNGSBETRAG_FREMD_WAEHRUNG) AS SUMME FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_FIBU IN ("+cSQL_ID_FIBU+") " +
											" GROUP BY WAEHRUNG_FREMD";
		
		String cSQL_GanzInclSkonto = "SELECT WAEHRUNG_FREMD, " +
											"SUM(" +
											" CASE WHEN NVL(JT_FIBU.SKONTO_DATUM_UEBERSCHRITTEN,'N')='Y' THEN "+
											 " (NVL(JT_FIBU.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)+NVL(JT_FIBU.SKONTOBETRAG_FREMD_WAEHRUNG,0))*JT_FIBU.FAKTOR_BUCHUNG_PLUS_MINUS "+
											 " ELSE "+
											 " (NVL(JT_FIBU.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)*JT_FIBU.FAKTOR_BUCHUNG_PLUS_MINUS) "+
										 " END " +
											") AS SUMME FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_FIBU IN ("+cSQL_ID_FIBU+") " +
											" GROUP BY WAEHRUNG_FREMD";
		
		
		
		MyRECORD_LIST  recListWerte = 			new MyRECORD_LIST(cSQL_Ganz,          "WAEHRUNG_FREMD");
		MyRECORD_LIST  recListWerteInclSkonto = new MyRECORD_LIST(cSQL_GanzInclSkonto,"WAEHRUNG_FREMD");
		
		
		
		//jetzt nachsehen, ob in der Selektion eine Firma ausgewaehlt ist: wenn ja, muss das kreditlimit dieser firma angezeigt werden
		if (S.isFull(this.oFIBU_LIST_Selector.get_oSelKundenMitPositionen().get_ActualWert()))
		{
			oGridHelp.setSize(4);
			oGridHelp.add(new MyE2_Label(new MyE2_String("Bewertung der momentanen Auswahl: "),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),4,E2_INSETS.I_2_2_2_2);

			
			for (Entry<String, MyRECORD> oEntry: recListWerte.entrySet())
			{
				oGridHelp.add(new MyE2_Label(new MyE2_String("Summe: ",true,oEntry.getValue().get_UnFormatedValue("WAEHRUNG_FREMD", ""),false)), E2_INSETS.I_2_2_10_2);
				
				String     cHASH = oEntry.getKey();
				BigDecimal bdSumme = oEntry.getValue().get_bdValue("SUMME", BigDecimal.ZERO, 2);
				
				//dann die summe mit skonto-korrektur einbeziehen und auch anzeigen
				BigDecimal bdSummeSkonto = recListWerteInclSkonto.get(cHASH).get_bdValue("SUMME", BigDecimal.ZERO, 2);
				
				MyE2_Label oLabelSumme = 		new MyE2_Label(MyNumberFormater.formatDez(bdSumme,2,true),		MyE2_Label.STYLE_NORMAL_BOLD());
				MyE2_Label oLabelSummeSkonto =	new MyE2_Label(MyNumberFormater.formatDez(bdSummeSkonto,2,true),MyE2_Label.STYLE_NORMAL_BOLD());
				
				oLabelSumme.setToolTipText(new MyE2_String("Summe der Spalte <Betrag (FW)> OHNE Berücksichtigung von überschrittenen Skonto-Abzügen!").CTrans());
				oLabelSummeSkonto.setToolTipText(new MyE2_String("Summe der Spalte <Betrag (FW)> MIT Einbeziehung von überschrittenen Skonto-Abzügen!").CTrans());
				
				if (bdSumme.compareTo(bdSummeSkonto)==0)
				{
					oGridHelp.add(oLabelSumme, this.oGLZahl);
				}
				else
				{
					GridLayoutData glHelp = new GridLayoutData();
					glHelp.setInsets(E2_INSETS.I_0_0_2_0);
					glHelp.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));

					oGridHelp.add(new E2_ComponentGroupHorizontal_NG(oLabelSumme,new MyE2_Label("("),oLabelSummeSkonto,new MyE2_Label(")"),glHelp), this.oGLZahl);
				}
				
				oGridHelp.add(new MyE2_Label(" "), E2_INSETS.I_2_2_10_2);
				oGridHelp.add(new MyE2_Label(" "), E2_INSETS.I_2_2_10_2);
			}

			
			//hier die kreditlimit-eintraege und die gueltigkeiten
			RECORD_ADRESSE 		recAdresse = new RECORD_ADRESSE(this.oFIBU_LIST_Selector.get_oSelKundenMitPositionen().get_ActualWert());
			RECORD_FIRMENINFO   recFirmeninfo = recAdresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
			
			/*
			oGridHelp.add(new MyE2_Label(new MyE2_String("Kreditlimit: ")), E2_INSETS.I_2_2_10_2);
			oGridHelp.add(new MyE2_Label(
					MyNumberFormater.formatDez(recFirmeninfo.get_KREDITLIMIT_bdValue(BigDecimal.ZERO),2,true), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);

			oGridHelp.add(new MyE2_Label(recFirmeninfo.get_KREDITLIMIT_VON_cF_NN("--"), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
			oGridHelp.add(new MyE2_Label(recFirmeninfo.get_KREDITLIMIT_BIS_cF_NN("--"), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);

			if (recFirmeninfo.get_KREDITLIMIT2_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)!=0)
			{
				oGridHelp.add(new MyE2_Label(new MyE2_String("Kreditlimit: ")), E2_INSETS.I_2_2_10_2);
				oGridHelp.add(new MyE2_Label(
						MyNumberFormater.formatDez(recFirmeninfo.get_KREDITLIMIT2_bdValue(BigDecimal.ZERO),2,true), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);

				oGridHelp.add(new MyE2_Label(recFirmeninfo.get_KREDITLIMIT2_VON_cF_NN("--"), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
				oGridHelp.add(new MyE2_Label(recFirmeninfo.get_KREDITLIMIT2_BIS_cF_NN("--"), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
			}
			
			if (recFirmeninfo.get_KREDITLIMIT3_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)!=0)
			{
				oGridHelp.add(new MyE2_Label(new MyE2_String("Kreditlimit: ")), E2_INSETS.I_2_2_10_2);
				oGridHelp.add(new MyE2_Label(
						MyNumberFormater.formatDez(recFirmeninfo.get_KREDITLIMIT3_bdValue(BigDecimal.ZERO),2,true), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);

				oGridHelp.add(new MyE2_Label(recFirmeninfo.get_KREDITLIMIT3_VON_cF_NN("--"), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
				oGridHelp.add(new MyE2_Label(recFirmeninfo.get_KREDITLIMIT3_BIS_cF_NN("--"), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
			}
			*/
			
			
			// Manfred 2011-10-26: Anpassung an die neue Kreditversicherungs-Verwaltung
			KV_Info oKV_Info = new KV_Info();
			Vector<KV_Info_Entry> vInfo = oKV_Info.getKreditlimitsFor(recAdresse.get_ID_ADRESSE_cUF());
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			
			if (vInfo != null && vInfo.size() > 0){
				for(KV_Info_Entry entry : vInfo){
					if (bibALL.isEmpty(entry.get_Description())){
						oGridHelp.add(new MyE2_Label(new MyE2_String("Kreditlimit: ")), E2_INSETS.I_2_2_10_2);
					} else {
						oGridHelp.add(new MyE2_Label(entry.get_Description() + ":"), E2_INSETS.I_2_2_10_2);
					}
					oGridHelp.add(new MyE2_Label(MyNumberFormater.formatDez( (entry.get_Betrag() != null ? entry.get_Betrag() : BigDecimal.ZERO ) ,2,true), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
					oGridHelp.add(new MyE2_Label((entry.get_Gueltig_ab() != null ?  df.format(entry.get_Gueltig_ab()) : "--"), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
					oGridHelp.add(new MyE2_Label((entry.get_Gueltig_bis() != null ? df.format(entry.get_Gueltig_bis()) : "--"), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
				}
			} else {
				oGridHelp.add(new MyE2_Label(new MyE2_String("Kreditlimit: ")), E2_INSETS.I_2_2_10_2);
				oGridHelp.add(new MyE2_Label("0", MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
				oGridHelp.add(new MyE2_Label("--", MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
				oGridHelp.add(new MyE2_Label("--", MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
			}
			// ENDE Anpassungen
			
		}
		else
		{
			oGridHelp.setSize(2);
			oGridHelp.add(new MyE2_Label(new MyE2_String("Bewertung der momentanen Auswahl: "),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),2,E2_INSETS.I_2_2_2_2);

			
			for (Entry<String, MyRECORD> oEntry: recListWerte.entrySet())
			{
				oGridHelp.add(new MyE2_Label(new MyE2_String("Summe: ",true,oEntry.getValue().get_UnFormatedValue("WAEHRUNG_FREMD", ""),false)), E2_INSETS.I_2_2_10_2);
				oGridHelp.add(new MyE2_Label(new MyE2_String(MyNumberFormater.formatDez(oEntry.getValue().get_bdValue("SUMME", BigDecimal.ZERO, 2),2,true),false), MyE2_Label.STYLE_NORMAL_BOLD()), this.oGLZahl);
			}
			
		}
		
		
		
	}
	
}
