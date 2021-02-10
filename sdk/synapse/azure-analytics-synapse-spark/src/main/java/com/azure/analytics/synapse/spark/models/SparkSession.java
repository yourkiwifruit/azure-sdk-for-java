// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.synapse.spark.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/** The SparkSession model. */
@Fluent
public final class SparkSession {
    /*
     * The livyInfo property.
     */
    @JsonProperty(value = "livyInfo")
    private SparkSessionState livyInfo;

    /*
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * The workspaceName property.
     */
    @JsonProperty(value = "workspaceName")
    private String workspaceName;

    /*
     * The sparkPoolName property.
     */
    @JsonProperty(value = "sparkPoolName")
    private String sparkPoolName;

    /*
     * The submitterName property.
     */
    @JsonProperty(value = "submitterName")
    private String submitterName;

    /*
     * The submitterId property.
     */
    @JsonProperty(value = "submitterId")
    private String submitterId;

    /*
     * The artifactId property.
     */
    @JsonProperty(value = "artifactId")
    private String artifactId;

    /*
     * The job type.
     */
    @JsonProperty(value = "jobType")
    private SparkJobType jobType;

    /*
     * The result property.
     */
    @JsonProperty(value = "result")
    private SparkSessionResultType result;

    /*
     * The schedulerInfo property.
     */
    @JsonProperty(value = "schedulerInfo")
    private SparkScheduler scheduler;

    /*
     * The pluginInfo property.
     */
    @JsonProperty(value = "pluginInfo")
    private SparkServicePlugin plugin;

    /*
     * The errorInfo property.
     */
    @JsonProperty(value = "errorInfo")
    private List<SparkServiceError> errors;

    /*
     * Dictionary of <string>
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /*
     * The id property.
     */
    @JsonProperty(value = "id", required = true)
    private int id;

    /*
     * The appId property.
     */
    @JsonProperty(value = "appId")
    private String appId;

    /*
     * Dictionary of <string>
     */
    @JsonProperty(value = "appInfo")
    private Map<String, String> appInfo;

    /*
     * The state property.
     */
    @JsonProperty(value = "state")
    private String state;

    /*
     * The log property.
     */
    @JsonProperty(value = "log")
    private List<String> logLines;

    /**
     * Get the livyInfo property: The livyInfo property.
     *
     * @return the livyInfo value.
     */
    public SparkSessionState getLivyInfo() {
        return this.livyInfo;
    }

    /**
     * Set the livyInfo property: The livyInfo property.
     *
     * @param livyInfo the livyInfo value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setLivyInfo(SparkSessionState livyInfo) {
        this.livyInfo = livyInfo;
        return this;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the workspaceName property: The workspaceName property.
     *
     * @return the workspaceName value.
     */
    public String getWorkspaceName() {
        return this.workspaceName;
    }

    /**
     * Set the workspaceName property: The workspaceName property.
     *
     * @param workspaceName the workspaceName value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
        return this;
    }

    /**
     * Get the sparkPoolName property: The sparkPoolName property.
     *
     * @return the sparkPoolName value.
     */
    public String getSparkPoolName() {
        return this.sparkPoolName;
    }

    /**
     * Set the sparkPoolName property: The sparkPoolName property.
     *
     * @param sparkPoolName the sparkPoolName value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setSparkPoolName(String sparkPoolName) {
        this.sparkPoolName = sparkPoolName;
        return this;
    }

    /**
     * Get the submitterName property: The submitterName property.
     *
     * @return the submitterName value.
     */
    public String getSubmitterName() {
        return this.submitterName;
    }

    /**
     * Set the submitterName property: The submitterName property.
     *
     * @param submitterName the submitterName value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
        return this;
    }

    /**
     * Get the submitterId property: The submitterId property.
     *
     * @return the submitterId value.
     */
    public String getSubmitterId() {
        return this.submitterId;
    }

    /**
     * Set the submitterId property: The submitterId property.
     *
     * @param submitterId the submitterId value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
        return this;
    }

    /**
     * Get the artifactId property: The artifactId property.
     *
     * @return the artifactId value.
     */
    public String getArtifactId() {
        return this.artifactId;
    }

    /**
     * Set the artifactId property: The artifactId property.
     *
     * @param artifactId the artifactId value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    /**
     * Get the jobType property: The job type.
     *
     * @return the jobType value.
     */
    public SparkJobType getJobType() {
        return this.jobType;
    }

    /**
     * Set the jobType property: The job type.
     *
     * @param jobType the jobType value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setJobType(SparkJobType jobType) {
        this.jobType = jobType;
        return this;
    }

    /**
     * Get the result property: The result property.
     *
     * @return the result value.
     */
    public SparkSessionResultType getResult() {
        return this.result;
    }

    /**
     * Set the result property: The result property.
     *
     * @param result the result value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setResult(SparkSessionResultType result) {
        this.result = result;
        return this;
    }

    /**
     * Get the scheduler property: The schedulerInfo property.
     *
     * @return the scheduler value.
     */
    public SparkScheduler getScheduler() {
        return this.scheduler;
    }

    /**
     * Set the scheduler property: The schedulerInfo property.
     *
     * @param scheduler the scheduler value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setScheduler(SparkScheduler scheduler) {
        this.scheduler = scheduler;
        return this;
    }

    /**
     * Get the plugin property: The pluginInfo property.
     *
     * @return the plugin value.
     */
    public SparkServicePlugin getPlugin() {
        return this.plugin;
    }

    /**
     * Set the plugin property: The pluginInfo property.
     *
     * @param plugin the plugin value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setPlugin(SparkServicePlugin plugin) {
        this.plugin = plugin;
        return this;
    }

    /**
     * Get the errors property: The errorInfo property.
     *
     * @return the errors value.
     */
    public List<SparkServiceError> getErrors() {
        return this.errors;
    }

    /**
     * Set the errors property: The errorInfo property.
     *
     * @param errors the errors value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setErrors(List<SparkServiceError> errors) {
        this.errors = errors;
        return this;
    }

    /**
     * Get the tags property: Dictionary of &lt;string&gt;.
     *
     * @return the tags value.
     */
    public Map<String, String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: Dictionary of &lt;string&gt;.
     *
     * @param tags the tags value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Get the appId property: The appId property.
     *
     * @return the appId value.
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * Set the appId property: The appId property.
     *
     * @param appId the appId value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    /**
     * Get the appInfo property: Dictionary of &lt;string&gt;.
     *
     * @return the appInfo value.
     */
    public Map<String, String> getAppInfo() {
        return this.appInfo;
    }

    /**
     * Set the appInfo property: Dictionary of &lt;string&gt;.
     *
     * @param appInfo the appInfo value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setAppInfo(Map<String, String> appInfo) {
        this.appInfo = appInfo;
        return this;
    }

    /**
     * Get the state property: The state property.
     *
     * @return the state value.
     */
    public String getState() {
        return this.state;
    }

    /**
     * Set the state property: The state property.
     *
     * @param state the state value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setState(String state) {
        this.state = state;
        return this;
    }

    /**
     * Get the logLines property: The log property.
     *
     * @return the logLines value.
     */
    public List<String> getLogLines() {
        return this.logLines;
    }

    /**
     * Set the logLines property: The log property.
     *
     * @param logLines the logLines value to set.
     * @return the SparkSession object itself.
     */
    public SparkSession setLogLines(List<String> logLines) {
        this.logLines = logLines;
        return this;
    }
}
