package com.github.sergey_kornyushin.presentation.film_page;

import java.lang.Override;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class FilmPagePresenter$Base$$ViewStateProvider extends ViewStateProvider {
	@Override
	public MvpViewState<? extends MvpView> getViewState() {
		return new FilmPageView$$State();
	}
}
