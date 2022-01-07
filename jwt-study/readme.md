# JWT를 사용한 api프로젝트
* jwt를 통해 권한 확인
* 회원 가입과 회원 별 권한, 권한에 따른 기능 사용 요소 적용

## api/authenticate
* ID, PW를 받아서 토큰을 만들고, 정보를 저장하여 return한다.

![](https://i.imgur.com/8bHegIT.png)

## api/signup
* ID, PW를 받아서 이미 존재하는 ID라면 회원가입 불가능
* 존재하지 않는 ID라면 권한정보를 생성하여(해당 프로젝트는 유저권한만 부여) 아이디 생성
    * UserDto를 통한 전달
![](https://i.imgur.com/nJRIGGs.png)

![](https://i.imgur.com/9W3GzDi.png)

## api/user/{username}
* preauthorize되어 있어 ADMIN계정 즉 운영자만 호출 가능하다.

![](https://i.imgur.com/2jgaaSb.png)
* 유저 계정인 fbcks97로 검색할 경우 권한 에러 발생

![](https://i.imgur.com/imjnibw.png)
![](https://i.imgur.com/LWiYAQk.png)
![](https://i.imgur.com/Xu3jgVZ.png)
* 기존 운영자 계정인 admin의 쿠키 정보를 받아와서 사용하면 검색 가능하다.
    * POST맨의 test를 사용하여 이전에 authenticate에서 운영자의 쿠키를 받아왔다.

![](https://i.imgur.com/EOJyIW3.png)
![](https://i.imgur.com/bBe5KwS.png)
* 이후 fbcks97계정의 쿠키 정보를 동일한 방법으로 받아와서 사용하면 검색 불가능하다.
    * 이는 이 과정에서 받아온 쿠키 정보는 ADMIN권한이 없는 단순한 사용자 계정이기 때문이다.

## api/user
* USER, ADMIN 모두 호출 가능하다.
![](https://i.imgur.com/G2GiHLx.png)




---


* JWT토큰의 활용과 사용자 정보 저장 등에 대해 공부한 프로젝트이다.
* entity, Dto
* service에서 사용될 모듈들을 따로 미리 사용법에 따라 정의하여 패키지를 분리하여 사용.
