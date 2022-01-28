Make Mine Marvel
====================

App written to show modern, testable, scalable architecture in Android 2022
    
- - -

## Design

Simple app with custom Application, Activity, a RecyclerView Fragment, and details Fragment

#### Dependency Management

To demonstrate understanding of Dependency Injection, a simple custom implementation is used. For the Android Application there is a Kotlin Object holding reference to the defined interfaces. The Application.onCreate() is used to inject the implementations at app start, and the EspressoTestApplication injects the test implementations for Espresso Instrumentation tests. Most dependencies use a single instance of an implementation, but the Network interface uses a factory to create a new instance each time it is requested. Dependency Injection is used in the 'coreandroid' module as well. 

#### Interfaces

Repositories and Coordinators are defined by interfaces to allow for easy scaling and testability. Each external library (Android Libraries excluded) is behind an interface/implementation to allow for flexibility/testing. Interfaces are defined in core/androidcore modules. Typealias is used to help define function parameters
* ComicCoordinator
* ComicRepository
* JsonDeserializer
* Logger
* Network
* ImageLoader

The KotlinXDeserializer implementation of the JsonDeserializer is an example of keeping the code clean (the Comic pojo), and allowing for flexibility. kotlinx.serialization uses annotations and deserialization strategies that would tightly couple the pojo to kotlinx.serialization. A class designed specifically for kotlinx.serialization (MarvelResponseComic) is used, and has a toComic function to match the required interface defined return type. I could easily switch to GSON or any other deserialization module without changing the Android Application. I also don't need to worry about web API changes forcing App changes (as the JSON mapping is separate from the App)

In my experience, Logging requirements change frequently, so using an interface allows quick adjustments with little code impact

#### Modules
The Android App pulls from the core and androidcore modules. Those modules either contain an implementation or reference an implementation module. Modularizing the code adds additional testability along with ensuring one way dependencies. Modules are able to be extracted and built as external libraries, allowing for multi application usage. These modules were not written to be compiled by KMM, but core and most of the other modules were written with KMM in mind.

#### Testing
Included is a limited number of tests to show my testing methodology
* Unit
* Instrumentation
* Espresso

All testing was done without mocking to show an understanding of proper testing. Each test class shows a different level of concern. Unit for Function/Field tests, Instrumentation for UI Logic, Espreso for Flow

- - -

## Challenges

#### Picasso
I hadn't done an image loader in a few years so while I had an interface in mind, I wasn't sure what to expect. The first challenge was determining how to access a jpg from the thumbnail url. I used Chrome Developer tools to see how the URL was transformed by cross-referencing a comic to the Marvel search feature (by appending different file names to define image shape/size). Next I encountered an issue where Picasso didn't load any image at first. My first thought was "I forgot to add Internet to the manifest permissions", which was true, but I also needed to define networkSecurityConfig in the Application manifest.

Difficulty rating: 1/5 would stackoverflow again

#### KotlinX.Serialization
I knew I'd encounter issues pulling in the KotlinX.Serialization, as I have every time I use it (it's still my favorite). When adding the library, as it requires buildscript magic at compile time to map json to pojo, it has a buildscript dependency and plugin. I followed the documentation and got the plugin formatted correctly on my 3rd try.

Difficulty rating: 2/5 "I'll get you next time, He-Man!!"

#### Json Deserialization Exception
My preferred development environment is Linux. I encounter fewer issues in my IDE, but Android Studio still will get stuck on some cache occasionally. I encountered an immediate crash when I switched from my hardcoded string ComicCoordinator to Ktor. I couldn't get Android Studio to enter debugging, so I lost extra time with logs and invalidating cache. Eventually I discovered the Ktor results were correct, but I was executing my callback outside my coroutine, so it triggered my default empty response instead of my web API results.

Difficulty rating: 1/5 Spider-Man pointing meme

## If I were to continue
I don't want to sink more than a day into the project, and I'm very satisfied with the results. I'd still polish more before publishing though, here are a few thoughs:
* Support filters to pull more than just Spider-Man comics
* Add pagination to make the RecyclerView infinitely scroll
* Work with UX team to provide the Disney Magic experience
* Animations! I would have liked to put a shared element transition when clicking the ViewHolder, but I timeboxed it to 20 minutes, and didn't end up with results I was happy with
* Add additional features to the Details screen like deeplinking to Marvel Unlimited, or adding a navigation buttons
* Optimize the APK. Depending on the required API (I chose 5.1 to match other Marvel apps), I could likely get an APK to be under 1mb (though I'd likely need to implement my own network/image loading)
* Full testing. I try for 80% coverage
* Include extension functions for ImageLoader and Network. Additionally add DSL for network calls to provide builder-like usage
* API result caching. Allow offline support, and better network data usage