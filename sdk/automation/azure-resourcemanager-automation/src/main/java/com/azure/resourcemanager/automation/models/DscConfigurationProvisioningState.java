// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.automation.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Defines values for DscConfigurationProvisioningState. */
public enum DscConfigurationProvisioningState {
    /** Enum value Succeeded. */
    SUCCEEDED("Succeeded");

    /** The actual serialized value for a DscConfigurationProvisioningState instance. */
    private final String value;

    DscConfigurationProvisioningState(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a DscConfigurationProvisioningState instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed DscConfigurationProvisioningState object, or null if unable to parse.
     */
    @JsonCreator
    public static DscConfigurationProvisioningState fromString(String value) {
        DscConfigurationProvisioningState[] items = DscConfigurationProvisioningState.values();
        for (DscConfigurationProvisioningState item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
