package blq.ssnb.mvp;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/7/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 绑定和解绑监听
 * ================================================
 * </pre>
 */
public interface IAttachOrDetachListener {
    /**
     * 绑定的时候需要调用该方法
     */
    void onMvpAttach();

    /**
     * 解绑的时候需要调用该方法
     */
    void onMvpDetach();
}
