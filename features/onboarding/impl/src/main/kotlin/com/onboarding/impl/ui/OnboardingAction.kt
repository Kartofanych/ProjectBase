package com.onboarding.impl.ui

sealed interface OnboardingAction {

    object OnFinish : OnboardingAction
}