# BE OnBoarding: Social Network Service Feed System

      └── main/
         ├── resources/
         │   └── application.properties
         | 
         └── kotlin/com/example/demo/
             ├── DemoApplication.kt
             ├── controller/
             │   ├── CommentController.kt
             │   ├── FollowController.kt
             │   ├── LikeController.kt
             │   ├── PostController.kt
             │   └── UserController.kt
             |
             ├── domain/
             │   └── 도메인 내부 데이터 type
             |
             ├── dto/
             |   └── 도메인 외부 통신 데이터(Request/Response)type
             |     
             ├── repository/
             │   └── 도메인별 Interface/Implement
             │   
             └── service/
                 └── 도메인별 Interface/Implement

## 구현 기능

- 유저 생성
- 포스트 생성
- 피드 읽기(조회)
- 유저에게 Follow 걸기
- Unfollow
- Follower 리스트 조회
- Followee 리스트 조회
- 특정 유저와의 촌수 확인
- 게시물 조회 (좋아요 정보, 커멘트 정보 포함)

## 미구현 기능

- 좋아요
- 좋아요 취소
- 커멘트 생성

### ERD

### API 명세




