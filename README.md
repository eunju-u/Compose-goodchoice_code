

# 여기어때 클론 앱

# 사용 기술

* Kotlin
* Compose
* Coroutine
* Flow (StateFlow)
* DI (Hilt)
* Clean Architecture
* MVVM
* RoomDB
* Naver Map
* 모듈화
  
# UI

1. 해상도
  * 해상도에 따라 위젯의 크기 또는 Text 의 크기가 지정된 비율로 변경됨.

   폴드폰 전체 화면    |       폴드폰 부분 화면        |
:-------------------------:|:-------------------------:
![](https://github.com/eunju-u/dddddd/assets/55544506/4c7ce3c7-9037-4889-8ee1-7891466b2af6)  |  ![](https://github.com/eunju-u/dddddd/assets/55544506/7c5a9238-1634-406d-91e3-58afb3f230d7)

--------
 
2. 캘린더
  * 현재 달부터 +5달의 캘린더 보여줌.
  * 날짜 선택 가능
  * 국내 캘린더, 해외 캘린더에 따라 노출되는 위젯이 다름

   국내 캘린더    |       해외 캘린더        |
:-------------------------:|:-------------------------:
![](https://github.com/eunju-u/dddddd/assets/55544506/f32013a5-2310-484e-8e66-070f8103cded)  |  ![](https://github.com/eunju-u/dddddd/assets/55544506/9d87df38-d9ad-49dc-8797-358072770227)

  > 캘린더 영상

  https://github.com/eunju-u/dddddd/assets/55544506/ef6b1359-f204-43e0-89d0-ff38acbcd68a

--------
 
3. Refresh UI, StickyHeader UI
  * 위로 끌어올리는 제스처 할 경우 Refresh UI 노출
  * 아래로 스크롤 시 StickyHeader UI 노출

  > Refresh UI, StickyHeader UI 영상

  https://github.com/eunju-u/dddddd/assets/55544506/03edd643-4c52-40a8-982c-1c27e3c67871

--------

4 Naver 지도
  * Naver 지도를 사용해 현재 위치 표시함.
  * 현재 위치에서 다른 위치로 지도 이동할 경우 현재 위치를 표시하는 버튼의 색상이 달라짐
  * 현재 위치 표시하는 버튼 클릭시 현재 위치로 이동함

  > Naver 지도 영상

  https://github.com/user-attachments/assets/1ac79d90-7cc2-4ee5-8edc-ea45c3e1fde3

--------

5. 필터
  * FlowRow 을 사용한 UI 구현
  * 주변 화면 필터에서 입력한 데이터 필터 상세 화면에 적용하도록 함.
  * 필터 상세 화면에서 입력한 데이터 주변 화면 필터에 적용하도록 함.

  > 필터 영상

  https://github.com/user-attachments/assets/527b4827-1be6-47ed-b296-37eb66966509
  
--------

6. 검색
  * 검색창 클릭시 키보드 노출
  * 검색창 클릭시 X 버튼 노출
  * 두 글자 미만으로 입력 후 결과 출력시 팝업 노출

  > 검색 영상

  https://github.com/user-attachments/assets/3e9daed5-0879-434b-a888-5514cb95ebac

--------

7. 상세화면
  * 찜 클릭 가능
  * BottomSheet 

  > 상세화면 영상
  
  https://github.com/eunju-u/dddddd/assets/55544506/f8abd86f-7a51-475e-82fa-92ab561a1871


--------

8. 로그인
  * 로그인시 이전 로그인한 소셜 인지하게 함

  > 로그인 영상
  
  https://github.com/eunju-u/dddddd/assets/55544506/2fc14d44-9c41-43b8-b088-ea8a42c90d01


--------

9. 최근 본 상품
   * 최근 본 상품이 홈 화면에 갱신되어 보여짐
   * 내 정보 화면의 최근 본 상품 상세 화면 진입시 삭제 가능

  > 최근 본 상품 영상
  
  https://github.com/eunju-u/Compose-goodchoice/assets/55544506/e730e37c-9a8e-4082-8738-d000d3ad7e45


--------

10. 찜 화면
   * 숙소 상세 화면에서 찜 할 수 있음
   * 로그인 된 상태에서만 찜 가능하며 비로그인 상태에서 찜할 경우 팝업 노출
   * 찜 화면에서 찜한 숙소 확인 가능 하며 찜 추가, 제거 가능
   * 찜 화면에서 하트 제거시 바로 삭제되지 않고 다른 네비게이션 진입 후 찜 화면 재 진입시 화면 갱신됨.

  > 찜 영상
  
  https://github.com/eunju-u/Compose-goodchoice/assets/55544506/9afdb4ef-799a-4687-9191-49b137c1204b


--------

# 구글 시트

https://docs.google.com/spreadsheets/d/1-4ys1pJDsMoKuG2PnsehHL54S2m13-EJOe7aYUgtiWk/edit#gid=316716901
