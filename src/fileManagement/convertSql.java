/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManagement;

import domain.Attribute;
import domain.EntitySet;
import domain.RelationshipSets;
import java.util.LinkedList;

/**
 *
 * @author ACER
 */
public class convertSql {

    private LinkedList<EntitySet> entitySetList;
    private LinkedList<RelationshipSets> relationshipSetList;

    private json json = new json();

    public convertSql(String address) {
        this.entitySetList = this.json.getEntitySets(address);
        this.relationshipSetList = this.json.getRelationshipSets(address);
    }

    public String createTable(EntitySet entitySet) {

        String result = "";

        if (isStrongEntity(entitySet)) {
            result += strongEntityTable(entitySet);
        }
        if (isChildEntity(entitySet)) {
            result += weakEntityTable(entitySet);
        }
        
        if (isWeakEntity(entitySet)) {
            result += weakEntityTable(entitySet);
        }

        return result;

    }

    private String weakEntityTable(EntitySet entitySet) {
        String table = "";
        LinkedList<String> primaryKeyList = new LinkedList<>();
        LinkedList<String> foreignKeyList = new LinkedList<>();

        table += "CREATE TABLE " + entitySet.getName() + "(\n";

        if (entitySet.getParentEntitySet() != null) {
            LinkedList<Attribute> primaryKeyParent = getPrimaryKeys(entitySet.getParentEntitySet());
            for (int i = 0; i < primaryKeyParent.size(); i++) {
                table += "\t" + primaryKeyParent.get(i).getName() + " " + primaryKeyParent.get(i).getDomain();
                if (primaryKeyParent.get(i).getPrecision() != 0) {
                    table += "(" + primaryKeyParent.get(i).getPrecision() + "),\n";
                } else {
                    table += ",\n";
                }
                primaryKeyList.add(primaryKeyParent.get(i).getName());
                foreignKeyList.add(primaryKeyParent.get(i).getName());
            }
        }

        for (int i = 0; i < entitySet.getAttributesList().size(); i++) {
            Attribute auxAttribute = entitySet.getAttributesList().get(i);
            if (auxAttribute.getType().equalsIgnoreCase("Composed")) {
                for (int j = 0; j < auxAttribute.getComponentList().size(); j++) {
                    Attribute compoment = auxAttribute.getComponentList().get(j);
                    table += "\t" + compoment.getName() + " " + compoment.getDomain();
                    if (compoment.getPrecision() != 0) {
                        table += "(" + compoment.getPrecision() + "),\n";
                    } else {
                        table += ",\n";
                    }
                    if (auxAttribute.isIsDiscriminator()) {
                        primaryKeyList.add(compoment.getName());
                    }
                }
            } else {
                table += "\t" + auxAttribute.getName() + " " + auxAttribute.getDomain();
                if (auxAttribute.getPrecision() != 0) {
                    table += "(" + auxAttribute.getPrecision() + "),\n";
                } else {
                    table += ",\n";
                }
            }
            if (auxAttribute.isIsPrimary() == true) {
                primaryKeyList.add(auxAttribute.getName());
            }
        }

        table += "\tPRIMARY KEY (";
        for (int i = 0; i < primaryKeyList.size(); i++) {
            table += primaryKeyList.get(i);
            if (i != primaryKeyList.size() - 1) {
                table += ", ";
            }
        }
        table += ")\n";

        table += "\tFOREIGN KEY (";
        for (int i = 0; i < foreignKeyList.size(); i++) {
            table += foreignKeyList.get(i);
            if (i != foreignKeyList.size() - 1) {
                table += ", ";
            }
        }
        table += ")\n";

        table += ");\n\n";

        return table;
    }

    private String strongEntityTable(EntitySet entitySet) {
        String table = "";
        LinkedList<String> primaryKeyList = new LinkedList<>();

        table += "CREATE TABLE " + entitySet.getName() + "(\n";

        for (int i = 0; i < entitySet.getAttributesList().size(); i++) {
            Attribute auxAttribute = entitySet.getAttributesList().get(i);
            if (auxAttribute.getType().equalsIgnoreCase("Composed")) {
                for (int j = 0; j < auxAttribute.getComponentList().size(); j++) {
                    Attribute compoment = auxAttribute.getComponentList().get(j);
                    table += "\t" + compoment.getName() + " " + compoment.getDomain();
                    if (compoment.getPrecision() != 0) {
                        table += "(" + compoment.getPrecision() + "),\n";
                    } else {
                        table += ",\n";
                    }
                }
            } else {
                table += "\t" + auxAttribute.getName() + " " + auxAttribute.getDomain();
                if (auxAttribute.getPrecision() != 0) {
                    table += "(" + auxAttribute.getPrecision() + "),\n";
                } else {
                    table += ",\n";
                }
            }
            if (auxAttribute.isIsPrimary() == true) {
                primaryKeyList.add(auxAttribute.getName());
            }
        }

        table += "\tPRIMARY KEY (";
        for (int i = 0; i < primaryKeyList.size(); i++) {
            table += primaryKeyList.get(i);
            if (i != primaryKeyList.size() - 1) {
                table += ", ";
            }
        }
        table += ")\n";

        table += ");\n\n";

        return table;
    }

    private LinkedList<Attribute> getPrimaryKeys(String entityName) {
        LinkedList<Attribute> primaryKeyList = new LinkedList<>();
        EntitySet auxEntitySet = new EntitySet();

        for (int i = 0; i < this.entitySetList.size(); i++) {
            if (entityName.equals(this.entitySetList.get(i).getName())) {
                auxEntitySet = this.entitySetList.get(i);
            }
        }

        for (int i = 0; i < auxEntitySet.getAttributesList().size(); i++) {
            Attribute auxAttribute = auxEntitySet.getAttributesList().get(i);
            if (auxAttribute.isIsPrimary() == true) {
                primaryKeyList.add(auxAttribute);
            }
        }

        return primaryKeyList;
    }

    private boolean isStrongEntity(EntitySet entitySet) {
        if (entitySet.getParentEntitySet() != null) {
            return false;
        } else if (entitySet.getParentEntitySet() == null) {
            for (int i = 0; i < entitySet.getAttributesList().size(); i++) {
                Attribute auxAttribute = entitySet.getAttributesList().get(i);
                if (auxAttribute.isIsDiscriminator() == true) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    private boolean isChildEntity(EntitySet entitySet) {
        if (entitySet.getParentEntitySet() != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isWeakEntity(EntitySet entitySet) {
        if (entitySet.getParentEntitySet() == null) {
            for (int i = 0; i < entitySet.getAttributesList().size(); i++) {
                Attribute auxAttribute = entitySet.getAttributesList().get(i);
                if (auxAttribute.isIsDiscriminator() == true) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }

    }
}
