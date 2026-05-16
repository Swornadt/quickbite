<div align="center">

# 🍽️ QuickBite
### Food Service Management System

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Jakarta EE](https://img.shields.io/badge/Jakarta_EE-10-0089CF?style=for-the-badge&logo=jakarta&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache_Tomcat-10-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

*A dynamic web-based food pre-ordering platform for college canteens*

---

[![Advanced Programming Techniques](https://img.shields.io/badge/Module-Advanced_Programming_Techniques-blueviolet?style=flat-square)](.)
[![Second Year](https://img.shields.io/badge/Year-Second_Year-blue?style=flat-square)](.)
[![MVC Architecture](https://img.shields.io/badge/Architecture-MVC-green?style=flat-square)](.)
[![License](https://img.shields.io/badge/License-Academic-red?style=flat-square)](.)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Team Members](#-team-members)
- [Tech Stack](#-tech-stack)
- [System Architecture](#-system-architecture)
- [Features](#-features)
- [Database Schema](#-database-schema)
- [Setup & Installation](#-setup--installation)
- [Usage](#-usage)

---

## 🌐 Overview

During peak hours, college canteens face extremely high volumes of orders, resulting in long queues and significant delays in service. Students typically have 30-minute breaks between classes, during which they must order, wait, collect, and eat — a timeline that the current manual ordering system makes nearly impossible to manage.

**QuickBite** is a full-stack, web-based pre-ordering platform that solves this problem. Students can pre-order food from their preferred on-campus outlets in advance, track orders in real time, and pick up their meals without waiting in line. Kitchen staff receive structured, real-time order feeds, and administrators maintain full control over menus, customers, and outlet operations.

> Built using **Java**, **JSP**, **Servlets**, and **MySQL** following the **MVC** architectural pattern as part of the **CS5054NI Advanced Programming Techniques** module at **Islington College Kathmandu**.

### 🎯 Purpose

To provide an efficient, role-based web application that enables students to pre-order food from any college outlet — eliminating long queues and helping them make effective use of their limited break time.

### 📌 Objectives

- ✅ Enable students to pre-order food online from multiple on-campus outlets
- ✅ Reduce queue time during peak hours at college canteens
- ✅ Support multiple outlets with independent menus and pricing
- ✅ Assist kitchen staff in managing and tracking order preparation efficiently
- ✅ Enable administrators to oversee the full operation of the food service system
- ✅ Implement secure, role-based authentication across Customer, Admin, and Kitchen Staff roles
- ✅ Incorporate input validation, exception handling, descriptive error pages, and user-friendly feedback throughout the application

---

## 👥 Team Members

| Name | Role |
|------|------|
| Sworna Dhan Tuladhar | Project Lead |
| Resha Koju | Developer / Designer |
| Sabrina Pradhan | Developer / Designer |
| Sanskar Piya | Developer |
| Sahil Shrestha | Developer / Database |
| Hridishna Deula | Developer |

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| **Language** | Java 17 |
| **Runtime** | Jakarta EE 10 |
| **Server** | Apache Tomcat 10 |
| **Frontend** | JSP, JSTL, HTML5, CSS3 |
| **Database** | MySQL 8 |
| **Password Hashing** | BCrypt |
| **Build Tool** | Maven |
| **IDE** | Eclipse |

---

## 🏗️ System Architecture

QuickBite follows the **Model-View-Controller (MVC)** architectural pattern, organised across the following layers:

```
QuickBite/
├── 📦 model/           POJOs representing core business entities
├── 🖼️  view/            JSP pages rendered server-side
├── 🎮 controller/      HttpServlets handling routing and request dispatch
├── ⚙️  service/         Business logic layer mediating between controllers and DAOs
├── 🗄️  dao/             Data Access Objects handling all database operations
└── 🔧 utils/           Shared utilities (session, validation, image upload, hashing)
```

### 🔄 Request Lifecycle

```
Browser Request
    │
    ▼
┌─────────────┐
│  AuthFilter │  ← Authentication & Role-Based Access Control
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ Controller  │  ← Routing & Request Dispatch
└──────┬──────┘
       │
       ▼
┌─────────────┐
│   Service   │  ← Business Logic & Validation
└──────┬──────┘
       │
       ▼
┌─────────────┐
│     DAO     │  ← SQL Execution
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Database   │  ← MySQL
└──────┬──────┘
       │
       ▼ (Model returned up the chain)
┌─────────────┐
│  JSP View   │  ← Server-side rendering
└─────────────┘
       │
       ▼
Browser Response
```

### 🔐 Role-Based Access Control

Access to all application routes is enforced by `AuthFilter`, which intercepts every incoming request:

| Role | Accessible Routes |
|------|-----------------|
| 👤 Guest | `/home`, `/login`, `/register`, `/about`, `/contact`, `/outlets/*` |
| 🛒 Customer | All guest routes + `/cart`, `/checkout`, `/profile/*` |
| 🔧 Admin | `/admin/*` |
| 👨‍🍳 Staff | `/kitchen/*` |

> Unauthenticated users accessing protected routes are redirected to `/login`. Authenticated users accessing routes outside their role are redirected to `/home`.

---

## ✨ Features

<details>
<summary><b>🛒 Customer</b></summary>

**Authentication**
- Register with personal details, profile image, and password
- Log in using phone number and password
- Log out and invalidate session

**Home & Navigation**
- Browse the home page with popular items across all outlets
- Navigate to outlets, about, contact, and FAQ sections

**Ordering**
- Browse available outlets and select a preferred location
- Search and filter menu items by category or name
- Add items to a session-based cart
- Update item quantities or remove items from cart
- Save items to a personal favourites list
- Review order summary at checkout
- Place instant orders or schedule for a future date and time
- Add special instructions for the kitchen

**Profile Management**
- View and edit personal profile details
- Change account password with old password verification
- View current and past order history
- View and remove saved favourite items

**Feedback**
- Submit a star rating and written feedback via the contact page

</details>

<details>
<summary><b>👨‍🍳 Kitchen Staff</b></summary>

**Authentication**
- Log in and log out

**Order Management**
- View all incoming orders for the assigned outlet
- Filter orders by status: Pending, Ongoing, Complete
- View detailed breakdown of each order including items and quantities
- Initiate an order to move it from Pending to Ongoing
- Mark individual items as done during preparation
- Mark the full order as complete

</details>

<details>
<summary><b>🔧 Administrator</b></summary>

**Authentication**
- Log in and log out

**Dashboard**
- View summary statistics and outlet-level sales reports
- Interact with calendar and data components

**Customer Management**
- View pending customer registrations and approve or reject them
- View all active customers and access individual customer profiles

**Menu Management**
- Add new menu items with image, category, type, ingredients, and allergens
- Edit existing items including per-outlet pricing
- Delete items from the system
- Filter menu view by outlet location

**Feedback & Reports**
- View all customer feedback with ratings and submission dates
- View sales and order reports filtered by outlet

</details>

---

## 🗄️ Database Schema

The `quickbite` database consists of the following tables:

| Table | Description |
|-------|-------------|
| `user` | Stores customer, admin, and staff accounts with role and approval status |
| `outlet` | Canteen or food outlet locations with name, status, and image |
| `item` | Menu items with category, type, description, ingredients, and allergens |
| `outlet_item` | Junction table linking items to outlets with per-outlet pricing |
| `order` | Customer order headers with status, date, notes, and preferred delivery time |
| `order_outlet_item` | Line items for each order linked to a specific outlet and item |
| `favorite` | Records of user-saved item and outlet combinations |
| `feedback` | Customer star ratings and written messages with timestamps |

### 🔗 Key Relationships

```
user              ──< order                (one user places many orders)
order             ──< order_outlet_item    (one order has many line items)
item              ──< outlet_item          (one item available at many outlets)
outlet            ──< outlet_item          (one outlet carries many items)
user              ──< favorite             (one user saves many favourites)
user              ──< feedback             (one user submits many feedbacks)
outlet_item       ──  item + outlet        (composite pricing junction)
order_outlet_item ──  order + outlet + item (order line item junction)
```

---

## ⚙️ Setup & Installation

### Prerequisites

Ensure the following are installed before proceeding:

| Requirement | Version |
|------------|---------|
| ☕ Java JDK | 17+ |
| 🐱 Apache Tomcat | 10 |
| 🐬 MySQL | 8.0 |
| 📦 Maven | 3.8+ |
| 🌑 Eclipse IDE | 2023+ (Jakarta EE) |

---

### Step 1 — Clone the Repository

```bash
git clone https://github.com/[your-repo-url]/QuickBite.git
cd QuickBite
```

---

### Step 2 — Set Up the Database

A SQL dump file is included in the repository for convenience. Run the following command 

manually via the MySQL shell:

```sql
CREATE DATABASE quickbite;
USE quickbite;
SOURCE /path/to/quickbite.sql;
```

> ✅ The dump includes the full schema and a pre-seeded admin account.

---

### Step 3 — Configure the Database Connection

Open the following file and update the credentials to match your local MySQL setup:

```
src/main/java/com/quickbite/utils/DBconfig.java
```

```java
private static final String URL      = "jdbc:mysql://localhost:3306/quickbite";
private static final String USER     = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```

---

### Step 4 — Build the Project

```bash
mvn clean install
```

---

### Step 5 — Deploy to Tomcat

1. Open Eclipse and navigate to the **Servers** tab
2. Right-click your Tomcat 10 server → **Add and Remove**
3. Move **QuickBite** to the configured column → click **Finish**
4. Right-click the server → **Start**

---

### Step 6 — Access the Application

Open your browser and navigate to:

```
http://localhost:8080/QuickBite
```

---

## 🚀 Usage

### 🔑 Default Admin Account

A default admin account is pre-seeded in the SQL dump:

| Field | Value |
|-------|-------|
| Email | `admin@gmail.com` |
| Password | `Admin@123` |

> ⚠️ **Security Notice:** Change the default admin password immediately after first login in any non-development environment.

---

### 👤 Customer Workflow

```
1. Register at /register
       ↓
2. Await admin approval
       ↓
3. Log in at /login
       ↓
4. Browse outlets at /outlets
       ↓
5. Add items to cart
       ↓
6. Checkout at /checkout (instant or scheduled)
       ↓
7. Track orders at /profile/order-history
```

---

### 👨‍🍳 Kitchen Staff Workflow

```
1. Log in → auto-redirected to /kitchen
       ↓
2. View orders by status (Pending / Ongoing / Complete)
       ↓
3. Open order details → initiate order
       ↓
4. Mark items done as they are prepared
       ↓
5. Mark full order as complete
```

---

### 🔧 Admin Workflow

```
1. Log in → auto-redirected to /admin
       ↓
2. Approve / reject pending customers under Customer Management
       ↓
3. Add, edit, or remove menu items under Menu Management
       ↓
4. Monitor feedback and outlet reports from the Dashboard
```

---

<div align="center">

*Developed for the CS5054NI Advanced Programming Techniques module coursework — Islington College Kathmandu*

</div>
