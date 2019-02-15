package blq.ssnb.mvp;


import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/7/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * MVP 中 P 的抽象类,实现 M和V的绑定和解绑功能
 * ================================================
 * </pre>
 */
public abstract class BasePresenter<V extends IView,M extends IModel> implements IPresenter<V,M> {
    private WeakReference<V> mViewWeakReference;
    private M mModel;

    @NonNull
    @Override
    public M getModel() {
        if(mModel == null){
            mModel = initModel();
        }
        return mModel;
    }

    @Override
    public V getView() {
        if(mViewWeakReference != null){
            return mViewWeakReference.get();
        }
        return null;
    }

    @Override
    public void attach(@NonNull V view) {
        if(mViewWeakReference == null){
            mViewWeakReference = new WeakReference<>(view);
        }else{
            if(getView() != null && getView() instanceof IAttachOrDetachListener){
                ((IAttachOrDetachListener)getView()).onMvpDetach();
            }
            mViewWeakReference.clear();
            mViewWeakReference = new WeakReference<>(view);
        }
        if(view instanceof LifecycleOwner){
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        if(view instanceof IAttachOrDetachListener){
            ((IAttachOrDetachListener)view).onMvpAttach();
        }
        if(getModel() instanceof IAttachOrDetachListener){
            ((IAttachOrDetachListener)getModel()).onMvpAttach();
        }

    }

    @Override
    public void detach() {
        if(mViewWeakReference != null){
            if(getView() != null && getView() instanceof IAttachOrDetachListener){
                ((IAttachOrDetachListener)getView()).onMvpDetach();
            }
            mViewWeakReference.clear();
            mViewWeakReference = null;
        }

        if(mModel != null){
            if(mModel instanceof IAttachOrDetachListener){
                ((IAttachOrDetachListener)mModel).onMvpDetach();
            }
            mModel = null;
        }
    }
}
