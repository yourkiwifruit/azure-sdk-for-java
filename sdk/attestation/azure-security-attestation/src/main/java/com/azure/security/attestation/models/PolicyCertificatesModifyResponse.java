// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.security.attestation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The response to an attestation policy management API. */
@Fluent
public final class PolicyCertificatesModifyResponse {
    /*
     * An RFC7519 JSON Web Token structure whose body is a
     * PolicyCertificatesModificationResult object.
     */
    @JsonProperty(value = "token")
    private String token;

    /**
     * Get the token property: An RFC7519 JSON Web Token structure whose body is a PolicyCertificatesModificationResult
     * object.
     *
     * @return the token value.
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Set the token property: An RFC7519 JSON Web Token structure whose body is a PolicyCertificatesModificationResult
     * object.
     *
     * @param token the token value to set.
     * @return the PolicyCertificatesModifyResponse object itself.
     */
    public PolicyCertificatesModifyResponse setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
