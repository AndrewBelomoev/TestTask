# Simple Android Application

This is a simple Android application that makes an HTTP request to a public API and displays a list of users.

## Technologies Used

- **Kotlin**
- **Coroutines**
- **Flow**
- **Retrofit**
- **XML**
- **Clean Architecture**
- **MVVM**
- **Koin**
- **Room**
- **JUnit**
- **Mockito**

## Modules

The project is divided into three modules:

1. **app**: The main application module.
2. **data**: The data layer, handling data from the network and local database.
3. **domain**: The domain layer, containing business logic and use cases.

## Features

- **User List**: Displays a list of users with their ID, name, and email.
- **User Details**: Displays detailed information about the user (name, email, phone, city).
- **Error Handling**: Displays a message if the API is not available.
- **Local Caching**: Caches user data locally using Room.
- **Repositories**: Remote and local repositories for handling data operations.
- **Dependency Injection**: Koin modules for dependency injection.
- **UI Components**: Using XML for the user interface with Fragments and ViewModels.

## How to Build and Run

1. Clone the repository.
2. Open the project in Android Studio.
3. Build the project:
   - Use Gradle to sync the project.
   - Ensure that all dependencies are downloaded.
4. Run the app:
   - Select an emulator or connected device.
   - Click the 'Run' button in Android Studio.

## Testing

- Unit tests are provided for the ViewModel and repositories using JUnit and Mockito.
- Run tests:
  ```bash
  ./gradlew test
