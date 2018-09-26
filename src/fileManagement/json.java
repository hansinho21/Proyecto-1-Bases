/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManagement;

import domain.Attribute;
import domain.EntitySet;
import domain.ParticipationEntity;
import domain.RelationshipSets;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ACER
 */
public class json {

    public LinkedList<EntitySet> getEntitySets(String address) {
        LinkedList<EntitySet> entitySetList = new LinkedList<>();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(address));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray entitySets = (JSONArray) jsonObject.get("EntitySets");
            Iterator<JSONObject> entitySetsIterator = entitySets.iterator();

            while (entitySetsIterator.hasNext()) {
                JSONObject auxJson = entitySetsIterator.next();
                EntitySet auxEntity = new EntitySet();

                auxEntity.setName((String) auxJson.get("Name"));
                auxEntity.setType((String) auxJson.get("Type"));
                auxEntity.setParentEntitySet((String) auxJson.get("ParentEntitySet"));

                JSONArray attributes = (JSONArray) auxJson.get("Attributes");
                Iterator<JSONObject> attributesIterator = attributes.iterator();
                LinkedList<Attribute> attributesList = new LinkedList<>();
                while (attributesIterator.hasNext()) {
                    JSONObject auxJsonAttributes = attributesIterator.next();
                    Attribute auxAttribute = new Attribute();

                    auxAttribute.setName((String) auxJsonAttributes.get("Name"));
                    auxAttribute.setDomain((String) auxJsonAttributes.get("Domain"));
                    auxAttribute.setType((String) auxJsonAttributes.get("Type"));
                    if (auxAttribute.getType().equalsIgnoreCase("Composed")) {
                        JSONArray components = (JSONArray) auxJsonAttributes.get("ComponentList");
                        Iterator<JSONObject> componentsIterator = components.iterator();
                        LinkedList<Attribute> componentList = new LinkedList<>();
                        while (componentsIterator.hasNext()) {
                            JSONObject auxJsonComponents = componentsIterator.next();
                            Attribute auxComponent = new Attribute();

                            auxComponent.setName((String) auxJsonComponents.get("Name"));
                            auxComponent.setDomain((String) auxJsonComponents.get("Domain"));
                            auxComponent.setType((String) auxJsonComponents.get("Type"));
                            auxComponent.setComponentList(new LinkedList<>());
                            auxComponent.setIsPrimary((Boolean) auxJsonComponents.get("IsPrimary"));
                            auxComponent.setIsDiscriminator((Boolean) auxJsonComponents.get("IsDiscriminator"));
                            auxComponent.setPrecision((long) auxJsonComponents.get("Precision"));

                            componentList.add(auxComponent);
                        }
                        auxAttribute.setComponentList(componentList);
                    } else {
                        auxAttribute.setComponentList(new LinkedList<>());
                    }
                    auxAttribute.setIsPrimary((Boolean) auxJsonAttributes.get("IsPrimary"));
                    auxAttribute.setIsDiscriminator((Boolean) auxJsonAttributes.get("IsDiscriminator"));
                    auxAttribute.setPrecision((long) auxJsonAttributes.get("Precision"));

                    attributesList.add(auxAttribute);
                }
                auxEntity.setAttributesList(attributesList);
                entitySetList.add(auxEntity);
            }

        } catch (IOException ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
        }

        return entitySetList;
    }

    public LinkedList<RelationshipSets> getRelationshipSets(String address) {
        LinkedList<RelationshipSets> relationshipSetsList = new LinkedList<>();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(address));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray relationshipSets = (JSONArray) jsonObject.get("RelationshipSets");
            Iterator<JSONObject> relationshipSetsIterator = relationshipSets.iterator();

            while (relationshipSetsIterator.hasNext()) {
                JSONObject auxJson = relationshipSetsIterator.next();
                RelationshipSets auxRelation = new RelationshipSets();

                auxRelation.setName((String) auxJson.get("Name"));
                auxRelation.setType((String) auxJson.get("Type"));

                JSONArray descriptiveAttributes = (JSONArray) auxJson.get("DescriptiveAttributes");
                if (descriptiveAttributes != null) {
                    Iterator<JSONObject> descriptiveAttributesIterator = descriptiveAttributes.iterator();
                    LinkedList<Attribute> descriptiveAttributesList = new LinkedList<>();
                    while (descriptiveAttributesIterator.hasNext()) {
                        JSONObject auxJsonAttributes = descriptiveAttributesIterator.next();
                        Attribute auxAttribute = new Attribute();

                        auxAttribute.setName((String) auxJsonAttributes.get("Name"));
                        auxAttribute.setDomain((String) auxJsonAttributes.get("Domain"));
                        auxAttribute.setType((String) auxJsonAttributes.get("Type"));
                        if (auxAttribute.getType().equalsIgnoreCase("Composed")) {
                            JSONArray components = (JSONArray) auxJsonAttributes.get("ComponentList");
                            Iterator<JSONObject> componentsIterator = components.iterator();
                            LinkedList<Attribute> componentList = new LinkedList<>();
                            while (componentsIterator.hasNext()) {
                                JSONObject auxJsonComponents = componentsIterator.next();
                                Attribute auxComponent = new Attribute();

                                auxComponent.setName((String) auxJsonComponents.get("Name"));
                                auxComponent.setDomain((String) auxJsonComponents.get("Domain"));
                                auxComponent.setType((String) auxJsonComponents.get("Type"));
                                auxComponent.setComponentList(new LinkedList<>());
                                auxComponent.setIsPrimary((Boolean) auxJsonComponents.get("IsPrimary"));
                                auxComponent.setIsDiscriminator((Boolean) auxJsonComponents.get("IsDiscriminator"));
                                auxComponent.setPrecision((long) auxJsonComponents.get("Precision"));

                                componentList.add(auxComponent);
                            }
                            auxAttribute.setComponentList(componentList);
                        } else {
                            auxAttribute.setComponentList(new LinkedList<>());
                        }
                        auxAttribute.setIsPrimary((Boolean) auxJsonAttributes.get("IsPrimary"));
                        auxAttribute.setIsDiscriminator((Boolean) auxJsonAttributes.get("IsDiscriminator"));
                        auxAttribute.setPrecision((long) auxJsonAttributes.get("Precision"));

                        descriptiveAttributesList.add(auxAttribute);
                    }
                    auxRelation.setDescriptiveAttributesList(descriptiveAttributesList);
                } else {
                    auxRelation.setDescriptiveAttributesList(new LinkedList<>());
                }

//                JSONArray participatiosEntities = (JSONArray) auxJson.get("ParticipationEntities");
//                Iterator<JSONObject> participatiosEntitiesIterator = participatiosEntities.iterator();
//                LinkedList<ParticipationEntity> participationEntityList = new LinkedList<>();
//                while (relationshipSetsIterator.hasNext()) {
//                    JSONObject auxJsonParticipation = participatiosEntitiesIterator.next();
//                    ParticipationEntity auxParticipationEntity = new ParticipationEntity();
//
//                    auxParticipationEntity.setEntityName((String) auxJsonParticipation.get("EntityName"));
//                    auxParticipationEntity.setCardinality((String) auxJsonParticipation.get("Cardinality"));
//                    auxParticipationEntity.setParticipationType((String) auxJsonParticipation.get("ParticipationType"));
//                
//                    participationEntityList.add(auxParticipationEntity);
//                }
//                auxRelation.setParticipationEntitiesList(participationEntityList);
                auxRelation.setParticipationEntitiesList(new LinkedList<>());
                relationshipSetsList.add(auxRelation);
            }

        } catch (IOException ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
        }

        return relationshipSetsList;
    }

    public void relationshipSets(String address) {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(address));

            JSONObject jsonObject = (JSONObject) obj;

            System.out.println("{");

            String name = (String) jsonObject.get("Name");
            System.out.println("  \"Name\": " + name);

            String type = (String) jsonObject.get("Type");
            System.out.println("  \"Type\": " + type);

            JSONArray descriptiveAttributes = (JSONArray) jsonObject.get("DescriptiveAttributes");
            Iterator<JSONObject> iteratorDescriptiveAttributes = descriptiveAttributes.iterator();
            System.out.println("  \"DescriptiveAttributes\": [");
            while (iteratorDescriptiveAttributes.hasNext()) {
                System.out.println("\t{");

                JSONObject auxJson = iteratorDescriptiveAttributes.next();

                String nameDescriptiveAttributes = (String) auxJson.get("Name");
                System.out.println("\t  \"Name\": " + nameDescriptiveAttributes);

                String domainDescriptiveAttributes = (String) auxJson.get("Domain");
                System.out.println("\t  \"Domain\": " + domainDescriptiveAttributes);

                String typeDescriptiveAttributes = (String) auxJson.get("Type");
                System.out.println("\t  \"Type\": " + typeDescriptiveAttributes);

                String componentListDescriptiveAttributes = (String) auxJson.get("ComponentList");
                System.out.println("\t  \"ComponentList\": " + componentListDescriptiveAttributes);

                Boolean isPrimaryDescriptiveAttributes = (Boolean) auxJson.get("IsPrimary");
                System.out.println("\t  \"IsPrimary\": " + isPrimaryDescriptiveAttributes);

                Boolean isDiscriminatorDescriptiveAttributes = (Boolean) auxJson.get("IsDiscriminator");
                System.out.println("\t  \"IsDiscriminator\": " + isDiscriminatorDescriptiveAttributes);

                Long precisionDescriptiveAttributes = (Long) auxJson.get("Precision");
                System.out.println("\t  \"Precision\": " + precisionDescriptiveAttributes);

                System.out.println("\t}");
            }
            System.out.println("  ]");

            JSONArray participationEntities = (JSONArray) jsonObject.get("ParticipationEntities");
            Iterator<JSONObject> iteratorParticipationEntities = participationEntities.iterator();
            System.out.println("  \"ParticipationEntities\": [");
            while (iteratorParticipationEntities.hasNext()) {
                System.out.println("\t{");

                JSONObject auxJson = iteratorParticipationEntities.next();

                String entityNameDescriptiveAttributes = (String) auxJson.get("EntityName");
                System.out.println("\t  \"EntityName\": " + entityNameDescriptiveAttributes);

                String cardinalityDescriptiveAttributes = (String) auxJson.get("Cardinality");
                System.out.println("\t  \"Cardinality\": " + cardinalityDescriptiveAttributes);

                String participationTypeDescriptiveAttributes = (String) auxJson.get("ParticipationType");
                System.out.println("\t  \"ParticipationType\": " + participationTypeDescriptiveAttributes);

                System.out.println("\t}");
            }
            System.out.println("  ]");

            System.out.println("}");

        } catch (IOException ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
