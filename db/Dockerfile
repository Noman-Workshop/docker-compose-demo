FROM mcr.microsoft.com/mssql/server:2019-latest

# create a configuration directory
RUN mkdir -p /usr/config

# copy the configuration file to the container
COPY . /usr/config

# set the working directory
WORKDIR /usr/config

# set the entrypoint
ENTRYPOINT ["./entrypoint.sh"]