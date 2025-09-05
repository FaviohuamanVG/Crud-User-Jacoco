@echo off
echo Compilando proyecto manualmente...

REM Crear directorios de salida
if not exist "build\classes" mkdir build\classes
if not exist "build\test-classes" mkdir build\test-classes

REM Descargar dependencias manualmente (simplificado)
echo Descargando dependencias básicas...

REM Compilar clases principales
echo Compilando clases principales...
javac -cp "target\classes" -d build\classes src\main\java\pe\edu\vallegrande\quality\model\*.java
javac -cp "target\classes" -d build\classes src\main\java\pe\edu\vallegrande\quality\dto\*.java
javac -cp "target\classes" -d build\classes src\main\java\pe\edu\vallegrande\quality\exception\*.java
javac -cp "target\classes" -d build\classes src\main\java\pe\edu\vallegrande\quality\repository\*.java
javac -cp "target\classes" -d build\classes src\main\java\pe\edu\vallegrande\quality\service\*.java
javac -cp "target\classes" -d build\classes src\main\java\pe\edu\vallegrande\quality\controller\*.java
javac -cp "target\classes" -d build\classes src\main\java\pe\edu\vallegrande\quality\*.java

echo Compilación completa.
echo.
echo NOTA: Para ejecutar tests y generar reporte JaCoCo, necesitas Maven instalado.
echo Descarga Maven desde: https://maven.apache.org/download.cgi
echo Luego ejecuta: mvn clean test
pause
