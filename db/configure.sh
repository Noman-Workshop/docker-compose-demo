#!/bin/bash

# wait for the server to start
sleep 15s

# exectue init.sql to create the DB and the schema in the DB
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P $MSSQL_SA_PASSWORD -d master -i init.sql
