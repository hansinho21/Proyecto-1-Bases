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
    
    private String Name;
    private String Type;
    private LinkedList<Attribute> DescriptiveAttributes;
    private LinkedList<ParticipationEntity> ParticipationEntities;

    public RelationshipSets() {
    }

    public RelationshipSets(String name, String type, LinkedList<Attribute> descriptiveAttributesList, LinkedList<ParticipationEntity> participationEntitiesList) {
        this.Name = name;
        this.Type = type;
        this.DescriptiveAttributes = descriptiveAttributesList;
        this.ParticipationEntities = participationEntitiesList;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public LinkedList<Attribute> getDescriptiveAttributesList() {
        return DescriptiveAttributes;
    }

    public void setDescriptiveAttributesList(LinkedList<Attribute> descriptiveAttributesList) {
        this.DescriptiveAttributes = descriptiveAttributesList;
    }

    public LinkedList<ParticipationEntity> getParticipationEntitiesList() {
        return ParticipationEntities;
    }

    public void setParticipationEntitiesList(LinkedList<ParticipationEntity> participationEntitiesList) {
        this.ParticipationEntities = participationEntitiesList;
    }

    @Override
    public String toString() {
        String data = "{\n";
        
        data += "  Name: " + this.Name + "\n";
        data += "  Type: " + this.Type + "\n";
        if(this.DescriptiveAttributes != null){
            data += "  DescriptiveAttributes: {\n";
            for (int i = 0; i < this.DescriptiveAttributes.size(); i++) {
                data += "\t" + this.DescriptiveAttributes.get(i).toString() + "\n";
            }
        } else {
            data += "  DescriptiveAttributes: null\n";
        }
        data += "  ParticipationEntities: {\n";
        for (int i = 0; i < this.ParticipationEntities.size(); i++) {
            data += "\t" + this.ParticipationEntities.get(i).toString() + "\n";
        }
        data += "}";
        
        return data;
    }
    
    
}
