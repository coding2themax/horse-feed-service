# Horse Feed Service

A comprehensive Java web application built with Spring MVC for managing horse feeding schedules and nutrition tracking.

## Features

- **Horse Management**: Add, edit, view, and delete horse profiles with breed, age, weight, and activity level information
- **Feeding Schedules**: Create and manage feeding schedules with specific times, quantities, and frequencies
- **Feed Type Management**: Track different types of feed with nutritional information
- **Dashboard**: Overview of all horses, schedules, and feed types with statistics
- **Responsive Design**: Modern Bootstrap-based UI that works on desktop and mobile devices

## Technology Stack

- **Backend**: Java 11, Spring MVC 5.3, Spring JDBC
- **Frontend**: JSP, Bootstrap 5, Font Awesome
- **Database**: H2 Database (embedded for demo)
- **Build Tool**: Maven
- **Server**: Apache Tomcat 9.x

## Project Structure

```
horse-feed-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/horsefeed/
│   │   │       ├── controller/     # Spring MVC Controllers
│   │   │       ├── dao/           # Data Access Objects
│   │   │       ├── model/         # Entity Models
│   │   │       └── service/       # Business Logic Services
│   │   ├── resources/
│   │   │   ├── schema.sql         # Database Schema
│   │   │   └── data.sql          # Sample Data
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── views/        # JSP Templates
│   │       │   ├── spring-servlet.xml
│   │       │   └── web.xml
│   │       └── index.jsp
├── target/                        # Compiled classes and WAR file
└── pom.xml                       # Maven configuration
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Apache Maven 3.6+
- Apache Tomcat 9.x (for deployment)

### Building the Application

1. **Clone the repository** (if using git):

   ```bash
   git clone <repository-url>
   cd horse-feed-service
   ```

2. **Build the WAR file**:

   ```bash
   mvn clean package
   ```

3. **Run with embedded Tomcat** (for development):

   ```bash
   mvn tomcat7:run
   ```

   The application will be available at: http://localhost:8080/horse-feed-service

### Deployment to Tomcat

1. **Build the WAR file**:

   ```bash
   mvn clean package
   ```

2. **Deploy to Tomcat**:
   - Copy `target/horse-feed-service.war` to your Tomcat `webapps/` directory
   - Start Tomcat
   - Access the application at: http://localhost:8080/horse-feed-service

### Database

The application uses H2 embedded database that is initialized automatically with:

- Database schema (`schema.sql`)
- Sample data (`data.sql`)

The database is created in-memory and will be reset on each application restart.

## API Endpoints

### Web Routes

- `GET /` - Dashboard/Home page
- `GET /horses` - List all horses
- `GET /horses/new` - Add new horse form
- `GET /horses/{id}` - View horse details
- `GET /horses/{id}/edit` - Edit horse form
- `POST /horses` - Save horse
- `POST /horses/{id}/delete` - Delete horse
- `GET /schedules` - List all feeding schedules
- `GET /schedules/new` - Add new schedule form
- `GET /schedules/{id}` - View schedule details
- `GET /schedules/{id}/edit` - Edit schedule form
- `POST /schedules` - Save schedule
- `POST /schedules/{id}/delete` - Delete schedule
- `GET /schedules/horse/{horseId}` - View schedules for specific horse

## Sample Data

The application comes with pre-loaded sample data including:

**Horses:**

- Thunder (Thoroughbred, High activity)
- Bella (Quarter Horse, Moderate activity)
- Spirit (Arabian, Low activity)
- Ranger (Appaloosa, High activity)
- Luna (Paint Horse, Moderate activity)

**Feed Types:**

- Premium Horse Pellets
- Senior Horse Feed
- Performance Mix
- Maintenance Cubes
- Alfalfa Hay

**Feeding Schedules:**

- Multiple daily feeding schedules for each horse

## Development

### Running in Development Mode

```bash
# Compile and run with auto-reload
mvn clean compile
mvn tomcat7:run
```

### Building for Production

```bash
# Create production WAR file
mvn clean package -Pprod

# The WAR file will be created at: target/horse-feed-service.war
```

## Configuration

### Database Configuration

The database configuration is located in `src/main/webapp/WEB-INF/spring-servlet.xml`. To use a different database:

1. Add the appropriate JDBC driver dependency in `pom.xml`
2. Update the `dataSource` bean configuration
3. Modify the SQL scripts if using a different database dialect

### Application Properties

Key configuration settings:

- **Context Path**: `/horse-feed-service` (configurable in server deployment)
- **Database**: H2 embedded (configurable in Spring configuration)
- **View Technology**: JSP with Spring MVC

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues and questions:

1. Check the existing documentation
2. Search for similar issues
3. Create a new issue with detailed information

---

**Horse Feed Service** - Professional horse nutrition and feeding schedule management.
