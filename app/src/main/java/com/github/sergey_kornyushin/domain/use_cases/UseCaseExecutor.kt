package com.github.sergey_kornyushin.domain.use_cases

interface UseCaseExecutor<T> {
    fun executeUseCase(useCaseResult: T)
}