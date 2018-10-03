/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.LinkedList;

/**
 *
 * @author ACER
 */
public class JsonObject {
    
    private LinkedList<EntitySet> EntitySets;
    private LinkedList<RelationshipSets> RelationshipSets;

    public JsonObject() {
    }

    public JsonObject(LinkedList<EntitySet> EntitySets, LinkedList<RelationshipSets> RelationshipSets) {
        this.EntitySets = EntitySets;
        this.RelationshipSets = RelationshipSets;
    }

    public LinkedList<EntitySet> getEntitySets() {
        return EntitySets;
    }

    public void setEntitySets(LinkedList<EntitySet> EntitySets) {
        this.EntitySets = EntitySets;
    }

    public LinkedList<RelationshipSets> getRelationshipSets() {
        return RelationshipSets;
    }

    public void setRelationshipSets(LinkedList<RelationshipSets> RelationshipSets) {
        this.RelationshipSets = RelationshipSets;
    }

    @Override
    public String toString() {
        String result = "{\n";
        result += "  EntitySets: [\n";
        for (int i = 0; i < EntitySets.size(); i++) {
            result += EntitySets.get(i).toString();
        }
        result += "\n  ],\n";
        result += "  RelationshipSets: [\n";
        for (int i = 0; i < RelationshipSets.size(); i++) {
            result += RelationshipSets.get(i).toString();
        }
        result += "\n  ]";
        result += "\n}";
        return result;
    }
    
}
