package blq.ssnb.mvp;


import android.arch.lifecycle.LifecycleOwner;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/7/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 带有生命周期监听的 MVP-V类
 * ================================================
 * </pre>
 */
public interface ILifecycleView extends IView,LifecycleOwner,IAttachOrDetachListener {}
