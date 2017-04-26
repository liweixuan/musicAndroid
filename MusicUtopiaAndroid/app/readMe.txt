 compile 'com.android.support:appcompat-v7:25.1.0'
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:design:25.1.0'
    compile 'com.ashokvarma.android:bottom-navigation-bar:1.3.0'
    compile 'com.jakewharton:butterknife:8.5.1'
    apt 'com.jakewharton:butterknife-compiler:8.5.1'
    compile 'cn.bingoogolapple:bga-refreshlayout:1.1.7'
    compile 'cn.pedant.sweetalert:library:1.3'
    compile 'com.android.support:cardview-v7:25.1.0'
    compile 'com.zhy:okhttputils:2.6.2'
    compile 'com.github.CymChad:BaseRecyclerViewAdapterHelper:v1.9.7'
    compile 'jp.wasabeef:glide-transformations:2.0.2'



  BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(mApp, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.drawable.ic_book_white_24dp);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.blue_btn_bg_color);
        moocStyleRefreshViewHolder.setLoadingMoreText("你说sfd");
        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.colorPrimary);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
        mContain.setLayoutManager(new GridLayoutManager(mApp, 2, GridLayoutManager.VERTICAL, false));
//        mDataRv.setLayoutManager(new LinearLayoutManager(mApp, LinearLayoutManager.VERTICAL, false));
//        mContain.setAdapter(mAdapter1);
        mAdapter1.setDuration(1500);
        mAdapter1.setEmptyView(true, true, View.inflate(getContext(), R.layout.text, null));
        mAdapter1.openLoadMore(true);
        mAdapter1.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mAdapter1.addHeaderView(View.inflate(getContext(), R.layout.text1, null));
        mAdapter1.addHeaderView(View.inflate(getContext(), R.layout.text3, null));
        mAdapter1.addFooterView(View.inflate(getContext(), R.layout.text3, null));
        mAdapter1.addFooterView(View.inflate(getContext(), R.layout.text, null));
        mAdapter1.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ToastUtil.show("1111111111111111");
            }
        });



mAdapter1.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ToastUtil.show(":::" + i);
            }
        });
        OnItemDragListener onItemDragListener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            }
        };

        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float v, float v1, boolean b) {

            }
        };
        mAdapter2 = new Dra(getDates());
        mContain.setAdapter(mAdapter2);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter2);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mContain);
        // 开启拖拽
        mAdapter2.enableDragItem(itemTouchHelper, R.id.tv1, true);
        mAdapter2.setOnItemDragListener(onItemDragListener);


// 开启滑动删除
//        mAdapter2.enableSwipeItem();
//        mAdapter2.setOnItemSwipeListener(onItemSwipeListener);
    }


    List<RefreshModel> getDates() {
        //GET请求
        OkHttpUtils.get().url("")
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
        //POST请求
        OkHttpUtils
                .post()
                .url("")
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
        //Post JSON 提交一个Gson字符串到服务器端，
        // 注意：传递JSON的时候，不要通过addHeader去设置contentType，
        // 而使用.mediaType(MediaType.parse("application/json; charset=utf-8")).。
        OkHttpUtils.postString()
                .url("")
                .content("")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(null);
        //Post File
        //将文件作为请求体，发送到服务器
        OkHttpUtils.postFile()
                .url("")
                .file(new File(""))
                .build()
                .execute(null);
        //Post表单形式上传文件
        //支持单个多个文件，addFile的第一个参数为文件的key，
        // 即类别表单中<input type="file" name="mFile"/>的name属性。
        OkHttpUtils.post()
                .addFile("mFile", "messenger_01.png", null)//
                .addFile("mFile", "test1.txt", null)//
                .url("")
                .addParams("", "")
                .build()
                .execute(null);
        //自定义CallBack

        //下载文件
        //注意下载文件可以使用FileCallback，需要传入文件需要保存的文件夹以及文件名。
        OkHttpUtils//
                .get()//
                .url("")//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "gson-2.2.1.jar")//
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(File response, int id) {

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                    }
                });

        //显示图片
        //显示图片，回调传入BitmapCallback即可。
        OkHttpUtils
                .get()//
                .url("")//
                .build()//
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {

                    }
                });
        //上传下载的进度显示
        /*new Callback<T>()
        {
            //...
            @Override
            public void inProgress(float progress)
            {
                //use progress: 0 ~ 1
            }
        }*/

        //取消单个请求
        RequestCall call = OkHttpUtils.get().url("").build();
        call.cancel();


        /*
        * 目前对于支持的方法都添加了最后一个参数Object tag，
        * 取消则通过OkHttpUtils.cancelTag(tag)执行。
        *  * OkHttpUtils
        .get()//
        .url(url)//
        .tag(this)//
     .build()//

        @   Override
        protected void onDestroy()
        {
         super.onDestroy();
     //可以取消同一个tag的
     OkHttpUtils.cancelTag(this);//取消以Activity.this作为tag的请求

    混淆
     #okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}


#okio
-dontwarn okio.**
-keep class okio.**{*;}

https://github.com/hongyangAndroid/okhttputils#上传下载的进度显示

}*/