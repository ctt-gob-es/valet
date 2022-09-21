package es.gob.valet.persistence.configuration.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.gob.valet.persistence.utils.BootstrapTreeNode;

public class Prueba {

	public static void main(String[ ] args) {
		String json = obtenerJSon();
		System.out.println(json);
	}

	private static String obtenerJSon() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		BootstrapTreeNode bootstrapTreeNode1_1 = new BootstrapTreeNode();
		bootstrapTreeNode1_1.setText("IT");
		bootstrapTreeNode1_1.setSelectable(false);
		bootstrapTreeNode1_1.setNodeId(0L);
		
		BootstrapTreeNode bootstrapTreeNode1_2 = new BootstrapTreeNode();
		bootstrapTreeNode1_2.setText("TSPName (en)");
		bootstrapTreeNode1_2.setSelectable(false);
		bootstrapTreeNode1_2.setParentId(0L);
		bootstrapTreeNode1_2.setNodeId(1L);
		
		BootstrapTreeNode bootstrapTreeNode1_2_1 = new BootstrapTreeNode();
		bootstrapTreeNode1_2_1.setText("TSPServiceName (en) - CAD: DD/MM/YYYY");
		bootstrapTreeNode1_2_1.setSelectable(true);
		bootstrapTreeNode1_2_1.setParentId(1L);
		bootstrapTreeNode1_2_1.setNodeId(11L);
				
		BootstrapTreeNode bootstrapTreeNode1_2_2 = new BootstrapTreeNode();
		bootstrapTreeNode1_2_2.setText("TSPServiceName");
		bootstrapTreeNode1_2_2.setSelectable(true);
		bootstrapTreeNode1_2_2.setParentId(1L);
		bootstrapTreeNode1_2_2.setNodeId(22L);
		
		BootstrapTreeNode bootstrapTreeNode2_1 = new BootstrapTreeNode();
		bootstrapTreeNode2_1.setText("TSPName (en) 2");
		bootstrapTreeNode2_1.setSelectable(false);
		bootstrapTreeNode2_1.setParentId(0L);
		bootstrapTreeNode2_1.setNodeId(33L);
		
		bootstrapTreeNode1_2.setNodes(new ArrayList<>());
		bootstrapTreeNode1_2.getNodes().add(bootstrapTreeNode1_2_1);
		bootstrapTreeNode1_2.getNodes().add(bootstrapTreeNode1_2_2);
		
		bootstrapTreeNode1_1.setNodes(new ArrayList<>());
		bootstrapTreeNode1_1.getNodes().add(bootstrapTreeNode1_2);
		bootstrapTreeNode1_1.getNodes().add(bootstrapTreeNode2_1);
		
		BootstrapTreeNode bootstrapTreeNode3_1 = new BootstrapTreeNode();
		bootstrapTreeNode3_1.setText("BE");
		bootstrapTreeNode3_1.setSelectable(false);
		bootstrapTreeNode3_1.setNodeId(1L);
		
		BootstrapTreeNode bootstrapTreeNode3_2 = new BootstrapTreeNode();
		bootstrapTreeNode3_2.setText("TSPName (en)");
		bootstrapTreeNode3_2.setSelectable(false);
		bootstrapTreeNode3_2.setParentId(1L);
		bootstrapTreeNode3_2.setNodeId(1L);
		
		BootstrapTreeNode bootstrapTreeNode3_2_1 = new BootstrapTreeNode();
		bootstrapTreeNode3_2_1.setText("TSPServiceName (en) - CAD: DD/MM/YYYY");
		bootstrapTreeNode3_2_1.setSelectable(true);
		bootstrapTreeNode3_2_1.setParentId(1L);
		bootstrapTreeNode3_2_1.setNodeId(11L);
		
		BootstrapTreeNode bootstrapTreeNode3_2_2 = new BootstrapTreeNode();
		bootstrapTreeNode3_2_2.setText("TSPServiceName (en) - CAD: DD/MM/YYYY");
		bootstrapTreeNode3_2_2.setSelectable(true);
		bootstrapTreeNode3_2_2.setParentId(1L);
		bootstrapTreeNode3_2_2.setNodeId(22L);
		
		bootstrapTreeNode3_2.setNodes(new ArrayList<>());
		bootstrapTreeNode3_2.getNodes().add(bootstrapTreeNode3_2_1);
		bootstrapTreeNode3_2.getNodes().add(bootstrapTreeNode3_2_2);
		
		bootstrapTreeNode3_1.setNodes(new ArrayList<>());
		bootstrapTreeNode3_1.getNodes().add(bootstrapTreeNode3_2);
		
		List<BootstrapTreeNode> l = new ArrayList<BootstrapTreeNode>();
		
		l.add(bootstrapTreeNode1_1);
		l.add(bootstrapTreeNode3_1);
		
		String res = null;
		try {
			 res  = objectMapper.writeValueAsString(l);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

}
