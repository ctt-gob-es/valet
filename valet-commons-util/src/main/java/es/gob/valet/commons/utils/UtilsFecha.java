/*
 * Este fichero forma parte de la plataforma de @firma.
 * La plataforma de @firma es de libre distribución cuyo código fuente puede ser consultado
 * y descargado desde http://forja-ctt.administracionelectronica.gob.es
 *
 * Copyright 2015 Gobierno de España
 * Este fichero se distribuye bajo las licencias EUPL versión 1.1  y GPL versión 3, o superiores, según las
 * condiciones que figuran en el fichero 'LICENSE.txt' que se acompaña.  Si se   distribuyera este
 * fichero individualmente, deben incluirse aquí las condiciones expresadas allí.
 */

/**
 * <b>File:</b><p>es.gob.afirma.utilidades.UtilsFecha.java.</p>
 * <b>Description:</b><p>Class that provides methods for managing dates.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI
 * certificates and electronic signature.</p>
 * <b>Date:</b><p>25/10/2005.</p>
 * @author Gobierno de España.
 * @version 1.1, 01/12/2015.
 */
package es.gob.valet.commons.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <p>Class that provides methods for managing dates.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI
 * certificates and electronic signature.</p>
 * @version 1.1, 01/12/2015.
 */
public class UtilsFecha {

	/**
	 * Constant attribute that represents the date format <code>yyyy-MM-dd EEE HH:mm:ss ZZZZ</code>.
	 */
	public static final String FORMATO_COMPLETO = "yyyy-MM-dd EEE HH:mm:ss ZZZZ";

	/**
	 * Constant attribute that represents the date format <code>yyyy-MM-dd</code>.
	 */
	public static final String FORMATO_FECHA = "yyyy-MM-dd";

	/**
	 * Constant attribute that represents the date format <code>HH:mm:ss</code>.
	 */
	public static final String FORMATO_HORA = "HH:mm:ss";

	/**
	 * Constant attribute that represents the date format <code>yyyy-MM-dd HH:mm:ss</code>.
	 */
	public static final String FORMATO_FECHA_HORA = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Constant attribute that represents the date format <code>dd/MM/yyyy</code>.
	 */
	public static final String FORMATO_FECHA_CORTO = "dd/MM/yyyy";

	/**
	 * Constant attribute that represents the date format <code>yyyyMMddHHmmss</code>.
	 */
	public static final String FORMATO_FECHA_JUNTA = "yyyyMMddHHmmss";

	/**
	 * Constant attribute that represents the date format <code>dd_MM_yyyy</code>.
	 */
	public static final String FORMATO_FECHA_BAJA = "dd_MM_yyyy";

	/**
	 * Constant attribute that represents the date format <code>"dd/MM/yyyy HH:mm:ss"</code>.
	 */
	public static final String FORMATO_FECHA_ESTANDAR = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Constant attribute that represents the date format <code>"dd/MM/yyyy/HH/mm/ss"</code>.
	 */
	public static final String FORMATO_FECHA_BARRAS = "dd/MM/yyyy/HH/mm/ss";

	/**
	 * Constant attribute that represents the date format <code>"yyyyMMddHHmmss.SZ"</code>.
	 */
	public static final String FORMATO_FECHA_JUNTA_ADICIONAL = "yyyyMMddHHmmss.SZ";

	/**
	 * Constant attribute that represents the date format <code>"yyyy-MM-dd'T'HHmmssZ"</code>.
	 */
	public static final String FORMATO_FECHA_HORA_ADICIONAL = "yyyy-MM-dd'T'HHmmssZ";

	/**
	 * Constant attribute that represents the date format <code>"yyyy-MM-dd HH:mm:ss,SSS"</code>.
	 */
	public static final String FORMATO_FECHA_HORA_COMPLETA = "yyyy-MM-dd HH:mm:ss,SSS";

	/**
	 * Constant attribute that represents the date format <code>"dd-MM-yyyy"</code>.
	 */
	public static final String FORMATO_FECHA_INVERTIDO = "dd-MM-yyyy";

	/**
	 * Constant attribute that represents the date format <code>"dd/MM/yyyy HH:mm:ss.SSS"</code>.
	 */
	public static final String FORMATO_FECHA_ESTANDAR_ADICIONAL = "dd/MM/yyyy HH:mm:ss.SSS";

	/**
	 * Constant attribute that represents the date format <code>"dd-MMM-yyyy HH:mm"</code>.
	 */
	public static final String FORMATO_FECHA_HORA_MINUTOS = "dd-MMM-yyyy HH:mm";

	/**
	 * Constant attribute that represents the date format <code>"yyyy-MM-dd HH:mm:ss ZZZZ"</code>.
	 */
	public static final String FORMATO_SEMICOMPLETO = "yyyy-MM-dd HH:mm:ss ZZZZ";

	/**
	 * Constant attribute that represents the date format <code>"yyyy-MM-dd'T'HH:mm:ss.SSS"</code>.
	 */
	public static final String FORMATO_FECHA_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	/**
	 * Constant attribute that represents the date format <code>"yyyy/MM/dd EEE hh:mm:ss zzzz"</code>.
	 */
	public static final String FORMATO_COMPLETO_ADICIONAL = "yyyy/MM/dd EEE hh:mm:ss zzzz";

	/**
	 * Constant attribute that represents the date format <code>"dd-MM-yy_HH-mm-ss"</code>.
	 */
	public static final String FORMATO_FECHA_HORA_SEGUNDOS = "dd-MM-yy_HH-mm-ss";

	/**
	 * Constant attribute that represents the date format <code>"MM/DD/YYYY HH24:MI:SS"</code>.
	 */
	public static final String FORMATO_FECHA_ORACLE = "MM/dd/yyyy HH:mm:ss";
	
	/**
	 * Constant attribute that represents the date format <code>"YYYY/MM/DD HH24:MI:SS"</code>.
	 */
	public static final String FORMATO_FECHA_MYSQL_HSQLDB = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Constant attribute that represents the date format <code>"yyyy-MM-dd HH:mm:ss.SSS zzz"</code>.
	 */
	public static final String FORMATO_FECHA_XML_CONFIGURACION = "yyyy-MM-dd HH:mm:ss.SSS zzz";
	
	/**
	 * Constant attribute that represents the date format <code>"yyyy-MM-dd'T'HH:mm:ss"</code>.
	 */
	public static final String FORMATO_FECHA_CRL_ISSUE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
	
	/**
	 * Constant attribute that represents the date format <code>"yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'"</code>.
	 */
	public static final String FORMATO_FECHA_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'";

	/**
	 * Attribute that represents the value of the date.
	 */
	private java.util.Date fecha;

	/**
	 * Attribute that represents the time zone offset.
	 */
	private TimeZone zona = null;

	/**
	 * Constructor method for the class UtilsFecha.java.
	 */
	public UtilsFecha() {
		Calendar cal = Calendar.getInstance();
		zona = cal.getTimeZone();
		fecha = cal.getTime();
	}

	/**
	 * Constructor method for the class UtilsFecha.java.
	 * @param pFecha Parameter that represents the value of the date.
	 */
	public UtilsFecha(Date pFecha) {
		zona = null;
		fecha = pFecha;
	}

	/**
	 * Constructor method for the class UtilsFecha.java.
	 * @param c Parameter that represents the object to set the value of the date and the time zone offset.
	 */
	public UtilsFecha(Calendar c) {
		zona = c.getTimeZone();
		fecha = c.getTime();
	}

	/**
	 * Constructor method for the class UtilsFecha.java.
	 * @param t Parameter that represents the time zone offset.
	 */
	public UtilsFecha(TimeZone t) {
		Calendar cal = Calendar.getInstance(t);
		zona = cal.getTimeZone();
		fecha = cal.getTime();
	}

	/**
	 * Constructor method for the class UtilsFecha.java.
	 * @param timezone Parameter that represents the identifier for the time zone offset.
	 */
	public UtilsFecha(String timezone) {
		zona = TimeZone.getTimeZone(timezone);
		fecha = Calendar.getInstance(zona).getTime();
	}

	/**
	 * Constructor method for the class UtilsFecha.java.
	 * @param fechaStr Parameter that represents the value of the date.
	 * @param formato Parameter that represents the format used for the date.
	 * @throws ParseException If the method fails.
	 */
	public UtilsFecha(String fechaStr, String formato) throws ParseException {
		Locale l = new Locale("ES", "es");

		SimpleDateFormat formador = new SimpleDateFormat(formato, l);
		zona = Calendar.getInstance().getTimeZone();
		formador.setTimeZone(zona);
		fecha = formador.parse(fechaStr);

	}

	/**
	 * Method that obtains a string with the value of the date for certain format.
	 * @param formato Parameter that represents the format to apply for the date.
	 * @return a string with the value of the date for certain format.
	 */
	public final String toString(String formato) {
		Locale l = new Locale("ES", "es");
		SimpleDateFormat formador = new SimpleDateFormat(formato, l);
		if (zona != null) {
			formador.setTimeZone(zona);
		}
		return formador.format(fecha);
	}
	
	/**
	 * Method that obtains a string with the value of the date for certain format.
	 * @param formato Parameter that represents the format to apply for the date.
	 * @param dateParam Date to be transform to String
	 * @return a string with the value of the date for certain format.
	 */
	public static final String toString(String formato, Date dateParam) {
		Locale l = new Locale("ES", "es");
		SimpleDateFormat formador = new SimpleDateFormat(formato, l);
		
		return formador.format(dateParam);
	}

	/**
	 * Method that obtains a string with the value of the date for certain format.
	 * @param formador Parameter that represents the concrete object for formatting and parsing the date in a locale-sensitive manner.
	 * @return a string with the value of the date for certain format.
	 */
	public final String toString(SimpleDateFormat formador) {
		return formador.format(fecha);
	}

	/**
	 * Method that obtains a string which represents the date on <code>UTC</code> format.
	 * @return the string on <code>UTC</code> format.
	 */
	public final String toUTCString() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_UTC);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(fecha) + "Z";
	}

	/**
	 * Method that obtains a date from a string with <code>UTC</code> format.
	 * @param utcDate Parameter that represents the string with <code>UTC</code> format.
	 * @return the date.
	 * @throws ParseException If the method fails.
	 */
	public static Date getUTCDate(String utcDate) throws ParseException {
		String[ ] t = utcDate.split("T");
		String pattern = "yyyy";
		String dateStr = null;
		dateStr = t[0].substring(0, NumberConstants.NUM4);
		if (t[0].length() > NumberConstants.NUM6) {
			dateStr = dateStr + t[0].substring(NumberConstants.NUM5, NumberConstants.NUM7);
			pattern = pattern + "MM";
			if (t[0].length() > NumberConstants.NUM9) {
				dateStr = dateStr + t[0].substring(NumberConstants.NUM8, NumberConstants.NUM10);
				pattern = pattern + "dd";
			}
		}
		if (t.length == 2) {
			String offSet = null;
			if (t[1].indexOf('Z') > -1) {
				t[1] = t[1].substring(0, t[1].indexOf('Z'));
				offSet = "+0000";
			} else if (t[1].indexOf('-') > -1) {
				offSet = t[1].substring(t[1].indexOf('-')).replaceAll(":", "");
				t[1] = t[1].substring(0, t[1].indexOf('-'));
			} else if (t[1].indexOf('+') > -1) {
				offSet = t[1].substring(t[1].indexOf('+')).replaceAll(":", "");
				t[1] = t[1].substring(0, t[1].indexOf('+'));
			}
			if (t[1].length() > 1) {
				dateStr = dateStr + t[1].substring(0, 2);
				pattern = pattern + "HH";
				if (t[1].length() > NumberConstants.NUM4) {
					dateStr = dateStr + t[1].substring(NumberConstants.NUM3, NumberConstants.NUM5);
					pattern = pattern + "mm";
					if (t[1].length() > NumberConstants.NUM7) {
						dateStr = dateStr + t[1].substring(NumberConstants.NUM6, NumberConstants.NUM8);
						pattern = pattern + "ss";
						if (t[1].length() > NumberConstants.NUM9) {
							pattern = pattern + ".SSS";
							t[1] = t[1].substring(NumberConstants.NUM8);
							for (int i = t[1].length(); i < NumberConstants.NUM4; i++) {
								t[1] = t[1] + "0";
							}
							dateStr = dateStr + t[1].substring(0, NumberConstants.NUM4);
						}
					}
				}
				if (offSet != null) {
					pattern = pattern + "Z";
					dateStr = dateStr + offSet;
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateStr);
	}

	/**
	 * Method that obtains the current date and hour of the system.
	 * @param formato Parameter that represents the format used to obtain the date.
	 * @return the current date and hour of the system.
	 */
	public static String getFechaSistema(String formato) {
		String fechaSistema = "";
		try {
			UtilsFecha serClsFecha = new UtilsFecha();
			fechaSistema = serClsFecha.toString(formato);
		} catch (Exception e) {
			fechaSistema = "FECHA KO";
		}
		return fechaSistema;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	public final String toString() {
		return toString(FORMATO_FECHA_HORA);
	}

	/**
	 * Gets the value of the attribute {@link #fecha}.
	 * @return the value of the attribute {@link #fecha}.
	 */
	public final Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the value of the attribute {@link #fecha}.
	 * @param pFecha The value for the attribute {@link #fecha}.
	 */
	public final void setFecha(Date pFecha) {
		fecha = pFecha;
	}

	/**
	 * Method that adds a number of days to the date.
	 * @param numDias Parameter that represents the number of days to add.
	 */
	public final void sumar(int numDias) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		fechaCalendar.add(Calendar.DATE, numDias);

		fecha = fechaCalendar.getTime();
	}

	/**
	 * Method that modifies the format of a date.
	 * @param fecha Parameter that represents the date.
	 * @param formatoOriginal Parameter that represents the original format.
	 * @param nuevoFormato Parameter that represents the new format.
	 * @return a string that represents the date with the new format.
	 * @throws ParseException If the method fails.
	 */
	public static String modificarFormato(String fecha, String formatoOriginal, String nuevoFormato) throws ParseException {
		UtilsFecha fechaAux = new UtilsFecha(fecha, formatoOriginal);
		return fechaAux.toString(nuevoFormato);
	}

	/**
	 * Method that adds a number of days to the system date.
	 * @param numDias Parameter that represents the number of days to add.
	 * @return a string that represents the date returned on format <code>yyyy-mm-dd</code>.
	 * @throws ParseException If the method fails.
	 */
	public final String sumarDias(int numDias) throws ParseException {

		Calendar fechaCalendar = Calendar.getInstance();

		fechaCalendar.add(Calendar.DATE, numDias);

		fecha = fechaCalendar.getTime();

		return modificarFormato(toString(), FORMATO_COMPLETO_ADICIONAL, FORMATO_FECHA);
	}

	/**
	 * Method that adds a number of days to certain date.
	 * @param fechaEnt Parameter that represents the date to add the days. It's a string with <code>YYYYMMDD</code> format.
	 * @param diasAdd Parameter that represents the number of days to add.
	 * @return a string that represents the date returned on format <code>yyyyMMDD</code>.
	 * @throws ParseException If the method fails.
	 */
	public static String getFechaMasNDias(String fechaEnt, int diasAdd) throws ParseException {
		/*Calendar	diaCaducidad 	= null;
		String		separadorFech	= "/";
		diaCaducidad = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		separadorFech = fechaEnt.substring(4,5);
		diaCaducidad.set(Integer.parseInt(fechaEnt.substring(0,4)), (Integer.parseInt(fechaEnt.substring(5,7))) - 1, Integer.parseInt(fechaEnt.substring(8,10)));
		diaCaducidad.add(Calendar.DATE,diasAdd);

		return  String.valueOf(diaCaducidad.get(Calendar.YEAR)) + separadorFech + String.valueOf(diaCaducidad.get(Calendar.MONTH) + 1) + separadorFech + String.valueOf(diaCaducidad.get(Calendar.DAY_OF_MONTH));
		 */
		UtilsFecha fecha = new UtilsFecha(fechaEnt, FORMATO_FECHA);
		return fecha.sumarDias(diasAdd);

	}
	
	/**
	 * Method that adds a number of days to certain date.
	 * @param fechaEnt Parameter that represents the date to add the days. 
	 * @param diasAdd Parameter that represents the number of days to add.
	 * @return a Date with diasAdd more.
	 */
	public static Date getFechaMasNDias(Date fechaEnt, int diasAdd) {
		Calendar fechaCalendar = Calendar.getInstance();

		fechaCalendar.setTime(fechaEnt);
		fechaCalendar.add(Calendar.DATE, diasAdd);

		return fechaCalendar.getTime();

	}

	/**
	 * Method that obtains a date from a string using a determined pattern.
	 * @param fecha Parameter that represents the string to transform in date.
	 * @param patron Parameter that represents the pattern used to generate the date.
	 * @return a date from a string using a determined pattern.
	 * @throws ParseException If the method fails.
	 */
	public static Date convierteFecha(String fecha, String patron) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(patron);
		Date fechaParseada = null;
		fechaParseada = format.parse(fecha);
		return fechaParseada;
	}

	/**
	 * Method that returns a Date from a string representing a PDF Date conform to the ASN.1 date format. This consists of
	 *  D:YYYYMMDDHHmmSSOHH'mm' where everything before and after YYYY is optional.
	 * @param value Parameter that represents the PDF Date.
	 * @return a wellformed Date if the input param is valid, or null in another case.
	 */
	public static Date parseToPDFDate(String value) {
		// Inicializamos variables
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;

		String yearStr = null;
		String monthStr = null;
		String dayStr = null;
		String hourStr = null;
		String minuteStr = null;
		String secondStr = null;
		String timezoneminuteStr = null;

		char timezonechar = '?'; // +, -, o Z
		int timezonehour = 0;
		int timezoneminute = 0;

		Calendar cal = null;

		// Verificación 1: el valor de entrada no puede ser nulo
		String str = value;
		if (str == null) {
			return null;
		}
		// Verificación 2: el valor de entrada debe tener, como mínimo 6
		// caracteres, esto es, formato D:YYYY
		str = str.trim();
		if (str.length() < NumberConstants.NUM6) {
			return null;
		}
		int datestate = 0;
		int charidx = 0;
		try {
			wloop : while (charidx < str.length()) {
				// Para parsear la fecha se utilizará una variable por cada
				// componente de la fecha
				switch (datestate) {
				// Verificación 3: el valor de entrada debe comenzar por
				// "D:"
					case 0:
						if ("D:".equals(str.substring(charidx, charidx + 2))) {
							charidx += 2;
						} else {
							return null;
						}
						datestate = 1;
						break;
					// Verificación 4: El año debe tener 4 cifras (0000-9999)
					case 1:
						yearStr = str.substring(charidx, charidx + NumberConstants.NUM4);
						year = Integer.parseInt(yearStr);
						charidx += NumberConstants.NUM4;
						if (year < 0 || year > NumberConstants.NUM9999) {
							return null;
						}
						datestate = 2;
						break;
					// Verificación 5: El mes debe tener 2 cifras (01-12)
					case 2:
						monthStr = str.substring(charidx, charidx + 2);
						if (!monthStr.startsWith("Z") && !monthStr.startsWith("-") && !monthStr.startsWith("+")) {
							month = Integer.parseInt(monthStr);
							charidx += 2;
							if (month < 1 || month > NumberConstants.NUM12) {
								return null;
							}
						} else {
							monthStr = null;
						}
						datestate = NumberConstants.NUM3;
						break;
					// Verificación 6: El día debe tener 2 cifras (01-31)
					case NumberConstants.NUM3:
						dayStr = str.substring(charidx, charidx + 2);
						if (!dayStr.startsWith("Z") && !dayStr.startsWith("-") && !dayStr.startsWith("+")) {
							day = Integer.parseInt(dayStr);
							if (day < 1 || day > NumberConstants.NUM31) {
								return null;
							}
							charidx += 2;
						} else {
							dayStr = null;
						}
						datestate = NumberConstants.NUM4;
						break;
					// Verificación 7: La hora debe tener 2 cifras (00-23)
					case NumberConstants.NUM4:
						hourStr = str.substring(charidx, charidx + 2);
						if (!hourStr.startsWith("Z") && !hourStr.startsWith("-") && !hourStr.startsWith("+")) {
							hour = Integer.parseInt(hourStr);
							charidx += 2;
							if (hour < 0 || hour > NumberConstants.NUM23) {
								return null;
							}
						} else {
							hourStr = null;
						}
						datestate = NumberConstants.NUM5;
						break;
					// Verificación 8: El minuto debe tener 2 cifras (00-59)
					case NumberConstants.NUM5:
						minuteStr = str.substring(charidx, charidx + 2);
						if (!minuteStr.startsWith("Z") && !minuteStr.startsWith("-") && !minuteStr.startsWith("+")) {
							minute = Integer.parseInt(minuteStr);
							charidx += 2;
							if (minute < 0 || minute > NumberConstants.NUM59) {
								return null;
							}
						} else {
							minuteStr = null;
						}
						datestate = NumberConstants.NUM6;
						break;
					// Verificación 9: El segundo debe tener 2 cifras (00-59)
					case NumberConstants.NUM6:
						secondStr = str.substring(charidx, charidx + 2);
						if (!secondStr.startsWith("Z") && !secondStr.startsWith("-") && !secondStr.startsWith("+")) {
							second = Integer.parseInt(secondStr);
							charidx += 2;
							if (second < 0 || second > NumberConstants.NUM59) {
								return null;
							}
						} else {
							secondStr = null;
						}
						datestate = NumberConstants.NUM7;
						break;
					// Verificación 10: La zona horaria debe tener 1 carácter
					// válido
					// ('+', '-', o 'Z')
					case NumberConstants.NUM7:
						timezonechar = str.charAt(charidx);
						if (timezonechar != 'Z' && timezonechar != '+' && timezonechar != '-') {
							return null;
						}
						charidx++;
						datestate = NumberConstants.NUM8;
						break;
					// Verificación 11: La hora que va tras la zona horaria debe
					// tener 2 cifras (00-23) si y sólo si
					// la zona horaria no tiene el carácter 'Z'
					case NumberConstants.NUM8:
						if (timezonechar == '+' || timezonechar == '-') {
							timezonehour = Integer.parseInt(str.substring(charidx, charidx + 2));
							if (timezonehour < 0 || timezonehour > NumberConstants.NUM23) {
								return null;
							}
							if (timezonechar == '-') {
								timezonehour = -timezonehour;
							}
							// Verificación 12: La hora que va tras la zona
							// horaria debe acabar en comilla simple
							if (!str.substring(charidx + 2, charidx + NumberConstants.NUM3).equals("'")) {
								return null;
							}
							charidx += 2;
						}
						datestate = NumberConstants.NUM9;
						break;
					// Verificación 13: El minuto que va tras la zona horaria
					// debe tener 2 cifras (00-59) si y sólo si
					// la zona horaria no tiene el carácter 'Z'
					case NumberConstants.NUM9:
						if (timezonechar == '+' || timezonechar == '-') {
							if (str.charAt(charidx) == '\'') {
								timezoneminuteStr = str.substring(charidx + 1, charidx + NumberConstants.NUM3);
								if (timezoneminuteStr.length() != 2) {
									return null;
								}
								timezoneminute = Integer.parseInt(timezoneminuteStr);
							}
							if (timezoneminute < 0 || timezoneminute > NumberConstants.NUM59) {
								return null;
							}
							if (timezonechar == '-') {
								timezoneminute = -timezoneminute;
							}
						}
						break wloop;
				}
			}
			// Verificación 14: El día debe ser válido para el mes obtenido
			if (yearStr != null && monthStr != null && dayStr != null) {

				// Mes con 28 o 29 días
				if (month == 2) {
					GregorianCalendar gc = new GregorianCalendar();
					// Año bisiesto
					if (gc.isLeapYear(year)) {
						if (day > NumberConstants.NUM29) {
							return null;
						}
					}
					// Año no bisiesto
					else {
						if (day > NumberConstants.NUM28) {
							return null;
						}
					}
				}
				// Meses con 30 días
				else if ((month == NumberConstants.NUM4 || month == NumberConstants.NUM6 || month == NumberConstants.NUM9 || month == NumberConstants.NUM11) && (day > NumberConstants.NUM30)) {
					return null;
				}
			}
			// Verificación 15: El número de campos rescatados debe ser al menos
			// 2
			if (datestate < 2) {
				return null;
			}
		}
		// Si se produce alguna excepción durante el proceso de asignación de
		// fechas entendemos que la fecha no está bien formada
		// y por tanto no es correcta.
		catch (Exception e) {
			return null;
		}
		// Construimos el objeto TimeZone que representará la zona horaria si se
		// especifica zona horaria.
		if (timezonechar != '?') {
			String tzStr = "GMT";
			if (timezonechar == 'Z') {
				tzStr += "+0000";
			} else {
				tzStr += timezonechar;
				NumberFormat nfmt = NumberFormat.getInstance();
				nfmt.setMinimumIntegerDigits(2);
				nfmt.setMaximumIntegerDigits(2);
				tzStr += nfmt.format(timezonehour);
				tzStr += nfmt.format(timezoneminute);
			}
			TimeZone tz = TimeZone.getTimeZone(tzStr);

			// Usamos el objeto TimeZone para crear un objeto Calendar con la
			// fecha teniendo en cuenta que los meses en Java comienzan en 0.
			cal = Calendar.getInstance(tz);
		}
		// Si no se especifica zona horaria
		else {
			cal = Calendar.getInstance();
		}
		if (month == 0) {
			month = 1;
		}
		cal.set(year, month - 1, day, hour, minute, second);
		return cal.getTime();
	}

	/**
	 * Method that obtains a period in milliseconds.
	 * @param hourPeriod Parameter that represents the hour.
	 * @param minutePeriod Parameter that represents the minutes.
	 * @param secondPeriod Parameter that represents the seconds.
	 * @return the period in milliseconds or null if some of the values.
	 */
	public static Long getPeriod(Long hourPeriod, Long minutePeriod, Long secondPeriod) {
		if (hourPeriod != null && minutePeriod != null && secondPeriod != null) {
			return (hourPeriod * NumberConstants.NUM3600 + minutePeriod * NumberConstants.NUM60 + secondPeriod) * NumberConstants.NUM1000;
		}
		return null;
	}

	/**
	 * Method that obtains the value in hours, minutes and seconds of a period in milliseconds.
	 * @param milliseconds Parameter that represents the period in milliseconds.
	 * @return an array where the first position is the value of the hour (24-hour), the second position is the value of the minutes
	 * and the third position is the value of the minutes.
	 */
	public static Integer[ ] getHoursSecondsMinutes(Long milliseconds) {

		int seconds = (int) (milliseconds / NumberConstants.NUM1000) % NumberConstants.NUM60;
		int minutes = (int) (milliseconds / (NumberConstants.NUM1000 * NumberConstants.NUM60) % NumberConstants.NUM60);
		int hours = (int) (milliseconds / (NumberConstants.NUM1000 * NumberConstants.NUM60 * NumberConstants.NUM60));

		Integer[ ] result = new Integer[NumberConstants.NUM3];
		result[0] = hours;
		result[1] = minutes;
		result[2] = seconds;

		return result;
	}
}