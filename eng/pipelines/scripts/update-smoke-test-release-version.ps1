[CmdletBinding()]
param (
    [Parameter(Mandatory=$True)][string] $ArtifactsPath,
    [Parameter(Mandatory=$True)][string] $ArtifactName
)

if (!(Test-Path $ArtifactsPath)) {
  throw "Expected $ArtifactsPath to exist"
}

$smokeTestPomPath = "./common/smoke-tests/pom.xml"

$pkg = Get-ChildItem "$ArtifactsPath/$ArtifactName/*.pom" | Select-Object -First 1
[xml]$packagePom = Get-Content $pkg
$releaseVersion = $packagePom.project.version

[xml]$smokeTestPom = Get-Content $smokeTestPomPath
$smokeTestPom.project.dependencies.GetEnumerator() |
  Where-Object { $_.artifactId -eq $ArtifactName } |
  ForEach-Object { $_.version = $releaseVersion }

$smokeTestPom.Save($smokeTestPomPath)
