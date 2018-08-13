package com.jukti.dictionary.domain.interactors;

/**
 * Created by Pl@b0n on 11,August,2018
 */
/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Q> the request type
 * @param <P> the response type
 */
public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequsetValues;
    private Callback<P> mCallback;

    public Q getmRequsetValues() {
        return mRequsetValues;
    }

    public void setmRequsetValues(Q mRequsetValues) {
        this.mRequsetValues = mRequsetValues;
    }

    public Callback<P> getmCallback() {
        return mCallback;
    }

    public void setmCallback(Callback<P> mCallback) {
        this.mCallback = mCallback;
    }


    public interface Callback<R> {
        void onSuccess(R returnObject);
        void onError();
    }

    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }

    /**
     * Data received from a request.
     */
    public interface ResponseValue {
    }

    public void run(){
        execute(mRequsetValues);
    }

    public abstract void execute(Q requestValues);
}
