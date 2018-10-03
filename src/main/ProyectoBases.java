/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.Attribute;
import domain.EntitySet;
import domain.RelationshipSets;
import fileManagement.convertSql;
import fileManagement.json;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class ProyectoBases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            json json = new json("EntityRelationshipJsonExample.json");
            convertSql sql = new convertSql("EntityRelationshipJsonExample.json");

            domain.JsonObject jsonObject = json.readJson();

            LinkedList<EntitySet> a = jsonObject.getEntitySets();

            System.out.println("----------------------------------------");

            LinkedList<RelationshipSets> b = jsonObject.getRelationshipSets();

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

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoBases.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
