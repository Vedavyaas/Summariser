# ■ Summarizer (In Development)
## ■ Overview
This is an **AI-powered summarization service** built with **Java + Spring Boot**.
It takes input text and produces a **concise, one-sentence summary**.
The long-term goal is to integrate it directly with **WhatsApp**, allowing users to instantly summarize chats, documents, or shared
---
## ■ Current Features
- ■ Spring Boot backend with summarization logic
- ■ GROQ API integration for AI-based summarization
- ■ REST endpoint exposed via Render deployment
---
## ■ Deployment
This project is configured for **Render**:
- Environment variables (GROQ_API_KEY, GROQ_API_URL) are set in the Render dashboard.
- No `.env` file is required.
- Dockerfile is included at the repo root for containerized deployment.
```bash
https://vs-ummariser.onrender.com
```
---
## ■ Roadmap
- [ ] WhatsApp API integration for direct chat summarisation
- [ ] Scalable deployment for multiple users
- [ ] Production-grade monitoring and logging
---
■ **Status**: Actively in development. Current focus is on **Render deployment + WhatsApp integration**.
