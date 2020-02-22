# MVP [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)  [![](https://jitpack.io/v/Bgtal/MVP.svg)](https://jitpack.io/#Bgtal/MVP)
> maven
```  
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        ...
    }
}
```
> 使用 support包请依赖 com.github.Bgtal:MVP:1.0.1   

> 使用 androidx包请依赖 com.github.Bgtal:MVP:x1.0.2   
## 项目依赖
#### support 包
>  implementation 'android.arch.lifecycle:common:1.1.1'  
#### androidx 包
>  implementation 'androidx.lifecycle:lifecycle-common:2.2.0'

## 类结构
```
|blq
|---ssnb
|-------mvp
|----------BasePresent.java
|----------MvpCallBack.java
|----------IAttachOrDetacheListener.java
|----------ILifecycleModel.java
|----------ILifecycleView.java
|----------IModel.java
|----------IPresenter.java
|----------IView.java

```

## 使用说明
1. 继承IModle IView 和IPresenter 分别添加所需要的接口
```
    /**
     * 一个登录的mvp集合
     */
    public interface LoginContract{
        /**
         * 登录的view回调方法
         */
        interface ILoginView extends IView{
            void onLoginSuccess(UserBean bean);
            void onLoginFail(String failMsg);
        }

        /**
         * 登录的model的方法
         */
        interface ILoginModel extends IModel{
            public void toLogin(String account, String password, MvpCallBack<UserBean> callBack);
        }

        /**
         * 登录的P方法
         */
        public abstract class AbsPresenter extends BasePresenter<ILoginView,ILoginModel>{
            public abstract void toLogin(String account,String password);
        }

    }
```

2. 实现相关的接口
具体实现就不写了

3. P方法的使用
```
public class MainActivity extends AppCompatActivity implements LoginContract.ILoginView{

    private LoginContract.AbsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化一个具体的实现类
        presenter = new LoginPresenter();

        //将presneter 添加到一个生命周期管理器中
        //1.如果 View 是继承LifecycleOwner（ILifecycleView 是继承 LifecycleOwner的）
        //那么在调用presenter.attach(IView)方法会自动加入生命周期管理

        //2.如果View 不是继承LifecycleOwner 那么就要手动添加生命周期监听  
        //getLifecycle().addObserver(presenter);  

        //3如果不调用这个方法的话，那么需要手动在onDestory中调用 presenter.detach()方法

        //P与view关联
        presenter.attach(this);

        //触发登录的请求
        presenter.toLogin("aaa","123456");

    }

    @Override
    public void onLoginSuccess(UserBean bean) {
        Log.e("MVP","登录成功："+bean.getID);
    }

    @Override
    public void onLoginFail(int errorCode ,String failMsg) {
        Log.e("MVP","登录失败："+errorCode+"-"+failMsg);
    }

    //===================如果没有添加生命周期监听的话那么就需要手动调用detach()方法来清空view和model
    /**
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.detach();
        }
    }**/
}

```


## 其他

- 邮箱: <blq_ssnb@outlook.com>
