# rollling-viewpager

### 1\. 스크롤을 빠르게 넘기면, 앱이 죽는다.

-   Timer와 Handler 객체를 이용해서 1초마다 페이지를 변경해주고 있는데, 뷰페이저를 아주많이 빠르게 스크롤 할 경우에는, 페이지를 관리 해주는 변수와, 실제로 유저가 보고 있는 페이지가 매칭이 안되는 경우가 생긴다. Timer 및 Handler 객체를 잘 다루지 못하고 있는 것 같다. 공부해서 여기에 정라해 놓아야 겠다.

### 2\. 이것을 커스텀 뷰로 바꾸어서 개발을 해보자.

### 3\. 이것을 라이브러리로 개발을 해보자.
