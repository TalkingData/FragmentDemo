# FragmentDemo


### What

FragmentDemo 演示了如何在 Android 工程中集成 [TalkingData Analytics SDK](https://www.talkingdata.com/app/document_web/index.jsp?statistics) 的接口功能。

### How

1. 访问 [TalkingData 官网](https://www.talkingdata.com/) 注册帐号并按照提示申请 `AppId`。
2. 访问 [SDK 定制网页](https://www.talkingdata.com/spa/sdk/#/config?productLine=AppAnalytics&sdkPlatform=Android),申请SDK jar包，导入到工程目录的libs下。
3. 在 `AndroidManifest.xml` 中添加下面的基本权限：

<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE"  />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"  />
<uses-permission android:name="android.permission.GET_TASKS"  />

4. 在 Android 工程 `Application` 类的 `onCreate` 方法中执行下面的代码初始化 SDK：

TCAgent.LOG_ON = true;
TCAgent.init(this, "[Your AppId]", "[Your ChannelId]");
TCAgent.setReportUncaughtExceptions(true);

另外，也可以像 Demo 中演示的那样将 `AppId` 和 `ChannelId` 以 `MetaData` 的形式在 `AndroidManifest.xml` 文件中声明，

<meta-data android:name="TD_APP_ID" android:value="Your_app_id" />
<meta-data android:name="TD_CHANNEL_ID" android:value="Your_channel_id" />

然后可以在初始化时这样调用：

TCAgent.init(this);

5. `MainActivity` 类中演示了如何进行 Activity 生命周期的跟踪和自定义事件的记录。
6. `FMActivity` 类中演示了如何对 Fragment 生命周期进行跟踪。

更详细的 SDK 接口解释和集成文档请参考 [App Analytics SDK 集成文档](https://www.talkingdata.com/app/document_web/index.jsp?statistics)。
