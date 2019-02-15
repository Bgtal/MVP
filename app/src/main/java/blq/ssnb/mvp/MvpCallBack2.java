package blq.ssnb.mvp;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/15
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public interface MvpCallBack2<T> extends MvpCallBack<T> {
    void onFail(int errorCode,String msg);
}
