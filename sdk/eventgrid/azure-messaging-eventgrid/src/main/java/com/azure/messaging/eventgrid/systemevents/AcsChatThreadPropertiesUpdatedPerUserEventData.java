// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.messaging.eventgrid.systemevents;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.Map;

/** The AcsChatThreadPropertiesUpdatedPerUserEventData model. */
@Fluent
public final class AcsChatThreadPropertiesUpdatedPerUserEventData extends AcsChatThreadEventBaseProperties {
    /*
     * The MRI of the user who updated the thread properties
     */
    @JsonProperty(value = "editedBy")
    private String editedBy;

    /*
     * The time at which the properties of the thread were updated
     */
    @JsonProperty(value = "editTime")
    private OffsetDateTime editTime;

    /*
     * The updated thread properties
     */
    @JsonProperty(value = "properties")
    private Map<String, Object> properties;

    /**
     * Get the editedBy property: The MRI of the user who updated the thread properties.
     *
     * @return the editedBy value.
     */
    public String getEditedBy() {
        return this.editedBy;
    }

    /**
     * Set the editedBy property: The MRI of the user who updated the thread properties.
     *
     * @param editedBy the editedBy value to set.
     * @return the AcsChatThreadPropertiesUpdatedPerUserEventData object itself.
     */
    public AcsChatThreadPropertiesUpdatedPerUserEventData setEditedBy(String editedBy) {
        this.editedBy = editedBy;
        return this;
    }

    /**
     * Get the editTime property: The time at which the properties of the thread were updated.
     *
     * @return the editTime value.
     */
    public OffsetDateTime getEditTime() {
        return this.editTime;
    }

    /**
     * Set the editTime property: The time at which the properties of the thread were updated.
     *
     * @param editTime the editTime value to set.
     * @return the AcsChatThreadPropertiesUpdatedPerUserEventData object itself.
     */
    public AcsChatThreadPropertiesUpdatedPerUserEventData setEditTime(OffsetDateTime editTime) {
        this.editTime = editTime;
        return this;
    }

    /**
     * Get the properties property: The updated thread properties.
     *
     * @return the properties value.
     */
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: The updated thread properties.
     *
     * @param properties the properties value to set.
     * @return the AcsChatThreadPropertiesUpdatedPerUserEventData object itself.
     */
    public AcsChatThreadPropertiesUpdatedPerUserEventData setProperties(Map<String, Object> properties) {
        this.properties = properties;
        return this;
    }
}