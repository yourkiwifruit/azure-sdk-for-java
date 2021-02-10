/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.synapse.v2020_12_01.implementation;

import com.microsoft.azure.management.synapse.v2020_12_01.ManagedIdentitySqlControlSettingsModel;
import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.synapse.v2020_12_01.ManagedIdentitySqlControlSettingsModelPropertiesGrantSqlControlToManagedIdentity;

class ManagedIdentitySqlControlSettingsModelImpl extends WrapperImpl<ManagedIdentitySqlControlSettingsModelInner> implements ManagedIdentitySqlControlSettingsModel {
    private final SynapseManager manager;
    ManagedIdentitySqlControlSettingsModelImpl(ManagedIdentitySqlControlSettingsModelInner inner, SynapseManager manager) {
        super(inner);
        this.manager = manager;
    }

    @Override
    public SynapseManager manager() {
        return this.manager;
    }

    @Override
    public ManagedIdentitySqlControlSettingsModelPropertiesGrantSqlControlToManagedIdentity grantSqlControlToManagedIdentity() {
        return this.inner().grantSqlControlToManagedIdentity();
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

}
