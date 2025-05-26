# 🧬 Profile Service (gRPC + Spring Boot + MongoDB)

This service provides CRUD operations for user profiles using **gRPC** and **Spring Boot**, backed by a **MongoDB** NoSQL database. It follows best practices for gRPC communication, protobuf definitions, and integration testing.

---

## 📦 Features

- 🛠️ Create, Read, Update, Delete (CRUD) operations on profile data
- 📡 Uses gRPC for efficient communication (binary protocol)
- 📁 MongoDB for persistent storage (schema-less, document-based)
- ✅ Integration tests using Spring Boot context and in-memory Mongo
- 🔐 Designed for secure gRPC and eventual auth-based Mongo connections

---

## 🧱 Tech Stack

| Layer            | Tech                         |
|------------------|------------------------------|
| Backend Framework | Spring Boot (3.x+)           |
| Protocol          | gRPC with Protocol Buffers   |
| Database          | MongoDB (NoSQL)              |
| Build Tool        | Maven                        |
| Testing           | Spring Boot Test + Testcontainers/Mongo Memory |
| Language          | Java 17+                     |

---
