/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Attribute;
import domain.EntitySet;
import domain.RelationshipSets;
import fileManagement.convertSql;
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
        convertSql sql = new convertSql("document (7).json");

        LinkedList<EntitySet> a = json.getEntitySets("document (7).json");
//        for (int i = 0; i < a.size(); i++) {
//            System.out.println(a.get(i).toString());
//        }
        
        System.out.println("----------------------------------------");
        
        LinkedList<RelationshipSets> b = json.getRelationshipSets("document (6).json");
//        for (int i = 0; i < b.size(); i++) {
//            System.out.println(b.get(i).toString());
//        }
        
        String string = "";
        String string2 = "";
       
        for (int i = 0; i < a.size(); i++) {
            string += sql.createEntityTables(a.get(i));
        }
        
        System.out.println(string);
        
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < b.size(); i++) {
            string2 += sql.createRelationshipTables(b.get(i));
        }
        
        System.out.println(string2);
        
    }
    
}
