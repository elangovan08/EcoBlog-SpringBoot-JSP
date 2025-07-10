# EcoBlog – An Eco-Conscious Blog Platform Using Spring Boot and JSP

Eco-conscious blog platform built with Spring Boot + JSP. Features posts, comments, likes, bookmarks, login/signup, roles, and more. WAR-packaged for deployment.

---

## 🔒 User Roles

| Role    | Access Permissions |
|---------|--------------------|
| Admin   | Manage users, posts, and comments |
| Poster  | Create and manage own posts |
| Viewer  | View posts and comment/like/bookmark |

---

## 🖼️ Screenshots

> Add screenshots of:
- Home page
- Post list and details
- Admin dashboard
- Comment section
- Responsive view on mobile

---
## 📁 Project Structure

EcoBlog/
├── src/
│ ├── main/
│ │ ├── java/com/ecoblog/...
│ │ ├── resources/
│ │ │ ├── application.properties
│ │ │ └── static/ (CSS, JS, images)
│ │ └── webapp/WEB-INF/jsp/
│ │ ├── home.jsp
│ │ ├── post-form.jsp
│ │ ├── post-list.jsp
│ │ ├── post-detail.jsp
│ │ ├── contact.jsp
│ │ ├── login.jsp
│ │ └── includes/ (header.jsp, footer.jsp)
└── pom.xml
---

## ⚙️ Installation & Setup

### Prerequisites
- Java 17 or above
- Maven
- MySQL
- IntelliJ IDEA / Eclipse
- Apache Tomcat (for WAR deployment)

### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/EcoBlog-SpringBoot-JSP.git
    cd EcoBlog-SpringBoot-JSP
    ```

2. Set up MySQL database:
    ```sql
    CREATE DATABASE ecoblog;
    ```

3. Configure `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/ecoblog
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Build the project:
    ```bash
    mvn clean install
    ```

5. Deploy the `WAR` file to Tomcat or run directly:
    ```bash
    mvn spring-boot:run
    ```

---

## 💡 Future Enhancements

- 🔐 Integrate Spring Security for full role-based login
- 📱 Progressive Web App (PWA) support
- 🌐 Multi-language support
- 📈 Post analytics dashboard
- 🗳️ Post voting and reaction emojis

---

## 🤝 Contributing

Contributions are welcome!  
Fork the repo, create a branch, make your changes, and submit a pull request.

---

## 🙌 Acknowledgements

- Spring Boot Documentation
- Bootstrap 5
- Unsplash (for eco images)
- JSTL & JSP Communities

---

## 🌏 About EcoBlog

EcoBlog is built with the mission to **promote awareness**, **share green ideas**, and **protest harmful practices**.  
Together, let’s create a cleaner and more conscious future. 🌿

