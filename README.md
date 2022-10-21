# Dog Breeds App
Hello everyone,

I hope you are doing well. Welcome to this project, if you are a dog lover and want to learn more about the different dog breeds then you are at the right place. :blush: This is the [Jetpack Compose](https://developer.android.com/jetpack/compose) Android app which shows you all the dog breeds of this world. And let you see the details,
images, and subbreeds for every dog breed. The app will also let you add/remove breeds to/from the favourites. Dog Breeds App can be used offline as well and
it is available in both light and dark themes.

On the first run, the app uses the [Dog CEO API](https://dog.ceo/dog-api/documentation/) to load data into the app. For later uses the data is persisted into the app using the database. So that users can also use the app offline.

Minimum SDK Version: 21

compile SDK Version: 33

Build System : [Gradle](gradle.org) 

## :hammer:  Architecture

The architecture of this project is MMVM (Model View ViewModel) Clean Architecture. The app is architectured in a way that it would be easier to understand for other developers and reviewers. I have used clean architecture because it goes a step further in segregating the code base’s responsibilities and concerns. The logic of the actions that can be performed in the app is abstracted. Clean Architecture could also be combined with MVP (Model View Presenter) but I have used it with MVVM because Android Architecture Components already has a built-in [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) class. I have tried to follow the [recommended app architecture](https://developer.android.com/topic/architecture#recommended-app-arch) from android.

The architecture of the app is divided into three layers.
- Ui Layer (Presentation Layer)
- Domain Layer
- Data Layer

### :iphone:  UI Layer 
The responsibility of the UI layer (or presentation layer) is to display the application data on the screen. In this project, the UI layer includes UI elements such as [Jetpack Compose](https://developer.android.com/jetpack/compose) functions, State Holders, and the UI States. All the UI element designing is done using declarative UI (Jetpack Compose) instead of XML. 

This app, for example, uses a **DogBreedsListViewModel** class as a state holder to produce the UI state for the screen displayed in that section. For each screen, I have created UI states to ensure [Unidirectional Data Flow](https://developer.android.com/topic/architecture/ui-layer#udf). For example, the state of a screen displaying dog breeds is called **DogBreedsListUiState**. I have followed android guidelines for [UI Layer](https://developer.android.com/topic/architecture/ui-layer) to come up with a better solution.

<p align="center">
<img src="https://user-images.githubusercontent.com/9715067/197088633-488dbb42-a099-42e9-a788-bcfe5ba64eef.png" width=600 height=400/>
</p>
The image above shows the transporting of data from data layer directly to the UI layer but I am also using domain layer in between for better abstraction. The UI layer part of the diagram explains how I am handling UI states.

### :outbox_tray:  Domain Layer 
The [domain layer](https://developer.android.com/topic/architecture/domain-layer) sits between the UI and data layers. The domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple ViewModels. It includes all the Use Cases of the app, for example, **DogBreedsUseCase**. Moreover, it also includes our database entities.

### :floppy_disk:  Data Layer 
The [data layer](https://developer.android.com/topic/architecture/data-layer) contains business logic, it is made of repositories that contain zero to many data sources. In our case, the **DataRepository** contains two data sources such as **RemoteDataSource** and **LocalDataSource**. The **RemoteDataSource** fetches data from API, whereas, **LocalDataSource** gets data from the [Room](https://developer.android.com/training/data-storage/room) database. The DataRepository is responsible to decide whether the data from a remote source or local source should be forwarded to the domain layer and then later to the UI layer.

I am using [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) dependency injection library to provide all the dependencies I have to pass among different layers.

The diagram below briefly gives an overview of the architecture of the project.

![uu](https://user-images.githubusercontent.com/9715067/197084654-a826b6b7-5069-4ba7-98cb-fed1d67b2c27.png "Overview of the App Architecture")

## :package:  Project Dependencies
I have used the dependencies which are part of [Jetpack](https://developer.android.com/jetpack) and also some third-party libraries. The project dependencies which are used in this project are as follows:

### Jetpack libraries
- [Compose](https://developer.android.com/jetpack/androidx/releases/compose) - Defines UI programmatically with composable functions that describe its shape and data dependencies.
- [Compose Foundation](https://developer.android.com/jetpack/androidx/releases/compose-foundation) - Write Jetpack Compose applications with ready-to-use building blocks and extend the foundation to build your design system pieces.
- [Compose UI](https://developer.android.com/jetpack/androidx/releases/compose-ui) - Fundamental components of compose UI needed to interact with the device, including layout, drawing, and input.
- [Compose Material](https://developer.android.com/jetpack/androidx/releases/compose-material) - Build Jetpack Compose UIs with ready-to-use Material Design Components.
- [Arch Core](https://developer.android.com/jetpack/androidx/releases/arch-core) - Helper for other arch dependencies, including JUnit test rules.
- [Room](https://developer.android.com/training/data-storage/room) - Create, store, and manage persistent data backed by an SQLite database. 
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection plays a central role in the architectural pattern used.
- [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - Build and structure your in-app UI, handle deep links, and navigate between screens.

### Other libraries
- [Kotlinx Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines. I used this for asynchronous programming to obtain data from the network.
- [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client and supports coroutines out of the box.
- [Moshi](https://github.com/square/moshi) - JSON Parser, used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters.
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection plays a central role in the architectural pattern used.
- [JUnit](https://junit.org/junit4/) - This was used for unit testing the database, the use cases, and the ViewModels.
- [Mockk](https://mockk.io/) This is a mocking library for Kotlin. I used it to provide test doubles during testing.
- [Truth](https://truth.dev/) - Assertions Library, provides readability as far as assertions are concerned.
- [Espresso](https://developer.android.com/training/testing/espresso) - Used for writing Android UI tests for our DAO.
- [Coil](https://github.com/coil-kt/coil) - Image loading for Android backed by Kotlin Coroutines.
- [Accompanist Pager](https://google.github.io/accompanist/pager/) - A library that provides paging layouts for Jetpack Compose.
- [Accompanist Navigation Animation](https://google.github.io/accompanist/navigation-animation/) A library that provides Compose Animation support for Jetpack Navigation Compose.
- [Kotest Assertions](https://kotest.io/docs/assertions/assertions.html) -  I am using it in the test cases. Kotest calls types of state assertion functions matchers.

## :wrench:  Testing 
I have tried to cover all three layers i.e. UI, data, and domain with our test coverage. But I assume, with time, the coverage can be improved. 

The data layer has an instrumented test for the database more specifically DAO. The database is our main source of data after the first run of the app that's why I found it important to be tested. 

The Use Cases are also tested to ensure whether they call the right repository methods.

In the UI layer, I have tested our state holder classes i.e. ViewModels.

## :computer:  Demo

The app is available in two themes _Light_ Theme and _Dark_ Theme. The themes can be changed by the user by clicking the _bulb_:bulb: icon on Dog Breeds List. The proper error screen is shown if the user tries to use the app for the first time without the internet. The user can also _retry_ to load the data after connecting to the internet.

The user has also been informed through the _Snackbar_ when the app is being used offline. In offline mode, some images might not load properly but the user can still use the app and can add and remove dog breeds to favourites.

You can see screenshots of the app below:

### Light Theme
|<img src="https://user-images.githubusercontent.com/9715067/197084952-ffbc67ba-b6f8-49e7-8bec-5f691dfb580f.png" width=200/>|<img src="https://user-images.githubusercontent.com/9715067/197085129-9dbe7266-cc54-4a5d-aad2-2c8b382684e0.png" width=200/>|<img src="https://user-images.githubusercontent.com/9715067/197087784-d60d839e-cb0a-41ba-945f-c8f63f8f60e4.png" width=200/>

|<img src="https://user-images.githubusercontent.com/9715067/197085221-b0c0eeb1-6a39-4dd9-83f0-408b20d941f1.png" width=200/>|<img src="https://user-images.githubusercontent.com/9715067/197085513-2de93033-2d3b-4e10-90b7-4161088dca2a.png" width=200/>|<img src="https://user-images.githubusercontent.com/9715067/197085669-9de8bece-4501-47d7-be86-e773032e2b4c.png" width=200/>

### Dark Theme
|<img src="https://user-images.githubusercontent.com/9715067/197085815-137ace62-0aae-4b13-b0be-43703078fa48.png" width=200/>|<img src="https://user-images.githubusercontent.com/9715067/197087623-c3a40f8b-843b-4228-8b47-8ca7f3ec064f.png" width=200/>|<img src="https://user-images.githubusercontent.com/9715067/197087396-eefac9c4-39c4-4d63-838a-b7400d4bf039.png" width=200/>

## Ending
I hope you like my work. Thank you! :raised_hands:







