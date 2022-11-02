package com.delizarov.mvpcalculator

interface MvpView

abstract class BasePresenter<TView : MvpView> {

    private var _view: TView? = null
    protected val view: TView
        get() = requireNotNull(_view) { "attempt to access in detached state"}

    fun attach(view: TView) {
        _view = view
    }

    fun detachView() {
        _view = null
    }
}