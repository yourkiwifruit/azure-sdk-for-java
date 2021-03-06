/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.resourcegraph.v2019_04_01;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for ResultTruncated.
 */
public enum ResultTruncated {
    /** Enum value true. */
    TRUE("true"),

    /** Enum value false. */
    FALSE("false");

    /** The actual serialized value for a ResultTruncated instance. */
    private String value;

    ResultTruncated(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a ResultTruncated instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed ResultTruncated object, or null if unable to parse.
     */
    @JsonCreator
    public static ResultTruncated fromString(String value) {
        ResultTruncated[] items = ResultTruncated.values();
        for (ResultTruncated item : items) {
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
