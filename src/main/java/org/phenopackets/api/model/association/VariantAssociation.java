package org.phenopackets.api.model.association;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.phenopackets.api.model.association.PhenotypeAssociation.Builder;
import org.phenopackets.api.model.entity.Entity;
import org.phenopackets.api.model.entity.Variant;
import org.phenopackets.api.model.evidence.Evidence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;

@JsonDeserialize(builder = VariantAssociation.Builder.class)
@JsonPropertyOrder({"entity", "variant", "evidence", "contributor", "date"})
public class VariantAssociation implements Association {

	private final Variant variant;
	private final String entityId;
	private final String contributorId;
	private final String date;
	private final List<Evidence> evidence;

    private VariantAssociation(Builder builder) {
        this.variant = builder.variant;
        this.entityId = builder.entityId;
        this.contributorId = builder.contributorId;
        this.date = builder.date;
        this.evidence = ImmutableList.copyOf(builder.evidence);
    }

	public Variant getVariant() {
		return variant;
	}

    @Override
    public String getEntityId() {
        return entityId;
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
	public List<Evidence> getEvidence() {
		return evidence;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantAssociation that = (VariantAssociation) o;
        return Objects.equals(variant, that.variant) &&
                Objects.equals(entityId, that.entityId) &&
                Objects.equals(evidence, that.evidence) &&
                Objects.equals(contributorId, that.contributorId) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variant, entityId, evidence, contributorId, date);
    }

    @Override
    public String toString() {
        return "VariantAssociation{" +
                "phenotype=" + variant +
                ", entityId=" + entityId +
                ", evidence=" + evidence +
                ", contributorId=" + contributorId +
                ", date=" + date +
                '}';
    }

    public static class Builder {

        private final Variant variant;

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
        public Builder(@JsonProperty("variant") Variant variant) {
            this.variant = variant;
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

        public VariantAssociation build() {
            return new VariantAssociation(this);
        }
    }
}
