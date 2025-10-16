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
 * <b>File:</b><p>es.gob.valet.dto.TslDataDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Valet Version DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.1, 07/10/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** 
 * <p>Class that represents an object that relates the code of a to the Valet Version DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 07/10/2025.
 */
public class ValetVersionDTO {

    /**
     * ID of the version.
     */
    private int idVersion;

    /**
     * Version string.
     */
    private String version;

    /**
     * Static list of all available versions.
     */
    private static final List<ValetVersionDTO> versionList;

    /**
     * Version string for version 1.1.0.
     */
    public static final String VERSION_1_1_0 = "1.1.0";

    /**
     * Version string for version 1.2.0.
     */
    public static final String VERSION_1_2_0 = "1.2.0";

    /**
     * Default constructor.
     */
    public ValetVersionDTO() {}

    /**
     * Enum representing available versions.
     */
    public enum VersionEnum {
        
        /**
         * Version 1.1.0.
         */
        V1(1, VERSION_1_1_0),

        /**
         * Version 1.2.0.
         */
        V2(2, VERSION_1_2_0);

        private final int id;
        private final String version;

        VersionEnum(int id, String version) {
            this.id = id;
            this.version = version;
        }

        /**
         * Returns the ID of the version.
         *
         * @return version ID
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the version string.
         *
         * @return version string
         */
        public String getVersion() {
            return version;
        }

        /**
         * Returns a list of all version strings.
         *
         * @return list of version strings
         */
        public static List<String> getVersionList() {
            return Arrays.stream(values()).map(VersionEnum::getVersion).collect(Collectors.toList());
        }

        /**
         * Returns a list of all versions as ValetVersionDTO objects.
         *
         * @return list of ValetVersionDTO objects
         */
        public static List<ValetVersionDTO> getAllVersions() {
            return Arrays.stream(values()).map(v -> new ValetVersionDTO(v)).collect(Collectors.toList());
        }
    }

    /**
     * Static block to initialize the list of all available versions.
     */
    static {
        versionList = VersionEnum.getAllVersions();
    }

    /**
     * Constructor initializing the DTO with values from the enum.
     *
     * @param versionEnum the version enum to initialize the DTO
     */
    public ValetVersionDTO(VersionEnum versionEnum) {
        this.idVersion = versionEnum.getId();
        this.version = versionEnum.getVersion();
    }

    /**
     * Returns the ID of the version.
     *
     * @return version ID
     */
    public int getIdVersion() {
        return idVersion;
    }

    /**
     * Returns the version string.
     *
     * @return version string
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the list of all available versions.
     *
     * @return list of ValetVersionDTO objects
     */
    public static List<ValetVersionDTO> getVersionList() {
        return versionList;
    }

}
