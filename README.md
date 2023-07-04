# TouristTracker Server Application

This repository contains the documentation for the TouristTracker server application. The server component of the TouristTracker system is responsible for database services, back-end functionality, and data analysis. This readme file provides an overview of the server application's architecture, features, and usage.

## 1. System Overview

### 1.1 System Architecture

The TouristTracker server application utilizes a document-oriented database called MongoDB for permanent storage of collected user data. It serves as the back-end for retrieving information from the database and providing functionality for data analysis. The server communicates with the client applications through HTTP requests and JSON payloads.

### 1.2 Server Application

The TouristTracker server application offers several features for analyzing the collected profile and GPS data of tourists. It is divided into three main elements:

1. **GUI**: The graphical user interface (GUI) provides a web-based front-end for users to interact with the application. Users can analyze collected data, view movement patterns, and perform various operations without requiring additional client program installations.

2. **Account Management**: The server application includes an account management feature that allows a super user to grant access to other users. This ensures controlled access to the application's data and functionalities.

3. **Back-End**: The back-end of the server application is implemented in Java. It serves as the bridge between the front-end GUI and the database. It includes the implementation of all functions required for displaying, filtering, and deleting the collected data. The back-end handles the processing of requests from the front-end and performs the necessary operations on the database.

## 2. Features

The TouristTracker server application provides the following features:

- Data storage: The server application uses MongoDB as a document-oriented database to store the collected user data permanently.
- Data retrieval: The back-end retrieves information from the database for back-end usage and data analysis.
- GUI functionality: The web-based GUI allows users to interact with the application, analyze collected data, and view movement patterns.
- Account management: The server application includes an account management feature that allows a super user to manage user access to the application.
- Back-end operations: The back-end handles the implementation of various functions required for displaying, filtering, and deleting the collected data.

## Conclusion

The TouristTracker server application serves as the backbone of the client-server system, providing database services, data analysis, and functionality for user interaction. For detailed implementation instructions and code, please refer to the documentation and source code provided in this repository.

git filter-branch --env-filter 'if [ "$GIT_AUTHOR_NAME" = "Mario Miosga" ]; then export GIT_AUTHOR_NAME="progxeno"; export GIT_AUTHOR_EMAIL="mario.miosga@gmail.com"; fi; if [ "$GIT_COMMITTER_NAME" = "Mario Miosga" ]; then export GIT_COMMITTER_NAME="progxeno"; export GIT_COMMITTER_EMAIL="mario.miosga@gmail.com"; fi' -- --branches --tags
