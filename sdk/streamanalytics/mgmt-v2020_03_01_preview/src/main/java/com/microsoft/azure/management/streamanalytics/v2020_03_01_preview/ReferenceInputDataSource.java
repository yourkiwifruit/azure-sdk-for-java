/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.streamanalytics.v2020_03_01_preview;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * Describes an input data source that contains reference data.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = ReferenceInputDataSource.class)
@JsonTypeName("ReferenceInputDataSource")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Microsoft.Storage/Blob", value = BlobReferenceInputDataSource.class),
    @JsonSubTypes.Type(name = "Microsoft.Sql/Server/Database", value = AzureSqlReferenceInputDataSource.class)
})
public class ReferenceInputDataSource {
}
