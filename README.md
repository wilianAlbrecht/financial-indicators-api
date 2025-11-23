# Earnings-dividend-yield


## Overview

The Financial Indicators API is a REST API built with Java 21 and Spring Boot 3 that provides essential financial indicators using data from public market sources. The goal of this project is to make financial analysis easier, faster, and more accessible for anyone who wants to explore stock fundamentals without dealing with complicated data setups.

## How it Works

The API collects financial information directly from public and free market data providers. Using these sources, it retrieves the most relevant data for a given stock symbol and calculates important financial indicators based on that information.
In simple terms, the process works like this:
 - It requests financial data from public APIs.
 - It processes that information internally.
 - It calculates useful financial indicators.
 - It returns everything in a clean and easy-to-understand JSON format.
This design keeps the experience straightforward while still allowing the project to grow and support more metrics over time.


## Current State

At the moment, the API already supports three working indicators: Earnings Yield, Dividend Yield, and Price-to-Earnings (P/E). The project is still in development and not yet deployed to production, but anyone is free to download it, run it locally, and explore how it works.


## Future Development

More indicators will be added over time, including Price-to-Book (P/B), ROE, ROA, Net Margin, and other fundamental metrics that are valuable for stock analysis.

A major upcoming improvement is the integration with ➡ [openFinanceData](https://github.com/wilianAlbrecht/OpenFinanceData), another project of mine focused on providing free, unrestricted market data for financial analysis. Once both projects are connected, users will no longer need to configure their own tokens manually, making the API easier to use and more powerful overall.


## Running the Project

You can run the Financial Indicators API locally with just a few steps. The project uses Maven Wrapper, so you don’t need Maven installed on your system — everything is already included.

To begin, clone the repository and enter the project folder:

```
git clone https://github.com/wilianAlbrecht/financial-indicators-api
cd financial-indicators-api

```

Once inside the project, you can start the API using:

On Linux, use:

```
./mvnw spring-boot:run

```

On Windows, use:

```
mvnw.cmd spring-boot:run

```

After the application starts, the API will be available on your local machine, usually at:


```
http://localhost:8080
```

## Token Configuration (BRAPI Required)

The Financial Indicators API requires a BRAPI token to retrieve external market data.
There are two ways to configure the token, but only one of them is recommended.

### Recommended: Using System Environment Variables

Using environment variables is the safest and cleanest way to configure your BRAPI token.
This keeps your token out of the project files and prevents accidental exposure on GitHub.

On Linux / macOS, run:
```
export BRAPI_TOKEN=YOUR_BRAPI_TOKEN_HERE
```

On Windows PowerShell, use:
```
setx BRAPI_TOKEN "YOUR_BRAPI_TOKEN_HERE"
```

After setting the variable, restart your terminal and run the project again.
The API will automatically read the token from your system.

### Not Recommended: Adding the Token in application.properties

You can also set the token directly inside the project, but this method is not recommended, especially if you plan to publish your project or commit your changes.

If you still want to use this approach, open (or create) the file:

```
src/main/resources/application.properties
```

And add the line:
```
brapi.token=YOUR_BRAPI_TOKEN_HERE
```

Remember: if you commit this file, your token becomes public, so use this approach only for local testing.



## Thank You

Thank you for your interest in this project! Your curiosity and support help its development and future improvements. If you’d like to follow the evolution of the API or contribute, feel free to explore the repository at any time.
