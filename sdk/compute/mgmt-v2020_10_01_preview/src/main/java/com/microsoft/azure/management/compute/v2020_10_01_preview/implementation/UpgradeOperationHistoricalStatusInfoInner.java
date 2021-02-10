/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2020_10_01_preview.implementation;

import com.microsoft.azure.management.compute.v2020_10_01_preview.UpgradeOperationHistoricalStatusInfoProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Virtual Machine Scale Set OS Upgrade History operation response.
 */
public class UpgradeOperationHistoricalStatusInfoInner {
    /**
     * Information about the properties of the upgrade operation.
     */
    @JsonProperty(value = "properties", access = JsonProperty.Access.WRITE_ONLY)
    private UpgradeOperationHistoricalStatusInfoProperties properties;

    /**
     * Resource type.
     */
    @JsonProperty(value = "type", access = JsonProperty.Access.WRITE_ONLY)
    private String type;

    /**
     * Resource location.
     */
    @JsonProperty(value = "location", access = JsonProperty.Access.WRITE_ONLY)
    private String location;

    /**
     * Get information about the properties of the upgrade operation.
     *
     * @return the properties value
     */
    public UpgradeOperationHistoricalStatusInfoProperties properties() {
        return this.properties;
    }

    /**
     * Get resource type.
     *
     * @return the type value
     */
    public String type() {
        return this.type;
    }

    /**
     * Get resource location.
     *
     * @return the location value
     */
    public String location() {
        return this.location;
    }

}
