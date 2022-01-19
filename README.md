# VIDOK11AndroidBasic
Videos
- Flash Screen: https://www.youtube.com/watch?v=JLIFqqnSNmg
- Custom List View: https://youtu.be/QgQ2hMKnesU
- Change Activity: https://youtu.be/hlg_fZYjiDE
- Add New Item to Custom List View: https://youtu.be/ASN2WN-jCMg
- Test: https://youtu.be/LJ2wOGWFk3E https://youtu.be/u_xp-ggygWM
- Edit, Delete Item in Custom List View: https://youtu.be/ZSytGXDVxxI
- Create Fragment: https://youtu.be/J3RHy4Qk7vY
- Add Custom List View to Fragment: https://youtu.be/-MSxb95P1og
- SQL Lite: https://youtu.be/H-1dOnjnleI
- Project: https://youtu.be/_WWwuNEsESY https://youtu.be/c882U0Ay3O0 https://youtu.be/oLxELZ2t6T0
- Call GET API: https://youtu.be/C3n4BBhMXY8
- Call POST API: https://youtu.be/vxo4UBJdG_4
- Call View POST API: https://youtu.be/dDo9c5x2JpI
- semi-fianl api description: https://docs.google.com/document/d/14wPFQvJ9KWQsa8OaA0hufDme8jxdvT-vls8Y5EMMcoI/edit?usp=sharing
- Google Sign In: https://youtu.be/TEo1hvi8WQw
- Using FireBase: https://youtu.be/LLRP7y0Paf4
- FireBase database config:
  Web client id : 400121687859-c2dkv7ikb52fd17tpri4pcv58p62bi76.apps.googleusercontent.com
 FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:400121687859:android:f09d3d60c07b9989")
                .setApiKey("AIzaSyAyrdsxZIsyVyoJ5_dlqWNxFY1Wi7HnYDg")
                .setDatabaseUrl("https://simplechatforumapplication.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(this /* Context */, options);
- Chat: https://youtu.be/uKMtyrWyZhI
- Call FireBase API: https://youtu.be/D5MsNpoPCFc
-Đề tài cuối kì:
  - Hình thức : thực hiện ứng dụng tại nhà
  - Nội dung :Ứng dụng sử dụng đăng nhập bằng tài khoản Google và tạo cơ sở dữ liệu FireBase. Sau khi đăng nhập thành công, gọi đến Api để nhận được dữ liệu trong chính FireBase của mình(khi gọi api sẽ có những tham số yêu cầu thông tin FireBase của người dùng). Sau khi FireBase nhận được dữ liệu, load thông tin dữ liệu lên, thêm sữa xóa dữ liệu và lưu trên FireBase
  - Yêu cầu:
    + Tạo được cơ sở dữ liệu FireBase
    + Sử dụng được Google+ API để đăng nhập
    + Gọi Api để đưa dữ liệu lên FireBase của cá nhân
    + Load được dữ liệu từ FireBase lên list dữ liệu(List sữ dụng RecyclerView)
    + Thao tác thêm sữa xóa và lưu lại trên FireBase
    + Tạo nút sync lại dữ liệu
    + Khi sync dữ liệu ban đầu quá lâu, cần có 1 dialog thể hiện ứng dụng đang thực thi quá trình
* ReferenceRoot name: AdvancedAndroidFinalTest
