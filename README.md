# 🏪 Inventory Management System

A modern, user-friendly **JavaFX-based Inventory Management System** that helps businesses efficiently track and manage their product inventory. Built with Java, MySQL, and a sleek graphical interface.

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-21-blue?style=for-the-badge&logo=javafx)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![Maven](https://img.shields.io/badge/Maven-3.8-red?style=for-the-badge&logo=apache-maven)

## ✨ Features

### 🔐 Secure Authentication
- **User Login System** with username/password authentication
- **Database-backed user management**
- Secure session handling

### 📦 Product Management
- **Add New Products** with name, quantity, and price
- **Real-time Inventory View** in a responsive table
- **Delete Products** with confirmation dialogs
- **Automatic Data Persistence** to MySQL database

### 🎯 User Interface
- **Modern JavaFX GUI** with clean, professional design
- **Responsive Table View** with sortable columns
- **Interactive Alerts** and confirmation dialogs
- **Real-time Data Refresh**

### 🔧 Advanced Functionality
- **Input Validation** with helpful error messages
- **Database Connection Management**
- **CRUD Operations** (Create, Read, Update, Delete)
- **Data Integrity Checks**

## 🚀 Quick Start

### Prerequisites
- **Java JDK 17** or later
- **MySQL Server** 8.0 or later
- **Maven** 3.6 or later

### Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/InventoryManagementSystem.git
   cd InventoryManagementSystem
2. **Database Setup**
(sql)
   ```bash
   CREATE DATABASE inventory_db;
   USE inventory_db;

   CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
   );

   CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    quantity INT,
    price DOUBLE
   );

   INSERT INTO users (username, password) VALUES ('admin', 'admin123');
3. **Configure Database Connection**
   
   *Edit src/main/java/db/DBUtil.java if needed:*
   ```bash
   private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";
   ```
## 🎮 Running the Application

**Method 1: Using Maven (Recommended)**

   bash
   ```bash
   # Clean, compile and run
   mvn clean compile exec:java -Dexec.mainClass="Main"

   # Or just run if already compiled
   mvn exec:java -Dexec.mainClass="Main"
   ```
**Method 2: Using Batch File (Windows)**

   bash
   ```bash
   # Double-click or run:
   run.bat
   ```
   **Method 3: Using Bash Script (Git Bash/Linux/Mac)**
   
   bash
   ```bash
   # Make executable and run
   chmod +x run.sh
   ./run.sh
   ```
   **Method 4: Using PowerShell (Windows)**
   
   powershell
   ```bash
   # Right-click and "Run with PowerShell"
   ./run.ps1
   ```
## 📁Project Structure
```bash
InventoryManagementSystem/
├── src/main/java/
│   ├── Main.java                 # 🎯 Application entry point
│   ├── ui/
│   │   ├── LoginUI.java          # 🔐 Login interface
│   │   └── InventoryUI.java      # 📊 Main inventory dashboard
│   ├── dao/
│   │   └── ProductDAO.java       # 🗄️ Data access operations
│   ├── db/
│   │   └── DBUtil.java           # 🔌 Database connection utility
│   └── model/
│       └── Product.java          # 📦 Product data model
├── lib/
│   └── mysql-connector-j-9.4.0.jar
├── pom.xml                       # 📦 Maven configuration
├── run.bat                       # ⚡ Windows run script
├── run.sh                        # 🐧 Linux/Mac run script
├── run.ps1                       # 💻 PowerShell run script
└── README.md                     # 📚 This file
```
## 🎯 Usage Guide

1. ***Login***
* Username: admin
*  Password: admin123
*  Secure authentication validates credentials against database
  

2. ***Manage Inventory***
* Add Products: Fill in name, quantity, and price fields
* View Products: See all products in the interactive table
* Delete Products: Select a product and click "Delete Selected"
* Refresh Data: Click "Refresh" to reload from database

3. ***Database Operations***
* View Database: Click "View Database" to see all records
* Real-time Updates: All changes immediately persist to MySQL
* Data Validation: Input validation prevents invalid entries

## 🛠️ Technical Details
***Architecture***
* MVC Pattern: Model-View-Controller architecture
* DAO Pattern: Data Access Object for database operations
* Layered Architecture: Separation of concerns

***Technologies Used***
* Frontend: JavaFX for modern GUI
* Backend: Java with MySQL connector
* Build Tool: Maven for dependency management
* Database: MySQL for data persistence

***Key Classes***
* **Product**: Data model representing inventory items
* **ProductDAO**: Handles all database operations
* **DBUtil:** Manages database connections
* **LoginUI**: Authentication interface
* **InventoryUI**: Main application interface

## 🔧 Development
***Building from Source***
```bash
# Compile the project
mvn clean compile

# Create executable JAR
mvn package

# Run tests
mvn test
```
***Adding new features***
1. Extend the Product model for new fields
2. Update ProductDAO for new database operations
3. Modify InventoryUI for new UI components
4. Update database schema as needed
## 🐛 Troubleshooting
***Common Issues***

**❌ Database Connection Failed**
* Check MySQL service is running
* Verify database credentials in DBUtil.java
* Ensure database and tables exist
  
**❌ JavaFX Not Found**
* Use Maven commands (handles dependencies automatically)
* Or download JavaFX SDK and set JAVAFX_HOME environment variable
  
**❌ Compilation Errors**
* Ensure JDK 17+ is installed
* Run mvn clean compile to rebuild
* Check all package declarations are correct

***Logs & Debugging***
* Check console output for detailed error messages
* Database operations are logged with timestamps
* Input validation provides helpful error messages
## 🤝 Contributing
We welcome contributions! Please follow these steps:
1. Fork the repository
2. Create a feature branch (git checkout -b feature/AmazingFeature)
3. Commit your changes (git commit -m 'Add some AmazingFeature')
4. Push to the branch (git push origin feature/AmazingFeature)
5. Open a Pull Request
   
***Development Guidelines***
* Follow Java coding conventions
* Add comments for complex logic
* Test all database operations
* Update documentation accordingly

## 📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors
* Jaswanth Guruguntla - https://github.com/JASWANTHguruguntla

## 🙏 Acknowledgments
* JavaFX community for excellent documentation
* MySQL team for robust database solutions
* Maven for seamless dependency management
* Open source contributors who make projects like this possible

## 📞 Support
If you encounter any issues or have questions:
1. Check the troubleshooting section above
2 Search existing GitHub issues
3. Create a new issue with detailed information

<div align="center">
⭐ Don't forget to star this repository if you found it helpful!
Happy Coding! 🚀
</div> 
