@echo off
@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM Maven Wrapper Script for Windows

set MAVEN_WRAPPER_VERSION=3.8.6
set MAVEN_WRAPPER_JAR="%~dp0.mvn\wrapper\maven-wrapper.jar"
set MAVEN_WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

echo Maven no está instalado en el sistema.
echo Para ejecutar los tests y generar el reporte JaCoCo, necesitas instalar Maven.
echo.
echo Opciones:
echo 1. Instalar Maven manualmente desde: https://maven.apache.org/download.cgi
echo 2. Usar Chocolatey: choco install maven
echo 3. Usar el IDE (IntelliJ IDEA, Eclipse) para ejecutar los tests
echo.
echo Comandos necesarios una vez instalado Maven:
echo   mvn clean test
echo   mvn jacoco:report
echo.
echo El reporte JaCoCo se generará en: target\site\jacoco\index.html
echo.
pause
