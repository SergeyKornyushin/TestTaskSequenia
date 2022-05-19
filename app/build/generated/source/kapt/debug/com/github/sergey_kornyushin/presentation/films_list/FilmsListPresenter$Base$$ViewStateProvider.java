package com.github.sergey_kornyushin.presentation.films_list;

import java.lang.Override;
import moxy.MvpView;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class FilmsListPresenter$Base$$ViewStateProvider extends ViewStateProvider {
	@Override
	public MvpViewState<? extends MvpView> getViewState() {
		return new FilmsListView$$State();
	}
}
