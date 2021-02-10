/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2020_12_01;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is the replication status of the gallery Image Version.
 */
public class ReplicationStatus {
    /**
     * This is the aggregated replication status based on all the regional
     * replication status flags. Possible values include: 'Unknown',
     * 'InProgress', 'Completed', 'Failed'.
     */
    @JsonProperty(value = "aggregatedState", access = JsonProperty.Access.WRITE_ONLY)
    private AggregatedReplicationState aggregatedState;

    /**
     * This is a summary of replication status for each region.
     */
    @JsonProperty(value = "summary", access = JsonProperty.Access.WRITE_ONLY)
    private List<RegionalReplicationStatus> summary;

    /**
     * Get this is the aggregated replication status based on all the regional replication status flags. Possible values include: 'Unknown', 'InProgress', 'Completed', 'Failed'.
     *
     * @return the aggregatedState value
     */
    public AggregatedReplicationState aggregatedState() {
        return this.aggregatedState;
    }

    /**
     * Get this is a summary of replication status for each region.
     *
     * @return the summary value
     */
    public List<RegionalReplicationStatus> summary() {
        return this.summary;
    }

}
