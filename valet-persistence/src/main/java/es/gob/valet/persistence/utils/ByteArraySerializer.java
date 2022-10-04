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
 * <b>File:</b><p>es.gob.valet.persistence.utils.ByteArraySerializer.java.</p>
 * <b>Description:</b><p>Class to decode and encode password using AES algorithm.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>04/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 04/10/2022.
 */
package es.gob.valet.persistence.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * <p>Class that representation tree in interfaces with boostrap treeview.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 04/10/2022.
 */
public class ByteArraySerializer extends JsonSerializer<byte[]> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void serialize(byte[] bytes, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeStartArray();

		for (byte b : bytes) {
			jgen.writeNumber(unsignedToBytes(b));
		}

		jgen.writeEndArray();

	}

	/**
	 * Method to convert bytes to unsigned bytes.
	 * 
	 * @param b parameter that contain byte.
	 * @return byte to unsigned.
	 */
	private static int unsignedToBytes(byte b) {
		return b & 0xFF;
	}
}
