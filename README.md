## SeoulGo
  
  
**프로젝트 기간**  
2016.09 ~ 2016.12  

**소속**  
개인 4인 동아리
  
**담당역할**  
SeoulGo Android App 개발  

**주사용 기술**  
위치 기반 서비스, 카메라 뷰 제작, 애니메이션, Sqlite등 사용
  
  
**프로젝트 내용**  
4인동아리에서 서울시 모바일 플랫폼 공모전을 참가하기 위해 만든 앱입니다.   
위치기반 서비스를 활용한 우리나라 궁궐 체험과 동시에 퍼즐 맞추기 게임을 진행하여 사람들에 참여를 유도하며,  
모든 퍼즐을 완성시에는 쿠폰 발급으로  주변 상권을 살릴 수 있는 서비스  Seoul Go  App 개발에 참여 하였습니다.  
  
**수상**  
**2016 함께서울 앱 공모전 참여(순위 = 30위)**  

## 위치기반서비스  
덕수궁 퍼즐 서비스 예제  
```java
private void DeoksugungLocation(double latitude, double longitude) {
        ArrayList<Integer> insideDeoksugung_List = new ArrayList<>();
        Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[3], null, null, null, null, null, null);  //덕수궁
        try {
            while (cursor.moveToNext()) {
                int number = Integer.parseInt(cursor.getString(1)); // number
                String puzzle_flag = cursor.getString(2);
                if (puzzle_flag.equals("off")) {
                    Log.e(TAG, "위치=" + Contact.Deoksugung[number] +
                            " 거리=" + distance(Contact.Deoksugung_Lat[number], Contact.Deoksugung_Lon[number], latitude, longitude));
                    if (distance(Contact.Deoksugung_Lat[number], Contact.Deoksugung_Lon[number], latitude, longitude) <= 20) {
                        if (!insideDeoksugung_List.contains(number)) { //20m안일떄 퍼즐생성!
                            insideDeoksugung_List.add(number);
                        }
                    } else {
                        if (insideDeoksugung_List.contains(number)) { // 20m 밖일때 퍼즐 제거!
                            insideDeoksugung_List.remove(number);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        if (insideDeoksugung_List.size() > 0) {
            notiFication(insideDeoksugung_List.size(), "덕수궁");
            Intent notiIntent = new Intent(Contact.NOTI_Deoksugung);
            notiIntent.putIntegerArrayListExtra(Contact.NOTI_Deoksugung_LIST, insideDeoksugung_List);
            sendBroadcast(notiIntent);
        } else {
            Intent notiIntent = new Intent(Contact.NOTI_Deoksugung_LIST_OUTSIDE);
            sendBroadcast(notiIntent);
        }
    }
```
  
## 이미지  
 
<div>
  <img src="https://user-images.githubusercontent.com/23161645/72856713-c0aacc00-3cfe-11ea-8c94-8fae08268526.png" hspace=8>
</div>
<div>
  <img src="https://user-images.githubusercontent.com/23161645/72856750-d6b88c80-3cfe-11ea-9d51-60f6642fa939.png" hspace=8>
</div>
<div>
  <img src="https://user-images.githubusercontent.com/23161645/72856753-d7e9b980-3cfe-11ea-80b2-b423237d72b8.png" hspace=8>
</div>

