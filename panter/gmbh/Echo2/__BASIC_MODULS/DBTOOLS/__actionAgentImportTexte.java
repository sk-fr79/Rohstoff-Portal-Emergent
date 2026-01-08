package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TEMP_TEXT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class __actionAgentImportTexte extends XX_ActionAgent
{
	
	private MyE2_Grid oGridServerAnzeige = new MyE2_Grid(1);




	private int       iCountAlleGeprueften = 				0;
	private int       iCountFirmaNichtGefunden = 		0;
	private int       iCountErfolg = 						0;
	
	
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	{
		
		new E2_ServerPushMessageContainer_STD(new Extent(400),new Extent(400),new MyE2_String("Textimport"),false,true,6000,this.oGridServerAnzeige,E2_INSETS.I_5_5_5_5) 
		{
			@Override
			public void Run_Loop() throws myException 
			{
				VectorSingle		vTextFehler = 		new VectorSingle();
				int 			iAnzahl = 			0;
				
				iCountAlleGeprueften = 				0;
				iCountErfolg =						0;
				
				
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				
				//zuerst die Bausteine identifizieren
				String[][] cKenn_baustein = bibDB.EinzelAbfrageInArray("SELECT DISTINCT KENNUNG,BAUSTEIN_NR FROM JT_TEMP_TEXT");
				
				
				iAnzahl = cKenn_baustein.length;
				
				
				for (int i=0;i<iAnzahl;i++)
				{
					
					iCountAlleGeprueften = i;
					
					RECLIST_TEMP_TEXT reclistText = new RECLIST_TEMP_TEXT(
							"SELECT * FROM JT_TEMP_TEXT WHERE KENNUNG="+bibALL.MakeSql(cKenn_baustein[i][0])+
														" AND BAUSTEIN_NR="+bibALL.MakeSql(cKenn_baustein[i][1])+" ORDER BY LFD_NR");
					
					
					String cTextblock = "";
					
					for (int k=0;k<reclistText.get_vKeyValues().size();k++)
					{
						if (k==(reclistText.get_vKeyValues().size()-1))
						{
							cTextblock=cTextblock+reclistText.get(k).get_TEXT_cUF_NN("");  //letzte zeile ohne CR
						}
						else
						{
							cTextblock=cTextblock+reclistText.get(k).get_TEXT_cUF_NN("")+"\n";
						}
					}

					
					
					RECLIST_ADRESSE  reclistAdresse = null;
					
					if (cKenn_baustein[i][0].equals("ABN_BEM"))
					{
						reclistAdresse = new RECLIST_ADRESSE("SELECT * FROM JT_ADRESSE WHERE ABN_NR="+bibALL.MakeSql(cKenn_baustein[i][1]));
					}
					else
					{
						reclistAdresse = new RECLIST_ADRESSE("SELECT * FROM JT_ADRESSE WHERE LIEF_NR="+bibALL.MakeSql(cKenn_baustein[i][1]));
					}						
					
					
					if (reclistAdresse.get_vKeyValues().size()==0)
					{
						__actionAgentImportTexte.this.iCountFirmaNichtGefunden++;
						vTextFehler.add("Lieferant nicht gefunden: "+cKenn_baustein[i][0]+" : " +cKenn_baustein[i][1]);
					}
					else
					{
						for (int l=0;l<reclistAdresse.get_vKeyValues().size();l++)
						{
							RECORD_ADRESSE  recAdresse = reclistAdresse.get(l);

							RECORDNEW_ADRESSE_INFO  recNewInfo = new RECORDNEW_ADRESSE_INFO();
							recNewInfo.set_NEW_VALUE_ID_ADRESSE(recAdresse.get_ID_ADRESSE_cUF());
							recNewInfo.set_NEW_VALUE_TEXT(cTextblock);
							recNewInfo.set_NEW_VALUE_IST_MESSAGE("Y");
							
							if (cKenn_baustein[i][0].equals("ABN_BEM"))
							{
								recNewInfo.set_NEW_VALUE_MESSAGE_TYP(myCONST.ADRESS_INFO_TYP_VERKAUF);
							}
							else
							{
								recNewInfo.set_NEW_VALUE_MESSAGE_TYP(myCONST.ADRESS_INFO_TYP_EINKAUF);
							}
							
							MyE2_MessageVector  oMV1 = new MyE2_MessageVector();
							recNewInfo.do_WRITE_NEW_ADRESSE_INFO(oMV1);
							
							if (oMV1.get_bIsOK())
							{
								iCountErfolg++;
							}
							else
							{
								oMV.add_MESSAGE(oMV1);
							}
						}
					}
					
					if (iCountAlleGeprueften%100==0 || iCountAlleGeprueften==iAnzahl)
					{
						__actionAgentImportTexte.this.oGridServerAnzeige.removeAll();
						
						__actionAgentImportTexte.this.oGridServerAnzeige.add(new MyE2_Label("... "+iCountAlleGeprueften+" von "+iAnzahl));
						__actionAgentImportTexte.this.oGridServerAnzeige.add(new MyE2_Label("... "));
						__actionAgentImportTexte.this.oGridServerAnzeige.add(new MyE2_Label("Firma nicht gefunden: "+iCountFirmaNichtGefunden));
						__actionAgentImportTexte.this.oGridServerAnzeige.add(new MyE2_Label("Erfolgreich angefügt: "+iCountErfolg));
					}
				}
				String cTextAString = "";
				for (String tex: vTextFehler)
				{
					cTextAString=cTextAString+"\n"+tex;
				}
				
				MyE2_TextArea  oText = new MyE2_TextArea(cTextAString, 500, -1, 30);
				__actionAgentImportTexte.this.oGridServerAnzeige.add(oText);
			}
			
			@Override
			public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
			{
			}

		};
	}

	
	
	public int getiCountAlleGeprueften() {
		return iCountAlleGeprueften;
	}
	

}
