@echo off
title Inventory Management System
color 0A

echo ========================================
echo   Inventory Management System Launcher
echo ========================================
echo.

:: Check if we're in the right directory
if not exist "pom.xml" (
    echo ERROR: pom.xml not found! Make sure run.bat is in the project root.
    echo Current directory: %CD%
    pause
    exit /b 1
)

echo Step 1: Cleaning previous build...
call mvn clean -q

echo Step 2: Compiling source code...
call mvn compile -q

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

echo Step 3: Starting application...
echo.
echo ========================================
echo   Application is starting...
echo ========================================
echo.

mvn exec:java -Dexec.mainClass="Main"

echo.
echo ========================================
echo   Application has been closed
echo ========================================
echo.
pause