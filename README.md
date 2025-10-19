# 1. Monolithic Architecture?

**"도메인과 도메인 DB가 강하게 결합되는 문제"**
1. 도메인 하나가 전체 서비스에 영향을 미친다.
2. 각 도메인의 유지보수가 어렵다.
3. 빌드 및 배포 시간이 길어서 생산성이 낮아진다.
4. Scale-out 이 어렵다.

# 2. MSA (Micro Service Architecture)?

**"도메인/서비스 별로 독립적으로 개발/배포되는 아키텍쳐"**

도메인 별로 Application과 DB가 각각 존재한다.
다만 어러 개의 application이 존재하기 때문에 API Gateway에서 조건에 따라 각 서비스로 요청하여 처리한다.

1. ~~도메인 하나가 전체 서비스에 영향을 미친다.~~
   - 도메인 하나의 장애가 전체로 퍼지지 않는다.
   - 예를 들어, Board에 트래픽이 몰려 장애가 발생해도 Member에 전파되지 않는다.
2. ~~각 도메인의 유지보수가 어렵다.~~
   - 독립적이기 때문에 각각 관리하면 된다.
3. ~~빌드 및 배포 시간이 길어서 생산성이 낮아진다.~~
   - 필요한 도메인만 빌드하면 되기 때문에 생산성이 높아진다.
4. ~~Scale-out 이 어렵다.~~
   - 필요한 도메인만 Scale out이 가능하다.

## 2-1. 도메인 사이의 통신 방식
크게 보면 4 가지이다.
1. RestTemplate
2. WebClient
3. OpenFeign
4. 메시지 브로커

# 3. Service Discovery

Service Discovery 패턴은 서비스의 IP, Port 등을 저장하고 관리하는 주소록의 역할을 한다.
서비스가 추가될 때마다 API Gateway에 서비스의 정보를 수정하고 관리하지 않고 Service Discovery 패턴을 통해 이 불편함을 해소한다.

Service Discovery 패턴을 구현함으로써 서비스를 호출하는 쪽에서는 서비스의 위치를 몰라도
Service Discovery를 구현한 구현체에 질의함으로써 요청을 전달할 수 있다.

이 프로젝트에서 사용할 Netflix Eureka는 Client-Side Discovery이다.

Client-Side Discovery는 클라이언트가 직접 Service Registry에 질의하여 IP와 Port를 받아서 호출하는 방식이다.
어플리케이션 단에서 처리가 가능하고 직접 구현하기 위해 Client-Side Discovery 방식을 사용한다.

> **Server-Side Discovery**
> Service Registry 앞단에 로드밸런서를 두어 로드밸런서가 Service Registry에 호출하는 방식이다.
> 클라이언트는 로드밸런서에 요청만 보내면 된다.

# 4. API Gateway

외부에서 각 마이크로 서비스로 진입하는 진입점으로 API Gateway를 공통되게 사용하여 외부에서는 각 마이크로 서비스를 몰라도 API Gatewy를 통해
라우팅을 할 수 있다.

또한 추가적으로 요청에 대한 공통 관심사(인증, 모니터링)를 API Gateway에서 처리할 수 있다.

- Netflix Zuul: 동기 방식
- Spring Cloud Gateway: Netty 기반의 비동기/Non-blocking 방식 (Spring webFlux)

Spring 측에서 Spring Cloud Gateway만 지원을 유지하기로 했기 때문에 Spring Cloud Gateway를 사용하는 것이 좋다.

### 동작방식
1. 클라이언트가 Spring Cloud Gateway로 요청
2. Gateway Handler Mapping에서 요청 경로를 파악해서 Gateway Web Mapping(Handler)로 요청을 전달
3. 핸들러에서 해당 요청과 관련된 Pre Filter 로직 실행
4. 프록시된 서비스로 라우팅
5. 프록시 서비스가 실행되고 Response를 반환
6. 핸들러에서 Response를 수신하고 Post Filter 로직 실행

