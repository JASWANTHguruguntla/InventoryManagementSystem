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
### 🎮 Running the Application

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
