# GitHub Repository Searcher 🚀

A Spring Boot backend application that allows users to search for GitHub repositories using the GitHub REST API, store results in a PostgreSQL database, and retrieve them using filters.

---

## 📌 Features

- 🔍 Search GitHub repositories by:
  - Query keyword
  - Language
  - Sort (stars, forks, updated)
- 🗃 Store repository details in PostgreSQL
- 🧾 Retrieve stored results with filters:
  - Language
  - Minimum stars
  - Sort order
- ⚙️ Avoids duplicate entries by updating existing records
- ❌ Handles empty results and invalid responses

---

## 🛠️ Technologies Used

- Java 17 / 22
- Spring Boot 3+
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- REST APIs
- Postman (for testing)

-----
🔌 API Endpoints
📮 POST /api/github/search
Request Body:

json
Copy
Edit
{
  "query": "spring boot",
  "language": "Java",
  "sort": "stars"
}
--> Response:

json
Copy
Edit
{
  "message": "Repositories fetched and saved successfully",
  "repositories": [ ... ]
}

-----
📥 GET /api/github/repositories
-- GET /api/github/repositories?language=Java&minStars=100&sort=stars

-----------------------------------


-- Author--
Nishikant Gupta


