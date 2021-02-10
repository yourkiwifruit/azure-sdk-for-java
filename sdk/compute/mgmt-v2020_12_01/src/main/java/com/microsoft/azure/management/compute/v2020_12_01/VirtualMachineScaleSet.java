/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2020_12_01;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.management.compute.v2020_12_01.implementation.VirtualMachineScaleSetInner;
import com.microsoft.azure.arm.model.Indexable;
import com.microsoft.azure.arm.model.Updatable;
import com.microsoft.azure.arm.model.Appliable;
import com.microsoft.azure.arm.model.Creatable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.compute.v2020_12_01.implementation.ComputeManager;
import java.util.Map;
import com.microsoft.azure.SubResource;
import java.util.List;

/**
 * Type representing VirtualMachineScaleSet.
 */
public interface VirtualMachineScaleSet extends HasInner<VirtualMachineScaleSetInner>, Indexable, Updatable<VirtualMachineScaleSet.Update>, HasManager<ComputeManager> {
    /**
     * @return the additionalCapabilities value.
     */
    AdditionalCapabilities additionalCapabilities();

    /**
     * @return the automaticRepairsPolicy value.
     */
    AutomaticRepairsPolicy automaticRepairsPolicy();

    /**
     * @return the doNotRunExtensionsOnOverprovisionedVMs value.
     */
    Boolean doNotRunExtensionsOnOverprovisionedVMs();

    /**
     * @return the extendedLocation value.
     */
    ExtendedLocation extendedLocation();

    /**
     * @return the hostGroup value.
     */
    SubResource hostGroup();

    /**
     * @return the id value.
     */
    String id();

    /**
     * @return the identity value.
     */
    VirtualMachineScaleSetIdentity identity();

    /**
     * @return the location value.
     */
    String location();

    /**
     * @return the name value.
     */
    String name();

    /**
     * @return the orchestrationMode value.
     */
    OrchestrationMode orchestrationMode();

    /**
     * @return the overprovision value.
     */
    Boolean overprovision();

    /**
     * @return the plan value.
     */
    Plan plan();

    /**
     * @return the platformFaultDomainCount value.
     */
    Integer platformFaultDomainCount();

    /**
     * @return the provisioningState value.
     */
    String provisioningState();

    /**
     * @return the proximityPlacementGroup value.
     */
    SubResource proximityPlacementGroup();

    /**
     * @return the scaleInPolicy value.
     */
    ScaleInPolicy scaleInPolicy();

    /**
     * @return the singlePlacementGroup value.
     */
    Boolean singlePlacementGroup();

    /**
     * @return the sku value.
     */
    Sku sku();

    /**
     * @return the tags value.
     */
    Map<String, String> tags();

    /**
     * @return the type value.
     */
    String type();

    /**
     * @return the uniqueId value.
     */
    String uniqueId();

    /**
     * @return the upgradePolicy value.
     */
    UpgradePolicy upgradePolicy();

    /**
     * @return the virtualMachineProfile value.
     */
    VirtualMachineScaleSetVMProfile virtualMachineProfile();

    /**
     * @return the zoneBalance value.
     */
    Boolean zoneBalance();

    /**
     * @return the zones value.
     */
    List<String> zones();

    /**
     * The entirety of the VirtualMachineScaleSet definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithResourceGroup, DefinitionStages.WithLocation, DefinitionStages.WithCreate {
    }

    /**
     * Grouping of VirtualMachineScaleSet definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of a VirtualMachineScaleSet definition.
         */
        interface Blank extends WithResourceGroup {
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify Location.
         */
        interface WithResourceGroup {
           /**
            * Specifies resourceGroupName.
            * @param resourceGroupName The name of the resource group
            * @return the next definition stage
            */
            WithLocation withExistingResourceGroup(String resourceGroupName);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify Location.
         */
        interface WithLocation {
           /**
            * Specifies location.
            * @param location Resource location
            * @return the next definition stage
            */
            WithCreate withLocation(String location);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify AdditionalCapabilities.
         */
        interface WithAdditionalCapabilities {
            /**
             * Specifies additionalCapabilities.
             * @param additionalCapabilities Specifies additional capabilities enabled or disabled on the Virtual Machines in the Virtual Machine Scale Set. For instance: whether the Virtual Machines have the capability to support attaching managed data disks with UltraSSD_LRS storage account type
             * @return the next definition stage
             */
            WithCreate withAdditionalCapabilities(AdditionalCapabilities additionalCapabilities);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify AutomaticRepairsPolicy.
         */
        interface WithAutomaticRepairsPolicy {
            /**
             * Specifies automaticRepairsPolicy.
             * @param automaticRepairsPolicy Policy for automatic repairs
             * @return the next definition stage
             */
            WithCreate withAutomaticRepairsPolicy(AutomaticRepairsPolicy automaticRepairsPolicy);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify DoNotRunExtensionsOnOverprovisionedVMs.
         */
        interface WithDoNotRunExtensionsOnOverprovisionedVMs {
            /**
             * Specifies doNotRunExtensionsOnOverprovisionedVMs.
             * @param doNotRunExtensionsOnOverprovisionedVMs When Overprovision is enabled, extensions are launched only on the requested number of VMs which are finally kept. This property will hence ensure that the extensions do not run on the extra overprovisioned VMs
             * @return the next definition stage
             */
            WithCreate withDoNotRunExtensionsOnOverprovisionedVMs(Boolean doNotRunExtensionsOnOverprovisionedVMs);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify ExtendedLocation.
         */
        interface WithExtendedLocation {
            /**
             * Specifies extendedLocation.
             * @param extendedLocation The extended location of the Virtual Machine Scale Set
             * @return the next definition stage
             */
            WithCreate withExtendedLocation(ExtendedLocation extendedLocation);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify HostGroup.
         */
        interface WithHostGroup {
            /**
             * Specifies hostGroup.
             * @param hostGroup Specifies information about the dedicated host group that the virtual machine scale set resides in. &lt;br&gt;&lt;br&gt;Minimum api-version: 2020-06-01
             * @return the next definition stage
             */
            WithCreate withHostGroup(SubResource hostGroup);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify Identity.
         */
        interface WithIdentity {
            /**
             * Specifies identity.
             * @param identity The identity of the virtual machine scale set, if configured
             * @return the next definition stage
             */
            WithCreate withIdentity(VirtualMachineScaleSetIdentity identity);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify OrchestrationMode.
         */
        interface WithOrchestrationMode {
            /**
             * Specifies orchestrationMode.
             * @param orchestrationMode Specifies the orchestration mode for the virtual machine scale set. Possible values include: 'Uniform', 'Flexible'
             * @return the next definition stage
             */
            WithCreate withOrchestrationMode(OrchestrationMode orchestrationMode);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify Overprovision.
         */
        interface WithOverprovision {
            /**
             * Specifies overprovision.
             * @param overprovision Specifies whether the Virtual Machine Scale Set should be overprovisioned
             * @return the next definition stage
             */
            WithCreate withOverprovision(Boolean overprovision);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify Plan.
         */
        interface WithPlan {
            /**
             * Specifies plan.
             * @param plan Specifies information about the marketplace image used to create the virtual machine. This element is only used for marketplace images. Before you can use a marketplace image from an API, you must enable the image for programmatic use.  In the Azure portal, find the marketplace image that you want to use and then click **Want to deploy programmatically, Get Started -&gt;**. Enter any required information and then click **Save**
             * @return the next definition stage
             */
            WithCreate withPlan(Plan plan);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify PlatformFaultDomainCount.
         */
        interface WithPlatformFaultDomainCount {
            /**
             * Specifies platformFaultDomainCount.
             * @param platformFaultDomainCount Fault Domain count for each placement group
             * @return the next definition stage
             */
            WithCreate withPlatformFaultDomainCount(Integer platformFaultDomainCount);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify ProximityPlacementGroup.
         */
        interface WithProximityPlacementGroup {
            /**
             * Specifies proximityPlacementGroup.
             * @param proximityPlacementGroup Specifies information about the proximity placement group that the virtual machine scale set should be assigned to. &lt;br&gt;&lt;br&gt;Minimum api-version: 2018-04-01
             * @return the next definition stage
             */
            WithCreate withProximityPlacementGroup(SubResource proximityPlacementGroup);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify ScaleInPolicy.
         */
        interface WithScaleInPolicy {
            /**
             * Specifies scaleInPolicy.
             * @param scaleInPolicy Specifies the scale-in policy that decides which virtual machines are chosen for removal when a Virtual Machine Scale Set is scaled-in
             * @return the next definition stage
             */
            WithCreate withScaleInPolicy(ScaleInPolicy scaleInPolicy);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify SinglePlacementGroup.
         */
        interface WithSinglePlacementGroup {
            /**
             * Specifies singlePlacementGroup.
             * @param singlePlacementGroup When true this limits the scale set to a single placement group, of max size 100 virtual machines. NOTE: If singlePlacementGroup is true, it may be modified to false. However, if singlePlacementGroup is false, it may not be modified to true
             * @return the next definition stage
             */
            WithCreate withSinglePlacementGroup(Boolean singlePlacementGroup);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify Sku.
         */
        interface WithSku {
            /**
             * Specifies sku.
             * @param sku The virtual machine scale set sku
             * @return the next definition stage
             */
            WithCreate withSku(Sku sku);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify Tags.
         */
        interface WithTags {
            /**
             * Specifies tags.
             * @param tags Resource tags
             * @return the next definition stage
             */
            WithCreate withTags(Map<String, String> tags);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify UpgradePolicy.
         */
        interface WithUpgradePolicy {
            /**
             * Specifies upgradePolicy.
             * @param upgradePolicy The upgrade policy
             * @return the next definition stage
             */
            WithCreate withUpgradePolicy(UpgradePolicy upgradePolicy);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify VirtualMachineProfile.
         */
        interface WithVirtualMachineProfile {
            /**
             * Specifies virtualMachineProfile.
             * @param virtualMachineProfile The virtual machine profile
             * @return the next definition stage
             */
            WithCreate withVirtualMachineProfile(VirtualMachineScaleSetVMProfile virtualMachineProfile);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify ZoneBalance.
         */
        interface WithZoneBalance {
            /**
             * Specifies zoneBalance.
             * @param zoneBalance Whether to force strictly even Virtual Machine distribution cross x-zones in case there is zone outage
             * @return the next definition stage
             */
            WithCreate withZoneBalance(Boolean zoneBalance);
        }

        /**
         * The stage of the virtualmachinescaleset definition allowing to specify Zones.
         */
        interface WithZones {
            /**
             * Specifies zones.
             * @param zones The virtual machine scale set zones. NOTE: Availability zones can only be set when you create the scale set
             * @return the next definition stage
             */
            WithCreate withZones(List<String> zones);
        }

        /**
         * The stage of the definition which contains all the minimum required inputs for
         * the resource to be created (via {@link WithCreate#create()}), but also allows
         * for any other optional settings to be specified.
         */
        interface WithCreate extends Creatable<VirtualMachineScaleSet>, DefinitionStages.WithAdditionalCapabilities, DefinitionStages.WithAutomaticRepairsPolicy, DefinitionStages.WithDoNotRunExtensionsOnOverprovisionedVMs, DefinitionStages.WithExtendedLocation, DefinitionStages.WithHostGroup, DefinitionStages.WithIdentity, DefinitionStages.WithOrchestrationMode, DefinitionStages.WithOverprovision, DefinitionStages.WithPlan, DefinitionStages.WithPlatformFaultDomainCount, DefinitionStages.WithProximityPlacementGroup, DefinitionStages.WithScaleInPolicy, DefinitionStages.WithSinglePlacementGroup, DefinitionStages.WithSku, DefinitionStages.WithTags, DefinitionStages.WithUpgradePolicy, DefinitionStages.WithVirtualMachineProfile, DefinitionStages.WithZoneBalance, DefinitionStages.WithZones {
        }
    }
    /**
     * The template for a VirtualMachineScaleSet update operation, containing all the settings that can be modified.
     */
    interface Update extends Appliable<VirtualMachineScaleSet>, UpdateStages.WithAdditionalCapabilities, UpdateStages.WithAutomaticRepairsPolicy, UpdateStages.WithDoNotRunExtensionsOnOverprovisionedVMs, UpdateStages.WithIdentity, UpdateStages.WithOverprovision, UpdateStages.WithPlan, UpdateStages.WithProximityPlacementGroup, UpdateStages.WithScaleInPolicy, UpdateStages.WithSinglePlacementGroup, UpdateStages.WithSku, UpdateStages.WithTags, UpdateStages.WithUpgradePolicy, UpdateStages.WithVirtualMachineProfile {
    }

    /**
     * Grouping of VirtualMachineScaleSet update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the virtualmachinescaleset update allowing to specify AdditionalCapabilities.
         */
        interface WithAdditionalCapabilities {
            /**
             * Specifies additionalCapabilities.
             * @param additionalCapabilities Specifies additional capabilities enabled or disabled on the Virtual Machines in the Virtual Machine Scale Set. For instance: whether the Virtual Machines have the capability to support attaching managed data disks with UltraSSD_LRS storage account type
             * @return the next update stage
             */
            Update withAdditionalCapabilities(AdditionalCapabilities additionalCapabilities);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify AutomaticRepairsPolicy.
         */
        interface WithAutomaticRepairsPolicy {
            /**
             * Specifies automaticRepairsPolicy.
             * @param automaticRepairsPolicy Policy for automatic repairs
             * @return the next update stage
             */
            Update withAutomaticRepairsPolicy(AutomaticRepairsPolicy automaticRepairsPolicy);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify DoNotRunExtensionsOnOverprovisionedVMs.
         */
        interface WithDoNotRunExtensionsOnOverprovisionedVMs {
            /**
             * Specifies doNotRunExtensionsOnOverprovisionedVMs.
             * @param doNotRunExtensionsOnOverprovisionedVMs When Overprovision is enabled, extensions are launched only on the requested number of VMs which are finally kept. This property will hence ensure that the extensions do not run on the extra overprovisioned VMs
             * @return the next update stage
             */
            Update withDoNotRunExtensionsOnOverprovisionedVMs(Boolean doNotRunExtensionsOnOverprovisionedVMs);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify Identity.
         */
        interface WithIdentity {
            /**
             * Specifies identity.
             * @param identity The identity of the virtual machine scale set, if configured
             * @return the next update stage
             */
            Update withIdentity(VirtualMachineScaleSetIdentity identity);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify Overprovision.
         */
        interface WithOverprovision {
            /**
             * Specifies overprovision.
             * @param overprovision Specifies whether the Virtual Machine Scale Set should be overprovisioned
             * @return the next update stage
             */
            Update withOverprovision(Boolean overprovision);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify Plan.
         */
        interface WithPlan {
            /**
             * Specifies plan.
             * @param plan The purchase plan when deploying a virtual machine scale set from VM Marketplace images
             * @return the next update stage
             */
            Update withPlan(Plan plan);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify ProximityPlacementGroup.
         */
        interface WithProximityPlacementGroup {
            /**
             * Specifies proximityPlacementGroup.
             * @param proximityPlacementGroup Specifies information about the proximity placement group that the virtual machine scale set should be assigned to. &lt;br&gt;&lt;br&gt;Minimum api-version: 2018-04-01
             * @return the next update stage
             */
            Update withProximityPlacementGroup(SubResource proximityPlacementGroup);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify ScaleInPolicy.
         */
        interface WithScaleInPolicy {
            /**
             * Specifies scaleInPolicy.
             * @param scaleInPolicy Specifies the scale-in policy that decides which virtual machines are chosen for removal when a Virtual Machine Scale Set is scaled-in
             * @return the next update stage
             */
            Update withScaleInPolicy(ScaleInPolicy scaleInPolicy);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify SinglePlacementGroup.
         */
        interface WithSinglePlacementGroup {
            /**
             * Specifies singlePlacementGroup.
             * @param singlePlacementGroup When true this limits the scale set to a single placement group, of max size 100 virtual machines. NOTE: If singlePlacementGroup is true, it may be modified to false. However, if singlePlacementGroup is false, it may not be modified to true
             * @return the next update stage
             */
            Update withSinglePlacementGroup(Boolean singlePlacementGroup);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify Sku.
         */
        interface WithSku {
            /**
             * Specifies sku.
             * @param sku The virtual machine scale set sku
             * @return the next update stage
             */
            Update withSku(Sku sku);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify Tags.
         */
        interface WithTags {
            /**
             * Specifies tags.
             * @param tags Resource tags
             * @return the next update stage
             */
            Update withTags(Map<String, String> tags);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify UpgradePolicy.
         */
        interface WithUpgradePolicy {
            /**
             * Specifies upgradePolicy.
             * @param upgradePolicy The upgrade policy
             * @return the next update stage
             */
            Update withUpgradePolicy(UpgradePolicy upgradePolicy);
        }

        /**
         * The stage of the virtualmachinescaleset update allowing to specify VirtualMachineProfile.
         */
        interface WithVirtualMachineProfile {
            /**
             * Specifies virtualMachineProfile.
             * @param virtualMachineProfile The virtual machine profile
             * @return the next update stage
             */
            Update withVirtualMachineProfile(VirtualMachineScaleSetUpdateVMProfile virtualMachineProfile);
        }

    }
}
