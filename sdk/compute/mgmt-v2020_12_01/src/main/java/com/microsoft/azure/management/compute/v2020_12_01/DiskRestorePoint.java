/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2020_12_01;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.management.compute.v2020_12_01.implementation.DiskRestorePointInner;
import com.microsoft.azure.arm.model.Indexable;
import com.microsoft.azure.arm.model.Refreshable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.compute.v2020_12_01.implementation.ComputeManager;
import org.joda.time.DateTime;

/**
 * Type representing DiskRestorePoint.
 */
public interface DiskRestorePoint extends HasInner<DiskRestorePointInner>, Indexable, Refreshable<DiskRestorePoint>, HasManager<ComputeManager> {
    /**
     * @return the encryption value.
     */
    Encryption encryption();

    /**
     * @return the familyId value.
     */
    String familyId();

    /**
     * @return the hyperVGeneration value.
     */
    HyperVGeneration hyperVGeneration();

    /**
     * @return the id value.
     */
    String id();

    /**
     * @return the name value.
     */
    String name();

    /**
     * @return the osType value.
     */
    OperatingSystemTypes osType();

    /**
     * @return the purchasePlan value.
     */
    PurchasePlan purchasePlan();

    /**
     * @return the sourceResourceId value.
     */
    String sourceResourceId();

    /**
     * @return the sourceUniqueId value.
     */
    String sourceUniqueId();

    /**
     * @return the timeCreated value.
     */
    DateTime timeCreated();

    /**
     * @return the type value.
     */
    String type();

}
