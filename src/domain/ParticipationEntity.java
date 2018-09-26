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
    
    private String entityName;
    private String cardinality;
    private String participationType;

    public ParticipationEntity(String entityName, String cardinality, String participationType) {
        this.entityName = entityName;
        this.cardinality = cardinality;
        this.participationType = participationType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public String getParticipationType() {
        return participationType;
    }

    public void setParticipationType(String participationType) {
        this.participationType = participationType;
    }

    @Override
    public String toString() {
        String data = "\t{\n";
        
        data += "\tEntityName: " + this.entityName + "\n";
        data += "\tCardinality: " + this.cardinality + "\n";
        data += "\tParticipationType: " + this.participationType + "\n";
        data += "\t}\n";
        
        return data;
    }
    
    
    
}
