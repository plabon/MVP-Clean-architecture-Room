package com.jukti.juktidictionary;

import com.jukti.dictionary.domain.interactors.UseCase;

/**
 * Created by Pl@b0n on 11,August,2018
 */
public class UseCaseHandler {

    private static UseCaseHandler instance;
    private final UseCaseScheduler useCaseScheduler;


    public UseCaseHandler(UseCaseScheduler useCaseScheduler) {
        this.useCaseScheduler = useCaseScheduler;
    }

    public static UseCaseHandler getInstance(){
        if(instance==null){
            instance = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return instance;
    }

    public<T extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute(final UseCase<T,R> useCase, T requestValues, UseCase.Callback<R> callback){

        useCase.setmCallback(new UIWrapperUseCaseCallBack<R>(callback, this));
        useCase.setmRequsetValues(requestValues);

        useCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run();
            }
        });
    }

    public <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                                 final UseCase.Callback<V> useCaseCallback) {
        useCaseScheduler.notifyRespoonse(response, useCaseCallback);
    }

    public <V extends UseCase.ResponseValue> void notifyError(final UseCase.Callback<V> useCaseCallback) {
        useCaseScheduler.notifyError(useCaseCallback);
    }

    private static final class UIWrapperUseCaseCallBack<V extends UseCase.ResponseValue> implements UseCase.Callback<V>{
        private final UseCase.Callback<V> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        public UIWrapperUseCaseCallBack(UseCase.Callback<V> callback,
                                 UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }
        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response,mCallback);
        }

        @Override
        public void onError() {
            mUseCaseHandler.notifyError(mCallback);

        }
    }

}
