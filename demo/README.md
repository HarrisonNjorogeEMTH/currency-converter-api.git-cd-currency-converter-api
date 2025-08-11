# Currency Conversion API

A minimal Spring Boot backend that:
- Fetches real-time exchange rates from the **Open Exchange Rates API** (`https://open.er-api.com/v6/latest/{CURRENCY_CODE}`)
- Converts between **USD**, **EUR**, and **JPY**
- Keeps a history of the last 5 conversions
- Generates a **PDF report** of the conversion history in a grid format
- Sends the PDF to a specified email address (via Gmail SMTP with App Password)

---

## Features
- Real-time currency rates
- Conversion between USD, EUR, and JPY
- PDF report with grid layout
- Automatic pagination in PDF
- Email sending via SMTP

---

## Requirements
- Java 17+
- Maven 3+
- Gmail account with [App Password enabled](https://support.google.com/accounts/answer/185833)
- Internet connection

---

## Setup

1. **Clone the repository**
```bash
git clone https://github.com/kamau-n/currency-converter-api.git
cd currency-converter-api


##  Usage 
Run
```
GET http://localhost:8080/currency/convert?from=USD&to=JPY&amount=100


```