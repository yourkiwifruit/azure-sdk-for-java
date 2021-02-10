/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2020_12_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data used when creating a disk.
 */
public class CreationData {
    /**
     * This enumerates the possible sources of a disk's creation. Possible
     * values include: 'Empty', 'Attach', 'FromImage', 'Import', 'Copy',
     * 'Restore', 'Upload'.
     */
    @JsonProperty(value = "createOption", required = true)
    private DiskCreateOption createOption;

    /**
     * Required if createOption is Import. The Azure Resource Manager
     * identifier of the storage account containing the blob to import as a
     * disk.
     */
    @JsonProperty(value = "storageAccountId")
    private String storageAccountId;

    /**
     * Disk source information.
     */
    @JsonProperty(value = "imageReference")
    private ImageDiskReference imageReference;

    /**
     * Required if creating from a Gallery Image. The id of the
     * ImageDiskReference will be the ARM id of the shared galley image version
     * from which to create a disk.
     */
    @JsonProperty(value = "galleryImageReference")
    private ImageDiskReference galleryImageReference;

    /**
     * If createOption is Import, this is the URI of a blob to be imported into
     * a managed disk.
     */
    @JsonProperty(value = "sourceUri")
    private String sourceUri;

    /**
     * If createOption is Copy, this is the ARM id of the source snapshot or
     * disk.
     */
    @JsonProperty(value = "sourceResourceId")
    private String sourceResourceId;

    /**
     * If this field is set, this is the unique id identifying the source of
     * this resource.
     */
    @JsonProperty(value = "sourceUniqueId", access = JsonProperty.Access.WRITE_ONLY)
    private String sourceUniqueId;

    /**
     * If createOption is Upload, this is the size of the contents of the
     * upload including the VHD footer. This value should be between 20972032
     * (20 MiB + 512 bytes for the VHD footer) and 35183298347520 bytes (32 TiB
     * + 512 bytes for the VHD footer).
     */
    @JsonProperty(value = "uploadSizeBytes")
    private Long uploadSizeBytes;

    /**
     * Logical sector size in bytes for Ultra disks. Supported values are 512
     * ad 4096. 4096 is the default.
     */
    @JsonProperty(value = "logicalSectorSize")
    private Integer logicalSectorSize;

    /**
     * Get this enumerates the possible sources of a disk's creation. Possible values include: 'Empty', 'Attach', 'FromImage', 'Import', 'Copy', 'Restore', 'Upload'.
     *
     * @return the createOption value
     */
    public DiskCreateOption createOption() {
        return this.createOption;
    }

    /**
     * Set this enumerates the possible sources of a disk's creation. Possible values include: 'Empty', 'Attach', 'FromImage', 'Import', 'Copy', 'Restore', 'Upload'.
     *
     * @param createOption the createOption value to set
     * @return the CreationData object itself.
     */
    public CreationData withCreateOption(DiskCreateOption createOption) {
        this.createOption = createOption;
        return this;
    }

    /**
     * Get required if createOption is Import. The Azure Resource Manager identifier of the storage account containing the blob to import as a disk.
     *
     * @return the storageAccountId value
     */
    public String storageAccountId() {
        return this.storageAccountId;
    }

    /**
     * Set required if createOption is Import. The Azure Resource Manager identifier of the storage account containing the blob to import as a disk.
     *
     * @param storageAccountId the storageAccountId value to set
     * @return the CreationData object itself.
     */
    public CreationData withStorageAccountId(String storageAccountId) {
        this.storageAccountId = storageAccountId;
        return this;
    }

    /**
     * Get disk source information.
     *
     * @return the imageReference value
     */
    public ImageDiskReference imageReference() {
        return this.imageReference;
    }

    /**
     * Set disk source information.
     *
     * @param imageReference the imageReference value to set
     * @return the CreationData object itself.
     */
    public CreationData withImageReference(ImageDiskReference imageReference) {
        this.imageReference = imageReference;
        return this;
    }

    /**
     * Get required if creating from a Gallery Image. The id of the ImageDiskReference will be the ARM id of the shared galley image version from which to create a disk.
     *
     * @return the galleryImageReference value
     */
    public ImageDiskReference galleryImageReference() {
        return this.galleryImageReference;
    }

    /**
     * Set required if creating from a Gallery Image. The id of the ImageDiskReference will be the ARM id of the shared galley image version from which to create a disk.
     *
     * @param galleryImageReference the galleryImageReference value to set
     * @return the CreationData object itself.
     */
    public CreationData withGalleryImageReference(ImageDiskReference galleryImageReference) {
        this.galleryImageReference = galleryImageReference;
        return this;
    }

    /**
     * Get if createOption is Import, this is the URI of a blob to be imported into a managed disk.
     *
     * @return the sourceUri value
     */
    public String sourceUri() {
        return this.sourceUri;
    }

    /**
     * Set if createOption is Import, this is the URI of a blob to be imported into a managed disk.
     *
     * @param sourceUri the sourceUri value to set
     * @return the CreationData object itself.
     */
    public CreationData withSourceUri(String sourceUri) {
        this.sourceUri = sourceUri;
        return this;
    }

    /**
     * Get if createOption is Copy, this is the ARM id of the source snapshot or disk.
     *
     * @return the sourceResourceId value
     */
    public String sourceResourceId() {
        return this.sourceResourceId;
    }

    /**
     * Set if createOption is Copy, this is the ARM id of the source snapshot or disk.
     *
     * @param sourceResourceId the sourceResourceId value to set
     * @return the CreationData object itself.
     */
    public CreationData withSourceResourceId(String sourceResourceId) {
        this.sourceResourceId = sourceResourceId;
        return this;
    }

    /**
     * Get if this field is set, this is the unique id identifying the source of this resource.
     *
     * @return the sourceUniqueId value
     */
    public String sourceUniqueId() {
        return this.sourceUniqueId;
    }

    /**
     * Get if createOption is Upload, this is the size of the contents of the upload including the VHD footer. This value should be between 20972032 (20 MiB + 512 bytes for the VHD footer) and 35183298347520 bytes (32 TiB + 512 bytes for the VHD footer).
     *
     * @return the uploadSizeBytes value
     */
    public Long uploadSizeBytes() {
        return this.uploadSizeBytes;
    }

    /**
     * Set if createOption is Upload, this is the size of the contents of the upload including the VHD footer. This value should be between 20972032 (20 MiB + 512 bytes for the VHD footer) and 35183298347520 bytes (32 TiB + 512 bytes for the VHD footer).
     *
     * @param uploadSizeBytes the uploadSizeBytes value to set
     * @return the CreationData object itself.
     */
    public CreationData withUploadSizeBytes(Long uploadSizeBytes) {
        this.uploadSizeBytes = uploadSizeBytes;
        return this;
    }

    /**
     * Get logical sector size in bytes for Ultra disks. Supported values are 512 ad 4096. 4096 is the default.
     *
     * @return the logicalSectorSize value
     */
    public Integer logicalSectorSize() {
        return this.logicalSectorSize;
    }

    /**
     * Set logical sector size in bytes for Ultra disks. Supported values are 512 ad 4096. 4096 is the default.
     *
     * @param logicalSectorSize the logicalSectorSize value to set
     * @return the CreationData object itself.
     */
    public CreationData withLogicalSectorSize(Integer logicalSectorSize) {
        this.logicalSectorSize = logicalSectorSize;
        return this;
    }

}
