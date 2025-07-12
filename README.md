# 📰 News Aggregator Application

This is a full-stack news aggregator application that allows users to:

* Register and log in
* Save their preferred news category and country
* Fetch personalized news articles from an external news API

---

## 🔧 Tech Stack

* **Backend:** Spring Boot, Spring Security, JWT, WebClient, H2 Database
* **Frontend:** React, Axios
* **News API:** [NewsAPI.org](https://newsapi.org)

---

## 📆 Backend Setup

1. Navigate to the backend project directory.
2. Run the app:

```bash
./mvnw spring-boot:run
```

The app should run on `http://localhost:8002`

### 🔐 API Endpoints

| Method | Endpoint           | Description                     | Auth Required |
| ------ | ------------------ | ------------------------------- | ------------- |
| POST   | `/api/register`    | Register a new user             | ❌ No          |
| POST   | `/api/login`       | Login and receive JWT token     | ❌ No          |
| GET    | `/api/preferences` | Get user preferences            | ✅ Yes         |
| PUT    | `/api/preferences` | Update user preferences         | ✅ Yes         |
| GET    | `/api/news`        | Fetch news based on preferences | ✅ Yes         |

---

## 🌐 Frontend Setup

1. Navigate to the frontend folder (e.g. `news-aggregator-ui`):

```bash
cd news-aggregator-ui
```

2. Install dependencies:

```bash
npm install
```

3. Start the React app:

```bash
npm start
```

The app runs at [http://localhost:3000](http://localhost:3000)

Make sure backend CORS is configured for `http://localhost:3000`.

---

## ✨ Features

* JWT-based authentication
* Store user preferences in H2 in-memory database
* Personalized news fetched from NewsAPI

---

## 🚫 CORS Setup

In `SecurityConfig.java`, allow frontend origin:

```java
config.setAllowedOrigins(List.of("http://localhost:3000"));
```

---

## 🔎 Testing

Sample user you can register:

```json
{
  "username": "testuser",
  "password": "test123"
}
```

---

## 🔧 API Key Setup

Sign up at [https://newsapi.org](https://newsapi.org) and set your key in `application.properties`:

```properties
news.api.key=YOUR_NEWS_API_KEY
```

---

## 🤔 Future Enhancements

* Add persistent login via `localStorage`
* Add logout functionality
* Enhance UI with loaders/spinners
* Add pagination to news feed

---

## ✨ Demo Screenshots

> Coming soon (add screenshots here)

1. User Registration - <img width="758" height="308" alt="image" src="https://github.com/user-attachments/assets/ab5708a3-c4c0-40b6-af8e-53dce3366623" />
2. User Login - <img width="611" height="351" alt="image" src="https://github.com/user-attachments/assets/c201cce8-4fc1-400d-bba9-65ccac7315f1" />
3. Save Preferences - <img width="718" height="372" alt="image" src="https://github.com/user-attachments/assets/c2fb5737-ca99-41d1-9550-6b1e14448002" />
4. fetch News - <img width="935" height="500" alt="image" src="https://github.com/user-attachments/assets/085d3796-abf0-487a-a2e1-ad1bf2f0c97c" />


## 🙏 Acknowledgements

* [NewsAPI.org](https://newsapi.org) for the news feed
