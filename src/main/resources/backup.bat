set PGUSER = postgres
set PGPASSWORD = postgres
    
for /f  "tokens=1,2,3,4 delims=/ " %%a in('DATE /T')

do set Date=%%b-%%c-%%d  
      
C:\Arquiv~1\Postgr~1\8.1\bin\pg_dump.exe -i -h localhost -p 5432 -U usuario -F c -b -o -v -f "C:\BackUpAutomatico\bancoBackup%Date%.backup" banco  
      
pause  