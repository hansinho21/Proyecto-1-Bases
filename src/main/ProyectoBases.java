/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Attribute;
import domain.EntitySet;
import domain.RelationshipSets;
import fileManagement.json;
import java.util.LinkedList;

/**
 *
 * @author ACER
 */
public class ProyectoBases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        json json = new json();

//        LinkedList<EntitySet> a = json.getEntitySets("entitySet2.json");
//        for (int i = 0; i < a.size(); i++) {
//            System.out.println(a.get(i).toString());
//        }
        
        LinkedList<RelationshipSets> b = json.getRelationshipSets("relationshipSets.json");
        for (int i = 0; i < b.size(); i++) {
            System.out.println(b.get(i).toString());
        }
       
        
//        LinkedList<Attribute> list = new LinkedList<>();
//        
//        Attribute a1 = new Attribute("first_name", "VARCHAR", "Simple", new LinkedList<Attribute>(), false, false, 100);
//    
//        list.add(a1);
//        
//        Attribute a = new Attribute("ID", "INT", "Simple", list, true, false, 0);
//        System.out.println(a);
    }
    
}
