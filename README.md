# SaaS Feedback Collector - Template

This repository contains a template for creating a SaaS feedback collector. The template helps you to start with the project structure, basic functionality, and deployment setup. You can customize it according to your tasks.

🧩 Scenario
Your SaaS product wants to allow users to submit feedback on features. Create a simple feedback collection system that allows users to submit feedback, view submitted entries, and list feedback by category.

## ✅ Requirements

### 🔧 Backend (Java 17, Spring Boot 3, AWS Lambda, AWS DynamoDB)

Build a serverless backend with the following endpoints:

`POST /feedback` – Submit a new feedback (fields: id, userId, category, message, timestamp)

`GET /feedback/{id}` – Get feedback by ID

`GET /feedback?category={category}` – List feedbacks filtered by category

Details:

- Use Spring Boot inside an AWS Lambda function. The [handler](backend/src/main/java/org/nextpertis/saasfeedbackcollector/config/AsynchronousLambdaHandler.java) is already given for you.
- Store data in AWS DynamoDB.
- Use basic validation.
- Return JSON responses.

### 🎨 Frontend (Angular 16, Responsive UI)
Build a simple UI with:

- A form to submit feedback (fields: category, message)
- A page to list all feedbacks, with category filter
- A mobile-friendly responsive layout

Details:

- Use Angular reactive forms.
- Show loading states and basic validation.
- Keep the design clean and usable on both mobile and desktop.

### ☁️ Infrastructure (GitHub Actions, Deployment)
Setup GitHub Actions to:

- Lint and test code on push.
- Build and deploy backend to AWS Lambda (can simulate deploy or use dummy script).
- Build frontend and simulate deployment (e.g. upload to a dummy S3 bucket or simply echo a success message).

## 💡 Tips

- The backend can be a single Lambda function or can be split into multiple Lambda functions if needed.
- Frontend can use mock API responses if deployment is too complex.
- You are encouraged to use best practices (DTOs, services, validation layers).
