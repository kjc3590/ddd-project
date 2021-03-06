# ddd-project

## 프로젝트 주제
헌터들이 힌트를 통해 보물을 찾는 이야기

## 요구사항

에그리거트: 헌터, 보물(미션)

* 헌터
- [x] 헌터는 미션을 도전하여 포인트 획득량과 힌트를 획득할 수 있다.
- [x] 미션을 도전하면 총알이 소모된다.
- [x] 헌터는 포인트가 100점이 되면 총알을 구매할 수 있다. 
- [x] 헌터는 지도를 조회하여 보물이 포함된 좌표들을 볼 수 있다. 
- [x] 헌터는 보물이 위치한 좌표에서 QR 코드를 찍고 보물을 획득한다.

* 보물
- [x] '러닝 타임'은 보물을 찾을 수 있는 유효 기간을 뜻한다.
- [x] '보물의 상태'는 대기,진행,완료로 이루어져있다.
   -  대기 = '러닝 타임' 에서 설정된 시작일 이전인 상태
   -  진행 = 헌터가 보물을 찾고 있는 상태
   -  완료 = '러닝 타임' 에서 설정된 종료일이 지났거나 헌터가 보물의 QR코드를 찍은 상태
- [x] 러닝타임이 끝나기 전에 ‘QR 코드' 를 찍으면 보물의 상태가 완료로 변경된다.
   -  ‘QR 코드’는 해당 보물의 비밀번호를 가지고 있다. 

* 미션
- [x]  미션을 '러닝타임' 안에 풀면 획득량과 힌트를 제공한다.
- [x]  '미션난이도'는 브론즈, 실버, 골드로 이루어져 있다. 
   -   이전 난이도의 문제를 모두 풀어야 다음 난이도의 문제로 넘어갈 수 있다. (브론즈 -> 실버-> 골드 순)
   -   '미션난이도'에 따라 획득량과 힌트카운트가 차이가 난다.

* 힌트
- [x] 미션을 성공하면 힌트를 획득해 보물의 가짜지점을 제거할 수 있다.



### 용어사전(유비쿼터스 언어)

| 한글명 | 영문명 | 설명 |
| ---------- | :--------- | :---------- |
| 보물 | treasure | 헌터 미션를 풀면 얻게 되는 보상 |
| 러닝타임 | runningTIme | 보물을 찾을 수 있는 유효기간. |
| 러닝타임의 시작일자  | startDate | 유효기간의 시작일자 |
| 러닝타임의 종료일자  | endDate | 유효기간의 종료일자 |
| 미션의 개수 | missionCount | 보물이 가지고 있는 미션의 개수를 뜻한다. |
| 보물의 가짜 지점 | fakePoint | 보물의 가짜 위치 |
| 좌표 | targetPoint | 보물의 위치를 뜻 한다. | 
| QR 코드 경로 | qrUrl | Qr코드를 통해서 접속하는 주소 |
| 좌표의 진짜 유무 | distinguish | 좌표가 진짜 보물의 위치인지 아닌지 판별해주는 역활을 한다. |
| 좌표 | Coordinates | 위도와 경도를 가진다. |
| 위도 | latitude | 좌표의 위도를 뜻한다. |
| 경도 | hardness | 좌표의 경도를 뜻한다. |
| 미션 | mission | 헌터가 힌트를 보기 위해 풀어야하는 과제 |
| 미션 난이도 | missionLevel | 해당 미션의 난이도 (종류는 쉬운순서대로 브론즈, 실버, 골드) 이있다. |
| 미션 포인트 | missionPoint | 미션을 성공하면 미션포인트를 얻는다. |
| 미션 포인트범위 | missionPointRange | 미션포인트의 범위 (미션 난이도의 따라 다름) |
| 힌트 | hint | 보물 위치를 알려주는 아이템 |
| 힌트카운터 | hintCounter | 몇 개의 힌트를 줄지 알려준다 |
| ~의 아이디 | ~Id | 해당 도메인의 식별키 이름을 뜻한다. |
| ~의 이름 | ~Name | 해당 도메인의 이름을 뜻한다. |
| ~의 비밀번호 | ~Pw | 해당 도메인의 비밀번호를 뜻한다. |
| ~의 상태 | ~Status | 해당 도메인의 상태를 뜻한다. |


### **모델링**

헌터

* 헌터는 아이디, 비밀번호, 이름, 사진, 포인트, 총알을 가진다.
* 헌터는 미션을 도전하여 획득량과 힌트를 획득할 수 있다.
* 미션을 도전하면 총알이 소모된다.
   -  총알의 기본, 최대 개수는 3개이며 리필 시간은 30분이다. (최대개수보다 작을때 리필이 적용된다.)
* 헌터는 포인트가 100점이 되면 총알을 구매할 수 있다. 
* 헌터는 지도를 조회하여 보물이 포함된 좌표들을 볼 수 있다. 
   -  힌트의 효과로 제거된 좌표를 제외한 좌표들을 보여준다. 
* 헌터는 보물이 위치한 좌표에서 QR 코드를 찍고 비밀번호를 알아내 보물을 획득한다.

보물
* 보물은 이름, 상태, 러닝타임, 좌표, QR 코드 경로, 미션을 가지며 관리자가 생성한다.
* '러닝 타임'은 보물을 찾을 수 있는 유효 기간을 뜻한다.
   -   '러닝 타임'은 시작일자, 종료일자로 이루어져있다.
   -   '러닝 타임'의 시작일은 보물의 생성 시간과 같거나 느려야 한다.	
* ‘보물의 상태’는 대기,진행,완료로 이루어져있다.
   -  대기 = '러닝 타임' 에서 설정된 시작일 이전인 상태
   -  진행 = 헌터가 보물을 찾고 있는 상태
   -  완료 = '러닝 타임' 에서 설정된 종료일이 지났거나 헌터가 보물의 QR코드를 찍은 상태
* ‘좌표’는 보물이 있는 위치를 나타낸다 
   -  위도, 경도,보물이 있는 좌표인지 판별하는 값을 가진다.
* ‘QR 코드’는 보물이 있는 좌표에 있으며 암호화된 경로, 해당 보물의 비밀번호를 가지고 있다.
   -  러닝타임이 끝나기 전에 ‘QR 코드' 를 찍으면 보물의 비밀번호를 알 수 있고 보물의 상태가 완료로 변경된다. 

미션

* 미션은 난이도, 획득량, 힌트카운터를 가진다. 
* 미션을 '러닝타임'안에 풀면 '미션난이도'에 따른 획득량과 힌트를 제공한다.
   -   미션난이도 * (randomInt(9) + 1)) 의 획득량을 제공한다. (브론즈:1 , 실버:2 , 골드:4) 
   -   힌트카운터를 제공한다.   (브론즈:2 , 실버:4 , 골드:8) 
* '미션난이도'는 브론즈, 실버, 골드로 이루어져 있다. 
   -   이전 난이도의 문제를 모두 풀어야 다음 난이도의 문제로 넘어갈 수 있다. (브론즈 -> 실버-> 골드 순)
   -   분포식에따라 생성된다. (미션별 분포량 = (총 미션개수  / 미션 난이도의 총합)) 

힌트

* 미션을 성공하면 미션난이도에 따른 획득량과 힌트를 획득하여 보물의 가짜지점을 제거할 수 있다.
* '힌트카운터'는 보물의 가짜 좌표를 지우는 힌트의 개수를 의미한다.
