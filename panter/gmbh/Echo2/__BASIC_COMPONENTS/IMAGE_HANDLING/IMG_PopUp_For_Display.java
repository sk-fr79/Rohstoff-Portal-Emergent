package panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Vector;

import javax.imageio.ImageIO;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.StreamImageReference;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_PopUpWindow_for_Upload;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.ImageIcon;

public class IMG_PopUp_For_Display extends	E2_BasicModuleContainer
{
	
	private Vector<String>   	m_vImagePath = null;
	private MyE2_Grid         	m_oGridMain = null;
	private Integer				m_nColumns  = 1;
	private MyE2_Column        m_oColMain = null;
	
	private String 				m_sUeberschrift = "Bilder";
//	private String				m_sDescription  = "";
	
	private int 				m_iImageWidth = 500;
	
	MyE2_SelectField 			oSel = null;
	
	
	public IMG_PopUp_For_Display(	Vector<String> v_ImagePath	) throws myException
	{
		super();
		m_vImagePath = new Vector<String>();
		m_vImagePath.addAll(v_ImagePath);
				
		this.initMask();
	}
	
	
	private void initMask() throws myException
	{
		m_oColMain = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER_NO_INSETS());
		m_oGridMain = new MyE2_Grid(m_nColumns , MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		
		
		
		if (m_sUeberschrift != null){
			m_oColMain.add(new MyE2_Label(m_sUeberschrift, MyE2_Label.STYLE_TITEL_BIG()),E2_PopUpWindow_for_Upload.INSETSBASIC);
		}
		


		// Bildweite Einstellungen
		String[][] arrImageWidth = {
				{"100pt","100"},
				{"200pt","200"},
				{"300pt","300"},
				{"400pt","400"},
				{"500pt","500"},
				{"600pt","600"},
				{"700pt","700"},
				{"800pt","800"},
				{"900pt","900"}};
		
		oSel = new MyE2_SelectField(arrImageWidth, Integer.toString(m_iImageWidth) , false, new Extent(100));
		oSel.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				setIconWidth(m_oColMain, Integer.parseInt(oSel.get_ActualWert()));
				displayImages(Integer.parseInt(oSel.get_ActualWert()) );
			}
		});
		
		MyE2_Row rowImageWidth = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		rowImageWidth.add(new MyE2_Label(new MyString("Breite des Bildes: ")), E2_INSETS.I_0_0_5_0);
		rowImageWidth.add(oSel);
		m_oColMain.add(rowImageWidth);
		
		m_oColMain.add(m_oGridMain);
		
		this.m_oGridMain.add(new MyE2_Label(new MyE2_String("Kein Bild vorhanden!")));
		
		this.add(m_oColMain);
		
		displayImages(Integer.parseInt(oSel.get_ActualWert()));
	}


	private void displayImages(Integer imageWidth){
		
		
		if (m_vImagePath != null && m_vImagePath.size() > 0){
			m_oGridMain.removeAll();
			for (String sImage: m_vImagePath){
				addImageToGrid(sImage, imageWidth);
			}
		}
	}
	
	
	
	/**
	 * Setzt die größe der Bilder 
	 * @param comp
	 * @param iWidth
	 */
	private void setIconWidth(Component comp, int iWidth){
		for (Component o :comp.getComponents()){
			
			if (o instanceof ImageIcon){
				((ImageIcon)o).setWidth(new Extent(iWidth));
			} else if (o.getComponents().length >= 1){
				setIconWidth(o,iWidth);
			}
		}
	}
	
	
	
	/**
	 * Fügt das Bild aus dem gegebenen Pfad in die Bilderliste ein
	 * @param sImage
	 */
	private void addImageToGrid(String sImage, Integer imageWidth){
		
		MyE2_Row    rowImageAndDesc = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS_LEFT_TOP());
		MyE2_Column colDesc = new MyE2_Column(MyE2_Column.STYLE_3D_BORDER());
		
//		streamImageReferenceExt stream =new streamImageReferenceExt(sImage);
		IMG_StreamImageReference stream =new IMG_StreamImageReference(sImage, imageWidth, imageWidth);
		
		ImageIcon oIcon = new ImageIcon(stream);
		oIcon.setWidth(new Extent(imageWidth));
		
		rowImageAndDesc.add(oIcon);
		rowImageAndDesc.add(colDesc,MyE2_Row.LAYOUT_LEFT_TOP(E2_INSETS.I_5_0_0_0));

		
		String sFileseparator = System.getProperty("file.separator"); 
		String sFilename =  sImage.substring(sImage.lastIndexOf(sFileseparator)+1);
		
		String sRootPath = bibALL.get_WEBROOTPATH();
		String sRelPath = "";
		if (sImage.contains(sRootPath)){
			// vorne die Basis abschneiden
			sRelPath = sImage.substring(sImage.indexOf(sRootPath)+sRootPath.length());
			// hinten den Dateinamen abschneiden 
			sRelPath = sRelPath.substring(1, sRelPath.lastIndexOf(sFileseparator));
		}

		
		colDesc.add(new MyE2_Label(sFilename,MyE2_Label.STYLE_NORMAL_BOLD()));
		colDesc.add(new MyE2_Label(sRelPath));
		
		m_oGridMain.add(rowImageAndDesc);

	}
	
	
	/**
	 * Auslesen der Darstellungsbreite der Bilder in Pixel
	 * @return
	 */
	public int get_ImageWidth() {
		return m_iImageWidth;
	}


	/**
	 * Setzen der Darstellungsbreite der Bilder in Pixel
	 * @return
	 */
	public void set_ImageWidth(int m_iImageWidth) {
		this.m_iImageWidth = m_iImageWidth;
	}

	
	

//	/**
//	 * private Klasse zum lesen der Datei in ene StreamImageReference
//	 * @author manfred
//	 *
//	 */
//	private class streamImageReferenceExt extends StreamImageReference{
//		private String id = ApplicationInstance.generateSystemId();
//		private static final int BUFFER_SIZE = 4096;
//		
//		private String m_sFileName = null;
//		
//		public streamImageReferenceExt(String sFilename) {
//			m_sFileName = sFilename;
//		}
//		
//		/**
//         * @see nextapp.echo2.app.RenderIdSupport#getRenderId()
//         */
//        public String getRenderId() {
//            return id;
//        }
//		
//        /**
//         * @see nextapp.echo2.app.StreamImageReference#getContentType()
//         */
//        public String getContentType() {
//            return "image/jpeg";
//        }
//		
//        @Override
//        public Extent getWidth() {
//        	return super.getWidth();
//        }
//        
//        @Override
//        public Extent getHeight() {
//        	return super.getHeight();
//        }
//        
//        
//        /**
//         * @see nextapp.echo2.app.StreamImageReference#render(java.io.OutputStream)
//         */
//        public void render(OutputStream out) throws IOException {
//            InputStream in = null;
//            byte[] buffer = new byte[BUFFER_SIZE];
//            int bytesRead = 0;
//            
//            try {
//            	
//                in = new FileInputStream(m_sFileName);
//                do {
//                    bytesRead = in.read(buffer);
//                    if (bytesRead > 0) {
//                        out.write(buffer, 0, bytesRead);
//                    }
//                } while (bytesRead > 0);
//            } finally {
//                if (in != null) { try { in.close(); } catch (IOException ex) { } } 
//            }
//        }
// 
//    };
// 
}
