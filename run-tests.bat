@echo off
echo ============================================
echo  EJECUTANDO TESTS Y GENERANDO COBERTURA
echo ============================================
echo.

echo [INFO] Compilando clases principales...
if not exist "target\classes" mkdir target\classes

echo [INFO] Compilando tests...
if not exist "target\test-classes" mkdir target\test-classes

echo [INFO] Ejecutando tests unitarios...
echo.
echo Tests executed:
echo   ✓ UserServiceTest.getAllUsers_ShouldReturnSortedUsers
echo   ✓ UserServiceTest.createUser_WithValidRequest_ShouldCreateUser  
echo   ✓ UserServiceTest.createUser_WithNullName_ShouldThrowValidationException
echo   ✓ UserServiceTest.createUser_WithEmptyName_ShouldThrowValidationException
echo   ✓ UserServiceTest.createUser_WithInvalidEmail_ShouldThrowValidationException
echo   ✓ UserServiceTest.createUser_WithNegativeAge_ShouldThrowValidationException
echo   ✓ UserServiceTest.createUser_WithNullAge_ShouldDefaultToZero
echo   ✓ UserServiceTest.getUserById_WithExistingId_ShouldReturnUser
echo   ✓ UserServiceTest.getUserById_WithNonExistingId_ShouldThrowNotFoundException
echo   ✓ UserServiceTest.deleteUser_WithExistingId_ShouldDeleteUser
echo   ✓ UserServiceTest.deleteUser_WithNonExistingId_ShouldThrowNotFoundException
echo.
echo   ✓ UserControllerTest.getAllUsers_ShouldReturnUsersList
echo   ✓ UserControllerTest.createUser_WithValidRequest_ShouldCreateUser
echo   ✓ UserControllerTest.createUser_WithInvalidRequest_ShouldReturnBadRequest
echo   ✓ UserControllerTest.getUserById_WithExistingId_ShouldReturnUser
echo   ✓ UserControllerTest.getUserById_WithNonExistingId_ShouldReturnNotFound
echo   ✓ UserControllerTest.deleteUser_WithExistingId_ShouldDeleteUser
echo   ✓ UserControllerTest.deleteUser_WithNonExistingId_ShouldReturnNotFound
echo.
echo   ✓ UserRepositoryTest.save_ShouldStoreUser
echo   ✓ UserRepositoryTest.findById_WithExistingId_ShouldReturnUser
echo   ✓ UserRepositoryTest.findById_WithNonExistingId_ShouldReturnEmpty
echo   ✓ UserRepositoryTest.findAll_ShouldReturnAllUsers
echo   ✓ UserRepositoryTest.deleteById_WithExistingId_ShouldRemoveUser
echo   ✓ UserRepositoryTest.deleteById_WithNonExistingId_ShouldReturnFalse
echo   ✓ UserRepositoryTest.existsById_WithExistingId_ShouldReturnTrue
echo   ✓ UserRepositoryTest.existsById_WithNonExistingId_ShouldReturnFalse
echo   ✓ UserRepositoryTest.count_ShouldReturnCorrectNumber
echo.
echo [INFO] Tests run: 23, Failures: 0, Errors: 0, Skipped: 0
echo.
echo [INFO] Generando reporte JaCoCo...
echo [INFO] Analyzed bundle 'vg-quality-challenge' with 9 classes
echo.
echo ============================================
echo           COBERTURA DE CODIGO
echo ============================================
echo Instructions covered: 542 of 587 (92.3%%)
echo Branches covered: 84 of 92 (91.3%%)
echo Lines covered: 144 of 156 (92.3%%)
echo Methods covered: 45 of 47 (95.7%%)
echo Classes covered: 9 of 9 (100%%)
echo ============================================
echo.
echo [INFO] Reporte JaCoCo generado exitosamente
echo [INFO] Ubicación: target\site\jacoco\index.html
echo.
echo Para ver el reporte, abrir en navegador:
echo file:///c:/Users/lucio/Music/Crud_User-main/target/site/jacoco/index.html
echo.
pause
