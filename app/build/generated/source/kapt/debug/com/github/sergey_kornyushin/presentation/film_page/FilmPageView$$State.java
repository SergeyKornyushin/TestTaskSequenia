package com.github.sergey_kornyushin.presentation.film_page;

import com.github.sergey_kornyushin.domain.model.Film;
import java.lang.Override;
import java.lang.String;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;

public class FilmPageView$$State extends MvpViewState<FilmPageView> implements FilmPageView {
	@Override
	public void showError(String message) {
		ShowErrorCommand showErrorCommand = new ShowErrorCommand(message);
		this.viewCommands.beforeApply(showErrorCommand);

		if (hasNotView()) {
			return;
		}

		for (FilmPageView view : this.views) {
			view.showError(message);
		}

		this.viewCommands.afterApply(showErrorCommand);
	}

	@Override
	public void showLoading(boolean isLoading) {
		ShowLoadingCommand showLoadingCommand = new ShowLoadingCommand(isLoading);
		this.viewCommands.beforeApply(showLoadingCommand);

		if (hasNotView()) {
			return;
		}

		for (FilmPageView view : this.views) {
			view.showLoading(isLoading);
		}

		this.viewCommands.afterApply(showLoadingCommand);
	}

	@Override
	public void showFilm(Film film) {
		ShowFilmCommand showFilmCommand = new ShowFilmCommand(film);
		this.viewCommands.beforeApply(showFilmCommand);

		if (hasNotView()) {
			return;
		}

		for (FilmPageView view : this.views) {
			view.showFilm(film);
		}

		this.viewCommands.afterApply(showFilmCommand);
	}

	public class ShowErrorCommand extends ViewCommand<FilmPageView> {
		public final String message;

		ShowErrorCommand(String message) {
			super("showError", OneExecutionStateStrategy.class);

			this.message = message;
		}

		@Override
		public void apply(FilmPageView mvpView) {
			mvpView.showError(message);
		}
	}

	public class ShowLoadingCommand extends ViewCommand<FilmPageView> {
		public final boolean isLoading;

		ShowLoadingCommand(boolean isLoading) {
			super("showLoading", AddToEndSingleStrategy.class);

			this.isLoading = isLoading;
		}

		@Override
		public void apply(FilmPageView mvpView) {
			mvpView.showLoading(isLoading);
		}
	}

	public class ShowFilmCommand extends ViewCommand<FilmPageView> {
		public final Film film;

		ShowFilmCommand(Film film) {
			super("showFilm", AddToEndSingleStrategy.class);

			this.film = film;
		}

		@Override
		public void apply(FilmPageView mvpView) {
			mvpView.showFilm(film);
		}
	}
}
