package com.example.togodemo.ui;

import net.tsz.afinal.annotation.view.ViewInject;

import com.example.togodemo.HomeFragment;
import com.example.togodemo.R;
import com.example.togodemo.ClassifyFragment;
import com.example.togodemo.BusinessFragment;
import com.example.togodemo.ShopcarFragment;
import com.example.togodemo.myApplication;
import com.example.togodemo.user.User_Login;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


public abstract class abstOnTouchListener extends FragmentActivity implements OnClickListener,OnTouchListener{

	 /** 
     * 滚动显示和隐藏menu时，手指滑动需要达到的速度。 可以自行设定
     */  
    public static final int SNAP_VELOCITY = 200;  
  
    /** 
     * 获取屏幕宽度值。 
     */  
    protected int screenWidth;  
  
    /** 
     * menu最多可以滑动到的左边缘。值由menu布局的宽度来定，
     * marginLeft到达此值之后，不能再减少。 
     */  
    protected int leftEdge;  
  
    /** 
     * menu最多可以滑动到的右边缘。值恒为0，即marginLeft到达0之后，不能增加。 
     */  
    protected int rightEdge = 0;  
  
    /** 
     * menu（隐藏了的菜单）完全显示时，留给content（正面显示的菜单）的宽度值。 可以自行更改
     */  
    protected int menuPadding = 80;  
  
    /** 
     * 主内容的布局。 
     */  
    protected View content;  
  
    /** 
     * menu的布局。 
     */  
    protected View menu;  
  
    /** 
     * menu布局的参数，通过此参数来更改leftMargin（左边界）的值。 
     */  
    protected LinearLayout.LayoutParams menuParams;  
  
    /** 
     * 记录手指按下时的横坐标。 
     */  
    protected float xDown;  
  
    /** 
     * 记录手指移动时的横坐标。 
     */  
    protected float xMove;  
  
    /** 
     * 记录手机抬起时的横坐标。 
     */  
    protected float xUp;  
  
    /** 
     * menu当前是显示还是隐藏。
     * 只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。 
     */  
    protected boolean isMenuVisible;  
  
    /** 
     * 用于计算手指滑动的速度。 
     */ 
    @ViewInject(id=R.id.iv_myhome,click="toMyhome")ImageView iv_myhome;
	//@ViewInject(id=R.id.tv_title)
	//protected TextView tv_title;
    protected VelocityTracker mVelocityTracker;  
    protected Button b1=null,b2=null;
    protected EditText e=null;
    protected TextView right=null;
    protected LinearLayout myLinearLayout=null;
    //定义一个静态常量flag，当flag=0时，单击个人中心图标进入menu界面，
    //进入menu界面后将flag赋值为1,flag=1时，单击个人中心按钮回到content界面；
    protected static int flag=0;
    protected ViewPager pager;
    protected RadioGroup mGroup;
	/**
	 * 用于展示消息的Fragment，已改成首页
	 */
    protected static HomeFragment homeFragment;

    /**
	 * 用于展示联系人的Fragment，已改成分类
	 */
	protected static ClassifyFragment classifyFragment;

	/**
	 * 用于展示动态的Fragment，已改成活动
	 */
	protected static BusinessFragment businessFragment;

	/**
	 * 用于展示设置的Fragment，已改成购物车
	 */
	protected static ShopcarFragment shopcarFragment;

	/**
	 * 消息界面布局，已改成首页
	 */
	protected View homeLayout;

	/**
	 * 联系人界面布局，已改成分类
	 */
	protected View classifyLayout;

	/**
	 * 动态界面布局，已改成活动
	 */
	protected View businessLayout;

	/**
	 * 设置界面布局,已改成购物车
	 */
	protected View shopcarLayout;

	/**
	 * 在Tab布局上显示消息图标的控件
	 */
	protected static ImageView messageImage;

	/**
	 * 在Tab布局上显示联系人图标的控件
	 */
	protected static ImageView contactsImage;

	/**
	 * 在Tab布局上显示动态图标的控件
	 */
	protected static ImageView newsImage;

	/**
	 * 在Tab布局上显示设置图标的控件
	 */
	protected static ImageView settingImage;

	/**
	 * 在Tab布局上显示消息标题的控件
	 */
	protected static TextView messageText;

	/**
	 * 在Tab布局上显示联系人标题的控件
	 */
	protected static TextView contactsText;

	/**
	 * 在Tab布局上显示动态标题的控件
	 */
	protected static TextView newsText;

	/**
	 * 在Tab布局上显示动态标题的控件
	 */
	protected static TextView settingText;

	/**
	 * 用于对Fragment进行管理
	 */
	protected static FragmentManager fragmentManager;
	protected TextView daifukuan;
	/**
	 * 判断当前处于menu界面还是content界面，0是menu
	 */
	protected int pageID;
	protected boolean wantToClose;
	  /** 
     * 初始化一些关键性数据。
     * 包括获取屏幕的宽度，
     * 给content布局重新设置宽度，
     * 给menu布局重新设置宽度和偏移距离等。 
     */  

    protected void initValues() {  
    	//WindowManager的作用
    			//整个Android的窗口机制是基于一个叫做 WindowManager，这个接口可以添加view到屏幕，
    			//也可以从屏幕删除view。它面向的对象一端是屏幕，另一端就是View
    			//显示窗口
    	        WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE); 
    	        //屏幕宽度值:screenWidth
    	        screenWidth = window.getDefaultDisplay().getWidth();  
    	        //主要的显示界面content，被隐藏了的LinearLayout(menu)
    	        content = findViewById(R.id.content1); 
    	        menu = findViewById(R.id.menu);  
    	        //menu布局的参数，通过此参数来更改leftMargin的值,用法LinearLayout.LayoutParams
    	        //menu.getLayoutParams()获得menu的长宽高信息
    	        menuParams =(LayoutParams) menu.getLayoutParams();  
    	        // 将menu的宽度设置为：屏幕宽度减去menuPadding（menu完全显示时，content所显示的宽度） 
    	        menuParams.width = screenWidth - menuPadding;  
    	        // 左边缘的值赋值为menu宽度的负数  
    	        leftEdge = -menuParams.width;  
    	        // menu的leftMargin设置为左边缘的值，这样初始化时menu就变为不可见  
    	        menuParams.leftMargin = leftEdge;  
    	        // 将content的宽度设置为屏幕宽度  
    	        content.getLayoutParams().width = screenWidth; 
    }  
    @Override 
    /*
     * 触摸屏相关的事件，是通过onTouchListener实现
     * MotionEvent触摸事件
     * Ontouch(View v ,MotionEvent event) 两个参数 v和 e都是系统自动传进来的，
     * 这个v取决于你当前点击的view对象，e代表你当前点事件的各个属性（比如坐标，按下，放开，移动，等等）
     */
    public boolean onTouch(View v, MotionEvent event) { 
    	//调用构造方法createVelocityTracker（）
    	//VelocityTracker主要用跟踪触摸屏事件（flinging事件和其他gestures手势事件）的速率。
        createVelocityTracker(event); 
        
       
       /* ACTION_DOWN: 表示用户开始触摸.

        ACTION_MOVE: 表示用户在移动(手指或者其他)

        ACTION_UP:表示用户抬起了手指 

       ACTION_CANCEL:表示手势被取消了
       
       getAction()获得用户触摸屏幕时的返回值
       */
        myApplication my=(myApplication) this.getApplication();
        if(my.isUSER_LOGIN()){
        switch (event.getAction()) {  
        case MotionEvent.ACTION_DOWN:  
            // 手指按下时，记录按下时的横坐标  
            xDown = event.getRawX();
            Log.i("ACTION_DOWN", "ACTION_DOWN");
            //判断手指点击的区域是否在content部分显示的区域内，若在该区域，则显示content
            if(xDown>screenWidth-menuPadding){
            	//scrollToContent();
            }
            break;  
        case MotionEvent.ACTION_MOVE:  
            // 手指移动时，对比按下时的横坐标，计算出移动的距离，来调整menu的leftMargin值，
        	//从而显示和隐藏menu  
            xMove = event.getRawX();  
            int distanceX = (int) (xMove - xDown);  
            if (isMenuVisible) {  
            	//当menu完全显示时
                menuParams.leftMargin = distanceX;  
            } else {  
            	//当menu未完全显示时
            	//menu左边距值=menu的-宽度+X轴总滑动距离
                menuParams.leftMargin = leftEdge + distanceX;  
            }  
           //若左边距小于左边缘时，让左边距=左边缘值（左边缘的正值就是menu的宽度值），若不设这个值
            //当用户用手继续向左滑动时，content会一直往左边隐藏，右侧则会出现一片空白
            if (menuParams.leftMargin < leftEdge) {  
                menuParams.leftMargin = leftEdge;  
                
            } 
            //若左边距大于0时，让左边距=0，若不设这个值，当用户用手继续向右滑动时，
            //最左边会有一块区域是空白的
            else if (menuParams.leftMargin > rightEdge) {  
                menuParams.leftMargin = rightEdge;  
            }  
            //setLayoutParams这个函数用于用代码自定义布局，为创建的view对象设置位置，大小，颜色等一系列的属性。
            menu.setLayoutParams(menuParams);  
            break;  
        case MotionEvent.ACTION_UP:  
            // 手指抬起时，进行判断当前手势的意图，从而决定是滚动到menu界面，还是滚动到content界面  
            xUp = event.getRawX();  
            if (wantToShowMenu()) {  
                if (shouldScrollToMenu()) {
                	right.setText(R.string.title_myhome);
                	 pageID=0;
                    scrollToMenu(); 
                   
                } else { 
                	right.setText(R.string.title_home);
                    scrollToContent(); 
                   
                }  
            } else if (wantToShowContent()) {  
                if (shouldScrollToContent()) { 
                	right.setText(R.string.title_home);
                    scrollToContent();  
                } else {  
                    scrollToMenu();  
                }  
            }  
            //当完成menu与content的切换时，要对VelocityTracker对象进行回收清零
            recycleVelocityTracker();  
            break;  
        }  
        
        return true; 
        }
		return false;
    }  
  
    /** 
     * 判断当前手势的意图是不是想显示content。
     * 如果手指移动的距离是负数，且当前menu是可见的，则认为当前手势是想要显示content。 
     *  
     * return 当前手势想显示content返回true，否则返回false。 
     */  
    protected boolean wantToShowContent() {  
        return xUp - xDown < -40 && isMenuVisible;  
    }  
  
    /** 
     * 判断当前手势的意图是不是想显示menu。
     * 如果手指移动的距离是正数，且当前menu是不可见的，则认为当前手势是想要显示menu。 
     *  
     * return 当前手势想显示menu返回true，否则返回false。 
     */  
    protected boolean wantToShowMenu() {  
        return xUp - xDown > 0 && !isMenuVisible;  
    }  
  
    /** 
     * 判断是否应该滚动将menu展示出来。
     * 如果手指移动距离大于屏幕的1/2，或者手指移动速度大于SNAP_VELOCITY， 
     * 就认为应该滚动将menu展示出来。 
     *  
     * @return 如果应该滚动将menu展示出来返回true，否则返回false。 
     */  
    protected boolean shouldScrollToMenu() {  
        return xUp - xDown > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;  
    }  
  
    /** 
     * 判断是否应该滚动将content展示出来。如果手指移动距离加上menuPadding大于屏幕的1/2， 
     * 或者手指移动速度大于SNAP_VELOCITY， 就认为应该滚动将content展示出来。 
     *  
     * @return 如果应该滚动将content展示出来返回true，否则返回false。 
     */  
    protected boolean shouldScrollToContent() {  
        return xDown - xUp + menuPadding > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;  
    }  
  
    /** 
     * 将屏幕滚动到menu界面，滚动速度设定为30. 
     */  
    protected void scrollToMenu() {
    	
        new ScrollTask().execute(30);
        flag=1;
    }  
  
    /** 
     * 将屏幕滚动到content界面，滚动速度设定为-30. 
     */  
    protected void scrollToContent() { 
        new ScrollTask().execute(-30);  
        flag=0;
    }  
  
    /** 
     * 创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当中。 
     *  
     * 在198行调用
     *    MotionEvent触摸事件         获得content界面的滑动事件 
     */  
    protected void createVelocityTracker(MotionEvent event) {  
        if (mVelocityTracker == null) {  
        /*185行
         * android.view.VelocityTracker
         * 主要用跟踪触摸屏事件（flinging事件和其他gestures手势事件）的速率。
         * 用obtain()来获得类的实例
         */
            mVelocityTracker = VelocityTracker.obtain();  
        }  
        /*
         * 用addMovement(MotionEvent)函数将触控MotionEvent event加入到VelocityTracker类实例中
         */
        mVelocityTracker.addMovement(event);  
    }  
  
    /** 
     * 获取手指在content界面滑动的速度。 
     *  
     * @return 滑动速度，以每秒钟移动了多少像素值为单位。 
     */  
    protected int getScrollVelocity() {
    	//用getXVelocity() 或getYVelocity()获得横向或竖向的速率到速率时，
    	//但是使用它们之前请先调用computeCurrentVelocity(int)来初始化速率的单位 。
        mVelocityTracker.computeCurrentVelocity(1000);  
        int velocity = (int) mVelocityTracker.getXVelocity();  
        //math.abs返回绝对值(取正数)
        return Math.abs(velocity);  
    }  
  
    /** 
     * 回收VelocityTracker对象。 
     */  
    protected void recycleVelocityTracker() {  
        mVelocityTracker.recycle();  
        mVelocityTracker = null;  
    }  
    /*
     * AsyncTask,是android提供的轻量级的异步类，可以直接继承AsyncTask,在类中实现异步操作
     * AsyncTask定义了三种泛型类型 Params，Progress和Result。
     * Params 启动任务执行的输入参数，比如HTTP请求的URL。
     * Progress 后台任务执行的百分比。
     * Result 后台执行任务最终返回的结果，比如String
     */
    
    class ScrollTask extends AsyncTask<Integer, Integer, Integer> {  
  
        @Override  
        /**  
         * 这里的Integer参数对应AsyncTask中的第一个参数   
         * 这里的Integer返回值对应AsyncTask的第三个参数  
         * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的控件进行设置和修改  
         * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作  
         */  
        protected Integer doInBackground(Integer... speed) {  
            int leftMargin = menuParams.leftMargin;  
            // 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。  
            while (true) {  
                leftMargin = leftMargin + speed[0];  
                if (leftMargin > rightEdge) {  
                    leftMargin = rightEdge;  
                    break;  
                }  
                if (leftMargin < leftEdge) {  
                    leftMargin = leftEdge;  
                    break;  
                }  
                publishProgress(leftMargin);  
                // 为了要有滚动效果产生，每次循环使线程睡眠20毫秒，这样肉眼才能够看到滚动动画。  
                sleep(20);  
            }  
            if (speed[0] > 0) {  
                isMenuVisible = true;  
            } else {  
                isMenuVisible = false;  
            }  
            return leftMargin;  
        }  
      //以下的代码是为了让侧滑效果执行更为流畅 
       @Override  
        /**  
         * 这里的Integer参数对应AsyncTask中的第二个参数  
         * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行  
         * onProgressUpdate是在UI线程中执行，所以可以对UI控件进行操作  
         */  
        protected void onProgressUpdate(Integer... leftMargin) {  
            menuParams.leftMargin = leftMargin[0];  
            menu.setLayoutParams(menuParams);  
        }  
  
        @Override 
        /**
         * 这里的Integer参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
         * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI控件进行设置 
         */
        protected void onPostExecute(Integer leftMargin) {  
            menuParams.leftMargin = leftMargin;  
            menu.setLayoutParams(menuParams);  
        }  
    }  
  
    /** 
     * 使当前线程睡眠指定的毫秒数。 
     *  
     * @param millis 
     *            指定当前线程睡眠多久，以毫秒为单位 
     */  
    protected void sleep(long millis) {  
        try {  
            Thread.sleep(millis);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }
    /**
     * 在此处设置第一时间修改标题栏事件
     */
    @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_layout:
			// 当点击了消息tab时，选中第1个tab
			right.setText(R.string.title_home);
			setTabSelection(0);
			break;
		case R.id.contacts_layout:
			// 当点击了联系人tab时，选中第2个tab
			 right.setText(R.string.title_classify);
			setTabSelection(1);
			break;
		case R.id.news_layout:
			// 当点击了动态tab时，选中第3个tab
			 right.setText(R.string.title_business);
			setTabSelection(2);
			break;
		case R.id.setting_layout:
			//引用自定义全局类
			myApplication my=(myApplication) this.getApplication();
			System.out.println("1,"+my.isUSER_LOGIN());
			if(!my.isUSER_LOGIN()){
				Intent in=new Intent(this,User_Login.class);
				startActivity(in);
			}else{
			// 当点击了设置tab时，选中第4个tab
				right.setText(R.string.title_shopcar);
				setTabSelection(3);
			}
			break;
		default:
			break;
		}
	}

    /**
	 * 根据传入的index参数来设置选中的tab页。<br>
	 * @param index
	 * 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。<br>
	 * transaction.replace(R.id.content, homeFragment);是替换的方式，替换是直接将之前的Fragment移除，所有状态都将清除，只保留一个fragment<br>
	 * 而add方法是添加，并不是真正移除，只是写了一个隐藏方法将fragment隐藏起来，当需要的时候进行显示，那么打开多个fragment后会堆积多层
	 *当需要进行轮播时采用add
	 */
    public static void setTabSelection(int index) {
    	        // 每次选中之前先清楚掉上次的选中状态
    			clearSelection();
    			// 开启一个Fragment事务
    			FragmentTransaction transaction = fragmentManager.beginTransaction();
    			// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
    			hideFragments(transaction);
    			switch (index) {
    			case 0:
    				// 当点击了消息tab时，改变控件的图片和文字颜色
    				messageImage.setImageResource(R.drawable.message_selected);
    				messageText.setTextColor(Color.WHITE);
//   				if (homeFragment == null) {
    					// 如果MessageFragment为空，则创建一个并添加到界面上
    					homeFragment = new HomeFragment();
//    					transaction.add(R.id.content, homeFragment);
    					transaction.replace(R.id.content, homeFragment);
/*    				} else {
    					// 如果MessageFragment不为空，则直接将它显示出来
    					transaction.show(homeFragment);
    				}
*/ 
    				break;
    			case 1:
    				
    				// 当点击了联系人tab时，改变控件的图片和文字颜色
    				contactsImage.setImageResource(R.drawable.contacts_selected);
    				contactsText.setTextColor(Color.WHITE);
//    				if (classifyFragment == null) {
    					// 如果ContactsFragment为空，则创建一个并添加到界面上
    					classifyFragment = new ClassifyFragment();
 //   					transaction.add(R.id.content, classifyFragment);
    					transaction.replace(R.id.content, classifyFragment);
/*    				} else {
    					// 如果ContactsFragment不为空，则直接将它显示出来
    					transaction.show(classifyFragment);
    				}
*/    				break;
    			case 2:
    				// 当点击了动态tab时，改变控件的图片和文字颜色
    				newsImage.setImageResource(R.drawable.news_selected);
    				newsText.setTextColor(Color.WHITE);
//    				if (businessFragment == null) {
    					// 如果NewsFragment为空，则创建一个并添加到界面上
    					businessFragment = new BusinessFragment();
 //   					transaction.add(R.id.content, businessFragment);
    					transaction.replace(R.id.content, businessFragment);
 /*   				} else {
    					// 如果NewsFragment不为空，则直接将它显示出来
    					transaction.show(businessFragment);
    				}

 */   				break;
    			case 3:
    			default:
    				// 当点击了设置tab时，改变控件的图片和文字颜色
    				settingImage.setImageResource(R.drawable.setting_selected);
    				settingText.setTextColor(Color.WHITE);
 //   				if (shopcarFragment == null) {
    					// 如果SettingFragment为空，则创建一个并添加到界面上
    					shopcarFragment = new ShopcarFragment();
//    					transaction.add(R.id.content, shopcarFragment);
    					transaction.replace(R.id.content, shopcarFragment);
/*    				} else {
    					// 如果SettingFragment不为空，则直接将它显示出来
    					transaction.show(shopcarFragment);
   				}
 */   				break;
    			}
    			transaction.commit();
	}

    /**
	 * 清除掉所有的选中状态。
	 */
	protected static void clearSelection() {
		messageImage.setImageResource(R.drawable.message_unselected);
		messageText.setTextColor(Color.parseColor("#82858b"));
		contactsImage.setImageResource(R.drawable.contacts_unselected);
		contactsText.setTextColor(Color.parseColor("#82858b"));
		newsImage.setImageResource(R.drawable.news_unselected);
		newsText.setTextColor(Color.parseColor("#82858b"));
		settingImage.setImageResource(R.drawable.setting_unselected);
		settingText.setTextColor(Color.parseColor("#82858b"));
	}


	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	protected static void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (classifyFragment != null) {
			transaction.hide(classifyFragment);
		}
		if (businessFragment != null) {
			transaction.hide(businessFragment);
		}
		if (shopcarFragment != null) {
			transaction.hide(shopcarFragment);
		}
	}
}
