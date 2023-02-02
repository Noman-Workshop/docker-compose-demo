#!/bin/bash

# wait for the SQL Server to come up and configure the database
./configure.sh &

# start the mssql server
/opt/mssql/bin/sqlservr