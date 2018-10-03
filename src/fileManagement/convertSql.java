/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManagement;

import domain.Attribute;
import domain.EntitySet;
import domain.JsonObject;
import domain.ParticipationEntity;
import domain.RelationshipSets;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 *
 * @author ACER
 */
public class convertSql {

    private LinkedList<EntitySet> entitySetList;
    private LinkedList<RelationshipSets> relationshipSetList;

    private json json;

    public convertSql(String address) throws FileNotFoundException {
        this.json = new json(address);
        initialize();
    }
    
    private void initialize() throws FileNotFoundException{
        JsonObject jsonObject = this.json.readJson();
        this.entitySetList = jsonObject.getEntitySets();
        this.relationshipSetList = jsonObject.getRelationshipSets();
    }

    public String createRelationshipTables(RelationshipSets relationshipSets) {
        String result = "";

        if (relationshipSets.getType().equalsIgnoreCase("Strong")) {
            result += strongRelationshipTable(relationshipSets);
        }

        return result;
    }

    public String createEntityTables(EntitySet entitySet) {

        String result = "";

        if (isStrongEntity(entitySet)) {
            result += strongEntityTable(entitySet);
        }
        if (isChildEntity(entitySet)) {
            result += childEntityTable(entitySet);
        }

        if (isWeakEntity(entitySet)) {
//            result += weakEntityTable(entitySet);
        }

        return result;

    }

    private String weakEntityTable(EntitySet entitySet) {
        String table = "";
        LinkedList<String> primaryKeyList = new LinkedList<>();
        LinkedList<Attribute> foreignKeyList = new LinkedList<>();
        EntitySet entityStrong = new EntitySet();

        //Obtener entidad a la que está relacionada la entidad debil
        for (int i = 0; i < this.relationshipSetList.size(); i++) {
            LinkedList<ParticipationEntity> auxParticipationEntityList = this.relationshipSetList.get(i).getParticipationEntitiesList();
            for (int j = 0; j < auxParticipationEntityList.size(); j++) {
                if(existEntity(entitySet.getName()) == true){
                    if(!auxParticipationEntityList.get(j).getEntityName().equals(entitySet.getName())){
                        entityStrong = getEntitySet(auxParticipationEntityList.get(j).getEntityName());
                    }
                }
            }
        }
        
        LinkedList<Attribute> entityStrongKeys = getPrimaryKeysEdited(entityStrong.getName());

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
        
        table += ", ";
        for (int i = 0; i < entityStrongKeys.size(); i++) {
            table += entityStrongKeys.get(i).getName();
            if (i != entityStrongKeys.size() - 1) {
                table += ", ";
            }
        }
        table += ")\n";
        
        foreignKeyList = getPrimaryKeys(entityStrong.getName());

        table += "\tFOREIGN KEY (";
        for (int i = 0; i < foreignKeyList.size(); i++) {
            table += foreignKeyList.get(i).getName();
            if (i != foreignKeyList.size() - 1) {
                table += ", ";
            }
        }
        table += ") REFERENCES " + entityStrong.getName()+ "\n";

        table += ");\n\n";

        return table;
    }

    private String childEntityTable(EntitySet entitySet) {
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
        table += ") REFERENCES " + entitySet.getParentEntitySet() + "\n";

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

    private String strongRelationshipTable(RelationshipSets relationshipSets) {
        String result = "";
        LinkedList<String> primaryKeyList = new LinkedList<>();

        result += "CREATE TABLE " + relationshipSets.getName() + "(\n";

        //Atributos
        for (int i = 0; i < relationshipSets.getParticipationEntitiesList().size(); i++) {
            ParticipationEntity auxParticipationEntity = relationshipSets.getParticipationEntitiesList().get(i);
            LinkedList<Attribute> auxAttributeList = getPrimaryKeysEdited(auxParticipationEntity.getEntityName());
            for (int j = 0; j < auxAttributeList.size(); j++) {
                Attribute auxAttribute = auxAttributeList.get(j);
                result += "\t" + auxAttribute.getName() + " " + auxAttribute.getDomain();
                if (auxAttribute.getPrecision() != 0) {
                    result += "(" + auxAttribute.getPrecision() + "),\n";
                } else {
                    result += ",\n";
                }
            }
        }

        //Primary key
        for (int i = 0; i < relationshipSets.getParticipationEntitiesList().size(); i++) {
            ParticipationEntity auxParticipationEntity = relationshipSets.getParticipationEntitiesList().get(i);
            if (auxParticipationEntity.getCardinality().equalsIgnoreCase("many")) {
                LinkedList<Attribute> auxPrimaryKeyList = getPrimaryKeys(auxParticipationEntity.getEntityName());
                for (int j = 0; j < auxPrimaryKeyList.size(); j++) {
                    primaryKeyList.add(auxPrimaryKeyList.get(j).getName());
                }
            }
        }

        result += "\tPRIMARY KEY (";
        for (int i = 0; i < primaryKeyList.size(); i++) {
            result += primaryKeyList.get(i);
            if (i != primaryKeyList.size() - 1) {
                result += ", ";
            }
        }
        result += ")\n";

        //Foreign key
        for (int i = 0; i < relationshipSets.getParticipationEntitiesList().size(); i++) {
            result += "\tFOREIGN KEY (";
            ParticipationEntity auxParticipationEntity = relationshipSets.getParticipationEntitiesList().get(i);
            LinkedList<Attribute> auxPrimaryKeyList = getPrimaryKeys(auxParticipationEntity.getEntityName());
            LinkedList<Attribute> auxPrimaryKeyEditedList = getPrimaryKeysEdited(auxParticipationEntity.getEntityName());
            for (int j = 0; j < auxPrimaryKeyEditedList.size(); j++) {
                result += auxPrimaryKeyEditedList.get(j).getName();
                if (j != auxPrimaryKeyEditedList.size() - 1) {
                    result += ", ";
                }
            }
            result += ") REFERENCES " + auxParticipationEntity.getEntityName() + " (";
            for (int j = 0; j < auxPrimaryKeyList.size(); j++) {
                result += auxPrimaryKeyList.get(j).getName();
                if (j != auxPrimaryKeyList.size() - 1) {
                    result += ", ";
                }
            }
            result += ")\n";
        }

        result += ");\n\n";

        return result;
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
    
    private EntitySet getEntitySet(String name){
        for (int i = 0; i < this.entitySetList.size(); i++) {
            if(this.entitySetList.get(i).getName().equals(name)){
                return this.entitySetList.get(i);
            }
        }
        return null;
    }

    private LinkedList<Attribute> getPrimaryKeysEdited(String entityName) {
        LinkedList<Attribute> primaryKeyList = new LinkedList<>();
        EntitySet auxEntitySet = new EntitySet();

        for (int i = 0; i < this.entitySetList.size(); i++) {
            if (entityName.equals(this.entitySetList.get(i).getName())) {
                auxEntitySet = this.entitySetList.get(i);
            }
        }
        for (int i = 0; i < auxEntitySet.getAttributesList().size(); i++) {
            Attribute attribute = auxEntitySet.getAttributesList().get(i);
            boolean aiuda = false;
            if (attribute.isIsPrimary() == true && aiuda == false) {
                aiuda = true;
                Attribute auxAttribute = attribute;
                auxAttribute.setName(attribute.getName() + "_" + entityName);
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

    private boolean existEntity(String name) {
        for (int i = 0; i < this.relationshipSetList.size(); i++) {
            LinkedList<ParticipationEntity> auxParticipationEntityList = this.relationshipSetList.get(i).getParticipationEntitiesList();
            for (int j = 0; j < auxParticipationEntityList.size(); j++) {
                if(auxParticipationEntityList.get(j).getEntityName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }
}
