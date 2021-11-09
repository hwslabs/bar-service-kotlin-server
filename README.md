# Bar Service Kotlin Server

![](logo/hypto_grpc_kotlin.png)

## Overview

This directory contains the server impl in Kotlin for a simple gRPC starter service. 
You can find detailed instructions for building and running example from below

- **Bar Service Server** using gRPC and Kotlin. For details, see the [project on github](https://github.com/hwslabs/bar-service-kotlin-server).

## File organization

The starter sources are organized into the following top-level folders:

- [starter-service-models](starter-service-models): `.proto` files for generating the stubs
- [starter-service-server](starter-service-server): Kotlin servers based on regular [stub][] artifacts

## Set up and run the server inside a docker on macOS
- <details>
  <summary>Install Docker</summary>

  Download and install the latest version of docker:
  [Download Latest Docker](https://docs.docker.com/desktop/mac/install/)

- <details>
  <summary>Clone the project</summary>

  Clone the project recursively cloning all submodules

  ```sh
  git clone git@github.com:hwslabs/bar-service-kotlin-server.git --recurse-submodules
  ```
  
  Navigate into the project:
  ```sh
  cd grpc-kotlin-starter
  ```

- <details>
  <summary>Run the server</summary>

  Build a docker image and run the server on a container:
  ```sh
  docker-compose up
  ```
  This will start the server and open up the 50051 port for connections


## Set up and run the server without any containers on macOS
- <details>
  <summary>Install Homebrew</summary>

  Download and install Homebrew:

  ```sh
  /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
  ```

- <details>
  <summary>Install JDK</summary>

  Install any version of JDK (8 preferred):

  ```sh
  brew install openjdk@8
  ```

  Add the installed version of JDK to your path through .zshrc or .bash_profile

  ```sh
  echo 'export PATH="/usr/local/opt/openjdk@8/bin:$PATH"' >> ~/.zshrc
  source ~/.zshrc
  ```

  or

  ```sh
  echo 'export PATH="/usr/local/opt/openjdk@8/bin:$PATH"' >> ~/.bash_profile
  source ~/.bash_profile
  ```

- <details>
  <summary>Clone the project</summary>

  Clone the project recursively cloning all submodules

  ```sh
  git clone git@github.com:hwslabs/bar-service-kotlin-server.git --recurse-submodules
  ```

  Navigate into the project:
  ```sh
  cd grpc-kotlin-starter
  ```

- <details>
  <summary>Run the server</summary>

  Start the server:

  ```sh
  ./gradlew starter-service-server:start
  ```

  This will start the server and open up the 50051 port for connections

[grpc.io Kotlin/JVM]: https://grpc.io/docs/languages/kotlin/
[Quick start]: https://grpc.io/docs/languages/kotlin/quickstart/
[Basics tutorial]: https://grpc.io/docs/languages/kotlin/basics/
[protos]: protos
[stub]: stub
