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
 * <b>File:</b><p>es.gob.valet.persistence.utils.BootstrapTreeNode.java.</p>
 * <b>Description:</b><p>Class to decode and encode password using AES algorithm.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.1, 27/09/2022.
 */
package es.gob.valet.persistence.utils;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * <p>Class that representation tree in interfaces with boostrap treeview.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 27/09/2022.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class BootstrapTreeNode implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -6541463222109373995L;

	/**
	 * Attribute that represents the identifier of the glyphicon home. 
	 */
	public static final String ICON_HOME = "glyphicon glyphicon-home text-info";

	/**
	 * Attribute that represents the identifier of the glyphicon certificate. 
	 */
	public static final String ICON_CERTIFICATE = "glyphicon glyphicon-certificate text-warning";

	/**
	 * Attribute that represents the identifier of the glyphicon certificate. 
	 */
	public static final String ICON_PAWN = "glyphicon glyphicon-pawn text-warning";

	/**
	 * Attribute that represents the identifier of the glyphicon certificate. 
	 */
	public static final String ICON_CERT_FIELD = "c-blue-500 ti-layout-list-thumb-alt";

	/**
	 * Attribute that represents the identifier of the glyphicon certificate. 
	 */
	public static final String ICON_CERT_FIELD_LEAF = "c-brown-500 ti-layout-list-thumb";

	/**
	 * Attribute that represents the identifier of the glyphicon certificate. 
	 */
	public static final String ICON_PARENT_DOWNLOAD = "download-crl-pink fa fa-download";

	/**
	 * Attribute that represents the identifier of the glyphicon certificate. 
	 */
	public static final String ICON_DOWNLOAD = "glyphicon glyphicon-download-alt c-blue-500";
	
	/**
	 * Attribute that represents the nodeId of the node. 
	 */
	private Object nodeId;

	/**
	 * Attribute that represents the parentId of the node. 
	 */
	private Object parentId;
	
	/**
	 * Attribute that represents the text of the node. 
	 */
	private String text;

	/**
	 * Attribute that represents the state of the node. 
	 */
	private State state;

	/**
	 * Attribute that represents the icon of the node. 
	 */
	private String icon;

	/**
	 * Attribute that represents the selectable of the node. 
	 */
	private Boolean selectable = true;
	
	/**
	 * Attribute that represents the nodes of the node. 
	 */
	private List<BootstrapTreeNode> nodes;

	/**
	 * Constructor method for the class BootstrapTreeNode.java.
	 */
	public BootstrapTreeNode() {
	}

	/**
	 * 
	 * Constructor method for the class BootstrapTreeNode.java.
	 * @param nodeId parameter that contain the id of node.
	 * @param parentId parameter that contain the parentId of node.
	 * @param text parameter that contain the text of node.
	 * @param icon parameter that contain the icon of node.
	 */
	public BootstrapTreeNode(Long nodeId, Long parentId, Long text, String icon) {
	}
		
	/**
	 * Gets the value of the attribute {@link #icon}.
	 * @return the value of the attribute {@link #icon}.
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the value of the attribute {@link #icon}.
	 * @param icon The value for the attribute {@link #icon}.
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Gets the value of the attribute {@link #text}.
	 * @return the value of the attribute {@link #text}.
	 */
	public List<BootstrapTreeNode> getNodes() {
		return nodes;
	}

	/**
	 * Sets the value of the attribute {@link #nodes}.
	 * @param state The value for the attribute {@link #nodes}.
	 */
	public void setNodes(List<BootstrapTreeNode> nodes) {
		this.nodes = nodes;
	}
	
	/**
	 * Gets the value of the attribute {@link #text}.
	 * @return the value of the attribute {@link #text}.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the value of the attribute {@link #text}.
	 * @param text The value for the attribute {@link #text}.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the value of the attribute {@link #text}.
	 * @return the value of the attribute {@link #text}.
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets the value of the attribute {@link #state}.
	 * @param state The value for the attribute {@link #state}.
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * Gets the value of the attribute {@link #selectable}.
	 * @return the value of the attribute {@link #selectable}.
	 */
	public Boolean getSelectable() {
		return selectable;
	}

	/**
	 * Sets the value of the attribute {@link #selectable}.
	 * @param selectable The value for the attribute {@link #selectable}.
	 */
	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}

	/**
	 * Gets the value of the attribute {@link #idNode}.
	 * @return the value of the attribute {@link #idNode}.
	 */
	public Object getNodeId() {
		return nodeId;
	}

	/**
	 * Sets the value of the attribute {@link #idNode}.
	 * @param nodeId The value for the attribute {@link #idNode}.
	 */
	public void setNodeId(Object nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * Gets the value of the attribute {@link #parentId}.
	 * @return the value of the attribute {@link #parentId}.
	 */
	public Object getParentId() {
		return parentId;
	}

	/**
	 * Sets the value of the attribute {@link #parentId}.
	 * @param parentId The value for the attribute {@link #parentId}.
	 */
	public void setParentId(Object parentId) {
		this.parentId = parentId;
	}

	/**
	 * <p>Class that representation tree in interfaces with boostrap treeview.</p>
	 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
	 * @version 1.0, 19/09/2022.
	 */
	public static class State {

		/**
		 * Attribute that represents the checked of the node. 
		 */
		private Boolean checked = false;
		/**
		 * Attribute that represents the disabled of the node. 
		 */
		private Boolean disabled = false;
		/**
		 * Attribute that represents the expanded of the node. 
		 */
		private Boolean expanded = false;
		/**
		 * Attribute that represents the selected of the node. 
		 */
		private Boolean selected = false;

		/**
		 * Constructor method for the class State.java.
		 */
		public State() {
		}

		/**
		 * Constructor method for the class State.java.
		 * checked id parameter that contain the checked of node. 
		 * checked id parameter that contain the disabled of node. 
		 * checked id parameter that contain the expanded of node. 
		 * checked id parameter that contain the selected of node. 
		 */
		public State(Boolean checked, Boolean disabled, Boolean expanded, Boolean selected) {
			this.checked = checked;
			this.disabled = disabled;
			this.expanded = expanded;
			this.selected = selected;
		}

		/**
		 * Gets the value of the attribute {@link #checked}.
		 * @return the value of the attribute {@link #checked}.
		 */
		public Boolean getChecked() {
			return checked;
		}

		/**
		 * Sets the value of the attribute {@link #checked}.
		 * @param checked The value for the attribute {@link #checked}.
		 */
		public void setChecked(Boolean checked) {
			this.checked = checked;
		}

		/**
		 * Gets the value of the attribute {@link #disabled}.
		 * @return the value of the attribute {@link #disabled}.
		 */
		public Boolean getDisabled() {
			return disabled;
		}

		/**
		 * Sets the value of the attribute {@link #disabled}.
		 * @param disabled The value for the attribute {@link #disabled}.
		 */
		public void setDisabled(Boolean disabled) {
			this.disabled = disabled;
		}

		/**
		 * Gets the value of the attribute {@link #expanded}.
		 * @return the value of the attribute {@link #expanded}.
		 */
		public Boolean getExpanded() {
			return expanded;
		}

		/**
		 * Sets the value of the attribute {@link #expanded}.
		 * @param expanded The value for the attribute {@link #expanded}.
		 */
		public void setExpanded(Boolean expanded) {
			this.expanded = expanded;
		}

		/**
		 * Gets the value of the attribute {@link #selected}.
		 * @return the value of the attribute {@link #selected}.
		 */
		public Boolean getSelected() {
			return selected;
		}

		/**
		 * Sets the value of the attribute {@link #selected}.
		 * @param selected The value for the attribute {@link #selected}.
		 */
		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
		
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((selectable == null) ? 0 : selectable.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BootstrapTreeNode other = (BootstrapTreeNode) obj;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (selectable == null) {
			if (other.selectable != null)
				return false;
		} else if (!selectable.equals(other.selectable))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
}
