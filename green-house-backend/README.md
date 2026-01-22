# Green House Backend API

A Spring Boot REST API for managing greenhouse data and monitoring. This project provides endpoints to create, read, update, and delete greenhouse records with Firebase integration.

## 📋 Project Overview

- **Framework**: Spring Boot 4.0.1
- **Java Version**: JDK 17
- **Build Tool**: Maven
- **Database**: Firebase (Firestore)
- **Server Port**: 8082

## 🛠️ Prerequisites

Before running this project, ensure you have the following installed:

1. **Java JDK 17** or higher
   - Download from: https://www.oracle.com/java/technologies/downloads/#java17
   - Verify installation: `java -version`

2. **Maven 3.6.0** or higher
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn -version`

3. **Git** (optional, for cloning)

## 📦 Installation & Setup

### Step 1: Clone or Extract the Project

```bash
# If using Git
git clone <repository-url>
cd green-house-backend

# Or extract the provided ZIP/folder
cd green-house-backend
```

### Step 2: Firebase Configuration

This project uses Firebase for data storage. You need to configure Firebase credentials:

1. **Obtain Firebase Service Account Key**:
   - Go to [Firebase Console](https://console.firebase.google.com/)
   - Select your project
   - Navigate to Project Settings → Service Accounts
   - Click "Generate New Private Key"
   - Save the JSON file

2. **Add the Service Account Key**:
   - Place the JSON file in: `src/main/resources/`
   - Rename it as needed (currently expects `serviceAccountKey.json` or `serviceAccountKey-1.json`)
   - Update the file path in [src/main/java/com/example/green_house/config/FirebaseConfig.java](src/main/java/com/example/green_house/config/FirebaseConfig.java) if needed

### Step 3: Configure Application Properties

Edit [src/main/resources/application.properties](src/main/resources/application.properties):

```properties
spring.application.name=green-house
server.port=8082
```

You can modify the `server.port` if port 8082 is already in use on your system.

## 🚀 Running the Project

### Option 1: Using Maven (Recommended)

```bash
# Build and run the project
mvn spring-boot:run
```

The application will start on `http://localhost:8082`

### Option 2: Build and Run JAR

```bash
# Build the project
mvn clean package

# Run the generated JAR file
java -jar target/green-house-0.0.1-SNAPSHOT.jar
```

### Option 3: Using IDE (IntelliJ IDEA or Eclipse)

1. Open the project in your IDE
2. Right-click on `GreenHouseApplication.java`
3. Select "Run" or "Debug"

## 📡 API Endpoints

The API is accessible at: `http://localhost:8082/api`

CORS is configured to allow requests from `http://localhost:5173`

### Available Endpoints

- **POST** `/api/save-ui` - Create a new greenhouse record
- **GET** `/api/history` - Retrieve all greenhouse records
- **GET** `/api/{id}` - Retrieve a specific record by ID
- **PUT/PATCH** `/api/{id}` - Update a record
- **DELETE** `/api/{id}` - Delete a record

## 📁 Project Structure

```
green-house-backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/green_house/
│   │   │   ├── GreenHouseApplication.java          # Main application entry point
│   │   │   ├── config/
│   │   │   │   ├── CorsConfig.java                 # CORS configuration
│   │   │   │   └── FirebaseConfig.java             # Firebase setup
│   │   │   ├── Cotroller/
│   │   │   │   └── GreenHouseController.java       # REST endpoints
│   │   │   ├── Model/
│   │   │   │   └── GreenHouseModel.java            # Data model
│   │   │   ├── Repository/
│   │   │   │   └── GreenHouseRepo.java             # Database operations
│   │   │   └── Service/
│   │   │       └── GreenHousService.java           # Business logic
│   │   └── resources/
│   │       ├── application.properties               # Application config
│   │       ├── serviceAccountKey.json               # Firebase credentials
│   │       ├── static/                              # Static files
│   │       └── templates/                           # HTML templates
│   └── test/
│       └── java/com/example/green_house/           # Unit tests
├── pom.xml                                         # Maven dependencies
├── mvnw / mvnw.cmd                                 # Maven wrapper (Windows/Unix)
└── README.md                                        # This file
```

## 🔧 Troubleshooting

### Port Already in Use

If port 8082 is already in use:

```bash
# On Windows (PowerShell)
Get-Process -Id (Get-NetTCPConnection -LocalPort 8082 -ErrorAction SilentlyContinue).OwningProcess

# Or change the port in application.properties
server.port=8083
```

### Firebase Configuration Issues

- Verify the service account JSON file is in the correct location
- Check that the JSON file has valid credentials
- Ensure your Firebase project allows the operations you're attempting

### Maven Build Issues

```bash
# Clear Maven cache and rebuild
mvn clean install

# Update dependencies
mvn dependency:resolve
```

### Java Version Mismatch

Ensure you're using Java 17:

```bash
java -version
```

## 📝 Dependencies

- **Spring Boot Web MVC** - For REST API development
- **Firebase Admin SDK 9.2.0** - For Firebase integration
- **Spring Boot DevTools** - For development with auto-reload
- **Spring Boot Test** - For unit testing

## 🤝 Sharing with Others

To share this project with another person:

1. **Without Firebase Credentials** (Recommended for security):

   ```bash
   # Remove sensitive files before sharing
   # Keep the project structure and code
   # They will need to add their own serviceAccountKey.json
   ```

2. **Full Setup Instructions**:
   - Send them this README file
   - Ask them to get their own Firebase service account key
   - They should follow the "Installation & Setup" section

3. **Zip the Project**:
   ```bash
   # Exclude target, .git, and IDE-specific folders
   tar -czf green-house-backend.tar.gz --exclude=target --exclude=.git --exclude=.idea --exclude=.vscode green-house-backend/
   ```

## 🔐 Security Notes

- **Never commit** `serviceAccountKey.json` to version control
- Use `.gitignore` to exclude credentials:
  ```
  serviceAccountKey.json
  serviceAccountKey-1.json
  *.env
  .env
  ```
- Store sensitive credentials in environment variables or secure vaults in production

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Firebase Admin SDK](https://firebase.google.com/docs/admin/setup)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/)

## 🐛 Common Issues & Solutions

| Issue                        | Solution                                                              |
| ---------------------------- | --------------------------------------------------------------------- |
| `Firebase connection failed` | Check Firebase credentials and internet connection                    |
| `Port 8082 already in use`   | Change port in application.properties or kill process using that port |
| `Maven build failure`        | Run `mvn clean install` and check Java version is 17                  |
| `CORS errors from frontend`  | Verify frontend origin is in `@CrossOrigin` annotation or CorsConfig  |

## 📞 Support

For issues or questions:

1. Check the troubleshooting section above
2. Review Firebase documentation
3. Check Spring Boot logs for detailed error messages

---

**Last Updated**: January 2026  
**Version**: 0.0.1-SNAPSHOT
