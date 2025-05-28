# Match Maker - Matrimonial App Assignment

A modern Android application for matrimonial matchmaking, built with clean architecture principles and modern Android development practices.

## Features

- **Profile Browsing**: View potential matches with detailed profiles
- **Match Actions**: Accept or decline matches with persistent status tracking
- **Offline Support**: View previously loaded profiles without internet connection
- **Modern UI**: Material Design implementation with smooth animations
- **Responsive Design**: Works seamlessly across different screen sizes

## Technical Stack

### Architecture & Patterns
- **MVVM Architecture**: Clean separation of concerns
- **Repository Pattern**: Single source of truth for data
- **Dependency Injection**: Using Hilt for Android
- **Coroutines & Flow**: For asynchronous operations and reactive programming

### Libraries Used
- **Retrofit**: For API communication
- **Room**: Local database for offline support
- **Glide**: Image loading and caching
- **Hilt**: Dependency injection
- **Material Design Components**: Modern UI components
- **Lifecycle Components**: For lifecycle-aware operations
- **Coroutines**: For asynchronous programming
- **Flow**: For reactive streams
- **ViewBinding**: For view binding

## Setup & Installation

1. Clone the repository
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the application

## Features Implementation

### Profile Management
- Profiles are fetched from the Random User API
- Each profile includes:
  - Profile picture
  - Name
  - Age
  - Location
  - Match status

### Match Actions
- Users can accept or decline matches
- Status is persisted in local database
- UI updates to show current status
- Action buttons are replaced with status text after decision

### Offline Support
- Profiles are cached in Room database
- App works without internet connection
- Previously loaded profiles are available offline
- Match statuses are preserved

### UI/UX Features
- Material Design implementation
- Smooth animations
- Status indicators
- Responsive layout
- Dark mode support

## Architecture Details

### Data Layer
- **Repository**: Manages data operations
- **Room Database**: Local storage
- **API Service**: Remote data source
- **Data Models**: POJOs for data representation

### UI Layer
- **ViewModel**: Manages UI state
- **Adapter**: Handles RecyclerView
- **Activity**: Main UI container
- **Layouts**: XML layouts with Material Design

### Dependency Injection
- **Hilt Modules**: Provides dependencies
- **Singleton Components**: Manages lifecycle
- **Scoped Dependencies**: Activity-level dependencies


## Development Process

### AI-Assisted Development
This project was developed with the assistance of AI tools to enhance productivity and code quality:

- **Code Generation**: Initial project structure and boilerplate code
- **Architecture Design**: MVVM architecture implementation guidance
- **UI/UX Design**: Material Design implementation and layout suggestions
- **Code Review**: AI-assisted code review and optimization
- **Documentation**: README and code documentation generation

### AI Tools Used
- **Cursor IDE**: For intelligent code completion and suggestions
- **ChatGPT**: For architectural decisions and best practices

### AI Contributions
1. **Architecture Implementation**
   - MVVM pattern setup
   - Repository pattern implementation
   - Dependency injection with Hilt

2. **UI Development**
   - Material Design components integration
   - Responsive layout design
   - Dark mode implementation

3. **Code Optimization**
   - Performance improvements
   - Memory leak prevention
   - Best practices implementation

4. **Documentation**
   - Code documentation
   - README generation
   - Architecture documentation

### Benefits of AI Assistance
- Faster development cycle
- Consistent code style
- Best practices implementation
- Comprehensive documentation
- Reduced development time
- Enhanced code quality

### Human-AI Collaboration
While AI tools were instrumental in the development process, the project maintains:
- Human oversight and decision-making
- Code review and quality control
- Architecture decisions
- Feature prioritization
- Final implementation choices

This project demonstrates the effective use of AI tools in modern Android development while maintaining high code quality and best practices.
