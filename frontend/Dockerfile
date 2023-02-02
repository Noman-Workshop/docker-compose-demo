# *** Select a base image
# **** Node image: v16.17.0
# *** Generate /code directory
# *** Copy . directory to /code
# *** Write a bash script:
# **** 1. install the dependencies
# ***** npm install
# **** 2. Start the server
# ***** npm start
# *** EXPOSE 4200
# *** CREATE AN ENTRYPOINT
# Create a dockerfile

FROM node:16.17.0

RUN mkdir -p /usr/code

WORKDIR /usr/code

COPY . /usr/code

RUN ls -la

RUN npm install

EXPOSE 4200

ENTRYPOINT ["npm", "start"]

# Build the image
# docker build -t angular-image docker/

# Run the container with name angular-container
# docker run -p 4200:4200 --name angular-container angular-image
