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
public class EntitySet {
    
    private String name;
    private String type;
    private String parentEntitySet; 
    private LinkedList<Attribute> attributesList;

    public EntitySet() {
    }

    public EntitySet(String name, String type, String parentEntitySet, LinkedList<Attribute> attributesList) {
        this.name = name;
        this.type = type;
        this.parentEntitySet = parentEntitySet;
        this.attributesList = attributesList;
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

    public String getParentEntitySet() {
        return parentEntitySet;
    }

    public void setParentEntitySet(String parentEntitySet) {
        this.parentEntitySet = parentEntitySet;
    }

    public LinkedList<Attribute> getAttributesList() {
        return attributesList;
    }

    public void setAttributesList(LinkedList<Attribute> attributesList) {
        this.attributesList = attributesList;
    }

    @Override
    public String toString() {
        return "EntitySet{" + "name=" + name + ", type=" + type + ", parentEntitySet=" + parentEntitySet + ", attributesList=" + attributesList + '}';
    }
    
    
    
}
