# Library System
This project is for a simple library system.

## Features

- 

## API Documentation

```yaml
openapi: 3.0.1
info:
  title: OpenAPI definition
  version: v0
servers:
  - url: http://localhost:8080
    description: Generated server url
paths:
  /library/book/return/{id}:
    put:
      tags:
        - library-controller
      operationId: returnBook
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/Book'
        '404':
          description: Not Found
        '500':
          description: Internal Server Error
  /library/book/loan/{id}:
    put:
      tags:
        - library-controller
      operationId: loanBook
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/Book'
        '404':
          description: Not Found
        '500':
          description: Internal Server Error
  /library/book:
    get:
      tags:
        - library-controller
      operationId: searchBook
      parameters:
        - name: title
          in: query
          required: false
          schema:
            type: string
        - name: author
          in: query
          required: false
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Book'
        '404':
          description: Not Found
        '500':
          description: Internal Server Error
    post:
      tags:
        - library-controller
      operationId: addBook
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/AddBookRequest'
        required: true
      responses:
        '201':
          description: Created
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/Book'
        '404':
          description: Not Found
        '500':
          description: Internal Server Error
  /library/book/available:
    get:
      tags:
        - library-controller
      operationId: getAvailableBooks
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Book'
        '404':
          description: Not Found
        '500':
          description: Internal Server Error
components:
  schemas:
    Book:
      type: object
      properties:
        id:
          type: integer
          format: int64
        title:
          type: string
        author:
          type: string
        availability:
          type: boolean
    AddBookRequest:
      type: object
      properties:
        title:
          type: string
        author:
          type: string

```