// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.security.attestation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The result of a call to retrieve policy certificates. */
@Fluent
public final class PolicyCertificatesResult {
    /*
     * SHA256 Hash of the binary representation certificate which was added or
     * removed
     */
    @JsonProperty(value = "x-ms-policy-certificates")
    private JsonWebKeySet policyCertificates;

    /**
     * Get the policyCertificates property: SHA256 Hash of the binary representation certificate which was added or
     * removed.
     *
     * @return the policyCertificates value.
     */
    public JsonWebKeySet getPolicyCertificates() {
        return this.policyCertificates;
    }

    /**
     * Set the policyCertificates property: SHA256 Hash of the binary representation certificate which was added or
     * removed.
     *
     * @param policyCertificates the policyCertificates value to set.
     * @return the PolicyCertificatesResult object itself.
     */
    public PolicyCertificatesResult setPolicyCertificates(JsonWebKeySet policyCertificates) {
        this.policyCertificates = policyCertificates;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getPolicyCertificates() != null) {
            getPolicyCertificates().validate();
        }
    }
}
