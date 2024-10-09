<h1 align="center">TimeTalk API</h1>

*<p align="center">TimeTalk API is a RESTFul API created to be used in a social network to ask questions, share experiences and make comments.</p>*

<p float="left">
  <img src="/images/TimeTalk.png" width="700" >
</p>

The features of the API are the next:
- [Create an account](#signup)
- [Login](#login)
- CRUD operations:
  - [Get all the posts](#get-all-posts)
  - [Create a new post](#create-post)
  - [Update an existing post](#update-post)
  - [Delete a post](#delete-post)
- Additional features:
  - [Comment on a post](#comment-post)
  - [Give a like to a post](#add-like-post)
  - [Remove like from post](#remove-like-post)
  - [Get basic user data](#get-profile)
- Error handling (Exceptions)  

## Used technologies
- Java 22
- Spring Boot 3
- Spring Security
- JWT
- JPA/Hibernate
- MySQL

## Endpoints

### <a name="signup"></a>Create an account
- **Method**: `POST`
- **Route**: `/api/v1/auth/signup`
- **Description**: Create a new account and obtain a JWT token. Follow the password best practices (minimum length of 8 characters, and use of a number) for security. Specify the role USER.
- **Request Body**:
```json
{
  "firstName": "Christian",
  "lastName": "Ramírez",
  "email": "christian@mail.com",
  "password": "mypasswordissecure123",
  "roleRequest": {
    "roleListName": [
      "USER"
    ]
  }
}
```
- **Response**:
    - **Status Code**: `200`
    - **Response Body**:
```json
{
  "email": "christian@mail.com",
  "jwt": "JWT_TOKEN",
  "status": true,
  "message": "User created successfully"
}
```

### <a name="login"></a>Login
- **Method**: `POST`
- **Route**: `/api/v1/auth/login`
- **Description**: Authenticate a user and obtain a JWT token.
- **Request Body**:
```json
{
  "email": "christian@mail.com",
  "password": "mypasswordissecure123"
}
```
- **Response**:
    - **Status Code**: `200`
    - **Response Body**:
```json
{
  "email": "chris@mail.com",
  "jwt": "JWT_TOKEN",
  "status": true,
  "message": "User logged successfully."
}
```


### <a name="get-all-posts"></a>Get all the posts
- **Method**: `GET`
- **Route**: `/api/v1/posts`
- **Description**: Returns all available posts. You must include a valid JWT in the `Authorization` header
of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`
- **Response**:
    - **Status Code**: `200`
    - **Response Body**:
```json
[
  {
      "id": 1,
      "author": {
        "firstName": "John",
        "lastName": "Doe"
      },
      "creationDate": "2024-10-08T08:00:51.492769",
      "title": "My first post",
      "description": "This is a post!",
      "likes": [],
      "comments": []
  },
  {
    "id": 2,
    "author": {
      "firstName": "John",
      "lastName": "Doe"
    },
    "creationDate": "2024-10-08T08:47:54.180589",
    "title": "My second post",
    "description": "This is a new post.",
    "likes": [
      {
        "user": {
          "firstName": "Christian",
          "lastName": "Ramírez"
        }
      }
    ],
    "comments": [
      {
        "author": {
          "firstName": "Christian",
          "lastName": "Ramírez"
        },
        "creationDate": "2024-10-08T08:48:50.231948",
        "content": "Amazing!"
      }
    ]
  }
] 
```

### <a name="create-post"></a>Create a new post
- **Method**: `POST`
- **Route**: `/api/v1/posts`
- **Description**: Create a new post. You must include a valid JWT in the `Authorization` header
  of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`
- **Request Body**:
```json
{
  "title": "My new post",
  "description": "This is a post!"
}
```
- **Response**:
    - **Status Code**: `200`
    - **Response Body**:
```json
{
  "id": 1,
  "author": {
    "firstName": "Christian",
    "lastName": "Ramírez"
  },
  "creationDate": "2024-10-08T08:59:40.9176076",
  "title": "My new post",
  "description": "This is a post!",
  "likes": [],
  "comments": []
}
```

### <a name="update-post"></a>Update an existing post
- **Method**: `PUT`
- **Route**: `/api/v1/posts/{post-id}`
- **Description**: Updates an existing post. The `{post-id}` parameter should be replaced with the ID of the post you want to update. Only the author of the post can access this endpoint. You must include a valid JWT in the `Authorization` header
  of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`
- **Request Body**:
```json
{
  "title": "My post updated",
  "description": "This post has been updated."
}
```
- **Response**:
    - **Status Code**: `200`
    - **Response Body**:
```json
{
  "id": 2,
  "author": {
    "firstName": "Christian",
    "lastName": "Ramírez"
  },
  "creationDate": "2024-10-08T09:08:18.681976",
  "title": "My post updated",
  "description": "This post has been updated!",
  "likes": [],
  "comments": []
}
```

### <a name="delete-post"></a>Delete a post
- **Method**: `DELETE`
- **Route**: `/api/v1/posts/{post-id}`
- **Description**: Deletes an existing post. The `{post-id}` parameter should be replaced with the ID of the post you want to delete. Only the author of the post can access this endpoint. You must include a valid JWT in the `Authorization` header
  of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`.
- **Request Body**: None
- **Response**:
    - **Status Code**: `204`
    - **Response Body**: None

### <a name="comment-post"></a>Comment a post
- **Method**: `POST`
- **Route**: `/api/v1/posts/{post-id}/comments`
- **Description**: Add a comment to an existing post. The `{post-id}` parameter should be replaced with the ID of the post you want to comment. You must include a valid JWT in the `Authorization` header
  of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`.
- **Request Body**:
```json
{
  "content": "Amazing!"
}
```
- **Response**:
    - **Status Code**: `201`
    - **Response Body**:
```json
{
  "author": {
    "firstName": "Christian",
    "lastName": "Ramírez"
  },
  "creationDate": "2024-10-08T09:13:55.4522113",
  "content": "Amazing!"
}
```

### <a name="add-like-post"></a>Give like to a post
- **Method**: `POST`
- **Route**: `/api/v1/posts/{post-id}/likes`
- **Description**: Add a like to an existing post. The `{post-id}` parameter should be replaced with the ID of the post you want to give a like. A user cannot like the same post more than once. The author of the post is not allowed to like their own post. You must include a valid JWT in the `Authorization` header
  of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`.
- **Request Body**: None
- **Response**:
    - **Status Code**: `200`
    - **Response Body**:
```json
{
  "postId": 1,
  "totalLikes": 1
}
```

### <a name="remove-like-post"></a>Remove like from a post
- **Method**: `DELETE`
- **Route**: `/api/v1/posts/{post-id}/likes`
- **Description**: Remove a like from an existing post. The `{post-id}` parameter should be replaced with the ID of the post from which you want to remove the like. Only the user who previously liked the post can use this endpoint. You must include a valid JWT in the `Authorization` header
  of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`.
- **Request Body**: None

- **Response**:
  - **Status Code**: `204`
  - **Response Body**: None

### <a name="get-profile"></a>Get basic user data
- **Method**: `GET`
- **Route**: `/api/v1/users/profile`
- **Description**: Get first name and last name from the authenticated user. Only the authenticated user can access this endpoint. You must include a valid JWT in the `Authorization` header
  of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`.
- **Request Body**: None
- **Response**:
    - **Status Code**: `200`
    - **Response Body**:
```json
{
  "firstName": "Christian",
  "lastName": "Ramírez"
}
```
