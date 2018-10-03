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
    
    private String Name;
    private String Type;
    private String ParentEntitySet; 
    private LinkedList<Attribute> Attributes;

    public EntitySet() {
    }

    public EntitySet(String name, String type, String parentEntitySet, LinkedList<Attribute> attributesList) {
        this.Name = name;
        this.Type = type;
        this.ParentEntitySet = parentEntitySet;
        this.Attributes = attributesList;
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

    public String getParentEntitySet() {
        return ParentEntitySet;
    }

    public void setParentEntitySet(String parentEntitySet) {
        this.ParentEntitySet = parentEntitySet;
    }

    public LinkedList<Attribute> getAttributesList() {
        return Attributes;
    }

    public void setAttributesList(LinkedList<Attribute> attributesList) {
        this.Attributes = attributesList;
    }

    @Override
    public String toString() {
        String result = "";
        result += "    {\n";
        result += "      Name: " + this.Name + "\n";
        result += "      Type: " + this.Type + "\n";
        result += "      ParentEntitySet: " + this.ParentEntitySet + "\n";
        result += "      Attributes: " + this.Attributes.toString() + "\n";
        result += "      ]\n";
        return result;
    }
    
    
    
}
