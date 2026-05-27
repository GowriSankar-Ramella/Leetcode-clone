# LeetCode Clone

A full-stack LeetCode-style coding platform built using React, Spring Boot, and Judge0. Users can solve coding problems in multiple programming languages, execute code securely using Judge0, track submissions, and monitor their coding statistics through a profile dashboard.

---

# Features

## Authentication & Security

* User Registration
* User Login
* JWT-based Authentication
* BCrypt Password Encryption
* Protected Routes
* Stateless Spring Security Configuration
* Secure API Access with JWT Tokens

---

## Problem System

* Problem Listing Page
* Problem Details Page
* Public Test Cases Display
* Multi-language Starter Code
* Language Switching
* Monaco Code Editor Integration

---

## Code Execution

* Run Code Against Public Test Cases
* Submit Code Against Hidden Test Cases
* Judge0 Self-hosted Integration
* Multi-language Support

  * Java
  * Python
  * JavaScript
* Dynamic Wrapper Generation
* Complex Input Handling

  * Arrays
  * Strings
  * Matrices
* Execution Result Panel

  * Accepted
  * Wrong Answer
  * Compilation Error
  * Runtime Error

---

## Submission Tracking

* Save Submission History
* User-wise Submission Tracking
* My Submissions Page
* Passed Test Cases Count
* Execution Status Tracking
* Submission Timestamp Tracking

---

## Profile Dashboard

* User Profile Information
* Total Submissions
* Accepted Submissions
* Acceptance Rate

---

## Editor Features

* Monaco Editor
* Language-specific Starter Code
* Code Persistence using Local Storage
* Separate Saved Code Per Problem & Language
* Loading States for Run/Submit Actions

---

# Tech Stack

## Frontend

* React
* Vite
* Tailwind CSS
* React Router DOM
* Axios
* Monaco Editor

## Backend

* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* MySQL
* Lombok

## Code Execution

* Judge0
* Docker
* AWS EC2

---

# System Architecture

```text
Frontend (React)
        ↓
Spring Boot Backend
        ↓
Execution Engine
        ↓
Judge0
        ↓
Docker Sandboxed Execution
```

---

# Project Structure

## Frontend Structure

```text
src
│
├── components
├── pages
├── services
├── context
├── hooks
├── routes
├── layouts
├── utils
│
├── App.jsx
└── main.jsx
```

---

## Backend Structure

```text
src/main/java/com/leetcode/backend
│
├── auth
├── execution
├── problem
├── startercode
├── submission
├── testcases
├── user
├── security
├── wrapper
│
└── config
```

---

# Authentication Flow

```text
User Login
    ↓
JWT Token Generated
    ↓
Frontend Stores Token
    ↓
Token Sent with API Requests
    ↓
Spring Security JWT Filter
    ↓
Authenticated User Access
```

---

# Code Execution Flow

```text
User Writes Code
        ↓
Frontend Sends Execution Request
        ↓
Spring Boot Backend
        ↓
Wrapper Generation
        ↓
Judge0 API
        ↓
Sandboxed Docker Execution
        ↓
Execution Result Returned
```

---

# Dynamic Wrapper Generation

The platform dynamically generates execution wrappers for each language.

Example Java Wrapper:

```java
class Main {

    public static void main(String[] args){

        Solution solution = new Solution();

        System.out.println(
            solution.add(2,3)
        );

    }

}
```

This allows user-submitted methods to be executed automatically against test cases.

---

# API Endpoints

## Authentication APIs

```http
POST /auth/register
POST /auth/login
```

---

## Problem APIs

```http
GET /problems
GET /problems/{id}
GET /problems/{id}/starter-codes/{language}
GET /problems/{id}/public-testcases
```

---

## Execution APIs

```http
POST /execute
```

---

## Submission APIs

```http
GET /submissions/my-history
```

---

## User APIs

```http
GET /users/me
GET /users/stats
```

---

# Supported Languages

| Language   | Judge0 Language ID |
| ---------- | ------------------ |
| Java       | 62                 |
| Python     | 71                 |
| JavaScript | 63                 |

---

# Database Design

## User

```text
id
name
email
password
role
```

---

## Problem

```text
id
title
description
difficulty
```

---

## Submission

```text
id
user_id
problem_id
language
status
passed
total
created_at
```

---

## TestCase

```text
id
problem_id
input
expected_output
is_hidden
```

---

# Security Features

* BCrypt Password Encryption
* JWT Authentication
* Stateless Sessions
* Spring Security Filter Chain
* Protected APIs
* CORS Configuration
* User-specific Submission Access

---

# Deployment

## Judge0 Deployment

Judge0 was self-hosted using:

* AWS EC2
* Docker
* Docker Compose

The execution engine communicates with the Judge0 instance through REST APIs.

---

# Future Improvements

* Admin Panel for Problem Creation
* Code Execution Analytics
* Contest System
* Discussion Forum
* Leaderboards
* Dark Theme
* Responsive Mobile UI
* Resizable Editor Panels
* Dockerized Full-stack Deployment
* CI/CD Pipeline

---

# Installation

## Frontend Setup

```bash
npm install
npm run dev
```

---

## Backend Setup

```bash
./mvnw spring-boot:run
```

---

## Judge0 Setup

```bash
docker compose up -d
```

---

# Environment Variables

## Frontend

```env
VITE_API_URL=http://localhost:8080
```

---

## Backend

```env
JWT_SECRET=your_secret_key
DB_URL=jdbc:mysql://localhost:3306/leetcode_clone
DB_USERNAME=root
DB_PASSWORD=password
JUDGE0_URL=http://your-judge0-server:2358
```

---

# Learning Outcomes

This project helped in understanding:

* Full-stack application development
* Spring Security & JWT
* REST API Design
* Judge0 Integration
* Dynamic Code Execution
* Multi-language Wrapper Generation
* React State Management
* Protected Frontend Routing
* Docker-based Sandboxed Execution
* AWS Deployment
