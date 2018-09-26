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
public class RelationshipSets {
    
    private String name;
    private String type;
    private LinkedList<Attribute> descriptiveAttributesList;
    private LinkedList<ParticipationEntity> participationEntitiesList;

    public RelationshipSets() {
    }

    public RelationshipSets(String name, String type, LinkedList<Attribute> descriptiveAttributesList, LinkedList<ParticipationEntity> participationEntitiesList) {
        this.name = name;
        this.type = type;
        this.descriptiveAttributesList = descriptiveAttributesList;
        this.participationEntitiesList = participationEntitiesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LinkedList<Attribute> getDescriptiveAttributesList() {
        return descriptiveAttributesList;
    }

    public void setDescriptiveAttributesList(LinkedList<Attribute> descriptiveAttributesList) {
        this.descriptiveAttributesList = descriptiveAttributesList;
    }

    public LinkedList<ParticipationEntity> getParticipationEntitiesList() {
        return participationEntitiesList;
    }

    public void setParticipationEntitiesList(LinkedList<ParticipationEntity> participationEntitiesList) {
        this.participationEntitiesList = participationEntitiesList;
    }

    @Override
    public String toString() {
        String data = "{\n";
        
        data += "  Name: " + this.name + "\n";
        data += "  Type: " + this.type + "\n";
        if(!this.descriptiveAttributesList.isEmpty()){
            data += "  DescriptiveAttributes: {\n";
            for (int i = 0; i < this.descriptiveAttributesList.size(); i++) {
                data += "\t" + this.descriptiveAttributesList.get(i).toString() + "\n";
            }
        } else {
            data += "  DescriptiveAttributes: null\n";
        }
        data += "  ParticipationEntities: {\n";
        for (int i = 0; i < this.participationEntitiesList.size(); i++) {
            data += "\t" + this.participationEntitiesList.get(i).toString() + "\n";
        }
        data += "}";
        
        return data;
    }
    
    
}
