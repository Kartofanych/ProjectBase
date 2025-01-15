package com.onboarding.impl.ui

sealed interface OnboardingEvent {

    object OpenLogin : OnboardingEvent
}