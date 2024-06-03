package Interface;

public interface ResponseCallback<T> {
    void onSuccess(T data);
    void onFail(Throwable th);
}
