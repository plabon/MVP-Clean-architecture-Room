package com.jukti.juktidictionary;

import com.jukti.dictionary.domain.interactors.UseCase;

/**
 * Created by Pl@b0n on 11,August,2018
 */
public interface UseCaseScheduler {
    void execute(Runnable runnable);
    <V extends UseCase.ResponseValue> void notifyRespoonse(final V response, final UseCase.Callback<V> callback);
    <V extends UseCase.ResponseValue> void notifyError(final UseCase.Callback<V> callback);
}
