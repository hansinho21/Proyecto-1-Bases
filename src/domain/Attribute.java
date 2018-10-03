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
        String data = "        {\n";
        
        data += "          Name: " + this.Name + "\n";
        data += "          Domain: " + this.Domain + "\n";
        data += "          Type: " + this.Type + "\n";
        
        if(this.ComponentList != null){
            data += "          ComponentList: [\n";
            for (int i = 0; i < this.ComponentList.size(); i++) {
                data += "          {\n";
                data += "            Name: " + this.ComponentList.get(i).Name + "\n";
                data += "            Domain: " + this.ComponentList.get(i).Domain + "\n";
                data += "            Type: " + this.ComponentList.get(i).Type + "\n";
                data += "            ComponentList: null\n";
                data += "            IsPrimary: " + this.ComponentList.get(i).IsPrimary + "\n";
                data += "            IsDiscriminator: " + this.ComponentList.get(i).IsDiscriminator + "\n";
                data += "            Precision: " + this.ComponentList.get(i).Precision + "\n";
                data += "          }\n";
            }
            data += "          ]\n";
        } else {
            data += "          ComponentList: null\n";
        }
        
        data += "          IsPrimary: " + this.IsPrimary + "\n";
        data += "          IsDiscriminator: " + this.IsDiscriminator + "\n";
        data += "          Precision: " + this.Precision + "\n";
        
        data += "          }\n";
        return data;
    }
    
    
    
}
