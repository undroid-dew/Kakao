package com.agoda.kakao

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.agoda.kakao.intercept.Interceptor

object Kakao {
    internal var viewInteraction: Interceptor<ViewInteraction, ViewAssertion, ViewAction>? = null
    internal var dataInteraction: Interceptor<DataInteraction, ViewAssertion, ViewAction>? = null
    internal var webInteraction: Interceptor<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>? = null

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your Kakao runtime
     */
    operator fun invoke(function: Kakao.() -> Unit) {
        function(this)
    }

    /**
     * Sets the interceptors for the whole Kakao runtime.
     * Interceptors will be invoked on all of the interactions with the KView instances.
     *
     * @param configurator Configuration of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(configurator: Interceptor.Configurator.() -> Unit) {
        Interceptor.Configurator().apply(configurator).configure().also {
            viewInteraction = it.first
            dataInteraction = it.second
            webInteraction = it.third
        }
    }

    /**
     * Removes the interceptors from the Kakao runtime.
     *
     * @see intercept
     * @see Interceptor
     */
    fun reset() {
        viewInteraction = null
        dataInteraction = null
        webInteraction = null
    }
}
