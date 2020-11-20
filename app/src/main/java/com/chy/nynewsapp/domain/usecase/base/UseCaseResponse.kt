package com.chy.nynewsapp.domain.usecase.base

import com.chy.nynewsapp.domain.model.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}

