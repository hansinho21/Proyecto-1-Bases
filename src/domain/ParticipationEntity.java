/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author ACER
 */
public class ParticipationEntity {
    
    private String EntityName;
    private String Cardinality;
    private String ParticipationType;

    public ParticipationEntity() {
    }

    public ParticipationEntity(String entityName, String cardinality, String participationType) {
        this.EntityName = entityName;
        this.Cardinality = cardinality;
        this.ParticipationType = participationType;
    }

    public String getEntityName() {
        return EntityName;
    }

    public void setEntityName(String entityName) {
        this.EntityName = entityName;
    }

    public String getCardinality() {
        return Cardinality;
    }

    public void setCardinality(String cardinality) {
        this.Cardinality = cardinality;
    }

    public String getParticipationType() {
        return ParticipationType;
    }

    public void setParticipationType(String participationType) {
        this.ParticipationType = participationType;
    }

    @Override
    public String toString() {
        String data = "\t{\n";
        
        data += "\tEntityName: " + this.EntityName + "\n";
        data += "\tCardinality: " + this.Cardinality + "\n";
        data += "\tParticipationType: " + this.ParticipationType + "\n";
        data += "\t}\n";
        
        return data;
    }
    
    
    
}
