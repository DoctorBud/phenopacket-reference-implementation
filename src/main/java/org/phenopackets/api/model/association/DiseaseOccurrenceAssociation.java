package org.phenopackets.api.model.association;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.phenopackets.api.model.association.PhenotypeAssociation.Builder;
import org.phenopackets.api.model.condition.DiseaseOccurrence;
import org.phenopackets.api.model.entity.Entity;
import org.phenopackets.api.model.evidence.Evidence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = DiseaseOccurrenceAssociation.Builder.class)
@JsonPropertyOrder({"entity", "disease_occurrence", "evidence", "contributor", "date"})
public class DiseaseOccurrenceAssociation implements Association {

    private final DiseaseOccurrence diseaseOccurrence;
    private final String entityId;
    private final List<Evidence> evidences;
    private final String contributorId;
	private final String date;


    private DiseaseOccurrenceAssociation(Builder builder) {
        this.diseaseOccurrence = builder.disease;
        this.entityId = builder.entityId;
        this.evidences = builder.evidence;
        this.contributorId = builder.contributorId;
        this.date = builder.date;
    }

    /**
     * @return the disease
     */
    @JsonProperty("disease_occurrence")
    public DiseaseOccurrence getDiseaseOcurrence() {
        return diseaseOccurrence;
    }

    @Override
    public String getEntityId() {
        return entityId;
    }

    @Override
    public List<Evidence> getEvidence() {
        return evidences;
    }
    
    @Override
    public String getContributorId() {
        return contributorId;
    }
    
    @Override
    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiseaseOccurrenceAssociation)) return false;
        DiseaseOccurrenceAssociation that = (DiseaseOccurrenceAssociation) o;
        return Objects.equals(diseaseOccurrence, that.diseaseOccurrence) &&
                Objects.equals(entityId, that.entityId) &&
                Objects.equals(evidences, that.evidences) &&
                Objects.equals(contributorId, that.contributorId) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseOccurrence, entityId, evidences, contributorId, date);
    }

    @Override
    public String toString() {
        return "DiseaseOccurrenceAssociation{" +
                "diseaseOccurrence=" + diseaseOccurrence +
                ", entityId='" + entityId + '\'' +
                ", evidences=" + evidences +
                ", contributorId=" + contributorId +
                ", date=" + date +
                '}';
    }

    public static class Builder {

        private final DiseaseOccurrence disease;

        @JsonProperty("entity")
        private String entityId;
        @JsonProperty("contributor")
        private String contributorId;
        @JsonProperty("date")
        private String date;
        @JsonProperty
        @JsonInclude(Include.NON_EMPTY)
        private List<Evidence> evidence = new ArrayList<>();

        @JsonCreator
        public Builder(@JsonProperty("disease_occurrence") DiseaseOccurrence disease) {
            this.disease = disease;
        }

        public Builder setEntity(Entity entity) {
            this.entityId = entity.getId();
            return this;
        }

        public Builder setEntityId(String entityId) {
            this.entityId = entityId;
            return this;
        }
        
        public Builder setContributorId(String contributorId) {
        	this.contributorId = contributorId;
        	return this;
        }
        
        public Builder setDate(String date) {
        	this.date = date;
        	return this;
        }

        public Builder setEvidence(List<Evidence> evidence) {
            this.evidence = evidence;
            return this;
        }

        public Builder addEvidence(Evidence evidence) {
            this.evidence.add(evidence);
            return this;
        }

        public DiseaseOccurrenceAssociation build() {
            return new DiseaseOccurrenceAssociation(this);
        }
    }
}
