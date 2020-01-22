package com.funnytoday.seoul.seoulgo.util;

import com.funnytoday.seoul.seoulgo.R;

/**
 * Created by YunTaeSik on 2016-08-23.
 */
public class Contact {
    public static String COUPON = "COUPON";
    public static String COLLECTION_FINISH = "COLLECTION_FINISH";
    public static String DELETE_COUPON = "DELETE_COUPON";
    public static String LANGUAGE_CHANGE = "LANGUAGE_CHANGE";
    public static String COLLECTION_GO = "COLLECTION_GO";
    public static String SHARED_CLICK = "SHARED_CLICK";


    public static String NOTI_SINHAN = "NOTI_SINHAN";
    public static String NOTI_Gyeongbokgung = "NOTI_Gyeongbokgung";
    public static String NOTI_Changgyeonggung = "NOTI_Changgyeonggung";
    public static String NOTI_Deoksugung = "NOTI_Deoksugung";
    public static String NOTI_Changdeokgung = "NOTI_Changdeokgung";

    public static String NOTI_SINHAN_LIST = "NOTI_SINHAN_LIST";
    public static String NOTI_Gyeongbokgung_LIST = "NOTI_NOTI_Gyeongbokgung_LIST";
    public static String NOTI_Changgyeonggung_LIST = "NOTI_Changgyeonggung_LIST";
    public static String NOTI_Deoksugung_LIST = "NOTI_Deoksugung_LIST";
    public static String NOTI_Changdeokgung_LIST = "NOTI_Changdeokgung_LIST";

    public static String NOTI_OUTSIDE = "NOTI_OUTSIDE";
    public static String NOTI_Gyeongbokgung_LIST_OUTSIDE = "NOTI_Gyeongbokgung_LIST_OUTSIDE";
    public static String NOTI_Changgyeonggung_LIST_OUTSIDE = "NOTI_Changgyeonggung_LIST_OUTSIDE";
    public static String NOTI_Deoksugung_LIST_OUTSIDE = "NOTI_Deoksugung_LIST_OUTSIDE";
    public static String NOTI_Changdeokgung_LIST_OUTSIDE = "NOTI_Changdeokgung_LIST_OUTSIDE";

    public static String Gyeongbokgung_Count = "Gyeongbokgung_Count";
    public static String Changgyeonggung_Count = "Changgyeonggung_Count";
    public static String Deoksugung_Count = "Deoksugung_Count";
    public static String Changdeokgung_Count = "Changdeokgung_Count";

    public static String[] AllianceColor = {"#EF404A", "#F2728C", "#FFD400", "#80B463", "#27AAE1"};
    public static String[] AllianceText = {"StarBucks", "EDIYA", "StarBucks", "EDIYA", "StarBucks"};

    public static int[] PUZZLE_ITEM_IMAGE = {R.drawable.puzzle_item_image_one, R.drawable.puzzle_item_image_two, R.drawable.puzzle_item_image_three};
    public static int[] PUZZLEVIEW_LIST = {R.id.one_puzzle, R.id.two_puzzle, R.id.three_puzzle, R.id.four_puzzle, R.id.five_puzzle, R.id.six_puzzle};

    public static String[] USER_TABLE_NAME = {"신한대", "경복궁", "창경궁", "덕수궁", "창덕궁", "경희궁"};
    public static String[] SinHan = {"도서관", "도봉관", "본관", "호림관", "에셀관",
            "도서관", "도봉관", "본관", "호림관", "강의동"
            , "도서관", "도봉관", "본관", "강의동", "에셀관"};
    public static double[] SinHan_Lat = {37.710515, 37.710145, 37.709404, 37.709234, 37.709406,
            37.710515, 37.710145, 37.709404, 37.709234, 37.710447
            , 37.710515, 37.710145, 37.709404, 37.710447, 37.709406};
    public static double[] SinHan_Lon = {127.045110, 127.044798, 127.044663, 127.045539, 127.046648,
            127.045110, 127.044798, 127.044663, 127.045539, 127.045756
            , 127.045110, 127.044798, 127.044663, 127.045756, 127.046648};

    public static String[] Gyeongbokgung = {"매표소", "긍정문", "근정전", "수정전", "경희루",
            "사정전", "자선당", "내소주방", "강녕전", "교태전",
            "자경전", "향원전", "장안당", "집옥제", "태원전"}; //15개  //완료
    //자선당하고 내소주방이 같음 수정 필요
    public static double[] Gyeongbokgung_Lat = {37.576756, 37.577625, 37.578371, 37.578792, 37.579229,
            37.578974, 37.578936, 37.579399, 37.579416, 37.579807,
            37.580160, 37.581942, 37.582989, 37.583338, 37.582549};
    public static double[] Gyeongbokgung_Lon = {126.977584, 126.976930, 126.976946, 126.975970, 126.975427,
            126.977004, 126.977959, 126.977895, 126.977020, 126.977063,
            126.978088, 126.977092, 126.976741, 126.976052, 126.974234};

    public static String[] Changgyeonggung = {"홍화문", "명정전", "문정전", "숭문당", "통명전"
            , "경춘전", "환경전", "집복헌", "춘당지", "대온실"
            , "월근문", "옥천교", "함푸양문", "풍기대", "양화당"}; //15개
    //완료
    public static double[] Changgyeonggung_Lat = {37.578818, 37.578760, 37.578431, 37.578596, 37.579564,
            37.579160, 37.579160, 37.579635, 37.581288, 37.582816,
            37.581599, 37.578786, 37.579480, 37.579991, 37.579613};
    public static double[] Changgyeonggung_Lon = {126.996415, 126.994980, 126.994814, 126.994480, 126.993661,
            126.994029, 126.994029, 126.994634, 126.995166, 126.994044,
            126.996273, 126.996056, 126.993087, 126.994255, 126.994055};

    public static String[] Deoksugung = {"대한문", "중화문", "즉조당", "할녕전", "덕흥전",
            "정광헌", "석조전", "석어당", "준명당", "중화전"
            , "융안문", "월곡문", "광명문", "석조전 사관", "분수대"}; //15개  //완료
    //자선당하고 내소주방이 같음 수정 필요
    public static double[] Deoksugung_Lat = {37.565021, 37.565112, 37.566308, 37.565733, 37.565851,
            37.566357, 37.566166, 37.566033, 37.566411, 37.565655,
            37.565665, 37.565340, 37.565168, 37.565859, 37.565950};
    public static double[] Deoksugung_Lon = {126.976375, 126.974629, 126.975075, 126.975881, 126.975459,
            126.975633, 126.974232, 126.975131, 126.974759, 126.974777,
            126.975534, 126.973786, 126.974102, 126.973872, 126.974225};


    public static String[] Changdeokgung = {"돈화문", "구 선원전", "인정전", "선정전", "희정전"
            , "성정각", "낙선재", "궐내각사", "대조전", "진선문"
            , "인정문", "수강재", "숙장문", "후원 매표소", "선정문"}; //15개
    public static double[] Changdeokgung_Lat = {37.577888, 37.579515, 37.579272, 37.579569, 37.579614,
            37.579282, 37.578656, 37.579143, 37.580057, 37.578460,
            37.578725, 37.578457, 37.578682, 37.579401, 37.579361};
    public static double[] Changdeokgung_Lon = {126.989892, 126.990121, 126.991084, 126.991699, 126.992226,
            126.992622, 126.993424, 126.989784, 126.992354, 126.990352,
            126.991166, 126.993859, 126.991527, 126.992980, 126.991735};
    public static double[] Changgyeonggung_Alliance_Lat = {37.584932};
    public static double[] Changgyeonggung_Alliance_Lon = {127.010037};

    public static double[] Gyeongbokgung_Alliance_Lat = {37.576366, 37.577757, 37.576470, 37.579657};
    public static double[] Gyeongbokgung_Alliance_Lon = {126.971794, 126.971299, 126.971994, 126.971123};

    public static double[] Deoksugung_Alliance_Lat = {37.564797, 37.564806, 37.564797};
    public static double[] Deoksugung_Alliance_Lon = {126.976299, 126.976207, 126.976299};

    public static int[] Gyeongbokgung_PhotoZone = {R.drawable.photo_zone1, R.drawable.photo_zone2,
            R.drawable.photo_zone3, R.drawable.photo_zone4, R.drawable.photo_zone5};
    public static int[] Changgyeonggung_PhotoZone = {R.drawable.cg_photo_zone1, R.drawable.cg_photo_zone2,
            R.drawable.cg_photo_zone3, R.drawable.cg_photo_zone4, R.drawable.cg_photo_zone5};

    public static int[] Deoksugung_PhotoZone = {R.drawable.deug_photo_zone1, R.drawable.deug_photo_zone2,
            R.drawable.deug_photo_zone3, R.drawable.deug_photo_zone4, R.drawable.deug_photo_zone5};
    public static int[] Changdeokgung_PhotoZone = {R.drawable.cd_photo_zone1, R.drawable.cd_photo_zone2,
            R.drawable.cd_photo_zone3, R.drawable.cd_photo_zone4, R.drawable.cd_photo_zone5};

}
