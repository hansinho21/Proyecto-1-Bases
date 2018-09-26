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
    
    private String name;
    private String domain;
    private String type;
    private LinkedList<Attribute> componentList;
    private boolean isPrimary;
    private boolean isDiscriminator;
    private long precision;

    public Attribute() {
    }

    public Attribute(String name, String domain, String type, LinkedList<Attribute> componentList, boolean isPrimary, boolean isDiscriminator, long precision) {
        this.name = name;
        this.domain = domain;
        this.type = type;
        this.componentList = componentList;
        this.isPrimary = isPrimary;
        this.isDiscriminator = isDiscriminator;
        this.precision = precision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LinkedList<Attribute> getComponentList() {
        return componentList;
    }

    public void setComponentList(LinkedList<Attribute> componentList) {
        this.componentList = componentList;
    }

    public boolean isIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public boolean isIsDiscriminator() {
        return isDiscriminator;
    }

    public void setIsDiscriminator(boolean isDiscriminator) {
        this.isDiscriminator = isDiscriminator;
    }

    public long getPrecision() {
        return precision;
    }

    public void setPrecision(long precision) {
        this.precision = precision;
    }

    @Override
    public String toString() {
        String data = "\n{\n";
        
        data += "\tName: " + this.name + "\n";
        data += "\tDomain: " + this.domain + "\n";
        data += "\tType: " + this.type + "\n";
        
        if(!this.componentList.isEmpty()){
            data += "\tComponentList: [\n";
            for (int i = 0; i < this.componentList.size(); i++) {
                data += "\t  {\n";
                data += "\t\tName: " + this.componentList.get(i).name + "\n";
                data += "\t\tDomain: " + this.componentList.get(i).domain + "\n";
                data += "\t\tType: " + this.componentList.get(i).type + "\n";
                data += "\t\tComponentList: null\n";
                data += "\t\tIsPrimary: " + this.componentList.get(i).isPrimary + "\n";
                data += "\t\tIsDiscriminator: " + this.componentList.get(i).isDiscriminator + "\n";
                data += "\t\tPrecision: " + this.componentList.get(i).precision + "\n";
                data += "\t  }\n";
            }
            data += "\t]\n";
        } else {
            data += "\tComponentList: null\n";
        }
        
        data += "\tIsPrimary: " + this.isPrimary + "\n";
        data += "\tIsDiscriminator: " + this.isDiscriminator + "\n";
        data += "\tPrecision: " + this.precision + "\n";
        
        data += "}";
        return data;
    }
    
    
    
}
