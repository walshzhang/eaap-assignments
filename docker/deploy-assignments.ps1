$WorkspaceDir = Resolve-Path -Path ..
$ManagementProjectDir = Join-Path $WorkspaceDir 'assignments-management'
$SubmissionProjectDir = Join-Path $WorkspaceDir 'assignments-submission'
$ReactUiProjctDir = Join-Path $WorkspaceDir 'assignments-react-ui'
$GatewayProjectDir = Join-Path $WorkspaceDir 'assignments-gateway'

function Build-Project {
    [CmdletBinding()]
    param (
        [Parameter(Mandatory)]
        [string] $Project,

        [Parameter()]
        [ValidateSet('maven', 'node')]
        [string] $Type = 'maven'
    )

    $ProjectName = Split-Path -Path $Project -Leaf
    Write-Host "building project $ProjectName ..."

    Set-Location $Project
    switch ($Type) {
        'maven' { 
            if(Test-Path -Path .\target -PathType Container) {
                Remove-Item -Path .\target -Force -Recurse
            }

            try { mvn clean package -DskipTests }
            catch { Write-Host $Error }

            if(Test-Path -Path .\docker\app.jar) {
                Remove-Item -Path .\docker\app.jar
            }

            Move-Item -Path .\target\*.jar -Destination .\docker\app.jar
        }
        'node' {
            if(Test-Path -Path .\build -PathType Container) {
                Remove-Item -Path .\build -Force -Recurse
            }

            try { npm run build }
            catch { Write-Host $Error }

            if(Test-Path -Path .\docker\build -PathType Container) {
                Remove-Item -Path .\docker\build -Force -Recurse
            }

            Move-Item -Path build -Destination .\docker\build
        }
        Default { throw 'invalid project type' }
    }
    
}

Build-Project -Project $ManagementProjectDir -Type 'maven'
Build-Project -Project $SubmissionProjectDir -Type 'maven'
Build-Project -Project $GatewayProjectDir -Type 'maven'
Build-Project -Project $ReactUiProjctDir -Type 'node'

Set-Location "$WorkspaceDir/docker"

docker-compose.exe up --build -d
