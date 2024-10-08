# TimeTalk API

TimeTalk API is a RESTFul API created to be used on TimeTalk, a social network (currently on development)
to ask questions, share experiences and make comments about watches.
The features of the API are the next:
- Create an account
- Login
- CRUD operations:
  - [Get all the posts](#get-all-posts)
  - [Create a new post](#create-post)
  - [Update an existing post](#update-post)
  - [Delete a post](#delete-post)
- Additional operations:
  - [Comment on a post](#comment-post)
  - [Give a like to a post](#add-like-post)
  - [Remove like from post](#remove-like-post)
- Error handling (Exceptions)  



## Used technologies
    - Java 22
    - Spring Boot 3
    - Spring Security
    - JWT
    - JPA/Hibernate
    - MySQL
    - Docker

## Endpoints
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
- **Description**: Updates an existing post. The `{post-id}` parameter should be replaced with the ID of the post you want to update. You must include a valid JWT in the `Authorization` header
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
- **Description**: Deletes an existing post. The `{post-id}` parameter should be replaced with the ID of the post you want to delete. You must include a valid JWT in the `Authorization` header
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
- **Description**: Add a like to an existing post. The `{post-id}` parameter should be replaced with the ID of the post you want to give a like. You must include a valid JWT in the `Authorization` header
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
- **Description**: Remove a like from an existing post. The `{post-id}` parameter should be replaced with the ID of the post from which you want to remove the like. You must include a valid JWT in the `Authorization` header
  of the request. For example: `Authorization: Bearer YOUR_JWT_TOKEN`.
- **Request Body**: None

- **Response**:
  - **Status Code**: `204`
  - **Response Body**: None
