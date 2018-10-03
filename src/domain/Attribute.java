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
public class Attribute {
    
    private String Name;
    private String Domain;
    private String Type;
    private LinkedList<Attribute> ComponentList;
    private boolean IsPrimary;
    private boolean IsDiscriminator;
    private long Precision;

    public Attribute() {
    }

    public Attribute(String name, String domain, String type, LinkedList<Attribute> componentList, boolean isPrimary, boolean isDiscriminator, long precision) {
        this.Name = name;
        this.Domain = domain;
        this.Type = type;
        this.ComponentList = componentList;
        this.IsPrimary = isPrimary;
        this.IsDiscriminator = isDiscriminator;
        this.Precision = precision;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        this.Domain = domain;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public LinkedList<Attribute> getComponentList() {
        return ComponentList;
    }

    public void setComponentList(LinkedList<Attribute> componentList) {
        this.ComponentList = componentList;
    }

    public boolean isIsPrimary() {
        return IsPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.IsPrimary = isPrimary;
    }

    public boolean isIsDiscriminator() {
        return IsDiscriminator;
    }

    public void setIsDiscriminator(boolean isDiscriminator) {
        this.IsDiscriminator = isDiscriminator;
    }

    public long getPrecision() {
        return Precision;
    }

    public void setPrecision(long precision) {
        this.Precision = precision;
    }

    @Override
    public String toString() {
        String data = "\n{\n";
        
        data += "\tName: " + this.Name + "\n";
        data += "\tDomain: " + this.Domain + "\n";
        data += "\tType: " + this.Type + "\n";
        
        if(this.ComponentList != null){
            data += "\tComponentList: [\n";
            for (int i = 0; i < this.ComponentList.size(); i++) {
                data += "\t  {\n";
                data += "\t\tName: " + this.ComponentList.get(i).Name + "\n";
                data += "\t\tDomain: " + this.ComponentList.get(i).Domain + "\n";
                data += "\t\tType: " + this.ComponentList.get(i).Type + "\n";
                data += "\t\tComponentList: null\n";
                data += "\t\tIsPrimary: " + this.ComponentList.get(i).IsPrimary + "\n";
                data += "\t\tIsDiscriminator: " + this.ComponentList.get(i).IsDiscriminator + "\n";
                data += "\t\tPrecision: " + this.ComponentList.get(i).Precision + "\n";
                data += "\t  }\n";
            }
            data += "\t]\n";
        } else {
            data += "\tComponentList: null\n";
        }
        
        data += "\tIsPrimary: " + this.IsPrimary + "\n";
        data += "\tIsDiscriminator: " + this.IsDiscriminator + "\n";
        data += "\tPrecision: " + this.Precision + "\n";
        
        data += "}";
        return data;
    }
    
    
    
}
