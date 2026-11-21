# First Tool Calling Application

A Java-based AI application demonstrating **LLM Tool Calling** using LangChain4j, Google Gemini and Java.

## Overview

This project demonstrates how an LLM can interact with custom Java methods through tool calling.

Instead of simply generating responses, the model can select and execute registered Java tools to retrieve information.

## Tech Stack

- Java 23
- Maven
- LangChain4j
- Google Gemini API

## Features

- Custom tools using LangChain4j's `@Tool` annotation
- Dynamic tool selection by the LLM
- Laptop brand and model availability lookup
- Laptop price lookup
- Conversation memory using `MessageWindowChatMemory`
- Interactive console-based chat interface

## How It Works

1. User submits a natural-language query.
2. Gemini determines whether a tool is required.
3. LangChain4j executes the selected Java method.
4. Tool results are returned to the model.
5. Gemini generates a natural-language response.

## Setup

1. Clone the repository.
2. Configure your Gemini API key as an environment variable named `GEMINI_API_KEY`.
3. Configure the model name in `Main.java`.
4. Reload Maven dependencies.
5. Run the `Main` class.

## Learning Objective

Understanding how LLMs interact with external Java functionality through tool calling, including tool definitions, execution, and conversation memory.

## Status

Learning project — actively experimenting with LangChain4j and Agentic AI.