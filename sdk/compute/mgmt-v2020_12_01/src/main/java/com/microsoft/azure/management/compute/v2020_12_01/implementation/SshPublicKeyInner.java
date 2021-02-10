/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2020_12_01.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains information about SSH certificate public key and the path on the
 * Linux VM where the public key is placed.
 */
public class SshPublicKeyInner {
    /**
     * Specifies the full path on the created VM where ssh public key is
     * stored. If the file already exists, the specified key is appended to the
     * file. Example: /home/user/.ssh/authorized_keys.
     */
    @JsonProperty(value = "path")
    private String path;

    /**
     * SSH public key certificate used to authenticate with the VM through ssh.
     * The key needs to be at least 2048-bit and in ssh-rsa format.
     * &lt;br&gt;&lt;br&gt; For creating ssh keys, see [Create SSH keys on
     * Linux and Mac for Linux VMs in
     * Azure](https://docs.microsoft.com/en-us/azure/virtual-machines/linux/mac-create-ssh-keys?toc=%2fazure%2fvirtual-machines%2flinux%2ftoc.json).
     */
    @JsonProperty(value = "keyData")
    private String keyData;

    /**
     * Get specifies the full path on the created VM where ssh public key is stored. If the file already exists, the specified key is appended to the file. Example: /home/user/.ssh/authorized_keys.
     *
     * @return the path value
     */
    public String path() {
        return this.path;
    }

    /**
     * Set specifies the full path on the created VM where ssh public key is stored. If the file already exists, the specified key is appended to the file. Example: /home/user/.ssh/authorized_keys.
     *
     * @param path the path value to set
     * @return the SshPublicKeyInner object itself.
     */
    public SshPublicKeyInner withPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Get sSH public key certificate used to authenticate with the VM through ssh. The key needs to be at least 2048-bit and in ssh-rsa format. &lt;br&gt;&lt;br&gt; For creating ssh keys, see [Create SSH keys on Linux and Mac for Linux VMs in Azure](https://docs.microsoft.com/en-us/azure/virtual-machines/linux/mac-create-ssh-keys?toc=%2fazure%2fvirtual-machines%2flinux%2ftoc.json).
     *
     * @return the keyData value
     */
    public String keyData() {
        return this.keyData;
    }

    /**
     * Set sSH public key certificate used to authenticate with the VM through ssh. The key needs to be at least 2048-bit and in ssh-rsa format. &lt;br&gt;&lt;br&gt; For creating ssh keys, see [Create SSH keys on Linux and Mac for Linux VMs in Azure](https://docs.microsoft.com/en-us/azure/virtual-machines/linux/mac-create-ssh-keys?toc=%2fazure%2fvirtual-machines%2flinux%2ftoc.json).
     *
     * @param keyData the keyData value to set
     * @return the SshPublicKeyInner object itself.
     */
    public SshPublicKeyInner withKeyData(String keyData) {
        this.keyData = keyData;
        return this;
    }

}
