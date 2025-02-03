

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
* Multi Module
  
# UI

1. 해상도
  * 해상도에 따라 위젯의 크기 또는 Text 의 크기가 지정된 비율로 변경됨.

   폴드폰 전체 화면    |       폴드폰 부분 화면        |
:-------------------------:|:-------------------------:
![image](https://github.com/user-attachments/assets/2bf0dbac-937b-40d0-a082-3428ff169a68)  |  ![image](https://github.com/user-attachments/assets/db592783-e5a0-45cb-9d62-1b37ccbffd76)

--------
 
2. 캘린더
  * 현재 달부터 +5달의 캘린더 보여줌.
  * 날짜 선택 가능
  * 국내 캘린더, 해외 캘린더에 따라 노출되는 위젯이 다름

   국내 캘린더    |       해외 캘린더        |
:-------------------------:|:-------------------------:
![image](https://github.com/user-attachments/assets/db442dc3-0188-4d07-b7b9-69aeb44dea97)  |  ![image](https://github.com/user-attachments/assets/a8107299-9963-4dcd-92e0-0c00abdf9e27)

  > 캘린더 영상

  https://github.com/user-attachments/assets/49ad7342-164c-499f-b5e5-69bf576fb057

--------
 
3. StickyHeader UI
  * 아래로 스크롤 시 StickyHeader UI 노출

  > StickyHeader UI 영상

  https://github.com/user-attachments/assets/e9b7f7c4-2cd1-484f-9b22-0309af0fed47

--------

4 Naver 지도
  * Naver 지도를 사용해 현재 위치 표시함.
  * 현재 위치에서 다른 위치로 지도 이동할 경우 현재 위치를 표시하는 버튼의 색상이 달라짐
  * 현재 위치 표시하는 버튼 클릭시 현재 위치로 이동함

  > Naver 지도 영상

  https://github.com/user-attachments/assets/28ef1c5d-2369-46d3-8462-059908e12cbe

--------

5. 필터
  * FlowRow 을 사용한 UI 구현
  * 주변 화면 필터에서 입력한 데이터 필터 상세 화면에 적용하도록 함.
  * 필터 상세 화면에서 입력한 데이터 주변 화면 필터에 적용하도록 함.

  > 필터 영상

  https://github.com/user-attachments/assets/56087f5d-2142-47bb-8a75-108b23945f83
  
--------

6. 검색
  * 검색창 클릭시 키보드 노출
  * 검색창 클릭시 X 버튼 노출
  * 두 글자 미만으로 입력 후 결과 출력시 팝업 노출

  > 검색 영상

  https://github.com/user-attachments/assets/1174c501-b487-4314-924f-947bc74f0861

--------

7. 상세화면
  * 찜 클릭 가능
  * BottomSheet 

  > 상세화면 영상
  
  https://github.com/user-attachments/assets/31323283-a2ca-4b79-b6a5-102bb2f0397b

--------

8. 로그인
  * 로그인시 이전 로그인한 소셜 인지하게 함

  > 로그인 영상
  
  https://github.com/user-attachments/assets/1ae86668-133d-49d8-abbe-cace1b087c06

--------

9. 최근 본 상품
   * 최근 본 상품이 홈 화면에 갱신되어 보여짐
   * 내 정보 화면의 최근 본 상품 상세 화면 진입시 삭제 가능

  > 최근 본 상품 영상
  
  https://github.com/user-attachments/assets/d7c10fdb-8f7f-48c2-94ae-14add9819aa7

--------

10. 찜 화면
   * 숙소 상세 화면에서 찜 할 수 있음
   * 로그인 된 상태에서만 찜 가능하며 비로그인 상태에서 찜할 경우 팝업 노출
   * 찜 화면에서 찜한 숙소 확인 가능 하며 찜 추가, 제거 가능
   * 찜 화면에서 하트 제거시 바로 삭제되지 않고 다른 네비게이션 진입 후 찜 화면 재 진입시 화면 갱신됨.

  > 찜 영상
  
  https://github.com/user-attachments/assets/efbbfce4-7160-4a71-bc46-7a5dd1acab7d

--------

# 구글 시트

https://docs.google.com/spreadsheets/d/1-4ys1pJDsMoKuG2PnsehHL54S2m13-EJOe7aYUgtiWk/edit#gid=316716901
