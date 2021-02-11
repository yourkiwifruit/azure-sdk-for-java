<#
    .SYNOPSIS
        Generates a JSON object representing an Azure Pipelines Job Matrix.
        See https://docs.microsoft.com/en-us/azure/devops/pipelines/process/phases?view=azure-devops&tabs=yaml#parallelexec

    .EXAMPLE
    .\eng\scripts\Create-JobMatrix $context
#>

[CmdletBinding()]
param (
    [Parameter(Mandatory=$True)][string] $ConfigPath,
    [Parameter(Mandatory=$True)][string] $Selection,
    [Parameter(Mandatory=$False)][string] $DisplayNameFilter,
    [Parameter(Mandatory=$False)][array] $Filters,
    [Parameter(Mandatory=$False)][array] $NonSparseParameters
)

. $PSScriptRoot/job-matrix-functions.ps1

$config = GetMatrixConfigFromJson (Get-Content $ConfigPath)
# Strip empty string filters in order to be able to use azure pipelines yaml join()
$Filters = $Filters | Where-Object { $_ }


Write-Verbose "Generating matrix with options:"
Write-Verbose "===================================================="
Write-Verbose "ConfigPath: $ConfigPath"
Write-Verbose "Selection: $Selection"
Write-Verbose "DisplayNameFilter: $DisplayNameFilter"
Write-Verbose "Filters: $($Filters -join " ")"
Write-Verbose "NonSparseParameters: $($NonSparseParameters -join " ")"
Write-Verbose "===================================================="

[array]$matrix = GenerateMatrix `
    -config $config `
    -selectFromMatrixType $Selection `
    -displayNameFilter $DisplayNameFilter `
    -filters $Filters `
    -nonSparseParameters $NonSparseParameters

$serialized = SerializePipelineMatrix $matrix

Write-Output $serialized.pretty
Write-Output "##vso[task.setVariable variable=matrix;isOutput=true]$($serialized.compressed)"
