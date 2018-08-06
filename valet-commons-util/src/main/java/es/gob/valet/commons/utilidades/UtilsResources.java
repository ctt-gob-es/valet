/* 
/*******************************************************************************
 * Copyright (C) 2018 MINHAFP, Gobierno de España
 * This program is licensed and may be used, modified and redistributed under the  terms
 * of the European Public License (EUPL), either version 1.1 or (at your option)
 * any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and
 * more details.
 * You should have received a copy of the EUPL1.1 license
 * along with this program; if not, you may find it at
 * http:joinup.ec.europa.eu/software/page/eupl/licence-eupl
 ******************************************************************************/

/** 
 * <b>File:</b><p>es.gob.valet.commons.utilidades.UtilsResources.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>1 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 1 ago. 2018.
 */
package es.gob.valet.commons.utilidades;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 1 ago. 2018.
 */
public class UtilsResources {
	/**
	 * Constant attribute that represents the token 'text/plain'.
	 */
	private static final String TOKEN_TEXT_PLAIN = "text/plain";
	
	/**
	 * Constant attribute that represents an empty string.
	 */
	private static final String EMPTY_STRING="";
	
	public static String getMimeType(byte[ ] data) {
		Tika t = new Tika();
		InputStream is = new ByteArrayInputStream(data);
		try {
			return t.detect(is);
		} catch (IOException e) {
			//LOGGER.error(Language.getFormatResCoreGeneral(LOG02, new Object[ ] { }), e);
			
			
		} finally {
			safeCloseInputStream(is);
		}
		return TOKEN_TEXT_PLAIN;
	}
	
	/**
	 * Method that handles the closing of a {@link InputStream} resource.
	 * @param is Parameter that represents a {@link InputStream} resource.
	 */
	public static void safeCloseInputStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				//LOGGER.error(Language.getFormatResCoreGeneral(LOG01, new Object[ ] { is.getClass().getName(), e.getMessage() }));
			}
		}
	}
	/**
	 * Autodetects extension given a mime type.
	 * 
	 * @param mimeType Mime Type to autodetect its extension.
	 * @return Extension associated to given mime type.
	 */
	public static String getExtension(String mimeType) {
		MimeType m;
		try {
			m = MimeTypes.getDefaultMimeTypes().forName(mimeType);
			return m.getExtension();
		} catch (MimeTypeException e) {
			//LOGGER.error(Language.getFormatResCoreGeneral(LOG03, new Object[ ] { }), e);
		}
		return EMPTY_STRING;
	}
	
	/**
	 * Method to convert an array of bytes into a File.
	 * 
	 * @param file File that will be created.
	 * @param bytes Array of bytes with the contents of the file.
	 * @throws IOException If the method fails.
	 */
	 public static void writeBytesToFile(File file, byte[] bytes) throws IOException {
	      BufferedOutputStream bos = null;
	      
	    try {
	      FileOutputStream fos = new FileOutputStream(file);
	      bos = new BufferedOutputStream(fos); 
	      bos.write(bytes);
	    }finally {
	      if(bos != null) {
	        try  {
	          //flush and close the BufferedOutputStream
	          bos.flush();
	          bos.close();
	        } catch(Exception e){}
	      }
	    }
	    
	    
	    /*
	     *     OutputStream out = new FileOutputStream(file);
	    	out.write(bytes);
	    	out.close();*/
	 }
}
