/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.loganalytics.v2020_08_01;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * The top level Log Analytics cluster resource container.
 */
@JsonFlatten
public class ClusterPatch {
    /**
     * The associated key properties.
     */
    @JsonProperty(value = "properties.keyVaultProperties")
    private KeyVaultProperties keyVaultProperties;

    /**
     * The sku properties.
     */
    @JsonProperty(value = "sku")
    private ClusterSku sku;

    /**
     * Resource tags.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * Get the associated key properties.
     *
     * @return the keyVaultProperties value
     */
    public KeyVaultProperties keyVaultProperties() {
        return this.keyVaultProperties;
    }

    /**
     * Set the associated key properties.
     *
     * @param keyVaultProperties the keyVaultProperties value to set
     * @return the ClusterPatch object itself.
     */
    public ClusterPatch withKeyVaultProperties(KeyVaultProperties keyVaultProperties) {
        this.keyVaultProperties = keyVaultProperties;
        return this;
    }

    /**
     * Get the sku properties.
     *
     * @return the sku value
     */
    public ClusterSku sku() {
        return this.sku;
    }

    /**
     * Set the sku properties.
     *
     * @param sku the sku value to set
     * @return the ClusterPatch object itself.
     */
    public ClusterPatch withSku(ClusterSku sku) {
        this.sku = sku;
        return this;
    }

    /**
     * Get resource tags.
     *
     * @return the tags value
     */
    public Map<String, String> tags() {
        return this.tags;
    }

    /**
     * Set resource tags.
     *
     * @param tags the tags value to set
     * @return the ClusterPatch object itself.
     */
    public ClusterPatch withTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

}
